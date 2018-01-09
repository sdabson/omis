package omis.alert.web.form;

import java.util.Date;

import omis.person.domain.Person;


/**
 * Form for offender alerts.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.1 (Dec 11, 2012)
 * @since OMIS 3.0
 */
public class AlertForm {
	
	private String description;
	
	private Date resolveDate;
	
	private String resolveDescription;
	
	private Person resolveByPerson;
	
	private Date expireDate;
	
	/** Instantiates an alert form. */
	public AlertForm() {
		// Default instantiation
	}
	
	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * Sets the description.
	 * 
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**
	 * Returns the resolve date.
	 * 
	 * @return resolve date
	 */
	public Date getResolveDate() {
		return this.resolveDate;
	}
	
	/**
	 * Sets the resolve date.
	 * 
	 * @param resolveDate resolve date
	 */
	public void setResolveDate(final Date resolveDate) {
		this.resolveDate = resolveDate;
	}
	
	/**
	 * Returns the resolve description.
	 * 
	 * @return resolve description
	 */
	public String getResolveDescription() {
		return this.resolveDescription;
	}
	
	/**
	 * Sets the resolve description.
	 * 
	 * @param resolveDescription resolve description
	 */
	public void setResolveDescription(
			final String resolveDescription) {
		this.resolveDescription = resolveDescription;
	}
	
	/**
	 * Returns expire date.
	 * 
	 * @return expire date
	 */
	public Date getExpireDate() {
		return this.expireDate;
	}

	/**
	 * Sets the expire date.
	 * 
	 * @param expireDate expire date
	 */
	public void setExpireDate(final Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * Returns the person by whom the alert was resolved.
	 * 
	 * @return person by whom alert was resolved
	 */
	public Person getResolveByPerson() {
		return this.resolveByPerson;
	}

	/**
	 * Sets the person by whom the alert was resolved.
	 * 
	 * @param resolveByPerson person by whom alert was resolved
	 */
	public void setResolveByPerson(final Person resolveByPerson) {
		this.resolveByPerson = resolveByPerson;
	}
}