package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;

/**
 * PsychologicalSectionSummaryDocumentImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 1, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryDocumentImpl implements PsychologicalSectionSummaryDocument {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Document document;
	
	private PsychologicalSectionSummary psychologicalSectionSummary;
	
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
	public Document getDocument() {
		return this.document;
	}

	/**{@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**{@inheritDoc} */
	@Override
	public PsychologicalSectionSummary getPsychologicalSectionSummary() {
		return this.psychologicalSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setPsychologicalSectionSummary(
			final PsychologicalSectionSummary psychologicalSectionSummary) {
		this.psychologicalSectionSummary = psychologicalSectionSummary;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PsychologicalSectionSummaryDocument)){
			return false;
		}
		
		PsychologicalSectionSummaryDocument that = (PsychologicalSectionSummaryDocument) obj;
		
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		if(this.getPsychologicalSectionSummary() == null){
			throw new IllegalStateException("Document required.");
		}
		
		if(!this.getDocument().equals(that.getDocument())){
			return false;
		}
		if(!this.getPsychologicalSectionSummary().equals(
				that.getPsychologicalSectionSummary())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		if(this.getPsychologicalSectionSummary() == null){
			throw new IllegalStateException("Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		hashCode = 29 * hashCode +
				this.getPsychologicalSectionSummary().hashCode();
		
		return hashCode;
	}
	
}
