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
        log.debug("isCharacterOnly= " + validator.isCharacterOnly("����a123bc"));
        
        log.debug("isRedmineProjectIdentifierValid = " + validator.isRedmineProjectIdentifierValid("=test_123"));
    }

    private static StringBuilder getHtmlStr() {
        StringBuilder htmlStr = new StringBuilder();
        htmlStr.append("<html>");
        htmlStr.append("   <body>");
        htmlStr.append("      <p>�˷R���O��z�n</p>");
        htmlStr.append("      <p>     </p>");
        htmlStr.append("      <p>�P�±z��xxxx������A�z�b�����W�ӽЪ��ȥ��I�O��w��O�����A�H�U�O�z����O���ӡA�ѱz�ѦҡC</p>");
        htmlStr.append("      <p>�i��O���e�j</p>");
        htmlStr.append("      <p>�O�渹�X: ${customer.policyNumber}</p>");
        htmlStr.append("      <p>�Q�O�I�H: ${customer.name}</p>");
        htmlStr.append("      <p>�ӽФ��: ${customer.applyDate}</p>");
        htmlStr.append("      <p>�O�I����: ${customer.fromDate} ~ ${customer.toDate}</p>");
        htmlStr.append("      <p>�ȹC�a�I: ${customer.place}</p>");
        htmlStr.append("      <p></p>");
        htmlStr.append("      <p>���O�I��ΫO�I�O�e����N���餺�H�ܭn�O�H�ҫ��w���p���a�}�C</p>");
        htmlStr.append("      <p></p>");
        htmlStr.append("      <p>                     �q��  ��a���w </p>");
        htmlStr.append("      <p><img src='cid:panda'></p>");
        htmlStr.append("   </body>");
        htmlStr.append("</html>");
        return htmlStr;
    }
}
