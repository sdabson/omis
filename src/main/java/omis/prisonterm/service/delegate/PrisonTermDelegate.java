package omis.prisonterm.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.prisonterm.dao.PrisonTermDao;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.user.domain.UserAccount;

/**
 * Prison Term Delegate.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<PrisonTerm> prisonTermInstanceFactory;
	
	/* DAOs. */
	
	private final PrisonTermDao prisonTermDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates delegate for prison terms.
	 * 
	 * @param prisonTermInstanceFactory instance factory for prison terms.
	 * @param prisonTermDao data access object for prison terms.
	 */
	public PrisonTermDelegate(
			final InstanceFactory<PrisonTerm> prisonTermInstanceFactory,
			final PrisonTermDao prisonTermDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.prisonTermInstanceFactory = prisonTermInstanceFactory;
		this.prisonTermDao = prisonTermDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates a new prison term.
	 * 
	 * @param offender offender
	 * @param actionDate date
	 * @param preSentenceCredits integer
	 * @param sentenceDate date
	 * @param sentenceTermYears integer
	 * @param sentenceTermDays integer
	 * @param paroleEligibilityDate date
	 * @param projectedDischargeDate date
	 * @param maximumDischargeDate date
	 * @param status prison term status
	 * @param sentenceToFollow boolean
	 * @param comments text 
	 * @return Creates a new prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 */
	public PrisonTerm create(final Offender offender, 
			final Date actionDate, 
			final Integer preSentenceCredits, 
			final Date sentenceDate, 
			final Integer sentenceTermYears, 
			final Integer sentenceTermDays, 
			final Date paroleEligibilityDate,
			final Date projectedDischargeDate, 
			final Date maximumDischargeDate, 
			final PrisonTermStatus status, 
			final Boolean sentenceToFollow, 
			final String comments,
			final UserAccount verificationUser,
			final Date verificationDate)
	throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
	if (this.prisonTermDao.find(offender, actionDate, status) != null) {
		throw new DuplicateEntityFoundException("Duplicate prison term found");
	}
			
	PrisonTerm prisonTerm = 
		this.prisonTermInstanceFactory.createInstance();
			prisonTerm.setCreationSignature(new CreationSignature(
		this.auditComponentRetriever.retrieveUserAccount(), 
		this.auditComponentRetriever.retrieveDate()));
			prisonTerm.setOffender(offender);
		this.populatePrisonTerm(prisonTerm, actionDate, preSentenceCredits, 
				sentenceDate, sentenceTermYears, sentenceTermDays, 
				paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
		return this.prisonTermDao.makePersistent(prisonTerm);
	}
	
	/** Updates a prison term.
	 * 
	 * @param prisonTerm prison term
	 * @param actionDate date
	 * @param preSentenceCredits integer
	 * @param sentenceDate date
	 * @param sentenceTermYears integer
	 * @param sentenceTermDays integer
	 * @param paroleEligibilityDate date
	 * @param projectedDischargeDate date
	 * @param maximumDischargeDate date
	 * @param status prison term status
	 * @param sentenceToFollow boolean
	 * @param comments text 
	 * @return Updates prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 * @throws ActivePrisonTermExistsException if an active prison term already
	 * exists.
	 */
	public PrisonTerm update(
			final PrisonTerm prisonTerm,
			final Date actionDate, 
			final Integer preSentenceCredits, 
			final Date sentenceDate, 
			final Integer sentenceTermYears, 
			final Integer sentenceTermDays, 
			final Date paroleEligibilityDate,
			final Date projectedDischargeDate, 
			final Date maximumDischargeDate, 
			final PrisonTermStatus status, 
			final Boolean sentenceToFollow, 
			final String comments,
			final UserAccount verificationUser,
			final Date verificationDate)
	throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
	if (this.prisonTermDao.findExcluding(prisonTerm.getOffender(), actionDate, 
			status, prisonTerm) != null) {
		throw new DuplicateEntityFoundException("Duplicate prison term found");
	}
	
	if (this.prisonTermDao.findExcludingActiveByOffender(prisonTerm
			.getOffender(), prisonTerm) !=null && 
				PrisonTermStatus.ACTIVE.equals(status)) {
		throw new ActivePrisonTermExistsException(
				"An active prison term already exists");
	}
	
	this.populatePrisonTerm(prisonTerm, actionDate, preSentenceCredits, 
			sentenceDate, sentenceTermYears, sentenceTermDays, 
			paroleEligibilityDate, projectedDischargeDate, maximumDischargeDate, 
			status, sentenceToFollow, comments, verificationUser, 
			verificationDate);
	return this.prisonTermDao.makePersistent(prisonTerm);
	}
	
	/** Removes a prison term.
	 * @param PrisonTerm - prisonTerm
	 * @param  - .
	 * @return Removes a prison term.
	 * @throws DuplicateEntityFoundException - when prison term already exists. 
	 */
	public void remove(final PrisonTerm prisonTerm) {
		this.prisonTermDao.makeTransient(prisonTerm);
	}
	
	/**
	 * Populates the specified prison term.
	 * 
	 * @param prisonTerm prison term
	 * @param actionDate date
	 * @param preSentenceCredits integer
	 * @param sentenceDate date
	 * @param sentenceTermYears integer
	 * @param sentenceTermDays integer
	 * @param paroleEligibilityDate date
	 * @param projectedDischargeDate date
	 * @param maximumDischargeDate date
	 * @param status prison term status
	 * @param sentenceToFollow boolean
	 * @param comments text 
	 * @return populated prison term.
	 */
	private void populatePrisonTerm(
			final PrisonTerm prisonTerm,
			final Date actionDate, 
			final Integer preSentenceCredits, 
			final Date sentenceDate, 
			final Integer sentenceTermYears, 
			final Integer sentenceTermDays, 
			final Date paroleEligibilityDate,
			final Date projectedDischargeDate, 
			final Date maximumDischargeDate, 
			final PrisonTermStatus status, 
			final Boolean sentenceToFollow, 
			final String comments,
			final UserAccount verificationUser,
			final Date verificationDate) {
		prisonTerm.setActionDate(actionDate);
		prisonTerm.setPreSentenceCredits(preSentenceCredits);
		prisonTerm.setSentenceDate(sentenceDate);
		prisonTerm.setSentenceTermYears(sentenceTermYears);
		prisonTerm.setSentenceTermDays(sentenceTermDays);
		prisonTerm.setParoleEligibilityDate(paroleEligibilityDate);
		prisonTerm.setProjectedDischargeDate(projectedDischargeDate);
		prisonTerm.setMaximumDischargeDate(maximumDischargeDate);
		prisonTerm.setStatus(status);
		prisonTerm.setSentenceToFollow(sentenceToFollow);
		prisonTerm.setComments(comments);
		prisonTerm.setVerificationUser(verificationUser);
		prisonTerm.setVerificationDate(verificationDate);
		prisonTerm.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}
