package omis.employment.domain.component;

import omis.employment.domain.WagePaymentFrequency;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Wage
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Jan 30, 2015)
 * @since: OMIS 3.0
 */
public class Wage implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal amount;
	private Integer hoursPerWeek;
	private WagePaymentFrequency wagePaymentFrequency;
	
	/** Instantiates a default wage. */
	public Wage() {
		// Do nothing
	}
	
	/**
	 * Sets the amount of wage.
	 * 
	 * @param amount amount
	 */
	public void setAmount(final BigDecimal amount){
		this.amount = amount;
	}
	
	/**
	 * Gets the amount of wage.
	 * 
	 * @return amount
	 */
	public BigDecimal getAmount(){
		return this.amount;
	}
	
	/**
	 * Sets hours  per week
	 * 
	 * @param hoursPerWeek hours per week
	 */
	public void setHoursPerWeek(final Integer hoursPerWeek){
		this.hoursPerWeek = hoursPerWeek;
	}
	
	/**
	 * Gets hours per week.
	 * 
	 * @return hoursPerWeek hours per week
	 */
	public Integer getHoursPerWeek(){
		return this.hoursPerWeek;
	}
	
	/**
	 * Sets how often this offender got paid
	 * 
	 * @param wagePaymentFrequency wage payment frequency
	 * @param  
	 */
	public void setWagePaymentFrequency(final WagePaymentFrequency wagePaymentFrequency){
		this.wagePaymentFrequency = wagePaymentFrequency;
	}
	
	/**
	 * Gets the info on how often this offender got paid.
	 * 
	 * @return wagePaymentFrequency wage payment frequency
	 */
	public WagePaymentFrequency getWagePaymentFrequency(){
		return this.wagePaymentFrequency;
	}
}