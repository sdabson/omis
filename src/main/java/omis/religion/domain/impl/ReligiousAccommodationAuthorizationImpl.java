package omis.religion.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;

/**
 * Implementation of religious accommodation authorization.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 22, 2014)
 * @since OMIS 3.0
 */
public class ReligiousAccommodationAuthorizationImpl
		implements ReligiousAccommodationAuthorization {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private ReligiousPreference preference;
	
	private ReligiousAccommodation accommodation;
	
	/**
	 * Instantiates an implementation of religious accommodation authorization.
	 */
	public ReligiousAccommodationAuthorizationImpl() {
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
	public void setPreference(
			final ReligiousPreference preference) {
		this.preference = preference;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousPreference getPreference() {
		return this.preference;
	}

	/** {@inheritDoc} */
	@Override
	public void setAccommodation(
			final ReligiousAccommodation accommodation) {
		this.accommodation = accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousAccommodation getAccommodation() {
		return this.accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ReligiousAccommodationAuthorization)) {
			return false;
		}
		ReligiousAccommodationAuthorization that
			= (ReligiousAccommodationAuthorization) obj;
		if (this.getPreference() == null) {
			throw new IllegalStateException("Preference required");
		}
		if (!this.getPreference().equals(that.getPreference())) {
			return false;
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		if (!this.getPreference().equals(that.getPreference())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPreference() == null) {
			throw new IllegalStateException("Preference required");
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getPreference().hashCode();
		hashCode = 29 * hashCode + this.getAccommodation().hashCode();
		return hashCode;
	}
}