package omis.workrestriction.domain;

import omis.audit.domain.Authorizable;
import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * WorkRestriction.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestriction 
	extends OffenderAssociable, Authorizable, Creatable, Updatable {
	
	/**
	 * Returns the ID of the work restriction
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Returns the work restriction category of the work restriction
	 * @return Category - WorkRestrictionCategory
	 */
	public WorkRestrictionCategory getCategory();
	
	/**
	 * Returns the notes of the work restriction
	 * @return Notes
	 */
	public String getNotes();
	
	/**
	 * Sets the ID of the work restriction
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Sets the work restriction category of the work restriction
	 * @param category - workRestrictionCategory
	 */
	public void setCategory(WorkRestrictionCategory category);
	
	/**
	 * Sets the notes of the work restriction
	 * @param notes - String
	 */
	public void setNotes(String notes);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	public boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	public int hashCode();

}
