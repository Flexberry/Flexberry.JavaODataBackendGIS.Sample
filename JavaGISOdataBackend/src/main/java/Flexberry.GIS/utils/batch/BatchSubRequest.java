package Flexberry.GIS.utils.batch;

import java.util.HashMap;
import java.util.Map;

public class BatchSubRequest {
    public HashMap<String, String> headers = new HashMap<>();
    public String queryPart = "";
    public HashMap<String, String> queryHeaders = new HashMap<>();
    public String bodyPart = "";

    public BatchSubRequest() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(key + ": " + value + System.lineSeparator());
        }

        if (headers.size() > 0) sb.append(System.lineSeparator());

        sb.append(queryPart);
        sb.append(System.lineSeparator());

        for (Map.Entry<String, String> entry : queryHeaders.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(key + ": " + value + System.lineSeparator());
        }

        if (bodyPart.length() > 0) {
            sb.append(System.lineSeparator());
            sb.append(bodyPart);
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }
}
