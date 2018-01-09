package omis.paroleboarditinerary.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboarditinerary.domain.ParoleBoardItineraryNote;

/**
 * Data access object for parole board itinerary note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public interface ParoleBoardItineraryNoteDao 
		extends GenericDao<ParoleBoardItineraryNote> {
	
	/**
	 * Returns the parole board itinerary note that matches the specified parole 
	 * board itinerary, description and date.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param description description
	 * @param date date
	 * @return parole board itinerary note
	 */
	ParoleBoardItineraryNote find(ParoleBoardItinerary boardItinerary, 
			String description, Date date);
	
	/**
	 * Returns the parole board itinerary note that matches the specified parole 
	 * board itinerary, description and date excluding the specified note.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param description description
	 * @param date date
	 * @param excludedNote excluded parole board itinerary note
	 * @return parole board itinerary note
	 */
	ParoleBoardItineraryNote findExcluding(ParoleBoardItinerary boardItinerary, 
			String description, Date date, 
			ParoleBoardItineraryNote excludedNote);
	
	/**
	 * Returns list of notes for the specified parole board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return list of parole board itinerary notes
	 */
	List<ParoleBoardItineraryNote> findItineraryNotesByBoardItinerary(
			ParoleBoardItinerary boardItinerary);
}
