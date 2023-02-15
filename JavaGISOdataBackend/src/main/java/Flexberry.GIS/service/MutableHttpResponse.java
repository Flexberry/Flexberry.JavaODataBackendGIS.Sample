package Flexberry.GIS.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MutableHttpResponse extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream capture;
    private ServletOutputStream output;
    private PrintWriter writer;
    private HttpServletResponse currentResponse;
    private String originalContent;

    public MutableHttpResponse(HttpServletResponse response) {
        super(response);

        capture = new ByteArrayOutputStream(response.getBufferSize());
        currentResponse = response;
    }

    @Override
    public ServletOutputStream getOutputStream() {
        if (writer != null) {
            String errorMessage = "getWriter() has already been called on this response.";
            throw new IllegalStateException(errorMessage);
        }

        if (output == null) {
            output = new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    capture.write(b);
                }

                @Override
                public void flush() throws IOException {
                    capture.flush();
                }

                @Override
                public void close() throws IOException {
                    capture.close();
                }
            };
        }

        return output;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (output != null) {
            String errorMessage = "getOutputStream() has already been called on this response.";
            throw new IllegalStateException(errorMessage);
        }

        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(capture,
                    getCharacterEncoding()));
        }

        return writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();

        if (writer != null) {
            writer.flush();
        } else if (output != null) {
            output.flush();
        }
    }

    public void InitOriginalContent() throws IOException {
        this.originalContent = this.getCaptureAsString();
    }

    public void SetupOrdinalContent() throws IOException {
        this.currentResponse.setContentLength(this.originalContent.length());
        this.currentResponse.getOutputStream().write(this.originalContent.getBytes());
    }

    public void replaceSubstringInResponse(String originSubstring, String finalSubstring) {
        this.originalContent = this.originalContent.replace(originSubstring, finalSubstring);
    }

    private byte[] getCaptureAsBytes() throws IOException {
        if (writer != null) {
            writer.close();
        } else if (output != null) {
            output.close();
        }

        return capture.toByteArray();
    }

    private String getCaptureAsString() throws IOException {
        return new String(getCaptureAsBytes(), getCharacterEncoding());
    }

    public void convertAttributeToJson(String attrName) {
        try {
            String currentCaptureValue = this.originalContent;
            JSONObject currentCaptureJson = new JSONObject(currentCaptureValue);
            JSONArray arrValues = currentCaptureJson.optJSONArray("value");

            for (Object  tObj: arrValues) {
                if (tObj instanceof JSONObject) {
                    JSONObject jObj = (JSONObject) tObj;
                    String attrValue = jObj.optString(attrName);
                    JSONObject attrJson = new JSONObject(attrValue);

                    jObj.put(attrName, attrJson);
                }
            }

            this.originalContent = currentCaptureJson.toString();
        } catch (Exception ignored) {

        }
    }
}
