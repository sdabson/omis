package omis.health.web.form;

import java.io.Serializable;

import omis.health.domain.LabWorkReferralStatusReason;

/**
 * Cancel lab work referral form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public class CancelLabWorkReferralForm implements Serializable {

	/* Property declaration. */
	
	private static final long serialVersionUID = 1L;
	private LabWorkReferralStatusReason statusReason;
	
	/**
	 * Instantiates a default instance of cancel lab work referral
	 * form.
	 */
	public CancelLabWorkReferralForm() {
		//Default constructor.
	}

	/**
	 * Returns the status reason.
	 * 
	 * @return lab work referral status reason
	 */
	public LabWorkReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason lab work referral status reason.
	 */
	public void setStatusReason(
			final LabWorkReferralStatusReason statusReason) {
		this.statusReason = statusReason;
	}
}