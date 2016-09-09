package albert.practice.validator;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.regex.Pattern;

import org.w3c.tidy.Tidy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatorUtils {

    public Boolean isEmailValid(String email) {
        Boolean isValid = Boolean.FALSE;

        Pattern EMAIL_PATTERN = Pattern.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");
        if (EMAIL_PATTERN.matcher(email).matches()) {
            isValid = Boolean.TRUE;
        }

        return isValid;
    }

    public Boolean isPhoneNumberValid(String mobilePhoneNumber) {
        Boolean isValid = Boolean.FALSE;

        Pattern MSISDN_PATTERN = Pattern.compile("[+-]?\\d{10,12}");

        if (MSISDN_PATTERN.matcher(mobilePhoneNumber).matches()) {
            isValid = Boolean.TRUE;
        }

        return isValid;
    }

    public Boolean isHtmlValid(String htmlString) {
        Boolean isValid = Boolean.FALSE;

        Tidy tidy = new Tidy();
        StringWriter writer = new StringWriter();
        tidy.parse(new StringReader(htmlString), writer);
        int errors = tidy.getParseErrors();
        if (errors == 0) {
            isValid = Boolean.TRUE;
        }
        return isValid;
    }

    /**
     * characters only, no digits.
     * 
     * @param str
     * @return
     */
    public Boolean isCharacterOnly(String str) {
        Boolean isValid = Boolean.FALSE;
        Pattern pattern = Pattern.compile("^[^0-9]*$");
        if (pattern.matcher(str).matches()) {
            isValid = Boolean.TRUE;
        }
        return isValid;
    }

    public Boolean isRedmineProjectIdentifierValid(String str) {
        Boolean isValid = Boolean.FALSE;
        Pattern pattern = Pattern.compile("^[a-z0-9_=]*$");
        if (pattern.matcher(str).matches()) {
            isValid = Boolean.TRUE;
        }
        return isValid;
    }
    
    public static void main(String[] args) {
        ValidatorUtils validator = new ValidatorUtils();

        String validEmail = "xxx@dsc.com";
        String invalidEmail = "xxxdsc.com";

        String validPhoneNum = "0912123456";
        String invalidPhoneNum = "091212345";

        System.out.println(validator.isEmailValid(validEmail));
        System.out.println(validator.isEmailValid(invalidEmail));
        System.out.println(validator.isPhoneNumberValid(validPhoneNum));
        System.out.println(validator.isPhoneNumberValid(invalidPhoneNum));

        // StringBuilder htmlStr = getHtmlStr();
        // System.out.println(validator.isHtmlValid(htmlStr.toString()));
        log.debug("isCharacterOnly= " + validator.isCharacterOnly("abc"));
        log.debug("isCharacterOnly= " + validator.isCharacterOnly("abc123"));
        log.debug("isCharacterOnly= " + validator.isCharacterOnly("測試a123bc"));
        
        log.debug("isRedmineProjectIdentifierValid = " + validator.isRedmineProjectIdentifierValid("=test_123"));
    }

    private static StringBuilder getHtmlStr() {
        StringBuilder htmlStr = new StringBuilder();
        htmlStr.append("<html>");
        htmlStr.append("   <body>");
        htmlStr.append("      <p>親愛的保戶您好</p>");
        htmlStr.append("      <p>     </p>");
        htmlStr.append("      <p>感謝您對xxxx的支持，您在網路上申請的旅平險保單已投保完成，以下是您的投保明細，供您參考。</p>");
        htmlStr.append("      <p>【投保內容】</p>");
        htmlStr.append("      <p>保單號碼: ${customer.policyNumber}</p>");
        htmlStr.append("      <p>被保險人: ${customer.name}</p>");
        htmlStr.append("      <p>申請日期: ${customer.applyDate}</p>");
        htmlStr.append("      <p>保險期間: ${customer.fromDate} ~ ${customer.toDate}</p>");
        htmlStr.append("      <p>旅遊地點: ${customer.place}</p>");
        htmlStr.append("      <p></p>");
        htmlStr.append("      <p>※保險單及保險費送金單將於近日內寄至要保人所指定之聯絡地址。</p>");
        htmlStr.append("      <p></p>");
        htmlStr.append("      <p>                     敬祝  闔家平安 </p>");
        htmlStr.append("      <p><img src='cid:panda'></p>");
        htmlStr.append("   </body>");
        htmlStr.append("</html>");
        return htmlStr;
    }
}
