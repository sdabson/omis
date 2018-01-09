package omis.incident.web.form;

import java.io.Serializable;

import omis.incident.domain.InvolvedPartyCategory;
import omis.person.domain.Person;

/**
 * Involved person item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 13, 2015)
 * @since OMIS 3.0
 */
public class InvolvedPersonItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Person person;
	
	private InvolvedPartyCategory category;
	
	/**
	 * Instantiates a default instance of involved person item.
	 */
	public InvolvedPersonItem() {
		//Default constructor.
	}

	/**
	 * Returns the person.
	 * 
	 * @return person
	 */
	public Person getPerson() {
		return this.person;
	}

	/**
	 * Sets the person.
	 * 
	 * @param person person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**
	 * Returns the involved party category.
	 * 
	 * @return involved party category
	 */
	public InvolvedPartyCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the involved party category.
	 * 
	 * @param category involved party category
	 */
	public void setCategory(final InvolvedPartyCategory category) {
		this.category = category;
	}
}