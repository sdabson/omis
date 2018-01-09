package omis.paroleboarditinerary.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboarditinerary.report.BoardAttendeeSummary;
import omis.paroleboarditinerary.report.ParoleBoardItinerarySummary;
import omis.paroleboarditinerary.report.ParoleBoardItinerarySummaryReportService;

/**
 * Hibernate implementation of the parole board itinerary summary report 
 * service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 21, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItinerarySummaryReportServiceHibernateImpl 
		implements ParoleBoardItinerarySummaryReportService {

	/* Queries. */
	
	private static final String FIND_BOARD_ITINERARIES_BY_DATE_QUERY_NAME =
			"findParoleBoardItinerariesByEffectiveDate";
	
	private static final String FIND_BOARD_ITINERARIES_BY_DATE_RANGE_QUERY_NAME 
			= "findParoleBoardItinerariesByDateRange";
	
	private static final String SUMMARIZE_BY_BOARD_ITINERARY_QUERY_NAME = 
			"findParoleBoardItinerarySummaryByParoleBoardItinerary";
	
	private static final String 
			FIND_BOARD_ATTENDEES_BY_BOARD_ITINERARY_QUERY_NAME =
					"findParoleBoardAttendeeSummariesByParoleBoardItinerary";
	
	/* Parameters.*/ 
	
	private static final String EFFECTIVE_DATE_PARAMETER_NAME = "effectiveDate";
	
	private static final String START_DATE_PARAMETER_NAME = "startDate";
	
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	
	private static final String BOARD_ITINERARY_PARAMETER_NAME =
			"boardItinerary";
	
	/* Members. */
	
	private final SessionFactory sessionFactory;
	
	/**
	 * Constructor.
	 *
	 * @param sessionFactory session factory
	 */
	public ParoleBoardItinerarySummaryReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardItinerary> findParoleBoardItineraryByEffectiveDate(
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardItinerary> itineraries = 
				(List<ParoleBoardItinerary>) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BOARD_ITINERARIES_BY_DATE_QUERY_NAME)
				.setTimestamp(EFFECTIVE_DATE_PARAMETER_NAME, effectiveDate)
				.list();
		return itineraries;
	}

	/** {@inheritDoc} */
	@Override
	public List<ParoleBoardItinerary> findParoleBoardItineraryByDateRange(
			final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<ParoleBoardItinerary> itineraries = 
				(List<ParoleBoardItinerary>) this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BOARD_ITINERARIES_BY_DATE_RANGE_QUERY_NAME)
				.setTimestamp(START_DATE_PARAMETER_NAME, startDate)
				.setTimestamp(END_DATE_PARAMETER_NAME, endDate)
				.list();
		return itineraries;
	}
	
	/** {@inheritDoc} */
	@Override
	public ParoleBoardItinerarySummary summarize(
			final ParoleBoardItinerary paroleBoardItinerary) {
		ParoleBoardItinerarySummary summary = (ParoleBoardItinerarySummary) this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(SUMMARIZE_BY_BOARD_ITINERARY_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAMETER_NAME, 
						paroleBoardItinerary)
				.uniqueResult();
		return summary;
	}

	/** {@inheritDoc} */
	@Override
	public List<BoardAttendeeSummary> 
			findParoleBoardAttendeeSummariesByParoleBoardItinerary(
					final ParoleBoardItinerary paroleBoardItinerary) {
		@SuppressWarnings("unchecked")
		List<BoardAttendeeSummary> summaries = (List<BoardAttendeeSummary>) this
				.sessionFactory.getCurrentSession()
				.getNamedQuery(
						FIND_BOARD_ATTENDEES_BY_BOARD_ITINERARY_QUERY_NAME)
				.setParameter(BOARD_ITINERARY_PARAMETER_NAME,
						paroleBoardItinerary)
				.list();
		return summaries;
	}
}
