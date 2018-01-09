package omis.specialneed.domain;

import java.io.Serializable;

/**
 * Special need classification.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeedClassification extends Serializable {
	
	/**
	 * Returns the id of the classification.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the classification.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Returns the name of the classification.
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name of the classification.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Returns the valid value of the classification.
	 * 
	 * @return the valid
	 */
	Boolean getValid();

	/**
	 * sets the valid value of the classification.
	 * 
	 * @param valid the valid to set
	 */
	void setValid(Boolean valid);
}