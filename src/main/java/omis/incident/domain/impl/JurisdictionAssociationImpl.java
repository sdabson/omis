package omis.incident.domain.impl;

import omis.incident.domain.Jurisdiction;
import omis.incident.domain.JurisdictionAssociation;
import omis.person.domain.Person;

/**
 * Jurisdiction association implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0
 */
public class JurisdictionAssociationImpl implements JurisdictionAssociation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Jurisdiction jurisdiction;
	
	private Person person;
	
	/**
	 * Instantiates a default instance of jurisdiction association.
	 */
	public JurisdictionAssociationImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	/** {@inheritDoc} */
	@Override
	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/** {@inheritDoc} */
	@Override
	public Person getPerson() {
		return person;
	}

	/** {@inheritDoc} */
	@Override
	public void setPerson(Person person) {
		this.person = person;
	}
}