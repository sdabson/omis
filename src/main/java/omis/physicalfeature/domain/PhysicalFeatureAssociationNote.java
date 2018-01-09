package omis.physicalfeature.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Physical feature association note.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 5, 2015)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureAssociationNote extends Creatable, Updatable {

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
	 * Returns the note.
	 * 
	 * @return note
	 */
	String getNote();
	
	/**
	 * Sets the note.
	 * 
	 * @param note note
	 */
	void setNote(String note);
	
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
	 * Returns the physical feature association.
	 * 
	 * @return physical feature association
	 */
	PhysicalFeatureAssociation getPhysicalFeatureAssociation();
	
	/**
	 * Sets the physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 */
	void setPhysicalFeatureAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation);
	
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