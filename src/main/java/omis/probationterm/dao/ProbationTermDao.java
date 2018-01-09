package omis.probationterm.dao;

import java.util.Date;
import java.util.List;

import omis.courtcase.domain.CourtCase;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.probationterm.domain.ProbationTerm;

/**
 * Probation term data access object.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.1 (November 21, 2017)
 * @since OMIS 3.0
 */
public interface ProbationTermDao extends GenericDao<ProbationTerm> {

	/**
	 * Returns the probation term matching the specified business key.
	 * 
	 * @param offender offender
	 * @param courtCase court case
	 * @param startDate start date
	 * @return probation term
	 */
	ProbationTerm find(Offender offender, CourtCase courtCase, Date startDate);
	
	/**
	 * Returns the probation term matching the specified business key excluding 
	 * the specified probation term.
	 * 
	 * @param excludedProbationTerm excluded probation term
	 * @param offender offender
	 * @param courtCase court case
	 * @param startDate start date
	 * @return probation term
	 */
	ProbationTerm findExcluding(ProbationTerm excludedProbationTerm, 
			Offender offender, CourtCase courtCase, Date startDate);

	/**
	 * Returns the probation term for the specified offender and court case on 
	 * the specified date.
	 * 
	 * @param offender offender
	 * @param courtCase court case
	 * @param date date
	 * @return probation term
	 */
	ProbationTerm findByOffenderAndCourtCaseOnDate(Offender offender, 
			CourtCase courtCase, Date date);

	/**
	 * Returns number of probation terms after the specified date that are not
	 * excluded.
	 * 
	 * @param offender offender
	 * @param courtCase court case
	 * @param date date
	 * @param excludedProbationTerm excluded probation term
	 * @return number of probation terms after the specified date
	 */
	long countAfterDateExcluding(Offender offender, CourtCase courtCase, 
			Date date, ProbationTerm excludedProbationTerm);

	/**
	 * Returns number of probation terms between the specified dates that are 
	 * not excluded.
	 * 
	 * @param offender offender
	 * @param courtCase court case
	 * @param startDate start date
	 * @param terminationDate termination date
	 * @param excludedProbationTerms excluded probation terms
	 * @return number of probation terms between the specified dates
	 */
	long countExcluding(Offender offender, CourtCase courtCase, Date startDate, 
			Date terminationDate, ProbationTerm... excludedProbationTerms);
	
	/**
	 * Returns probation terms for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return probation terms
	 */
	List<ProbationTerm> findByCourtCase(CourtCase courtCase);
}
