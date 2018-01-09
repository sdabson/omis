package omis.health.validator;

import omis.health.web.form.EditLabWorkForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for edit lab work form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 15, 2014)
 * @since OMIS 3.0
 */
public class EditLabWorkFormValidator implements Validator {

	/**
	 * Instantiates a default instance of edit lab work form validator.
	 */
	public EditLabWorkFormValidator() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return EditLabWorkForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		EditLabWorkForm form= (EditLabWorkForm) target;
		
		if (form.getLabWorkCategory() == null) {
			errors.rejectValue("labWorkCategory", 
					"labWork.labWorkCategory.empty");
		}
		if (form.getSampleDate() == null) {
			errors.rejectValue("sampleDate", "labWork.sampleDate.empty");
		}
		if (form.getSampleLab() == null) {
			errors.rejectValue("sampleLab", "labWork.sampleLab.empty");
		}
		if (form.getOrderDate() != null && form.getSampleDate() != null) {
			if (form.getOrderDate().getTime() 
					> form.getSampleDate().getTime()) {
				errors.rejectValue("orderDate", "labWork.orderDate.afterDate");
			}
		}
		if (form.getResultsDate() != null && form.getSampleDate() != null) {
			if (form.getResultsDate().getTime() 
					< form.getSampleDate().getTime()) {
				errors.rejectValue("resultsDate", 
						"labWork.resultsDate.beforeDate");
			}
		}
		if (form.getSampleTaken() == null || !form.getSampleTaken()) {
			if (form.getResultsDate() != null) {
				errors.rejectValue("resultsDate",
						"labWork.resultsDate.sampleNotTaken");
			}
			if (form.getResultsLab() != null) {
				errors.rejectValue("resultsLab",
						"labWork.resultsLab.sampleNotTaken");
			}
		}
	}
}