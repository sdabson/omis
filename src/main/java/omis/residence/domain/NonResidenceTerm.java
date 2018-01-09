package omis.residence.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.person.domain.Person;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Non residence term.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 19, 2015)
 * @since  OMIS 3.0
 */
public interface NonResidenceTerm extends Creatable, Updatable {

	/**
	 * Returns the id of the non residence term.
	 * 
	 * @return id
	 */
	Long getId();

	/**
	 * Sets the id of the non residence term.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the person of the non residence term.
	 * 
	 * @return person
	 */
	Person getPerson();
	
	/**
	 * Sets the person of the non residence term.
	 * 
	 * @param person person
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the date range of the non residence term.
	 * 
	 * @return dateRange
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range of the non residence term.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the location of the non residence term.
	 * 
	 * @return location
	 */
	Location getLocation();
	
	/**
	 * Sets the location of the non residence term.
	 * 
	 * @param location location
	 */
	void setLocation(Location location);
	
	/**
	 * Returns the state of the non residence term.
	 * 
	 * @return state
	 */
	State getState();
	
	/**
	 * Sets the state of the non residence term.
	 * 
	 * @param state state
	 */
	void setState(State state);
	
	/**
	 * Returns the city of the non residence term.
	 * 
	 * @return city
	 */
	City getCity();
	
	/**
	 * Sets the city of the non residence term.
	 * 
	 * @param city city
	 */
	void setCity(City city);
	
	/**
	 * Returns the notes for the non residence term.
	 * 
	 * @return notes
	 */
	String getNotes();
	
	/**
	 * Sets the notes for the non residence term.
	 * 
	 * @param notes notes
	 */
	void setNotes(String notes);
	
	/**
	 * Returns confirmed for the non residence term.
	 * 
	 * @return confirmed
	 */
	Boolean getConfirmed();
	
	/**
	 * Sets confirmed for the non residence term.
	 * 
	 * @param confirmed confirmed
	 */
	void setConfirmed(Boolean confirmed);
	
	/**
	 * Returns the verification signature for the non residence term.
	 * 
	 * @return verificationSignature
	 */
	VerificationSignature getVerificationSignature();
	
	/**
	 * Sets the verification signature for the non residence term.
	 * 
	 * @param verificationSignature verification signature
	 */
	void setVerificationSignature(VerificationSignature verificationSignature);
	
	/**
	 * Returns the status of the non residence term.
	 * 
	 * @return status
	 */
	ResidenceStatus getStatus();
	
	/**
	 * Sets the status of the non residence term.
	 * 
	 * @param status status
	 */
	void setStatus(ResidenceStatus status);
	
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
