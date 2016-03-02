package albert.practice.xstream;

import java.util.ArrayList;
import java.util.List;

import albert.practice.xstream.beans.Attributes;
import albert.practice.xstream.beans.PolicyNumbers;
import albert.practice.xstream.beans.ProposalNums;
import albert.practice.xstream.beans.Root;

import com.thoughtworks.xstream.XStream;

/**
 * 預期產出結果 <Root> <Attributes> <WorkLoad>TW_NEWNBED</WorkLoad>
 * <DetailDocCat>PD-投資型商品要保書_OT通路</DetailDocCat> <ScanDate>2016/03/02</ScanDate>
 * <ScanTime>15:56:00</ScanTime> <StockingNumber>2016E000001</StockingNumber>
 * <CaseId>EDOC-201600100001</CaseId> <PolicyNumbers> <PolicyNumber>C12345678</PolicyNumber>
 * </PolicyNumbers> <ProposalNums> <ProposalNum>A87654321</ProposalNum> </ProposalNums>
 * </Attributes> </Root>
 */
public class XStreamTestClient {

    public static void main(String[] args) {

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

        Root root = new Root();
        root.setAttributes(attribute);

        XStream xStream = new XStream();
        xStream.alias("Root", Root.class);

        xStream.alias("PolicyNumbers", PolicyNumbers.class);
        xStream.addImplicitCollection(Attributes.class, "PolicyNumbers");

        xStream.alias("ProposalNums", ProposalNums.class);
        xStream.addImplicitCollection(Attributes.class, "ProposalNums");

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xStream.toXML(root);
        System.out.println(xml);

    }
}
