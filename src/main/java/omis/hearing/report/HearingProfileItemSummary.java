package omis.hearing.report;

/**
 * Hearing profile item summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 26, 2017)
 * @since OMIS 3.0
 */
public class HearingProfileItemSummary {

	private Long adjudicatedHearingCount;
	
	private Long nonAdjudicatedHearingCount;
	
	public HearingProfileItemSummary(final Long adjudicatedHearingCount,
			final Long nonAdjudicatedHearingCount) {		
		this.adjudicatedHearingCount = adjudicatedHearingCount;		
		this.nonAdjudicatedHearingCount = nonAdjudicatedHearingCount;
	}

	/**
	 * Returns an adjudicated hearing count.
	 *
	 * @return the adjudicatedHearingCount
	 */
	public Long getAdjudicatedHearingCount() {
		return this.adjudicatedHearingCount;
	}

	/**
	 * Returns a nonAdjudicated hearing count.
	 *
	 * @return the nonAdjudicatedHearingCount
	 */
	public Long getNonAdjudicatedHearingCount() {
		return this.nonAdjudicatedHearingCount;
	}
}