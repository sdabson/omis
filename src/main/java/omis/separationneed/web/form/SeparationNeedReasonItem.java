package omis.separationneed.web.form;

import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;

/**
 * Separation need reason item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonItem {

	private SeparationNeedReason reason;
	
	private SeparationNeedReasonAssociation association;
	
	/**
	 * Instantiates a default instance of separation need reason item.
	 */
	public SeparationNeedReasonItem() {
		//Default constructor
	}
	
	/**
	 * Instantiates an instance of separation need reason item with the
	 * specified separation need reason, whether active applies, and separation
	 * need reason association.
	 * 
	 * @param reason separation need reason
	 * @param associaiton separation need reason association
	 */
	public SeparationNeedReasonItem(final SeparationNeedReason reason,
			final SeparationNeedReasonAssociation associaiton) {
		this.reason = reason;
		this.association = associaiton;
	}

	/**
	 * Returns the separation need reason.
	 * 
	 * @return separation need reason
	 */
	public SeparationNeedReason getReason() {
		return this.reason;
	}

	/**
	 * Sets the separation need reason.
	 * 
	 * @param reason separation need reason
	 */
	public void setReason(final SeparationNeedReason reason) {
		this.reason = reason;
	}

	/**
	 * Returns the separation need reason association.
	 * 
	 * @return separation need reason association
	 */
	public SeparationNeedReasonAssociation getAssociation() {
		return this.association;
	}

	/**
	 * Sets the separation need reason association.
	 * 
	 * @param association separation need reason association
	 */
	public void setAssociation(
			final SeparationNeedReasonAssociation association) {
		this.association = association;
	}
}