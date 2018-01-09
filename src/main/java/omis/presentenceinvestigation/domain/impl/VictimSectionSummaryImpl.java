package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;

/**
 * VictimSectionSummaryImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryImpl implements VictimSectionSummary {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	
	private String text;
	
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
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof VictimSectionSummary)){
			return false;
		}
		
		VictimSectionSummary that = (VictimSectionSummary) obj;
		
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationRequest required.");
		}
		if(this.getText() == null){
			throw new IllegalStateException(
					"Text required.");
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
		if(this.getText() == null){
			throw new IllegalStateException(
					"Text required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode +
				this.getPresentenceInvestigationRequest().hashCode();
		
		return hashCode;
	}
	
}
