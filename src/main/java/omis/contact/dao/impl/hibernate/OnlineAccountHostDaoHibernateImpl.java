package omis.contact.dao.impl.hibernate;

import java.util.List;

import omis.contact.dao.OnlineAccountHostDao;
import omis.contact.domain.OnlineAccountHost;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Online account host data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (July 29, 2015)
 * @since OMIS 3.0
 */

public class OnlineAccountHostDaoHibernateImpl 
	extends GenericHibernateDaoImpl<OnlineAccountHost> 
	implements OnlineAccountHostDao {
	/* Query names. */
	private static final String FIND_ONLINE_ACCOUNT_HOSTS_QUERY_NAME 
		= "findOnlineAccountHosts";

	/**
	 * Instantiates an instance of online account host data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OnlineAccountHostDaoHibernateImpl(final SessionFactory sessionFactory, 
		final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<OnlineAccountHost> find(){
		List<OnlineAccountHost> hosts;
		hosts = (List<OnlineAccountHost>) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ONLINE_ACCOUNT_HOSTS_QUERY_NAME)
			.list();
		return hosts;
	}
}



