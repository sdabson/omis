package omis.prisonterm.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.user.domain.UserAccount;

/**
 * Prison term service.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

public interface PrisonTermService {

	/**
	 * Creates a new prison term.
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
	 * @return new prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 * @throws ActivePrisonTermExistsException 
	 */
	PrisonTerm create(Offender offender, Date actionDate, 
			Integer preSentenceCredits, Date sentenceDate, 
			Integer sentenceTermYears, Integer sentenceTermDays, 
			Date paroleEligibilityDate, Date projectedDischargeDate, 
			Date maximumDischargeDate, PrisonTermStatus status,  
			Boolean sentenceToFollow, String comments, 
			UserAccount verificationUser, Date verificationDate)
		throws DuplicateEntityFoundException, ActivePrisonTermExistsException;
	
	/**
	 * Updates an existing prison term.
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
	 * 
	 * @return updated prison term
	 * @throws DuplicateEntityFoundException if new prison term already exists.
	 */
	PrisonTerm update(PrisonTerm prisonTerm, Date actionDate, 
			Integer preSentenceCredits, Date sentenceDate, 
			Integer sentenceTermYears, Integer sentenceTermDays, 
			Date paroleEligibilityDate, Date projectedDischargeDate, 
			Date maximumDischargeDate, PrisonTermStatus status,  
			Boolean sentenceToFollow, String comments,
			UserAccount verificationUser, Date verificationDate)
		throws DuplicateEntityFoundException, ActivePrisonTermExistsException;
	
	/**
	 * Removes a prison term for the specified offender.
	 * 
	 * @param prisonTerm prison term
	 */	
	void remove(PrisonTerm prisonTerm);
	
	/**
	 * Returns a list of user accounts.
	 * 
	 * @return list of user accounts
	 */
	List<UserAccount> findUserAccounts(String query);


	
}
