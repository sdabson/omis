package omis.employment.web.validator;

import omis.address.web.validator.delegate.AddressFieldsValidatorDelegate;
import omis.employment.web.form.EmployerChangeForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for change employer form.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 31, 2015)
 * @since OMIS 3.0
 */
public class ChangeEmployerFormValidator implements Validator {
	private AddressFieldsValidatorDelegate addressFieldsValidatorDelegate;
	
	/** Instantiates a validator for change employer form. */
	public ChangeEmployerFormValidator(final AddressFieldsValidatorDelegate
			addressFieldsValidatorDelegate) {
		// Default instantiation
		this.addressFieldsValidatorDelegate = addressFieldsValidatorDelegate;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EmployerChangeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		EmployerChangeForm employerChangeForm
			= (EmployerChangeForm) target;

		if(employerChangeForm.getNewEmployer()){
			if(employerChangeForm.getNewEmployerName()==null 
				|| "".equals(employerChangeForm.getNewEmployerName())){
				errors.rejectValue("newEmployerName", 
				"employerChangeForm.newEmployerName");
			}
			if (employerChangeForm.getNewAddress()) {
				if (employerChangeForm.getAddressFields() == null) {
					errors.rejectValue("addressFields",
						"createRelationships.addressFields.empty");
				} else {
					this.addressFieldsValidatorDelegate.validateAddressFields(
					employerChangeForm.getAddressFields(), "addressFields", 
					errors);
				}
			} 
			else {
				if (employerChangeForm.getAddressSearchResult()==null){
					errors.rejectValue("addressSearchResult",
						"createRelationships.locationSearchResult.empty");
				}
			}
		}else{
			if(employerChangeForm.getExistingEmployer()==null ){
				errors.rejectValue("existingEmployer", 
				"employerChangeForm.existingEmployer");
			}
		}
	}
}  