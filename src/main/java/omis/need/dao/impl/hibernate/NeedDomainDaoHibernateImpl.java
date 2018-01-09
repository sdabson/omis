package omis.need.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.need.dao.NeedDomainDao;
import omis.need.domain.NeedDomain;

import org.hibernate.SessionFactory;

/**
 * Need Domain data access object hibernate implementation.
 * @author Kelly Churchill
 * @author Joel Norris
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class NeedDomainDaoHibernateImpl 
	extends GenericHibernateDaoImpl<NeedDomain> 
	implements NeedDomainDao {

	/* Query names. */
	
	private static final String FIND_NEED_DOMAINS_QUERY_NAME
		= "findNeedDomains";
	
	/**
	 * Instantiates an instance of criminogenic domain data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public NeedDomainDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<NeedDomain> findAll() {
		@SuppressWarnings("unchecked")
		List<NeedDomain> criminogenicDomain = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_NEED_DOMAINS_QUERY_NAME)
				.list();
		return criminogenicDomain;
	}
}