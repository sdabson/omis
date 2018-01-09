package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.HairColorDao;
import omis.demographics.domain.HairColor;

/**
 * HairColorDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class HairColorDelegate {
	
	public final HairColorDao hairColorDao;

	/**
	 * Contructor for HairColorDelegate
	 * @param hairColorDao
	 */
	public HairColorDelegate(HairColorDao hairColorDao) {
		this.hairColorDao = hairColorDao;
	}
	
	/**
	 * Returns a list of all HairColors
	 * @return List of all HairColors
	 */
	public List<HairColor> findAll() {
		return this.hairColorDao.findAll();
	}
	
	/**
	 * Returns the hair color with the specified name.
	 * 
	 * @param name name of hair color to return
	 * @return hair color with specified name
	 */
	public HairColor findByName(final String name) {
		return this.hairColorDao.findByName(name);
	}
	
}
