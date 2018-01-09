package omis.offenderphoto.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Form for offender photos.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 26, 2014)
 * @since OMIS 3.0
 */
public class OffenderPhotoForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date photoDate;
	
	private byte[] photoData;
	
	private List<OffenderPhotoAssociationNoteItem> noteItems 
	= new ArrayList<OffenderPhotoAssociationNoteItem>();
	
	/** Instantiates a form for offender photos. */
	public OffenderPhotoForm() {
		// Default instantiation
	}

	/**
	 * Returns the photo date.
	 * 
	 * @return photo date
	 */
	public Date getPhotoDate() {
		return this.photoDate;
	}

	/**
	 * Sets the photo date.
	 * 
	 * @param photoDate photo date
	 */
	public void setPhotoDate(final Date photoDate) {
		this.photoDate = photoDate;
	}

	/**
	 * Returns the photo data.
	 * 
	 * @return photo data
	 */
	public byte[] getPhotoData() {
		return this.photoData;
	}

	/**
	 * Sets the photo data.
	 * 
	 * @param photoData photo data
	 */
	public void setPhotoData(final byte[] photoData) {
		this.photoData = photoData;
	}

	/**
	 * Returns offender photo association note items.
	 * 
	 * @return list of offender photo association note items
	 */
	public List<OffenderPhotoAssociationNoteItem> getNoteItems() {
		return this.noteItems;
	}

	/**
	 * Sets offender photo association note items.
	 * 
	 * @param noteItems offender photo association note items
	 */
	public void setNoteItems(
			final List<OffenderPhotoAssociationNoteItem> noteItems) {
		this.noteItems = noteItems;
	}
}