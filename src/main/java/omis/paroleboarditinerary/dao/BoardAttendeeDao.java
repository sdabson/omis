package omis.paroleboarditinerary.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.paroleboarditinerary.domain.AttendeeRoleCategory;
import omis.paroleboarditinerary.domain.BoardAttendee;
import omis.paroleboarditinerary.domain.ParoleBoardItinerary;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Data access object for board attendee.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 16, 2017)
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
	 * @param role attendee role category
	 * @return board attendee
	 */
	BoardAttendee find(ParoleBoardItinerary boardItinerary, 
			ParoleBoardMember boardMember, Long number, 
			AttendeeRoleCategory role);
	
	/**
	 * Returns the board attendee that matches the specified parole board 
	 * itinerary, board member, number and attendee role category excluding the 
	 * specified attendee.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @param boardMember parole board member
	 * @param number number
	 * @param role attendee role category
	 * @param excludedAttendee excluded board attendee
	 * @return board attendee
	 */
	BoardAttendee findExcluding(ParoleBoardItinerary boardItinerary, 
			ParoleBoardMember boardMember, Long number, 
			AttendeeRoleCategory role, BoardAttendee excludedAttendee);
	
	/**
	 * Returns list of board attendee for the specified parole board itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return list of board attendees
	 */
	List<BoardAttendee> findBoardAttendeesByBoardItinerary(
			ParoleBoardItinerary boardItinerary);
	
	/**
	 * Returns the alternate board attendee for the specified parole board 
	 * itinerary.
	 * 
	 * @param boardItinerary parole board itinerary
	 * @return board attendee
	 */
	BoardAttendee findBoardAlternateAttendeeByBoardItinerary(
			ParoleBoardItinerary boardItinerary);
}
