/**
 * 
 */
package omis.facility.service.impl;

import java.util.List;

import omis.facility.dao.BedDao;
import omis.facility.domain.Bed;
import omis.facility.domain.Room;
import omis.facility.service.BedService;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public class BedServiceImpl implements BedService {

	private BedDao bedDao;
	
	/**
	 * Instantiates a bed service implementation with the specified data
	 * access object.
	 * 
	 * @param bedDao bed DAO.
	 */
	public BedServiceImpl(final BedDao bedDao) {
		this.bedDao = bedDao;
	}

	/** {@inheritDoc} */
	@Override
	public Bed findById(final Long id) {
		return this.bedDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public Bed save(final Bed bed) {
		return this.bedDao.makePersistent(bed);
	}

	/** {@inheritDoc} */
	@Override
	public List<Bed> findByRoom(final Room room) {
		return this.bedDao.findByRoom(room);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Bed bed) {
		this.bedDao.makeTransient(bed);
	}

}
