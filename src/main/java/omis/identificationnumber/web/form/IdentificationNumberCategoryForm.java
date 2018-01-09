package omis.identificationnumber.web.form;

import omis.identificationnumber.domain.Multitude;

/**
 * IdentificationNumberCategoryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Nov 1, 2017)
 *@since OMIS 3.0
 *
 */
public class IdentificationNumberCategoryForm {
	
	public String name;
	
	public Multitude multitude;
	
	public Boolean valid;
	
	/**
	 * 
	 */
	public IdentificationNumberCategoryForm() {
	}

	/**
	 * Returns the name
	 * @return name - String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name - String
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the multitude
	 * @return multitude - Multitude
	 */
	public Multitude getMultitude() {
		return multitude;
	}

	/**
	 * Sets the multitude
	 * @param multitude - Multitude
	 */
	public void setMultitude(final Multitude multitude) {; 
		this.multitude = multitude;
	}

	/**
	 * Returns the valid
	 * @return valid - Boolean
	 */
	public Boolean getValid() {
		return valid;
	}

	/**
	 * Sets the valid
	 * @param valid - Boolean
	 */
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
}
