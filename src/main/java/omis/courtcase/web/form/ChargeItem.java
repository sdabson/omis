package omis.courtcase.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.courtcase.domain.Charge;
import omis.offense.domain.Offense;

/**
 * Charge item on a court case form.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.1 (Aug 11, 2017)
 * @since OMIS 3.0
 */
public class ChargeItem
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Charge charge;
	
	private ChargeItemOperation operation;
	
	private Date date;
	
	private Date fileDate;
	
	private Offense offense;
	
	private Integer count;
	
	/** Instantiates a default charge on a court case form. */
	public ChargeItem() {
		// Default instantiation
	}
	
	/** Returns charge.
	 * @return charge. */
	public Charge getCharge() {
		return this.charge;
	}
	
	/** Sets charge.
	 * @param charge - charge. */
	public void setCharge(final Charge charge) {
		this.charge = charge;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation.
	 */
	public ChargeItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets operation of charge item.
	 * 
	 * @param operation - operation.
	 */
	public void setOperation(final ChargeItemOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the file date.
	 * 
	 * @return file date
	 */
	public Date getFileDate() {
		return this.fileDate;
	}

	/**
	 * Sets the file date.
	 * 
	 * @param fileDate file date
	 */
	public void setFileDate(final Date fileDate) {
		this.fileDate = fileDate;
	}

	/**
	 * Returns the offense.
	 * 
	 * @return offense
	 */
	public Offense getOffense() {
		return this.offense;
	}

	/**
	 * Sets the offense.
	 * 
	 * @param offense offense
	 */
	public void setOffense(final Offense offense) {
		this.offense = offense;
	}

	/**
	 * Returns the count.
	 * 
	 * @return count
	 */
	public Integer getCount() {
		return this.count;
	}

	/**
	 * Sets the count.
	 * 
	 * @param count count
	 */
	public void setCount(final Integer count) {
		this.count = count;
	}
}