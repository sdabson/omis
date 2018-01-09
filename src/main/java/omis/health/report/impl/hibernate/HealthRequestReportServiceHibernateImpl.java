package omis.health.report.impl.hibernate;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.HealthRequestStatus;
import omis.health.report.HealthRequestReportService;
import omis.health.report.HealthRequestSummary;
import omis.health.report.delegate.UnitLookUpDelegate;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of service to report health requests.
 *
 * @author Stephen Abson
 * @version 0.1.0 (Apr 13, 2014)
 * @since OMIS 3.0
 */
public class HealthRequestReportServiceHibernateImpl
		implements HealthRequestReportService {

	/* Query names. */

	private static final String FIND_BY_CATEGORY_AND_STATUS_QUERY_NAME
		= "findHealthRequestSummariesByStatusAndCategory";

	private static final String FIND_BY_STATUS_QUERY_NAME
		= "findHealthRequestSummariesByStatus";
	
	private static final String FIND_BY_OFFENDER_STATUS_QUERY_NAME
		= "findHealthRequestSummariesByOffenderStatus";

	private static final String FIND_BY_CATEGORY_EXCLUDING_STATUS_QUERY_NAME
		= "findHealthRequestSummariesByCategoryExcludingStatus";

	private static final String FIND_BY_EXCLUDING_STATUS_QUERY_NAME
		= "findHealthRequestSummariesExcludingStatus";

	private static final String FIND_BY_CATEGORY_QUERY_NAME
		= "findHealthRequestSummariesByCategory";

	private static final String FIND_QUERY_NAME
		= "findHealthRequestSummaries";
	
	/* Parameter names. */

	private static final String FACILITY_PARAM_NAME = "facility";

	private static final String CATEGORIES_PARAM_NAME = "categories";

	private static final String STATUS_PARAM_NAME = "status";

	private static final String OFFENDER_PARAM_NAME = "offender";

	/* Members. */

	private final SessionFactory sessionFactory;
	
	private final UnitLookUpDelegate unitLookUpDelegate;

	/* Constructor. */

	/**
	 * Instantiates Hibernate implementation of service to report
	 * health requests.
	 *
	 * @param unitLookUpDelegate delegate to look up units
	 * @param sessionFactory session factory
	 */
	public HealthRequestReportServiceHibernateImpl(
			final SessionFactory sessionFactory,
			final UnitLookUpDelegate unitLookUpDelegate) {
		this.sessionFactory = sessionFactory;
		this.unitLookUpDelegate = unitLookUpDelegate;
	}

	/* Implementation. */

	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> findOpenByCategory(
			final Facility facility, final Date effectiveDate,
			final HealthRequestCategory...categories) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_BY_CATEGORY_AND_STATUS_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(STATUS_PARAM_NAME, HealthRequestStatus.OPEN)
				.setParameterList(CATEGORIES_PARAM_NAME, categories).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> findOpen(final Facility facility,
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(FIND_BY_STATUS_QUERY_NAME)
				.setParameter(STATUS_PARAM_NAME, HealthRequestStatus.OPEN)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> findOpenByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_STATUS_QUERY_NAME)
				.setParameter(STATUS_PARAM_NAME, HealthRequestStatus.OPEN)
				.setParameter(OFFENDER_PARAM_NAME, offender).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> findResolvedByCategory(
			final Facility facility,
			final Date effectiveDate,
			final HealthRequestCategory... categories) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_BY_CATEGORY_EXCLUDING_STATUS_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameter(STATUS_PARAM_NAME, HealthRequestStatus.OPEN)
				.setParameterList(CATEGORIES_PARAM_NAME, categories).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> findResolved(final Facility facility,
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_BY_EXCLUDING_STATUS_QUERY_NAME)
				.setParameter(STATUS_PARAM_NAME, HealthRequestStatus.OPEN)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> findByCategory(
			final Facility facility,
			final Date effectiveDate,
			final HealthRequestCategory... categories) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(
						FIND_BY_CATEGORY_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility)
				.setParameterList(CATEGORIES_PARAM_NAME, categories).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<HealthRequestSummary> find(final Facility facility,
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		final List<HealthRequestSummary> summaries = this.sessionFactory
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(FACILITY_PARAM_NAME, facility).list();
		addUnits(summaries, effectiveDate);
		return summaries;
	}
	
	// Looks up and adds unit information
	private void addUnits(final List<HealthRequestSummary> summaries,
			final Date effectiveDate) {
		Field field;
		try {
			field = HealthRequestSummary.class.getDeclaredField("unitName");
		} catch (SecurityException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		field.setAccessible(true);
		for (HealthRequestSummary summary : summaries) {
			String unitName = this.unitLookUpDelegate
					.findUnitAbbreviationByOffenderNumber(
							summary.getOffenderNumber(),
							effectiveDate);
			try {
				field.set(summary, unitName);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}
}