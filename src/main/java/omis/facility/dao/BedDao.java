/**
 * 
 */
package omis.facility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Bed;
import omis.facility.domain.Room;

/**
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.1 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public interface BedDao extends GenericDao<Bed> {

	/**
	 * Returns a list of beds according to a specified room.
	 * @param room room
	 * @return list of beds
	 */
	List<Bed> findByRoom(Room room);
	
	/**
	 * Returns the bed matching the specified room and number.
	 * 
	 * @param room room
	 * @param number number
	 * @return bed
	 */
	Bed find(Room room, Integer number);
	
	/**
	 * Returns the bed matching the specified room and number excluding the specified bed.
	 * 
	 * @param room room
	 * @param number number
	 * @param excludedBed excluded bed
	 * @return bed
	 */
	Bed findExcluding(Room room, Integer number, Bed excludedBed);
}
