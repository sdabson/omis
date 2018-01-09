package omis.custody.dao;

import java.util.Date;
import java.util.List;

import omis.custody.domain.CustodyReview;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;

/**
 * Database access objects for custody review.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public interface CustodyReviewDao extends GenericDao<CustodyReview> {

	/**
	 * Returns a list of custody reviews for the specified offender.
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
	 * @return the custody review.
	 */
	CustodyReview findByOffenderOnDate(Offender offender, Date date);

	/**
	 * Returns the custody review with the specified natural key properties. If
	 * no custody review is found, {@code null} is returned.
	 * 
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason change reason
	 * @param actionDate action date
	 * @return custody level
	 */
	CustodyReview find(Offender offender, Date actionDate);
	
	/**
	 * Returns the custody review with the specified natural key properties
	 * excluding the specified custody review.  If no custody review is found, 
	 * {@code null} is returned. 
	 * 
	 * @param offender offender
	 * @param custodyLevel custody level
	 * @param changeReason change reason
	 * @param actionDate action date
	 * @param custodyReview custody review
	 * @return custody review
	 */
	CustodyReview findExcluding(Offender offender, Date actionDate,
			CustodyReview custodyReview);	
}