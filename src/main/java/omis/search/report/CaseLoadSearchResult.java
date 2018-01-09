package omis.search.report;

/** Caseload search result.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 19, 2013)
 * @since OMIS 3.0 */
public class CaseLoadSearchResult {
	private final Long id;
	private final String title;
	private final String description;


	/** Constructor.
	 * @param id id of caseload.
	 * @param title of caseload.
	 * @param description description of caseload. */
	public CaseLoadSearchResult(
			final Long id,
			final String title,
			final String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}

	/** gets id.
	 * @return id. */
	public Long getId() { return this.id; }

	/** gets title.
	 * @return title. */
	public String getTitle() { return this.title; }

	/** gets description.
	 * @return description. */
	public String getDescription() { return this.description; }
}
