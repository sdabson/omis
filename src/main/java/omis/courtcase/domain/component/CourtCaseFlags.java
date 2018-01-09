package omis.courtcase.domain.component;

import java.io.Serializable;

/**
 * Flags associated with a court case. 
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseFlags 
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean criminallyConvictedYouth;
	
	private Boolean youthTransfer;
	
	private Boolean dismissed;
	
	private Boolean convictionOverturned;
	
	/** Instantiates a default association of court case flags. */
	public CourtCaseFlags() {
		// Default instantiation
	}

	/**
	 * Instantiates association of court case flags.
	 * 
	 * @param criminallyConvictedYouth whether a criminally convicted youth
	 * @param youthTransfer whether a youth transfer
	 * @param dismissed whether dismissed
	 * @param convictionOverturned whether conviction is overturned
	 */
	public CourtCaseFlags(
			final Boolean criminallyConvictedYouth,
			final Boolean youthTransfer,
			final Boolean dismissed,
			final Boolean convictionOverturned) {
		this.criminallyConvictedYouth = criminallyConvictedYouth;
		this.youthTransfer = youthTransfer;
		this.dismissed = dismissed;
		this.convictionOverturned = convictionOverturned;
	}

	/**
	 * Returns whether this case portrays the offender as a criminally 
	 * convicted youth.
	 * 
	 * @return whether offender is a criminally convicted youth
	 */
	public Boolean getCriminallyConvictedYouth() {
		return criminallyConvictedYouth;
	}

	/**
	 * Sets whether this case portrays the offender as a criminally 
	 * convicted youth.
	 * 
	 * @param criminallyConvictedYouth criminally convicted youth
	 */
	public void setCriminallyConvictedYouth(Boolean criminallyConvictedYouth) {
		this.criminallyConvictedYouth = criminallyConvictedYouth;
	}

	/**
	 * Returns whether the court case is a youth transfer.
	 * 
	 * @return whether court case is youth transfer
	 */
	public Boolean getYouthTransfer() {
		return youthTransfer;
	}

	/**
	 * Sets whether the court case is a youth transfer.
	 * 
	 * @param youthTransfer youth transfer
	 */
	public void setYouthTransfer(Boolean youthTransfer) {
		this.youthTransfer = youthTransfer;
	}

	/**
	 * Returns whether the court case was dismissed.
	 * 
	 * @return whether the court case was dismissed
	 */
	public Boolean getDismissed() {
		return dismissed;
	}

	/**
	 * Sets whether the court case was dismissed.
	 * @param dismissed dismissed
	 */
	public void setDismissed(Boolean dismissed) {
		this.dismissed = dismissed;
	}

	/**
	 * Returns whether the court case conviction was overturned.
	 * 
	 * @return whether the court case conviction was overturned
	 */
	public Boolean getConvictionOverturned() {
		return convictionOverturned;
	}

	/**
	 * Sets whether the court case conviction was overturned.
	 * @param convictionOverturned conviction overturned
	 */
	public void setConvictionOverturned(Boolean convictionOverturned) {
		this.convictionOverturned = convictionOverturned;
	}
}
