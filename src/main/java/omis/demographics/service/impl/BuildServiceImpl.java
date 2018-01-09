package omis.demographics.service.impl;

import java.util.List;

import omis.demographics.dao.BuildDao;
import omis.demographics.domain.Build;
import omis.demographics.service.BuildService;

/**
 * Implementation of service for builds.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public class BuildServiceImpl
		implements BuildService {

	private final BuildDao buildDao;
	
	/**
	 * Instantiates an implementation of service for builds with the specified
	 * resources.
	 * 
	 * @param buildDao data access object for builds
	 */
	public BuildServiceImpl(final BuildDao buildDao) {
		this.buildDao = buildDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Build> findAll() {
		return this.buildDao.findAll();
	}
}