package omis.victim.web.form;

import java.io.Serializable;

/**
 * Form to search for victims.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 4, 2015)
 * @since OMIS 3.0
 */
public class VictimSearchForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private VictimSearchType type;
	
	private Boolean offendersOnly;
	
	private String lastName;
	
	private String firstName;
	
	private Integer offenderNumber;
	
	/** Instantiates victim search form. */
	public VictimSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns type.
	 * 
	 * @return type
	 */
	public VictimSearchType getType() {
		return this.type;
	}
	
	/**
	 * Sets type.
	 * 
	 * @param type type
	 */
	public void setType(final VictimSearchType type) {
		this.type = type;
	}
	
	/**
	 * Returns whether to only return offenders.
	 * 
	 * @return whether to only return offender
	 */
	public Boolean getOffendersOnly() {
		return this.offendersOnly;
	}

	/**
	 * Sets whether to only return offenders.
	 * 
	 * @param offendersOnly whether to only return offenders
	 */
	public void setOffendersOnly(final Boolean offendersOnly) {
		this.offendersOnly = offendersOnly;
	}

	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns offender number. 
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}

	/**
	 * Sets offender number.
	 * 
	 * @param offenderNumber offender number
	 */
	public void setOffenderNumber(final Integer offenderNumber) {
		this.offenderNumber = offenderNumber;
	}
}