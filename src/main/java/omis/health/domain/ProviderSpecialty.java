package omis.health.domain;

import java.io.Serializable;

/**
 * Provider specialty.
 * 
 * @author Sheronda Vaugn
 * @version 0.1.0 (Apr 24, 2014)
 * @since OMIS 3.0
 */
public interface ProviderSpecialty extends Serializable {

	/**
	 * Returns the ID.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 *
	 * Returns the name. 
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * 
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * 
	 * Returns the abbreviation.
	 * 
	 * @return abbreviation
	 */
	String getAbbreviation();
	
	/**
	 * Sets the abbreviation.
	 * 
	 * @param abbreviation abbreviation
	 */
	void setAbbreviation(String abbreviation);
	
	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * 
	 * Sets the description.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * 
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	Short getSortOrder();
	
	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the valid status.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the valid status.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
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