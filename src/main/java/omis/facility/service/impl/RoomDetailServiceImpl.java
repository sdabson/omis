/**
 * 
 */
package omis.facility.service.impl;

import omis.facility.dao.RoomDetailDao;
import omis.facility.domain.RoomDetail;
import omis.facility.service.RoomDetailService;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 11 2013)
 * @since OMIS 3.0
 */
public class RoomDetailServiceImpl implements RoomDetailService {

	private RoomDetailDao roomDetailDao;
	
	/**
	 * Instantiates a room detail service implementaiton with the specified
	 * data access object.
	 * 
	 * @param roomDetailDao room detail DAO
	 */
	public RoomDetailServiceImpl(final RoomDetailDao roomDetailDao) {
		this.roomDetailDao = roomDetailDao;
	}

	/** {@inheritDoc} */
	@Override
	public RoomDetail save(final RoomDetail roomDetail) {
		return this.roomDetailDao.makePersistent(roomDetail);
	}
}