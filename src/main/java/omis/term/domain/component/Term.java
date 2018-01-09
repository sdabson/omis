package omis.term.domain.component;

import java.io.Serializable;

/**
 * Term.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 30, 2013)
 * @since OMIS 3.0
 */
public class Term
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer years;
	
	private Integer months;
	
	private Integer days;
	
	/** Instantiates a default term. */
	public Term() {
		// Default instantiation
	}
	
	/**
	 * Instantiates a term.
	 * 
	 * @param years years
	 * @param months months
	 * @param days days
	 */
	public Term(final Integer years, final Integer months, final Integer days) {
		this.years = years;
		this.months = months;
		this.days = days;
	}
	
	/**
	 * Sets the years.
	 * 
	 * @param years years
	 */
	public void setYears(final Integer years) {
		this.years = years;
	}
	
	/**
	 * Returns the years.
	 * 
	 * @return years
	 */
	public Integer getYears() {
		return this.years;
	}
	
	/**
	 * Sets the months.
	 * 
	 * @param months months
	 */
	public void setMonths(final Integer months) {
		this.months = months;
	}
	
	/**
	 * Returns the months.
	 * 
	 * @return months
	 */
	public Integer getMonths() {
		return this.months;
	}
	
	/**
	 * Sets the days.
	 * 
	 * @param days days
	 */
	public void setDays(final Integer days) {
		this.days = days;
	}
	
	/**
	 * Returns the days.
	 * 
	 * @return days
	 */
	public Integer getDays() {
		return this.days;
	}
	
	/**
	 * Determines whether {@code this} and {code obj} are equal.
	 * 
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Term)) {
			return false;
		}
		Term that = (Term) obj;
		if (this.getYears() != null) {
			if (!this.getYears().equals(that.getYears())) {
				return false;
			}
		} else if (that.getYears() != null) {
			return false;
		}
		if (this.getMonths() != null) {
			if (!this.getMonths().equals(that.getMonths())) {
				return false;
			}
		} else if (that.getMonths() != null) {
			return false;
		}
		if (this.getDays() != null) {
			if (!this.getDays().equals(that.getDays())) {
				return false;
			}
		} else if (that.getDays() != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Generates and returns a hash code.
	 * 
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getYears() != null) {
			hashCode = 29 * hashCode + this.getYears().hashCode();
		}
		if (this.getMonths() != null) {
			hashCode = 31 * hashCode + this.getMonths().hashCode();
		}
		if (this.getDays() != null) {
			hashCode = 33 * hashCode + this.getDays().hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Returns a string containing the years, months and days.
	 * 
	 * @return string containing years, months and days
	 */
	@Override
	public String toString() {
		return String.format("%d years %d months %d days",
				this.getYears(), this.getMonths(), this.getDays());
	}
}