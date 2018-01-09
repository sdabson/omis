package omis.user.service;

import java.util.Date;

import omis.user.domain.UserAccount;
import omis.user.exception.IdenticalPasswordException;
import omis.user.exception.PasswordUsedException;

/**
 * Service to change password.
 * 
 * <p>This service should be invoked by the user the password of an account
 * of which is to be changed. This service should not be used by user account
 * managers.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 2, 2014)
 * @since OMIS 3.0
 */
public interface ChangePasswordService {

	/**
	 * Changes password.
	 * 
	 * @param userAccount user account
	 * @param password encrypted password
	 * @param neverExpires whether password never expires
	 * @param changeDate date on which password is changed  
	 * @return user account
	 * @throws PasswordUsedException if the password has been used for the
	 * user
	 * @throws IdenticalPasswordException if an attempt is made to reuse
	 * current password
	 */
	UserAccount change(UserAccount userAccount, String password,
			boolean neverExpires, Date changeDate)
			throws PasswordUsedException, IdenticalPasswordException;
	
	/**
	 * Returns whether password for is already used.
	 * 
	 * @param userAccount user account
	 * @param password password
	 * @param changeDate date on which change is to occur
	 * @return whether password for user account is already used
	 */
	boolean passwordUsed(UserAccount userAccount, String password,
			Date changeDate);
}