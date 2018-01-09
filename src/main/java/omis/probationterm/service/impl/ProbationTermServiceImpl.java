package omis.probationterm.service.impl;

import java.util.Date;

import omis.courtcase.domain.CourtCase;
import omis.offender.domain.Offender;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.exception.ProbationTermConflictException;
import omis.probationterm.exception.ProbationTermExistsAfterException;
import omis.probationterm.exception.ProbationTermExistsException;
import omis.probationterm.service.ProbationTermService;
import omis.probationterm.service.delegate.ProbationTermDelegate;
import omis.term.domain.component.Term;
import omis.term.service.delegate.TermCalculatorDelegate;
import omis.util.DateManipulator;

/**
 * Implementation of service for probation terms.
 * 
 * @author Josh Divine
 * @version 0.1.0 (May 24, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermServiceImpl implements ProbationTermService {

	private final ProbationTermDelegate probationTermDelegate;
	
	private final TermCalculatorDelegate termCalculatorDelegate;
	
	/**
	 * Instantiates an implementation of service for probation terms with the
	 * specified delegate.
	 * 
	 * @param probationTermDelegate probation term delegate
	 */
	public ProbationTermServiceImpl(
			final ProbationTermDelegate probationTermDelegate,
			final TermCalculatorDelegate termCalculatorDelegate) {
		this.probationTermDelegate = probationTermDelegate;
		this.termCalculatorDelegate = termCalculatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public ProbationTerm create(final CourtCase courtCase, final Term term, 
			final Date startDate, final Date terminationDate,
			final Date expirationDate, final Integer jailCredit,
			final Integer sentenceDays)
			throws ProbationTermExistsException, ProbationTermConflictException, 
			ProbationTermExistsAfterException {
		Offender offender = (Offender) courtCase.getDocket().getPerson();
		return this.probationTermDelegate.create(offender, courtCase, term, 
				startDate, terminationDate, expirationDate, jailCredit,
				sentenceDays);
	}

	/** {@inheritDoc} */
	@Override
	public ProbationTerm update(final ProbationTerm probationTerm, 
			final Term term, final Date startDate, final Date terminationDate, 
			final Date expirationDate, final Integer jailCredit, 
			final Integer sentenceDays) throws ProbationTermExistsException, 
			ProbationTermConflictException, ProbationTermExistsAfterException {
		return this.probationTermDelegate.update(probationTerm, term, startDate, 
				terminationDate, expirationDate, jailCredit, sentenceDays);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final ProbationTerm probationTerm) {
		this.probationTermDelegate.remove(probationTerm);
	}

	/** {@inheritDoc} */
	@Override
	public Integer calculateTotalProbationTermDays(final Term term) {
		return this.termCalculatorDelegate.calculateTotalDays(term);
	}

	/** {@inheritDoc} */
	@Override
	public Date calculateProbationExpirationDate(final Date startDate, 
			final Integer sentenceDays) {
		DateManipulator dateManipulator = new DateManipulator(startDate);
		dateManipulator.changeDate(sentenceDays);
		return dateManipulator.getDate();
	}

}
