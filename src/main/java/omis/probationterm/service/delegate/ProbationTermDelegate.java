package omis.probationterm.service.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.domain.CourtCase;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.probationterm.dao.ProbationTermDao;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.exception.ProbationTermConflictException;
import omis.probationterm.exception.ProbationTermExistsAfterException;
import omis.probationterm.exception.ProbationTermExistsException;
import omis.term.domain.component.Term;

/**
 * Delegate for probation terms.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.2 (Nov 21, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<ProbationTerm> probationTermInstanceFactory;
	
	/* DAOs. */
	
	private final ProbationTermDao probationTermDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate for probation terms.
	 * 
	 * @param probationTermInstanceFactory instance factory for probation terms
	 * @param probationTermDao data access object for probation terms
	 * @param auditComponentRetriever audit component retriever
	 */
	public ProbationTermDelegate(
			final InstanceFactory<ProbationTerm> probationTermInstanceFactory,
			final ProbationTermDao probationTermDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.probationTermInstanceFactory = probationTermInstanceFactory;
		this.probationTermDao = probationTermDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new probation term.
	 * 
	 * @param offender offender
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
	public ProbationTerm create(final Offender offender, 
			final CourtCase courtCase, final Term term, 
			final Date startDate, final Date terminationDate,
			final Date expirationDate, final Integer jailCredit,
			final Integer sentenceDays)
				throws ProbationTermExistsException, 
					ProbationTermConflictException, 
					ProbationTermExistsAfterException {
		if (this.probationTermDao.find(offender, courtCase,  
				startDate) != null) {
			throw new ProbationTermExistsException("Probation term exists");
		}
		ProbationTerm startProbationTerm = this.probationTermDao
				.findByOffenderAndCourtCaseOnDate(offender, courtCase, 
						startDate);
		ArrayList<ProbationTerm> excludedProbationTerms 
			= new ArrayList<ProbationTerm>();
		ProbationTerm endProbationTerm = null;
		if (startDate != null) {
			excludedProbationTerms.add(startProbationTerm);
		}
		if (terminationDate != null) {
			endProbationTerm = this.probationTermDao
					.findByOffenderAndCourtCaseOnDate(offender, courtCase, 
							terminationDate);
			if (endProbationTerm != null) {
				excludedProbationTerms.add(endProbationTerm);
			}
		} else {
			long probationCount = this.probationTermDao.countAfterDateExcluding(
					offender, courtCase, startDate, startProbationTerm);
			if (probationCount > 0) {
				throw new ProbationTermExistsAfterException(probationCount + 
					" probation term(s) exist after the specified date range.");
			}
		}
		//Throws an exception if terms exist between the start and end 
		//dates that can not be automatically adjusted
		long existingCount = this.probationTermDao.countExcluding(
				offender, courtCase, startDate, terminationDate, 
				excludedProbationTerms.toArray(new ProbationTerm[0]));
		if (existingCount > 0) {
			throw new ProbationTermConflictException("Date span covers " 
					+ existingCount + " existing probation term(s) that"
							+ " can not be adjusted automatically.");
		}
		if (startProbationTerm != null) {
			startProbationTerm.setTerminationDate(startDate);
			startProbationTerm.setUpdateSignature(this.newUpdateSignature());
			this.probationTermDao.makePersistent(startProbationTerm);
		}
		if (endProbationTerm != null) {
			endProbationTerm.setStartDate(terminationDate);
			endProbationTerm.setUpdateSignature(this.newUpdateSignature());
			this.probationTermDao.makePersistent(endProbationTerm);
		}
		ProbationTerm probationTerm = this.probationTermInstanceFactory
				.createInstance();
		this.populateProbationTerm(probationTerm, offender, courtCase, term, 
				startDate, terminationDate, expirationDate, jailCredit,
				sentenceDays);
		probationTerm.setCreationSignature(this.newCreationSignature());
		probationTerm.setUpdateSignature(this.newUpdateSignature());
		return this.probationTermDao.makePersistent(probationTerm);
	}
	
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
	public ProbationTerm update(final ProbationTerm probationTerm, 
			final Term term, final Date startDate, final Date terminationDate, 
			final Date expirationDate, final Integer jailCredit, 
			final Integer sentenceDays) throws ProbationTermExistsException,
					ProbationTermConflictException, 
					ProbationTermExistsAfterException {
		if (this.probationTermDao.findExcluding(probationTerm, 
				probationTerm.getOffender(), probationTerm.getCourtCase(), 
				startDate) != null) {
			throw new ProbationTermExistsException("Probation term exists");
		}
		// Throws exception if conflicting location terms exist
		long conflictingCount = this.probationTermDao.countExcluding(
				probationTerm.getOffender(), probationTerm.getCourtCase(),
				startDate, terminationDate, probationTerm);
		if (conflictingCount > 0) {
			throw new ProbationTermConflictException(
					conflictingCount + " conflicting probation term(s) exist");
		}
		if (terminationDate == null) {
			long existingTermsAfterDateCount = this.probationTermDao
					.countAfterDateExcluding(probationTerm.getOffender(),
							probationTerm.getCourtCase(),
							startDate, probationTerm);
			if (existingTermsAfterDateCount > 0) {
				throw new ProbationTermExistsAfterException(
						existingTermsAfterDateCount + " probation term(s) exist"
								+ " after the specified date range.");
			}
		}
		this.populateProbationTerm(probationTerm, probationTerm.getOffender(), 
				probationTerm.getCourtCase(), term, startDate, terminationDate,
				expirationDate, jailCredit, sentenceDays);
		probationTerm.setUpdateSignature(this.newUpdateSignature());
		return this.probationTermDao.makePersistent(probationTerm);
	}
	
	/**
	 * Removes the specified probation term.
	 * 
	 * @param probationTerm probation term
	 */
	public void remove(ProbationTerm probationTerm) {
		this.probationTermDao.makeTransient(probationTerm);
	}
	
	/**
	 * Returns probation terms for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return probation terms
	 */
	public List<ProbationTerm> findByCourtCase(final CourtCase courtCase) {
		return this.probationTermDao.findByCourtCase(courtCase);
	}
	
	/* Helper methods */
	
	// Populates a probation term
	private ProbationTerm populateProbationTerm(
			final ProbationTerm probationTerm, final Offender offender,
			final CourtCase courtCase, final Term term, 
			final Date startDate, final Date terminationDate,
			final Date expirationDate, 
			final Integer jailCredit, final Integer sentenceDays) {
		probationTerm.setOffender(offender);
		probationTerm.setCourtCase(courtCase);
		probationTerm.setTerm(term);
		probationTerm.setStartDate(startDate);
		probationTerm.setTerminationDate(terminationDate);
		probationTerm.setExpirationDate(expirationDate);
		probationTerm.setJailCredit(jailCredit);
		probationTerm.setSentenceDays(sentenceDays);
		return probationTerm;
	}
	
	// Returns a new update signature with current date and user.
	private UpdateSignature newUpdateSignature() {
		return new UpdateSignature(this.auditComponentRetriever
				.retrieveUserAccount(), this.auditComponentRetriever
				.retrieveDate());
	}
	
	// Returns a new creation signature with the current date and user.
	private CreationSignature newCreationSignature() {
		return new CreationSignature(this.auditComponentRetriever
				.retrieveUserAccount(), this.auditComponentRetriever
				.retrieveDate());
	}
}
