package omis.disciplinaryCode.dao;

import omis.dao.GenericDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * SupervisoryOrganizationCodeDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public interface SupervisoryOrganizationCodeDao 
	extends GenericDao<SupervisoryOrganizationCode> {
	
	/**
	 * Finds a supervisory organization code by specified supervisory organization
	 * @param supervisoryOrganization - supervisory organization
	 * @param code - disciplinary code
	 * @return supervisory organization code with specified supervisory organization
	 */
	SupervisoryOrganizationCode find(
			SupervisoryOrganization supervisoryOrganization, 
			DisciplinaryCode code);
	
	/**
	 * Finds a supervisory organization code by specified supervisory organization
	 * excluding specified supervisory organization code
	 * @param supervisoryOrganization - supervisory organization
	 * @param code - disciplinary code
	 * @param supervisoryOrganizationCode - supervisory organization code to exclude
	 * @return supervisory organization code with specified supervisory organization
	 * excluding specified supervisory organization code
	 */
	SupervisoryOrganizationCode findExcluding(
			SupervisoryOrganization supervisoryOrganization,  
			DisciplinaryCode code,
			SupervisoryOrganizationCode supervisoryOrganizationCode);
	
	
	
}
