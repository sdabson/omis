package omis.courtdocument.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.courtdocument.domain.CourtDocumentCategory;

/** Implementation of court document category.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 9, 2015)
 * @since OMIS 3.0 */
public class CourtDocumentCategoryImpl 
		implements CourtDocumentCategory {
	private static final long serialVersionUID = 1L;
	private static final int[] HASHS = {3};
	private static final String NAME_REQUIRED = "Name required.";
	private Long id;
	private String name;
	private Boolean valid;
	private CreationSignature creationSignature;
//--------------------------------Constructors----------------------------------
	/** Constructor. */
	public CourtDocumentCategoryImpl() { /* Do nothing. */ }
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
	public Boolean getValid() {
		return this.valid; 
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature; 
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
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
//------------------------------------------------------------------------------
	 /** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		this.checkState();
		final boolean result;
		
		if (this == obj) {
			result = true;
		} else if (obj instanceof CourtDocumentCategory) {
			CourtDocumentCategory that = (CourtDocumentCategory) obj;
			if (this.getName().equals(that.getName())) {
				result = true;
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		return HASHS[0] * this.getName().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getName() == null) {
			throw new IllegalStateException(NAME_REQUIRED);
		}
	}
}
