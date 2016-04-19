package albert.practice.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import lombok.extern.slf4j.Slf4j;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;

@Slf4j
public class DESUtils {

    private static Cipher ecipher;
    private static Cipher dcipher;

    private static SecretKey key;

    static {
        // generate secret key using DES algorithm
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            dcipher.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String str) {
        try {
            // encode the string into a sequence of bytes using the named charset
            // storing the result into a new byte array.
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);

            // encode to base64
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(String str) {
        try {
            // decode with base64 to get bytes
            byte[] dec = BASE64DecoderStream.decode(str.getBytes());
            byte[] utf8 = dcipher.doFinal(dec);

            // create new string based on the specified charset
            return new String(utf8, "UTF8");
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String plainText = "123456abcdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String encryptedText = DESUtils.encrypt(plainText);
        String decryptedText = DESUtils.decrypt(encryptedText);

        log.debug("plainText = " + plainText);
        log.debug("encryptedText = " + encryptedText);
        log.debug("decryptedText = " + decryptedText);
    }
}
