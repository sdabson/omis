package omis.violationevent.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventDocument;

/**
 * ViolationEventDocument.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventDocumentImpl implements ViolationEventDocument {

	private static final long serialVersionUID = 1L;
	
	private Document document;
	
	private ViolationEvent violationEvent;

	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Default Constructor for ViolationEventDocumentImpl
	 */
	public ViolationEventDocumentImpl() {
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
	public void setDocument(Document document) {
		this.document = document;
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEvent getViolationEvent() {
		return this.violationEvent;
	}

	/**{@inheritDoc} */
	@Override
	public void setViolationEvent(ViolationEvent violationEvent) {
		this.violationEvent = violationEvent;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof ViolationEventDocument)){
			return false;
		}
		
		ViolationEventDocument that = (ViolationEventDocument) obj;
		
		if(this.getDocument() == null){
			throw new IllegalStateException("Document required.");
		}
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("ViolationEvent required.");
		}
		
		if(!this.getDocument().equals(that.getDocument())){
			return false;
		}
		if(!this.getViolationEvent().equals(that.getViolationEvent())){
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
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("ViolationEvent required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDocument().hashCode();
		hashCode = 29 * hashCode + this.getViolationEvent().hashCode();
		
		return hashCode;
	}
	
}
