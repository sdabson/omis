package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.TribeDao;
import omis.demographics.domain.Tribe;
import omis.demographics.service.TribeService;

/**
 * Implementation of service for tribes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public class TribeServiceImpl
		implements TribeService {

	private final TribeDao tribeDao;
	
	/**
	 * Instantiates an implementation of service for tribes.
	 * 
	 * @param tribeDao data access object for tribes
	 */
	public TribeServiceImpl(final TribeDao tribeDao) {
		this.tribeDao = tribeDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Tribe> findAll() {
		return this.tribeDao.findAll();
	}
}