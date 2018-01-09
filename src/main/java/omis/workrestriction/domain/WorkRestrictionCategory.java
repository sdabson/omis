package omis.workrestriction.domain;

import java.io.Serializable;

/**
 * WorkRestrictionCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionCategory extends Serializable{
	
	/**
	 * Returns the ID of the work restriction category
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Returns the name of the work restriction category
	 * @return Name
	 */
	public String getName();
	
	/**
	 * Returns the state of valid of the work restriction category
	 * @return Valid 
	 */
	public Boolean getValid();
	
	/**
	 * Sets the ID of the work restriction category
	 * @param id - ID 
	 */
	public void setId(Long id);
	
	/**
	 * Sets the name of the work restriction category
	 * @param name - Name
	 */
	public void setName(String name);
	
	/**
	 * Sets the state of valid of the work restriction category
	 * @param valid - valid
	 */
	public void setValid(Boolean valid);
	
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
