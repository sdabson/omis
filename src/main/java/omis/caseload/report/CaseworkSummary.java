package omis.caseload.report;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.CaseworkCategory;

/** SUmmary for casework.
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0 */
public class CaseworkSummary {
	private final Long caseloadId;
	private final Long caseWorkerAssignmentId;
	private final String caseWorkerFirstName;
	private final String caseWorkerLastName;
	private final String caseWorkerMiddleName;
	private final Date startDate;
	private final Date endDate;
	private final String caseloadName;
	private final CaseworkCategory caseworkCategory;
	
	private List<OffenderCaseAssignmentSummary> offenderCases;
	
	/** constructor.
	 * @param caseloadId - caseload id.
	 * @param caseWorkerAssignmentId - case worker assignment id.
	 * @param caseWorkerFirstName - case worker first name.
	 * @param caseWorkerLastName - case worker last name.
	 * @param caseWorkerMiddleName - case worker middle name.
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @param caseloadName - caseload name. 
	 * @param caseworkCategory - case work category. */
	public CaseworkSummary(final Long caseloadId, 
					final Long caseWorkerAssignmentId,
					final String caseWorkerFirstName, 
					final String caseWorkerLastName, 
					final String caseWorkerMiddleName,
					final Date startDate, final Date endDate,
					final String caseloadName,
					final CaseworkCategory caseworkCategory) {
		this.caseloadId = caseloadId;
		this.caseWorkerAssignmentId = caseWorkerAssignmentId;
		this.caseWorkerFirstName = caseWorkerFirstName;
		this.caseWorkerLastName = caseWorkerLastName;
		this.caseWorkerMiddleName = caseWorkerMiddleName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.caseloadName = caseloadName;
		this.caseworkCategory = caseworkCategory;
	}
	
	/** Sets offender cases for this casework.
	 * @param offenderCases - offender cases. */
	public void setOffenderCases(final List<OffenderCaseAssignmentSummary> 
					offenderCases) {
		this.offenderCases = offenderCases;
	}
	
	/** Gets caseload id.
	 * @return caseload id. */
	public Long getCaseloadId() {
		return this.caseloadId;
	}
	
	/** Gets case worker assignment id. 
	 * @return case worker assignment id. */
	public Long getCaseWorkerAssignmentId() {
		return this.caseWorkerAssignmentId;
	}
	
	/** Gets case worker first name. 
	 * @return case worker first name. */
	public String getCaseWorkerFirstName() {
		return this.caseWorkerFirstName;
	}
	
	/** Gets case worker last name. 
	 * @return case worker last name. */
	public String getCaseWorkerLastName() {
		return this.caseWorkerLastName;
	}
	
	/** Gets case worker middle name.
	 * @return case worker middle name. */
	public String getCaseWorkerMiddleName() {
		return this.caseWorkerMiddleName;
	}

	/** Gets start date.
	 * @return start date. */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/** Gets end date.
	 * @return end date. */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/** Gets caseload name.
	 * @return caseload name. */
	public String getCaseloadName() {
		return this.caseloadName;
	}
	
	/** Gets casework category.
	 * @return casework category. */
	public CaseworkCategory getCaseworkCategory() {
		return this.caseworkCategory;
	}
	
	/** gets offender case assignments.
	 * @return offender case assignments. */
	public List<OffenderCaseAssignmentSummary> getOffenderCases() {
		return this.offenderCases;
	}
}
