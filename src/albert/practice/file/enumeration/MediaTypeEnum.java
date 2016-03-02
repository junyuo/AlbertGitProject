package albert.practice.file.enumeration;

/**
 * List of MIME types / Internet Media Types: http://www.freeformatter.com/mime-types-list.html
 */
public enum MediaTypeEnum {

    DOC("doc", "application/msword"), DOCX("docx",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"), XLSX(
            "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"), TXT(
            "txt", "text/plain"), JPG("jpg", "image/jpeg"), PDF("pdf", "application/pdf"), EXE(
            "exe", "application/x-msdownload");

    private MediaTypeEnum(String fileExtenstion, String mediaType) {
        this.fileExtenstion = fileExtenstion;
        this.mediaType = mediaType;
    }

    private String fileExtenstion;
    private String mediaType;

    public String getFileExtenstion() {
        return fileExtenstion;
    }

    public String getMediaType() {
        return mediaType;
    }

}
