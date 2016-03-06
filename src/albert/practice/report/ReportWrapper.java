package albert.practice.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Report Wrapper Class
 * 
 * @author albert
 * 
 */
public class ReportWrapper {
	private transient JRDataSource dataSource;
	private final static String JASPER_CLASSPATH = "..\\..\\..\\..\\report\\templates\\";
	private transient InputStream reportStream;
	private transient JasperPrint print;

	/**
	 * Set report data source
	 * 
	 * @param data
	 *            List of value object
	 */
	private void setDataSource(final List data) {
		dataSource = new JRBeanCollectionDataSource(data);
	}

	/**
	 * Get report input stream
	 */
	private void getReportInputStream(final String fileName) {
		reportStream = getClass().getResourceAsStream(JASPER_CLASSPATH.concat(fileName.concat(".jasper")));
	}

	/**
	 * generate JasperPrint
	 * 
	 * @param params
	 *            parameters
	 * @throws JRException
	 */
	public void generateJasperPrint(final Map params) throws JRException {
		final JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
		print = JasperFillManager.fillReport(report, params, dataSource);
	}

	/**
	 * Set content type and header
	 * 
	 * @param fileName
	 *            is File name
	 * @param response
	 *            is HttpServletResponse
	 * @param exportEnum
	 *            is ExportFormatEnum
	 */
	public void setResponse(final String fileName, final HttpServletResponse response,
			final ExportFormatEnum exportEnum) {
		response.setCharacterEncoding("UTF-8");
		if ("pdf".equals(exportEnum.getValue())) {
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\"".concat(fileName).concat(".pdf\""));
		} else if ("csv".equals(exportEnum.getValue())) {
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=".concat(fileName).concat(".csv"));
		}
	}

	/**
	 * Export report
	 * 
	 * @param exportEnum
	 *            is ExportFormatEnum
	 * @param outputStream
	 *            is OutputStream
	 * @throws JRException
	 * @throws IOException
	 */
	public void exportReport(final ExportFormatEnum exportEnum, final OutputStream outputStream)
			throws JRException, IOException {
		if ("pdf".equals(exportEnum.getValue())) {
			final JRExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			pdfExporter.exportReport();
		} else if ("csv".equals(exportEnum.getValue())) {
			// byte-order marker (BOM)
			final byte bomByteArr[] = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
			// insert BOM byte array into outputStream
			outputStream.write(bomByteArr);
			final JRExporter csvExporter = new JRCsvExporter();
			csvExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			csvExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			csvExporter.exportReport();
		}
		outputStream.flush();
	}

	/**
	 * do export report based on the parameters
	 * 
	 * @param data
	 *            is List of VO
	 * @param fileName
	 *            is file name
	 * @param params
	 *            is parameters
	 * @param response
	 *            is HttpServletResponse
	 * @param exportEnum
	 *            is ExportFormatEnum
	 * @param outputStream
	 *            is OutputStream
	 * @throws JRException
	 * @throws IOException
	 */
	public void execute(final List data, final String fileName, final Map params, final HttpServletResponse response,
			final ExportFormatEnum exportEnum, final OutputStream outputStream) throws JRException, IOException {
		setDataSource(data);
		getReportInputStream(fileName);
		generateJasperPrint(params);
		setResponse(fileName, response, exportEnum);
		exportReport(exportEnum, outputStream);
		if (reportStream != null) {
			reportStream.close();
		}
	}
}