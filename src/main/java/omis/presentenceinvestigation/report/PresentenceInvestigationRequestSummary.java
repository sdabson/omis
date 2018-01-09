package omis.presentenceinvestigation.report;

import java.util.Date;

/** Report object for presentence investigation summary.
 * @author Ryan Johns
 * @author Annie Jacques
 * @version 0.1.1 (May 16, 2017)
 * @since OMIS 3.0 */
public class PresentenceInvestigationRequestSummary {
	
	private final Long presentenceInvestigationRequestId;
	private final Long offenderId;
	private final String docketValue;
	private final Date requestDate;
	private final String status;
	private final String assignedUserFirstName;
	private final String assignedUserLastName;
	private final String assignedUserUserName;
	private final Date expectedCompletionDate;
	private final Date completionDate;
	private final String offenderFirstName;
	private final String offenderLastName;
	private final String offenderMiddleName;
	private final Integer offenderNumber;
	private final Date sentenceDate;
	private final String category;
	
	
	
	
	
	/** Constructor.
	 * @param presentenceInvestigationRequstId - presentence investigation
	 * request id.
	 * @param offenderId - offender id
	 * @param docketValue - docketValue.
	 * @param requestDate - request date.
	 * @param status - status.
	 * @param assignedUserFirstName - assigned user first name.
	 * @param assignedUserLastName - assigned user last name.
	 * @param assignedUserUserName - assigned user user name
	 * @param expectedCompletionDate - expected completion date.
	 * @param completionDate - completion date.	 
	 * @param offenderFirstName - offender first name
	 * @param offenderLastName - offender last name
	 * @param offenderMiddleName - offender middle name
	 * @param offenderNumber - offender number
	 * @param sentenceDate - sentence Date
	 * @param category - Presentence Investigation Category
	 */
	public PresentenceInvestigationRequestSummary(
			final Long presentenceInvestigationRequestId, final Long offenderId,
			final String docketValue, final Date requestDate, final String status,
			final String assignedUserFirstName, final String assignedUserLastName,
			final String assignedUserUserName, final Date expectedCompletionDate,
			final Date completionDate, final String offenderFirstName,
			final String offenderLastName, final String offenderMiddleName,
			final Integer offenderNumber, final Date sentenceDate,
			final String category) {
		this.presentenceInvestigationRequestId = presentenceInvestigationRequestId;
		this.offenderId = offenderId;
		this.docketValue = docketValue;
		this.requestDate = requestDate;
		this.status = status;
		this.assignedUserFirstName = assignedUserFirstName;
		this.assignedUserLastName = assignedUserLastName;
		this.assignedUserUserName = assignedUserUserName;
		this.expectedCompletionDate = expectedCompletionDate;
		this.completionDate = completionDate;
		this.offenderFirstName = offenderFirstName;
		this.offenderLastName = offenderLastName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.sentenceDate = sentenceDate;
		this.category = category;
	}

	

	/**
	 * @return the presentenceInvestigationRequestId
	 */
	public Long getPresentenceInvestigationRequestId() {
		return this.presentenceInvestigationRequestId;
	}
	

	/**
	 * @return the offenderId
	 */
	public Long getOffenderId() {
		return this.offenderId;
	}

	/**
	 * @return the docketValue
	 */
	public String getDocketValue() {
		return this.docketValue;
	}

	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return this.requestDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @return the assignedUserFirstName
	 */
	public String getAssignedUserFirstName() {
		return this.assignedUserFirstName;
	}

	/**
	 * @return the assignedUserLastName
	 */
	public String getAssignedUserLastName() {
		return this.assignedUserLastName;
	}
	
	/**
	 * @return the assignedUserUserName
	 */
	public String getAssignedUserUserName() {
		return this.assignedUserUserName;
	}

	/**
	 * @return the expectedCompletionDate
	 */
	public Date getExpectedCompletionDate() {
		return this.expectedCompletionDate;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return this.completionDate;
	}

	/**
	 * @return the offenderFirstName
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}

	/**
	 * @return the offenderLastName
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}

	/**
	 * @return the offenderNumber
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Returns the offenderMiddleName
	 * @return offenderMiddleName - String
	 */
	public String getOffenderMiddleName() {
		return offenderMiddleName;
	}

	/**
	 * Returns the sentenceDate
	 * @return sentenceDate - Date
	 */
	public Date getSentenceDate() {
		return sentenceDate;
	}

	/**
	 * Returns the category
	 * @return category - String
	 */
	public String getCategory() {
		return category;
	}
	
	
}
