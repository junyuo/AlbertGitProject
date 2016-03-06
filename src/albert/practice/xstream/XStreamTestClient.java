package albert.practice.xstream;

import java.awt.image.BufferedImage;
import java.io.File;
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

	// TODO not finish yet.
	// https://www.developerfeed.com/how-convert-base64-encoded-stream-image-using-java/
	public void toXmlTiffFile(String xml) throws IOException {
		System.out.println("xml=" + xml);
		String path = "/Users/albert/Documents/test";
		String tiffFile = "07000H.tiff";

		BufferedImage bImage = ImageIO.read(new File("/Users/albert/Documents/test/07000H.xml"));
		ImageIO.write(bImage, "tiff", new File(path + "/" + tiffFile));

	}
}
