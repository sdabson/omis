package omis.document.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.domain.Document;

/** Implementation of document.
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2015)
 * @since OMIS 3.0 */
public class DocumentImpl implements Document { 
	private static final long serialVersionUID = 1L;
	private static final int[] HASH_VALUES = {13, 17};
	private static final String FILE_NAME_REQUIRED = "File name required.";
	private Long id;
	private String title;
	private Date date;
	private String filename;
	private String fileExtension;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
//--------------------------------Constructors----------------------------------
	/** Constructor. */
	public DocumentImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getTitle() {
		return this.title;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getFilename() {
		return this.filename; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getFileExtension() {
		return this.fileExtension; 
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
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFilename(final String filename) { 
		this.filename = filename;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) { 
		this.date = date;
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
		hash += this.getFilename().hashCode() * HASH_VALUES[index++];
		return hash;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (obj == this) {
			result = true;
		} else if (obj instanceof Document) {
			Document that = (Document) obj;
			if (this.getFilename().equals(that.getFilename())) {
				result = true;
			}
		}
		return result;
	}
		
	
	/* Checks state. */
	private void checkState() {
		if (this.getFilename() == null) {
			throw new IllegalStateException(FILE_NAME_REQUIRED);
		}
	}
}
