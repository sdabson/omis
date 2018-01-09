package omis.stg.domain;

import java.io.Serializable;
import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.person.domain.Person;

/**
 * Activity within a security threat group.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 22, 2016)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupActivity extends Serializable {

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
	 * Sets the date.
	 * 
	 * @param date date
	 */
	void setReportDate(Date date);
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	Date getReportDate();
	
	/**
	 * Returns the person reporting the activity.
	 * 
	 * @return person
	 */
	Person getReportedBy();
	
	/**
	 * Returns the person reporting the activity.
	 * 
	 * @return person
	 */
	void setReportedBy(Person reportedBy);
	
	
	/**
	 * Sets the summary.
	 * 
	 * @return summary
	 */
	void setSummary(String summary);
	
	/**
	 * Returns the summary.
	 * 
	 * @return summary
	 */
	String getSummary();
	
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
