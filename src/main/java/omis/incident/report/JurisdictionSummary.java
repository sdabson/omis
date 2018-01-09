package omis.incident.report;

/**
 * Jurisdiction summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 3, 2015)
 * @since OMIS 3.0
 */
public class JurisdictionSummary {

	private final Long jurisdictionId;
	
	private final String jurisdictionName;
	
	private final Long incidentCount;
	
	private final Long incidentReportCount;
	
	/**
	 * Instantiates a jurisdiction summary with the specified properties.
	 * 
	 * @param jurisdictionId jurisdiction id
	 * @param jurisdictionName jurisdiction name
	 * @param incidentCount incident count
	 * @param incidentReportCount incident report count
	 */
	public JurisdictionSummary(final Long jurisdictionId,
			final String jurisdictionName, final Long incidentCount,
			final Long incidentReportCount) {
		this.jurisdictionId = jurisdictionId;
		this.jurisdictionName = jurisdictionName;
		this.incidentCount = incidentCount;
		this.incidentReportCount = incidentReportCount;
	}

	/**
	 * Returns the jurisdiction id.
	 * 
	 * @return jurisdiction id
	 */
	public Long getJurisdictionId() {
		return this.jurisdictionId;
	}

	/**
	 * Returns the jurisdiction name.
	 * 
	 * @return jurisdiction name
	 */
	public String getJurisdictionName() {
		return this.jurisdictionName;
	}

	/**
	 * Returns the incident count.
	 * 
	 * @return incident count
	 */
	public Long getIncidentCount() {
		return this.incidentCount;
	}

	/**
	 * Sets the incident report count.
	 * 
	 * @return incident report count
	 */
	public Long getIncidentReportCount() {
		return this.incidentReportCount;
	}
}