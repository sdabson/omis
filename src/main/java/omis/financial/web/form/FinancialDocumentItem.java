package omis.financial.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;
import omis.financial.domain.FinancialDocumentAssociation;

/**
 * FinancialDocumentForm
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (June 2, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialDocumentItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private FinancialDocumentAssociation financialDocumentAssociation;
	
	private ItemOperation operation;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> documentTagItems;
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	/**
	 * Instantiates a default prison term form.
	 */
	public FinancialDocumentItem() {
		// Default Instantiation
	}
	
	/**
	 * Returns the document
	 * @return document - Document
	 */
	public Document getDocument() {
		return document;
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
		return title;
	}

	/**
	 * Sets the title
	 * @param title - String
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the documentTagItems
	 * @return documentTagItems - List<DocumentTagItem>
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return documentTagItems;
	}

	/**
	 * Sets the documentTagItems
	 * @param documentTagItems - List<DocumentTagItem>
	 */
	public void setDocumentTagItems(final List<DocumentTagItem>
		documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
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
		return fileExtension;
	}

	/**
	 * Sets the fileExtension
	 * @param fileExtension - String
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
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

	/**
	 * @return the operation
	 */
	public ItemOperation getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(ItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the financial document association
	 */
	public FinancialDocumentAssociation getFinancialDocumentAssociation() {
		return financialDocumentAssociation;
	}

	/**
	 * @param financialDocumentAssociation the financial document association
	 *  to set
	 */
	public void setFinancialDocumentAssociation(
			FinancialDocumentAssociation financialDocumentAssociation) {
		this.financialDocumentAssociation = financialDocumentAssociation;
	}

}
