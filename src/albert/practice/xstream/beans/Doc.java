package albert.practice.xstream.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Doc implements Serializable {

    private static final long serialVersionUID = 1L;

    // 訂單編號
    private String regId;

    // 要保人ID
    private String ownerId;

    // 保單號碼
    private String policyNumber;

    // 被保人 ID
    private String insuredId;

    // 表單代碼
    private String formId;

}
