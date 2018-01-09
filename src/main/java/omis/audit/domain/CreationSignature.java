package omis.audit.domain;

import java.util.Date;

import omis.user.domain.UserAccount;


/**
 * Creation signature of the recording of information.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 26, 2012)
 * @since OMIS 3.0
 */
public class CreationSignature extends AbstractSignature {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates a default creation signature. */
	public CreationSignature() {
		// Do nothing
	}
	
	/**
	 * Instantiates a creation signature with the specified property values.
	 * 
	 * @param userAccount account of user that performed the creation
	 * @param date date of creation
	 */
	public CreationSignature(final UserAccount userAccount,
			final Date date) {
		super(userAccount, date);
	}
}