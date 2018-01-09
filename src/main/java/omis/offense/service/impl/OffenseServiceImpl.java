package omis.offense.service.impl;

import java.util.List;

import omis.offense.dao.OffenseDao;
import omis.offense.domain.Offense;
import omis.offense.service.OffenseService;

/**
 * Offense service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0
 */
public class OffenseServiceImpl implements OffenseService {

	private OffenseDao offenseDao;
	
	/**
	 * Instantiates an offense service implementation with the specified
	 * data access object.
	 * 
	 * @param offenseDao offense DAO
	 */
	public OffenseServiceImpl(final OffenseDao offenseDao) {
		this.offenseDao = offenseDao;
	}

	/** {@inheritDoc} */
	@Override
	public Offense findById(final Long id) {
		return this.offenseDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public Offense save(final Offense offense) {
		return this.offenseDao.makePersistent(offense);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Offense offense) {
		this.offenseDao.makeTransient(offense);
	}

	/** {@inheritDoc} */
	@Override
	public List<Offense> findAll() {
		return this.offenseDao.findAll();
	}

}
