package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.HairColorDao;
import omis.demographics.domain.HairColor;
import omis.demographics.service.HairColorService;

/**
 * Implementation of service for hair colors.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public class HairColorServiceImpl
		implements HairColorService {

	private final HairColorDao hairColorDao;
	
	/**
	 * Instantiates an implementation of service for hair colors with the
	 * specified resources.
	 * 
	 * @param hairColorDao data access object for hair colors
	 */
	public HairColorServiceImpl(final HairColorDao hairColorDao) {
		this.hairColorDao = hairColorDao;
	}

	/** {@inheritDoc} */
	@Override
	public HairColor findById(final Long id) {
		return this.hairColorDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public HairColor findByName(final String name) {
		return this.hairColorDao.findByName(name);
	}

	/** {@inheritDoc} */
	@Override
	public List<HairColor> findAll() {
		return this.hairColorDao.findAll();
	}
}