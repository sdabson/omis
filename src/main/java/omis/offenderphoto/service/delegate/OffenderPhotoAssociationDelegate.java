package omis.offenderphoto.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.media.dao.PhotoDao;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.offenderphoto.dao.OffenderPhotoAssociationDao;
import omis.offenderphoto.domain.OffenderPhotoAssociation;

/**
 * OffenderPhotoAssociationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class OffenderPhotoAssociationDelegate {
	
	
	private final OffenderPhotoAssociationDao offenderPhotoAssociationDao;
	
	private final PhotoDao photoDao;
	
	private final InstanceFactory<OffenderPhotoAssociation> 
		offenderPhotoAssociationInstanceFactory;
	
	private final InstanceFactory<Photo> photoInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for OffenderPhotoAssociationDelegate
	 * @param offenderPhotoAssociationDao
	 * @param offenderPhotoAssociationInstanceFactory
	 * @param auditComponentRetriever
	 */
	public OffenderPhotoAssociationDelegate(
			final OffenderPhotoAssociationDao offenderPhotoAssociationDao,
			final PhotoDao photoDao,
			final InstanceFactory<OffenderPhotoAssociation> 
				offenderPhotoAssociationInstanceFactory,
			final InstanceFactory<Photo> photoInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.offenderPhotoAssociationDao = offenderPhotoAssociationDao;
		this.photoDao = photoDao;
		this.offenderPhotoAssociationInstanceFactory = 
				offenderPhotoAssociationInstanceFactory;
		this.photoInstanceFactory = photoInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an OffenderPhotoAssociation
	 * @param offender - offender
	 * @param photo - photo
	 * @param profile - Boolean
	 * @return Created OffenderPhotoAssociation
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderPhotoAssociation create(final Offender offender,
			final Photo photo, final Boolean profile)
					throws DuplicateEntityFoundException{
		
		OffenderPhotoAssociation offenderPhotoAssociation = 
				this.offenderPhotoAssociationInstanceFactory.createInstance();
		
		offenderPhotoAssociation.setPerson(offender);
		offenderPhotoAssociation.setPhoto(photo);
		offenderPhotoAssociation.setProfile(profile);
		offenderPhotoAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		offenderPhotoAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenderPhotoAssociationDao
				.makePersistent(offenderPhotoAssociation);
	}
	
	/**
	 * Updates an OffenderPhotoAssociation
	 * @param offenderPhotoAssociation - OffenderPhotoAssociation to update
	 * @param photo - Photo
	 * @param profile - Boolean
	 * @return Updated OffenderPhotoAssociation
	 * @throws DuplicateEntityFoundException
	 */
	public OffenderPhotoAssociation update(
			final OffenderPhotoAssociation offenderPhotoAssociation,
			final Photo photo, final Boolean profile)
				throws DuplicateEntityFoundException{
		
		offenderPhotoAssociation.setPhoto(photo);
		offenderPhotoAssociation.setProfile(profile);
		offenderPhotoAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenderPhotoAssociationDao
				.makePersistent(offenderPhotoAssociation);
	}
	
	/**
	 * Removes an OffenderPhotoAssociation
	 * @param offenderPhotoAssociation - OffenderPhotoAssociation to remove
	 */
	public void remove(final OffenderPhotoAssociation offenderPhotoAssociation){
		this.offenderPhotoAssociationDao.makeTransient(offenderPhotoAssociation);
	}
	
	public Photo createPhoto(final Offender offender, final String filename, 
			final Date photoDate){
		Photo photo = this.photoInstanceFactory.createInstance();
		photo.setFilename(filename);
		photo.setDate(photoDate);
		photo.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		photo.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.photoDao.makePersistent(photo);
	}
	
	/**
	 * Returns photo associations for offender.
	 * 
	 * @param offender offender
	 * @return photo associations for offender
	 */
	public List<OffenderPhotoAssociation> findByOffender(
			final Offender offender) {
		return this.offenderPhotoAssociationDao.findByOffender(offender);
	}
	
	/**
	 * Returns profile photo association for offender.
	 * 
	 * @param offender offender
	 * @return profile association for offender
	 */
	public OffenderPhotoAssociation findProfilePhotoAssociation(
			final Offender offender) {
		return this.offenderPhotoAssociationDao
				.findProfilePhotoAssociation(offender);
	}
	
}
