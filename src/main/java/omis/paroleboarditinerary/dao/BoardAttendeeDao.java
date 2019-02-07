package omis.paroleboarditinerary.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.paroleboarditinerary.domain.BoardAttendee;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Data access object for board attendee.
 * 
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.1 (Feb 5, 2019)
 * @since OMIS 3.0
 */
public interface BoardAttendeeDao 
		extends GenericDao<BoardAttendee> {
	
	/**
	 * Returns the board attendee that matches the specified parole board 
	 * itinerary, board member, number and attendee role category.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param boardMember parole board member
	 * @param number number
	 * @return board attendee
	 */
	BoardAttendee find(ParoleBoardItinerary boardItinerary, 
			ParoleBoardMember boardMember, Long number);
	
	/**
	 * Returns the board attendee that matches the specified parole board 
	 * itinerary, board member, number and attendee role category excluding the 
	 * specified attendee.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param boardMember parole board member
	 * @param number number
	 * @param excludedAttendee excluded board attendee
	 * @return board attendee
	 */
	BoardAttendee findExcluding(ParoleBoardItinerary boardItinerary, 
			ParoleBoardMember boardMember, Long number,
			BoardAttendee excludedAttendee);
	
	/**
	 * Returns list of board attendee for the specified parole board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return list of board attendees
	 */
	List<BoardAttendee> findBoardAttendeesByBoardItinerary(
			ParoleBoardItinerary boardItinerary);
}
