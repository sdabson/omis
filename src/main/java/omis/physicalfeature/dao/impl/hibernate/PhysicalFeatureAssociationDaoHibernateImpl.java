package omis.physicalfeature.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.physicalfeature.dao.PhysicalFeatureAssociationDao;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object
 * for offender physical feature association.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<PhysicalFeatureAssociation>
	implements PhysicalFeatureAssociationDao {
	
	/* Parameter names */
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String FEATURE_PARAM_NAME = "feature";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String EXCLUDED_ASSOCIATION_PARAM_NAME
		= "excludedPhysicalFeatureAssociation";
	
	/* Query names */
	
	private static final String FIND_QUERY_NAME
		= "findPhysicalFeatureAssociation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findPhysicalFeatureAssociationExcluding";
	
	private static final String FIND_FEATURES_BY_OFFENDER_QUERY_NAME 
		= "findOffenderPhysicalFeaturesByOffender";

	/**
	 * Instantiates an instance of offender physical feature data access object
	 * hibernate entity implementation with the specified session factory
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PhysicalFeatureAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeatureAssociation> 
	findPhysicalFeatureAssociationsByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<PhysicalFeatureAssociation> features = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FEATURES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return features;
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociation find(final String description, 
			final PhysicalFeature feature, final Offender offender) {
		PhysicalFeatureAssociation association = (PhysicalFeatureAssociation)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(FEATURE_PARAM_NAME, feature)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public PhysicalFeatureAssociation findExcluding(final String description,
			final PhysicalFeature feature, final Offender offender,
			final PhysicalFeatureAssociation physicalFeatureAssociation) {
		PhysicalFeatureAssociation association = (PhysicalFeatureAssociation)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(FEATURE_PARAM_NAME, feature)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EXCLUDED_ASSOCIATION_PARAM_NAME,
						physicalFeatureAssociation)
				.uniqueResult();
		return association;
	}
}