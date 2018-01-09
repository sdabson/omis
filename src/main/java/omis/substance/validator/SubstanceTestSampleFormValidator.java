package omis.substance.validator;

import omis.substance.web.form.SubstanceTestSampleForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Substance test sample form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 21, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestSampleFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SubstanceTestSampleForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		
		SubstanceTestSampleForm form = (SubstanceTestSampleForm) target;
		
		if (form.getRequest() != null) {
			if (form.getTaken() != null) {
				if (!form.getTaken()) {
					if (form.getStatusReason() == null) {
						errors.rejectValue("statusReason",
								"substanceTestSample.statusReason.empty");
					}
				} else {
					this.validateSampleInfo(form, errors);
				}
			} else {
				errors.rejectValue("taken", "substanceTestSample.taken.empty");
			}
		} else {
			this.validateSampleInfo(form, errors);
		}
	}
	
	/*
	 * Validates sample specific inputs.
	 * 
	 * @param form substance test sample form
	 * @param errors errors
	 * @return errors
	 */
	private Errors validateSampleInfo(final SubstanceTestSampleForm form,
			final Errors errors) {
		if (form.getCollectionDate() == null) {
			errors.rejectValue("collectionDate", 
				"substanceTestSample.collectionDate.empty");
		}
		if (form.getCollectionTime() == null) {
			errors.rejectValue("collectionTime", 
				"substanceTestSample.collectionTime.empty");
		}
		if (form.getCollectionEmployee() == null) {
			errors.rejectValue("collectionEmployee",
				"substanceTestSample.collectionEmployee.empty");
		}
		if (form.getSampleCollectionMethod() == null) {
			errors.rejectValue("sampleCollectionMethod", 
				"substanceTestSample.sampleCollectionMethod.empty");
		}
		if (!form.getRandom() && form
				.getSubstanceTestReason() == null) {
			errors.rejectValue("substanceTestReason", 
					"substanceTest.reason.empty");
		}
		return errors;
	}
}