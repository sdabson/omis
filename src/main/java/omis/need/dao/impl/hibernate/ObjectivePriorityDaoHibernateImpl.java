package omis.need.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.need.dao.ObjectivePriorityDao;
import omis.need.domain.ObjectivePriority;

import org.hibernate.SessionFactory;

/**
 * Objective priority data access object hibernate implementation.
 * @author Kelly Churchill
 * @version 0.1.0 (July 2, 2015)
 * @since OMIS 3.0
 */
public class ObjectivePriorityDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ObjectivePriority> 
	implements ObjectivePriorityDao {

	private static final String FIND_PRIORITIES_QUERY_NAME
	= "findObjectivePriorities";

	/**
	 * Instantiate an instance of objective priorities with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ObjectivePriorityDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ObjectivePriority> findAll() {
		@SuppressWarnings("unchecked")
		List<ObjectivePriority> priorities = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_PRIORITIES_QUERY_NAME)
				.list();
		return priorities;
	}
}