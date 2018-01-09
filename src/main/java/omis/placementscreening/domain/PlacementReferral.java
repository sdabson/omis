package omis.placementscreening.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.facility.domain.Facility;
import omis.offender.domain.OffenderAssociable;
import omis.placementscreening.domain.component.ReferralSource;

/** Referral for admittance to program.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public interface PlacementReferral extends OffenderAssociable, Creatable {
	/** Gets id.
	 * @return id id. */
	Long getId();
	
	/** Gets date of referral.
	 * @return date date. */
	Date getDate();
	
	/** Gets program category of referral.
	 * @return programReferral program referral. */
	ProgramCategory getProgramCategory();
	
	/** Gets the referral source.
	 * @return referralSource referral source. */
	ReferralSource getReferralSource();
	
	/** Gets facility of referral.
	 * @return facility facility. */
	Facility getFacility();
	
	/** Sets id.
	 * @param id id. */
	void setId(Long id);
	
	/** Sets date of referral.
	 * @param date date. */
	void setDate(Date date);
	
	/** Sets program category of referral.
	 * @param programCategory program category. */
	void setProgramCategory(ProgramCategory programCategory);
	
	/** Sets facility of referral.
	 * @param facility facility. */
	void setFacility(Facility facility);
	
	/** Sets the referral source.
	 * @param referralSource referral source. */
	void setReferralSource(ReferralSource referralSource);
	
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
