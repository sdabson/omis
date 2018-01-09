package omis.physicalfeature.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;

/**
 * Offender physical feature service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureAssociationService {

	/**
	 * Creates a new physical feature association with the specified properties.
	 * 
	 * @param feature physical feature
	 * @param description description
	 * @param originationDate origination date
	 * @param offender offender
	 * @return physical feature association
	 * @throws DuplicateEntityFoundException duplicate entity found exception 
	 */
	PhysicalFeatureAssociation create(PhysicalFeature feature, 
			String description, Date originationDate, Offender offender) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified physical feature association with the specified
	 * properties.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @param feature physical feature
	 * @param description description
	 * @param orginationDate origination date
	 * @param offender offender
	 * @return physical feature association
	 * @throws DuplicateEntityFoundException duplicate entity found exception 
	 */
	PhysicalFeatureAssociation update(
			PhysicalFeatureAssociation physicalFeatureAssociation, 
			PhysicalFeature feature, String description,
			Date originationDate, Offender offender) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Creates a new photo with the specified date and file name.
	 * 
	 * @param date date
	 * @param fileName file name
	 */
	Photo createPhoto(Date date, String fileName);
	
	/**
	 * Removes the specified photo.
	 * 
	 * @param photo photo
	 */
	void removePhoto(Photo photo);
	
	/**
	 * Creates a physical feature association note for the specified 
	 * physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @param note note
	 * @param date date
	 * @return physical feature association note
	 */
	PhysicalFeatureAssociationNote createNote(
			PhysicalFeatureAssociation physicalFeatureAssociation,
			String note, Date date);
	
	/**
	 * Updates the specified physical feature association note.
	 * 
	 * @param associationNote physical feature association note
	 * @param note note
	 * @param date date
	 * @return physical feature association note
	 */
	PhysicalFeatureAssociationNote updateNote(
			PhysicalFeatureAssociationNote associationNote, String note,
			Date date);
	
	/**
	 * Removes the specified physical feature association note.
	 * 
	 * @param note physical feature association note
	 */
	void removeNote(PhysicalFeatureAssociationNote note);
	
	/**
	 * Associates a photo with the specified physical feature association by
	 * creating a new physical feature photo association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @param offender offender
	 * @param photo photo
	 * @return
	 */
	PhysicalFeaturePhotoAssociation createPhysicalFeaturePhotoAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation,
			Offender offender, Photo photo);
	
	/**
	 * Updates the specified physical feature photo association.
	 * 
	 * @param photoAssociation physical feature photo association
	 * @param photo photo
	 * @return physical feature photo association
	 */
	PhysicalFeaturePhotoAssociation updatePhysicalFeaturePhotoAssociation(
			PhysicalFeaturePhotoAssociation photoAssociation, Photo photo);
	
	/**
	 * Removes the specified physical feature photo association.
	 * 
	 * @param physicalFeaturePhotoAssociation physical feature photo association
	 */
	void removePhysicalFeaturePhotoAssociation(
			PhysicalFeaturePhotoAssociation physicalFeaturePhotoAssociation);
	
	/**
	 * Removes the specified physical feature association.
	 * 
	 * @param pFAssociation physical feature association
	 */
	void remove(PhysicalFeatureAssociation pFAssociation);

	/**
	 * Returns a list of all physical features associations with
	 * the specified offender.
	 * 
	 * @param offender offender
	 * @return list of physical feature associations
	 */
	List<PhysicalFeatureAssociation> findPhysicalFeaturesAssociationsByOffender(
			Offender offender);
	
	/**
	 * Returns a list of all physical features for the specified classification.
	 * 
	 * @param featureClassification feature classification
	 * @return list of physical features
	 */
	List<PhysicalFeature> findPhysicalFeatureByClassification(
			FeatureClassification featureClassification);

	/**
	 * Returns a list of all physical features.
	 * 
	 * @return list of physical features
	 */
	List<PhysicalFeature> findAllPhysicalFeatures();
	
	/**
	 * Returns a list of all physical feature photo associations for the
	 * specified physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @return list of physical feature photo associations
	 */
	List<PhysicalFeaturePhotoAssociation>
		findPhotoAssociationsByPhysicalFeatureAssociation(
		PhysicalFeatureAssociation physicalFeatureAssociation);
	
	/**
	 * Returns the physical feature photo association for the specified
	 * photo and physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @param photo photo
	 * @return
	 */
	PhysicalFeaturePhotoAssociation findPhysicalFeaturePhotoAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation, Photo photo);
	
	/**
	 * Returns all physical feature photo associations for the specified
	 * physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @return list of physical feature photo associations
	 */
	List<PhysicalFeaturePhotoAssociation>
	findPhotoAssociationsByFeatureAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation);
	
	/**
	 * Returns a list of all valid feature classifications.
	 * 
	 * @return list of feature classifications
	 */
	List<FeatureClassification> findFeatureClassifications();
	
	/**
	 * Returns a list of physicla feature association notes for the specified
	 * physical feature association.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @return list of physical feature association notes
	 */
	List<PhysicalFeatureAssociationNote> findNotesByPhysicalFeatureAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation);

	/**
	 * Updates the specified physical feature photo association.
	 * 
	 * @param photoAssociation physical feature photo association
	 * @param date date
	 * @return updated physical feature photo association
	 */
	PhysicalFeaturePhotoAssociation updatePhotoAssociation(
			PhysicalFeaturePhotoAssociation photoAssociation, Date date);

	/**
	 * Counts other photos associated with the specified offender.
	 * 
	 * @param offender
	 * @return count of other photos
	 */
	Integer countOtherPhotosByOffender(Offender offender);
}