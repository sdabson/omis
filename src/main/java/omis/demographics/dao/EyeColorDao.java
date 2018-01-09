package omis.demographics.dao;

import omis.dao.GenericDao;
import omis.demographics.domain.EyeColor;

/**
 * Data access object for eye colors.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public interface EyeColorDao
		extends GenericDao<EyeColor> {

	/**
	 * Returns the eye color with the specified name.
	 * 
	 * @param name name of eye color to return
	 * @return eye color with specified name
	 */
	EyeColor findByName(String name);
}