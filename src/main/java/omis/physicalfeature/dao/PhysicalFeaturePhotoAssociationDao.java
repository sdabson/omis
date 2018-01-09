package omis.physicalfeature.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;

/**
 * Data access object for physical feature photo.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeaturePhotoAssociationDao 
	extends GenericDao<PhysicalFeaturePhotoAssociation> {

	/**
	 * Returns a list of all physical feature photos that are associated with
	 * the specified physical feature association.
	 * @param pFAssociation physical feature association
	 * @return list of physical feature photos
	 */
	List<PhysicalFeaturePhotoAssociation> findByPhysicalFeatureAssociation(
			PhysicalFeatureAssociation pFAssociation);

	/**
	 * Return the physical feature photo with the specified properties.
	 * 
	 * @param pFAssociation physical feature association
	 * @param photo photo 
	 * @return physical feature photo;
	 * {@code null} if no such photo exists
	 */
	PhysicalFeaturePhotoAssociation find(
			PhysicalFeatureAssociation pFAssociation, Photo photo);

	/**
	 * Removes photo associations by physical feature association, excluding 
	 * the physical feature photo associations specified.
	 * 
	 * @param pFAssociation physical feature association
	 * @param excludingPhotoAssociations array of physical feature photo 
	 * associations
	 */
	void removeAssociationByPhysicalFeatureAssociationExcluding(
			PhysicalFeatureAssociation pFAssociation, 
			PhysicalFeaturePhotoAssociation... excludingPhotoAssociations);

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
	 * Returns the photo that matches the specified filename.
	 * 
	 * @param filename file name
	 * @return photo
	 */
	Photo findMatchingPhysicalFeaturePhoto(String filename);

	/**
	 * Returns a list of {@code physical feature photo associations} that belong
	 * to the specified offender and have {@code null} physical feature
	 * association property.
	 * 
	 * @param offender offender
	 * @return list of orphaned physical feature photo associations
	 */
	List<PhysicalFeaturePhotoAssociation> findOrphanedPhotoAssications(
			Offender offender);

	/**
	 * Returns the physical feature photo association with the specified
	 * properties excluding the specified physical feature photo association.
	 * 
	 * @param association association
	 * @param photo photo
	 * @param physicalFeaturePhotoAssociation physical feature photo 
	 * association
	 * @return physical feature photo association;
	 * {@code null} if no such physical feature photo association exists
	 */
	PhysicalFeaturePhotoAssociation findExcluding(PhysicalFeatureAssociation 
			association, Photo photo, PhysicalFeaturePhotoAssociation 
			physicalFeaturePhotoAssociation);
}