package albert.practice.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;

import albert.practice.file.enumeration.MediaTypeEnum;

/**
 * 
 * @author albert
 * 
 */
public class FileMediaTypeTest {

    // set up the path and name of test file
    private final static String DROPBOX_HOME = "D:/dropbox/";
    private final static String DOC_FILE = DROPBOX_HOME
            + "庫務組/交付文件(第二階段)/PDS/NTA_PSR_FMS_PDS_1030930_V1.0.doc";
    private final static String DOCX_FILE = DROPBOX_HOME
            + "債務管理系統/NTA_IFMIS_DBM_測試及操作文件/DBM001E.docx";
    private final static String XLSX_FILE = DROPBOX_HOME
            + "庫務組/交付文件(第二階段)/PDS/NTA_PSR_FMS_PDS_附件/NTA_PSR_FMS_PDS_1030901_需求追溯表.xlsx";
    private final static String TXT_FILE = DROPBOX_HOME + "庫務組/測試.txt";
    private final static String JPG_FILE = DROPBOX_HOME + "庫務組/ads100fa.jpg";
    private final static String PDF_FILE = DROPBOX_HOME + "eBooks/Head First Python.pdf";
    private final static String FAKE_EXE_FILE = DROPBOX_HOME
            + "eBooks/The Intelligent Investor 拷貝.exe";

    public static void main(String[] args) throws IOException {
        new FileMediaTypeTest().checkFileMediaType(DOC_FILE, MediaTypeEnum.DOC.getMediaType());
        new FileMediaTypeTest().checkFileMediaType(DOCX_FILE, MediaTypeEnum.DOCX.getMediaType());
        new FileMediaTypeTest().checkFileMediaType(XLSX_FILE, MediaTypeEnum.XLSX.getMediaType());
        new FileMediaTypeTest().checkFileMediaType(TXT_FILE, MediaTypeEnum.TXT.getMediaType());
        new FileMediaTypeTest().checkFileMediaType(JPG_FILE, MediaTypeEnum.JPG.getMediaType());
        new FileMediaTypeTest().checkFileMediaType(PDF_FILE, MediaTypeEnum.PDF.getMediaType());
        new FileMediaTypeTest().checkFileMediaType(FAKE_EXE_FILE, MediaTypeEnum.EXE.getMediaType());
    }

    public void checkFileMediaType(String sourceFile, String expectedMediaType) throws IOException {

        File file = FileUtils.getFile(sourceFile);

        try {
            Tika tika = new Tika();

            // Detects the media type of the given file. The type detection is
            // based on the document content and a potential known file
            // extension.
            String mediaType = tika.detect(file);

            System.out.println("\nchecking " + sourceFile + "...");

            if (!(expectedMediaType.equals(mediaType))) {
                String actualMediaTypeName = mediaType;
                String errorMsg = "Worng media type ! Expected:" + expectedMediaType + ", Actual:"
                        + actualMediaTypeName;
                System.err.println(errorMsg);
                throw new RuntimeException(errorMsg);
            } else {
                System.out.println("Correct media type : " + mediaType);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
