package omis.substance.domain;

import java.io.Serializable;

/**
 * Substance.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 3, 2013)
 * @since OMIS 3.0
 */
public interface Substance extends Serializable {
	
	/**
	 * Return the id of the substance.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the substance.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the name of the substance.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name of the substance.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Returns whether valid applies.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets whether valid applies.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether testable applies.
	 * 
	 * @return testable
	 */
	Boolean getTestable();
	
	/**
	 * Sets whether testable applies.
	 * 
	 * @param testable testable
	 */
	void setTestable(Boolean testable);
}