package omis.document.web.form;

import java.io.Serializable;

import omis.document.domain.DocumentTag;

/** Document tag form.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 23, 2015)
 * @since OMIS 3.0 */
public class DocumentTagItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private DocumentTagOperation documentTagOperation;
	private DocumentTag documentTag;
	
	/** Constructor. */
	public DocumentTagItem() { }
	
	/**
	 * Instantiates an instance of document tag item.
	 * 
	 * @param name name
	 * @param documentTagOperation document tag operation
	 * @param documentTag document tag
	 */
	public DocumentTagItem(final String name, final
			DocumentTagOperation documentTagOperation,
			final DocumentTag documentTag) {
		this.name = name;
		this.documentTagOperation = documentTagOperation;
		this.documentTag = documentTag;
	}
	
	/** Gets name.
	 * @return name. */
	public String getName() { 
		return this.name; 
	}
	
	/** Gets document tag operation.
	 * @return document tag operation. */
	public DocumentTagOperation getDocumentTagOperation() {
		return this.documentTagOperation;
	}
	
	/** Gets document tag.
	 * @return document tag. */
	public DocumentTag getDocumentTag() {
		return this.documentTag;
	}
	
	/** Sets name.
	 * @param name - name. */
	public void setName(final String name) {
		this.name = name;
	}
	
	/** Sets document tag operation.
	 * @param documentTagOperation - document tag operation. */
	public void setDocumentTagOperation(
			final DocumentTagOperation documentTagOperation) {
		this.documentTagOperation = documentTagOperation;
	}
	
	/** Sets document tag.
	 * @param documentTag - document tag. */
	public void setDocumentTag(
			final DocumentTag documentTag) {
		this.documentTag = documentTag;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}	
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		final boolean result;
		
		if (obj == this) {
			result = true;
		} else if (obj instanceof DocumentTagItem) {
			final DocumentTagItem that = (DocumentTagItem) obj;
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
}
