package omis.paroleboardcondition.dao;

import omis.condition.domain.Agreement;
import omis.dao.GenericDao;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.domain.ParoleBoardAgreementCategory;

/**
 * Parole Board Agreement Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ParoleBoardAgreementDao
		extends GenericDao<ParoleBoardAgreement> {
	
	/**
	 * Finds a Parole Board Agreement by the specified properties.
	 * @param agreement - Agreement
	 * @param category - Parole Board Agreement Category
	 * @return ParoleBoardAgreement - Parole Board Agreement by the specified
	 * properties.
	 */
	ParoleBoardAgreement find(Agreement agreement,
			ParoleBoardAgreementCategory category);
	
	/**
	 * Finds a Parole Board Agreement by the specified properties excluding
	 * given Parole Board Agreement.
	 * @param agreement - Agreement
	 * @param category - Parole Board Agreement Category
	 * @param paroleBoardAgreementExcluding - Parole Board Agreement to exclude.
	 * @return ParoleBoardAgreement - Parole Board Agreement by the specified
	 * properties excluding given Parole Board Agreement.
	 */
	ParoleBoardAgreement findExcluding(Agreement agreement,
			ParoleBoardAgreementCategory category,
			ParoleBoardAgreement paroleBoardAgreementExcluding);
}
