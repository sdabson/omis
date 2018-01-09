package omis.specialneed.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;

/**
 * Implementation of special need associable document.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedAssociableDocumentImpl 
	implements SpecialNeedAssociableDocument {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private SpecialNeed specialNeed;
	
	private SpecialNeedAssociableDocumentCategory category;
	
	private Document document;
	
	/** Instantiates an implementation of special need associable document. */
	public SpecialNeedAssociableDocumentImpl() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setSpecialNeed(final SpecialNeed specialNeed) {
		this.specialNeed = specialNeed;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeed getSpecialNeed() {
		return this.specialNeed;
	}

	/** {@inheritDoc} */
	@Override
	public void setCategory(
			final SpecialNeedAssociableDocumentCategory category) {
		this.category = category;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedAssociableDocumentCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}

	/** {@inheritDoc} */
	@Override
	public Document getDocument() {
		return this.document;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SpecialNeedAssociableDocument)) {
			return false;
		}
		SpecialNeedAssociableDocument that = 
				(SpecialNeedAssociableDocument) obj;
		if (this.getSpecialNeed() == null) {
			throw new IllegalStateException("Special need required");
		}
		if (!this.getSpecialNeed().equals(that.getSpecialNeed())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getSpecialNeed() == null) {
			throw new IllegalStateException("Special need required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getSpecialNeed().hashCode();
		
		return hashCode;
	}
}