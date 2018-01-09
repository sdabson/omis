package omis.contact.domain;

import java.io.Serializable;
import omis.contact.domain.OnlineAccountCategory;

/**
 * Online account host
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 1, 2015)
 * @since: OMIS 3.0
 */
public interface OnlineAccountHost extends Serializable{
	/**
	 * Returns the online account host id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	/**
	 * Sets the online account host id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the online account name.
	 * 
	 * @param name online account name
	 */
	void setName(String name);
	
	/**
	 * Gets the online account name.
	 * 
	 * @returns online account name
	 */
	String getName();
	
	/**
	 * Sets the "valid".
	 * 
	 * @param valid this online account host is valid or not 
	 */
	void setValid(Boolean valid);
	
	/**
	 * Gets the "valid".
	 * 
	 * @returns valid this online account host is valid or not 
	 */
	Boolean getValid();
	
	/**
	 * Sets the category.
	 * 
	 * @param category online account category 
	 */
	void setCategory(OnlineAccountCategory category);
	
	/**
	 * Gets the category.
	 * 
	 * @returns category online account category  
	 */
	OnlineAccountCategory getCategory();
	
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