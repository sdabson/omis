/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
	 * Instantiates term.
	 * 
	 * <p>Copies supplied {@code term}.
	 * 
	 * @param term term to copy
	 */
	public Term(final Term term) {
		this.years = term.getYears();
		this.months = term.getMonths();
		this.days = term.getDays();
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
	 * Returns whether {@code this} is longer than {@code term}.
	 * 
	 * <p>Returns {@code false} if the terms are equal.
	 * 
	 * <p>Functions symmetrically to {@link Term#shorterThan}.
	 * 
	 * @param term term to which to compare {@code this}
	 * @return {@code true} if {@code this} is longer than {@code term};
	 * {@code false} otherwise including if equal
	 */
	public boolean longerThan(final Term term) {
		if (this.getYears() != null && term.getYears() != null) {
			if (this.getYears() > term.getYears()) {
				return true;
			}
		} else if (term.getYears() == null) {
			if (this.getYears() != null) {
				return true;
			}
		}
		if (this.getMonths() != null && term.getMonths() != null) {
			if (this.getMonths() > term.getMonths()) { 
				return true;
			}
		} else if (term.getMonths() == null) {
			if (this.getMonths() != null) {
				return true;
			}
		}
		if (this.getDays() != null && term.getDays() != null) {
			if (this.getDays() > term.getDays()) {
				return true;
			}
		} else if (term.getDays() == null) {
			if (this.getDays() != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns whether {@code this} is shorter than {@code term}.
	 * 
	 * <p>Returns {@code false} if terms are equal.
	 * 
	 * <p>Functions symmetrically to {@link Term#longerThan}.
	 * 
	 * @param term term to which to compare {@code this}
	 * @return {@code true} if {@code this} is shorter than {@code term};
	 * {@code false} otherwise including if equal
	 */
	public boolean shorterThan(final Term term) {
		if (this.getYears() != null && term.getYears() != null) {
			if (this.getYears() < term.getYears()) {
				return true;
			}
		} else if (this.getYears() == null) {
			if (term.getYears() != null) {
				return true;
			}
		}
		if (this.getMonths() != null && term.getMonths() != null) {
			if (this.getMonths() < term.getMonths()) {
				return true;
			}
		} else if (this.getMonths() == null) {
			if (term.getMonths() != null) {
				return true;
			}
		}
		if (this.getDays() != null && term.getDays() != null) {
			if (this.getDays() < term.getDays()) {
				return true;
			}
		} else if (this.getDays() == null) {
			if (term.getDays() != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns difference between {@code this} and {@code term}.
	 * 
	 * @param term term
	 * @return difference between {@code this} and {@code term}
	 */
	public Term difference(final Term term) {
		int years;
		int months;
		int days;
		if (this.getYears() != null && term.getYears() != null) {
			years = this.getYears() - term.getYears();
		} else if (term.getYears() != null) {
			years = 0 - term.getYears();
		} else {
			years = this.getYears();
		}
		if (this.getMonths() != null && term.getMonths() != null) {
			months = this.getMonths() - term.getMonths();
		} else if (term.getMonths() != null) {
			months = 0 - term.getMonths();
		} else {
			months = this.getMonths();
		}
		if (this.getDays() != null && term.getDays() != null) {
			days = this.getDays() - term.getDays();
		} else if (term.getDays() != null) {
			days = 0 - term.getDays();
		} else {
			days = this.getDays();
		}
		return new Term(years, months, days);
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