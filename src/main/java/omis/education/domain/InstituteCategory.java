package omis.education.domain;

import omis.audit.domain.Creatable;

/**
 * InstituteCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 25, 2016)
 *@since OMIS 3.0
 *
 */
public interface InstituteCategory extends Creatable {

	/**
	 * Gets the ID
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Returns name of institute category
	 * @return name
	 */
	public String getName();
	
	/**
	 * Gets the sort order
	 * @return sort order
	 */
	public Short getSortOrder();
	
	/**
	 * Gets valid
	 * @return valid
	 */
	public Boolean getValid();
	
	/**
	 * Sets name of institute category
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Sets the sort order
	 * @param sortOrder - sort order
	 */
	public void setSortOrder(Short sortOrder);
	
	/**
	 * Sets valid
	 * @param valid - valid
	 */
	public void setValid(Boolean valid);
	
	/**
	 * Sets the ID
	 * @param id - id
	 */
	public void setId(Long id);
	
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
