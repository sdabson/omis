package omis.audit.domain;

import java.io.Serializable;
import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Signature of an auditable action performed on a record.
 * @author Stephen Abson
 * @version 0.1.0 (Jul 26, 2012)
 * @since OMIS 3.0
 */
public interface Signature extends Serializable {

	/**
	 * Returns the account of the user the performed the auditable action.
	 * @return account of user that performed auditable action
	 */
	UserAccount getUserAccount();
	
	/**
	 * Sets the account of the user the performed the auditable action.
	 * @param userAccount account of user the performed auditable action
	 */
	void setUserAccount(UserAccount userAccount);
	
	/**
	 * Returns the date when the auditable action was performed.
	 * @return date when auditable action was performed
	 */
	Date getDate();
	
	/**
	 * Sets the date when the auditable action was performed.
	 * @param date date when auditable action was performed
	 */
	void setDate(Date date);
}