package omis.security.service.impl;

import java.util.List;

import omis.security.dao.AccessAttemptDao;
import omis.security.domain.AccessAttempt;
import omis.security.service.AccessAttemptService;

/**
 * Implementation of service to log and view access attempts.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (July 25, 2013)
 * @since OMIS 3.0
 */
public class AccessAttemptServiceImpl
		implements AccessAttemptService {
	
	private final AccessAttemptDao accessAttemptDao;
	
	/**
	 * Instantiates a service for access attempts.
	 * 
	 * @param accessAttemptDao data access object for access attempts
	 */
	public AccessAttemptServiceImpl(
			final AccessAttemptDao accessAttemptDao) {
		this.accessAttemptDao = accessAttemptDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AccessAttempt> findByUsername(final String username) {
		return this.accessAttemptDao.findByUsername(username);
	}
}