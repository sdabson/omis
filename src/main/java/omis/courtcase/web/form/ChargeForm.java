package omis.courtcase.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.offense.domain.Offense;

/**
 * Charge form.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 15, 2017)
 * @since OMIS 3.0
 */
public class ChargeForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Offense offense;
	
	private Date date;
	
	private Date fileDate;
	
	private Integer counts;
	
	/** Instantiates a default charge form. */
	public ChargeForm() {
		// Default instantiation
	}

	/**
	 * Returns the offense.
	 * 
	 * @return offense
	 */
	public Offense getOffense() {
		return offense;
	}

	/**
	 * Sets the offense.
	 * 
	 * @param offense offense
	 */
	public void setOffense(Offense offense) {
		this.offense = offense;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the file date.
	 * 
	 * @return file date
	 */
	public Date getFileDate() {
		return fileDate;
	}

	/**
	 * Sets the file date.
	 * 
	 * @param fileDate file date
	 */
	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	/**
	 * Returns the number of counts.
	 * 
	 * @return number of counts
	 */
	public Integer getCounts() {
		return counts;
	}

	/**
	 * Sets the number of counts.
	 * 
	 * @param counts number of counts
	 */
	public void setCounts(Integer counts) {
		this.counts = counts;
	}

}
