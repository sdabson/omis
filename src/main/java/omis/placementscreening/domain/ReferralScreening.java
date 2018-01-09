package omis.placementscreening.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;

/** Screening process for program admittance.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public interface ReferralScreening extends Creatable, Updatable {
	/** Gets id.
	 * @return id id. */
	Long getId();
	
	/** Gets placement referral.
	 * @return placementReferral placement referral. */
	PlacementReferral getPlacementReferral();
	
	
	/** Gets screening order.
	 * @return screeningOrder screening order. */
	Integer getScreeningOrder();
	
	/** Gets screening window.
	 * @return screeningWindow screening window. */
	DateRange getScreeningWindow();

	/** Gets referral screening center.
	 * @return referralScreeningFacility referral screening facility. */
	ReferralScreeningCenter getReferralScreeningCenter();
	
	/** Gets screening level category.
	 * @return screeningLevelCategory screening level category. */
	ScreeningLevelCategory getScreeningLevelCategory();
	
	/** Sets id.
	 * @param id id. */
	void setId(Long id);
	
	/** Sets placement referral.
	 * @param placementReferral placement referral. */
	void setPlacementReferral(PlacementReferral placementReferral);
	 	
	/** Sets screening order.
	 * @param screeningOrder screening order. */
	void setScreeningOrder(Integer screeningOrder);
	
	/** Sets screening window.
	 * @param screeningWindow screening window. */
	void setScreeningWindow(DateRange screeningWindow);
	
	/** Sets screening center.
	 * @param referralScreeningCenter referral screening center. */
	void setReferralScreeningCenter(
			ReferralScreeningCenter referralScreeningCenter);
	
	/** Sets screening level category.
	 * @param screeningLevelCategory screening level category. */
	void setScreeningLevelCategory(
			ScreeningLevelCategory screeningLevelCategory);

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
