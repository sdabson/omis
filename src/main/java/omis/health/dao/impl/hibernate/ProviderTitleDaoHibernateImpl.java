package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderTitleDao;
import omis.health.domain.ProviderTitle;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for provider titles.
 *  
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 13, 2014)
 * @since OMIS 3.0
 */
public class ProviderTitleDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ProviderTitle>
		implements ProviderTitleDao {

	/**
	 * Instantiates a hibernate implementation of data access object for
	 * provider titles.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProviderTitleDaoHibernateImpl(final SessionFactory sessionFactory,
					final String entityName) { super(sessionFactory, 
							entityName);
	}
}
