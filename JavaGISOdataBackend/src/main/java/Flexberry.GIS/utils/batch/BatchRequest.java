package Flexberry.GIS.utils.batch;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchRequest {
    public String url;
    public String body;
    public String boundaryId;
    public List<BatchRequestPart> parts;

    public BatchRequest(String url, String body, String boundaryId) throws ParseException {
        this.url = url;
        this.body = body;
        this.boundaryId = boundaryId;

        parseBody();
    }

    private void parseBody() throws ParseException {
        parts = new ArrayList<>();
        String[] lines = body.split(System.lineSeparator());

        for (int i = 0; i < lines.length; i++) lines[i]= lines[i].trim();

        String boundaryCheckValue = "--" + boundaryId + "--";
        Integer endIndex = SeekLinesToValue(0, lines, boundaryCheckValue);

        // Если не нашли конца запроса, то вернем ошибку.
        if (endIndex >= lines.length) throw getParseException(boundaryCheckValue, endIndex, 0);

        Integer currentIndex = SeekEmptyLines(0, lines);

        while (currentIndex < endIndex) {
            boundaryCheckValue = "--" + boundaryId;

            // Каждый блок должен начинаться с --ID запроса
            if (!lines[currentIndex].equals(boundaryCheckValue)) throw getParseException(boundaryCheckValue, currentIndex, 0);

            currentIndex++;
            BatchRequestPart batchRequestPart = new BatchRequestPart(boundaryId);

            // Заголовки.
            batchRequestPart.headers = getHeaders(currentIndex, lines, endIndex);
            currentIndex += batchRequestPart.headers.size();

            for (Map.Entry<String, String> entry : batchRequestPart.headers.entrySet()) {
                if (entry.getKey().equals("Content-Type")) batchRequestPart.ContentType = entry.getValue();
            }

            currentIndex = SeekEmptyLines(currentIndex, lines);

            // Changeset.
            if (batchRequestPart.ContentType.startsWith("multipart/mixed;boundary=")) {
                String changeSetId = batchRequestPart.ContentType.substring("multipart/mixed;boundary=".length());
                String changeSetIdCheckValue = "--" + changeSetId + "--";
                Integer changeSetEndIndex = SeekLinesToValue(currentIndex, lines, changeSetIdCheckValue);

                // Если не нашли конца Changeset, то вернем ошибку.
                if (changeSetEndIndex >= endIndex) throw getParseException(changeSetIdCheckValue, changeSetEndIndex, 0);

                batchRequestPart.isChangeSet = true;
                batchRequestPart.changeSetId = changeSetId;
                changeSetIdCheckValue = "--" + changeSetId;

                // Каждый блок должен начинаться с --Changeset.
                if (!lines[currentIndex].equals(changeSetIdCheckValue)) throw getParseException(changeSetIdCheckValue, currentIndex, 0);

                // Пока встречаются --Changeset.
                while (currentIndex < changeSetEndIndex && lines[currentIndex].equals(changeSetIdCheckValue)) {
                    currentIndex++;
                    BatchSubRequest batchSubRequest = new BatchSubRequest();

                    // Заголовки.
                    batchSubRequest.headers = getHeaders(currentIndex, lines, changeSetEndIndex);
                    currentIndex += batchSubRequest.headers.size();

                    currentIndex = SeekEmptyLines(currentIndex, lines);

                    // Строка запроса.
                    batchSubRequest.queryPart = lines[currentIndex];
                    currentIndex = SeekEmptyLines(currentIndex + 1, lines);

                    // Заголовки.
                    batchSubRequest.queryHeaders = getHeaders(currentIndex, lines, changeSetEndIndex);
                    currentIndex += batchSubRequest.queryHeaders.size();
                    currentIndex = SeekEmptyLines(currentIndex, lines);

                    // Тело запроса.
                    StringBuilder bodyBuilder = new StringBuilder();

                    while (currentIndex < changeSetEndIndex && !lines[currentIndex].startsWith("--")) {
                        if (lines[currentIndex].length() > 0) {
                            bodyBuilder.append(lines[currentIndex]);
                        }

                        currentIndex++;
                    }

                    batchSubRequest.bodyPart = bodyBuilder.toString();
                    batchRequestPart.requests.add(batchSubRequest);
                }

                // Пропускаем --Changeset--.
                currentIndex = SeekEmptyLines(currentIndex + 1, lines);
            } else {
                // Не Changeset запрос.
                BatchSubRequest batchSubRequest = new BatchSubRequest();

                // Строка запроса.
                batchSubRequest.queryPart = lines[currentIndex];
                currentIndex = SeekEmptyLines(currentIndex + 1, lines);

                // Заголовки.
                batchSubRequest.queryHeaders = getHeaders(currentIndex, lines, endIndex);
                currentIndex += batchSubRequest.queryHeaders.size();
                currentIndex = SeekEmptyLines(currentIndex, lines);

                // Тело запроса.
                StringBuilder bodyBuilder = new StringBuilder();

                while (currentIndex < endIndex && !lines[currentIndex].startsWith("--")) {
                    if (lines[currentIndex].length() > 0) {
                        bodyBuilder.append(lines[currentIndex]);
                    }

                    currentIndex++;
                }

                batchSubRequest.bodyPart = bodyBuilder.toString();
                batchRequestPart.requests.add(batchSubRequest);
            }

            parts.add(batchRequestPart);
        }
    }

    public static int SeekEmptyLines(Integer currentIndex, String[] lines) {
        Integer ind = currentIndex;

        while(ind < lines.length && lines[ind].length() == 0) ind++;

        return ind;
    }

    public static int SeekLinesToValue(Integer currentIndex, String[] lines, String value) {
        Integer ind = currentIndex;

        while(ind < lines.length && !lines[ind].equals(value)) ind++;

        return ind;
    }

    public static HashMap<String, String> getHeaders(Integer startIndex, String[] lines, Integer endIndex) throws ParseException {
        HashMap<String, String> headers = new HashMap<>();
        Integer currentIndex = startIndex;

        while (currentIndex <= endIndex && lines[currentIndex].length() > 0) {
            String header = lines[currentIndex];
            Integer nameLength = header.indexOf(":");

            // Проверка заголовка.
            if (nameLength <= 0) throw getParseException(":", currentIndex, header.length());

            String headerName = header.substring(0, nameLength);
            String headerValue = header.substring(nameLength + 1).trim();

            headers.put(headerName, headerValue);
            currentIndex++;
        }

        return headers;
    }

    public static HashMap<String, String> removeHeader(HashMap<String, String> headers, String removeValue) {
        String keyValue = null;

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(removeValue)) {
                keyValue = entry.getKey();
            }
        }

        if (keyValue != null) headers.remove(keyValue);

        return headers;
    }

    public static ParseException getParseException(String str, Integer lineNumber, Integer position) {
        return new ParseException("Ожидается значение '" + str + "' в строке " + lineNumber.toString()
                + " на позиции " + position.toString(), lineNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (BatchRequestPart batchRequestPart : parts) {
            sb.append(System.lineSeparator());
            sb.append(System.lineSeparator());
            sb.append("--" + boundaryId);
            sb.append(System.lineSeparator());
            sb.append(batchRequestPart.toString());
        }

        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("--" + boundaryId + "--");

        return sb.toString();
    }
}
