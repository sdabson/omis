/**
 * 
 */
package omis.specialneed.domain;

import java.io.Serializable;

/**
 * Special need source.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 14 2013)
 * @since OMIS 3.0
 */
public interface SpecialNeedSource extends Serializable {

	/**
	 * Return the id of the source.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the source.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the name of the source.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name of the source.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Returns the valid value of the source.
	 * 
	 * @return the valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the valid value of the source.
	 * 
	 * @param valid the valid to set
	 */
	void setValid(Boolean valid);
}