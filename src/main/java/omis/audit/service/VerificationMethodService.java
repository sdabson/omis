package omis.audit.service;

import java.util.List;

import omis.audit.domain.VerificationMethod;

/**
 * Service for verification methods.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 24, 2014)
 * @since OMIS 3.0
 */
public interface VerificationMethodService {

	/**
	 * Returns verification methods.
	 * 
	 * @return verification methods
	 */
	List<VerificationMethod> findAll();
}