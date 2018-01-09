package omis.caseload.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;

/**
 * Caseload contact.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 26, 2016)
 * @since OMIS 3.0
 */
public interface CaseloadContact extends Creatable, Updatable {
	
	/**
	 * Sets the ID for the contact.
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID for the contact.
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the date for the contact.
	 *
	 * @param contactDate contact date
	 */
	void setContactDate(Date contactDate);
	
	/**
	 * Returns the date for the contact.
	 *
	 * @return contact date
	 */
	Date getContactDate();
	
	/**
	 * Sets the person to contact by for the contact.
	 *
	 * @param contactBy contact by 
	 */
	void setContactBy(Person contactBy);
	
	/**
	 * Returns the person to contact by for the contact.
	 *
	 * @return contact by person
	 */
	Person getContactBy();
	
	/**
	 * Sets the contact category for the contact.
	 *
	 * @param category category
	 */
	void setCategory(ContactCategory category);
	
	/**
	 * Returns the contact category for the contact.
	 *
	 * @return category
	 */
	ContactCategory getCategory();
	
	/**
	 * Sets the caseload for the contact.
	 *
	 * @param caseload caseload
	 */
	void setCaseAssignment(OffenderCaseAssignment caseAssignment);
	
	/**
	 * Returns the caseload for the contact.
	 *
	 * @return caseload
	 */
	OffenderCaseAssignment getCaseAssignment();
	
	/**
	 * Sets the case note of the caseload contact.
	 *
	 *
	 * @param caseNote case note
	 */
	void setCaseNote(String caseNote);
	
	/**
	 * Returns the case note of the caseload contact.
	 *
	 *
	 * @return case note
	 */
	String getCaseNote();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}