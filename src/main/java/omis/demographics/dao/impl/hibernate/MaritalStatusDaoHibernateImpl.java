package omis.demographics.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.demographics.dao.MaritalStatusDao;
import omis.demographics.domain.MaritalStatus;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * marital statuses.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public class MaritalStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<MaritalStatus>
		implements MaritalStatusDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findMaritalStatuses";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation data access object for marital
	 * statuses.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MaritalStatusDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<MaritalStatus> findAll() {
		@SuppressWarnings("unchecked")
		List<MaritalStatus> maritalStatuses = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return maritalStatuses;
	}
}