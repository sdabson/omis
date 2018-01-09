package omis.user.dao;

import java.util.Date;

import omis.dao.GenericDao;
import omis.user.domain.UsedPassword;
import omis.user.domain.UserAccount;

/**
 * Data access object for used passwords.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 28, 2014)
 * @since OMIS 3.0
 */
public interface UsedPasswordDao
		extends GenericDao<UsedPassword> {

	/**
	 * Returns the used password.
	 * 
	 * @param userAccount user account
	 * @param password password
	 * @param effectiveDate effective date; if password reuse is permitted
	 * outside of a certain period of time, this used password within that
	 * time is returned; in implementations where the same password is
	 * never allowed more than once, this parameter is ignored 
	 * @return used password
	 */
	UsedPassword find(UserAccount userAccount, String password,
			Date effectiveDate);
}