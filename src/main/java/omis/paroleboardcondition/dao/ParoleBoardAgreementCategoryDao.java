package omis.paroleboardcondition.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Category Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ParoleBoardAgreementCategoryDao
		extends GenericDao<ParoleBoardAgreementCategory> {
	
	/**
	 * Finds a Parole Board Agreement Category by the specified name.
	 * @param name - String
	 * @return ParoleBoardAgreementCategory - Parole Board Agreement Category
	 * found by the specified name.
	 */
	ParoleBoardAgreementCategory find(String name);
	
	/**
	 * Finds a Parole Board Agreement Category by the specified name excluding
	 * the specified Parole Board Agreement Category.
	 * @param name - String
	 * @param paroleBoardAgreementCategoryExcluded - Parole Board Agreement
	 * Category to exclude
	 * @return ParoleBoardAgreementCategory - Parole Board Agreement Category
	 * by the specified name excluding the specified Parole Board Agreement
	 * Category.
	 */
	ParoleBoardAgreementCategory findExcluding(String name,
			ParoleBoardAgreementCategory paroleBoardAgreementCategoryExcluded);
	
	/**
	 * Returns a list of all Parole Board Agreement Categories.
	 * @return List of all Parole Board Agreement Categories.
	 */
	List<ParoleBoardAgreementCategory> findAll();
}
