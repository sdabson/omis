package omis.prisonterm.service.impl;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.prisonterm.dao.PrisonTermDao;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.prisonterm.service.PrisonTermService;
import omis.prisonterm.service.delegate.PrisonTermDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of service for prison terms.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Yidong Li
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

public class PrisonTermServiceImpl implements PrisonTermService {

	private final PrisonTermDelegate prisonTermDelegate;
	
	private final UserAccountDelegate userAccountDelegate;
	
	private final PrisonTermDao prisonTermDao;
	
	/**
	 * Instantiates an implementation of service for prison terms with the
	 * specified data access object.
	 * 
	 * @param prisonTermDao data access object for prison terms.
	 * @param prisonTermInstanceFactory instance factory for prison terms
	 * @param auditComponentRetriever retriever for audit components
	 */
	public PrisonTermServiceImpl(
			final PrisonTermDelegate prisonTermDelegate,
			final UserAccountDelegate userAccountDelegate,
			final PrisonTermDao prisonTermDao) {
		this.prisonTermDelegate = prisonTermDelegate;
		this.userAccountDelegate = userAccountDelegate;
		this.prisonTermDao = prisonTermDao;
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTerm create(final Offender offender, final Date actionDate, 
			final Integer preSentenceCredits, final Date sentenceDate, 
			final Integer sentenceTermYears, final Integer sentenceTermDays,
			final Date paroleEligibilityDate, final Date projectedDischargeDate, 
			final Date maximumDischargeDate, final PrisonTermStatus status,
			final Boolean sentenceToFollow, final String comments,
			final UserAccount verificationUser, final Date verificationDate)
			throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
		PrisonTerm existingPrisonTerm = this.prisonTermDao
				.findExcludingActiveByOffender(offender, null);
		if (existingPrisonTerm != null && PrisonTermStatus.ACTIVE.equals(status)) {
			this.update(existingPrisonTerm, existingPrisonTerm.getActionDate(), 
				existingPrisonTerm.getPreSentenceCredits(), 
				existingPrisonTerm.getSentenceDate(), 
				existingPrisonTerm.getSentenceTermYears(), 
				existingPrisonTerm.getSentenceTermDays(), 
				existingPrisonTerm.getParoleEligibilityDate(), 
				existingPrisonTerm.getProjectedDischargeDate(), 
				existingPrisonTerm.getMaximumDischargeDate(), 
				PrisonTermStatus.HISTORICAL, 
				existingPrisonTerm.getSentenceToFollow(), 
				existingPrisonTerm.getComments(), 
				existingPrisonTerm.getVerificationUser(), 
				existingPrisonTerm.getVerificationDate());
		}
		return this.prisonTermDelegate.create(offender, actionDate, 
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, projectedDischargeDate, 
				maximumDischargeDate, status, sentenceToFollow, comments, 
				verificationUser, verificationDate);
	}

	/** {@inheritDoc} */
	@Override
	public PrisonTerm update(final PrisonTerm prisonTerm, final Date actionDate, 
			final Integer preSentenceCredits, final Date sentenceDate, 
			final Integer sentenceTermYears, final Integer sentenceTermDays,
			final Date paroleEligibilityDate, final Date projectedDischargeDate, 
			final Date maximumDischargeDate, final PrisonTermStatus status,
			final Boolean sentenceToFollow, final String comments, 
			final UserAccount verificationUser, final Date verificationDate)
			throws DuplicateEntityFoundException, 
					ActivePrisonTermExistsException {
		return this.prisonTermDelegate.update(prisonTerm, actionDate,  
				preSentenceCredits, sentenceDate, sentenceTermYears, 
				sentenceTermDays, paroleEligibilityDate, 
				projectedDischargeDate, maximumDischargeDate, status,  
				sentenceToFollow, comments, verificationUser, verificationDate);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final PrisonTerm prisonTerm) {
		this.prisonTermDelegate.remove(prisonTerm);		
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findUserAccounts(final String query) {
		return this.userAccountDelegate.search(query);
	}

}
