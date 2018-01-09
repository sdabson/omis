package omis.employment.web.validator;

import omis.address.web.validator.delegate.AddressFieldsValidatorDelegate;
import omis.employment.web.controller.EmploymentAddressOperation;
import omis.employment.web.form.EmploymentForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for employment form.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 4, 2015)
 * @since OMIS 3.0
 */
public class EmploymentFormValidator implements Validator {
	private AddressFieldsValidatorDelegate addressFieldsValidatorDelegate;
	
	/** Instantiates a validator for employment form. */
	public EmploymentFormValidator(final AddressFieldsValidatorDelegate
			addressFieldsValidatorDelegate) {
		// Default instantiation
		this.addressFieldsValidatorDelegate = addressFieldsValidatorDelegate;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmploymentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		EmploymentForm employmentForm
			= (EmploymentForm) target;

		if (employmentForm.getJobTitle()==null||employmentForm.getJobTitle().isEmpty()) {
			errors.rejectValue("jobTitle", "employmentTerm.job.jobTitle.empty");
		} 
		if(employmentForm.getExistingEmployer()==null){
			if (employmentForm.getEmployerName()==null||employmentForm.getEmployerName().isEmpty()) {
				errors.rejectValue("employerName", "employmentForm.employerName");
			} 
		}

		if (employmentForm.getStartDate() != null
			&& employmentForm.getEndDate() != null
			&& employmentForm.getStartDate().getTime()
			> employmentForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
			"employmentForm.startDate.startDateGreaterThanEndDate");
		}
		
		if(employmentForm.getExistingEmployer()==null){
			if(employmentForm.getEmployerName()==null){
				errors.rejectValue("employerName", "employmentForm.employerName");
			}
		}
		
		if(employmentForm.getExistingEmployer()==null){
			if(employmentForm.getEmploymentAddressOperation()!=null){
				if (employmentForm.getEmploymentAddressOperation().equals(EmploymentAddressOperation.NEW)) {
					if (employmentForm.getAddressFields() == null) {
						errors.rejectValue("addressFields",
							"createRelationships.addressFields.empty");
					} else {
						this.addressFieldsValidatorDelegate.validateAddressFields(
						employmentForm.getAddressFields(), "addressFields", 
						errors);
					}
				} 
				else {
					if(employmentForm.getAddress()==null){
						errors.rejectValue("address",	"employmentForm.address");
					}
				}
			}
		}
	}
}  