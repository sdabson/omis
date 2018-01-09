package omis.physicalfeature.web.form;

import java.util.Date;

import omis.media.domain.Photo;

/**
 * Photo item.
 * @author Joel Norris
 * @version 0.1.0 (Nov 18, 2013)
 * @since OMIS 3.0
 */
public class PhotoItem {

	private Photo photo;
	
	private Date date;
	
	private byte[] photoData;
	
	private PhotoItemOperation operation;

	/**
	 * Instantiates an instance of photo item with the specified properties.
	 * 
	 * @param photo photo
	 * @param date date
	 * @param photoData photo data
	 * @param operation photo item operation
	 */
	public PhotoItem(final Photo photo, final Date date, final byte[] photoData,
			final PhotoItemOperation operation) {
		this.photo = photo;
		this.date = date;
		this.photoData = photoData;
		this.operation = operation;
	}
	
	/**
	 * Instantiates a default instance of photo item.
	 */
	public PhotoItem() {
		//Default constructor.
	}
	
	/**
	 * Return the photo of the photo item.
	 * @return photo
	 */
	public Photo getPhoto() {
		return this.photo;
	}

	/**
	 * Sets the photo of the photo item.
	 * @param photo photo
	 */
	public void setPhoto(final Photo photo) {
		this.photo = photo;
	}

	/**
	 * Returns the photo data of the photo item.
	 * @return photo data
	 */
	public byte[] getPhotoData() {
		return this.photoData;
	}

	/**
	 * Sets the photo data of the photo item.
	 * @param photoData photo data
	 */
	public void setPhotoData(final byte[] photoData) {
		this.photoData = photoData;
	}

	/**
	 * Returns the date of the photo item.
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date of the photo item.
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the photo item operation.
	 * 
	 * @return operation photo item operation
	 */
	public PhotoItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the photo item operation.
	 * 
	 * @param operation photo item operation
	 */
	public void setOperation(final PhotoItemOperation operation) {
		this.operation = operation;
	}
}