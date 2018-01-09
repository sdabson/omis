package omis.person.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;
import omis.person.domain.PersonName;

/**
 * Implementation of person name.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 7, 2012)
 * @since OMIS 3.0
 */
public class PersonNameImpl
		implements PersonName {

	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Long id;

	private Person person;

	private String lastName;

	private String firstName;

	private String middleName;

	private String suffix;
	
	/** Instantiates a default implementation of person name. */
	public PersonNameImpl() {
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
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/** {@inheritDoc} */
	@Override
	public String getLastName() {
		return this.lastName;
	}

	/** {@inheritDoc} */
	@Override
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/** {@inheritDoc} */
	@Override
	public String getFirstName() {
		return this.firstName;
	}

	/** {@inheritDoc} */
	@Override
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/** {@inheritDoc} */
	@Override
	public String getMiddleName() {
		return this.middleName;
	}

	/** {@inheritDoc} */
	@Override
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	/** {@inheritDoc} */
	@Override
	public String getSuffix() {
		return this.suffix;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PersonName)) {
			return false;
		}
		PersonName that = (PersonName) obj;
		if (this.getLastName() == null) {
			throw new IllegalStateException("Last name required");
		}
		if (!this.getLastName().equals(that.getLastName())) {
			return false;
		}
		if (this.getFirstName() == null) {
			throw new IllegalStateException("First name required");
		}
		if (!this.getFirstName().equals(that.getFirstName())) {
			return false;
		}
		if (this.getMiddleName() != null) {
			if (!this.getMiddleName().equals(that.getMiddleName())) {
				return false;
			}
		} else if (that.getMiddleName() != null) {
			return false;
		}
		if (this.getSuffix() != null) {
			if (!this.getSuffix().equals(that.getSuffix())) {
				return false;
			}
		} else if (that.getSuffix() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getLastName() == null) {
			throw new IllegalStateException("Last name required");
		}
		if (this.getFirstName() == null) {
			throw new IllegalStateException("First name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getLastName().hashCode();
		hashCode = 31 * hashCode + this.getFirstName().hashCode();
		if (this.getMiddleName() != null) {
			hashCode = 33 * hashCode + this.getMiddleName().hashCode();
		}
		if (this.getSuffix() != null) {
			hashCode = 35 * hashCode + this.getSuffix().hashCode();
		}
		return hashCode;
	}
}