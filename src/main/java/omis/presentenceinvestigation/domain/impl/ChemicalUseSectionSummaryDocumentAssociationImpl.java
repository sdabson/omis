package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;

/**
 * ChemicalUseSectionSummaryDocumentAssociationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryDocumentAssociationImpl
	implements ChemicalUseSectionSummaryDocumentAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Document document;
	
	private ChemicalUseSectionSummary chemicalUseSectionSummary;
	
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
	public ChemicalUseSectionSummary getChemicalUseSectionSummary() {
		return this.chemicalUseSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary) {
		this.chemicalUseSectionSummary = chemicalUseSectionSummary;
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
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof ChemicalUseSectionSummaryDocumentAssociation)){
			return false;
		}
		
		ChemicalUseSectionSummaryDocumentAssociation that =
				(ChemicalUseSectionSummaryDocumentAssociation) obj;
		
		if(this.getChemicalUseSectionSummary() == null){
			throw new IllegalStateException("ChemicalUseSectionSummary required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException(
					"Document required.");
		}
		
		if(!this.getChemicalUseSectionSummary().equals(
				that.getChemicalUseSectionSummary())){
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
		if(this.getChemicalUseSectionSummary() == null){
			throw new IllegalStateException(
					"ChemicalUseSectionSummary required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException(
					"Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getChemicalUseSectionSummary().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
	
}
