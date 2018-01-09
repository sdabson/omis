package omis.caseload.dao;

import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.dao.GenericDao;

/**
 * Data access object for caseload.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0
 */
public interface CaseloadDao extends GenericDao<Caseload> {

	/**
	 * Returns the caseload.
	 *
	 * @param name name
	 * @param category category
	 * @return caseload
	 */
	Caseload find(String name, CaseworkCategory category);

	/**
	 * Returns the caseload excluding the one in view.
	 *
	 * @param name name
	 * @param category category
	 * @param caseload caseload
	 * @return caseload
	 */
	Caseload findExcluding(String name, CaseworkCategory category, 
					Caseload caseload);
}