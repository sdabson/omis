package omis.warrant.domain.component;

import java.util.Date;

import omis.person.domain.Person;

/**
 * ClearSignature.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class ClearSignature {
	
	private Person person;
	
	private Date date;
	
	/**
	 * 
	 */
	public ClearSignature() {
	}

	/**
	 * @param person
	 * @param date
	 */
	public ClearSignature(final Person person, final Date date) {
		this.person = person;
		this.date = date;
	}

	/**
	 * Returns the person
	 * @return person - Person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Sets the person
	 * @param person - Person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
}
