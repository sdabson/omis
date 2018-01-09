package omis.religion.service;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.exception.OperationNotAuthorizedException;
import omis.offender.domain.Offender;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;

/**
 * Services for religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 22, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousPreferenceService {

	/**
	 * Returns whether the accommodation is authorized for the duration of the
	 * religious preference.
	 * 
	 * @param preference religious preference
	 * @param accommodation accommodation
	 * @return whether accommodation is authorized during preference
	 */
	boolean isAccommodationAuthorized(ReligiousPreference preference,
			ReligiousAccommodation accommodation);
	
	/**
	 * Authorizes an accommodation for the duration of the religious preference.
	 * 
	 * <p>If the accommodation is already authorized, throws a
	 * {@code BusinessException}.
	 * 
	 * @param preference religious preferences
	 * @param accommodation accommodation
	 * @param auditUserAccount audit user account
	 * @throws BusinessException if already authorized
	 */
	ReligiousAccommodationAuthorization authorizeAccommodation(
			ReligiousPreference preference,
			ReligiousAccommodation accommodation)
					throws OperationNotAuthorizedException;
	
	/**
	 * Removes the authorization of an accommodation for the duration of the
	 * religious preference.
	 * 
	 * <p>If the accommodation is not authorized, throws a
	 * {@code BusinessException}.
	 * 
	 * @param preference religious preference
	 * @param accommodation accommodation of which the remove the authorization
	 * @throws OperationNotAuthorizedException if not authorized
	 */
	void removeAccommodationAuthorization(
			ReligiousPreference preference,
			ReligiousAccommodation accommodation)
					throws OperationNotAuthorizedException;
	
	/**
	 * Returns religions.
	 * 
	 * @return religions
	 */
	List<Religion> findReligions();
	
	/**
	 * Returns religious accommodations.
	 * 
	 * @return religious accommodations
	 */
	List<ReligiousAccommodation> findAccommodations();
	
	/**
	 * Saves the initial religious preference.
	 * 
	 * @param offender offender
	 * @param religion religion
	 * @param startDate start date
	 * @return newly created initial religious preference
	 * @throws DuplicateEntityFoundException if the religious preference
	 * already exists
	 */
	ReligiousPreference saveInitial(
			Offender offender, Religion religion, Date startDate) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Saves new religious preference.
	 * 
	 * @param offender offender
	 * @param religion religion
	 * @param dateRange date range
	 * @param comment comment
	 * @param verificationSignature verification signature
	 * @return newly created verification signature
	 * @throws DuplicateEntityFoundException if the religious preference
	 * already exists
	 * @throws DateConflictException if the religious preference date range
	 * conflicts with existing religious preferences
	 * @throws OperationNotAuthorizedException if an attempt was made to
	 * enter a religious preference within the minimum amount of time required
	 * between changes without an authorized user
	 */
	ReligiousPreference save(
			Offender offender, Religion religion, DateRange dateRange,
			String comment,	VerificationSignature verificationSignature)
				throws DuplicateEntityFoundException, DateConflictException,
					OperationNotAuthorizedException;
	
	/**
	 * Updates an existing religious preference.
	 * 
	 * @param preference preference
	 * @param religion religion
	 * @param dateRange date range
	 * @param comment comment
	 * @param verificationSignature verification signature
	 * @return updated religious preference
	 * @throws DuplicateEntityFoundException if the religious preference
	 * already exists
	 * @throws DateConflictException if the religious preference date range
	 * conflicts with existing religious preferences
	 * @throws OperationNotAuthorizedException if an attempt was made to
	 * enter a religious preference within the minimum amount of time required
	 * between changes without an authorized user
	 */
	ReligiousPreference update(ReligiousPreference preference,
			Religion religion, DateRange dateRange, String comment,
			VerificationSignature verificationSignature)
					throws DuplicateEntityFoundException, DateConflictException,
					OperationNotAuthorizedException;
	
	/**
	 * Returns religious preferences for offender.
	 * 
	 * @param offender offender
	 * @return religious preferences for offender
	 */
	List<ReligiousPreference> findByOffender(Offender offender);
	
	/**
	 * Returns religious preference for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return religious preference for offender on date
	 */
	ReligiousPreference findByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Returns whether there are any active religious preferences for the
	 * offender on the date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return whether there are any active religious preferences for offender
	 * on date
	 */
	boolean existByOffenderOnDate(Offender offender, Date date);
	
	/**
	 * Removes a religious preferences.
	 * 
	 * @param preference religious preference to remove
	 */
	void remove(ReligiousPreference preference);

	/**
	 * Returns verification methods.
	 * 
	 * @return verification methods
	 */
	List<VerificationMethod> findVerificationMethods();
}