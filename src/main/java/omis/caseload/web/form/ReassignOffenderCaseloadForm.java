package omis.caseload.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.caseload.domain.Caseload;

/**
 * Reassign offender caseload form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 2, 2016)
 * @since OMIS 3.0
 */
public class ReassignOffenderCaseloadForm {
	
	private Date reassignDate;
	private Caseload caseload;
	private Date endDate;
	private List<OffenderTransferItem> offenderTransferItems
					= new ArrayList<OffenderTransferItem>();

	/** Instantiates an implementation of ReassignOffenderCaseloadForm. */
	public ReassignOffenderCaseloadForm() {
		// Default constructor.
	}

	/**
	 * Return offender reassign date.
	 *
	 * @return the reassign date
	 */
	public Date getReassignDate() {
		return this.reassignDate;
	}

	/**
	 * Set offender reassign date.
	 *
	 * @param reassignDate reassign date
	 */
	public void setReassignDate(final Date reassignDate) {
		this.reassignDate = reassignDate;
	}

	/**
	 * Return caseload.
	 *
	 * @return the caseload
	 */
	public Caseload getCaseload() {
		return this.caseload;
	}

	/**
	 * Set caseload.
	 *
	 * @param caseload caseload
	 */
	public void setCaseload(final Caseload caseload) {
		this.caseload = caseload;
	}

	/**
	 * Returns the caseload end date.
	 *
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the caseload end date.
	 *
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns list of caselaod offender transfer items.
	 *
	 * @return the offenderTransferItems
	 */
	public List<OffenderTransferItem> getOffenderTransferItems() {
		return this.offenderTransferItems;
	}

	/**
	 * Sets list of caselaod offender transfer items.
	 *
	 * @param offenderTransferItems offenderTransferItems
	 */
	public void setOffenderTransferItems(
					final List<OffenderTransferItem> offenderTransferItems) {
		this.offenderTransferItems = offenderTransferItems;
	}
}