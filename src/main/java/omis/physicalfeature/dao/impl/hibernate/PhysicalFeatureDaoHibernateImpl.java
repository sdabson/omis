package omis.physicalfeature.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.physicalfeature.dao.PhysicalFeatureDao;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object
 * for physical feature.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class PhysicalFeatureDaoHibernateImpl 
	extends GenericHibernateDaoImpl<PhysicalFeature>
	implements PhysicalFeatureDao {

	/**
	 * Instantiates an instance of physical feature data access object
	 * hibernate entity implementation with the specified session factory
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PhysicalFeatureDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeature> findAll() {
		@SuppressWarnings("unchecked")
		List<PhysicalFeature> features = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findPhysicalFeatures")
				.list();
		return features;
	}

	/** {@inheritDoc} */
	@Override
	public List<PhysicalFeature> findPhysicalFeatureByClassification(
			final FeatureClassification featureClassification) {
		@SuppressWarnings("unchecked")
		List<PhysicalFeature> features = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findPhysicalFeatureByClassification")
				.setParameter("featureClassification", featureClassification)
				.list();
		return features;
	}
}