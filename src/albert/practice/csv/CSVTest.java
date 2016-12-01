package albert.practice.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CSVTest {

    // Delimiter used in CSV file
    private static final String NEW_LINE_SEPARATOR = "\n";

    // CSV file header
    private static final String[] FILE_HEADER = {"ns", "identifier"};

    // OpcVo attributes
    private static final String NS = "ns";
    private static final String ID = "identifier";

    public static void main(String[] args) throws IOException {
        String csvDirectory = "D:/work/AOCS/csv/";
        String csvFileName = "test.csv";

        CSVTest test = new CSVTest();
        // test.writeToCSV(csvFolder.concat(csvFile));
        List<OpcVo> opcs = test.readFromCsv(csvDirectory.concat(csvFileName));
        opcs.forEach(opc -> log.debug(opc.toString()));
    }

    public void writeToCSV(String fileName) throws IOException {
        List<OpcVo> opcs = getDummyData();

        // Create the CSVFormat object with "\n" as a record delimiter
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (FileWriter fileWriter = new FileWriter(fileName);
                CSVPrinter csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);) {
            // Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);

            // Write a new OpcVo object list to the CSV file
            for (OpcVo opc : opcs) {
                List dataRecord = new ArrayList<>();
                dataRecord.add(String.valueOf(opc.getNs()));
                dataRecord.add(opc.getIdentifier());
                csvFilePrinter.printRecord(dataRecord);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    public List<OpcVo> readFromCsv(String fileName) throws IOException {
        // Create a new list of OpcVo to be filled by CSV file data
        List<OpcVo> result = new ArrayList<>();

        // Create the CSVFormat object with the header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);

        try (FileReader fileReader = new FileReader(fileName);
                CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);) {
            List dataRecord = new ArrayList<>();
            // Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            
            for (int i = 1; i < csvRecords.size(); i++) {
              //Create a new OpcVo object and fill this data
                CSVRecord record = csvRecords.get(i);
                String ns = record.get(NS);
                String identifier = record.get(ID);
                result.add(new OpcVo(Integer.valueOf(ns), identifier));
            }
        } catch (IOException e) {
            throw e;
        }

        return result;
    }

    private List<OpcVo> getDummyData() {
        OpcVo opc1 = new OpcVo(2, "Channel1.Device1.FireZone_01");
        OpcVo opc2 = new OpcVo(2, "Channel1.Device1.EmergencyPushButton_01");
        OpcVo opc3 = new OpcVo(2, "Channel1.Device1.EmergencyExitWindow_01");
        OpcVo opc4 = new OpcVo(2, "Channel1.Device1.EmergencyExit_01");

        return Arrays.asList(opc1, opc2, opc3, opc4);
    }
}
