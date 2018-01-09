package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;

/**
 * Health section summary document association item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryDocumentAssociationItem {
	
	private HealthSectionSummaryDocumentAssociation 
		healthSectionSummaryDocumentAssociation;
	
	private Document document;
	
	private String title;
	
	private String filename;
	
	private String fileExtension;
	
	private Date fileDate;
	
	private List<DocumentTagItem> documentTagItems =
			new ArrayList<DocumentTagItem>();;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	private byte[] data;
	
	public HealthSectionSummaryDocumentAssociationItem() {
		//Default constructor.		
	}

	/**
	 * Return health section summary document association.
	 *
	 * @return the healthSectionSummaryDocumentAssociation
	 */
	public HealthSectionSummaryDocumentAssociation 
		getHealthSectionSummaryDocumentAssociation() {
		return this.healthSectionSummaryDocumentAssociation;
	}

	/**
	 * Sets the health sectiom summary document association.
	 *
	 * @param healthSectionSummaryDocumentAssociation 
	 * health section summary document association
	 */
	public void setHealthSectionSummaryDocumentAssociation(
			final HealthSectionSummaryDocumentAssociation 
			healthSectionSummaryDocumentAssociation) {
		this.healthSectionSummaryDocumentAssociation 
			= healthSectionSummaryDocumentAssociation;
	}

	/**
	 * Return the document.
	 *
	 * @return the document
	 */
	public Document getDocument() {
		return this.document;
	}

	/**
	 * Set the document.
	 *
	 * @param document document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Return the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the title.
	 *
	 * @param title title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Return the file name.
	 *
	 * @return the filename
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * Set the file name.
	 *
	 * @param filename filename
	 */
	public void setFilename(final String filename) {
		this.filename = filename;
	}

	/**
	 * Return file extension.
	 *
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * Set the file extension.
	 *
	 * @param fileExtension fileExtension
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Return file date.
	 *
	 * @return the fileDate
	 */
	public Date getFileDate() {
		return this.fileDate;
	}

	/**
	 * Sets the file date.
	 *
	 * @param fileDate file date
	 */
	public void setFileDate(final Date fileDate) {
		this.fileDate = fileDate;
	}

	/**
	 * Return list of document tags.
	 *
	 * @return the documentTagItems
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return this.documentTagItems;
	}

	/**
	 * Set list of document tags.
	 *
	 * @param documentTagItems document tag items
	 */
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Return the presentence investigation item operation.
	 *
	 * @return the itemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Set the presentence investigation item operation.
	 *
	 * @param itemOperation item operation
	 */
	public void setItemOperation(
			final PresentenceInvestigationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}

	/**
	 * Return date.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return this.data;
	}

	/**
	 * Set date.
	 *
	 * @param data data
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}
}