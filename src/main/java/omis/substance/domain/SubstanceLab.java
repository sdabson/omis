package omis.substance.domain;

import java.io.Serializable;

/**
 * Substance lab.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 7, 2016)
 * @since OMIS 3.0
 */
public interface SubstanceLab extends Serializable{

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
	 * Returns whether private lab applies.
	 * 
	 * @return privateLab
	 */
	public Boolean getPrivateLab();

	/**
	 * Sets whether private lab applies.
	 * 
	 * @param privateLab private lab
	 */
	public void setPrivateLab(Boolean privateLab);
	
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
}