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
        File source = new File("D://dropbox/test.pdf");
        InputStream sourcetStream = null;
        try {
            sourcetStream = new FileInputStream(source);
            byte[] bytes = IOUtils.toByteArray(sourcetStream);

            byte[] encodedSource = FileBase64Util.encode(bytes);
            System.out.println("encode successfully....");

            byte[] decodedSource = FileBase64Util.decode(encodedSource);
            System.out.println("decode successfully....");

            FileUtils.writeByteArrayToFile(new File("D://dropbox/new_test.pdf"), decodedSource);
            System.out.println("write to new file");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (sourcetStream != null) {
                sourcetStream.close();
            }
        }

    }

    public static byte[] encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    public static byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

}
