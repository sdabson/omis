package omis.paroleboarditinerary.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Parole board itinerary summary.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 20, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItinerarySummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final String locationOrganizationName;
	
	/**
	 * Instantiates an implementation of parole board itinerary summary.
	 * 
	 * @param id id
	 * @param startDate start date
	 * @param endDate end date
	 * @param locationOrganizationName location organization name
	 * @param eligibilityCount eligibility count
	 */
	public ParoleBoardItinerarySummary(final Long id, final Date startDate,
			final Date endDate, final String locationOrganizationName) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.locationOrganizationName = locationOrganizationName;
	}

	/**
	 * Returns the id of the parole board itinerary.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Returns the location organization name.
	 * 
	 * @return location organization name
	 */
	public String getLocationOrganizationName() {
		return locationOrganizationName;
	}
}
