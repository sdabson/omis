package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;

/**
 * Supervision history section summary implementation. 
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public class SupervisionHistorySectionSummaryImpl 
	implements SupervisionHistorySectionSummary {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String text;
	
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/** Instantiates an implementation of 
	 * SupervisionHistorySectionSummaryImpl */
	public SupervisionHistorySectionSummaryImpl() {
		// Default constructor.
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
	public String getText() {
		return this.text;
	}

	/** {@inheritDoc} */
	@Override
	public void setText(final String text) {
		this.text = text;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SupervisionHistorySectionSummary)) {
			return false;
		}
		
		SupervisionHistorySectionSummary that 
			= (SupervisionHistorySectionSummary) obj;
		
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException("Presentence investigation request "
					+ "required.");
		}
		if (!this.getPresentenceInvestigationRequest().equals(
				that.getPresentenceInvestigationRequest())) {
			return false;
		}
		
		if (this.getText() == null) {
			throw new IllegalStateException("Supervision history section "
					+ "summary required.");
		}
		if (!this.getText().equals(that.getText())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException("Presentence investigation request "
					+ "required."); 
		}
		if (this.getText() == null) {
			throw new IllegalStateException("Supervision history section "
					+ "summary required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getPresentenceInvestigationRequest()
			.hashCode();
		hashCode = 29 * hashCode + this.getText().hashCode();
		
		return hashCode;
	}
}