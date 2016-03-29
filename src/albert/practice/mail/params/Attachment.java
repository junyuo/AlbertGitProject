package albert.practice.mail.params;

import java.io.Serializable;

public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName;
    private byte[] bytes;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
