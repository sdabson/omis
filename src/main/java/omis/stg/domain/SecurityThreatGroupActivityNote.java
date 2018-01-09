package omis.stg.domain;

import java.io.Serializable;
import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;

/**
 * Activity note within a security threat group.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 28, 2014)
 * @since OMIS 3.0
 */

public interface SecurityThreatGroupActivityNote extends Serializable {

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
	 * Sets the date.
	 * 
	 * @param date date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date getDate();
	
	/**
	 * Sets the value.
	 * 
	 * @return value
	 */
	void setValue(String value);
	
	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	String getValue();
	
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

