package albert.practice.xstream;

import com.thoughtworks.xstream.XStream;

import albert.practice.xstream.beans.Doc;

public class XStreamTest2 {

	public static void main(String[] args) {

		// prepare bean for XML
		Doc doc = new Doc();
		doc.setRegId("123456");
		doc.setOwnerId("A123456789");
		doc.setPolicyNumber("TA800000001");
		doc.setInsuredId("B123456789");
		doc.setFormId("A00123");

		// Initializing XStream
		XStream xStream = new XStream();

		// create an alias called doc to the desired class (Doc)
		xStream.alias("doc", Doc.class);

		// Serializing an object to XML
		String xml = xStream.toXML(doc);

		// print XML
		System.out.println(xml);
	}

}
