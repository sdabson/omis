/**
 * DetainerWarrant
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 7, 2016)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.domain.component;

import java.io.Serializable;
import java.util.Date;

public class DetainerWarrant implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date receivedDate;
	
	private Date issuedDate;
	
	private String number;

	/* Constructors */
	
	public DetainerWarrant(){
		//Nothing
		
	}
	
	/**
	 * Creates a detainer warrant with the specified field
	 * @param receivedDate - received date
	 * @param issuedDate - issued date
	 * @param number - warrant number
	 */
	public DetainerWarrant(final Date receivedDate, final Date issuedDate, 
			final String number) {
		this.receivedDate = receivedDate;
		this.issuedDate = issuedDate;
		this.number = number;
	}

	
	
	
	
	/* Getters and setters */


	/**
	 * Gets received date
	 * @return receivedDate - received date
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}

	/**
	 * Sets received date
	 * @param receivedDate the receivedDate to set
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	/**
	 * Gets issued date
	 * @return issuedDate - issued date
	 */
	public Date getIssuedDate() {
		return issuedDate;
	}

	/**
	 * Sets issued date
	 * @param issuedDate the issuedDate to set
	 */
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	/**
	 * Gets number
	 * @return number - number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets number
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
}
