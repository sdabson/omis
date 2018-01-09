package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;

/**
 * PsychologicalSectionSummaryImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryImpl implements PsychologicalSectionSummary {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest() {
		return this.presentenceInvestigationRequest;
	}

	/**{@inheritDoc} */
	@Override
	public void setPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		this.presentenceInvestigationRequest = presentenceInvestigationRequest;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PsychologicalSectionSummary)){
			return false;
		}
		
		PsychologicalSectionSummary that = (PsychologicalSectionSummary) obj;
		
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationRequest required.");
		}
		
		if(!this.getPresentenceInvestigationRequest().equals(
				that.getPresentenceInvestigationRequest())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationRequest required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode +
				this.getPresentenceInvestigationRequest().hashCode();
		
		return hashCode;
	}

}
