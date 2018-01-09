/**
 * 
 */
package omis.facility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

/**
 * Room data access object.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 3, 2017)
 * @since OMIS 3.0
 */
public interface RoomDao extends GenericDao<Room> {

	/**
	 * Returns an integer with the amount of beds in the specified room. 0 is
	 * returned if none found.
	 * 
	 * @param room room
	 * @return Integer
	 */
	Integer countBedsByRoom(Room room);

	/**
	 * Returns rooms for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of faciltie's rooms
	 */
	List<Room> findByFaciility(Facility facility);

	/**
	 * Returns rooms for the specified complex.
	 * 
	 * @param complex complex
	 * @return list of complex's rooms
	 */
	List<Room> findByComplex(Complex complex);

	/**
	 * Return rooms for the specified unit.
	 * 
	 * @param unit unit
	 * @return list of unit's rooms
	 */
	List<Room> findByUnit(Unit unit);

	/**
	 * Returns the rooms for the specified section.
	 * 
	 * @param section section
	 * @return list of section's rooms
	 */
	List<Room> findBySection(Section section);

	/**
	 * Returns the rooms for the specified level.
	 * 
	 * @param level level
	 * @return list of level's rooms
	 */
	List<Room> findByLevel(Level level);
	
	/**
	 * Returns a list of rooms that contain the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @param level level
	 * @return list of rooms
	 */
	List<Room> findRooms(Facility facility, Complex complex, Unit unit, 
			Section section, Level level);
	
	/**
	 * Returns the room that matches the specified name and facility.
	 *  
	 * @param name name
	 * @param facility facility
	 * @return room
	 */
	Room find(String name, Facility facility);
	
	/**
	 * Returns the room that matches the specified name and facility excluding 
	 * the specified room.
	 *  
	 * @param name name
	 * @param facility facility
	 * @param excludedRoom excluded room
	 * @return room
	 */
	Room findExcluding(String name, Facility facility, Room excludedRoom);
}