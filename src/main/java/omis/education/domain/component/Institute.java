package omis.education.domain.component;

import java.io.Serializable;

import omis.education.domain.InstituteCategory;


/**
 * Institute.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 25, 2016)
 *@since OMIS 3.0
 *
 */
public class Institute implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private InstituteCategory category;

	/**
	 * Default constructor for Institute
	 */
	public Institute(){
		//Default
	}
	
	/**
	 * Returns name of institute
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of institute
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns category of institute
	 * @return the instituteCategory
	 */
	public InstituteCategory getCategory() {
		return category;
	}

	/**
	 * Sets category of institute
	 * @param instituteCategory the instituteCategory to set
	 */
	public void setCategory(InstituteCategory category) {
		this.category = category;
	}
	
	
	
	
}
