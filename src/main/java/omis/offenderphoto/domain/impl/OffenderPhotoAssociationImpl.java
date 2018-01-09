package omis.offenderphoto.domain.impl;

import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.personphoto.domain.impl.AbstractPersonPhotoAssociation;

/**
 * Implementation of offender photo association.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 23, 2014)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationImpl
		extends AbstractPersonPhotoAssociation
		implements OffenderPhotoAssociation {

	private static final long serialVersionUID = 1L;
	
	private Boolean profile;
	
	/** Instantiates an implementation of offender photo association. */
	public OffenderPhotoAssociationImpl() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public void setProfile(final Boolean profile) {
		this.profile = profile;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getProfile() {
		return this.profile;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof OffenderPhotoAssociation)) {
			return false;
		}
		OffenderPhotoAssociation that = (OffenderPhotoAssociation) obj;
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		if (!this.getPerson().equals(that.getPerson())) {
			return false;
		}
		if (this.getPhoto() == null) {
			throw new IllegalStateException("Photo required");
		}
		if (!this.getPhoto().equals(that.getPhoto())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPerson() == null) {
			throw new IllegalStateException("Person required");
		}
		if (this.getPhoto() == null) {
			throw new IllegalStateException("Photo required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getPerson().hashCode();
		hashCode = 29 * hashCode + this.getPhoto().hashCode();
		return hashCode;
	}
}