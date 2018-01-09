package omis.immigration.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.immigration.dao.ImmigrationStatusDao;
import omis.immigration.domain.ImmigrationStatus;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * immigration statuses.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public class ImmigrationStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<ImmigrationStatus>
		implements ImmigrationStatusDao {

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * immigration statuses with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ImmigrationStatusDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
}