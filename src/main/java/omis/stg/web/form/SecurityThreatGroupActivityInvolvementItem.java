package omis.stg.web.form;

import java.io.Serializable;

import omis.offender.domain.Offender;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;

public class SecurityThreatGroupActivityInvolvementItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private SecurityThreatGroupActivityInvolvement activityInvolvement;
	
	private Offender offender;
	
	private String narrative;
	
	private SecurityThreatGroupActivityInvolvementItemOperation operation;

	public SecurityThreatGroupActivityInvolvementItemOperation getOperation() {
		return operation;
	}

	public void setOperation(SecurityThreatGroupActivityInvolvementItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Instantiates a default instance of security threat group activity 
	 * involvement item.
	 */
	public SecurityThreatGroupActivityInvolvementItem() {
		//Default constructor.
	}

	/** Returns the security threat group activity involvement.
	 * @return activityInvolvement
	 */
	public SecurityThreatGroupActivityInvolvement getActivityInvolvement() {
		return activityInvolvement;
	}

	/** Sets the security threat group activity involvement.
	 * @param activityInvolvement
	 */
	public void setActivityInvolvement(SecurityThreatGroupActivityInvolvement activityInvolvement) {
		this.activityInvolvement = activityInvolvement;
	}

	/** Returns the offender.
	 * @return offender
	 */
	public Offender getOffender() {
		return offender;
	}

	/** Sets the offender.
	 * @param offender
	 */
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/** Returns the security threat group activity narrative.
	 * @return narrative
	 */
	public String getNarrative() {
		return narrative;
	}

	/** Sets the security threat group activity narrative
	 * @param narrative
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	
	
}
