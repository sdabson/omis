package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.EyeColorDao;
import omis.demographics.domain.EyeColor;
import omis.demographics.service.EyeColorService;

/**
 * Implementation of service for eye colors.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public class EyeColorServiceImpl
		implements EyeColorService {

	private final EyeColorDao eyeColorDao;

	/**
	 * Instantiates an implementation of service for eye colors with the
	 * specified resources.
	 * 
	 * @param eyeColorDao data access object for eye colors
	 */
	public EyeColorServiceImpl(final EyeColorDao eyeColorDao) {
		this.eyeColorDao = eyeColorDao;
	}

	/** {@inheritDoc} */
	@Override
	public EyeColor findById(final Long id) {
		return this.eyeColorDao.findById(id, false);
	}

	/** {@inheritDoc} */
	@Override
	public EyeColor findByName(final String name) {
		return this.eyeColorDao.findByName(name);
	}

	/** {@inheritDoc} */
	@Override
	public List<EyeColor> findAll() {
		return this.eyeColorDao.findAll();
	}
}