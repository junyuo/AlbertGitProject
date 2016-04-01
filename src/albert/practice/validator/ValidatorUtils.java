package albert.practice.validator;

import java.util.regex.Pattern;

public class ValidatorUtils {

    public Boolean isValidEmail(String email) {
        Boolean isValid = Boolean.FALSE;

        Pattern EMAIL_PATTERN = Pattern.compile("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$");
        if (EMAIL_PATTERN.matcher(email).matches()) {
            isValid = Boolean.TRUE;
        }

        return isValid;
    }

    public Boolean isValidPhoneNumber(String mobilePhoneNumber) {
        Boolean isValid = Boolean.FALSE;

        Pattern MSISDN_PATTERN = Pattern.compile("[+-]?\\d{10,12}");

        if (MSISDN_PATTERN.matcher(mobilePhoneNumber).matches()) {
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

        System.out.println(validator.isValidEmail(validEmail));
        System.out.println(validator.isValidEmail(invalidEmail));
        System.out.println(validator.isValidPhoneNumber(validPhoneNum));
        System.out.println(validator.isValidPhoneNumber(invalidPhoneNum));

    }
}
