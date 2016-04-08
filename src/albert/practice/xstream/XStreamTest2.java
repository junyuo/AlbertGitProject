package albert.practice.xstream;

import lombok.extern.slf4j.Slf4j;
import albert.practice.xstream.beans.Doc;

import com.thoughtworks.xstream.XStream;

@Slf4j
public class XStreamTest2 {

    public static void main(String[] args) {
        // prepare bean for XML
        Doc doc = new Doc();
        doc.setRegId("123456");
        doc.setOwnerId("A123456789");
        doc.setPolicyNumber("TA800000001");
        doc.setInsuredId("B123456789");
        doc.setFormId("A00123");

        log.debug("doc = " + doc.toString());

        // Initializing XStream
        XStream xStream = new XStream();

        // create an alias called doc to the desired class (Doc)
        xStream.alias("doc", Doc.class);

        // Serializing an object to XML
        String xml = xStream.toXML(doc);

        // print XML
        log.debug("xml = " + xml);
    }

}
