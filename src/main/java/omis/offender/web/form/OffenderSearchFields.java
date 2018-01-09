package omis.offender.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Fields to search for offenders.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 10, 2015)
 * @since OMIS 3.0
 */
public class OffenderSearchFields
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OffenderSearchType type;
	
	private String lastName;
	
	private String firstName;
	
	private Integer offenderNumber;
	
	private String socialSecurityNumber;
	
	private Date birthDate;
	
	/** Instantiates default fields for offender search. */
	public OffenderSearchFields() {
		// Default instantiation
	}

	/**
	 * Returns type.
	 * 
	 * @return type
	 */
	public OffenderSearchType getType() {
		return this.type;
	}

	/**
	 * Sets type.
	 * 
	 * @param type type
	 */
	public void setType(final OffenderSearchType type) {
		this.type = type;
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

	/**
	 * Returns social security number.
	 * 
	 * @return social security number
	 */
	public String getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}

	/**
	 * Sets social security number.
	 * 
	 * @param socialSecurityNumber social security number
	 */
	public void setSocialSecurityNumber(
			final String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * Returns birth date.
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}

	/**
	 * Sets birth date.
	 * 
	 * @param birthDate birth date
	 */
	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}
}