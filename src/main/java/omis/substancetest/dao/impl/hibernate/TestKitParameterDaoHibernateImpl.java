package omis.substancetest.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substancetest.dao.TestKitParameterDao;
import omis.substancetest.domain.TestKit;
import omis.substancetest.domain.TestKitParameter;

import org.hibernate.SessionFactory;

/**
 * Test Kit Parameter DAO Hibernate Impl.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public class TestKitParameterDaoHibernateImpl  
	extends GenericHibernateDaoImpl<TestKitParameter>
	implements TestKitParameterDao {
	
	/**
	 * Instantiates a data access object for test kit parameter with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public TestKitParameterDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<TestKitParameter> findByTestKit(final TestKit testKit) {
		@SuppressWarnings("unchecked")
		List<TestKitParameter> testKitParameters = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("findParametersByKit")
				.setParameter("testKit", testKit)
				.list();
		return testKitParameters;
	}
}