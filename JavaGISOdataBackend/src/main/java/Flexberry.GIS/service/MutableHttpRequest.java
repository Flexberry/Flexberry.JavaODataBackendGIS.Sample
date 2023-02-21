package Flexberry.GIS.service;

import Flexberry.GIS.utils.batch.BatchRequest;
;
import Flexberry.GIS.utils.batch.BatchRequestPart;
import Flexberry.GIS.utils.batch.BatchSubRequest;
import org.json.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MutableHttpRequest extends HttpServletRequestWrapper {
    private String body;
    private List<String> excessHeaders;
    private Map<String, String> replacedQuerySubstrings;
    private Map<String, String> replacedBodySubstrings;
    private List<String> existedEntities;

    private Boolean isBatchRequest = false;
    private String batchBoundaryId = null;
    BatchRequest batchRequest = null;

    public MutableHttpRequest(HttpServletRequest request) throws IOException {
        super(request);

        excessHeaders = new ArrayList<String>();
        existedEntities = new ArrayList<String>();
        replacedQuerySubstrings = new HashMap<String,String>();
        replacedBodySubstrings = new HashMap<String,String>();

        catchRequestBody(request);

        String currentUrl = request.getRequestURL().toString();

        // batch запрос.
        if (currentUrl.endsWith("$batch")) {
            batchBoundaryId = getBatchBoundary(request);

            if (batchBoundaryId != null) {
                try {
                    batchRequest = new BatchRequest(currentUrl, body, batchBoundaryId);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                isBatchRequest = true;
            }
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        String thisBody = body;

        if (isBatchRequest) {
            thisBody = batchRequest.toString();
        }

        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(thisBody.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };

        return servletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public Enumeration getHeaders(String name) {
        List values = Collections.list(super.getHeaders(name));

        for (String excessHeader : excessHeaders)
        {
            if(name.equalsIgnoreCase(excessHeader)) {
                values.clear();
            }
        }

        return Collections.enumeration(values);
    }

    @Override
    public String getQueryString() {
        String queryString = super.getQueryString();

        if (queryString == null) {
            return null;
        }

        String modifiedQueryString = modifyQueryString(queryString);

        return modifiedQueryString;
    }

    public void deleteExcessHeader(String header) {
        excessHeaders.add(header);
    }

    public void replaceSubstringInQuery(String originSubstring, String finalSubstring) {
        replacedQuerySubstrings.put(originSubstring, finalSubstring);
    }

    public void replaceSubstringInBody(String originSubstring, String finalSubstring) {
        replacedBodySubstrings.put(originSubstring, finalSubstring);
    }

    public void fixPrimaryKeyValuesInBody() {
        body = fixPrimaryKeyValuesInBody(body);
    }

    public static String fixPrimaryKeyValuesInBody(String strBody) {
        JSONObject currentBodyJson;

        if (strBody == null || strBody.length() == 0) return strBody;

        try {
            currentBodyJson = new JSONObject(strBody);
        } catch (Exception ignored) {
            return strBody;
        }

        ArrayList<String> keysToRemove = new ArrayList<>();

        for (String keyStr : currentBodyJson.keySet()) {
            if (keyStr.endsWith("@odata.bind")) {
                String keyValue = currentBodyJson.get(keyStr).toString();

                if (keyValue.equalsIgnoreCase("null")) {
                    keysToRemove.add(keyStr);
                } else if (!keyValue.contains("\'")) {
                    keyValue = keyValue.replace("(", "('").replace(")", "')");
                    currentBodyJson.put(keyStr, keyValue);
                }
            }
        }

        for (String keyStr : keysToRemove) {
            currentBodyJson.remove(keyStr);
        }

        return currentBodyJson.toString();
    }

    public void setExistingEntitiesList(List<String> entities)
    {
        existedEntities = entities;
    }

    private void catchRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];

                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    String originalString = new String(charBuffer);
                    charBuffer = originalString.toCharArray();

                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
    }

    private String modifyQueryString(String originString) {
        String modifiedQueryString = originString;

        // olingo-jpa-processor не поддерживает, чтобы в запросе, в select указывались параметры, которые указывают на связанные сущности.
        // Они должны запрашиваться только через expand.
        // Находим блоки select и удаляем из них связанные сущности, список которых сформирован в классе Servlet.
        for (String entityName : existedEntities) {
            Pattern pattern = Pattern.compile("\\(%24select(.*?)\\)|%24select=(.*?)$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(modifiedQueryString);

            while(matcher.find())
            {
                String findedSelect = matcher.group();

                String selectWithoutEntities = findedSelect.replaceAll("%2C" + entityName + "$", "");
                selectWithoutEntities = selectWithoutEntities.replaceAll("%2C" + entityName + "%", "%");
                selectWithoutEntities = selectWithoutEntities.replaceAll("%2C" + entityName + "\\)", "\\)");

                modifiedQueryString = modifiedQueryString.replace(findedSelect, selectWithoutEntities);
            }
        }

        // filter PrimaryKey
        Pattern patternFilterPk = Pattern.compile("%24filter=[^\\%]*__PrimaryKey[^\\%\\,]*\\+[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", Pattern.CASE_INSENSITIVE);
        Matcher matcherFilterPk = patternFilterPk.matcher(modifiedQueryString);

        while(matcherFilterPk.find())
        {
            String foundPkFilter = matcherFilterPk.group();
            int foundPkFilterLength = foundPkFilter.length();

            String fixedPkFiler = foundPkFilter.substring(0, foundPkFilterLength - 36) +
                    "'" + foundPkFilter.substring(foundPkFilterLength - 36, foundPkFilterLength) + "'";

            modifiedQueryString = modifiedQueryString.replace(foundPkFilter, fixedPkFiler);
        }

        // PrimaryKey в прямом запросе
        patternFilterPk = Pattern.compile("http:\\/\\/.*\\/FlexberryGISService\\/odata\\/.*\\([0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", Pattern.CASE_INSENSITIVE);
        matcherFilterPk = patternFilterPk.matcher(modifiedQueryString);

        while(matcherFilterPk.find())
        {
            String foundPkFilter = matcherFilterPk.group();
            int foundPkFilterLength = foundPkFilter.length();

            String fixedPkFiler = foundPkFilter.substring(0, foundPkFilterLength - 36) +
                    "'" + foundPkFilter.substring(foundPkFilterLength - 36, foundPkFilterLength) + "'";

            modifiedQueryString = modifiedQueryString.replace(foundPkFilter, fixedPkFiler);
        }

        // Меняем в строке запроса дополнительные подстроки, сформированные в классе MutableHttpFilter.
        for (Map.Entry<String, String> entry : replacedQuerySubstrings.entrySet()) {
            modifiedQueryString = modifiedQueryString.replace(entry.getKey(), entry.getValue());
        }

        return modifiedQueryString;
    }

    public static String convertGISJsonValuesToString(String body) {
        if (body.equals("")) return body;
        
        // Olingo не понимает тип JSON, только строку. Поэтому нужно преобразовать значение полей с геоданными в строку.
        String convertedBody = body;

        Pattern pattern = Pattern.compile("\"BoundingBox\":\\{(.*?)\\}\\}\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(convertedBody);

        while(matcher.find())
        {
            String findedSelect = matcher.group();
            String modifyFinded = findedSelect.replaceAll("\"","\\\\\"");
            modifyFinded = modifyFinded.replaceAll("\\\\\"BoundingBox\\\\\"", "\"BoundingBox\"");
            modifyFinded = modifyFinded.replaceAll("\"BoundingBox\":\\{", "\"BoundingBox\":\"{");
            modifyFinded = modifyFinded + "\"";

            convertedBody = convertedBody.replace(findedSelect, modifyFinded);
        }

        return convertedBody;
    }

    public static String getBatchBoundary(HttpServletRequest request) {
        String contentType =  request.getContentType();
        int indexBoundary = contentType.indexOf("boundary=");

        if (indexBoundary >= 0) {
            String[] arr = contentType.substring(indexBoundary).split("[=;]");

            if (arr.length > 1) return arr[1];
        }

        return null;
    }

    public void ApplyChangesForRequest() {
        if (this.isBatchRequest) {
            for (BatchRequestPart batchRequestPart : batchRequest.parts) {
                for (BatchSubRequest batchSubRequest : batchRequestPart.requests) {
                    // body
                    batchSubRequest.bodyPart = convertGISJsonValuesToString(batchSubRequest.bodyPart);

                    for (Map.Entry<String, String> entry : replacedBodySubstrings.entrySet()) {
                        batchSubRequest.bodyPart = batchSubRequest.bodyPart.replace(entry.getKey(), entry.getValue());
                    }

                    batchSubRequest.bodyPart = fixPrimaryKeyValuesInBody(batchSubRequest.bodyPart);

                    // query
                    batchSubRequest.queryPart = modifyQueryString(batchSubRequest.queryPart);

                    // headers
                    for (String excessHeader : excessHeaders) {
                        batchSubRequest.headers = BatchRequest.removeHeader(batchSubRequest.headers, excessHeader);
                        batchSubRequest.queryHeaders = BatchRequest.removeHeader(batchSubRequest.queryHeaders, excessHeader);
                    }
                }

                // headers
                for (String excessHeader : excessHeaders) {
                    batchRequestPart.headers = BatchRequest.removeHeader(batchRequestPart.headers, excessHeader);
                }
            }
        } else {
            body = convertGISJsonValuesToString(body);

            for (Map.Entry<String, String> entry : replacedBodySubstrings.entrySet()) {
                body = body.replace(entry.getKey(), entry.getValue());
            }

            fixPrimaryKeyValuesInBody();
        }
    }
}
