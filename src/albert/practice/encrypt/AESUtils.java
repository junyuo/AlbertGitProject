package albert.practice.encrypt;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.codec.binary.Base64;

@Slf4j
// http://stackoverflow.com/questions/17567996/illegal-block-size-exception-input-length-must-be-multiple-of-16-when-decrypting
public class AESUtils {

    private static String secretKey = "XMzDdG4D03CKm2IxIWQw7g==a";

    public static String encryptText(String plainText) {
        byte[] raw;
        String encryptedString = "";
        SecretKeySpec skeySpec;
        byte[] encryptText = plainText.getBytes();
        Cipher cipher;
        raw = Base64.decodeBase64(secretKey);
        skeySpec = new SecretKeySpec(raw, "AES");
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return encryptedString;

    }

    public static String decryptText(String encryptedText) {
        Cipher cipher;
        String encryptedString;
        byte[] encryptText = null;
        byte[] raw;
        SecretKeySpec skeySpec;
        raw = Base64.decodeBase64(secretKey);
        skeySpec = new SecretKeySpec(raw, "AES");
        encryptText = Base64.decodeBase64(encryptedText);
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            encryptedString = new String(cipher.doFinal(encryptText));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }

        return encryptedString;
    }

    public static void main(String[] args) throws NoSuchPaddingException, GeneralSecurityException {
        String plainText = "test123";
        String encryptedText = AESUtils.encryptText(plainText);
        String decryptedText = AESUtils.decryptText(encryptedText);

        log.debug("plainText = " + plainText);
        log.debug("encryptedText = " + encryptedText);
        log.debug("decryptedText = " + decryptedText);
    }
}
