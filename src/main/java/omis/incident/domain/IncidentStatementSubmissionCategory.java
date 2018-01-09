package omis.incident.domain;

/**
 * Category of incident statement submissions.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 3, 2016)
 * @since OMIS 3.0
 */
public enum IncidentStatementSubmissionCategory {

	/** Default. */
	DEFAULT,
	
	/** Secure. */
	SECURE,
	
	/** Draft. */
	DRAFT;
	
	/**
	 * Returns instance name ({@code this.name()}).
	 * 
	 * @return instance name ({@code this.name()})
	 */
	public String getName() {
		return this.name();
	}
}
