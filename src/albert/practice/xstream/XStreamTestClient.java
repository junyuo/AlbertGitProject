package albert.practice.xstream;

import java.util.ArrayList;
import java.util.List;

import albert.practice.xstream.beans.Attributes;
import albert.practice.xstream.beans.PolicyNumbers;
import albert.practice.xstream.beans.ProposalNums;
import albert.practice.xstream.beans.Root;

import com.thoughtworks.xstream.XStream;

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
        policyNumberList.add(new PolicyNumbers("12345678"));
        policyNumberList.add(new PolicyNumbers("AAA12345678"));

        attribute.setPolicyNumbers(policyNumberList);

        List<ProposalNums> proposalNumList = new ArrayList<ProposalNums>();
        proposalNumList.add(new ProposalNums("87654321"));

        attribute.setProposalNums(proposalNumList);

        Root root = new Root();
        root.setAttributes(attribute);

        XStream xStream = new XStream();
        xStream.alias("Root", Root.class);

        xStream.alias("PolicyNumbers", PolicyNumbers.class);
        xStream.addImplicitCollection(Attributes.class, "PolicyNumbers");

        xStream.alias("ProposalNums", ProposalNums.class);
        xStream.addImplicitCollection(Attributes.class, "ProposalNums");

        System.out.println(xStream.toXML(root));

    }
}
