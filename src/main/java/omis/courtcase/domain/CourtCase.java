package omis.courtcase.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.docket.domain.Docket;
import omis.region.domain.State;

/**
 * Court case.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 20, 2014)
 * @since OMIS 3.0
 */
public interface CourtCase extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the docket.
	 * 
	 * @return docket
	 */
	Docket getDocket();
	
	/**
	 * Sets the docket.
	 * 
	 * @param docket docket
	 */
	void setDocket(Docket docket);
	
	/**
	 * Returns the pronouncement date.
	 * 
	 * @return pronouncement date
	 */
	Date getPronouncementDate();
	
	/**
	 * Sets the pronouncement date.
	 * 
	 * @param pronouncementDate pronouncement date
	 */
	void setPronouncementDate(Date pronouncementDate);
	
	/**
	 * Returns the sentence review date.
	 * 
	 * @return sentence review date
	 */
	Date getSentenceReviewDate();
	
	/**
	 * Sets the sentence review date.
	 * 
	 * @param sentenceReviewDate sentence review date
	 */
	void setSentenceReviewDate(Date sentenceReviewDate);
	
	/**
	 * Returns the court case personnel
	 * 
	 * @return court case personnel
	 */
	CourtCasePersonnel getPersonnel();
	
	/**
	 * Sets the court case personnel
	 * 
	 * @param courtCasePersonnel court case personnel
	 */
	void setPersonnel(CourtCasePersonnel courtCasePersonnel);

	/**
	 * Returns the court case flags
	 * 
	 * @return court case flags
	 */
	CourtCaseFlags getFlags();
	
	/**
	 * Sets the court case flags
	 * 
	 * @param courtCaseFlags court case flags
	 */
	void setFlags(CourtCaseFlags courtCaseFlags);
	
	/**
	 * Returns the comments.
	 * 
	 * @return comments
	 */
	String getComments();
	
	/**
	 * Sets the comments.
	 * 
	 * @param comments comments
	 */
	void setComments(String comments);
	
	/**
	 * Returns the inter state number.
	 * 
	 * @return inter state number
	 */
	String getInterStateNumber();
	
	/**
	 * Sets the inter state number.
	 * 
	 * @param interStateNumber inter state number
	 */
	void setInterStateNumber(String interStateNumber);
	
	/**
	 * Returns the inter state.
	 * 
	 * @return inter state
	 */
	State getInterState();
	
	/**
	 * Sets the inter state.
	 * 
	 * @param interState inter state
	 */
	void setInterState(State interState);
	
	/**
	 * Returns the jurisdiction authority.
	 * 
	 * @return jurisdiction authority
	 */
	JurisdictionAuthority getJurisdictionAuthority();
	
	/**
	 * Sets the jurisdiction authority.
	 * 
	 * @param jurisdictionAuthority jurisdiction authority
	 */
	void setJurisdictionAuthority(JurisdictionAuthority jurisdictionAuthority);
	
	/**
	 * Returns the danger designator.
	 * 
	 * @return danger designator
	 */
	OffenderDangerDesignator getDangerDesignator();
	
	/**
	 * Sets the danger designator.
	 * 
	 * @param dangerDesignator danger designator
	 */
	void setDangerDesignator(OffenderDangerDesignator dangerDesignator);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}