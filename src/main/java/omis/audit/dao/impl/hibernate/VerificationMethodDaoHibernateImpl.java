package omis.audit.dao.impl.hibernate;

import java.util.List;

import omis.audit.dao.VerificationMethodDao;
import omis.audit.domain.VerificationMethod;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for verification method.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Feb 7, 2013)
 * @since OMIS 3.0
 */
public class VerificationMethodDaoHibernateImpl
		extends GenericHibernateDaoImpl<VerificationMethod>
		implements VerificationMethodDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "verificationName";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * verification methods.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VerificationMethodDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VerificationMethod> findAll() {
		@SuppressWarnings("unchecked")
		List<VerificationMethod> methods = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						"findVerificationMethods")
				.list();
		return methods;
	}

	@Override
	public VerificationMethod find(String name, Boolean valid) {
		VerificationMethod verification = (VerificationMethod) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return verification;
	}
	
}