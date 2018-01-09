package omis.health.validator;

import omis.health.web.form.ProviderAssignmentForm;
import omis.health.web.form.ProviderType;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for provider assignment form.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 19, 2014)
 * @since OMIS 3.0
 */
public class ProviderAssignmentFormValidator 
		implements Validator {
	
	/** Instantiates a default provider assignment form validator. */
	public ProviderAssignmentFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ProviderAssignmentForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ProviderAssignmentForm providerAssignmentForm 
			= (ProviderAssignmentForm) target;
		if (providerAssignmentForm.getProvider() == null) {
			errors.rejectValue("provider", "providerAssignment.provider.empty");
		}
		if (providerAssignmentForm.getTitle() == null) {
			errors.rejectValue("title", "providerAssignment.title.empty");
		}
		if (providerAssignmentForm.getProviderType() == null) {
			errors.rejectValue("providerType", 
					"providerAssignment.providerType.empty");
		} else {
			if (providerAssignmentForm.getProviderType() 
					== ProviderType.EXTERNAL && providerAssignmentForm
					.getMedicalFacility() == null) {
				errors.rejectValue("medicalFacility",
						"providerAssignment.medicalFacility.empty");
			}
		}
		if (providerAssignmentForm.getStartDate() != null
				&& providerAssignmentForm.getEndDate() != null) { 
			if (providerAssignmentForm.getStartDate().getTime()
					> providerAssignmentForm.getEndDate().getTime()) {
				errors.rejectValue("startDate", 
					"providerAssignment.startDate.afterEndDate");
			}
		}
	}
}

