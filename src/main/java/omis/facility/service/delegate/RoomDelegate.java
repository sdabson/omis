package omis.facility.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.RoomDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Room;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;
import omis.instance.factory.InstanceFactory;

/**
 * Room delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class RoomDelegate {

	private final RoomDao roomDao;
	
	private final InstanceFactory<Room> roomInstanceFactory;
	
	/**
	 * Constructor for room delegate.
	 * 
	 * @param roomDao room data access object
	 * @param roomInstanceFactory room instance factory
	 */
	public RoomDelegate(final RoomDao roomDao,
			final InstanceFactory<Room> roomInstanceFactory) {
		this.roomDao = roomDao;
		this.roomInstanceFactory = roomInstanceFactory;
	}
	
	/**
	 * Creates a new room.
	 * 
	 * @param name name
	 * @param facility facility
	 * @param complex complex
	 * @param level level
	 * @param section section
	 * @param unit unit
	 * @param active active
	 * @return room
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public Room create(final String name, final Facility facility, 
			final Complex complex, final Level level, final Section section,
			final Unit unit, final Boolean active) 
					throws DuplicateEntityFoundException {
		if (this.roomDao.find(name, facility) != null) {
			throw new DuplicateEntityFoundException("The room already exists.");
		}
		Room room = this.roomInstanceFactory.createInstance();
		populateRoom(room, name, facility, complex, level, section, unit, 
				active);
		return this.roomDao.makePersistent(room);
	}
	
	/**
	 * Updates an existing room.
	 * 
	 * @param room room
	 * @param name name
	 * @param facility facility
	 * @param complex complex
	 * @param level level
	 * @param section section
	 * @param unit unit
	 * @param active active
	 * @return room
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public Room update(final Room room, final String name, 
			final Facility facility, final Complex complex, final Level level, 
			final Section section, final Unit unit, final Boolean active) 
					throws DuplicateEntityFoundException {
		if (this.roomDao.findExcluding(name, facility, room) != null) {
			throw new DuplicateEntityFoundException("The room already exists.");
		}
		populateRoom(room, name, facility, complex, level, section, unit, 
				active);
		return this.roomDao.makePersistent(room);
	}

	/**
	 * Removes the specified room.
	 * 
	 * @param room room
	 */
	public void remove(final Room room) {
		this.roomDao.makeTransient(room);
	}
	
	/**
	 *  Returns an integer with the amount of beds in the specified room. 0 is
	 * returned if none found.
	 * 
	 * @param room room
	 * @return Integer
	 */
	public Integer countBedsByRoom(final Room room) {
		return this.roomDao.countBedsByRoom(room);
	}
	
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
	public List<Room> findRooms(final Facility facility, final Complex complex, 
			final Unit unit, final Section section, final Level level) {
		return this.roomDao.findRooms(facility, complex, unit, section, level);
	}
	
	// Populates a room
	private void populateRoom(final Room room, final String name, 
			final Facility facility, final Complex complex, final Level level, 
			final Section section, final Unit unit, final Boolean active) {
		room.setActive(active);
		room.setComplex(complex);
		room.setFacility(facility);
		room.setLevel(level);
		room.setName(name);
		room.setSection(section);
		room.setUnit(unit);
	}
}
