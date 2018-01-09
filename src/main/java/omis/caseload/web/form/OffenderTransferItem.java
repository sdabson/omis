package omis.caseload.web.form;

import omis.caseload.domain.OffenderCaseAssignment;

/**
 * Offender transfer item. 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 13, 2017)
 * @since OMIS 3.0
 */
public class OffenderTransferItem {
	private Boolean transfering;
	
	private OffenderCaseAssignment offenderAssignment;
	
	private Long offenderAssignmentId;
	
	private OffenderTransferingItemBoolean offenderTransfering;
	
	/** Instantiates an implementation of OffenderTransferItem. */
	public OffenderTransferItem() {
		// Default constructor.
	}
	
	/**
	 * Returns offender transfer item boolean.
	 *
	 * @return the transfering
	 */
	public Boolean getTransfering() {
		return this.transfering;
	}

	/**
	 * Sets the offender transfer item boolean.
	 *
	 * @param transfering transfering
	 */
	public void setTransfering(final Boolean transfering) {
		this.transfering = transfering;
	}

	/**
	 * Returns the offender transfer item offender assignment.
	 *
	 * @return the offenderAssignment
	 */
	public OffenderCaseAssignment getOffenderAssignment() {
		return this.offenderAssignment;
	}

	/**
	 * Sets the offender transfer item offender assignment.
	 *
	 * @param offenderAssignment offenderAssignment
	 */
	public void setOffenderAssignment(
					final OffenderCaseAssignment offenderAssignment) {
		this.offenderAssignment = offenderAssignment;
	}

	/**
	 * Returns the offender transfer item offender assignment ID.
	 *
	 * @return the offenderAssignmentId
	 */
	public Long getOffenderAssignmentId() {
		return this.offenderAssignmentId;
	}

	/**
	 * Sets the offender transfer item offender assignment ID.
	 *
	 * @param offenderAssignmentId offenderAssignmentId
	 */
	public void setOffenderAssignmentId(final Long offenderAssignmentId) {
		this.offenderAssignmentId = offenderAssignmentId;
	}

	/**
	 * Returns whether the offender transfer item is transferring.
	 *
	 * @return the offenderTransfering
	 */
	public OffenderTransferingItemBoolean getOffenderTransfering() {
		return this.offenderTransfering;
	}

	/**
	 * Sets whether the offender transfer item is transferring.
	 *
	 * @param offenderTransfering offenderTransfering
	 */
	public void setOffenderTransfering(
					final OffenderTransferingItemBoolean offenderTransfering) {
		this.offenderTransfering = offenderTransfering;
	}
}