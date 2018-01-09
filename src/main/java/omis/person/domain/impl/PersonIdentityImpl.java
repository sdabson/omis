package omis.person.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.country.domain.Country;
import omis.demographics.domain.Sex;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Implementation of person identity.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Sep 3, 2013)
 * @since OMIS 3.0
 */
public class PersonIdentityImpl
		implements PersonIdentity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;
	
	private Person person;
	
	private Integer socialSecurityNumber;
	
	private String stateIdNumber;
	
	private Long birthDate;
	
	private Country birthCountry;
	
	private State birthState;
	
	private City birthPlace;
	
	private Sex sex;
	
	private Boolean deceased;
	
	private Date deathDate;
	
	/** Instantiates a default person identity. */
	public PersonIdentityImpl() {
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
	public void setSocialSecurityNumber(
			final Integer socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/** {@inheritDoc} */
	@Override
	public Integer getSocialSecurityNumber() {
		return this.socialSecurityNumber;
	}

	/** {@inheritDoc} */
	@Override
	public void setStateIdNumber(final String stateIdNumber) {
		this.stateIdNumber = stateIdNumber;
	}

	/** {@inheritDoc} */
	@Override
	public String getStateIdNumber() {
		return this.stateIdNumber;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setBirthDate(final Date birthDate) {
		if (birthDate != null) {
			this.birthDate = birthDate.getTime();
		} else {
			this.birthDate = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public Date getBirthDate() {
		if (this.birthDate != null) {
			return new Date(this.birthDate);
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setBirthCountry(final Country birthCountry) {
		this.birthCountry = birthCountry;
	}

	/** {@inheritDoc} */
	@Override
	public Country getBirthCountry() {
		return this.birthCountry;
	}

	/** {@inheritDoc} */
	@Override
	public void setBirthState(final State birthState) {
		this.birthState = birthState;
	}

	/** {@inheritDoc} */
	@Override
	public State getBirthState() {
		return this.birthState;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setBirthPlace(final City birthPlace) {
		this.birthPlace = birthPlace;
	}

	/** {@inheritDoc} */
	@Override
	public City getBirthPlace() {
		return this.birthPlace;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSex(final Sex sex) {
		this.sex = sex;
	}
	
	/** {@inheritDoc} */
	@Override
	public Sex getSex() {
		return this.sex;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDeceased(final Boolean deceased) {
		this.deceased = deceased;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getDeceased() {
		return this.deceased;
	}

	/** {@inheritDoc} */
	@Override
	public void setDeathDate(final Date deathDate) {
		this.deathDate = deathDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDeathDate() {
		return this.deathDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PersonIdentity)) {
			return false;
		}
		PersonIdentity that = (PersonIdentity) obj;
		if (this.getSocialSecurityNumber() != null) {
			if (!this.getSocialSecurityNumber()
					.equals(that.getSocialSecurityNumber())) {
				return false;
			}
		} else if (that.getSocialSecurityNumber() != null) {
			return false;
		}
		if (this.getBirthDate() != null) {
			if (!this.getBirthDate().equals(that.getBirthDate())) {
				return false;
			}
		} else if (that.getBirthDate() != null) {
			return false;
		}
		if (this.getBirthCountry() != null) {
			if (!this.getBirthCountry().equals(that.getBirthCountry())) {
				return false;
			}
		} else if (that.getBirthCountry() != null) {
			return false;
		}
		if (this.getBirthPlace() != null) {
			if (!this.getBirthPlace().equals(that.getBirthPlace())) {
				return false;
			}
		} else if (that.getBirthPlace() != null) {
			return false;
		}
		if (this.getPerson() != null) {
			if (!this.getPerson().equals(that.getPerson())) {
				return false;
			}
		} else if (that.getPerson() != null) {
			return false;
		}
		if (this.getSex()!=null&&that.getSex()!=null) {
			if (!this.getSex().equals(that.getSex())) {
				return false;
			}
		} else if ((this.getSex()!=null && that.getSex()==null) 
			|| (this.getSex()==null && that.getSex()!=null)){
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getSocialSecurityNumber() != null) {
			hashCode = 29 * hashCode
					+ this.getSocialSecurityNumber().hashCode();
		}
		if (this.getBirthDate() != null) {
			hashCode = 29 * hashCode + this.getBirthDate().hashCode();
		}
		if (this.getBirthCountry() != null) {
			hashCode = 29 * hashCode + this.getBirthCountry().hashCode();
		}
		if (this.getBirthPlace() != null) {
			hashCode = 29 * hashCode + this.getBirthPlace().hashCode();
		}
		if (this.getPerson() != null) {
			hashCode = 29 * hashCode + this.getPerson().hashCode();
		}
		if (this.getSex() != null) {
			hashCode = 29 * hashCode + this.getSex().hashCode();
		}
		return hashCode;
	}
}