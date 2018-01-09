package omis.specialneed.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Special need note.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeedNote extends Creatable, Updatable {
	
	/**
	 * Sets the ID of the special need note.
	 *
	 *
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Return the ID of the special need note.
	 *
	 *
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the special need associated with this special need note.
	 *
	 *
	 * @param specialNeed special need
	 */
	void setSpecialNeed(SpecialNeed specialNeed);
	
	/**
	 * Returns the special need associated with the special need note.
	 *
	 *
	 * @return special need
	 */
	SpecialNeed getSpecialNeed();
	
	/**
	 * Sets the date of the special need note.
	 *
	 *
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the date of the special need note.
	 *
	 *
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the value of the special need note.
	 *
	 *
	 * @param value value
	 */
	void setValue(String value);
	
	/**
	 * Return the value of the special need note.
	 *
	 *
	 * @return value
	 */
	String getValue();
	
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