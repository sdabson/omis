package omis.custody.service;

import java.util.Date;
import java.util.List;

import omis.custody.domain.CustodyChangeReason;
import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Custody Review Service.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 08 2013)
 * @since OMIS 3.0
 */
public interface CustodyReviewService {
	
	/**
	 * Saves a new custody review with the specified properties.
	 * 
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason change reason
	 * @param actionDate action date
	 * @param nextReviewDate next review date
	 * @return custody review
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	CustodyReview create(Offender offender, CustodyLevel custodyLevel, 
			CustodyChangeReason changeReason, Date actionDate, 
			Date nextReviewDate) throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified custody review with the specified properties.
	 * 
	 * @param custodyReview custody review
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason change reason
	 * @param actionDate action date
	 * @param nextReviewDate next review date
	 * @return custody review
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	CustodyReview update(CustodyReview custodyReview, Offender offender, 
			CustodyLevel custodyLevel, CustodyChangeReason changeReason, 
			Date actionDate, Date nextReviewDate)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified custody review.
	 * 
	 * @param custodyReview custody review
	 */
	void remove(CustodyReview custodyReview);
	
	/**
	 * Returns a list of all custody reviews for a specified offender.
	 * 
	 * @param offender offender
	 * @return list of custody reviews
	 */
	List<CustodyReview> findByOffender(Offender offender);

	/**
	 * Returns the custody review for the specified offender on 
	 * the specified date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return the custody review
	 */
	CustodyReview findByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Creates a new custody override
	 * @param custodyReview custody review
	 * @param custodyLevel custody level
	 * @return custody override
	 */
	CustodyOverride overrideReview(final CustodyReview custodyReview, 
			final CustodyLevel custodyLevel) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Authorizes the specified custody override
	 * @param custodyOverride custody override
	 * @return custody override
	 */
	CustodyOverride authorizeOverride(final CustodyOverride custodyOverride); 
	
	/**
	 * Removes the specified custody override.
	 * 
	 * @param custodyOverride custody override
	 */
	void removeOverride(final CustodyOverride custodyOverride);
	
	/**
	 * Returns the override for the specified custody review.
	 * 
	 * @param custodyReview custody review
	 * @return the custody override
	 */
	CustodyOverride findOverrideByReview(final CustodyReview custodyReview);
	
	/**
	 * Finds all custody change reasons.
	 * 
	 * @return list of custody change reasons
	 */
	List<CustodyChangeReason> findCustodyChangeReasons();	
	
	/**
	 * Returns a list of all custody levels.
	 * 
	 * @return list of custody levels
	 */
	List<CustodyLevel> findCustodyLevels();
}