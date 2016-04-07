package albert.practice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ServiceTypeEnum {

    SMS("SMS", "OTP簡訊發送服務"), EMAIL("EMAIL", "電子郵件發送服務"), TIF("TIF", "檔案調閱服務");

    @Getter
    private String type;

    @Getter
    private String name;

}
