package omis.audit.service.impl;

import java.util.List;

import omis.audit.dao.VerificationMethodDao;
import omis.audit.domain.VerificationMethod;
import omis.audit.service.VerificationMethodService;

/**
 * Implementation of service for verification methods.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2014)
 * @since OMIS 3.0
 */
public class VerificationMethodServiceImpl
		implements VerificationMethodService {

	private final VerificationMethodDao verificationMethodDao;
	
	/**
	 * Instantiates an implementation of service for verification methods.
	 * 
	 * @param verificationMethodDao data access object for verification methods
	 */
	public VerificationMethodServiceImpl(
			final VerificationMethodDao verificationMethodDao) {
		this.verificationMethodDao = verificationMethodDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<VerificationMethod> findAll() {
		return this.verificationMethodDao.findAll();
	}
}