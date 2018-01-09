package omis.probationterm.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Summary for probation term.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (May 24, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;

	private final String offenderLastName;

	private final String offenderFirstName;

	private final String offenderMiddleName;

	private final String offenderSuffix;

	private final Integer offenderNumber;

	private final String courtCaseDocketValue;

	private final Integer termYears;

	private final Integer termMonths;

	private final Integer termDays;

	private final Date startDate;

	private final Date terminationDate;

	private final Date expirationDate;

	private final Integer jailCredit;

	private final Integer sentenceDays;

	/**
	 * Instantiation of probation term with all objects specified.
	 * 
	 * @param id ID
	 * @param offenderLastName offender last name
	 * @param offenderFirstName offender first name
	 * @param offenderMiddleName offender middle name
	 * @param offenderSuffix offender suffix
	 * @param offenderNumber offender number
	 * @param courtCaseDocket court case docket
	 * @param termYears term years
	 * @param termMonths term months
	 * @param termDays term days
	 * @param startDate start date
	 * @param terminationDate termination date
	 * @param expirationDate expiration date
	 * @param jailCredit jail credit
	 * @param sentenceDays sentence days
	 */
	public ProbationTermSummary(final Long id, final String offenderLastName, 
			final String offenderFirstName, final String offenderMiddleName, 
			final String offenderSuffix, final Integer offenderNumber, 
			final String courtCaseDocket, final Integer termYears, 
			final Integer termMonths, final Integer termDays, 
			final Date startDate, final Date terminationDate,
			final Date expirationDate, final Integer jailCredit,
			final Integer sentenceDays) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.courtCaseDocketValue = courtCaseDocket;
		this.termYears = termYears;
		this.termMonths = termMonths;
		this.termDays = termDays;
		this.startDate = startDate;
		this.terminationDate = terminationDate;
		this.expirationDate = expirationDate;
		this.jailCredit = jailCredit;
		this.sentenceDays = sentenceDays;
	}
	
	/**
	 * Instantiation of probation term with all objects specified.
	 * 
	 * @param expirationDate - expiration date
	 */
	public ProbationTermSummary(final Date expirationDate) {
		this.id = null;
		this.offenderLastName = null;
		this.offenderFirstName = null;
		this.offenderMiddleName = null;
		this.offenderSuffix = null;
		this.offenderNumber = null;
		this.courtCaseDocketValue = null;
		this.termYears = null;
		this.termMonths = null;
		this.termDays = null;
		this.startDate = null;
		this.terminationDate = null;
		this.expirationDate = expirationDate;
		this.jailCredit = null;
		this.sentenceDays = null;
	}

	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Returns the offenders last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return offenderLastName;
	}

	/**
	 * Returns the offenders first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return offenderFirstName;
	}

	/**
	 * Returns the offenders middle name.
	 * 
	 * @return offender middle name
	 */
	public String getOffenderMiddleName() {
		return offenderMiddleName;
	}

	/**
	 * Returns the offenders suffix.
	 * 
	 * @return offender suffix
	 */
	public String getOffenderSuffix() {
		return offenderSuffix;
	}

	/**
	 * Returns the offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return offenderNumber;
	}

	/**
	 * Returns the court case docket.
	 * 
	 * @return court case docket
	 */
	public String getCourtCaseDocketValue() {
		return courtCaseDocketValue;
	}

	/**
	 * Returns the term years.
	 * 
	 * @return term years
	 */
	public Integer getTermYears() {
		return termYears;
	}

	/**
	 * Returns the term months.
	 * 
	 * @return term months
	 */
	public Integer getTermMonths() {
		return termMonths;
	}

	/**
	 * Returns the term days.
	 * 
	 * @return term days
	 */
	public Integer getTermDays() {
		return termDays;
	}

	/**
	 * Returns the start date.
	 * @return start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Returns the termination date.
	 * 
	 * @return termination date
	 */
	public Date getTerminationDate() {
		return terminationDate;
	}

	/**
	 * Returns the expiration date.
	 * 
	 * @return expiration date
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Returns the jail credit.
	 * 
	 * @return jail credit
	 */
	public Integer getJailCredit() {
		return jailCredit;
	}

	/**
	 * Returns the sentence days.
	 * 
	 * @return sentence days
	 */
	public Integer getSentenceDays() {
		return sentenceDays;
	}
}
