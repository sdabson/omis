package omis.religion.report;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Summary of religious preference.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousPreferenceSummary
		extends Serializable {

	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Returns the religion name.
	 * 
	 * @return religion name
	 */
	String getReligionName();
	
	/**
	 * Returns the name of the religious group.
	 * 
	 * @return name of religious group
	 */
	String getReligionGroupName();
	
	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	Date getStartDate();
	
	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	Date getEndDate();
	
	/**
	 * Returns the authorized accommodation names.
	 * 
	 * @return authorized accommodation names
	 */
	List<String> getAuthorizedAccommodationNames();
	
	/**
	 * Returns whether the preference is approved.
	 * 
	 * @return whether preference is approved
	 */
	Boolean getApproved();
}