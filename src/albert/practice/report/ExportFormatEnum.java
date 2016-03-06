package albert.practice.report;

public enum ExportFormatEnum {
	PDF("pdf"), CSV("csv");

	private ExportFormatEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

}
