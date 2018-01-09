package omis.health.validator;

import omis.health.web.form.LabWorkRequirementRequestItem;
import omis.health.web.form.LabWorkRequirementRequestOperation;
import omis.health.web.form.RequestLabWorkRequirementForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form to request lab work requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
public class RequestLabWorkRequirementFormValidator
		implements Validator {

	/** Instantiates a validator for form to request lab work requirements. */
	public RequestLabWorkRequirementFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return RequestLabWorkRequirementForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		RequestLabWorkRequirementForm requestLabWorkRequirementForm
			= (RequestLabWorkRequirementForm) target;
		for (int itemIndex = 0; itemIndex < requestLabWorkRequirementForm
				.getLabWorkRequirementRequestItems().size(); itemIndex++) {
			LabWorkRequirementRequestItem item = requestLabWorkRequirementForm
					.getLabWorkRequirementRequestItems().get(itemIndex);
			if (LabWorkRequirementRequestOperation.CREATE.equals(
						item.getOperation())
				|| LabWorkRequirementRequestOperation.UPDATE.equals(
						item.getOperation())) {
				for (int innerItemIndex = 0; innerItemIndex < itemIndex;
						innerItemIndex++) {
					LabWorkRequirementRequestItem innerItem
						= requestLabWorkRequirementForm
							.getLabWorkRequirementRequestItems()
								.get(innerItemIndex);
					if (LabWorkRequirementRequestOperation.CREATE.equals(
							innerItem.getOperation())
							|| LabWorkRequirementRequestOperation.UPDATE.equals(
									innerItem.getOperation())) {
						if (((item.getSampleOrderedBy() == null
								&& innerItem.getSampleOrderedBy() == null)
								|| (item.getSampleOrderedBy() != null
									&& item.getSampleOrderedBy().equals(
											innerItem.getSampleOrderedBy())))
							&& ((item.getSampleDate() == null
								&& innerItem.getSampleDate() == null)
								||  (item.getSampleDate() != null
									&& item.getSampleDate().equals(innerItem
										.getSampleDate())))
							&& ((item.getCategory() == null
								&& innerItem.getCategory() == null)
								|| (item.getCategory() != null
									&& item.getCategory().equals(innerItem
											.getCategory())))
							&& ((item.getSampleLab() == null
								&& innerItem.getSampleLab() == null)
								|| (item.getSampleLab() != null
									&& item.getSampleLab()
										.equals(innerItem.getSampleLab())))
							&& ((item.getResultsLab() == null
								&& innerItem.getResultsLab() == null)
								|| (item.getResultsLab() != null
									&& item.getResultsLab()
										.equals(innerItem.getResultsLab())))) {
							errors.rejectValue(
									"labWorkRequirementRequestItems["
											+ itemIndex + "]",
									"labWorkRequirementRequest.duplicate");
							break;
						}
					}
				}
			}
		}
	}
}