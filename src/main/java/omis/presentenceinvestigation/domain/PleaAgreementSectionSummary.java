package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * PleaAgreementSectionSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
public interface PleaAgreementSectionSummary extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the PleaAgreementSectionSummary
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the PleaAgreementSectionSummary
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the PresentenceInvestigationRequest for the
	 * PleaAgreementSectionSummary
	 * @return presentenceInvestigationRequest - PresentenceInvestigationRequest
	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest();
	
	/**
	 * Sets the PresentenceInvestigationRequest for the PleaAgreementSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 */
	public void setPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Returns the Summary for the PleaAgreementSectionSummary
	 * @return summary - String
	 */
	public String getSummary();
	
	/**
	 * Sets the Summary for the PleaAgreementSectionSummary
	 * @param summary - String
	 */
	public void setSummary(String summary);
	
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
