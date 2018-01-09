package omis.visitation.report;

import java.io.Serializable;

/**
 * Facility visitation summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (March 7, 2017)
 * @since OMIS 3.0
 */
public class FacilityVisitationSummary implements Serializable{

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final Integer offenderCount;
	private final Integer visitorCount;
	private final Integer activeVisits;
	private final Integer personTotal;
	
	/**
	 * Instantiates an instance of facility visitation summary with the
	 * specified counts.
	 * 
	 * @param id id
	 * @param offenderCount offender count
	 * @param visitorCount visitor count
	 * @param activeVisits active visits
	 * @param personTotal person total
	 */
	public FacilityVisitationSummary(final Long id, final Integer offenderCount,
			final Integer visitorCount, final Integer activeVisits,
			final Integer personTotal) {
		this.id = id;
		this.offenderCount = offenderCount;
		this.visitorCount = visitorCount;
		this.activeVisits = activeVisits;
		this.personTotal = personTotal;
	}

	/**
	 * Returns the facility id of the the facility being summarized.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the offender count.
	 * 
	 * @return offender count
	 */
	public Integer getOffenderCount() {
		return this.offenderCount;
	}

	/**
	 * Returns the visitor count.
	 * 
	 * @return visitor count
	 */
	public Integer getVisitorCount() {
		return this.visitorCount;
	}

	/**
	 * Returns the active visits count.
	 * 
	 * @return active visits
	 */
	public Integer getActiveVisits() {
		return this.activeVisits;
	}

	/**
	 * Returns the total persons count.
	 * 
	 * @return person total
	 */
	public Integer getPersonTotal() {
		return this.personTotal;
	}
}