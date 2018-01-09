package omis.incident.domain;

import omis.person.domain.Person;

/**
 * Information source.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 10, 2015)
 * @since OMIS 3.0
 */
public class InformationSource {

	private Person informant;
	
	private InformationSourceCategory category;
	
	private String name;
	
	/**
	 * Instantiates a default instance of information source.
	 */
	public InformationSource() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of information source with specified informant
	 * and category.
	 * 
	 * @param informant person
	 * @param category information source category
	 */
	public InformationSource(final Person informant,
			final InformationSourceCategory category, final String name) {
		this.informant = informant;
		this.category = category;
		this.name = name;
	}

	/**
	 * Returns the informant.
	 * 
	 * @return informant
	 */
	public Person getInformant() {
		return this.informant;
	}

	/**
	 * Sets the informant.
	 * 
	 * @param informant person
	 */
	public void setInformant(final Person informant) {
		this.informant = informant;
	}

	/**
	 * Returns the information source category.
	 * 
	 * @return information source category
	 */
	public InformationSourceCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the information source category.
	 * 
	 * @param category information source category
	 */
	public void setCategory(final InformationSourceCategory category) {
		this.category = category;
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
	public void setName(String name) {
		this.name = name;
	}
}