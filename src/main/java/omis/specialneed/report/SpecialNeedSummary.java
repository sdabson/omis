package omis.specialneed.report;

import java.io.Serializable;
import java.util.Date;

import omis.datatype.DateRange;

/**
 * Special need summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final String classificationName;
	
	private final String categoryName;
	
	private final String sourceName;
	
	private final String comment;
	
	private final String sourceComment;
	
	private final Boolean active;

	/**
	 * Instantiates an implementation of special need summary.
	 *
	 * @param id id 
	 * @param offenderLastName offender last name
	 * @param offenderFirstName offender first name
	 * @param offenderMiddleName offender middle name
	 * @param offenderSuffix offender suffix
	 * @param offenderNumber offender number
	 * @param startDate start date
	 * @param endDate end date
	 * @param classificationName classification name
	 * @param categoryName category name
	 * @param sourceName source name
	 * @param comment comment
	 * @param sourceComment source comment
	 */
	public SpecialNeedSummary(final Long id, final String offenderLastName,
			final String offenderFirstName, final String offenderMiddleName, 
			final String offenderSuffix, final Integer offenderNumber, 
			final Date startDate, final Date endDate, 
			final String classificationName, final String categoryName, 
			final String sourceName, final String comment, 
			final String sourceComment, final Date effectiveDate) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.classificationName = classificationName;
		this.categoryName = categoryName;
		this.sourceName = sourceName;
		this.comment = comment;
		this.sourceComment = sourceComment;
		this.active = new DateRange(startDate, endDate).isActive(effectiveDate);
	}
		
	/**
	 * Gets the id of the special need.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Gets the offender last name of the special need.
	 *
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Gets the offender first name of the special need.
	 *
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Gets the offender middle name of the special need.
	 *
	 * @return the offenderMiddleName
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Gets the offender suffix of the special need.
	 *
	 * @return the offenderSuffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}

	/**
	 * Gets the offender number of the special need.
	 *
	 * @return the offenderNumber
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Gets the start date of the special need.
	 * 
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Gets the end date of the special need.
	 *
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Gets the classification name of the special need.
	 *
	 * @return the classificationName
	 */
	public String getClassificationName() {
		return this.classificationName;
	}

	/**
	 * Gets the category name of the special need.
	 *
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return this.categoryName;
	}

	/**
	 * Gets the source name of the special need.
	 *
	 * @return the sourceName
	 */
	public String getSourceName() {
		return this.sourceName;
	}

	/**
	 * Gets the comment of the special need.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Gets the source comment of the special need.
	 *
	 * @return the sourceComment
	 */
	public String getSourceComment() {
		return this.sourceComment;
	}

	/**
	 * Gets the active of the  .
	 *
	 * @return the active
	 */
	public Boolean getActive() {
		return this.active;
	}
}