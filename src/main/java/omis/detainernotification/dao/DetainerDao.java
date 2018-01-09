
package omis.detainernotification.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.detainernotification.domain.Detainer;
import omis.offender.domain.Offender;

/**
 * DetainerDao.java
 * 
 *@author Annie Jacques 
 *@author Joel Norris
 *@version 0.1.1 (Jul 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface DetainerDao extends GenericDao<Detainer> {
	/**
	 * Returns detainer with the specified properties.
	 * @param offender - offender
	 * @param receivedDate - received date
	 * @param courtCaseNumber court case number
	 * @param warrantNumber warrant number
	 * @return detainer with specified properties
	 */
	Detainer find(Offender offender, Date receivedDate,
			String courtCaseNumber, String warrantNumber);
	
	/**
	 * Returns detainer with the specified properties excluding the
	 * specified detainer
	 * @param offender - offender
	 * @param receivedDate - received date
	 * @param courtCaseNumber court case number
	 * @param warrantNumber warrant number
	 * @param excludedDetainer - excluded detainer
	 * @return detainer with the specified properties excluding the
	 * specified detainer
	 */
	Detainer findExcluding(Offender offender, Date receivedDate,
			String courtCaseNumber, String warrantNumber,
			Detainer excludedDetainer);
	
	/**
	 * Finds detainers with the specified offender
	 * @param offender - offender
	 * @return detainers with the specified offender
	 */
	List<Detainer> findByOffender(Offender offender);
	
}
