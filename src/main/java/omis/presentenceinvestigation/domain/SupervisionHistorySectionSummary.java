package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Supervision history section summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 8, 2017)
 * @since OMIS 3.0
 */
public interface SupervisionHistorySectionSummary extends Creatable, Updatable {

	/**
	 * Returns the supervision history section summary ID.
	 *
	 * @return ID 
	 */
	public Long getId();
	
	/**
	 * Sets the supervision history section summary ID.
	 *
	 * @param id ID
	 */
	public void setId(Long id);
	
	/**
	 * Returns the text of the supervision history section summary.
	 *
	 * @return
	 */
	public String getText();
	
	/**
	 * Sets the text of the supervision history section summary.
	 *
	 * @param text
	 */
	public void setText(String text);
	
	/**
	 * Returns the presentence investigation request 
	 * supervision history section summary.
	 *
	 * @return presentence investigation request
	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest();
	
	/**
	 * Sets the presentence investigation request 
	 * supervision history section summary.
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 */
	public void setPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
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