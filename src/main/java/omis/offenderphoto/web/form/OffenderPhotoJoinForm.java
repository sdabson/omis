package omis.offenderphoto.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Offender photo join form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (November 15, 2018)
 * @since OMIS 3.0
 */
public class OffenderPhotoJoinForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date photoDate;
	private byte[] photoData;
	private List<OffenderPhotoAssociationNoteItem> noteItems 
		= new ArrayList<OffenderPhotoAssociationNoteItem>();
	private List<OffenderPhotoItem> photoItems
		= new ArrayList<OffenderPhotoItem>();
	private Long leftImage;
	private Long rightImage;
	
	/** Instantiates a default instance of offender photo join form. */
	public OffenderPhotoJoinForm() {
		//Default constructor.
	}

	/**
	 * Returns photo date.
	 * @return photo date
	 */
	public Date getPhotoDate() {
		return this.photoDate;
	}

	/**
	 * Sets photo date.
	 * @param photoDate photo date
	 */
	public void setPhotoDate(final Date photoDate) {
		this.photoDate = photoDate;
	}

	/**
	 * Returns photo data.
	 * @return photo data
	 */
	public byte[] getPhotoData() {
		return this.photoData;
	}

	/**
	 * Sets photo data.
	 * @param photoData photo data
	 */
	public void setPhotoData(final byte[] photoData) {
		this.photoData = photoData;
	}

	/**
	 * Returns offender photo association note items.
	 * @return list offender photo association note items
	 */
	public List<OffenderPhotoAssociationNoteItem> getNoteItems() {
		return this.noteItems;
	}

	/**
	 * Sets offender photo association note item.
	 * @param noteItems note items
	 */
	public void setNoteItems(List<OffenderPhotoAssociationNoteItem> noteItems) {
		this.noteItems = noteItems;
	}

	/**
	 * Returns offender photo items.
	 * @return list of offender photo item
	 */
	public List<OffenderPhotoItem> getPhotoItems() {
		return this.photoItems;
	}

	/**
	 * Sets offender photo items.
	 * @param photoItems list of offender photo item
	 */
	public void setPhotoItems(List<OffenderPhotoItem> photoItems) {
		this.photoItems = photoItems;
	}
	
	/**
	 * Returns left image.
	 * @return left image
	 */
	public Long getLeftImage() {
		return this.leftImage;
	}

	/**
	 * Sets left image.
	 * @param leftImage left image
	 */
	public void setLeftImage(final Long leftImage) {
		this.leftImage = leftImage;
	}

	/**
	 * Returns right image.
	 * @return right image
	 */
	public Long getRightImage() {
		return this.rightImage;
	}

	/**
	 * Sets right image.
	 * @param rightImage right image
	 */
	public void setRightImage(final Long rightImage) {
		this.rightImage = rightImage;
	}
}