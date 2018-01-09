package omis.caseload.report;

import java.util.Date;

/** Offender case assignment summary.
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0 */
public class OffenderCaseAssignmentSummary {
	private final Long offenderCaseWorkId;
	private final Long offenderId;
	private final Integer offenderNumber;
	private final String offenderFirstName;
	private final String offenderLastName;
	private final String offenderMiddleName;
	private final String lastContactCategory;
	private final Date lastContactDate;
	private final Date startDate;
	private final Date endDate;
	
	/** Constructor.
	 * @param offenderCaseWorkId - offender case work id.
	 * @param offenderId - offender id.
	 * @param offenderNumber - offender number.
	 * @param offenderFirstName - offender first name.
	 * @param offenderLastName - offender last name.
	 * @param offenderMiddleName - offender middle name.
	 * @param lastContactCategory - last contact category.
	 * @param lastContactDate - last contact date.
	 * @param startDate - start date.
	 * @param endDate - end date. */
	public OffenderCaseAssignmentSummary(final Long offenderCaseWorkId, 
					final Long offenderId, final Integer offenderNumber,
					final String offenderFirstName, 
					final String offenderLastName,
					final String offenderMiddleName, 
					final String lastContactCategory,
					final Date lastContactDate, final Date startDate, 
					final Date endDate) {
		this.offenderCaseWorkId = offenderCaseWorkId;
		this.offenderId = offenderId;
		this.startDate = startDate;
		this.offenderNumber = offenderNumber;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.lastContactDate = lastContactDate;
		this.endDate = endDate;
		this.lastContactCategory = lastContactCategory;
	}
	
	/** Gets offender case work id. 
	 * @return offender case work id. */
	public Long getOffenderCaseWorkId() {
		return this.offenderCaseWorkId;
	}
	
	/** Gets offender id.
	 * @return offender id. */
	public Long getOffenderId() {
		return this.offenderId;
	}
	
	/** Gets start date.
	 * @return start date. */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/** Gets offender number.
	 * @return offender number. */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/** Gets offender middle name.
	 * @return offender middle name. */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/** Gets offender last name.
	 * @return offender last name. */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/** Gets offender first name.
	 * @return offender first name. */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/** Gets last contact category.
	 * @return last contact category. */
	public String getLastContactCategory() {
		return this.lastContactCategory;
	}
	
	/** Gets end date.
	 * @return end date. */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/** Gest last contact date.
	 * @return last contact date. */
	public Date getLastContactDate() {
		return this.lastContactDate;
	}
}
