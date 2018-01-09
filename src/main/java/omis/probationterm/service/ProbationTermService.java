package omis.probationterm.service;

import java.util.Date;

import omis.courtcase.domain.CourtCase;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.exception.ProbationTermConflictException;
import omis.probationterm.exception.ProbationTermExistsAfterException;
import omis.probationterm.exception.ProbationTermExistsException;
import omis.term.domain.component.Term;

/**
 * Probation term service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (May 24, 2017)
 * @since OMIS 3.0
 */
public interface ProbationTermService {

	/**
	 * Creates a new probation term.
	 * 
	 * @param courtCase court case
	 * @param term term
	 * @param startDate start date
	 * @param terminationDate termination date
	 * @param expirationDate expiration date
	 * @param jailCredit jail credit
	 * @param sentenceDays sentence days
	 * @return probation term
	 * @throws ProbationTermExistsException thrown when probation term exists
	 * @throws ProbationTermConflictException thrown when probation term 
	 * conflicts with another probation term
	 * @throws ProbationTermExistsAfterException thrown when a probation term 
	 * exists after a null end dated probation term
	 */
	ProbationTerm create(CourtCase courtCase, Term term, Date startDate,
			Date terminationDate, Date expirationDate, Integer jailCredit,
			Integer sentenceDays) 
					throws ProbationTermExistsException, 
					ProbationTermConflictException, 
					ProbationTermExistsAfterException;
	
	/**
	 * Updates an existing probation term.
	 * 
	 * @param probationTerm probation term
	 * @param term term
	 * @param startDate start date
	 * @param terminationDate termination date
	 * @param expirationDate expiration date
	 * @param jailCredit jail credit
	 * @param sentenceDays sentence days
	 * @return probation term
	 * @throws ProbationTermExistsException thrown when probation term exists
	 * @throws ProbationTermConflictException thrown when probation term 
	 * conflicts with another probation term
	 * @throws ProbationTermExistsAfterException thrown when a probation term 
	 * exists after a null end dated probation term
	 */
	ProbationTerm update(ProbationTerm probationTerm, Term term, 
			Date startDate, Date terminationDate, Date expirationDate,
			Integer jailCredit, Integer sentenceDays) 
					throws ProbationTermExistsException,
					ProbationTermConflictException, 
					ProbationTermExistsAfterException;
	
	/**
	 * Removes the specified probation term.
	 * 
	 * @param probationTerm probation term
	 */
	void remove(ProbationTerm probationTerm);
	
	/**
	 * Calculates how many days are contained within the term.
	 * 
	 * @param term term
	 * @return days contain with the term
	 */
	Integer calculateTotalProbationTermDays(Term term);

	/**
	 * Calculates the probation expiration date for the specified start date and 
	 * sentence days.
	 * 
	 * @param startDate start date
	 * @param sentenceDays sentence days
	 * @return probation expiration date
	 */
	Date calculateProbationExpirationDate(Date startDate, Integer sentenceDays);
}
