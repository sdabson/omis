package omis.bedplacement.service.impl;

import java.util.List;

import omis.bedplacement.dao.BedPlacementReasonDao;
import omis.bedplacement.domain.BedPlacementReason;
import omis.bedplacement.service.BedPlacementReasonService;

/**
 * Implementation of service for movement reason.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Apr, 04 2013)
 * @since OMIS 3.0
 */
public class BedPlacementReasonServiceImpl implements BedPlacementReasonService {

	private BedPlacementReasonDao bedPlacementReasonDao;
	
	/**
	 * Instantiates a movement reason service implementation with the specified
	 * data access object.
	 * 
	 * @param movementReasonDao movement reason DAO
	 */
	public BedPlacementReasonServiceImpl(
			final BedPlacementReasonDao bedPlacementReasonDao) {
		this.bedPlacementReasonDao = bedPlacementReasonDao;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacementReason findById(final Long id) {
		return this.bedPlacementReasonDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacementReason save(final BedPlacementReason bedPlacementReason) {
		return this.bedPlacementReasonDao.makePersistent(bedPlacementReason);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final BedPlacementReason bedPlacementReason) {
		this.bedPlacementReasonDao.makeTransient(bedPlacementReason);
	}

	/** {@inheritDoc} */
	@Override
	public List<BedPlacementReason> findAll() {
		return this.bedPlacementReasonDao.findAll();
	}
}