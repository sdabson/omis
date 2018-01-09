package omis.residence.domain;

import omis.address.domain.Address;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.person.domain.Person;

/**
 * Residence status term. 
 *
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.0.1 (Sep 19, 2014)
 * @since OMIS 3.0
 */
public interface ResidenceTerm
		extends Creatable, Updatable {

	/**
	 * Sets the ID of the residence term.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the residence term.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the person of the residence term.
	 * 
	 * @param person person
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the person of residence term.
	 * 
	 * @return person
	 */
	Person getPerson();
	
	/**
	 * Sets the date range of the residence term.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range of the residence term.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Returns the address of the residence term.
	 * 
	 * @return address
	 */
	Address getAddress();
	
	/**
	 * Sets the address of the residence term.
	 * 
	 * @param address address
	 */
	void setAddress(Address address);
	
	/**
	 * Returns confirmed for the residence term.
	 * 
	 * @return confirmed
	 */ 
	Boolean getConfirmed();
	
	/**
	 * Sets confirmed for the residence term.
	 * 
	 * @param confirmed confirmed
	 */
	void setConfirmed(Boolean confirmed);
	
	/**
	 * Returns the notes of the residence term.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets the notes of the residence term.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns the verification signature of the residence term.
	 * 
	 * @return verficationSignature
	 */
	VerificationSignature getVerificationSignature();
	
	/**
	 * Sets the verification signature of the residence term.
	 * 
	 * @param verficationSignature verification signature
	 */
	void setVerificationSignature(VerificationSignature verficationSignature);
	
	/**
	 * Sets the status of the residence term.
	 * 
	 * @param status status
	 */
	void setStatus(ResidenceStatus status);
	
	/**
	 * Returns the status of the residence term.
	 * 
	 * @return status
	 */
	ResidenceStatus getStatus();
	
	/**
	 * Returns the category of the residence term.
	 * 
	 * @return category
	 */
	ResidenceCategory getCategory();
	
	/**
	 * Sets the category of the residence term.
	 * 
	 * @param category category
	 */
	void setCategory(ResidenceCategory category);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}