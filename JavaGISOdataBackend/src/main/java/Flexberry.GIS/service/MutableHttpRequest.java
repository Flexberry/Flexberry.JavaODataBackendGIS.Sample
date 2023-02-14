package Flexberry.GIS.service;

import org.json.JSONObject;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MutableHttpRequest extends HttpServletRequestWrapper {
    private String body;
    private List<String> excessHeaders;
    private Map<String,String> replacedQuerySubstrings;

    private List<String> existedEntities;

    public MutableHttpRequest(HttpServletRequest request) throws IOException {
        super(request);

        excessHeaders = new ArrayList<String>();
        replacedQuerySubstrings = new HashMap<String,String>();

        catchRequestBody(request);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
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
        body = convertGISJsonValuesToString(body);
        body = body.replace(originSubstring, finalSubstring);
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

        // Меняем в строке запроса дополнительные подстроки, сформированные в классе MutableHttpFilter.
        for (Map.Entry<String, String> entry : replacedQuerySubstrings.entrySet()) {
            modifiedQueryString = modifiedQueryString.replace(entry.getKey(), entry.getValue());
        }

        return modifiedQueryString;
    }

    private String convertGISJsonValuesToString(String body) {
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
}
