package omis.identificationnumber.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.identificationnumber.service.IdentificationNumberService;
import omis.identificationnumber.service.delegate.IdentificationNumberCategoryDelegate;
import omis.identificationnumber.service.delegate.IdentificationNumberDelegate;
import omis.identificationnumber.service.delegate.IdentificationNumberIssuerDelegate;
import omis.offender.domain.Offender;

/**
 * Service for identification number service.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class IdentificationNumberServiceImpl
		implements IdentificationNumberService {
	
	/* Delegates. */
	
	private final IdentificationNumberDelegate identificationNumberDelegate;
	
	private final IdentificationNumberIssuerDelegate
	identificationNumberIssuerDelegate;
	
	private final IdentificationNumberCategoryDelegate
	identificationNumberCategoryDelegate;
	
	/* Constructors. */
	
	/**
	 * Implementation of service for identification numbers.
	 * 
	 * @param identificationNumberDelegate delegate for identification numbers
	 * @param identificationNumberIssuerDelegate delegate for identification
	 * number issuers
	 * @param identificationNumberCategoryDelegate delegate for identification
	 * number categories
	 */
	public IdentificationNumberServiceImpl(
			final IdentificationNumberDelegate identificationNumberDelegate,
			final IdentificationNumberIssuerDelegate
			identificationNumberIssuerDelegate,
			final IdentificationNumberCategoryDelegate
			identificationNumberCategoryDelegate) {
		this.identificationNumberDelegate = identificationNumberDelegate;
		this.identificationNumberIssuerDelegate
			= identificationNumberIssuerDelegate;
		this.identificationNumberCategoryDelegate
			= identificationNumberCategoryDelegate;
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public IdentificationNumber create(
			final Offender offender,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final String value,
			final Date issueDate,
			final Date expireDate)
					throws DuplicateEntityFoundException, DateConflictException {
		return this.identificationNumberDelegate.create(offender, issuer,
				category, value, issueDate, expireDate);
	}

	/** {@inheritDoc} */
	@Override
	public IdentificationNumber update(
			final IdentificationNumber identificationNumber,
			final IdentificationNumberIssuer issuer,
			final IdentificationNumberCategory category,
			final String value,
			final Date issueDate,
			final Date expireDate)
					throws DuplicateEntityFoundException, DateConflictException {
		return this.identificationNumberDelegate.update(identificationNumber,
				issuer, category, value, issueDate, expireDate);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final IdentificationNumber identificationNumber) {
		this.identificationNumberDelegate.remove(identificationNumber);
	}

	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumberIssuer> findIssuers() {
		return this.identificationNumberIssuerDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumberCategory> findCategories() {
		return this.identificationNumberCategoryDelegate.findAll();
	}
}