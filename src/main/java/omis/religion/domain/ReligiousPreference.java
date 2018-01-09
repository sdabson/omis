package omis.religion.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.offender.domain.OffenderAssociable;

/**
 * Religious preference.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 22, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousPreference
		extends OffenderAssociable, Updatable, Creatable {

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
	 * Sets the religion.
	 * 
	 * @param religion religion
	 */
	void setReligion(Religion religion);
	
	/**
	 * Returns the religion.
	 * 
	 * @return religion
	 */
	Religion getReligion();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the comment.
	 * 
	 * @param comment comment
	 */
	void setComment(String comment);
	
	/**
	 * Returns the comment.
	 * 
	 * @return comment
	 */
	String getComment();
	
	/**
	 * Sets the verification signature.
	 * 
	 * @param verificationSignature verification signature
	 */
	void setVerificationSignature(VerificationSignature verificationSignature);
	
	/**
	 * Returns the verification signature
	 * 
	 * @return verification signature
	 */
	VerificationSignature getVerificationSignature();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}