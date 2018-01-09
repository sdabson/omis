package omis.report;


/** Report format.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0 */
public enum ReportFormat {
	/** PDF type. */
	PDF("pdf","application/pdf"),
	/** HTML type. */
	HTML("html","text/html"),
	/** Document type. */
	DOCX("docx","application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	/** Spreadsheet type. */
	XLS("xls","application/vnd.ms-excel"),
	/** Spreadsheet type. */
	XLSX("xlsx","application/vnd.ms-excel"),
	/** Rich text format type. */
	RTF("rtf","application/rtf"),
	/** csv type. */
	CSV("csv","text/plain"),
	/** XML type. */
	XML("xml","text/xml"),
	/** Open document Text. */
	ODT("odt","application/vnd.oasis.opendocument.text"),
	/** Open document spreadsheet. */
	ODS("ods","application/vnd.oasis.opendocument.spreadsheet");

	private String name;
	private String contentType;

	/** Constructor. */
	private ReportFormat(final String name,
			final String contentType) {
		this.name = name;
		this.contentType = contentType;
	}

	/** Gets format name.
	 * @return name name. */
	public String getName() { return this.name; }

	/** Gets content type.
	 * @return content type. */
	public String getContentType() { return this.contentType; }
}
