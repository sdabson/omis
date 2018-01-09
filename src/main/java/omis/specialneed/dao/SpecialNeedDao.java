/**
 * 
 */
package omis.specialneed.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Data access object for special need.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.2 (Sep 01, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeedDao extends GenericDao<SpecialNeed> {
	
	/**
	 * Returns a list of special needs with the specified description.
	 * 
	 * @param specialNeedCategory special need category
	 * @return list of special needs
	 */
	List<SpecialNeed> findBySpecialNeedCategory(
			SpecialNeedCategory specialNeedCategory);
	
	/**
	 * Returns a list of special needs with the specified source.
	 * 
	 * @param specialNeedSource special need source
	 * @return list of special needs
	 */
	List<SpecialNeed> findBySource(SpecialNeedSource specialNeedSource);

	/**
	 * Returns the special need with the specified attributes.
	 * 
	 * @param startDate start date
	 * @param classification 
	 * @param category special need category
	 * @param source special need source
	 * @param offender offender
	 * @return special need
	 */
	SpecialNeed find(Date startDate, SpecialNeedClassification classification, 
			SpecialNeedCategory category, SpecialNeedSource source,
			Offender offender);

	/**
	 * Returns the special need with the specified attributes, excluding the
	 * specified special need.
	 *  
	 * @param specialNeed special need
	 * @param startDate start date
	 * @param category special need category
	 * @param source special need source
	 * @param offender offender
	 * @return special need
	 */
	SpecialNeed findExcluding(SpecialNeed specialNeed, Date startDate,
			SpecialNeedCategory category, 
			SpecialNeedClassification classification, SpecialNeedSource source, 
			Offender offender);
}