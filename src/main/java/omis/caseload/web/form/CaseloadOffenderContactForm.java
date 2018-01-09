package omis.caseload.web.form;

import java.util.Date;

import omis.caseload.domain.CaseloadContact;
import omis.caseload.domain.ContactCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.person.domain.Person;

/**
 * Caseload offender contact form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Oct 3, 2016)
 * @since OMIS 3.0
 */
public class CaseloadOffenderContactForm {

	private Date contactDate;
	private Person contactBy;
	private ContactCategory category;
	private OffenderCaseAssignment offenderAssignment;
	private CaseloadContact offenderContact;
	private String caseNote;
	

	/** Instantiates an implementation of CaseloadOffenderContactForm. */
	public CaseloadOffenderContactForm() {
		// Default constructor.
	}


	/**
	 * Gets the contact date of contact with offender.
	 *
	 * @return the date
	 */
	public Date getContactDate() {
		return this.contactDate;
	}


	/** Sets the date of contact with offender.
	 * @param contactDate contact date */
	public void setContactDate(final Date contactDate) {
		this.contactDate = contactDate;
	}


	/**
	 * Gets the contact by person of contact with offender.
	 *
	 * @return the contactBy
	 */
	public Person getContactBy() {
		return this.contactBy;
	}


	/** Sets the contact by person of contact with offender.
	 * @param contactBy contactBy */
	public void setContactBy(final Person contactBy) {
		this.contactBy = contactBy;
	}


	/**
	 * Gets the contact category of contact with offender.
	 *
	 * @return the category
	 */
	public ContactCategory getCategory() {
		return this.category;
	}


	/** Sets the contact category of contact with offender.
	 * @param category category */
	public void setCategory(final ContactCategory category) {
		this.category = category;
	}


	/**
	 * Gets the offender case assignment of contact with offender.
	 *
	 * @return the offenderAssignment
	 */
	public OffenderCaseAssignment getOffenderAssignment() {
		return this.offenderAssignment;
	}


	/** Sets the offender assignment of contact with offender.
	 * @param offenderAssignment offenderAssignment */
	public void setOffenderAssignment(
					final OffenderCaseAssignment offenderAssignment) {
		this.offenderAssignment = offenderAssignment;
	}


	/** Gets offender contact.
	 * @return the offenderContact */
	public CaseloadContact getOffenderContact() {
		return this.offenderContact;
	}


	/** Sets offender contact.
	 * @param offenderContact offenderContact */
	public void setOffenderContact(final CaseloadContact offenderContact) {
		this.offenderContact = offenderContact;
	}


	/** Returns the case note.
	 * @return the caseNote */
	public String getCaseNote() {
		return this.caseNote;
	}


	/** Sets the case note.
	 * @param caseNote case note */
	public void setCaseNote(final String caseNote) {
		this.caseNote = caseNote;
	}
}