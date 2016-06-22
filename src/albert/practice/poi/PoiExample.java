package albert.practice.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import lombok.extern.slf4j.Slf4j;

//http://www.codejava.net/coding/how-to-write-excel-files-in-java-using-apache-poi
//http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
@Slf4j
public class PoiExample {

    private static String excelFile = "D:" + File.separator + "issue.xls";

    public static void main(String[] args) throws IOException {
	PoiExample test = new PoiExample();
	test.writeExcel();

	test.readExcel(excelFile);
    }

    public List<Issue> readExcel(String excelFile) throws IOException {

	List<Issue> issues = new ArrayList<Issue>();

	try (InputStream inputStream = new FileInputStream(new File(excelFile));
		// 1. Create a Workbook.
		Workbook workbook = new HSSFWorkbook(inputStream);) {

	    // 2. Get first sheet
	    Sheet sheet = workbook.getSheetAt(0);

	    // 3. Iterate through each rows from first sheet
	    Iterator<Row> rowIterator = sheet.iterator();
	    int rowCount = 1;
	    while (rowIterator.hasNext()) {
		// (1) ignore header row
		if (rowCount == 1) {
		    rowIterator.next();
		    rowCount++;
		}
		// (2) start to read each row from second row
		else {
		    Row row = rowIterator.next();
		    Integer id = Double.valueOf(row.getCell(0).getNumericCellValue()).intValue();
		    String subject = row.getCell(1).getStringCellValue();
		    String status = row.getCell(2).getStringCellValue();
		    String priority = row.getCell(3).getStringCellValue();
		    String notes = row.getCell(4).getStringCellValue();

		    Issue issue = new Issue();
		    issue.setId(id);
		    issue.setSubject(subject);
		    issue.setStatus(status);
		    issue.setPriority(priority);
		    issue.setNotes(notes);

		    issues.add(issue);
		}
	    }
	} catch (FileNotFoundException e) {
	    throw e;
	}

	for (Issue issue : issues) {
	    log.debug("issue = " + issue.toString());
	}

	return issues;
    }

    public void writeExcel() throws IOException {
	// 0. prepare dummy data
	List<Issue> issues = createDummyIssues();

	// 1. Create a Workbook.
	try (Workbook workbook = new HSSFWorkbook()) {
	    // 2. Create a Sheet.
	    Sheet sheet = workbook.createSheet("issue list");

	    // 3. create cell style
	    CellStyle style = createCellStyle(workbook);

	    // 4. Repeat the following steps until all data is processed:
	    // (1) Create a Row.
	    // (2) Create Cells in a Row. Apply formatting using CellStyle.
	    int rowCount = 0;
	    Row headerRow = sheet.createRow(rowCount);
	    writeHeader(headerRow, style);

	    for (Issue issue : issues) {
		Row row = sheet.createRow(++rowCount);
		writeDataForEachRow(issue, row, style);
	    }

	    // 5. auto resize column width
	    for (int i = 0; i < 5; i++) {
		sheet.autoSizeColumn(i);
	    }

	    sheet.createFreezePane(0, 1);
	    
	    // 6. Write to an OutputStream.
	    try (FileOutputStream outputStream = new FileOutputStream(excelFile);) {
		workbook.write(outputStream);
		log.debug("write issue data to excel file successfully");
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } 
	}
    }

    private void writeHeader(Row headerRow, CellStyle style) {
	Cell cell = headerRow.createCell(0);
	cell.setCellValue("編號");
	cell.setCellStyle(style);

	cell = headerRow.createCell(1);
	cell.setCellValue("主旨");
	cell.setCellStyle(style);

	cell = headerRow.createCell(2);
	cell.setCellValue("狀態");
	cell.setCellStyle(style);

	cell = headerRow.createCell(3);
	cell.setCellValue("優先");
	cell.setCellStyle(style);

	cell = headerRow.createCell(4);
	cell.setCellValue("註解");
	cell.setCellStyle(style);
    }

    private void writeDataForEachRow(Issue issue, Row row, CellStyle style) {
	Cell cell = row.createCell(0);
	cell.setCellValue(issue.getId());
	cell.setCellStyle(style);

	cell = row.createCell(1);
	cell.setCellValue(issue.getSubject());
	cell.setCellStyle(style);

	cell = row.createCell(2);
	cell.setCellValue(issue.getStatus());
	cell.setCellStyle(style);

	cell = row.createCell(3);
	cell.setCellValue(issue.getPriority());
	cell.setCellStyle(style);

	cell = row.createCell(4);
	cell.setCellValue(issue.getNotes());
	cell.setCellStyle(style);
    }

    private CellStyle createCellStyle(Workbook workbook) {
	CellStyle cellStyle = workbook.createCellStyle();
	cellStyle.setBorderBottom((short) 1);
	cellStyle.setBorderTop((short) 1);
	cellStyle.setBorderLeft((short) 1);
	cellStyle.setBorderRight((short) 1);
	cellStyle.setWrapText(true);

	return cellStyle;
    }

    private List<Issue> createDummyIssues() {
	Issue issue1 = new Issue(1, "查不到資料", "新建立", "正常",
		"蜘蛛人(2016-05-26 17:05:00):\n這個提議不錯，來做吧！\n\n浩克(2016-05-26 17:05:00):\n測試無誤\n\n");
	Issue issue2 = new Issue(2, "新增時發生錯誤", "新建立", "高", "");
	Issue issue3 = new Issue(3, "刪除失敗", "處理中", "高", "");

	return Arrays.asList(issue1, issue2, issue3);
    }

}
