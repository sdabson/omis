package omis.physicalfeature.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.media.domain.Photo;
import omis.offender.domain.Offender;
import omis.physicalfeature.dao.PhysicalFeaturePhotoAssociationDao;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object
 * for physical feature photo.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeaturePhotoAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<PhysicalFeaturePhotoAssociation>
	implements PhysicalFeaturePhotoAssociationDao {
	
	/* Query names. */
	
	private static final String FIND_PHOTO_BY_OFFENDER_PHYSICAL_FEATURE
		= "findPhotoByOffenderPhysicalFeature";
	
	private static final String 
	FIND_PHYSICAL_FEATURE_PHOTO_ASSOCIATINO_QUERY_NAME
		= "findPhysicalFeaturePhotoAssociation";
	
	private static final String REMOVE_PHYSICAL_FEATURE_EXCLUDING_QUERY_NAME
		= "removeByPhysicalFeatureExcluding";
	
	private static final String FIND_PHYSICAL_FEATURE_PHOTO_ORPHANS_QUERY_NAME
		= "findPhysicalFeaturePhotoOrphans";
	
	private static final String FIND_MATCHING_PHYSICAL_FEATURE_PHOTO_QUERY_NAME
		= "findMatchingPhysicalFeaturePhoto";
	
	private static final String 
	FIND_ORPHANED_PHYSICAL_FEATURE_PHOTO_ASSOCIATIONS_QUERY_NAME 
	= "findOrphanedPhysicalFeaturePhotoAssociations";
	
	private static final String
	FIND_PHYSICAL_FEATURE_PHOTO_ASSOCIATION_EXCLUDING_PARAM_NAME
	= "findPhysicalFeaturePhotoAssociationExcluding";
	
	/* Parameter names. */
	
	private static final String PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME
		= "pFAssociation";
	
	private static final String PHOTO_PARAM_NAME = "photo";
	
	private static final String ASSOCIATIONS_PARAM_NAME = "associations";
	
	private static final String PHOTOS_PARAM_NAME = "photos";
	
	private static final String PERSISTED_PHOTOS_PARAM_NAME = "persistedPhotos";
	
	private static final String FILE_NAME_PARAM_NAME = "filename";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String
	EXCLUDED_PHYSICAL_FEATURE_PHOTO_ASSOCIATION_PARAM_NAME
	= "excludedPFPhotoAssociation";
	
	/**
	 * Instantiates an instance of physical feature photo data access object
	 * hibernate entity implementation with the specified session factory and
	 * entity name.
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PhysicalFeaturePhotoAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation> 
	findByPhysicalFeatureAssociation(
			final PhysicalFeatureAssociation pFAssociation) {
		@SuppressWarnings("unchecked")
		List<PhysicalFeaturePhotoAssociation> photos = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PHOTO_BY_OFFENDER_PHYSICAL_FEATURE)
				.setParameter(PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME,
						pFAssociation)
				.list();
		return photos;
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation find(
			final PhysicalFeatureAssociation pFAssociation, final Photo photo) {
		PhysicalFeaturePhotoAssociation physicalFeaturePhoto = 
				(PhysicalFeaturePhotoAssociation) getSessionFactory()
					.getCurrentSession()
					.getNamedQuery(
							FIND_PHYSICAL_FEATURE_PHOTO_ASSOCIATINO_QUERY_NAME)
					.setParameter(PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME,
							pFAssociation)
					.setParameter(PHOTO_PARAM_NAME, photo)
					.uniqueResult();
		return physicalFeaturePhoto;
	}

	/** {@inheritDoc} */
	@Override
	public void removeAssociationByPhysicalFeatureAssociationExcluding(
			final PhysicalFeatureAssociation pFAssociation,
			final PhysicalFeaturePhotoAssociation... 
				excludingPhotoAssociations) {
		this.getSessionFactory().getCurrentSession()
		.getNamedQuery(REMOVE_PHYSICAL_FEATURE_EXCLUDING_QUERY_NAME)
		.setParameter(PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME, pFAssociation)
		.setParameterList(ASSOCIATIONS_PARAM_NAME, excludingPhotoAssociations)
		.executeUpdate();
	}

	/** {@inheritDoc} */
	@Override
	public List<Photo> findPhysicalFeaturePhotoOrphans(final List<Photo> photos,
			final List<Photo> persistedPhotos) {
		@SuppressWarnings("unchecked")
		List<Photo> orphanedPhotos = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PHYSICAL_FEATURE_PHOTO_ORPHANS_QUERY_NAME)
				.setParameterList(PHOTOS_PARAM_NAME, photos)
				.setParameterList(PERSISTED_PHOTOS_PARAM_NAME, persistedPhotos)
				.list();
		return orphanedPhotos;
	}

	/** {@inheritDoc} */
	@Override
	public Photo findMatchingPhysicalFeaturePhoto(final String filename) {
		Photo photo = (Photo) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_MATCHING_PHYSICAL_FEATURE_PHOTO_QUERY_NAME)
				.setParameter(FILE_NAME_PARAM_NAME, filename)
				.uniqueResult();
		return photo;
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeaturePhotoAssociation> findOrphanedPhotoAssications(
			final Offender offender) {
		@SuppressWarnings("unchecked")
		List<PhysicalFeaturePhotoAssociation> orphanedPhotos = this
				.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_ORPHANED_PHYSICAL_FEATURE_PHOTO_ASSOCIATIONS_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return orphanedPhotos;
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeaturePhotoAssociation findExcluding(
			final PhysicalFeatureAssociation association, final Photo photo,
			final PhysicalFeaturePhotoAssociation 
			physicalFeaturePhotoAssociation) {
		PhysicalFeaturePhotoAssociation physicalFeaturePhoto = 
				(PhysicalFeaturePhotoAssociation) getSessionFactory()
					.getCurrentSession()
					.getNamedQuery(
				FIND_PHYSICAL_FEATURE_PHOTO_ASSOCIATION_EXCLUDING_PARAM_NAME)
					.setParameter(PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME,
							association)
					.setParameter(PHOTO_PARAM_NAME, photo)
					.setParameter(
						EXCLUDED_PHYSICAL_FEATURE_PHOTO_ASSOCIATION_PARAM_NAME, 
							physicalFeaturePhotoAssociation)
					.uniqueResult();
		return physicalFeaturePhoto;
	}
}