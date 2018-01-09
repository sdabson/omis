package omis.religion.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Religious accommodation authorized for the duration of the religious
 * preference of an offender.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 22, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousAccommodationAuthorization
		extends Creatable, Updatable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the religious preference the period during which the accommodation
	 * is authorized.
	 * 
	 * @param preference preference period during which accommodation is
	 * authorized
	 */
	void setPreference(ReligiousPreference preference);
	
	/**
	 * Returns the religious preference the period during which the 
	 * accommodation is authorized.
	 * 
	 * @return preference period during which accommodation is authorized
	 */
	ReligiousPreference getPreference();
	
	/**
	 * Sets the accommodation authorized for the duration of the religious
	 * preference.
	 * 
	 * @param accommodation accommodation authorized for duration of religious
	 * preference
	 */
	void setAccommodation(ReligiousAccommodation accommodation);
	
	/**
	 * Returns the accommodation authorized for the duration of the religious
	 * preference.
	 * 
	 * @return accommodation authorized for the duration of the religious
	 * preference
	 */
	ReligiousAccommodation getAccommodation();
	
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