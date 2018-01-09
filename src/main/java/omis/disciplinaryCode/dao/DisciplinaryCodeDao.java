package omis.disciplinaryCode.dao;



import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public interface DisciplinaryCodeDao extends GenericDao<DisciplinaryCode> {
	
	/**
	 * Returns a disciplinary code with the specified value
	 * @param value - value
	 * @return disciplinary code with the specified value
	 */
	DisciplinaryCode find(String value);
	
	/**
	 * Returns a disciplinary code with the specified value excluding the 
	 * specified disciplinary code
	 * @param value - value
	 * @param disciplinaryCode - disciplinary code
	 * @return disciplinary code with the specified value excluding the 
	 * specified disciplinary code
	 */
	DisciplinaryCode findExcluding(String value, 
			DisciplinaryCode disciplinaryCode);
	
	/**
	 * Returns a disciplinary code with the specified value as a list
	 * @param value - value
	 * @return disciplinary code with the specified value as a list
	 */
	List<DisciplinaryCode>  search(String value);
	
	/**
	 * Returns a list of disciplinary codes valid for specified Supervisory 
	 * Organization on specified date
	 * @param supervisoryOrganization - SupervisoryOrganization
	 * @param effectiveDate - Date
	 * @return  list of disciplinary codes valid for specified Supervisory 
	 * Organization on specified date
	 */
	List<DisciplinaryCode>  findBySupervisoryOrganizationAndDate(
			SupervisoryOrganization supervisoryOrganization, Date effectiveDate);
	
	
}
