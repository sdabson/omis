package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.MaritalStatusDao;
import omis.demographics.domain.MaritalStatus;
import omis.demographics.service.MaritalStatusService;

/**
 * Implementation of service for marital statuses.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 7, 2014)
 * @since OMIS 3.0
 */
public class MaritalStatusServiceImpl
		implements MaritalStatusService {

	private final MaritalStatusDao maritalStatusDao;
	
	/**
	 * Instantiates an implementation of service for marital statuses.
	 * 
	 * @param maritalStatusDao data access object for marital statuses
	 */
	public MaritalStatusServiceImpl(final MaritalStatusDao maritalStatusDao) {
		this.maritalStatusDao = maritalStatusDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<MaritalStatus> findAll() {
		return this.maritalStatusDao.findAll();
	}
}