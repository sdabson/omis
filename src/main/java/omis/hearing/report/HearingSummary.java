
package omis.hearing.report;

import java.io.Serializable;
import java.util.Date;

import omis.hearing.domain.HearingCategory;
import omis.hearing.domain.HearingStatusCategory;

/**
 * HearingSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (April 18, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long hearingId;
	
	private final Date hearingDate;
	
	private final String locationName;
	
	private final HearingCategory category;
	
	private final HearingStatusCategory hearingStatusCategory;
	
	private final Date hearingStatusDate;

	/**
	 * @param hearingId
	 * @param hearingDate
	 * @param locationName
	 * @param category
	 * @param status
	 */
	public HearingSummary(final Long hearingId, final Date hearingDate,
			final String locationName, final HearingCategory category,
			final HearingStatusCategory hearingStatusCategory,
			final Date hearingStatusDate) {
		this.hearingId = hearingId;
		this.hearingDate = hearingDate;
		this.locationName = locationName;
		this.category = category;
		this.hearingStatusCategory = hearingStatusCategory;
		this.hearingStatusDate = hearingStatusDate;
	}

	/**
	 * @return the hearingId
	 */
	public Long getHearingId() {
		return hearingId;
	}

	/**
	 * @return the hearingDate
	 */
	public Date getHearingDate() {
		return hearingDate;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @return the category
	 */
	public HearingCategory getCategory() {
		return category;
	}

	/**
	 * @return the hearingStatusCategory
	 */
	public HearingStatusCategory getHearingStatusCategory() {
		return hearingStatusCategory;
	}

	/**
	 * Returns the hearingStatusDate
	 * @return hearingStatusDate - Date
	 */
	public Date getHearingStatusDate() {
		return hearingStatusDate;
	}
	
	
	

}
