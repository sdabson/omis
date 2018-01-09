package omis.detainernotification.web.form;

import omis.document.domain.DocumentTag;

/**
 * DocumentTagItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class DocumentTagItem {
	
	private DocumentTag documentTag;
	
	private String name;
	
	private DetainerNotificationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public DocumentTagItem() {
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
	 * Returns the name
	 * @return name - String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name - String
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - PresentenceInvestigationItemOperation
	 */
	public DetainerNotificationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - PresentenceInvestigationItemOperation
	 */
	public void setItemOperation(
			final DetainerNotificationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
	
}
