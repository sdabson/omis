package omis.courtcase.dao;

import java.util.List;

import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.dao.GenericDao;
import omis.offense.domain.Offense;

/**
 * Data access object for charges.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.4 (May 9, 2017)
 * @since OMIS 3.0
 */
public interface ChargeDao
		extends GenericDao<Charge> {

	/**
	 * Returns the charge with the specified property values.
	 * 
	 * @param courtCase court case
	 * @param offense offense
	 * @param counts counts
	 * @return charge with specified property values; {@code null} if no such
	 * charge exists
	 */
	Charge find(CourtCase courtCase, Offense offense, Integer counts);
	
	/** Finds charges excluding.
	 * @param courtCase court case
	 * @param offense offense
	 * @param counts counts 
	 * @param excluding excluding
	 */
	Charge findExcluding(CourtCase courtCase, Offense offense, 
			Integer counts, Charge...excluding);
	
	/**
	 * Returns charges by court case.
	 * 
	 * @param courtCase court case
	 * @return charges by court case
	 */
	List<Charge> findByCourtCase(CourtCase courtCase);

	/**
	 * Removes charges by court case excluding the ones specified.
	 * 
	 * @param courtCase court case
	 * @param excludedCharges charges to exclude
	 */
	void removeByCourtCaseExcluding(CourtCase courtCase,
			Charge... excludedCharges);
	
	/**
	 * Returns the number of charges of a court case.
	 * 
	 * @param courtCase court case the number of charges of which to return
	 * @return number of charges of court case
	 */
	Long countByCourtCase(CourtCase courtCase);
}