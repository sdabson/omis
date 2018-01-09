package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;

/**
 * FinancialSectionSummaryDocumentAssociationImpl.java
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 14, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryDocumentAssociationImpl 
	implements FinancialSectionSummaryDocumentAssociation {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Document document;
	
	private FinancialSectionSummary financialSectionSummary;
	
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
	public FinancialSectionSummary getFinancialSectionSummary() {
		return this.financialSectionSummary;
	}

	/**{@inheritDoc} */
	@Override
	public void setFinancialSectionSummary(
			final FinancialSectionSummary financialSectionSummary) {
		this.financialSectionSummary = financialSectionSummary;
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
		if(!(obj instanceof FinancialSectionSummaryDocumentAssociation)){
			return false;
		}
		
		FinancialSectionSummaryDocumentAssociation that =
				(FinancialSectionSummaryDocumentAssociation) obj;
		
		if(this.getFinancialSectionSummary() == null){
			throw new IllegalStateException("FinancialSectionSummary required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException(
					"Document required.");
		}
		
		if(this.getFinancialSectionSummary().equals(
				that.getFinancialSectionSummary())){
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
		if(this.getFinancialSectionSummary() == null){
			throw new IllegalStateException(
					"FinancialSectionSummary required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException(
					"Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getFinancialSectionSummary().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
	
}
