package omis.term.service.delegate;

import java.util.Date;
import java.util.GregorianCalendar;

import omis.term.domain.component.Term;

/**
 * Implementation of term calculator.
 * 
 * <p>This calculator takes into account leap years and months of varying
 * length. 
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public class TermCalculatorDelegateStandard
		implements TermCalculatorDelegate {
	
	/* Constants. */
	
	private static final int DAYS_IN_YEAR = 365;
	
	private static final int DAYS_IN_MONTH = 30;

	/** Implementation of term calculator. */
	public TermCalculatorDelegateStandard() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public Date calculate(final Term term, final Date commencementDate) {
		return this.calculate(term.getYears(), term.getMonths(), term.getDays(),
				commencementDate);
	}

	/** {@inheritDoc} */
	@Override
	public Date calculate(final int years, final int months,
			final int days, final Date commencementDate) {
		if (commencementDate == null) {
			throw new IllegalArgumentException("Commencement date required");
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(commencementDate.getTime());
		cal.add(GregorianCalendar.YEAR, years);
		cal.add(GregorianCalendar.MONTH, months);
		cal.add(GregorianCalendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}

	/** {@inheritDoc} */
	@Override
	public Date addYears(final int years, final Date commencementDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(commencementDate.getTime());
		cal.add(GregorianCalendar.YEAR, years);
		return cal.getTime();
	}

	/** {@inheritDoc} */
	@Override
	public Date addMonths(final int months, final Date commencementDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(commencementDate.getTime());
		cal.add(GregorianCalendar.MONTH, months);
		return cal.getTime();
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
			totalDays = term.getYears()
					* TermCalculatorDelegateStandard.DAYS_IN_YEAR;
		} else {
			totalDays = 0;
		}
		if (term.getMonths() != null) {
			totalDays = totalDays + (term.getMonths()
					* TermCalculatorDelegateStandard.DAYS_IN_MONTH);
		}
		if (term.getDays() != null) {
			totalDays = totalDays + term.getDays();
		}
		return totalDays;
	}
}