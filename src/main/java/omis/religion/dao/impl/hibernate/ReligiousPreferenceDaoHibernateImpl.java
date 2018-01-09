package omis.religion.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.religion.dao.ReligiousPreferenceDao;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousPreference;

/**
 * Hibernate implementation of data access object for religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceDaoHibernateImpl
		extends GenericHibernateDaoImpl<ReligiousPreference>
		implements ReligiousPreferenceDao {

	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findReligiousPreferencesByOffender";
	
	private static final String FIND_BY_OFFENDER_ON_DATE_QUERY_NAME
		= "findReligiousPreferenceByOffenderOnDate";
	
	private static final String FIND_BY_OFFENDER_ON_DATE_EXCLUDING_QUERY_NAME
		= "findReligiousPreferenceByOffenderOnDateExcluding";
	
	private static final String COUNT_BY_OFFENDER_IN_DATES_EXCLUDING_QUERY_NAME
		= "countReligiousPreferencesByOffenderInDateRangeExcluding";

	private static final String COUNT_BY_OFFENDER_IN_DATES_QUERY_NAME
		= "countReligiousPreferencesByOffenderInDateRange";

	private static final String COUNT_QUERY_NAME = "countReligiousPreferences";
	
	private static final String COUNT_EXCLUDING_QUERY_NAME
		= "countReligiousPreferencesExcluding";
	
	private static final String COUNT_START_DATE_CHANGES_QUERY_NAME
		= "countReligiousPreferenceStartDateChangesByOffenderInDateRange";

	private static final String COUNT_START_DATE_CHANGES_EXCLUDING_QUERY_NAME
	= "countReligiousPreferenceStartDateChangesByOffenderInDateRangeExcluding";

	private static final String COUNT_END_DATE_CHANGES_QUERY_NAME
		= "countReligiousPreferenceEndDateChangesByOffenderInDateRange";

	private static final String COUNT_END_DATE_CHANGES_EXCLUDING_QUERY_NAME
	= "countReligiousPreferenceEndDateChangesByOffenderInDateRangeExcluding";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String EXCLUDED_PREFERENCES_PARAM_NAME
		= "excludedPreferences";

	private static final String RELIGION_PARAM_NAME = "religion";
	
	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * religious preferences.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ReligiousPreferenceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public List<ReligiousPreference> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<ReligiousPreference> preferences = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		return preferences;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousPreference findByOffenderOnDate(
			final Offender offender, final Date date) {
		ReligiousPreference preference = (ReligiousPreference)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(DATE_PARAM_NAME, date).uniqueResult();
		return preference;
	}
	
	/** {@inheritDoc} */
	@Override
	public ReligiousPreference findByOffenderOnDateExcluding(
			final Offender offender, final Date date,
			final ReligiousPreference... excludedPreferences) {
		ReligiousPreference preference = (ReligiousPreference)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_ON_DATE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameterList(EXCLUDED_PREFERENCES_PARAM_NAME,
						excludedPreferences)
				.setParameter(DATE_PARAM_NAME, date).uniqueResult();
		return preference;
	}

	/** {@inheritDoc} */
	@Override
	public long  countByOffenderOnDateExcluding(final Offender offender,
			final Date startDate, final Date endDate,
			final ReligiousPreference... excludedPreferences) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_BY_OFFENDER_IN_DATES_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameterList(EXCLUDED_PREFERENCES_PARAM_NAME,
						excludedPreferences)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public long countByOffenderOnDate(final Offender offender,
			final Date startDate, final Date endDate) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_BY_OFFENDER_IN_DATES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public boolean exists(final Offender offender, final Religion religion,
			final Date startDate, final Date endDate) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(RELIGION_PARAM_NAME, religion)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result > 0;
	}

	/** {@inheritDoc} */
	@Override
	public boolean existsExcluding(final Offender offender,
			final Religion religion, final Date startDate, final Date endDate,
			final ReligiousPreference... excludedPreferences) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(RELIGION_PARAM_NAME, religion)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setParameterList(EXCLUDED_PREFERENCES_PARAM_NAME,
						excludedPreferences)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result > 0;
	}

	/** {@inheritDoc} */
	@Override
	public long countStartDateChanges(final Offender offender,
			final Date startDate, final Date endDate) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_START_DATE_CHANGES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public long countStartDateChangesExcluding(final Offender offender,
			final Date startDate, final Date endDate,
			final ReligiousPreference... excludedPreferences) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_START_DATE_CHANGES_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameterList(EXCLUDED_PREFERENCES_PARAM_NAME,
						excludedPreferences)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public long countEndDateChanges(final Offender offender,
			final Date startDate, final Date endDate) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_END_DATE_CHANGES_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public long countEndDateChangesExcluding(final Offender offender,
			final Date startDate, final Date endDate,
			final ReligiousPreference... excludedPreferences) {
		long result = (Long) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						COUNT_END_DATE_CHANGES_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameterList(EXCLUDED_PREFERENCES_PARAM_NAME,
						excludedPreferences)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate).uniqueResult();
		return result;
	}
}