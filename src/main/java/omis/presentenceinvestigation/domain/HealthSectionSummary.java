package omis.presentenceinvestigation.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.presentenceinvestigation.domain.HealthRating;

/**
 * Health section summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 3, 2017)
 * @since OMIS 3.0
 */
public interface HealthSectionSummary extends Creatable, Updatable {
	
	/**
	 * Returns the health section summary ID.
	 *
	 *
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the health section summary ID.
	 *
	 *
	 * @param id ID
	 */
	public void setId(Long id);
	
	/**
	 * Returns the presentence investigation request health section summary.
	 *
	 *
	 * @return presentence investigation
	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest();
	
	/**
	 * Sets the presentence investigation request health section summary.
	 *
	 *
	 * @param presentenceInvestigationRequest  presentence investigation request
	 * @return presentence investigation
	 */
	public void setPresentenceInvestigationRequest(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/**
	 * Return the health rating.
	 *
	 *
	 * @return rating
	 */
	HealthRating getRating();
	
	/**
	 * Sets the health rating.
	 *
	 *
	 * @param rating rating
	 */
	 void setRating(HealthRating rating);

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