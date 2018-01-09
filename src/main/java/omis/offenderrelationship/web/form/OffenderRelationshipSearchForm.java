package omis.offenderrelationship.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Form for searching for offender relationships.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (Jun 24, 2015)
 * @since OMIS 3.0
 */
public class OffenderRelationshipSearchForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private OffenderRelationshipSearchType type;
	
	private String lastName;
	
	private String firstName;
	
	private Integer offenderNumber;
	
	private Date birthDate;
	
	private String socialSecurityNumber;
	

	/** Instantiates a default form for searching for offender relationships. */
	public OffenderRelationshipSearchForm() {
		// Default instantiation
	}

	/**
	 * Returns type.
	 * 
	 * @return type
	 */
	public OffenderRelationshipSearchType getType() {
		return this.type;
	}

	/**
	 * Sets type.
	 * 
	 * @param type type
	 */
	public void setType(final OffenderRelationshipSearchType type) {
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
	public void setSocialSecurityNumber(final String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
}