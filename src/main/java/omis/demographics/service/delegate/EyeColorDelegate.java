package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.EyeColorDao;
import omis.demographics.domain.EyeColor;

/**
 * EyeColorDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class EyeColorDelegate {
	
	public final EyeColorDao eyeColorDao;

	/**
	 * Contructor for EyeColorDelegate
	 * @param eyeColorDao
	 */
	public EyeColorDelegate(EyeColorDao eyeColorDao) {
		this.eyeColorDao = eyeColorDao;
	}
	
	/**
	 * Returns a list of all EyeColors
	 * @return List of all EyeColors
	 */
	public List<EyeColor> findAll() {
		return this.eyeColorDao.findAll();
	}
	
	/**
	 * Returns an EyeColor by name
	 * @param name - String
	 * @return EyeColor
	 */
	public EyeColor findByName(final String name) {
		return this.eyeColorDao.findByName(name);
	}
	
}
