package omis.presentenceinvestigation.web.form;

import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;

/**
 * FinancialSectionSummaryDocumentAssociationItem
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 14, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryDocumentAssociationItem {

	private FinancialSectionSummaryDocumentAssociation 
		financialSectionSummaryDocumentAssociation;
	
	private Document document;
	
	private String filename;
	
	private String fileExtension;
	
	private String title;
	
	private Date fileDate;
	
	private List<DocumentTagItem> documentTagItems;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	private byte[] data;
	
	/** 
	 * Instantiates an implementation of 
	 * FinancialSectionSummaryDocumentAssociationItem 
	 */
	public FinancialSectionSummaryDocumentAssociationItem() {
		// Default constructor
	}
	
	/**
	 * Returns the chemicalUseSectionSummaryDocumentAssociation
	 * @return financialSectionSummaryDocumentAssociation -
	 * FinancialSectionSummaryDocumentAssociation
	 */
	public FinancialSectionSummaryDocumentAssociation
			getFinancialSectionSummaryDocumentAssociation() {
		return financialSectionSummaryDocumentAssociation;
	}

	/**
	 * Sets the chemicalUseSectionSummaryDocumentAssociation
	 * @param financialSectionSummaryDocumentAssociation -
	 * FinancialSectionSummaryDocumentAssociation
	 */
	public void setFinancialSectionSummaryDocumentAssociation(
			final FinancialSectionSummaryDocumentAssociation
				financialSectionSummaryDocumentAssociation) {
		this.financialSectionSummaryDocumentAssociation =
				financialSectionSummaryDocumentAssociation;
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
	 * Returns the filename
	 * @return filename - String
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename
	 * @param filename - String
	 */
	public void setFilename(final String filename) {
		this.filename = filename;
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
	 * Returns the fileDate
	 * @return fileDate - Date
	 */
	public Date getFileDate() {
		return fileDate;
	}

	/**
	 * Sets the fileDate
	 * @param fileDate - Date
	 */
	public void setFileDate(final Date fileDate) {
		this.fileDate = fileDate;
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
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - PresentenceInvestigationItemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - PresentenceInvestigationItemOperation
	 */
	public void setItemOperation(
			final PresentenceInvestigationItemOperation itemOperation) {
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
