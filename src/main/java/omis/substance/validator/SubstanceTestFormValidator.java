package omis.substance.validator;

import omis.substance.web.form.LabResultItem;
import omis.substance.web.form.ResultItem;
import omis.substance.web.form.SubstanceTestForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Substance Test Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 21, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SubstanceTestForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SubstanceTestForm form = (SubstanceTestForm) target;
		
		if (form.getResultDate() == null) {
			errors.rejectValue("resultDate",
					"substanceTest.resultDate.empty");
		}
		int resultItemIndex = 0;
		for (ResultItem resultItem : form.getResultItems()) {
			if (resultItem.getProcess() != null && resultItem.getProcess()) {
				if (resultItem.getSubstanceTestResultValue() == null) {
					errors.rejectValue("resultItems[" + resultItemIndex 
							+ "].substanceTestResultValue",
							"substanceTest.resultItem.resultOption.empty");
				}
				if (resultItem.getSubstance() == null) {
					errors.rejectValue("resultItems[" + resultItemIndex 
							+ "].substance",
							"substanceTest.resultItem.substance.empty");
				}
				if (resultItem.getAdmit() == null) {
					errors.rejectValue("resultItems[" + resultItemIndex 
							+ "].admit",
							"substanceTest.resultItem.admit.empty");
				}
				if (resultItem.getAdmit() && resultItem
						.getAdmitPrior() == null) {
					errors.rejectValue("resultItems[" + resultItemIndex 
							+ "].admitPrior", 
							"substanceTest.resultItem.admitPrior.empty");
				}
				
				/*
				 * Search previous result items to see if the current one
				 * is a duplicate.
				 */
				boolean foundDuplicate = false;
				for (int previousResultItemIndex = 0; 
						previousResultItemIndex < resultItemIndex; 
						previousResultItemIndex++) {
					ResultItem thatResultItem = form.getResultItems()
							.get(previousResultItemIndex);
					if (thatResultItem.getProcess() != null 
							&& thatResultItem.getProcess()) {
						if (resultItem.getSubstance() != null
								&& resultItem.getSubstance()
									.equals(thatResultItem.getSubstance())) {
							foundDuplicate = true;
						}
					}
				}
				if (foundDuplicate) {
					errors.rejectValue("resultItems", 
							"substanceTest.resultItem.duplicate");
				}
			}
			resultItemIndex++;
		}
		if (resultItemIndex < 1) {
			errors.rejectValue("resultItems", 
					"substanceTest.resultItems.empty");
		}
		
		int labResultItemIndex = 0;
		for (LabResultItem labResultItem : form.getLabResultItems()) {
			if (labResultItem.getProcess() != null && labResultItem
					.getProcess()) {
				if (labResultItem.getSubstanceTestResultValue() == null) {
					errors.rejectValue("labResultItems[" 
							+ labResultItemIndex 
							+ "].substanceTestResultValue",
							"substanceTest.labResultItem.resultOption" 
							+ ".empty");
				}
				if (labResultItem.getSubstance() == null) {
					errors.rejectValue("labResultItems[" 
							+ labResultItemIndex 
							+ "].substance", 
							"substanceTest.labResultItem.substance.empty");
				}
				
				/*
				 * Search previous crime lab result items to see if the current 
				 * one is a duplicate.
				 */
				boolean foundDuplicate = false;
				for (int previousClResultItemIndex = 0; 
						previousClResultItemIndex < labResultItemIndex; 
						previousClResultItemIndex++) {
					LabResultItem thatClResultItem = form
							.getLabResultItems()
							.get(previousClResultItemIndex);
					if (thatClResultItem.getProcess() != null 
							&& thatClResultItem.getProcess()) {
						if (labResultItem.getSubstance() != null
								&& labResultItem.getSubstance()
									.equals(thatClResultItem.getSubstance())) {
							foundDuplicate = true;
						}
					}
				}
				if (foundDuplicate) {
					errors.rejectValue("labResultItems", 
							"substanceTest.labResultItem.duplicate");
				}
			}
			labResultItemIndex++;
		}
		if (form.getLabInvolved() != null && form.getLabInvolved()) {
			if (form.getLab() == null) {
				errors.rejectValue("lab", "substanceTest.lab.empty");
			} else {
				if (form.getLab().getPrivateLab()) {
					if (form.getPrivateLabJustification() == null 
							|| form.getPrivateLabJustification().isEmpty()) {
						errors.rejectValue("privateLabJustification", 
								"substanceTest.privateLabJustification.empty");
					}
					if (form.getAuthorizingStaff() == null) {
						errors.rejectValue("authorizingStaff",
								"substanceTest.authorizingStaff.empty");
					}
				}
			}
			if(form.getLabRequestDate() == null) {
				errors.rejectValue("labRequestDate", 
						"substanceTest.labRequestDate.empty");
			} else {
				if (form.getLabResultDate() != null && 
						form.getLabRequestDate().getTime() 
						> form.getLabResultDate().getTime()) {
					errors.rejectValue("labRequestDate", 
							"substanceTest.labRequestDate.afterLabResultDate");
				}
				if (form.getResultDate() != null 
						&& form.getLabRequestDate().getTime() 
						< form.getResultDate().getTime()) {
					errors.rejectValue("labRequestDate", 
							"substanceTest.labRequestDate"
							+ ".beforeSubstanceTestResultDate");
				}
			}
			if (labResultItemIndex < 1 
					&& form.getLabResultDate() != null) {
				errors.rejectValue("labResultItems", 
						"substanceTest.labResultItems.empty");
			}
			if (labResultItemIndex >= 1) {
				if (form.getLabResultDate() == null) {
					errors.rejectValue("labResultDate", 
							"substanceTest.labResultDate.empty");
				} else {
					if (form.getResultDate() != null) {
						if (form.getLabResultDate().getTime() < form
							.getResultDate().getTime()) {
							errors.rejectValue("labResultDate", 
									"substanceTest.labResultDate.early");
						}
					}
				}
			}
		}
	}
}