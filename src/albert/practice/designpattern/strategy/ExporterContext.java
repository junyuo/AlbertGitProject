package albert.practice.designpattern.strategy;

import lombok.Data;

@Data
public class ExporterContext {
    
    private String data;
    
    public void doExport(ExportStrategy strategy) {
        strategy.exportData(this.data);
    }
    
}
