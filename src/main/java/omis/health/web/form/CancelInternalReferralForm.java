package omis.health.web.form;

import java.io.Serializable;

import omis.health.domain.InternalReferralStatusReason;

/**
 * Form to cancel an internal referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class CancelInternalReferralForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private InternalReferralStatusReason statusReason;
	
	/** Instantiates a form to cancel an internal referral. */
	public CancelInternalReferralForm() {
		// Default instantiation
	}

	/**
	 * Returns the status reason.
	 * 
	 * @return status reason
	 */
	public InternalReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason status reason
	 */
	public void setStatusReason(
			final InternalReferralStatusReason statusReason) {
		this.statusReason = statusReason;
	}
}