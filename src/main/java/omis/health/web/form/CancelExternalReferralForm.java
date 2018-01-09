package omis.health.web.form;

import java.io.Serializable;

import omis.health.domain.ExternalReferralStatusReason;

/**
 * Form to cancel external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 25, 2014)
 * @since OMIS 3.0
 */
public class CancelExternalReferralForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ExternalReferralStatusReason cancellationStatusReason;

	/** Instantiates a form to cancel external referrals. */
	public CancelExternalReferralForm() {
		// Default instantiation
	}

	/**
	 * Returns status reason for cancellation.
	 * 
	 * @return status reason for cancellation
	 */
	public ExternalReferralStatusReason getCancellationStatusReason() {
		return this.cancellationStatusReason;
	}

	/**
	 * Sets status reason for cancellation.
	 * 
	 * @param cancellationStatusReason status reason for cancellation
	 */
	public void setCancellationStatusReason(
			final ExternalReferralStatusReason cancellationStatusReason) {
		this.cancellationStatusReason = cancellationStatusReason;
	}
}