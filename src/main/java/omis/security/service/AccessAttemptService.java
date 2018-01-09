package omis.security.service;

import java.util.List;

import omis.security.domain.AccessAttempt;

/**
 * Service to log and view access attempts.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (July 24, 2013)
 * @since OMIS 3.0
 */
public interface AccessAttemptService {
	
	/**
	 * Returns access attempts by username.
	 * 
	 * @param username username of access attempts to return
	 * @return access attempts for specified username
	 */
	List<AccessAttempt> findByUsername(String username);
}