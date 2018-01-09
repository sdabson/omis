package omis.audit;

import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Retrieves components for audit signatures.
 * 
 * <p>Where and if the components are stored is up the implementation.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 12, 2014)
 * @since OMIS 3.0
 */
public interface AuditComponentRetriever {

	/**
	 * Returns the user account.
	 * 
	 * @return user account
	 */
	UserAccount retrieveUserAccount();
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date retrieveDate();
}