package omis.health.domain;

import java.io.Serializable;

/**
 * Provider title.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Apr 11, 2014)
 * @since OMIS 3.0
 */
public interface ProviderTitle extends Serializable {

	/**
	 * Returns the ID of the provider title.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the ID of the provider title.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the provider assignment category of the provider title.
	 * 
	 * @return provider assignment category
	 */
	ProviderAssignmentCategory getCategory();
	
	/**
	 * Sets the provider assignment category of the provider title.
	 * 
	 * @param category provider assignment category
	 */
	void setCategory(ProviderAssignmentCategory category);
	
	/**
	 * Returns the name of the provider title.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name of the provider title.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the abbreviation of the provider title.
	 * 
	 * @return abbreviation
	 */
	String getAbbreviation();
	
	/**
	 * Sets the abbreviation of the provider title.
	 * 
	 * @param abbreviation abbreviation
	 */
	void setAbbreviation(String abbreviation);
	
	/**
	 * Returns the description of the provider title.
	 * 
	 * @return descripiton
	 */
	String getDescription();
	
	/**
	 * Sets the description of the provider title.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns the sort order of the provider title.
	 * 
	 * @return sort order
	 */
	Short getSortOrder();
	
	/**
	 * Sets the sort order of the provider title.
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the valid status of the provider title.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the valid status of the provider title.
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