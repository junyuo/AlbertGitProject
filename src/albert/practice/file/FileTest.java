package albert.practice.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;

/**
 * MediaType List : http://www.freeformatter.com/mime-types-list.html
 * 
 * @author albert
 *
 */
public class FileTest {

	private final static String DROPBOX_HOME = "/Users/albert/Dropbox/";
	private final static String DOC_FILE = DROPBOX_HOME + "庫務組/交付文件(第二階段)/PDS/NTA_PSR_FMS_PDS_1030930_V1.0.doc";
	private final static String DOCX_FILE = DROPBOX_HOME + "債務管理系統/NTA_IFMIS_DBM_測試及操作文件/DBM001E.docx";
	private final static String XLSX_FILE = DROPBOX_HOME
			+ "庫務組/交付文件(第二階段)/PDS/NTA_PSR_FMS_PDS_附件/NTA_PSR_FMS_PDS_1030901_需求追溯表.xlsx";
	private final static String TXT_FILE = DROPBOX_HOME + "庫務組/測試.txt";
	private final static String JPG_FILE = DROPBOX_HOME + "庫務組/ads100fa.jpg";
	private final static String PDF_FILE = DROPBOX_HOME + "eBooks/Head First Python.pdf";
	private final static String FAKE_EXE_FILE = DROPBOX_HOME + "eBooks/The Intelligent Investor 拷貝.exe";

	private final static MediaType doc = MediaType.parse("application/msword");
	private final static MediaType docx = MediaType
			.parse("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	private final static MediaType xlsx = MediaType
			.parse("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	private final static MediaType txt = MediaType.parse("text/plain");
	private final static MediaType jpg = MediaType.parse("image/jpeg");
	private final static MediaType pdf = MediaType.parse("application/pdf");
	private final static MediaType exe = MediaType.parse("application/x-msdownload");

	public static void main(String[] args) throws IOException {
		new FileTest().checkFileMediaType(DOC_FILE, doc);
		new FileTest().checkFileMediaType(DOCX_FILE, docx);
		new FileTest().checkFileMediaType(XLSX_FILE, xlsx);
		new FileTest().checkFileMediaType(JPG_FILE, jpg);
		new FileTest().checkFileMediaType(PDF_FILE, pdf);
		new FileTest().checkFileMediaType(FAKE_EXE_FILE, exe);
	}

	public void checkFileMediaType(String sourceFile, MediaType expectedMediaType) throws IOException {
		File file = FileUtils.getFile(sourceFile);
		try {
			Tika tika = new Tika();

			String mediaType = tika.detect(file);
			System.out.println("\nchecking " + sourceFile + "...");
			if (!(expectedMediaType.getBaseType().toString().equals(mediaType))) {
				String actualMediaTypeName = mediaType;
				String expectedMediaTypeName = expectedMediaType.getBaseType().toString();
				String errorMsg = "Worng media type ! Expected:" + expectedMediaTypeName + ", Actual:"
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