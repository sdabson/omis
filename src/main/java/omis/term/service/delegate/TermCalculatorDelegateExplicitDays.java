package omis.term.service.delegate;

import java.util.Date;
import java.util.GregorianCalendar;

import omis.term.domain.component.Term;

/**
 * Implementation of term calculator where the number of days per month and
 * year can be explicitly set.
 * 
 * <p>If {@code daysInYear} is not set, years will be calculated taking into
 * account leap years.
 * 
 * <p>If {@code monthsInYear} is not set, months will be calculated taking
 * into account actual month length.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public class TermCalculatorDelegateExplicitDays
		implements TermCalculatorDelegate {

	private Integer daysInYear;
	
	private Integer daysInMonth;
	
	/**
	 * Instantiate a default implementation of term calculator with explicit
	 * days.
	 */
	public TermCalculatorDelegateExplicitDays() {
		// Default instantiation
	}
	
	/**
	 * Sets the number of days in a year.
	 * 
	 * @param daysInYear number of days in a year
	 */
	public void setDaysInYear(final int daysInYear) {
		this.daysInYear = daysInYear;
	}
	
	/**
	 * Returns the number of days in a month.
	 * 
	 * @param daysInMonth number of days in a month
	 */
	public void setDaysInMonth(final int daysInMonth) {
		this.daysInMonth = daysInMonth;
	}
	
	/** {@inheritDoc} */
	@Override
	public Date calculate(final Term term, final Date commencementDate) {
		return this.calculate(makeNullSafe(term.getYears()),
							  makeNullSafe(term.getMonths()),
							  makeNullSafe(term.getDays()),
				commencementDate);
	}

	/** {@inheritDoc} */
	@Override
	public Date calculate(final int years, final int months, final int days,
			final Date commencementDate) {
		if (commencementDate == null) {
			throw new IllegalArgumentException("Commencement date required");
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(commencementDate.getTime());
		int totalDays = days;
		if (this.daysInYear != null) {
			totalDays = totalDays + (years * this.daysInYear);
		} else {
			cal.add(GregorianCalendar.YEAR, years);
		}
		if (this.daysInMonth != null) {
			totalDays = totalDays + (months * this.daysInMonth);
		} else {
			cal.add(GregorianCalendar.MONTH, months);
		}
		cal.add(GregorianCalendar.DAY_OF_YEAR, totalDays);
		return cal.getTime();
	}

	/** {@inheritDoc} */
	@Override
	public Date addYears(final int years, final Date commencementDate) {
		if (this.daysInYear != null) {
			return addDays(years * this.daysInYear, commencementDate);
		} else {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(commencementDate.getTime());
			cal.add(GregorianCalendar.YEAR, years);
			return cal.getTime();
		}
	}

	/** {@inheritDoc} */
	@Override
	public Date addMonths(final int months, final Date commencementDate) {
		if (this.daysInYear != null) {
			return addDays(months * this.daysInMonth, commencementDate);
		} else {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTimeInMillis(commencementDate.getTime());
			cal.add(GregorianCalendar.MONTH, months);
			return cal.getTime();
		}
	}

	/** {@inheritDoc} */
	@Override
	public Date addDays(final int days, final Date commencementDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(commencementDate.getTime());
		cal.add(GregorianCalendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}
	
	/** {@inheritDoc} */
	@Override
	public int calculateTotalDays(final Term term) {
		int totalDays;
		if (term.getYears() != null) {
			totalDays = term.getYears() * this.daysInYear;
		} else {
			totalDays = 0;
		}
		if (term.getMonths() != null) {
			totalDays = totalDays + (term.getMonths() * this.daysInMonth);
		}
		if (term.getDays() != null) {
			totalDays = totalDays + term.getDays();
		}
		return totalDays;
	}
	
	// Makes null safe - return 0 if value is null
	private int makeNullSafe(final Integer value) {
		if (value != null) {
			return value;
		} else {
			return 0;
		}
	}
}