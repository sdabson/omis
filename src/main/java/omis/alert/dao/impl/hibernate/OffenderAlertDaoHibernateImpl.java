package omis.alert.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.alert.dao.OffenderAlertDao;
import omis.alert.domain.OffenderAlert;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of offender alert data access object.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.0 (September 13, 2012)
 * @since OMIS 3.0
 * @see OffenderAlert
 * @see OffenderAlertDao
 */
public class OffenderAlertDaoHibernateImpl extends
	GenericHibernateDaoImpl<OffenderAlert> implements
	OffenderAlertDao {

	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findOffenderAlertsByOffender";
	
	private static final String FIND_QUERY_NAME = "findOffenderAlert";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findOffenderAlertExcluding";
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	private static final String EXPIRE_DATE_PARAM_NAME = "expireDate";

	private static final String DESCRIPTION_PARAM_NAME = "description";

	private static final String EXCLUDED_OFFENDER_ALERT_PARAM_NAME
		= "excludedOffenderAlert";

	/**
	 * Instantiates a data access object for offender alerts with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderAlertDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderAlert> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderAlert> offenderAlerts =
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return offenderAlerts;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderAlert find(final Offender offender,
			final Date expireDate, final String description) {
		OffenderAlert offenderAlert = (OffenderAlert) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EXPIRE_DATE_PARAM_NAME, expireDate)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		return offenderAlert;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderAlert findExcluding(final Offender offender,
			final Date expireDate, final String description,
			final OffenderAlert excludedOffenderAlert) {
		OffenderAlert offenderAlert = (OffenderAlert) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(EXPIRE_DATE_PARAM_NAME, expireDate)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(EXCLUDED_OFFENDER_ALERT_PARAM_NAME,
						excludedOffenderAlert)
				.uniqueResult();
		return offenderAlert;
	}
}