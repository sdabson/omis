package omis.audit.domain;

import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Update signature of a the modification of recorded information.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 26, 2012)
 * @since OMIS 3.0
 */
public class UpdateSignature extends AbstractSignature {

	private static final long serialVersionUID = 1L;
	
	/** Instantiates a default update signature. */
	public UpdateSignature() {
		// Do nothing
	}
	
	/**
	 * Instantiates an update signature with the specified property values.
	 * 
	 * @param userAccount account of user that performed update
	 * @param date date of update
	 */
	public UpdateSignature(final UserAccount userAccount, final Date date) {
		super(userAccount, date);
	}
}