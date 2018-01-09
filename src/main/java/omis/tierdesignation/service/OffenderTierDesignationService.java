package omis.tierdesignation.service;

import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;

/**
 * Service for offender tier designations.
 * 
 * @author Jason Nelson
 * @author Stephen Abson
 * @version 0.1.2 (Dec 12, 2012)
 * @since OMIS 3.0
 */
public interface OffenderTierDesignationService {
	
	/**
	 * Finds tier designations for the offender.
	 * 
	 * @param offender offender
	 * @return tier designations for offender
	 */
	List<OffenderTierDesignation> findByOffender(Offender offender);
	
	/**
	 * Saves an offender tier designation.
	 * 
	 * @param offender offender
	 * @param level level
	 * @param source source
	 * @param changeReason change reason
	 * @param dateRange date range
	 * @param comment comment
	 * @return saved offender tier designation
	 * @throws DuplicateEntityFoundException if tier designation already exists
	 */
	OffenderTierDesignation save(
			Offender offender, TierLevel level, TierSource source,
			TierChangeReason changeReason, DateRange dateRange,
			String comment) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an offender tier designation.
	 * 
	 * @param offenderTierDesignation offender tier designation
	 * @param level level
	 * @param source source
	 * @param changeReason change reason
	 * @param dateRange date range
	 * @param comment comment
	 * @return updated offender tier designation
	 * @throws DuplicateEntityFoundException if tier designation already exists
	 */
	OffenderTierDesignation update(
			OffenderTierDesignation offenderTierDesignation,
			TierLevel level, TierSource source,
			TierChangeReason changeReason, DateRange dateRange,
			String comment) throws DuplicateEntityFoundException;
	
	/**
	 * Removes the offender tier designation.
	 * 
	 * @param offenderTierDesignation offender tier designation to remove
	 */
	void remove(OffenderTierDesignation offenderTierDesignation);
	
	/**
	 * Returns tier change reasons.
	 * 
	 * @return tier change reasons
	 */
	List<TierChangeReason> findChangeReasons();
	
	/**
	 * Returns tier levels.
	 * 
	 * @return tier levels
	 */
	List<TierLevel> findLevels();
	
	/**
	 * Return tier sources.
	 * 
	 * @return tier sources
	 */
	List<TierSource> findSources();
}