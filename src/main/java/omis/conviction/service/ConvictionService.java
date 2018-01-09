package omis.conviction.service;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.offense.domain.Offense;
import omis.sentence.domain.Sentence;

/**
 * Conviction service.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.4 (Jan 30, 2017)
 * @since OMIS 3.0
 */
public interface ConvictionService {
	
	/**
	 * Convicts an offense.
	 * 
	 * @param courtCase court case
	 * @param offense offense
	 * @param severity offense severity
	 * @param date date
	 * @param counts counts
	 * @param flags conviction flags
	 * @return conviction
	 * @throws ConvictionExistsException
	 */
	Conviction convict(CourtCase courtCase, Offense offense, 
			OffenseSeverity severity, Date date, Integer counts, 
			ConvictionFlags flags) throws ConvictionExistsException;
	
	/**
	 * Updates an existing conviction.
	 * 
	 * @param conviction conviction
	 * @param offense offense
	 * @param severity offense severity
	 * @param date date
	 * @param counts counts
	 * @param flags conviction flags
	 * @return conviction
	 * @throws ConvictionExistsException
	 */
	Conviction update(Conviction conviction, Offense offense, 
			OffenseSeverity severity, Date date, Integer counts, 
			ConvictionFlags flags) throws ConvictionExistsException;
	
	/**
	 * Removes a conviction.
	 * 
	 * @param conviction conviction
	 */
	void remove(Conviction conviction);
	
	/**
	 * Returns charges by court case.
	 * 
	 * @param courtCase court case
	 * @return charges by court case
	 */
	List<Charge> findChargesByCourtCase(CourtCase courtCase);
	
	/**
	 * Returns offenses.
	 * 
	 * @return offenses
	 */
	List<Offense> findOffenses();
	
	/**
	 * Returns sentence for the specified conviction.
	 * 
	 * @param conviction conviction
	 * @return sentence sentence
	 */
	List<Sentence> findByConviction(Conviction conviction);
	
	/**
	 * Returns active sentence for the specified conviction.
	 * 
	 * @param conviction conviction
	 * @return active sentence
	 */
	Sentence findActiveByConviction(Conviction conviction);
	
	/**
	 * Returns whether the court case has existing convictions.
	 * 
	 * @param courtCase court case.
	 * @return court case
	 */
	boolean hasConvictions(CourtCase courtCase);

	
	/**
	 * Returns convictions for a court case.
	 * 
	 * @param courtCase court case
	 * @return convictions
	 */
	List<Conviction> findConvictionsByCourtCase(CourtCase courtCase);
}