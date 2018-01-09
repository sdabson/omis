package omis.locationterm.service;

import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DateRangeOutOfBoundsException;
import omis.exception.DuplicateEntityFoundException;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationReasonTerm;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.exception.LocationReasonTermConflictException;
import omis.offender.domain.Offender;
import omis.offender.exception.OffenderMismatchException;

/**
 * Service for location reason term.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 16, 2014)
 * @since OMIS 3.0
 */
public interface LocationReasonTermService {

	/**
	 * Returns location reason terms for offender.
	 * 
	 * @param offender offender
	 * @return location reason terms for offender
	 */
	List<LocationReasonTerm> findByOffender(Offender offender);
	
	/**
	 * Returns reasons an offender can be at a location.
	 * 
	 * @return reasons an offender can be at location
	 */
	List<LocationReason> findLocationReasons();
	
	/**
	 * Returns location terms for offender.
	 * 
	 * @param offender offender
	 * @return location terms for offender
	 */
	List<LocationTerm> findLocationTerms(Offender offender);
	
	/**
	 * Creates a location reason term.
	 * 
	 * @param offender offender
	 * @param locationTerm location term
	 * @param reason location reason
	 * @param dateRange date range
	 * @return location reason term
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws OffenderMismatchException if offender and offender of
	 * location term are not the same
	 * @throws LocationReasonTermConflictException if conflicting location
	 * reason terms exist
	 * @throws DateRangeOutOfBoundsException if the reason term is outside of
	 * the bounds of the location term
	 */
	LocationReasonTerm create(Offender offender, LocationTerm locationTerm,
			LocationReason reason, DateRange dateRange)
					throws DuplicateEntityFoundException,
						OffenderMismatchException,
						LocationReasonTermConflictException,
						DateRangeOutOfBoundsException;
	
	/**
	 * Updates a location reason term.
	 * 
	 * @param locationReasonTerm location reason term
	 * @param locationTerm location term
	 * @param reason location reason
	 * @param dateRange date range
	 * @return location reason term
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws OffenderMismatchException if offender of location reason term
	 * and offender of location term are not the same
	 * @throws LocationReasonTermConflictException if conflicting location
	 * reason terms exist
	 * @throws DateRangeOutOfBoundsException if the reason term is outside of
	 * the bounds of the location term
	 */
	LocationReasonTerm update(LocationReasonTerm locationReasonTerm,
			LocationTerm locationTerm, LocationReason reason,
			DateRange dateRange)
					throws DuplicateEntityFoundException,
						OffenderMismatchException,
						LocationReasonTermConflictException,
						DateRangeOutOfBoundsException;
	
	/**
	 * Removes location reason term.
	 * 
	 * @param locationReasonTerm location reason term to remove
	 */
	void remove(LocationReasonTerm locationReasonTerm);
}