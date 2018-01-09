package omis.staff.web.form;

import java.io.Serializable;

/**
 * Form for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffTitleForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Short sortOrder;
	
	private Boolean valid;
	
	/** Instantiates a form for staff titles. */
	public StaffTitleForm() {
		// Default instantiation
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * Returns whether the staff title is valid.
	 * 
	 * @return whether staff title is valid
	 */
	public Boolean getValid() {
		return this.valid;
	}

	/**
	 * Sets whether the staff title is valid.
	 * 
	 * @param valid whether staff title is valid
	 */
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
}