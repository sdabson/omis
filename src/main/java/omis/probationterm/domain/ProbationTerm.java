package omis.probationterm.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.courtcase.domain.CourtCase;
import omis.offender.domain.OffenderAssociable;
import omis.term.domain.component.Term;

/**
 * Probation term.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.0 (November 21, 2017)
 * @since OMIS 3.0
 */
public interface ProbationTerm 
	extends Creatable, Updatable, OffenderAssociable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the court case.
	 * 
	 * @param courtCase court case
	 */
	void setCourtCase(CourtCase courtCase);
	
	/**
	 * Returns the court case.
	 * 
	 * @return court case
	 */
	CourtCase getCourtCase();
	
	/**
	 * Sets the term.
	 * 
	 * @param term term
	 */
	void setTerm(Term term);
	
	/**
	 * Returns the term.
	 * 
	 * @return term
	 */
	Term getTerm();
	
	/**
	 * Sets start date.
	 * 
	 * @param startDate start date
	 */
	void setStartDate(Date startDate);
	
	/**
	 * Returns start date.
	 * 
	 * @return start date
	 */
	Date getStartDate();
	
	/**
	 * Sets termination date.
	 * 
	 * @param terminationDate termination date
	 */
	void setTerminationDate(Date terminationDate);
	
	/**
	 * Returns termination date.
	 * 
	 * @return termination date
	 */
	Date getTerminationDate();
	
	/**
	 * Sets the expiration date.
	 * 
	 * @param expirationDate expiration date
	 */
	void setExpirationDate(Date expirationDate);
	
	/**
	 * Returns the expiration date.
	 * 
	 * @return expiration date
	 */
	Date getExpirationDate();
	
	/**
	 * Sets the jail credit.
	 * 
	 * @param jailCredit jail credit
	 */
	void setJailCredit(Integer jailCredit);
	
	/**
	 * Returns the jail credit.
	 * 
	 * @return jail credit
	 */
	Integer getJailCredit();
	
	/**
	 * Sets the sentence days.
	 * 
	 * @param sentenceDays sentence days
	 */
	void setSentenceDays(Integer sentenceDays);
	
	/**
	 * Returns the sentence days.
	 * 
	 * @return sentence days
	 */
	Integer getSentenceDays();
}
