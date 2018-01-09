package omis.audit.domain;

import java.util.Date;
import omis.user.domain.UserAccount;

/**
 * Authorization Signature.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 14, 2013)
 * @since OMIS 3.0
 */
public class AuthorizationSignature extends AbstractSignature {

	private static final long serialVersionUID = 1L;
	
	/**Instantiates a default authorization signature.*/
	public AuthorizationSignature() {
		//Do nothing
	}
	
	/**
	 * Instantiates a authorization signature with the specified
	 * user account and date.
	 * 
	 * @param userAccount user account
	 * @param date date
	 */
	public AuthorizationSignature(final UserAccount userAccount, 
			final Date date) {
		super(userAccount, date);
	}

}
