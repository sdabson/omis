package omis.visitation.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Visitor Log Summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 24, 2013)
 * @since OMIS 3.0
 */
public class VisitSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Boolean currentlyVisiting;
	
	private final Long visitId;
	
	private final String lastName;

	private final String firstName;
	
	private final Date date;
	
	private final String badgeNumber;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	/**
	 * Instantiates an instance of visitor summary report object.
	 * @param currentlyVisiting currently visiting
	 * @param visitId visit id
	 * @param lastName last name
	 * @param firstName first name
	 * @param date date
	 * @param badgeNumber badge number
	 */
	public VisitSummary(final Boolean currentlyVisiting, 
			final Long visitId, final String lastName, 
			final String firstName, final Date date, final String badgeNumber,
			final String offenderLastName, final String offenderFirstName,
			final String offenderMiddleName, final Integer offenderNumber) {
		this.currentlyVisiting = currentlyVisiting;
		this.visitId = visitId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.date = date;
		this.badgeNumber = badgeNumber;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
	}
	
	/**
	 * Instantiates an instance of visitor log summary report object.
	 * @param currentlyVisiting currently visiting
	 * @param visitId visit id
	 * @param lastName last name
	 * @param firstName first name
	 * @param date date
	 * @param badgeNumber badge number
	 */
	public VisitSummary(final Boolean currentlyVisiting, 
			final Long visitId, final String lastName, 
			final String firstName, final Date date, final String badgeNumber) {
		this.currentlyVisiting = currentlyVisiting;
		this.visitId = visitId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.date = date;
		this.badgeNumber = badgeNumber;
		this.offenderLastName = null;
		this.offenderFirstName = null;
		this.offenderMiddleName = null;
		this.offenderNumber = null;
	}
	
	/**
	 * Return the currently visiting value of the visitor log summary.
	 * @return currently visiting value
	 */
	public Boolean getCurrentlyVisiting() {
		return this.currentlyVisiting;
	}
	
	/**
	 * Return the visit id of the visitor log summary.
	 * @return visit id
	 */
	public Long getVisitId() {
		return this.visitId;
	}

	/**
	 * Return the last name of the visitor log summary.
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Return the first name of the visitor log summary.
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Return the date of the visitor log summary.
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Return the badge number of the visitor log summary.
	 * @return badge number
	 */
	public String getBadgeNumber() {
		return this.badgeNumber;
	}

	/**
	 * Returns the offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * Returns the offender first name.
	 * 
	 * @return first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * Returns the offender middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}

	/**
	 * Returns the offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
}