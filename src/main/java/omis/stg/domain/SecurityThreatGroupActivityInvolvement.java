package omis.stg.domain;

import java.io.Serializable;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;

/**
 * Involvement within a security threat group activity.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 28, 2016)
 * @since OMIS 3.0
 */

public interface SecurityThreatGroupActivityInvolvement extends Serializable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the activity.
	 * 
	 * @return activity
	 */
	void setActivity(SecurityThreatGroupActivity activity);
	
	/**
	 * Returns the activity.
	 * 
	 * @return activity
	 */
	SecurityThreatGroupActivity getActivity();
	
	/**
	 * Sets the offender.
	 * 
	 * @return offender
	 */
	void setOffender(Offender offender);
	
	/**
	 * Returns the offender.
	 * 
	 * @return offender
	 */
	Offender getOffender();
	
	/**
	 * Sets the narrative.
	 * 
	 * @return narrative
	 */
	void setNarrative(String narrative);
	
	/**
	 * Returns the narrative.
	 * 
	 * @return narrative
	 */
	String getNarrative();
	
	/**
	 * Returns creationSignature.
	 * 
	 * @return creationSignature
	 */
	CreationSignature getCreationSignature();
	
	/**
	 * Sets creationSignature.
	 * 
	 * @return creationSignature
	 */
	void setCreationSignature(CreationSignature creationSignature);
	
	/**
	 * Returns updateSignature.
	 * 
	 * @return updateSignature
	 */
	UpdateSignature getUpdateSignature();
	
	/**
	 * Sets updateSignature.
	 * 
	 * @return updateSignature
	 */
	void setUpdateSignature(UpdateSignature updateSignature);
	
}
