package omis.security.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.security.domain.AccessAttempt;

/**
 * Data access object for access attempts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 31, 2012)
 * @since OMIS 3.0
 */
public interface AccessAttemptDao
		extends GenericDao<AccessAttempt> {

	/**
	 * Returns access attempts by username.
	 * 
	 * @param username username the access attempts of which to return
	 * @return access attempts for the specified username
	 */
	List<AccessAttempt> findByUsername(String username);
}