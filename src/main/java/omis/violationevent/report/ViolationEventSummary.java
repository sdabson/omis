package omis.violationevent.report;

import java.io.Serializable;
import java.util.Date;

import omis.violationevent.domain.ViolationEventCategory;

/**
 * ViolationEventSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 18, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventSummary implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final Long violationEventId;
	
	private final Date date;
	
	private final ViolationEventCategory category;
	
	private final String jurisdiction;
	
	private final Long violationCount;

	/**
	 * Constructor for ViolationEventSummary
	 * @param violationEventId - Long
	 * @param date - Date
	 * @param category - ViolationEventCategory
	 * @param jurisdiction - String
	 * @param violationCount - Long
	 */
	public ViolationEventSummary(final Long violationEventId, final Date date,
			final ViolationEventCategory category,
			final String jurisdiction,
			final Long violationCount) {
		this.violationEventId = violationEventId;
		this.date = date;
		this.category = category;
		this.jurisdiction = jurisdiction;
		this.violationCount = violationCount;
	}

	/**
	 * Returns the violationEventId
	 * @return violationEventId - violationEventId
	 */
	public Long getViolationEventId() {
		return violationEventId;
	}

	/**
	 * Returns the date
	 * @return date - date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Returns the category
	 * @return category - ViolationEventCategory
	 */
	public ViolationEventCategory getCategory() {
		return category;
	}

	/**
	 * Returns the jurisdiction
	 * @return jurisdiction - String
	 */
	public String getJurisdiction() {
		return jurisdiction;
	}

	/**
	 * Returns the violationCount
	 * @return violationCount - Long
	 */
	public Long getViolationCount() {
		return violationCount;
	}
	
}
