package albert.practice.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class FileBase64Util {

    public static void main(String[] args) throws IOException {
        FileBase64Util util = new FileBase64Util();

        File attachment = new File("D://dropbox/test.pdf");

        String encodedString = util.getEncodeBase64String(attachment);
        System.out.println("encode successfully....");
        System.out.println("encodedString = " + encodedString);

        byte[] decodedSource = Base64.decodeBase64(encodedString.getBytes());
        System.out.println("decode successfully....");

        FileUtils.writeByteArrayToFile(new File("D://dropbox/new_test.pdf"), decodedSource);
        System.out.println("write to new file");
    }

    public String getEncodeBase64String(File source) throws IOException {
        InputStream sourcetStream = null;
        String encodedString = "";
        try {
            sourcetStream = new FileInputStream(source);
            byte[] bytes = IOUtils.toByteArray(sourcetStream);
            byte[] encodedSource = Base64.encodeBase64(bytes);
            encodedString = new String(encodedSource);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sourcetStream != null) {
                sourcetStream.close();
            }
        }
        return encodedString;
    }

    public static byte[] decode(byte base64Data[]) {
        return Base64.decodeBase64(base64Data);
    }

}
