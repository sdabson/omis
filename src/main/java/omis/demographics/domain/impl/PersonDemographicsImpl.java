package omis.demographics.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.demographics.domain.DominantSide;
import omis.demographics.domain.MaritalStatus;
import omis.demographics.domain.PersonDemographics;
import omis.demographics.domain.Race;
import omis.demographics.domain.Tribe;
import omis.demographics.domain.component.PersonAppearance;
import omis.demographics.domain.component.PersonPhysique;
import omis.person.domain.Person;

/**
 * Implementation of person demographics.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0
 */
public class PersonDemographicsImpl
		implements PersonDemographics {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Person person;
	
	private PersonAppearance appearance;
	
	private Race race;
	
	private Boolean hispanicEthnicity;
	
	private PersonPhysique physique;
	
	private DominantSide dominantSide;
	
	private MaritalStatus maritalStatus;
	
	private Tribe tribe;
	
	/** Instantiates an implementation of person demographics. */
	public PersonDemographicsImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setPerson(final Person person) {
		this.person = person;
	}

	/** {@inheritDoc} */
	@Override
	public Person getPerson() {
		return this.person;
	}

	/** {@inheritDoc} */
	@Override
	public void setAppearance(final PersonAppearance appearance) {
		this.appearance = appearance;
	}

	/** {@inheritDoc} */
	@Override
	public PersonAppearance getAppearance() {
		return this.appearance;
	}

	/** {@inheritDoc} */
	@Override
	public void setRace(final Race race) {
		this.race = race;
	}

	/** {@inheritDoc} */
	@Override
	public Race getRace() {
		return this.race;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setHispanicEthnicity(final Boolean hispanicEthnicity) {
		this.hispanicEthnicity = hispanicEthnicity;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getHispanicEthnicity() {
		return this.hispanicEthnicity;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPhysique(final PersonPhysique physique) {
		this.physique = physique;
	}

	/** {@inheritDoc} */
	@Override
	public PersonPhysique getPhysique() {
		return this.physique;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDominantSide(final DominantSide dominantSide) {
		this.dominantSide = dominantSide;
	}

	/** {@inheritDoc} */
	@Override
	public DominantSide getDominantSide() {
		return this.dominantSide;
	}

	/** {@inheritDoc} */
	@Override
	public void setMaritalStatus(final MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public MaritalStatus getMaritalStatus() {
		return this.maritalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setTribe(final Tribe tribe) {
		this.tribe = tribe;
	}

	/** {@inheritDoc} */
	@Override
	public Tribe getTribe() {
		return this.tribe;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PersonDemographics)) {
			return false;
		}
		PersonDemographics that = (PersonDemographics) obj;
		if (this.getPerson() == null) {
			throw new IllegalArgumentException("Person required");
		}
		if (!this.getPerson().equals(that.getPerson())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPerson() == null) {
			throw new IllegalArgumentException("Person required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getPerson().hashCode();
		return hashCode;
	}
}