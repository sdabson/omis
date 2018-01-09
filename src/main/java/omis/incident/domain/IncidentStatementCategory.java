package omis.incident.domain;

import java.io.Serializable;

/**
 * Incident statement category.
 * @author Joel Norris
 * @version 0.1.0 (Sept 9, 2016)
 * @since OMIS 3.0
 */
public interface IncidentStatementCategory extends Serializable {
	
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	public void setId(Long id);
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName();
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(String name);
	
	/**
	 * Returns whether valid applies.
	 * 
	 * @return valid
	 */
	public Boolean getValid();
	
	/**
	 * Sets whether valid applies.
	 * 
	 * @param valid valid
	 */
	public void setValid(Boolean valid);
	
	/**
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	public Short getSortOrder();
	
	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	public void setSortOrder(Short sortOrder);
	
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