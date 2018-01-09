package omis.detainernotification.web.form;

import java.util.Date;
import java.util.List;

import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.Direction;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.document.domain.Document;

/**
 * InterstateDetainerActivityItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class InterstateDetainerActivityItem {
	
	private InterstateDetainerActivity interstateDetainerActivity;
	
	private Date activityDate;
	
	private DetainerActivityCategory category;
	
	private Direction direction;
	
	private Document document;
	
	private String title;
	
	private String filename;
	
	private String fileExtension;
	
	private Date fileDate;
	
	private List<DocumentTagItem> documentTagItems;
	
	private byte[] data;
	
	private DetainerNotificationItemOperation itemOperation;
	
	
	/**
	 * 
	 */
	public InterstateDetainerActivityItem() {
	}


	/**
	 * Returns the interstateDetainerActivity
	 * @return interstateDetainerActivity - InterstateDetainerActivity
	 */
	public InterstateDetainerActivity getInterstateDetainerActivity() {
		return interstateDetainerActivity;
	}


	/**
	 * Sets the interstateDetainerActivity
	 * @param interstateDetainerActivity - InterstateDetainerActivity
	 */
	public void setInterstateDetainerActivity(
			final InterstateDetainerActivity interstateDetainerActivity) {
		this.interstateDetainerActivity = interstateDetainerActivity;
	}


	/**
	 * Returns the activityDate
	 * @return activityDate - Date
	 */
	public Date getActivityDate() {
		return activityDate;
	}


	/**
	 * Sets the activityDate
	 * @param activityDate - Date
	 */
	public void setActivityDate(final Date activityDate) {
		this.activityDate = activityDate;
	}


	/**
	 * Returns the category
	 * @return category - DetainerActivityCategory
	 */
	public DetainerActivityCategory getCategory() {
		return category;
	}


	/**
	 * Sets the category
	 * @param category - DetainerActivityCategory
	 */
	public void setCategory(final DetainerActivityCategory category) {
		this.category = category;
	}


	/**
	 * Returns the direction
	 * @return direction - Direction
	 */
	public Direction getDirection() {
		return direction;
	}


	/**
	 * Sets the direction
	 * @param direction - Direction
	 */
	public void setDirection(final Direction direction) {
		this.direction = direction;
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
	public void setDocumentTagItems(final List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
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
	 * Returns the itemOperation
	 * @return itemOperation - DetainerNotificationItemOperation
	 */
	public DetainerNotificationItemOperation getItemOperation() {
		return itemOperation;
	}


	/**
	 * Sets the itemOperation
	 * @param itemOperation - DetainerNotificationItemOperation
	 */
	public void setItemOperation(
			final DetainerNotificationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
	
}
