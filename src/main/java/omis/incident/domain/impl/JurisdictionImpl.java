package omis.incident.domain.impl;

import omis.incident.domain.Jurisdiction;
import omis.media.domain.Photo;
import omis.organization.domain.Organization;

/**
 * Jurisdiction implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 3, 2015)
 * @since OMIS 3.0
 */
public class JurisdictionImpl implements Jurisdiction {

	private static final long serialVersionUID = 1L;

	Long id;
	
	Organization organization;
	
	Photo photo;
	
	/** Instantiates a default instance of jurisdiction. */
	public JurisdictionImpl() {
		//Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Organization getOrganization() {
		return this.organization;
	}

	/** {@inheritDoc} */
	@Override
	public void setOrganization(final Organization organization) {
		this.organization = organization;
	}
	
	/** {@inheritDoc} */
	@Override
	public Photo getPhoto() {
		return photo;
	}

	/** {@inheritDoc} */
	@Override
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof Jurisdiction)) {
			return false;
		}
		
		Jurisdiction that = (Jurisdiction) o;
		
		if (this.getOrganization() == null) {
			throw new IllegalStateException("Organization required.");
		}
		if (!this.getOrganization().equals(that.getOrganization())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOrganization() == null) {
			throw new IllegalStateException("Organization required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOrganization().hashCode();
		
		return hashCode;
	}
}