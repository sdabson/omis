package omis.person.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;
import omis.person.domain.PersonName;

/**
 * Implementation of person.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Aug 30, 2013)
 * @since OMIS 3.0
 */
public class PersonImpl implements Person {
	
	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Long id;

	private PersonName name;

	private PersonIdentity identity;
	
	/** Instantiates a default person. */
	public PersonImpl() {
		// Default instantiation
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
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
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
	public void setName(final PersonName name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public PersonName getName() {
		return this.name;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setIdentity(final PersonIdentity identity) {
		this.identity = identity;
	}

	/** {@inheritDoc} */
	@Override
	public PersonIdentity getIdentity() {
		return this.identity;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Person)) {
			return false;
		}
		Person that = (Person) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		if (this.getIdentity() != null && that.getIdentity() !=null) {
			if (this.getIdentity().getSocialSecurityNumber() != null) {
				if (!this.getIdentity().getSocialSecurityNumber()
						.equals(that.getIdentity().getSocialSecurityNumber())) {
					return false;
				}
			} else if (that.getIdentity().getSocialSecurityNumber() != null) {
				return false;
			}
			if (this.getIdentity().getBirthDate() != null) {
				if (!this.getIdentity().getBirthDate().equals(
					that.getIdentity().getBirthDate())) {
					return false;
				}
			} else if (that.getIdentity().getBirthDate() != null) {
				return false;
			}
			if (this.getIdentity().getBirthCountry() != null) {
				if (!this.getIdentity().getBirthCountry().equals(that
					.getIdentity().getBirthCountry())) {
					return false;
				}
			} else if (that.getIdentity().getBirthCountry() != null) {
				return false;
			}
			if (this.getIdentity().getBirthPlace() != null) {
				if (!this.getIdentity().getBirthPlace().equals(
					that.getIdentity().getBirthPlace())) {
					return false;
				}
			} else if (that.getIdentity().getBirthPlace() != null) {
				return false;
			}
			if (this.getIdentity().getSex()!=null&&that.getIdentity().getSex()
				!=null) {
				if (!this.getIdentity().getSex().equals(that.getIdentity()
					.getSex())) {
					return false;
				}
			} else if ((this.getIdentity().getSex()!=null && 
				that.getIdentity().getSex()==null) 
				|| (this.getIdentity().getSex()==null && that.getIdentity()
				.getSex()!=null)){
				return false;
			}
		} else if (that.getIdentity() != null && this.getIdentity()==null) {
			return false;
		} else if (that.getIdentity() == null && this.getIdentity()!=null) {
			return false;
		}
				
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		if (this.getIdentity() != null) {
			if (this.getIdentity().getSocialSecurityNumber() != null) {
				hashCode = 29 * hashCode
						+ this.getIdentity().getSocialSecurityNumber().hashCode();
			}
			if (this.getIdentity().getBirthDate() != null) {
				hashCode = 29 * hashCode + this.getIdentity().getBirthDate()
				.hashCode();
			}
			if (this.getIdentity().getBirthCountry() != null) {
				hashCode = 29 * hashCode + this.getIdentity().getBirthCountry()
				.hashCode();
			}
			if (this.getIdentity().getBirthPlace() != null) {
				hashCode = 29 * hashCode + this.getIdentity().getBirthPlace()
				.hashCode();
			}
			if (this.getIdentity().getSex() != null) {
				hashCode = 29 * hashCode + this.getIdentity().getSex().hashCode();
			}
		}
		return hashCode;
	}
}