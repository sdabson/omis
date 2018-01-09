/**
 * DetainerAgencyDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Mar 22, 2017)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.dao;


import java.util.List;

import omis.dao.GenericDao;
import omis.detainernotification.domain.DetainerAgency;


public interface DetainerAgencyDao extends GenericDao<DetainerAgency> {
	
	/**
	 * Finds detainer agency with specified agency name
	 * @param agencyName - agency name
	 * @return detainer agency with specified agency name
	 */
	DetainerAgency find(String agencyName);
	
	/**
	 * Finds detainer agency with specified agency name excluding specified
	 * detainer agency
	 * @param agencyName - agency name
	 * @param excludedDetainerAgency - excluded detainer agency
	 * @return detainer agency with specified agency name excluding specified
	 * detainer agency
	 */
	DetainerAgency findExcluding(String agencyName, 
			DetainerAgency excludedDetainerAgency);
	
	/**
	 * Returns a list of all Detainer Agencies
	 * @return List of all Detainer Agencies
	 */
	List<DetainerAgency> findAll();
	
}
