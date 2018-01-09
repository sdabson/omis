package omis.visitation.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.person.report.AlternateNameSummary;
import omis.visitation.report.VisitationAssociationSummary;
import omis.visitation.report.VisitationAssociationSummaryReportService;

import org.hibernate.SessionFactory;

/**
 * Report service implementation for visitor list.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 24, 2013)
 * @since OMIS 3.0
 */
public class VisitationAssociationSummaryReportServiceHibernateImpl 
	implements VisitationAssociationSummaryReportService {
	/* Query Names */
	
	private static final String SUMMARIZE_VISITOR_LIST_BY_OFFENDER_QUERY_NAME =
			"summarizeVisitorListByOffender";
	
	private static final String
		SUMMARIZE_VISITATION_ASSOCIATIONS_BY_OFFENDER_IN_RANGE_QUERY_NAME
		= "summarizeVisitationAssociationsByOffenderInRange";
	
	private static final String SUMMARIZE_VISITOR_LIST_BY_FACILITY_QUERY_NAME =
			"summarizeVisitorListByFacility";
	
	private static final String FIND_ALTERNATIVE_NAMES_QUERY_NAME
		= "findAlternativeNames";
	
	/* Parameters */
	
	private static final String OFFENDER_PARAM = "offender";
	
	private static final String DATE_PARAM = "date";
	
	private static final String FACILITY_PARAM = "facility";
	
	private static final String START_DATE_PARAM = "startDate";
	
	private static final String END_DATE_PARAM = "endDate";
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private SessionFactory sessionFactory;
	
	/**
	 * Instantiates a default instance of visitor list report service
	 * hibernate implementation.
	 */
	public VisitationAssociationSummaryReportServiceHibernateImpl() {
		//Default constructor.
	}

	/**
	 * Return the session factory.
	 * @return the sessionFactory
	 */
	public final SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	/**
	 * Set the session factory.
	 * @param sessionFactory the sessionFactory to set
	 */
	public final void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociationSummary> summarizeVisitationAssociations(
			final Offender offender, final Date date) {
		@SuppressWarnings("unchecked")
		List<VisitationAssociationSummary> visitorSummaries = (List<VisitationAssociationSummary>)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(SUMMARIZE_VISITOR_LIST_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM, offender)
				.setTimestamp(DATE_PARAM, date)
				.list();
		return visitorSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociationSummary> summarizeVisitationAssociationsByFacility(
			final Facility facility, final Date dateTime) {
		@SuppressWarnings("unchecked")
		List<VisitationAssociationSummary> visitorSummaries = (List<VisitationAssociationSummary>)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(SUMMARIZE_VISITOR_LIST_BY_FACILITY_QUERY_NAME)
				.setParameter(FACILITY_PARAM, facility)
				.setTimestamp(DATE_PARAM, dateTime)
				.list();
		return visitorSummaries;
	}

	@Override
	public List<VisitationAssociationSummary>
		summarizeVisitationAssociationsInRange(final Offender offender,
				final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
	List<VisitationAssociationSummary> visitorSummaries 
			= (List<VisitationAssociationSummary>)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
			SUMMARIZE_VISITATION_ASSOCIATIONS_BY_OFFENDER_IN_RANGE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM, offender)
				.setTimestamp(START_DATE_PARAM, startDate)
				.setTimestamp(END_DATE_PARAM, endDate)
				.list();
		return visitorSummaries;
	}
	
	@Override
	public List<AlternateNameSummary>  summarizeAlternativeNames(
		final Person person, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<AlternateNameSummary> alternativeNameSummaries
		= this.sessionFactory.getCurrentSession()
		.getNamedQuery(FIND_ALTERNATIVE_NAMES_QUERY_NAME)
		.setParameter(PERSON_PARAM_NAME, person)
		.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
		.list();
		return alternativeNameSummaries;
	}
}