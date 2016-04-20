package albert.practice.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

@Slf4j
public class PdfFileToTif {

    private final float dpi = 300f;

    public static void main(String[] args) {
        File pdfFile = new File("D:\\dropbox\\Getting Started.pdf");
        String destination = "D:\\dropbox\\";

        PdfFileToTif test = new PdfFileToTif();
        test.convertPdfToTif(pdfFile, destination);
    }

    public void convertPdfToTif(File pdfFile, String destination) {
        if (!isFileExisted(pdfFile)) {
            throw new RuntimeException("File not found ! (" + pdfFile.getAbsolutePath() + ")");
        }

        String pdfFileName = pdfFile.getName();

        try {
            // load PDF document
            PDDocument document = PDDocument.load(pdfFile);

            // create PDF renderer
            PDFRenderer renderer = new PDFRenderer(document);

            // go through each page of PDF, and generate TIF for each PDF page.
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                // Returns the given page as an RGB image with 300 DPI.
                BufferedImage image = renderer.renderImageWithDPI(i, dpi, ImageType.BINARY);

                // Assign the file name of TIF
                String fileName = pdfFileName + "_" + String.format("%02d", i + 1);
                log.debug("Generating  " + fileName + ".tif to " + destination);

                // Writes a buffered image to a file using the given image format.
                ImageIOUtil.writeImage(image, destination + fileName + ".tif", Math.round(dpi));
                image.flush();
            }
            log.debug("PDF to TIF conversion well done!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判斷檔案是否存在
     * 
     * @param file
     * @return true - 檔案存在; false - 檔案不存在
     */
    private Boolean isFileExisted(File file) {
        Boolean isExisted = Boolean.FALSE;
        isExisted = (file.exists() && (!file.isDirectory()));
        return isExisted;
    }

}
