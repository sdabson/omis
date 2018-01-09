package omis.health.validator;

import omis.health.web.form.CreateLabWorksForm;
import omis.health.web.form.LabWorkItem;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for create lab works form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 12, 2014)
 * @since OMIS 3.0
 */
public class CreateLabWorksFormValidator implements Validator {
	
	/**
	 * Instantiates a default instance of the validator for the create lab works
	 * form.
	 */
	public CreateLabWorksFormValidator() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CreateLabWorksForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CreateLabWorksForm form = (CreateLabWorksForm) target;
		if (form.getOffender() == null && form.getOffenderRequired()) {
			errors.rejectValue("offender", "labWork.offender.empty");
		}
		if (form.getLabWorkItems() == null 
				|| form.getLabWorkItems().size() < 1) {
			errors.rejectValue("labWorkItems", "labWork.labWorkItems.empty");
		} else {
			int count = 0;
			for (LabWorkItem item : form.getLabWorkItems()) {
				if (item.getProcess() != null && item.getProcess()) {
					if (item.getLabWorkCategory() == null) {
						errors.rejectValue("labWorkItems[" + count 
								+ "].labWorkCategory", 
								"labWork.labWorkCategory.empty");
					}
					if (item.getSampleLab() == null) {
						errors.rejectValue("labWorkItems[" + count 
								+ "].sampleLab",
								"labWork.sampleLab.empty");
					}
					if (item.getSampleDate() == null) {
						errors.rejectValue("labWorkItems[" + count 
								+ "].sampleDate", 
								"labWork.sampleDate.empty");
					}
					if (item.getOrderDate() != null 
							&& item.getSampleDate() != null) {
						if (item.getOrderDate().getTime()
								> item.getSampleDate().getTime()) {
							errors.rejectValue("labWorkItems[" + count 
								+ "].orderDate", 
								"labWork.orderDate.afterDate");
						}
					}
					if (item.getResultsDate() != null
							&& item.getSampleDate() != null) {
						if (item.getResultsDate().getTime()
								< item.getSampleDate().getTime()) {
							errors.rejectValue("labWorkItems[" + count 
								+ "].resultsDate", 
								"labWork.resultsDate.beforeDate");
						}
					}
					if (item.getSampleTaken() == null || !item.getSampleTaken()) {
						if (item.getResultsDate() != null) {
							errors.rejectValue("labWorkItems[" + count 
								+ "].resultsDate", 
								"labWork.resultsDate.sampleNotTaken");
						}
						if (item.getResultsLab() != null) {
							errors.rejectValue("labWorkItems[" + count 
								+ "].resultsLab", 
								"labWork.resultsLab.sampleNotTaken");
						}
					}
				}
				count++;
			}
		}
	}
}
