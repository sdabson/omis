package omis.workrestriction.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * WorkRestrictionNote.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 25, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionNote extends Creatable, Updatable {
	
	/**
	 * Returns the id
	 * @return ID 
	 */
	public Long getId();
	
	/**
	 * Sets the id
	 * @param id
	 */
	public void setId(Long id);
	
	/**
	 * Returns the WorkRestriction
	 * @return workRestriction
	 */
	public WorkRestriction getWorkRestriction();
	
	/**
	 * Sets the WorkRestriction
	 * @param workRestriction
	 */
	public void setWorkRestriction(WorkRestriction workRestriction);
	
	/**
	 * Returns the date
	 * @return date
	 */
	public Date getDate();
	
	/**
	 * Sets the date
	 * @param date
	 */
	public void setDate(Date date);
	
	/**
	 * Returns the value
	 * @return value - String
	 */
	public String getValue();
	
	/**
	 * Sets the value
	 * @param value - String
	 */
	public void setValue(String value);
	
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
