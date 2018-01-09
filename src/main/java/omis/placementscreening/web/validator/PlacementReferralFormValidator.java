package omis.placementscreening.web.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.placementscreening.web.form.PlacementReferralForm;
import omis.placementscreening.web.form.PrereleaseScreeningItem;
import omis.placementscreening.web.form.ScreeningItemOperation;
import omis.placementscreening.web.form.TreatmentScreeningItem;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** Validator for placement referrals.
 * @author Ryan Johns
 * @version 0.1.0 (Feb 27, 2015)
 * @since OMIS 3.0 */
public class PlacementReferralFormValidator implements Validator {
	private static final String DATE_EMPTY = "date.empty";
	private static final String DUPLICATE_FACILITY_SUBMITTED = 
			"referralScreeningCenter.facility.duplicate";
	private static final String DUPLICATE_ORDER_SUBMITTED =
			"order.duplicate";
	private static final String MULTIPLE_TREATMENT_FACILITY_CATEGORIES =
			"referralScreeningCenter.multiple.categories";
	private static final String PROGRAM_CATEGORY_EMPTY =
			"programCategory.empty";
	private static final String REFERRING_FACILITY_EMPTY = 
			"referringFacility.empty";
	private static final String REFERRAL_SCREENING_CENTER_EMPTY =
			"referralScreeningCenter.empty";
	private static final String REFERRING_USER_EMPTY = "referringUser.empty";
	private static final String SCREENING_ITEMS_EMPTY = "screeningItems.empty";
	private static final String PRERELEASE_ITEMS_EMPTY = 
			"prereleaseScreeningItems.empty";
	private static final String TREATMENT_ITEMS_EMPTY =
			"treatmentScreeningItems.empty";
	
	private final PrereleaseScreeningItemValidator
		prereleaseScreeningItemValidator;
	
	private final TreatmentScreeningItemValidator
		treatmentScreeningItemValidator;
	
	/** Constructor. 
	 * @param prereleaseScreeningItemValidator prerelease screening item 
	 * validator.
	 * @param treatmentScreeningItemValidator treatment screening item 
	 * validator. */
	public PlacementReferralFormValidator(
			final PrereleaseScreeningItemValidator
			prereleaseScreeningItemValidator,
			final TreatmentScreeningItemValidator
			treatmentScreeningItemValidator) {
		
		this.prereleaseScreeningItemValidator =
				prereleaseScreeningItemValidator;
		this.treatmentScreeningItemValidator = 
				treatmentScreeningItemValidator;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return clazz.isAssignableFrom(PlacementReferralForm.class);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object obj, final Errors errors) {
		PlacementReferralForm form = (PlacementReferralForm) obj;
		
		ValidationUtils.rejectIfEmpty(errors, "referralDate", DATE_EMPTY);
		
		ValidationUtils.rejectIfEmpty(errors, "referringUser", 
				REFERRING_USER_EMPTY);
		
		ValidationUtils.rejectIfEmpty(errors, "referringFacility",
				REFERRING_FACILITY_EMPTY);
		
		ValidationUtils.rejectIfEmpty(errors, "programCategory",
				PROGRAM_CATEGORY_EMPTY);
		
		ValidationUtils.rejectIfEmpty(errors, "referralScreeningCenter",
				REFERRAL_SCREENING_CENTER_EMPTY);
		
		/* No Referral centers queued. */
		if ((form.getTreatmentScreeningItems() == null 
				|| form.getTreatmentScreeningItems().size() == 0) 
				&& (form.getPrereleaseScreeningItems() == null
				|| form.getPrereleaseScreeningItems().size() == 0)) {
			errors.rejectValue("screeningItems", 
					SCREENING_ITEMS_EMPTY);
		} else {
			this.validatePrereleaseScreeningItems(
					form.getPrereleaseScreeningItems(),
					form.getProgramCategory(), errors);
			
			this.validateTreatmentScreeningItems(
					form.getTreatmentScreeningItems(),
					form.getProgramCategory(), errors);
		}
	}

	/* Validate prerelease screening items. */
	private void validatePrereleaseScreeningItems(
			final List<PrereleaseScreeningItem> list,
			final ProgramCategory programCategory,
			final Errors errors) {
		Set<ReferralScreeningCenter> rscs = 
				new HashSet<ReferralScreeningCenter>();
		Set<Integer> orders = new HashSet<Integer>();
		
		if (list != null && list.size() > 0) {
			for (int x = 0; x < list.size(); x++) {
				errors.pushNestedPath("prereleaseScreeningItems[" + x + "]");
					
				if (list.get(x).getItemOperation() 
						!= ScreeningItemOperation.REMOVE) {
					if (rscs.add(list.get(x).getReferralScreeningCenter())) {
						if (orders.add(list.get(x).getOrder())) {
							ValidationUtils.invokeValidator(
									this.prereleaseScreeningItemValidator,
									list.get(x),
									errors);
						} else {
							errors.rejectValue("order", 
									DUPLICATE_ORDER_SUBMITTED);
						}
					} else {
						errors.rejectValue("referralScreeningCenter", 
							DUPLICATE_FACILITY_SUBMITTED);
					}
				}
				errors.popNestedPath();
			}
		} else {
			if (programCategory != null 
					&& !programCategory.getTreatmentFlag()) {
				/* No prerelease items, but prerelease category selected. */
				errors.rejectValue("prereleaseScreeningItems", 
						PRERELEASE_ITEMS_EMPTY);
			}
		}
	}
	
	/* Validate treatment screening items. */
	private void validateTreatmentScreeningItems(
			final List<TreatmentScreeningItem> list,
			final ProgramCategory programCategory,
			final Errors errors) {
		Set<ReferralScreeningCenter> rscs = 
				new HashSet<ReferralScreeningCenter>();
		Set<Integer> orders = new HashSet<Integer>();
		ProgramCategory programListCategory = null;
		if (list != null && list.size() > 0) {
			for (int x = 0; x < list.size(); x++) {
				errors.pushNestedPath("treatmentScreeningItems[" + x + "]");
				TreatmentScreeningItem item = list.get(x);
				if (list.get(x).getItemOperation() 
						!= ScreeningItemOperation.REMOVE) {
					if (rscs.add(list.get(x).getReferralScreeningCenter())) {
						if (orders.add(list.get(x).getOrder())) {
							ValidationUtils.invokeValidator(
									this.treatmentScreeningItemValidator,
									list.get(x),
									errors);
							
							if (programListCategory == null) {
								programListCategory = item
										.getReferralScreeningCenter()
										.getProgramCategory();
							}
							
							if (programListCategory 
									!= item.getReferralScreeningCenter()
										.getProgramCategory()) {
								errors.rejectValue("referralScreeningCenter",
										MULTIPLE_TREATMENT_FACILITY_CATEGORIES);
							}
							
							
						} else {
							errors.rejectValue("order", 
									DUPLICATE_ORDER_SUBMITTED);
						}
					} else {
						errors.rejectValue("referralScreeningCenter", 
								DUPLICATE_FACILITY_SUBMITTED);
					}
				}
				errors.popNestedPath();
			}
		} else {
			if (programCategory != null 
					&& programCategory.getTreatmentFlag()) {
				/* No Treatment items, but treatment category selected. */
				errors.rejectValue("treatmentScreeningItems",
						TREATMENT_ITEMS_EMPTY);
			}
		}
	}
}
