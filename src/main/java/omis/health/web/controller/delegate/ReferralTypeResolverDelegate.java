package omis.health.web.controller.delegate;

import omis.health.domain.HealthRequestCategory;
import omis.health.web.form.ReferralType;

/**
 * Resolves referral types.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (May 1, 2014)
 * @since OMIS 3.0
 */
public class ReferralTypeResolverDelegate {

	/** Instantiates a referral type resolver delegate. */
	public ReferralTypeResolverDelegate() {
		// Default instantiation
	}
	
	/**
	 * Resolves the health request category to a referral type.
	 * 
	 * @param category category
	 * @return referral type
	 */
	public ReferralType resolveCategory(
			final HealthRequestCategory category) {
		if (HealthRequestCategory.INTERNAL_MEDICAL.equals(category)) {
			return ReferralType.INTERNAL_MEDICAL;
		} else if (HealthRequestCategory.EXTERNAL_MEDICAL.equals(category)) {
			return ReferralType.EXTERNAL_MEDICAL;
		} else if (HealthRequestCategory.INTERNAL_DENTAL.equals(category)) {
			return ReferralType.INTERNAL_DENTAL;
		} else if (HealthRequestCategory.EXTERNAL_DENTAL.equals(category)) {
			return ReferralType.EXTERNAL_DENTAL;
		} else if (HealthRequestCategory.INTERNAL_MENTAL.equals(category)) {
			return ReferralType.INTERNAL_MENTAL;
		} else if (HealthRequestCategory.INTERNAL_OPTICAL.equals(category)) {
			return ReferralType.INTERNAL_OPTICAL;
		} else if (HealthRequestCategory.EXTERNAL_OPTICAL.equals(category)) {
			return ReferralType.EXTERNAL_OPTICAL;
		} else if (HealthRequestCategory.LAB.equals(category)){
			return ReferralType.LAB;
		} else {
			throw new IllegalArgumentException(
				String.format("Unresolvable category type %s",
						category.getName()));
		}
	}
	
	/**
	 * Resolves referral type to health request category.
	 * 
	 * @param referralType referral type to resolve
	 * @return request category to which referral type is resolved
	 */
	public HealthRequestCategory resolveToRequestCategory(
			final ReferralType referralType) {
		if (ReferralType.INTERNAL_MEDICAL.equals(referralType)) {
			return HealthRequestCategory.INTERNAL_MEDICAL;
		} else if (ReferralType.EXTERNAL_MEDICAL.equals(referralType)) {
			return HealthRequestCategory.EXTERNAL_MEDICAL;
		} else if (ReferralType.INTERNAL_MENTAL.equals(referralType)) {
			return HealthRequestCategory.INTERNAL_MENTAL;
		} else if (ReferralType.INTERNAL_DENTAL.equals(referralType)) {
			return HealthRequestCategory.INTERNAL_DENTAL;
		} else if (ReferralType.EXTERNAL_DENTAL.equals(referralType)) {
			return HealthRequestCategory.EXTERNAL_DENTAL;
		} else if (ReferralType.INTERNAL_OPTICAL.equals(referralType)) {
			return HealthRequestCategory.INTERNAL_OPTICAL;
		} else if (ReferralType.EXTERNAL_OPTICAL.equals(referralType)) {
			return HealthRequestCategory.EXTERNAL_OPTICAL;
		} else if (referralType == null) {
			throw new IllegalArgumentException("Referral type required");
		} else {
			throw new IllegalArgumentException(
					String.format("Unresolvable referral type %s",
							referralType.getName()));
		}
	}
}