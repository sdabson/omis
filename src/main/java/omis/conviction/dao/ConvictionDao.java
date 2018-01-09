package omis.conviction.dao;

import java.util.List;

import omis.conviction.domain.Conviction;
import omis.courtcase.domain.CourtCase;
import omis.dao.GenericDao;
import omis.offense.domain.Offense;

/**
 * Data access object for convictions.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (May 2, 2017)
 * @since OMIS 3.0
 */
public interface ConvictionDao
		extends GenericDao<Conviction> {
	
	/**
	 * Returns all convictions for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return convictions for court case
	 */
	List<Conviction> findByCourtCase(CourtCase courtCase);
	
	/**
	 * Returns the number of convictions by court case.
	 * 
	 * @param courtCase court case
	 * @return number of convictions for court case
	 */
	Long countByCourtCase(CourtCase courtCase);
	
	/**
	 * Returns the conviction with the specified parameters.
	 * 
	 * @param courtCase court case
	 * @param offense offense
	 * @return conviction
	 */
	Conviction find(CourtCase courtCase, Offense offense);
	
	/**
	 * Returns the conviction with the specified parameters excluding the 
	 * specified conviction.
	 * 
	 * @param excludedConviction conviction to exclude
	 * @param courtCase court case
	 * @param offense offense
	 * @return conviction
	 */
	Conviction findExcluding(Conviction excludedConviction, 
			CourtCase courtCase, Offense offense);
}