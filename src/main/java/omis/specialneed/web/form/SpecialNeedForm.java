/**
 * 
 */
package omis.specialneed.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.document.domain.Document;
import omis.document.web.form.DocumentTagItem;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Form for special need.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.1 (Sep. 01, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedForm {

	private String comment;
	
	private String sourceComment;
	
	private Date startDate;
	
	private Date endDate;
	
	private SpecialNeedSource source;
	
	private SpecialNeedCategory category;
	
	private SpecialNeedClassification classification;
	
	private SpecialNeedAssociableDocumentCategory documentCategory;
	
	private Document document;
	
	private String title;
	
	private List<DocumentTagItem> documentTagItems;
	
	private Date date;
	
	private String fileExtension;
	
	private byte[] data;
	
	private SpecialNeedAssociableDocumentItemOperation documentItemOperation;
	
	private List<SpecialNeedNoteItem> specialNeedNotes
		= new ArrayList<SpecialNeedNoteItem>();	
	

	/** Instantiates a default instance of special need form. */
	public SpecialNeedForm() {
		//empty constructor
	}
	
	/**
	 * Returns the comment of the form.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * Sets the comment of the form.
	 * 
	 * @param comment the comment to set
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * Returns the source comment.
	 * 
	 * @return sourceComment source comment
	 */
	public String getSourceComment() {
		return this.sourceComment;
	}

	/**
	 * Sets the source comment.
	 * 
	 * @param sourceComment source comment
	 */
	public void setSourceComment(String sourceComment) {
		this.sourceComment = sourceComment;
	}

	/**
	 * Returns the start date of the special need form.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the end date of the special need form.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date of the special need form.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date of the special need form.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the source of the form.
	 * 
	 * @return the source
	 */
	public SpecialNeedSource getSource() {
		return this.source;
	}

	/**
	 * Sets the source of the form.
	 * @param source the source to set
	 */
	public void setSource(final SpecialNeedSource source) {
		this.source = source;
	}

	/**
	 * Returns the category of the form.
	 * 
	 * @return the category
	 */
	public SpecialNeedCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category of the form.
	 * 
	 * @param category the category to set
	 */
	public void setCategory(final SpecialNeedCategory category) {
		this.category = category;
	}

	/**
	 * Gets the classification of the special need.
	 *
	 * @return the classification
	 */
	public SpecialNeedClassification getClassification() {
		return this.classification;
	}

	/**
	 * Sets the classification of the special need.
	 *
	 * @param classification classification
	 */
	public void setClassification(
			final SpecialNeedClassification classification) {
		this.classification = classification;
	}

	/**
	 * Gets the special need notes of the special need.
	 *
	 * @return the specialNeedNotes
	 */
	public List<SpecialNeedNoteItem> getSpecialNeedNotes() {
		return this.specialNeedNotes;
	}

	/**
	 * Sets the special need notes of the special need.
	 *
	 * @param specialNeedNotes specialNeedNotes
	 */
	public void setSpecialNeedNotes(
			final List<SpecialNeedNoteItem> specialNeedNotes) {
		this.specialNeedNotes = specialNeedNotes;
	}

	/**
	 * Returns the special need associable document category.
	 * 
	 * @return special need associable document category
	 */
	public SpecialNeedAssociableDocumentCategory getDocumentCategory() {
		return documentCategory;
	}

	/**
	 * Sets the special need associable document category.
	 * 
	 * @param documentCategory special need associable document category
	 */
	public void setDocumentCategory(
			final SpecialNeedAssociableDocumentCategory documentCategory) {
		this.documentCategory = documentCategory;
	}

	/**
	 * Returns the special need associable document.
	 * 
	 * @return document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * Sets the special need associable document.
	 * 
	 * @param document document
	 */
	public void setDocument(final Document document) {
		this.document = document;
	}

	/**
	 * Returns the document title.
	 * 
	 * @return document title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the document title.
	 * 
	 * @param title document title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Returns the document date.
	 * 
	 * @return document date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the document date.
	 * 
	 * @param date document date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the document file extension.
	 * 
	 * @return document file extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Sets the document file extension.
	 * 
	 * @param fileExtension document file extension
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Returns the document data.
	 * 
	 * @return document data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the document data.
	 * 
	 * @param data document data
	 */
	public void setData(final byte[] data) {
		this.data = data;
	}

	/**
	 * Returns the document tag items.
	 * 
	 * @return document tag items
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return documentTagItems;
	}

	/**
	 * Sets the document tag items.
	 * 
	 * @param documentTagItems document tag items
	 */
	public void setDocumentTagItems(
			final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}

	/**
	 * Returns the special need associable document item operation.
	 * 
	 * @return special need associable document item operation
	 */
	public SpecialNeedAssociableDocumentItemOperation 
			getDocumentItemOperation() {
		return documentItemOperation;
	}

	/**
	 * Sets the special need associable document item operation.
	 * 
	 * @param documentItemOperation special need associable document item 
	 * operation
	 */
	public void setDocumentItemOperation(
			final SpecialNeedAssociableDocumentItemOperation 
					documentItemOperation) {
		this.documentItemOperation = documentItemOperation;
	}
}