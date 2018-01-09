package omis.physicalfeature.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.physicalfeature.dao.FeatureClassificationDao;
import omis.physicalfeature.domain.FeatureClassification;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object
 * for feature classification.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class FeatureClassificationDaoHibernateImpl
	extends GenericHibernateDaoImpl<FeatureClassification>
	implements FeatureClassificationDao {

	/**
	 * Instantiates an instance of feature classification data access object
	 * hibernate entity implementation with the specified session factory 
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FeatureClassificationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<FeatureClassification> findValid() {
		@SuppressWarnings("unchecked")
		List<FeatureClassification> featureClassifications = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery("findValidFeatureClassification")
			.list();
		return featureClassifications;
	}
}