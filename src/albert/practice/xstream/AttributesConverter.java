package albert.practice.xstream;

import albert.practice.xstream.beans.Attributes;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AttributesConverter implements Converter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Attributes.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Attributes attributes = (Attributes) source;
        writer.startNode("Workload");
        writer.setValue(attributes.getWorkLoad());
        writer.endNode();

        writer.startNode("DetailDocCat");
        writer.setValue(attributes.getDetailDocCat());
        writer.endNode();

        writer.startNode("ScanDate");
        writer.setValue(attributes.getScanDate());
        writer.endNode();

        writer.startNode("ScanTime");
        writer.setValue(attributes.getScanTime());
        writer.endNode();

        writer.startNode("StockingNumber");
        writer.setValue(attributes.getStockingNumber());
        writer.endNode();

        writer.startNode("CaseId");
        writer.setValue(attributes.getCaseId());
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        Attributes attributes = new Attributes();
        reader.moveDown();
        attributes.setWorkLoad(reader.getValue());
        reader.moveUp();

        reader.moveDown();
        attributes.setDetailDocCat(reader.getValue());
        reader.moveUp();

        reader.moveDown();
        attributes.setScanDate(reader.getValue());
        reader.moveUp();

        reader.moveDown();
        attributes.setScanTime(reader.getValue());
        reader.moveUp();

        reader.moveDown();
        attributes.setStockingNumber(reader.getValue());
        reader.moveUp();

        reader.moveDown();
        attributes.setCaseId(reader.getValue());
        reader.moveUp();

        return attributes;
    }

}
