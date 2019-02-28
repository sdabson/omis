package omis.separationneed.report;

import java.io.Serializable;
import java.util.Date;

import omis.supervision.domain.CorrectionalStatus;

/**
 * Separation need summary.
 * 
 * @author Joel Norris
 * @version 0.1.1 (May 4, 2017)
 * @since OMIS 3.0
 */
public class SeparationNeedSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long separationNeedId;
	
	private final Long targetOffenderId;

	private final String targetOffenderFirstName;
	
	private final String targetOffenderLastName;
	
	private final String targetOffenderMiddleInitial;
	
	private final Integer targetOffenderNumber;
	
	private final Long subjectOffenderId;
	
	private final String subjectOffenderFirstName;
	
	private final String subjectOffenderLastName;
	
	private final String subjectOffenderMiddleInitial;
	
	private final Integer subjectOffenderNumber;
	
	private final String targetOffenderHousingLocation;
	
	private final String subjectOffenderHousingLocation;
	
	private final Date date;
	
	private final Date removalDate;
	
	private final Boolean active;
	
	private final String creationComment;
	
	/**
	 * Instantiates an instance of separation need summary with the specified
	 * summary values.
	 * 
	 * @param separationNeedId separation need ID
	 * @param targetOffenderHousingUnit target offender housing unit
	 * @param subjectOffenderHousingUnit subject offender housing unit
	 * @param targetOffenderFirstName target offender first name
	 * @param targetOffenderLastName target offender last name
	 * @param subjectOffenderMiddleInitial subject offender middle initial
	 * @param subjectOffenderFirstName subject offender first name
	 * @param subjectOffenderLastName subject offender last name
	 * @param subjectOffenderMiddleInitial subject offender middle initial
	 * @param date date
	 * @param removalDate removal date
	 * @param active whether active applies
	 * @param creationComment creation comment
	 */
	public SeparationNeedSummary(
			final Long separationNeedId,
			final Long targetOffenderId,
			final Long subjectOffenderId,
			final String targetOffenderHousingLocation,
			final String subjectOffenderHousingLocation,
			final String targetOffenderFirstName,
			final String targetOffenderLastName, 
			final String targetOffenderMiddleInitial,
			final Integer targetOffenderNumber,
			final String subjectOffenderFirstName,
			final String subjectOffenderLastName,
			final String subjectOffenderMiddleInitial,
			final Integer subjectOffenderNumber, final Date date,
			final Date removalDate, final Boolean active,
			final String creationComment) {
		this.targetOffenderId = targetOffenderId;
		this.subjectOffenderId = subjectOffenderId;
		this.separationNeedId = separationNeedId;
		this.targetOffenderHousingLocation = targetOffenderHousingLocation;
		this.subjectOffenderHousingLocation = subjectOffenderHousingLocation;
		this.targetOffenderNumber = targetOffenderNumber;
		this.targetOffenderFirstName = targetOffenderFirstName;
		this.targetOffenderLastName = targetOffenderLastName;
		this.targetOffenderMiddleInitial = targetOffenderMiddleInitial;
		this.subjectOffenderFirstName = subjectOffenderFirstName;
		this.subjectOffenderLastName = subjectOffenderLastName;
		this.subjectOffenderNumber = subjectOffenderNumber;
		this.subjectOffenderMiddleInitial = subjectOffenderMiddleInitial;
		this.date = date;
		this.removalDate = removalDate;
		this.active = active;
		this.creationComment = creationComment;
	}
	
	/**
	 * Instantiates an instance of separation need summary with the specified
	 * summary values, setting active based on correctional status of first and
	 * second person.
	 * 
	 * @param separationNeedId separation need ID
	 * @param targetOffenderHousingUnit target offender housing unit
	 * @param subjectOffenderHousingUnit subject offender housing unit
	 * @param targetOffenderFirstName target offender first name
	 * @param targetOffenderLastName target offender last name
	 * @param subjectOffenderMiddleInitial subject offender middle initial
	 * @param subjectOffenderFirstName subject offender first name
	 * @param subjectOffenderLastName subject offender last name
	 * @param subjectOffenderMiddleInitial subject offender middle initial
	 * @param date date
	 * @param removalDate removal date
	 * @param firstPersonStatus first person correctional status
	 * @param second PersonStatus second person correctional status
	 * @param creationComment creation comment
	 */
	public SeparationNeedSummary(
			final Long separationNeedId,
			final Long targetOffenderId,
			final Long subjectOffenderId,
			final String targetOffenderHousingLocation,
			final String subjectOffenderHousingLocation,
			final String targetOffenderFirstName,
			final String targetOffenderLastName, 
			final String targetOffenderMiddleInitial,
			final Integer targetOffenderNumber,
			final String subjectOffenderFirstName,
			final String subjectOffenderLastName,
			final String subjectOffenderMiddleInitial,
			final Integer subjectOffenderNumber, final Date date,
			final Date removalDate, final CorrectionalStatus firstPersonStatus,
			final CorrectionalStatus secondPersonStatus,
			final String creationComment) {
		this.targetOffenderId = targetOffenderId;
		this.subjectOffenderId = subjectOffenderId;
		this.separationNeedId = separationNeedId;
		this.targetOffenderHousingLocation = targetOffenderHousingLocation;
		this.subjectOffenderHousingLocation = subjectOffenderHousingLocation;
		this.targetOffenderNumber = targetOffenderNumber;
		this.targetOffenderFirstName = targetOffenderFirstName;
		this.targetOffenderLastName = targetOffenderLastName;
		this.targetOffenderMiddleInitial = targetOffenderMiddleInitial;
		this.subjectOffenderFirstName = subjectOffenderFirstName;
		this.subjectOffenderLastName = subjectOffenderLastName;
		this.subjectOffenderNumber = subjectOffenderNumber;
		this.subjectOffenderMiddleInitial = subjectOffenderMiddleInitial;
		this.date = date;
		this.removalDate = removalDate;
		if(firstPersonStatus != null && firstPersonStatus.getLocationRequired() && secondPersonStatus != null) {
			if(firstPersonStatus.equals(secondPersonStatus)) {
				this.active = true;
			} else {
				this.active = false;
			}
		} else {
			this.active = false;
		}
		this.creationComment = creationComment;
	}

	/**
	 * Returns target offender id.
	 * 
	 * @return target offender id
	 */
	public Long getTargetOffenderId() {
		return this.targetOffenderId;
	}

	/**
	 * Returns subject offender id.
	 * 
	 * @return subject offender id.
	 */
	public Long getSubjectOffenderId() {
		return this.subjectOffenderId;
	}

	/**
	 * Returns target offender middle initial.
	 * 
	 * @return target offender middle initial
	 */
	public String getTargetOffenderMiddleInitial() {
		return this.targetOffenderMiddleInitial;
	}

	/**
	 * Returns subject offender middle initial.
	 * 
	 * @return subject offender middle initial
	 */
	public String getSubjectOffenderMiddleInitial() {
		return this.subjectOffenderMiddleInitial;
	}

	/**
	 * Returns the separation need id.
	 * 
	 * @return separation need ID
	 */
	public Long getSeparationNeedId() {
		return this.separationNeedId;
	}

	/**
	 * Returns target offender first name.
	 * 
	 * @return target offender first name
	 */
	public String getTargetOffenderFirstName() {
		return this.targetOffenderFirstName;
	}

	/**
	 * Returns target offender last name.
	 * 
	 * @return target offender last name
	 */
	public String getTargetOffenderLastName() {
		return this.targetOffenderLastName;
	}

	/**
	 * Returns subject offender first name.
	 * 
	 * @return subject offender first name
	 */
	public String getSubjectOffenderFirstName() {
		return this.subjectOffenderFirstName;
	}

	/**
	 * Returns subject offender last name.
	 * 
	 * @return subject offender last name
	 */
	public String getSubjectOffenderLastName() {
		return this.subjectOffenderLastName;
	}

	/**
	 * Returns target offender housing location.
	 * 
	 * @return target offender housing location
	 */
	public String getTargetOffenderHousingLocation() {
		return this.targetOffenderHousingLocation;
	}

	/**
	 * Returns subject offender housing location.
	 * 
	 * @return subject offender housing location
	 */
	public String getSubjectOffenderHousingLocation() {
		return this.subjectOffenderHousingLocation;
	}

	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Returns removal date.
	 * 
	 * @return removal date
	 */
	public Date getRemovalDate() {
		return this.removalDate;
	}
	
	/**
	 * Returns creation comment.
	 * 
	 * @return creation comment
	 */
	public String getCreationComment() {
		return this.creationComment;
	}

	/**
	 * Returns target offender number.
	 * 
	 * @return target offender number
	 */
	public Integer getTargetOffenderNumber() {
		return this.targetOffenderNumber;
	}

	/**
	 * Returns subject offender number.
	 * 
	 * @return subject offender number
	 */
	public Integer getSubjectOffenderNumber() {
		return this.subjectOffenderNumber;
	}

	/**
	 * Returns whether active applies or not.
	 * 
	 * @return active
	 */
	public Boolean getActive() {
		return this.active;
	}
}