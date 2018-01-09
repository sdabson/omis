package omis.relationship.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;

/**
 * Implementation of relationship.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 21, 2013)
 * @since OMIS 3.0
 */
public class RelationshipImpl implements Relationship {
	
	private static final long serialVersionUID = 1L;
	
	private CreationSignature creationSignature;
	
	private Long id;
	
	private Person firstPerson;
	
	private Person secondPerson;

	/** Instantiates a default relationship. */
	public RelationshipImpl() {
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
	public void setFirstPerson(final Person firstPerson) {
		this.firstPerson = firstPerson;
	}

	/** {@inheritDoc} */
	@Override
	public Person getFirstPerson() {
		return this.firstPerson;
	}

	/** {@inheritDoc} */
	@Override
	public void setSecondPerson(final Person secondPerson) {
		this.secondPerson = secondPerson;
	}

	/** {@inheritDoc} */
	@Override
	public Person getSecondPerson() {
		return this.secondPerson;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Relationship)) {
			return false;
		}
		Relationship that = (Relationship) obj;
		if (this.getFirstPerson() == null) {
			throw new IllegalStateException("First person required");
		}
		if (!this.getFirstPerson().equals(that.getFirstPerson())) {
			return false;
		}
		if (this.getSecondPerson() == null) {
			throw new IllegalStateException("Second person required");
		}
		if (!this.getSecondPerson().equals(that.getSecondPerson())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getFirstPerson() == null) {
			throw new IllegalStateException("First person required");
		}
		if (this.getSecondPerson() == null) {
			throw new IllegalStateException("Second person required");
		}
		int hashCode = 14;
		hashCode = hashCode * 29 + this.getFirstPerson().hashCode();
		hashCode = hashCode * 29 + this.getSecondPerson().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format("#%d: [%s] [%s]", this.getId(),
				this.getFirstPerson() != null
						? this.getFirstPerson().getName()
							: "no first person name",
				this.getSecondPerson() != null
						? this.getSecondPerson().getName()
							: "no second person name");
	}
}