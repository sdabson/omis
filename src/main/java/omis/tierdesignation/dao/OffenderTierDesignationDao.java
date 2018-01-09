package omis.tierdesignation.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;

/**
 * Data access object for offender tier designation entities.
 * @author Jason Nelson
 * @version 0.1.2 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see OffenderTierDesignation
 */
public interface OffenderTierDesignationDao
		extends GenericDao<OffenderTierDesignation> {

	/**
	 * Finds and returns a list of offender tier designations by offender.
	 * @param offender offender whose tier designations to find
	 * @return List of Tier Designations for specified offender
	 */
	List<OffenderTierDesignation> findByOffender(Offender offender);
	
	/**
	 * Returns the offender tier designation with the specified properties.
	 * 
	 * @param offender offender
	 * @param level level
	 * @param source source
	 * @param changeReason change reason
	 * @param dateRange date range
	 * @return offender tier designation with specified properties
	 */
	OffenderTierDesignation find(Offender offender, TierLevel level,
			TierSource source, TierChangeReason changeReason,
			DateRange dateRange);
	
	/**
	 * Returns the offender tier designation with the specified properties
	 * excluding the specified tier designation. 
	 * 
	 * @param offender offender
	 * @param level level
	 * @param source source
	 * @param changeReason change reason
	 * @param dateRange date range
	 * @param excludedOffenderTierDesignation offender tier designation to
	 * exclude 
	 * @return offender tier designation with specified properties
	 */
	OffenderTierDesignation findExcluding(Offender offender, TierLevel level,
			TierSource source, TierChangeReason changeReason,
			DateRange dateRange, OffenderTierDesignation
			excludedOffenderTierDesignation);
}