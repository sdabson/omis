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
import omis.prisonterm.domain.PrisonTermDocumentAssociation;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.user.domain.UserAccount;

/**
 * Prison Term Delegate.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.2 (Dec 18, 2018)
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
	 * @param auditComponentRetriever audit component retriever
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
	 * @param verificationUser Verification User
	 * @param verificationDate Verification Date
	 * @param sentenceCalculation Sentence Calculation
	 * @return Created a new prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 * @throws ActivePrisonTermExistsException When an active Prison Term exists
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
			final Date verificationDate,
			final PrisonTermDocumentAssociation sentenceCalculation)
		throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
		if (this.prisonTermDao.find(offender, actionDate, status) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate prison term found");
		}
				
		PrisonTerm prisonTerm = 
				this.prisonTermInstanceFactory.createInstance();
		prisonTerm.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(), 
			this.auditComponentRetriever.retrieveDate()));
		prisonTerm.setOffender(offender);
		this.populatePrisonTerm(prisonTerm, actionDate,
				preSentenceCredits, sentenceDate, sentenceTermYears,
				sentenceTermDays, paroleEligibilityDate,
				projectedDischargeDate, maximumDischargeDate, status,
				sentenceToFollow, comments, verificationUser,
				verificationDate, sentenceCalculation);
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
	 * @param verificationUser Verification User
	 * @param verificationDate Verification Date
	 * @param sentenceCalculation Sentence Calculation
	 * @return Updated prison term
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
			final Date verificationDate,
			final PrisonTermDocumentAssociation sentenceCalculation)
					throws DuplicateEntityFoundException,
					ActivePrisonTermExistsException {
		if (this.prisonTermDao.findExcluding(prisonTerm.getOffender(),
				actionDate, status, prisonTerm) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate prison term found");
		}
		
		if (this.prisonTermDao.findExcludingActiveByOffender(prisonTerm
				.getOffender(), prisonTerm) != null
				&& PrisonTermStatus.ACTIVE.equals(status)) {
			throw new ActivePrisonTermExistsException(
					"An active prison term already exists");
		}
		
		this.populatePrisonTerm(prisonTerm, actionDate, preSentenceCredits,
				sentenceDate, sentenceTermYears, sentenceTermDays,
				paroleEligibilityDate, projectedDischargeDate,
				maximumDischargeDate, status, sentenceToFollow, comments,
				verificationUser, verificationDate, sentenceCalculation);
		return this.prisonTermDao.makePersistent(prisonTerm);
	}
	
	/**
	 * Removes a prison term.
	 * 
	 * @param prisonTerm - Prison Term to remove
	 */
	public void remove(final PrisonTerm prisonTerm) {
		this.prisonTermDao.makeTransient(prisonTerm);
	}
	
	/**
	 * Returns the active prison term for the specified offender.
	 * 
	 * @param offender Offender
	 * @return active prison term for the specified offender.
	 */
	public PrisonTerm findActiveByOffender(final Offender offender) {
		return this.prisonTermDao.findExcludingActiveByOffender(
				offender, null);
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
	 * @param verificationUser Verification User
	 * @param verificationDate Verification Date
	 * @param sentenceCalculation Sentence Calculation
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
			final Date verificationDate,
			final PrisonTermDocumentAssociation sentenceCalculation) {
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
		prisonTerm.setSentenceCalculation(sentenceCalculation);
		prisonTerm.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}
}
