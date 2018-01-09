package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.ComplexionDao;
import omis.demographics.domain.Complexion;
import omis.demographics.service.ComplexionService;

/**
 * Implementation of service for complexions.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public class ComplexionServiceImpl
		implements ComplexionService {

	private final ComplexionDao complexionDao;
	
	/**
	 * Instantiates an implementation of service for complexions with the
	 * specified resources.
	 * 
	 * @param complexionDao data access object for complexions
	 */
	public ComplexionServiceImpl(final ComplexionDao complexionDao) {
		this.complexionDao = complexionDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Complexion> findAll() {
		return this.complexionDao.findAll();
	}
}