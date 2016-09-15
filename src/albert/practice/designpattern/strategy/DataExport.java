package albert.practice.designpattern.strategy;

public class DataExport {

    public static void main(String[] args) {
        String data = "[test data]";
        
        DataExport exporter = new DataExport();
        exporter.exportData(FileTypeEnum.XLS, data);
        exporter.exportData(FileTypeEnum.PDF, data);
        exporter.exportData(FileTypeEnum.HTML, data);

        ExporterContext context = new ExporterContext();
        context.setData("abc_data");
        context.doExport(new HtmlExporter());
        context.doExport(new ExcelExporter());
        context.doExport(new PdfExporter());
    }

    @Deprecated
    public void exportData(FileTypeEnum fileType, String data) {
        ExportStrategy exporter = null;
        if (FileTypeEnum.XLS == fileType) {
            exporter = new ExcelExporter();
        } else if (FileTypeEnum.PDF == fileType) {
            exporter = new PdfExporter();
        } else if (FileTypeEnum.HTML == fileType) {
            exporter = new HtmlExporter();
        }
        exporter.exportData(data);
    }

}
