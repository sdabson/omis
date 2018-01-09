package omis.adaaccommodation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;

/**
 * ADA accommodation issuance.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public interface AccommodationIssuance 
	extends Creatable, Updatable {

	/**
	 * Sets the ID of the ADA accommodation issuance.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the ADA accommodation issuance.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ADA accommodation issuance time stamp.
	 * 
	 * @param timestamp time stamp
	 */
	void setTimestamp(Date timestamp);
	
	/**
	 * Return the ADA accommodation issuance time stamp.
	 * 
	 * @return time stamp
	 */
	Date getTimestamp();
	
	/**
	 * Sets the ADA accommodation issuance issuer.
	 * 
	 * @param issuer issuer
	 */
	void setIssuer(Person issuer);
	
	/**
	 * Returns the ADA accommodation issuance issuer.
	 * 
	 * @return issuer
	 */
	Person getIssuer();
	
	/**
	 * Sets the ADA accommodation issuance.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Return the ADA accommodation issuance.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Sets the ADA issuance accommodation.
	 * 
	 * @param accommodation accommodation
	 */
	void setAccommodation(Accommodation accommodation);
	
	/**
	 * Return the ADA issuance accommodation.
	 * 
	 * @return accommodation
	 */
	Accommodation getAccommodation();
	
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