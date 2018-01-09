/**
 * InterstateAgreementDetainerDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 8, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.dao;

import omis.dao.GenericDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.InterstateAgreementDetainer;


public interface InterstateAgreementDetainerDao 
	extends GenericDao<InterstateAgreementDetainer> {
	/**
	 * Finds Interstate Agreement Detainer with the specified detainer
	 * @param Detainer - detainer
	 * @return Interstate Agreement Detainer with the specified detainer
	 */
	InterstateAgreementDetainer find(Detainer detainer);
	
	/**Finds Interstate Agreement Detainer with the specified detainer excluding 
	 * specified Interstate Agreement Detainer
	 * @param detainer - detainer
	 * @param excludedInterstateAgreementDetainer
	 * @return Interstate Agreement Detainer with the specified detainer excluding 
	 * specified Interstate Agreement Detainer
	 */
	InterstateAgreementDetainer findExcluding(Detainer detainer, 
			InterstateAgreementDetainer excludedInterstateAgreementDetainer);
}
