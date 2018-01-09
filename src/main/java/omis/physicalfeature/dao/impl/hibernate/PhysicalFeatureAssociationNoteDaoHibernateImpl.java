package omis.physicalfeature.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.physicalfeature.dao.PhysicalFeatureAssociationNoteDao;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;

/**
 * Physical feature association note data access object hibernate
 * implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationNoteDaoHibernateImpl 
	extends GenericHibernateDaoImpl<PhysicalFeatureAssociationNote>
	implements PhysicalFeatureAssociationNoteDao {

	/* Query names. */
	
	private static final String 
	FIND_NOTES_BY_PHYSICAL_FEATURE_ASSOCIATION_QUERY_NAME
		= "findNotesByPhysicalFeatureAssociation";
	
	/* Parameter names. */
	
	private static final String PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME
		= "featureAssociation";
	
	/**
	 * Instantiates an instance of physical feature association note data
	 * access object with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PhysicalFeatureAssociationNoteDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName); 
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeatureAssociationNote> findNotesByPhysicalFeatureAssociation(
			PhysicalFeatureAssociation physicalFeatureAssociation) {
		@SuppressWarnings("unchecked")
		List<PhysicalFeatureAssociationNote> notes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_NOTES_BY_PHYSICAL_FEATURE_ASSOCIATION_QUERY_NAME)
				.setParameter(PHYSICAL_FEATURE_ASSOCIATION_PARAM_NAME,
						physicalFeatureAssociation)
				.list();
		return notes;
	}
}