package omis.offenderphoto.service;

import java.util.Date;
import java.util.List;

import omis.exception.BusinessException;
import omis.exception.DuplicateEntityFoundException;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.offenderphoto.domain.OffenderPhotoAssociation;
import omis.offenderphoto.domain.OffenderPhotoAssociationNote;

/**
 * Service for offender photos.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.1 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public interface OffenderPhotoService {

	/**
	 * Returns profile photo association for offender.
	 * 
	 * @param offender offender
	 * @return profile association for offender
	 */
	OffenderPhotoAssociation findProfilePhotoAssociation(Offender offender);
	
	/**
	 * Returns the profile photo for the offender.
	 * 
	 * @param offender offender
	 * @return profile photo for offender
	 */
	Photo findProfilePhoto(Offender offender);
	
	/**
	 * Sets the offender profile photo association.
	 * 
	 * <p>The current offender profile photo association will no longer be used
	 * as the offender profile photo association. 
	 * 
	 * @param association association to use for offender profile
	 */
	void setOffenderProfilePhoto(OffenderPhotoAssociation association);
	
	/**
	 * Returns photo associations for offender.
	 * 
	 * @param offender offender
	 * @return photo associations for offender
	 */
	List<OffenderPhotoAssociation> findByOffender(Offender offender);
	
	/**
	 * Removes an offender photo association.
	 * 
	 * @param association association to remove
	 * @throws BusinessException if offender profile photo association
	 */
	void remove(OffenderPhotoAssociation association) throws BusinessException;
	
	/**
	 * Associates an offender to a photo file that is not a profile photo.
	 * 
	 * @param offender offender
	 * @param filename filename
	 * @param photoDate photo date
	 * @return association of offender to photo file
	 */
	OffenderPhotoAssociation associateNoneProfilePhotoFile(
			Offender offender, String filename, Date photoDate);
	
	/**
	 * Associates an offender profile photo to a photo file.
	 * 
	 * @param offender offender
	 * @param filename filename
	 * @param photoDate photo date
	 * @return association of offender to photo file
	 */
	OffenderPhotoAssociation associateProfilePhotoFile(
			Offender offender, String filename, Date photoDate);
	
	/**
	 * Updates offender photo association.
	 * 
	 * @param offenderPhotoAssociation offender photo association
	 * @param photoDate photo date
	 * @return updated offender photo association
	 */
	OffenderPhotoAssociation updateAssociation(
			OffenderPhotoAssociation offenderPhotoAssociation,
			Date photoDate);
	
	/**
	 * Returns offender photo association notes for the specified offender
	 * photo association.
	 * 
	 * @param association offender photo association
	 * @return list of offender photo association notes
	 */
	List<OffenderPhotoAssociationNote> findAssociationNotes(
			OffenderPhotoAssociation association);
	
	/**
	 * Adds an offender photo association note to the specified offender photo
	 * association.
	 * 
	 * @param association offender photo association
	 * @param date date
	 * @param value value
	 * @return offender photo association note
	 * @throws DuplicateEntityFoundException thrown when a duplicate offender
	 * photo association note is found
	 */
	OffenderPhotoAssociationNote addAssociationNote(
			OffenderPhotoAssociation association, Date date, String value)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified offender photo association note.
	 * 
	 * @param associationNote offender photo association note
	 * @param date date
	 * @param value value
	 * @return updated offender photo association note
	 * @throws DuplicateEntityFoundException thrown when a duplicate offender
	 * photo association note, except the specified association note, is found.
	 */
	OffenderPhotoAssociationNote updateAssociationNote(
			OffenderPhotoAssociationNote associationNote, Date date, 
			String value) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified offender photo association note.
	 * 
	 * @param associationNote offender photo association note
	 */
	void removeAssociationNote(OffenderPhotoAssociationNote associationNote);
}