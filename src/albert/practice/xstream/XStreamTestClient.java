package albert.practice.xstream;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

import albert.practice.xstream.beans.Attributes;
import albert.practice.xstream.beans.PolicyNumbers;
import albert.practice.xstream.beans.ProposalNums;
import albert.practice.xstream.beans.Root;

/**
 * 預期產出結果 <Root> <Attributes> <WorkLoad>TW_NEWNBED</WorkLoad>
 * <DetailDocCat>PD-投資型商品要保書_OT通路</DetailDocCat> <ScanDate>2016/03/02</ScanDate>
 * <ScanTime>15:56:00</ScanTime> <StockingNumber>2016E000001</StockingNumber>
 * <CaseId>EDOC-201600100001</CaseId>
 * <PolicyNumbers> <PolicyNumber>C12345678</PolicyNumber> </PolicyNumbers>
 * <ProposalNums> <ProposalNum>A87654321</ProposalNum> </ProposalNums>
 * </Attributes> </Root>
 */
public class XStreamTestClient {

	public static void main(String[] args) throws IOException {

		Attributes attribute = new Attributes();
		attribute.setWorkLoad("TW_NEWNBED");
		attribute.setDetailDocCat("PD-投資型商品要保書_OT通路");
		attribute.setScanDate("2016/03/02");
		attribute.setScanTime("15:56:00");
		attribute.setStockingNumber("2016E000001");
		attribute.setCaseId("EDOC-201600100001");

		List<PolicyNumbers> policyNumberList = new ArrayList<PolicyNumbers>();
		policyNumberList.add(new PolicyNumbers("A12345678"));
		// policyNumberList.add(new PolicyNumbers("AAA12345678"));

		attribute.setPolicyNumbers(policyNumberList);

		List<ProposalNums> proposalNumList = new ArrayList<ProposalNums>();
		proposalNumList.add(new ProposalNums("B87654321"));

		attribute.setProposalNums(proposalNumList);

		String xml = new XStreamTestClient().toXml(attribute);
		System.out.println(xml);

		new XStreamTestClient().toXmlFile(attribute);

		new XStreamTestClient().toXmlTiffFile(xml);
	}

	public String toXml(Attributes attributes) {
		Root root = new Root();
		root.setAttributes(attributes);

		XStream xStream = new XStream();
		xStream.alias("Root", Root.class);

		xStream.alias("PolicyNumbers", PolicyNumbers.class);
		xStream.addImplicitCollection(Attributes.class, "PolicyNumbers");

		xStream.alias("ProposalNums", ProposalNums.class);
		xStream.addImplicitCollection(Attributes.class, "ProposalNums");

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(root);
		return xml;
	}

	public void toXmlFile(Attributes attributes) throws IOException {
		Root root = new Root();
		root.setAttributes(attributes);

		XStream xStream = new XStream();
		xStream.alias("Root", Root.class);

		xStream.alias("PolicyNumbers", PolicyNumbers.class);
		xStream.addImplicitCollection(Attributes.class, "PolicyNumbers");

		xStream.alias("ProposalNums", ProposalNums.class);
		xStream.addImplicitCollection(Attributes.class, "ProposalNums");

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(root);

		String path = "/Users/albert/Documents/test";
		String fileName = "07000H.xml";
		File file = new File(path + "/" + fileName);

		try {
			FileUtils.writeStringToFile(file, xml, "UTF-8");
		} catch (IOException e) {
			throw e;
		}
	}

	// http://stackoverflow.com/questions/27658986/how-to-convert-multi-line-text-to-image-using-java
	public void toXmlTiffFile(String xml) throws IOException {
		System.out.println("xml=" + xml);

		String path = "/Users/albert/Documents/test";
		String tiffFile = "07000H.png";
		String xmlFile = "07000H.xml";

		File file = new File(path + "/" + tiffFile);

		int width = 1000;
		int height = 1000;

		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();

		Font font = new Font("Arial", Font.PLAIN, 12);
		g2d.setFont(font);
		g2d.dispose();

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2d.setFont(font);
		g2d.setColor(Color.BLACK);

		BufferedReader br = null;
		int nextLinePosition = 25;
		int fontSize = 12;
		try {
			br = new BufferedReader(new FileReader(new File(path + "/" + xmlFile)));
			String line;
			while ((line = br.readLine()) != null) {
				g2d.drawString(line, 0, nextLinePosition);
				nextLinePosition = nextLinePosition + fontSize;
			}

		} catch (FileNotFoundException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (br != null) {
				br.close();
			}
		}

		g2d.dispose();

		try {
			// only support jpg, gif, png
			ImageIO.write(img, "png", file);
		} catch (IOException ex) {
			throw ex;
		}

		// copy from png to tiff
		FileUtils.copyFile(file, new File(path + "/07000H.tiff"));

	}

}
