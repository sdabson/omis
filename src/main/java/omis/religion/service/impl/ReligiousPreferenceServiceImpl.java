package omis.religion.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.exception.OperationNotAuthorizedException;
import omis.offender.domain.Offender;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;
import omis.religion.service.ReligiousPreferenceService;
import omis.religion.service.delegate.ReligionDelegate;
import omis.religion.service.delegate.ReligiousAccommodationAuthorizationDelegate;
import omis.religion.service.delegate.ReligiousAccommodationDelegate;
import omis.religion.service.delegate.ReligiousPreferenceDelegate;

/**
 * Implementation of service for religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceServiceImpl
		implements ReligiousPreferenceService {

	private final ReligiousPreferenceDelegate religiousPreferenceDelegate;
	
	private final ReligionDelegate religionDelegate;
	
	private final ReligiousAccommodationDelegate religiousAccommodationDelegate;
	
	private final ReligiousAccommodationAuthorizationDelegate
			religiousAccommodationAuthorizationDelegate;
	
	private final VerificationMethodDelegate verificationMethodDelegate;
	
	
	/**
	 * Instantiates an implementation of service for religious preferences.
	 * 
	 * @param religiousPreferenceDelegate religious preference delegate
	 * @param religionDelegate religion delegate
	 * @param religiousAccommodationDelegate religious accommodation delegate
	 * @param religiousAccommodationAuthorizationDelegate religious 
	 * accommodation authorization delegate
	 * @param verificationMethodDelegate verification method delegate
	 */
	public ReligiousPreferenceServiceImpl(
			final ReligiousPreferenceDelegate religiousPreferenceDelegate,
			final ReligionDelegate religionDelegate,
			final ReligiousAccommodationDelegate religiousAccommodationDelegate,
			final ReligiousAccommodationAuthorizationDelegate
				religiousAccommodationAuthorizationDelegate,
			final VerificationMethodDelegate verificationMethodDelegate) {
		this.religiousPreferenceDelegate = religiousPreferenceDelegate;
		this.religionDelegate = religionDelegate;
		this.religiousAccommodationDelegate = religiousAccommodationDelegate;
		this.religiousAccommodationAuthorizationDelegate
			= religiousAccommodationAuthorizationDelegate;
		this.verificationMethodDelegate = verificationMethodDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean isAccommodationAuthorized(
			final ReligiousPreference preference,
			final ReligiousAccommodation accommodation) {
		return this.religiousAccommodationAuthorizationDelegate
				.find(preference, accommodation) != null;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousAccommodationAuthorization authorizeAccommodation(
			final ReligiousPreference preference,
			final ReligiousAccommodation accommodation) 
				throws OperationNotAuthorizedException {
		return this.religiousAccommodationAuthorizationDelegate.authorize(
				preference, accommodation);
	}

	/** {@inheritDoc} */
	@Override
	public void removeAccommodationAuthorization(
			final ReligiousPreference preference,
			final ReligiousAccommodation accommodation)
					throws OperationNotAuthorizedException {
		this.religiousAccommodationAuthorizationDelegate.remove(preference, 
				accommodation);
	}

	/** {@inheritDoc} */
	@Override
	public List<Religion> findReligions() {
		return this.religionDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<ReligiousAccommodation> findAccommodations() {
		return this.religiousAccommodationDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public ReligiousPreference saveInitial(
			final Offender offender, final Religion religion,
			final Date startDate) throws DuplicateEntityFoundException {
		return this.religiousPreferenceDelegate.createInitial(offender, 
				religion, startDate);
	}

	/** {@inheritDoc} */
	@Override
	public List<ReligiousPreference> findByOffender(final Offender offender) {
		return this.religiousPreferenceDelegate.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final ReligiousPreference preference) {
		this.religiousAccommodationAuthorizationDelegate
			.removeByPreference(preference);
		this.religiousPreferenceDelegate.remove(preference);
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousPreference findByOffenderOnDate(
			final Offender offender, final Date date) {
		return this.religiousPreferenceDelegate.findByOffenderOnDate(offender, 
				date);
	}

	/** {@inheritDoc} */
	@Override
	public boolean existByOffenderOnDate(final Offender offender,
			final Date date) {
		return this.religiousPreferenceDelegate.findByOffenderOnDate(offender, 
				date) != null;
	}

	/** {@inheritDoc} */
	@Override
	public ReligiousPreference save(final Offender offender,
			final Religion religion, final DateRange dateRange,
			final String comment,
			final VerificationSignature verificationSignature)
				throws DuplicateEntityFoundException, DateConflictException,
					OperationNotAuthorizedException {
		return this.religiousPreferenceDelegate.save(offender, religion, 
				dateRange, comment, verificationSignature);
	}
	
	/** {@inheritDoc} */
	@Override
	public ReligiousPreference update(
			final ReligiousPreference preference, final Religion religion,
			final DateRange dateRange, final String comment,
			final VerificationSignature verificationSignature) 
				throws DuplicateEntityFoundException, DateConflictException,
					OperationNotAuthorizedException {
		return this.religiousPreferenceDelegate.update(preference, religion, 
				dateRange, comment, verificationSignature);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VerificationMethod> findVerificationMethods() {
		return this.verificationMethodDelegate.findAll();
	}
}