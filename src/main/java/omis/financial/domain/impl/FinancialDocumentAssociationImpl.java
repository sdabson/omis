package omis.financial.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.financial.domain.FinancialDocumentAssociation;
import omis.offender.domain.Offender;

/**
 * FinancialAssociableDocumentImpl
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialDocumentAssociationImpl 
		implements FinancialDocumentAssociation {
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Offender offender;
	
	private Document document;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
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
	public Offender getOffender() {
		return this.offender;
	}

	/**{@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
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
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof FinancialDocumentAssociation)){
			return false;
		}
		
		FinancialDocumentAssociation that = (FinancialDocumentAssociation) obj;
		
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		
		if(!this.getOffender().equals(that.getOffender())){
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
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}

}
