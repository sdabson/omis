package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryDocumentAssociation;

/**
 * VictimSectionSummaryDocumentAssociationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryDocumentAssociationImpl
	implements VictimSectionSummaryDocumentAssociation {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Document document;
	
	private VictimSectionSummary victimSectionSummary;
	
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
	public VictimSectionSummary getVictimSectionSummary() {
		return this.victimSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setVictimSectionSummary(
			final VictimSectionSummary victimSectionSummary) {
		this.victimSectionSummary = victimSectionSummary;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof VictimSectionSummaryDocumentAssociation)){
			return false;
		}
		
		VictimSectionSummaryDocumentAssociation that =
				(VictimSectionSummaryDocumentAssociation) obj;
		
		if(this.getVictimSectionSummary() == null){
			throw new IllegalStateException("VictimSectionSummary required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		
		if(!this.getVictimSectionSummary().equals(that.getVictimSectionSummary())){
			return false;
		}
		if(!this.getDocument().equals(that.getDocument())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getVictimSectionSummary() == null){
			throw new IllegalStateException("VictimSectionSummary required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getVictimSectionSummary().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
	
}
