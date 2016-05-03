package albert.practice.mail.params;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private String receiverEmail; // 收件者 (必填)
    private String subject; // 主旨 (必填)
    private String content; // 內文 (必填)
    private List<Attachment> encodedBytes; // encoded base64
    private List<File> attachments; // 附加檔案 (非必填)
    private List<Attachment> attachmentsForMcDe; // 附加檔案 (非必填)

}
