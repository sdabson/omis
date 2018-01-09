/**
 * 
 */
package omis.facility.service.impl;

import omis.facility.dao.RoomDao;
import omis.facility.domain.Room;
import omis.facility.service.RoomService;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public class RoomServiceImpl implements RoomService {

	private RoomDao roomDao;
	
	/**
	 * Instantiates a room service implementation with the specified data
	 * access object.
	 * 
	 * @param roomDao room DAO
	 */
	public RoomServiceImpl(final RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	/** {@inheritDoc} */
	@Override
	public Room findById(final Long id) {
		return this.roomDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public Room save(final Room room) {
		return this.roomDao.makePersistent(room);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Room room) {
		this.roomDao.makeTransient(room);
	}

	/** {@inheritDoc} */
	@Override
	public Integer countBedsByRoom(final Room room) {
		return this.roomDao.countBedsByRoom(room);
	}
}