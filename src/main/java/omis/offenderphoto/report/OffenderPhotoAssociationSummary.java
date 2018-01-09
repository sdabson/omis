package omis.offenderphoto.report;

import java.util.Date;

/** Summary of offender photos.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0 */
public class OffenderPhotoAssociationSummary {
	private final Long offenderId;
	private final Long offenderPhotoAssociationId;
	private final Date photoDate;
	
	/** Constructor.
	 * @param offenderId - offender id.
	 * @param photoDate - date of photo. 
	 * @param offenderPhotoAssociationId - offender photo association id. */
	public OffenderPhotoAssociationSummary(final Long offenderId, 
			final Date photoDate, final Long offenderPhotoAssociationId) {
		this.offenderId = offenderId;
		this.photoDate = photoDate;
		this.offenderPhotoAssociationId = offenderPhotoAssociationId;
	}
	
	/** Gets offender is.
	 * @return offender id. */
	public Long getOffenderId() { 
		return this.offenderId; 
	}	
	
	/** Gets photo date.
	 * @return photo date. */
	public Date getPhotoDate() { 
		return this.photoDate; 
	}
	
	/** Gets photo association id.
	 * @return offenderPhotoAssociationId - offender photo association id. */
	public Long getOffenderPhotoAssociationId() {
		return this.offenderPhotoAssociationId;
	}
}
