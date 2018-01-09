package omis.courtcasecondition.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.condition.domain.Agreement;
import omis.docket.domain.Docket;
/**
 * 
 * CourtCaseAgreement.java
 * 
 *@author unsigned
 *@author Annie Jacques 
 *@version 0.1.1 (Aug 29, 2017)
 *@since OMIS 3.0
 *
 */
public interface CourtCaseAgreement extends Creatable, Updatable{


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
	 * Gets the agreement.
	 * @return The agreement.
	 */
	public Agreement getAgreement();

	/**
	 * Sets the agreement.
	 * @param agreement The agreement.
	 */
	public void setAgreement(Agreement agreement);

	/**
	 * Returns the Docket for the court case agreement
	 * @return docket - Docket
	 */
	public Docket getDocket();
	
	/**
	 * Sets the Docket for the court case agreement
	 * @param docket - Docket
	 */
	public void setDocket(Docket docket);
	
	/**
	 * Returns the Accepted Date for the court case agreement
	 * @return acceptedDate - Date
	 */
	public Date getAcceptedDate();
	
	/**
	 * Sets the Accepted Date for the court case agreement
	 * @param acceptedDate - Date
	 */
	public void setAcceptedDate(Date acceptedDate);
	
	/**
	 * Get Court Case Agreement Category
	 * @return Court Case Agreement Category
	 */
	public CourtCaseAgreementCategory getCourtCaseAgreementCategory();
	
	/**
	 * Set Court Case Agreement Category
	 * @param courtCaseAgreementCategory Court Case Agreement Category
	 */
	public void setCourtCaseAgreementCategory(CourtCaseAgreementCategory 
			courtCaseAgreementCategory);
	
	 /** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
