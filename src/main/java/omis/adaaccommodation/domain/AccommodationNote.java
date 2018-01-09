package omis.adaaccommodation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
* ADA accommodation note.
*
* @author Sheronda Vaughn
* @version 0.1.0 (Jul 14, 2015)
* @since OMIS 3.0
*/
public interface AccommodationNote 
	extends Creatable, Updatable {

	/**
	 * Sets the ID of the ADA accommodation note.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the ADA accommodation note.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ADA accommodation note text.
	 * 
	 * @param text text
	 */
	void setText(String text);
	
	/**
	 * Return the ADA accommodation note text.
	 * 
	 * @return text
	 */
	String getText();
	
	/**
	 * Sets the ADA accommodation note date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Return the date of the ADA accommodation note.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the ADA accommodation of the ADA accommodation note.
	 * 
	 * @param accommodation accommodation
	 */
	void setAccommodation(Accommodation accommodation);
	
	/**
	 * Return the ADA accommodation of the ADA accommodation note.
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