package omis.paroleboarditinerary.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.paroleboarditinerary.domain.BoardMeetingSite;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;

/**
 * Data access object for board meeting sites.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
 * @since OMIS 3.0
 */
public interface BoardMeetingSiteDao extends GenericDao<BoardMeetingSite> {
	
	/**
	 * Returns the board meeting site that matches the specified parole 
	 * board itinerary, location, date, and order.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param location location
	 * @param date date
	 * @param order order
	 * @return board meeting site
	 */
	BoardMeetingSite find(ParoleBoardItinerary boardItinerary, 
			Location location, Date date, Integer order);
	
	/**
	 * Returns the board meeting site that matches the specified parole 
	 * board itinerary, location, date, and order excluding the specified board 
	 * meeting site.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param location location
	 * @param date date
	 * @param order order
	 * @param excludedSite excluded board meeting site
	 * @return board meeting site
	 */
	BoardMeetingSite findExcluding(ParoleBoardItinerary boardItinerary, 
			Location location, Date date, Integer order, 
			BoardMeetingSite excludedSite);
	
	/**
	 * Returns a list of board meeting sites that match the specified parole 
	 * board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return list of board meeting sites
	 */
	List<BoardMeetingSite> findBoardMeetingSitesByBoardItinerary(
			ParoleBoardItinerary boardItinerary);
}
