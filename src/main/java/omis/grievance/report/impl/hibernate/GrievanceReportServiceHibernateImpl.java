package omis.grievance.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.report.GrievanceReportService;
import omis.grievance.report.GrievanceSummary;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSummary;
import omis.offender.report.delegate.hibernate.OffenderReportDelegate;
import omis.util.StringUtility;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of report service for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
public class GrievanceReportServiceHibernateImpl
		implements GrievanceReportService {
	
	/* Query names. */
	
	private static final String SUMMARIZE_BY_OFFENDER_QUERY_NAME
		= "summarizeGrievancesByOffender";
	
	private static final String SUMMARIZE_BY_LOCATION_QUERY_NAME
		= "summarizeGrievancesByLocation";
	
	private static final String FIND_GRIEVANCE_LOCATIONS_QUERY_NAME
		= "findGrievanceLocations";
	
	private static final String FIND_GRIEVANCE_SUBJECTS_QUERY_NAME
		= "findGrievanceSubjects";
	
	/* Parameters */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String LOCATION_PARAM_NAME = "location";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/* Delegates. */
	
	private final OffenderReportDelegate offenderReportDelegate;
	
	/* Resources. */
	
	private final SessionFactory sessionFactory;

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of report service for grievances.
	 * 
	 * @param sessionFactory session factory
	 * @param offenderReportDelegate delegate for offender reports
	 */
	public GrievanceReportServiceHibernateImpl(
			final SessionFactory sessionFactory,
			final OffenderReportDelegate offenderReportDelegate) {
		this.sessionFactory = sessionFactory;
		this.offenderReportDelegate = offenderReportDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceSummary> summarizeByOffender(
			final Offender offender, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<GrievanceSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceSummary> summarizeByLocation(
			final GrievanceLocation location, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<GrievanceSummary> summaries
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_LOCATION_QUERY_NAME)
				.setParameter(LOCATION_PARAM_NAME, location)
				.setTimestamp(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSummary> searchOffenders(final String query) {
		if (StringUtility.isIntegral(query)) {
			return this.offenderReportDelegate.summarizeByOffenderNumber(
					Integer.valueOf(query));
		} else {
			return this.offenderReportDelegate.summarizeByNameQuery(query);
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceLocation> findGrievanceLocations() {
		@SuppressWarnings("unchecked")
		List<GrievanceLocation> locations
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_GRIEVANCE_LOCATIONS_QUERY_NAME)
				.setReadOnly(true)
				.list();
		return locations;
	}

	/** {@inheritDoc} */
	@Override
	public List<GrievanceSubject> findGrievanceSubjects() {
		@SuppressWarnings("unchecked")
		List<GrievanceSubject> subjects
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_GRIEVANCE_SUBJECTS_QUERY_NAME)
				.setReadOnly(true)
				.list();
		return subjects;
	}
}