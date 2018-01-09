package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;

/**
 * OffenseSectionSummaryForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 3, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryForm {
	
	private String text;
	
	private List<OffenseSectionSummaryAssociableDocumentItem>
		offenseSectionSummaryAssociableDocumentItems =
		new ArrayList<OffenseSectionSummaryAssociableDocumentItem>();
	
	private String chargeReason;
	
	private String involvementReason;
	
	private String courtRecommendation;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> documentTagItems =
			new ArrayList<DocumentTagItem>();
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	/**
	 * Default Constructor for OffenseSectionSummaryForm
	 */
	public OffenseSectionSummaryForm() {
	}

	/**
	 * Returns the text
	 * @return text - String
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text
	 * @param text - String
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the offenseSectionSummaryAssociableDocumentItems
	 * @return offenseSectionSummaryAssociableDocumentItems -
	 * List<OffenseSectionSummaryAssociableDocumentItem>
	 */
	public List<OffenseSectionSummaryAssociableDocumentItem>
			getOffenseSectionSummaryAssociableDocumentItems() {
		return offenseSectionSummaryAssociableDocumentItems;
	}

	/**
	 * Sets the offenseSectionSummaryAssociableDocumentItems
	 * @param offenseSectionSummaryAssociableDocumentItems -
	 * List<OffenseSectionSummaryAssociableDocumentItem>
	 */
	public void setOffenseSectionSummaryAssociableDocumentItems(
			final List<OffenseSectionSummaryAssociableDocumentItem>
				offenseSectionSummaryAssociableDocumentItems) {
		this.offenseSectionSummaryAssociableDocumentItems =
				offenseSectionSummaryAssociableDocumentItems;
	}

	/**
	 * Returns the chargeReason
	 * @return chargeReason - String
	 */
	public String getChargeReason() {
		return chargeReason;
	}

	/**
	 * Sets the chargeReason
	 * @param chargeReason - String
	 */
	public void setChargeReason(final String chargeReason) {
		this.chargeReason = chargeReason;
	}

	/**
	 * Returns the involvementReason
	 * @return involvementReason - String
	 */
	public String getInvolvementReason() {
		return involvementReason;
	}

	/**
	 * Sets the involvementReason
	 * @param involvementReason - String
	 */
	public void setInvolvementReason(final String involvementReason) {
		this.involvementReason = involvementReason;
	}

	/**
	 * Returns the courtRecommendation
	 * @return courtRecommendation - String
	 */
	public String getCourtRecommendation() {
		return courtRecommendation;
	}

	/**
	 * Sets the courtRecommendation
	 * @param courtRecommendation - String
	 */
	public void setCourtRecommendation(final String courtRecommendation) {
		this.courtRecommendation = courtRecommendation;
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
	public void setDocumentTagItems(final List<DocumentTagItem> documentTagItems) {
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
	
	
	
	
}
