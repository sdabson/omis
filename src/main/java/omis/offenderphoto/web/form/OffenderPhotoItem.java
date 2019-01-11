package omis.offenderphoto.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Offender photo item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (November 15, 2018)
 * @since OMIS 3.0
 */
public class OffenderPhotoItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private byte[] photoData;
	private Date date;
	
	/** Instantiates a default instance of offender photo item. */
	public OffenderPhotoItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an offender photo item.
	 * 
	 * @param id ID
	 * @param photoData photo date
	 * @param date date
	 * @param leftImage left image
	 * @param rightImage right image
	 */
	public OffenderPhotoItem(final Long id, final byte[] photoData,
			final Date date) {
		this.id = id;
		this.photoData = photoData;
		this.date = date;
	}

	/**
	 * Returns the ID.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the ID.
	 * 
	 * @param id id
	 */
	public void setId(final Long id) {
		this.id = id;
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
	 * Return the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
}