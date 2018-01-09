package omis.military.report.impl.hibernate;

import java.util.List;

import omis.military.domain.MilitaryServiceTerm;
import omis.military.report.MilitaryReportService;
import omis.military.report.MilitaryServiceTermNoteSummary;
import omis.military.report.MilitaryServiceTermSummary;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Military report service hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class MilitaryReportServiceHibernateImpl 
implements MilitaryReportService {

	/* Session factory. */
	
	private final SessionFactory sessionFactory;
	
	/* Query names. */
	
	private static final String 
	FIND_SERVICE_TERM_SUMMARIES_BY_OFFENDER_QUERY_NAME
		= "findServiceTermSummariesByOffender";
	
	private static final String FIND_NOTE_SUMMARIES_BY_SERVICE_TERM_QUERY_NAME
		= "findMilitaryServiceTermNoteSummariesByMilitaryServiceTerm";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String SERVICE_TERM_PARAM_NAME = "serviceTerm";
	
	/* Constructor. */
	
	/**
	 * Instantiates a military report service with the specified session
	 * factory.
	 * 
	 * @param sessionFactory session factory
	 */
	public MilitaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTermSummary> 
	findMilitaryServiceTermSummariesByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<MilitaryServiceTermSummary> serviceTermSummaries =
				this.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_SERVICE_TERM_SUMMARIES_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		return serviceTermSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryServiceTermNoteSummary> 
	findMilitaryServiceTermNotesByMilitaryServiceTerm(
			final MilitaryServiceTerm serviceTerm) {
		@SuppressWarnings("unchecked")
		List<MilitaryServiceTermNoteSummary> noteSummaries 
			= this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_NOTE_SUMMARIES_BY_SERVICE_TERM_QUERY_NAME)
				.setParameter(SERVICE_TERM_PARAM_NAME, serviceTerm)
				.list();
		return noteSummaries;
	}
}