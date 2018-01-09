package omis.commitstatus.domain;

import java.io.Serializable;

/**
 * Commit status 
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (May 31, 2017)
 * @since: OMIS 3.0
 */
public interface CommitStatus extends Serializable{
	/**
	 * Returns the commit status id.
	 * 
	 * @returns id
	 */
	Long getId();
	
	/**
	 * Sets id
	 * 
	 * @param name
	 */
	void setId(Long id);
	
	/**
	 * Returns the name.
	 * 
	 * @returns name
	 */
	String getName();
	
	/**
	 * Sets the name
	 * 
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * Returns the valid.
	 * 
	 * @returns valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the valid
	 * 
	 * @param valid
	 */
	void setValid(Boolean valid);
}