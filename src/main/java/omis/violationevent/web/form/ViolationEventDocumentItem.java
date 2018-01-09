package omis.violationevent.web.form;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.violationevent.domain.ViolationEventDocument;
import omis.document.web.form.DocumentTagItem;

/**
 * ViolationEventDocumentItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventDocumentItem{
	
	private ViolationEventDocument violationEventDocument;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> tagItems;
	
	private Date date;
	
	private String fileExtension;
	
	private ViolationEventItemOperation itemOperation;
	
	private byte[] data;
	
	/**
	 * 
	 */
	public ViolationEventDocumentItem() {
	}

	/**
	 * Returns the violationEventDocument
	 * @return violationEventDocument - ViolationEventDocument
	 */
	public ViolationEventDocument getViolationEventDocument() {
		return this.violationEventDocument;
	}

	/**
	 * Sets the violationEventDocument
	 * @param violationEventDocument - ViolationEventDocument
	 */
	public void setViolationEventDocument(
			final ViolationEventDocument violationEventDocument) {
		this.violationEventDocument = violationEventDocument;
	}

	/**
	 * Returns the document
	 * @return document - Document
	 */
	public Document getDocument() {
		return this.document;
	}

	/**
	 * Sets the document
	 * @param document - Document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Returns the title
	 * @return title - String
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title
	 * @param title - String
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the tagItems
	 * @return tagItems - List<DocumentTagItem>
	 */
	public List<DocumentTagItem> getTagItems() {
		return this.tagItems;
	}

	/**
	 * Sets the tagItems
	 * @param tagItems - List<DocumentTagItem>
	 */
	public void setTagItems(final List<DocumentTagItem> tagItems) {
		this.tagItems = tagItems;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the fileExtension
	 * @return fileExtension - String
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * Sets the fileExtension
	 * @param fileExtension - String
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
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

	/**
	 * Returns the data
	 * @return data - byte[]
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data
	 * @param data - byte[]
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	
	
	
	
}
