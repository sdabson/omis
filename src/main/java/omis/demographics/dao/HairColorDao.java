package omis.demographics.dao;

import omis.dao.GenericDao;
import omis.demographics.domain.HairColor;

/**
 * Data access object for hair colors.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Jan 30, 2013)
 * @since OMIS 3.0
 */
public interface HairColorDao
		extends GenericDao<HairColor> {

	/**
	 * Returns the hair color with the specified name.
	 * 
	 * @param name name of hair color to return
	 * @return hair color with specified name
	 */
	HairColor findByName(String name);
}