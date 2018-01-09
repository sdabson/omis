package omis.physicalfeature.service;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;

/**
 * Physical feature photo service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 21, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeaturePhotoAssociationService {
	
	/**
	 * Returns the physical feature photo with the specified id.
	 * @param id id
	 * @return physical feature photo
	 */
	PhysicalFeaturePhotoAssociation findById(Long id);
	
	/**
	 * Removes the specified physical feature photo associations.
	 * @param physicalFeaturePhotoAssociation physical feature photo association
	 */
	void remove(PhysicalFeaturePhotoAssociation 
			physicalFeaturePhotoAssociation);
	
	/**
	 * Returns a list of all physical feature photo associations.
	 * @return physical feature photo
	 */
	List<PhysicalFeaturePhotoAssociation> findAll();

	/**
	 * Returns a list of all physical feature photo associations that are 
	 * associated with the specified physical feature association.
	 * @param pFAssociation physical feature association
	 * @return list of physical feature photos
	 */
	List<PhysicalFeaturePhotoAssociation> findByPhysicalFeatureAssociation(
			PhysicalFeatureAssociation pFAssociation);
	
	/**
	 * Return the physical feature photo association
	 * with the specified properties.
	 * 
	 * @param pFAssociation physical feature association
	 * @param photo photo 
	 * @return physical feature photo association;
	 * {@code null} if no such photo association exists
	 */
	PhysicalFeaturePhotoAssociation find(
			PhysicalFeatureAssociation pFAssociation, Photo photo);

	/**
	 * Removes photo associations by physical feature association, excluding 
	 * the ones specified.
	 * 
	 * @param pFAssociation physical feature association
	 * @param excludingPhotoAssociations array of physical feature photo 
	 * associations
	 */
	void removeAssociationByPhysicalFeatureAssociationExcluding(
			PhysicalFeatureAssociation pFAssociation, 
			PhysicalFeaturePhotoAssociation... excludingPhotoAssociations);
	
	/**
	 * Finds the photo object that matches the specified photo object's id and
	 * delete's that object.
	 * 
	 * @param photo photo
	 */
	void removePhysicalFeaturePhotoAssociationPhoto(Photo photo);
	
	/**
	 * Returns a list of photos that were part of the specified list of photos, 
	 * but not part of the specified list of persisted photos.
	 *  
	 * @param photos photos
	 * @param persistedPhotos persisted photos
	 * @return list of orphaned photos
	 */
	List<Photo> findPhysicalFeaturePhotoOrphans(List<Photo> photos, 
			List<Photo> persistedPhotos);

	/**
	 * Returns a list of {@code physical feature photo associations} that belong
	 * to the specified offender and have {@code null} physical feature
	 * association property.
	 * 
	 * @param offender offender
	 * @return list of orphaned physical feature photo associations
	 */
	List<PhysicalFeaturePhotoAssociation> findOrphanedPhotoAssociations(
			Offender offender);
	
	/**
	 * Saves a new physical feature photo association with the specified
	 * properties.
	 * 
	 * @param association association
	 * @param person person
	 * @param photo photo
	 * @return physical feature photo association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	PhysicalFeaturePhotoAssociation save(PhysicalFeatureAssociation association,
			Person person, Photo photo) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified physical feature photo association with the
	 * specified properties.
	 * 
	 * @param association association
	 * @param person person
	 * @param photo photo
	 * @param physicalFeaturePhotoAssociation physical feature photo association
	 * @return physical feature photo association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	PhysicalFeaturePhotoAssociation update(
			PhysicalFeatureAssociation association, Person person, Photo photo,
			PhysicalFeaturePhotoAssociation physicalFeaturePhotoAssociation)
		throws DuplicateEntityFoundException;
}