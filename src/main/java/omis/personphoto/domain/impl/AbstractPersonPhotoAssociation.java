package omis.personphoto.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.media.domain.Photo;
import omis.person.domain.Person;
import omis.personphoto.domain.PersonPhotoAssociation;

/**
 * Abstract implementation of person photo association.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 8, 2013)
 * @since OMIS 3.0
 */
public abstract class AbstractPersonPhotoAssociation
		implements PersonPhotoAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Person person;

	private Photo photo;

	/** Instantiates an abstract implementation of person photo association. */
	public AbstractPersonPhotoAssociation() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(
			final Long id) {
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
	public void setPhoto(final Photo photo) {
		this.photo = photo;
	}

	/** {@inheritDoc} */
	@Override
	public Photo getPhoto() {
		return this.photo;
	}
	
	/** {@inheritDoc} */
	@Override
	public abstract boolean equals(final Object obj);
	
	/** {@inheritDoc} */
	@Override
	public abstract int hashCode();
}