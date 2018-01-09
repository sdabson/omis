package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.SampleCollectionMethodDao;
import omis.substancetest.domain.SampleCollectionMethod;

import org.hibernate.SessionFactory;

/**
 * Sample Collection Method DAO Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public class SampleCollectionMethodDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SampleCollectionMethod>
	implements SampleCollectionMethodDao {

	/**
	 * Instantiates a data access object for sample collection method with 
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SampleCollectionMethodDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<SampleCollectionMethod> findValid() {
		@SuppressWarnings("unchecked")
		List<SampleCollectionMethod> validMethods = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findValidSampleCollectionMethod")
				.list();
		return validMethods;
	}
}