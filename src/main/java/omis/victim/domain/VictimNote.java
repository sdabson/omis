package omis.victim.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.person.domain.Person;

/**
 * Victim note.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 22, 2015)
 * @since OMIS 3.0
 */
public interface VictimNote
		extends Creatable, Updatable {

	/**
	 * Sets ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets category.
	 *  
	 * @param category category
	 */
	void setCategory(VictimNoteCategory category);
	
	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	VictimNoteCategory getCategory();
	
	/**
	 * Sets victim.
	 * 
	 * @param victim victim
	 */
	void setVictim(Person victim);
	
	/**
	 * Returns victim.
	 * 
	 * @return victim.
	 */
	Person getVictim();
	
	/**
	 * Sets association.
	 * 
	 * @param association association
	 */
	void setAssociation(VictimAssociation association);
	
	/**
	 * Returns association.
	 * 
	 * @return association
	 */
	VictimAssociation getAssociation();
	
	/**
	 * Sets date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	void setValue(String value);
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	String getValue();
	
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