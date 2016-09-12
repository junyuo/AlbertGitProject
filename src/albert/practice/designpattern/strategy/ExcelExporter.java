package albert.practice.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelExporter implements ExportStrategy {

    @Override
    public void exportData(String data) {
        log.debug("exporting " + data + " into excel file.");
    }

}
