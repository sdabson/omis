package omis.condition.service.impl;

import java.util.List;

import omis.condition.domain.Agreement;
import omis.condition.service.AgreementService;
import omis.condition.service.delegate.AgreementDelegate;
import omis.offender.domain.Offender;

/**
 * Service for Agreements Implementation.
 * 
 * @author Jonny Santy
 * @author Trevor Isles
 * @version 0.1.2 (July 7, 2016)
 * @since OMIS 3.0
 */
public class AgreementServiceImpl 
		implements AgreementService{

	private final AgreementDelegate agreementDelegate;

	/**
	 * Instantiates a implementation of service for agreements with the
	 * specified data access object.
	 * 
	 * @param agreementDao data access object for agreements
	 */
	public AgreementServiceImpl(final AgreementDelegate agreementDelegate) {
		this.agreementDelegate = agreementDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public List<Agreement> findByOffender(final Offender offender) {
		return this.agreementDelegate.findByOffender(offender);
	}
	
	

}
