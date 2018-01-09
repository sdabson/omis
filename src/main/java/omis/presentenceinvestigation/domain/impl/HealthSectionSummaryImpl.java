package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Health section summary implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 3, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryImpl implements HealthSectionSummary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private HealthRating rating;

	/** Instantiates an implementation of HealthSectionSummaryImpl */
	public HealthSectionSummaryImpl() {
		// Default constructor.
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
	public PresentenceInvestigationRequest 
	getPresentenceInvestigationRequest() {
		return this.presentenceInvestigationRequest;
	}

	/** {@inheritDoc} */
	@Override
	public void setPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest
			presentenceInvestigationRequest) {
		this.presentenceInvestigationRequest = presentenceInvestigationRequest;
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthRating getRating() {
		return this.rating;
	}

	/** {@inheritDoc} */
	@Override
	public void setRating(final HealthRating rating) {
		this.rating = rating;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof HealthSectionSummary)) {
			return false;
		}
		
		HealthSectionSummary that = (HealthSectionSummary) obj;
		
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException("Presentence investigation request"
					+ " required.");
		}
		if (!this.getPresentenceInvestigationRequest().equals(
				that.getPresentenceInvestigationRequest())) {
			return false;
		}
		if (this.getRating() == null) {
			throw new IllegalStateException("Health rating required.");
		}
		if (!this.getRating().equals(that.getRating())) {
			return false;
		}
		return true;		
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException("Presentence investigation request "
					+ "required");
		}
		if (this.getRating() == null) {
			throw new IllegalStateException("Health rating required.");
		}
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getPresentenceInvestigationRequest()
			.hashCode();
		hashCode = 29 * hashCode + this.getRating().hashCode();
		
		return hashCode;
	}	
}