package albert.practice.mail.params;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class EmailParams implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String receiverEmail; // 收件者 (必填)
    private String subject; // 主旨 (必填)
    private String content; // 內文 (必填)
    private List<Attachment> encodedBytes; // encoded base64
    private List<File> attachments; // 附加檔案 (非必填)

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public List<Attachment> getEncodedBytes() {
        return encodedBytes;
    }

    public void setEncodedBytes(List<Attachment> encodedBytes) {
        this.encodedBytes = encodedBytes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
