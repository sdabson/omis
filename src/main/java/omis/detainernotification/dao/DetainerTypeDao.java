/**
 * DetainerTypeDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 8, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.dao;


import omis.dao.GenericDao;
import omis.detainernotification.domain.DetainerType;


public interface DetainerTypeDao extends GenericDao<DetainerType> {
	/**
	 * Finds detainer type with the specified name
	 * @param name - name
	 * @return detainer type with the specified name
	 */
	DetainerType find(String name);
	
	/**Finds detainer type with the specified name excluding specified 
	 * detainer type
	 * @param name
	 * @param excludedDetainerType
	 * @return detainer type with the specified name excluding specified 
	 * detainer type
	 */
	DetainerType findExcluding(String name, DetainerType excludedDetainerType);

}
