package omis.health.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.ProviderSpecialtyDao;
import omis.health.domain.ProviderSpecialty;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for provider specialties.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class ProviderSpecialtyDaoHibernateImpl 
		extends GenericHibernateDaoImpl<ProviderSpecialty>
		implements ProviderSpecialtyDao {
				
	/**
	 * Instantiates a hibernate implementation of data access object for 
	 * provider specialties.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name	
	 */
	public ProviderSpecialtyDaoHibernateImpl(
				final SessionFactory sessionFactory,
					final String entityName) { super(sessionFactory, 
							entityName);
	}
}
