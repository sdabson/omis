package omis.paroleboarditinerary.report;

import java.util.Date;
import java.util.List;

import omis.paroleboarditinerary.domain.ParoleBoardItinerary;

/**
 * Parole board itinerary summary report service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 21, 2017)
 * @since OMIS 3.0
 */
public interface ParoleBoardItinerarySummaryReportService {

	/**
	 * Returns a list of parole board itineraries for the specified date.
	 * 
	 * @param effectiveDate effective date
	 * @return list of parole board itineraries
	 */
	List<ParoleBoardItinerary> findParoleBoardItineraryByEffectiveDate(
			Date effectiveDate);
	
	/**
	 * Returns a list of parole board itineraries for the specified date range.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of parole board itineraries
	 */
	List<ParoleBoardItinerary> findParoleBoardItineraryByDateRange(
			Date startDate, Date endDate);
	
	/**
	 * Returns a parole board itinerary summary for the specified parole board
	 * itinerary.
	 * 
	 * @param paroleBoardItinerary parole board itinerary
	 * @return parole board itinerary summary
	 */
	ParoleBoardItinerarySummary summarize(
			ParoleBoardItinerary paroleBoardItinerary);
	
	/**
	 * Returns a list of board attendee summaries for the specified parole board
	 * itinerary.
	 * 
	 * @param paroleBoardItinerary parole board itinerary
	 * @return list of board attendee summaries
	 */
	List<BoardAttendeeSummary> 
			findParoleBoardAttendeeSummariesByParoleBoardItinerary(
					ParoleBoardItinerary paroleBoardItinerary);
}
