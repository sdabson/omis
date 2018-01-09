package omis.criminalassociation.validator;

import omis.criminalassociation.web.form.CriminalAssociationForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Criminal association Form Validator.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (April 14, 2015)
 * @since OMIS 3.0
 */
public class CriminalAssociationFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CriminalAssociationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CriminalAssociationForm associationForm 
			= (CriminalAssociationForm) target;	
		
		if (associationForm.getCriminalAssociationCategory() == null) {
			errors.rejectValue("criminalAssociationCategory", 
					"association.criminalAssociationCategory.empty");
		}
		if (associationForm.getOtherOffender() == null) {
			errors.rejectValue("otherOffender", 
					"associaiton.otherOffender.empty");
		}
	}
}