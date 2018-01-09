package omis.stg.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;
import omis.stg.dao.SecurityThreatGroupActivityDao;
import omis.stg.domain.SecurityThreatGroupActivity;

/**
 * Hibernate implementation of data access object for security threat group
 * activity.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 28, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityDaoHibernateImpl 
		extends GenericHibernateDaoImpl<SecurityThreatGroupActivity>
		implements SecurityThreatGroupActivityDao {
	
	/* Query names. */
	
	private static final String FIND_QUERY_NAME 
		= "findSecurityThreatGroupActivity";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findExcludingActivity";
	
	private static final String FIND_ALL_QUERY_NAME = "findAll";

	/* Parameter names. */
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String REPORTED_BY_PARAM_NAME = "reportedBy";
	
	private static final String SUMMARY_PARAM_NAME = "summary";
	
	private static final String EXCLUDED_ACTIVITY_PARAM_NAME 
		= "excludedActivity";
	
	/* Constructor.*/
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * security threat group activity.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SecurityThreatGroupActivityDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivity> findAll() {
		@SuppressWarnings("unchecked")
		List<SecurityThreatGroupActivity> activity
			= this.getSessionFactory().getCurrentSession().getNamedQuery(
					FIND_ALL_QUERY_NAME).list();
		return activity;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivity find(
			final Date date, 
			final Person reportedBy, 
			final String summary) {
		SecurityThreatGroupActivity activity = (SecurityThreatGroupActivity)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(REPORTED_BY_PARAM_NAME, reportedBy)
				.setParameter(SUMMARY_PARAM_NAME, summary)
				.uniqueResult();
		return activity;
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupActivity findExcluding(
			final SecurityThreatGroupActivity excludedActivity, 
			final Date date,
			final Person reportedBy, 
			final String summary) {
		SecurityThreatGroupActivity activity = (SecurityThreatGroupActivity)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(EXCLUDED_ACTIVITY_PARAM_NAME, excludedActivity)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(REPORTED_BY_PARAM_NAME, reportedBy)
				.setParameter(SUMMARY_PARAM_NAME, summary)
				.uniqueResult();
		return activity;
	}

}
