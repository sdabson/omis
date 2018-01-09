package omis.courtcasecondition.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.condition.domain.AgreementAssociableDocument;
import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;

/**
 * Agreement Associable Document Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 30, 2017)
 *@since OMIS 3.0
 *
 */
public class AgreementAssociableDocumentItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private AgreementAssociableDocument agreementAssociableDocument;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	private CourtCaseConditionItemOperation itemOperation;

	/**
	 * Returns the agreementAssociableDocument.
	 * @return agreementAssociableDocument - AgreementAssociableDocument
	 */
	public AgreementAssociableDocument getAgreementAssociableDocument() {
		return agreementAssociableDocument;
	}

	/**
	 * Sets the agreementAssociableDocument.
	 * @param agreementAssociableDocument - AgreementAssociableDocument
	 */
	public void setAgreementAssociableDocument(
			final AgreementAssociableDocument agreementAssociableDocument) {
		this.agreementAssociableDocument = agreementAssociableDocument;
	}

	/**
	 * Returns the document.
	 * @return document - Document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Sets the document.
	 * @param document - Document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Returns the title.
	 * @return title - String
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * @param title - String
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the tagItems.
	 * @return tagItems - List<DocumentTagItem>
	 */
	public List<DocumentTagItem> getTagItems() {
		return tagItems;
	}

	/**
	 * Sets the tagItems.
	 * @param tagItems - List<DocumentTagItem>
	 */
	public void setTagItems(final List<DocumentTagItem> tagItems) {
		this.tagItems = tagItems;
	}

	/**
	 * Returns the date.
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the fileExtension.
	 * @return fileExtension - String
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Sets the fileExtension.
	 * @param fileExtension - String
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Returns the data.
	 * @return data - byte[]
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * @param data - byte[]
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	/**
	 * Returns the itemOperation.
	 * @return itemOperation - CourtCaseConditionItemOperation
	 */
	public CourtCaseConditionItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 * @param itemOperation - CourtCaseConditionItemOperation
	 */
	public void setItemOperation(
			final CourtCaseConditionItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
