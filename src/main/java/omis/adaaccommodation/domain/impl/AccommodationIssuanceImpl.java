package omis.adaaccommodation.domain.impl;

import java.util.Date;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationIssuance;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;

/**
 * Implementation of ADA accommodation issuance.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 15, 2015)
 * @since OMIS 3.0
 */
public class AccommodationIssuanceImpl 
	implements AccommodationIssuance { 
	
	private static final long serialVersionUID = 1L;
	
	private UpdateSignature updateSignature;
	
	private CreationSignature creationSignature;
	
	private Long id;
	
	private Date timestamp;
	
	private Person issuer;
	
	private String description;
	
	private Accommodation accommodation;

	/** Instantiates an implementation of accommodation issuance. */
	public AccommodationIssuanceImpl() {
		// Default instantiation
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
	public void setTimestamp(final Date timestamp) {
		this.timestamp = timestamp;
	}

	/** {@inheritDoc} */
	@Override
	public Date getTimestamp() {
		return this.timestamp;
	}

	/** {@inheritDoc} */
	@Override
	public Person getIssuer() {
		return this.issuer;
	}

	/** {@inheritDoc} */
	@Override
	public void setIssuer(final Person issuer) {
		this.issuer = issuer;
	}

	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/** {@inheritDoc} */
	@Override
	public void setAccommodation(final Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	/** {@inheritDoc} */
	@Override
	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AccommodationIssuance)) {
			return false;
		}
		AccommodationIssuance that = (AccommodationIssuance) obj;
		if (this.getTimestamp() == null) {
			throw new IllegalStateException("Date required");
		}
		if (!this.getTimestamp().equals(that.getTimestamp())) {
			return false;
		}
		if (this.getIssuer() == null) {
			throw new IllegalStateException("Issuer required");
		}
		if(!this.getIssuer().equals(that.getIssuer())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		if (!this.getAccommodation().equals(that.getAccommodation())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getTimestamp() == null) {
			throw new IllegalStateException("Date required");
		}
		if (this.getIssuer() == null) {
			throw new IllegalStateException("Issuer required");
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required");
		}
		if (this.getAccommodation() == null) {
			throw new IllegalStateException("Accommodation required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTimestamp().hashCode();
		hashCode = 29 * hashCode + this.getIssuer().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getAccommodation().hashCode();
		
		return hashCode;
	}
}