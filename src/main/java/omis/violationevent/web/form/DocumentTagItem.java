package omis.violationevent.web.form;

import omis.document.domain.DocumentTag;

/**
 * DocumentTagItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 27, 2017)
 *@since OMIS 3.0
 *
 */
public class DocumentTagItem {
	
	private String tag;
	
	private DocumentTag documentTag;
	
	private ViolationEventItemOperation itemOperation;
	
	/**
	 * 
	 */
	public DocumentTagItem() {
	}

	/**
	 * Returns the tag
	 * @return tag - String
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag
	 * @param tag - String
	 */
	public void setTag(final String tag) {
		this.tag = tag;
	}

	/**
	 * Returns the documentTag
	 * @return documentTag - DocumentTag
	 */
	public DocumentTag getDocumentTag() {
		return documentTag;
	}

	/**
	 * Sets the documentTag
	 * @param documentTag - DocumentTag
	 */
	public void setDocumentTag(final DocumentTag documentTag) {
		this.documentTag = documentTag;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - ViolationEventItemOperation
	 */
	public ViolationEventItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - ViolationEventItemOperation
	 */
	public void setItemOperation(final ViolationEventItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
}
