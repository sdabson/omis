package omis.health.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;
import omis.health.domain.ProviderLevel;
import omis.offender.domain.Offender;

/**
 * Health request implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Mar 31, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestImpl implements HealthRequest {

	private static final long serialVersionUID = 1L;

	private Long id;
		
	private Boolean labsRequired;
	
	private Offender offender;
	
	private Date date;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private HealthRequestCategory category;

	private HealthRequestStatus status;

	private Facility facility;

	private Boolean asap;
	
	private ProviderLevel providerLevel;
	
	private String notes;
	
	/**
	 * Instantiates a default health request implementation.
	 */
	public HealthRequestImpl() {
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
	public void setStatus(final HealthRequestStatus status) {
		this.status = status;
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequestStatus getStatus() {
		return this.status;
	}

	/** {@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/** {@inheritDoc} */
	@Override
	public Facility getFacility() {
		return this.facility;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getLabsRequired() {
		return this.labsRequired;
	}

	/** {@inheritDoc} */
	@Override
	public void setLabsRequired(final Boolean labsRequired) {
		this.labsRequired = labsRequired;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthRequestCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(final HealthRequestCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getAsap() {
		return this.asap;
	}

	/** {@inheritDoc} */
	@Override
	public void setAsap(final Boolean asap) {
		this.asap = asap;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProviderLevel getProviderLevel() {
		return this.providerLevel;
	}

	/** {@inheritDoc} */
	@Override
	public void setProviderLevel(final ProviderLevel providerLevel) {
		this.providerLevel = providerLevel;
	}

	/** {@inheritDoc} */
	@Override
	public String getNotes() {
		return this.notes;
	}

	/** {@inheritDoc} */
	@Override
	public void setNotes(final String notes) {
		this.notes = notes;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof HealthRequest)) {
			return false;
		}
		
		HealthRequest that = (HealthRequest) o;
		
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required.");
		}
		if (!this.getFacility().equals(that.getFacility())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
			return false;
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Health request category" 
					+ " required.");
		}
		if (!this.getCategory().equals(that
				.getCategory())) {
			return false;
		}
		if (this.getStatus() == null) {
			throw new IllegalStateException("Status required");
		}
		if (!this.getStatus().equals(that.getStatus())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required.");
		}
		if (this.getFacility() == null) {
			throw new IllegalStateException("Facility required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (this.getCategory() == null) {
			throw new IllegalStateException("Health request category" 
					+ " required.");
		}
		if (this.getStatus() == null) {
			throw new IllegalStateException("Status required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getFacility().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		hashCode = 29 * hashCode + this.getStatus().hashCode();
		
		return hashCode;
	}
}