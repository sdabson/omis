package omis.document.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;

/** Implementation of document tag.
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2015)
 * @since OMIS 3.0 */
public class DocumentTagImpl implements DocumentTag {
	private static final long serialVersionUID = 1L;
	private static final int[] HASH_VALUES = {11, 13, 17};
	private static final String NAME_REQUIRED = "Name required.";
	private static final String DOCUMENT_REQUIRED = "Document required.";
	private Long id;
	private String name;
	private Document document;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
//--------------------------------Constructors----------------------------------
	/** Constructor. */
	public DocumentTagImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Document getDocument() {
		return this.document;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() { 
		return this.creationSignature; 
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() { 
		return this.updateSignature; 
	}
//-----------------------------------Setters------------------------------------
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
					final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int index = 0;
		int hash = HASH_VALUES[index];
		hash += HASH_VALUES[index++] * this.getName().hashCode();
		hash += HASH_VALUES[index++] * this.getDocument().hashCode();
		return hash;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		this.checkState();
		boolean result = false;
		if (obj == this) {
			result = true;
		} else if (obj instanceof DocumentTag) {
			DocumentTag that = (DocumentTag) obj;
			if (this.getDocument().equals(that.getDocument())
							&& this.getName().equals(that.getName())) {
				result = true;
			}
		}
		return result;
	}
	
	/* checks state. */
	private void checkState() {
		if (this.getName() == null) {
			throw new IllegalStateException(NAME_REQUIRED);
		}
		
		if (this.getDocument() == null) {
			throw new IllegalStateException(DOCUMENT_REQUIRED);
		}
	}
}
