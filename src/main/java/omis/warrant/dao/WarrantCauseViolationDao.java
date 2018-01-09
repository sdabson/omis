package omis.warrant.dao;

import java.util.List;

import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.dao.GenericDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCauseViolation;

/**
 * WarrantCauseDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantCauseViolationDao
	extends GenericDao<WarrantCauseViolation> {
	
	/**
	 * Finds and returns a WarrantCauseViolation with the specified properties
	 * @param warrant - Warrant
	 * @param cause - CourtCase
	 * @param condition - Condition
	 * @return WarrantCauseViolation with the specified properties
	 */
	public WarrantCauseViolation find(Warrant warrant, CourtCase cause,
			Condition condition);
	
	/**
	 * Finds and returns a WarrantCauseViolation with the specified properties excluding
	 * specified WarrantCauseViolation
	 * @param warrant - Warrant
	 * @param cause - CourtCase
	 * @param condition - Condition
	 * @param warrantCauseViolationExcluded - WarrantCauseViolation to exclude
	 * from search
	 * @return WarrantCauseViolation with the specified properties excluding
	 * specified WarrantCauseViolation
	 */
	public WarrantCauseViolation findExcluding(
			Warrant warrant, CourtCase cause, Condition condition,
			WarrantCauseViolation warrantCauseViolationExcluded);
	
	/**
	 * Returns a list of all WarrantCauseViolations with specified Warrant
	 * @param warrant - Warrant
	 * @return List of all WarrantCauseViolations with specified Warrant
	 */
	public List<WarrantCauseViolation> findByWarrant(Warrant warrant);
	
}
