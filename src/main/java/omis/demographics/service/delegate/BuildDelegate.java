package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.BuildDao;
import omis.demographics.domain.Build;

/**
 * BuildDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class BuildDelegate {
	
	public final BuildDao buildDao;

	/**
	 * Contructor for BuildDelegate
	 * @param buildDao
	 */
	public BuildDelegate(BuildDao buildDao) {
		this.buildDao = buildDao;
	}
	
	/**
	 * Returns a list of all Builds
	 * @return List of all Builds
	 */
	public List<Build> findAll() {
		return this.buildDao.findAll();
	}
	
}
