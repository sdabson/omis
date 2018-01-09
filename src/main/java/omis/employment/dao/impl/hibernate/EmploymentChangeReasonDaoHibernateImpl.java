package omis.employment.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.employment.dao.EmploymentChangeReasonDao;
import omis.employment.domain.EmploymentChangeReason;

import org.hibernate.SessionFactory;

/**
 * Employment change reason data access object hibernate implementation..
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.2 (Dec 14, 2017)
 * @since OMIS 3.0
 */

public class EmploymentChangeReasonDaoHibernateImpl 
	extends GenericHibernateDaoImpl<EmploymentChangeReason> 
	implements EmploymentChangeReasonDao  {
	/* Query names. */
	
	private static final String FIND_EMPLOYMENT_CHANGE_REASONS = 
			"findEmploymentChangeReasons";
	
	private static final String FIND_QUERY_NAME = 
			"findEmploymentChangeReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findEmploymentChangeReasonExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_CHANGE_REASON_PARAM_NAME = 
			"excludedChangeReason";
	
	/* Constructor. */
	
	/**
	 * Instantiates a Hibernate implementation of data access object for 
	 * employment change reason with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EmploymentChangeReasonDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EmploymentChangeReason> findEmploymentChangeReasons( ) {
		@SuppressWarnings("unchecked")
		List<EmploymentChangeReason> endReasons=getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EMPLOYMENT_CHANGE_REASONS)
			.list();
		return endReasons;
	}

	/** {@inheritDoc} */
	@Override
	public EmploymentChangeReason find(final String name) {
		EmploymentChangeReason employmentChangeReason = (EmploymentChangeReason)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return employmentChangeReason;
	}

	/** {@inheritDoc} */
	@Override
	public EmploymentChangeReason findExcluding(final String name, 
			final EmploymentChangeReason excludedChangeReason) {
		EmploymentChangeReason employmentChangeReason = (EmploymentChangeReason)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_CHANGE_REASON_PARAM_NAME, 
						excludedChangeReason)
				.uniqueResult();
		return employmentChangeReason;
	}
}
