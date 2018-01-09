package omis.caseload.web.form;

import java.util.Date;

import omis.person.domain.Person;

/**
 * Reassign officer caseload form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public class ReassignOfficerCaseloadForm {
	
	private Date reassignDate;
	private Person staffMember;

	/** Instantiates an implementation of ReassignOfficerCaseloadForm. */
	public ReassignOfficerCaseloadForm() {
		// Default constructor.
	}

	/**
	 * Returns the staff member reassign date.
	 *
	 * @return the reassignDate
	 */
	public Date getReassignDate() {
		return this.reassignDate;
	}

	/**
	 * Sets the staff member reassign date.
	 *
	 * @param reassignDate reassign date
	 */
	public void setReassignDate(final Date reassignDate) {
		this.reassignDate = reassignDate;
	}

	/**
	 * Return staff member.
	 *
	 * @return the staffMember
	 */
	public Person getStaffMember() {
		return this.staffMember;
	}

	/**
	 * Sets the staff member.
	 *
	 * @param staffMember staff member
	 */
	public void setStaffMember(final Person staffMember) {
		this.staffMember = staffMember;
	}	
}