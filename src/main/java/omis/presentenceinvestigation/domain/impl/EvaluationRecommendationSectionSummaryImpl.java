package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/**
 * Evaluation Recommendation Section Summary Impl
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
public class EvaluationRecommendationSectionSummaryImpl
		implements EvaluationRecommendationSectionSummary {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String text;
	
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
	public String getText() {
		return this.text;
	}

	/**{@inheritDoc} */
	@Override
	public void setText(final String text) {
		this.text = text;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationRequest
		getPresentenceInvestigationRequest() {
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
		if(!(obj instanceof EvaluationRecommendationSectionSummary)){
			return false;
		}
		
		EvaluationRecommendationSectionSummary that =
				(EvaluationRecommendationSectionSummary) obj;
		
		if(this.getText() == null){
			throw new IllegalStateException("Text required.");
		}
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"Presentence Investigation Request required.");
		}
		
		if(!this.getText().equals(that.getText())){
			return false;
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
		if(this.getText() == null){
			throw new IllegalStateException("Text required.");
		}
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"Presentence Investigation Request required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getText().hashCode();
		hashCode = 29 * hashCode +
				this.getPresentenceInvestigationRequest().hashCode();
		
		return hashCode;
	}
	
}
