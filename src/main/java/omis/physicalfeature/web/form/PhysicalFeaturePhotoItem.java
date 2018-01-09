package omis.physicalfeature.web.form;

import java.util.Date;

import omis.media.domain.Photo;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;

/**
 * Physical feature photo item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 6, 2016)
 * @since OMIS 3.0
 */
public class PhysicalFeaturePhotoItem extends PhotoItem {

	private PhysicalFeaturePhotoAssociation photoAssociation;

	/**
	 * Instantiates an instance of physical feature photo item using the photo
	 * item super constructor and a physical feature photo association.
	 * 
	 * @param photo photo
	 * @param date date
	 * @param photoData photo date
	 * @param operation photo item operation
	 * @param photoAssociation physical feature photo association
	 */
	public PhysicalFeaturePhotoItem(final Photo photo, final Date date, 
			final byte[] photoData, final PhotoItemOperation operation,
			final PhysicalFeaturePhotoAssociation photoAssociation) {
		super(photo, date, photoData, operation);
		this.photoAssociation = photoAssociation;
	}
	
	/**
	 * Instantiates a default instance of physical feature photo item.
	 */
	public PhysicalFeaturePhotoItem() {
		//Default constructor.
	}

	/**
	 * Returns the physical feature photo association.
	 * 
	 * @return physical feature photo association
	 */
	public PhysicalFeaturePhotoAssociation getPhotoAssociation() {
		return this.photoAssociation;
	}

	/**
	 * Sets the physical feature photo association.
	 * 
	 * @param photoAssociation physical feature photo association
	 */
	public void setPhotoAssociation(
			final PhysicalFeaturePhotoAssociation photoAssociation) {
		this.photoAssociation = photoAssociation;
	}
}