package omis.court.service.impl;

import java.util.List;

import omis.court.dao.CourtDao;
import omis.court.domain.Court;
import omis.court.service.CourtService;

/**
 * Service for courts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 28, 2013)
 * @since OMIS 3.0
 */
public class CourtServiceImpl
		implements CourtService {

	private final CourtDao courtDao;

	/**
	 * Instantiates an implementation of service for courts with the specified
	 * resources.
	 * 
	 * @param courtDao data access object for courts
	 */
	public CourtServiceImpl(final CourtDao courtDao) {
		this.courtDao = courtDao;
	}

	/** {@inheritDoc} */
	@Override
	public Court findById(final Long id) {
		return this.courtDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public List<Court> findAll() {
		return this.courtDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public Court save(final Court court) {
		return this.courtDao.makePersistent(court);
	}

	@Override
	public void remove(final Court court) {
		this.courtDao.makeTransient(court);
	}
}