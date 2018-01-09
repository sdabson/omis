/**
 * NotificationDesignation
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 7, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.domain.component;

import java.io.Serializable;
import java.util.Date;


public class NotificationDesignation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date date;
	
	
	
	/* Constructors */
	
	public NotificationDesignation(){
		
		//nothing
	}
	
	/**
	 * Creates a notification designation with the specified field
	 * @param designationDate - designation date
	 */
	public NotificationDesignation(final Date date) {
		this.date = date;
	}





	/**
	 * Gets designation date
	 * @return designationDate - designation
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets designation date
	 * @param designationDate the designationDate to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
