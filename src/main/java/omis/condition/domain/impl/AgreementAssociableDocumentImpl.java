package omis.condition.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.document.domain.Document;

/**
 * Agreement Associable Document Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 27, 2017)
 *@since OMIS 3.0
 *
 */
public class AgreementAssociableDocumentImpl
		implements AgreementAssociableDocument {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Agreement agreement;
	
	private Document document;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
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
	public Agreement getAgreement() {
		return this.agreement;
	}

	/**{@inheritDoc} */
	@Override
	public void setAgreement(final Agreement agreement) {
		this.agreement = agreement;
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
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AgreementAssociableDocument)) {
			return false;
		}
		
		AgreementAssociableDocument that = (AgreementAssociableDocument) obj;
		
		if (this.getAgreement() == null) {
			throw new IllegalStateException("Agreement required.");
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		
		if (!this.getAgreement().equals(that.getAgreement())) {
			return false;
		}
		if (!this.getDocument().equals(that.getDocument())) {
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAgreement() == null) {
			throw new IllegalStateException("Agreement required.");
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getAgreement().hashCode();
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		
		return hashCode;
	}
}
