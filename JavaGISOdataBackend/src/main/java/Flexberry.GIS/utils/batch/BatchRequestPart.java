package Flexberry.GIS.utils.batch;

import java.util.*;

public class BatchRequestPart {
    public String boundaryId;
    public boolean isChangeSet = false;
    public String changeSetId = "";
    public HashMap<String, String> headers = new HashMap<>();
    public ArrayList<BatchSubRequest> requests = new ArrayList<>();

    public String ContentType = "";

    public BatchRequestPart(String boundaryId) {
        this.boundaryId = boundaryId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(key + ": " + value + System.lineSeparator());
        }

        sb.append(System.lineSeparator());

        if (isChangeSet) {
            for (BatchSubRequest batchSubRequest : requests) {
                sb.append("--" + changeSetId);
                sb.append(System.lineSeparator());
                sb.append(batchSubRequest.toString());
            }

            sb.append(System.lineSeparator());
            sb.append("--" + changeSetId + "--");
            sb.append(System.lineSeparator());
        } else {
            sb.append(requests.get(0).toString());
        }

        return sb.toString();
    }
}
