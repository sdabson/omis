/**
 * DetainerWarrantProcessingStatusDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 8, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.dao;

import omis.dao.GenericDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerWarrantProcessingStatus;


public interface DetainerWarrantProcessingStatusDao 
	extends GenericDao<DetainerWarrantProcessingStatus> {
	/**
	 * Finds Detainer Warrant Processing Status with the specified detainer
	 * @param Detainer - detainer
	 * @return Detainer Warrant Processing Status with the specified detainer
	 */
	DetainerWarrantProcessingStatus find(Detainer detainer);
	
	/**Finds Detainer Warrant Processing Status with the specified detainer 
	 * excluding specified Detainer Warrant Processing Status
	 * @param Detainer - detainer
	 * @param excludedDetainerWarrantProcessingStatus
	 * @return Detainer Warrant Processing Status with the specified detainer 
	 * excluding specified Detainer Warrant Processing Status
	 */
	DetainerWarrantProcessingStatus findExcluding(Detainer detainer, 
		DetainerWarrantProcessingStatus excludedDetainerWarrantProcessingStatus);
}
