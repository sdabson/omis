package omis.security.service.delegate;

import java.util.List;

import omis.security.dao.AccessAttemptDao;
import omis.security.domain.AccessAttempt;

/**
 * Delegate for access attempts.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 8, 2016)
 * @since OMIS 3.0
 */
public class AccessAttemptDelegate {

	/* Data access objects. */
	
	private final AccessAttemptDao accessAttemptDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for access attempts.
	 * 
	 * @param accessAttemptDao data access object for access attempts
	 */
	public AccessAttemptDelegate(
			final AccessAttemptDao accessAttemptDao) {
		this.accessAttemptDao = accessAttemptDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns access attempts by username.
	 * 
	 * @param username username
	 * @return access attempts by username
	 */
	public List<AccessAttempt> findByUsername(final String username) {
		return this.accessAttemptDao.findByUsername(username);
	}
}