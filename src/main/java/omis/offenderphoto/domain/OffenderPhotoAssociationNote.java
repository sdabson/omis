package omis.offenderphoto.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Offender photo association note.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public interface OffenderPhotoAssociationNote extends Creatable, Updatable {
	
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	String getValue();
	
	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	void setValue(String value);
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the offender photo association.
	 * 
	 * @return offender photo association
	 */
	OffenderPhotoAssociation getAssociation();
	
	/**
	 * Sets the offender photo association.
	 * 
	 * @param association offender photo association
	 */
	void setAssociation(OffenderPhotoAssociation association);
	
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
	
}