package omis.offenderflag.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Data access object for offender flags.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public interface OffenderFlagDao
		extends GenericDao<OffenderFlag> {

	/**
	 * Returns offender flags by offender.
	 * 
	 * @param offender offender
	 * @return offender flags be offender
	 */
	List<OffenderFlag> findByOffender(Offender offender);
	
	/**
	 * Returns an offender flag.
	 * 
	 * @param offender offender
	 * @param category category
	 * @return offender flag
	 */
	OffenderFlag find(Offender offender, OffenderFlagCategory category);
	
	/**
	 * Returns the number of missing flags per category for the offender.
	 * 
	 * @param offender offender
	 * @param categories categories
	 * @return number of missing flags per category
	 */
	long countMissingFlags(Offender offender,
			OffenderFlagCategory... categories);
}