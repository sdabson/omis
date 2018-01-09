package omis.caseload.web.validator;

import omis.caseload.web.form.CaseloadOffenderContactForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Caseload offender contact form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Oct 5, 2016)
 * @since OMIS 3.0
 */
public class CaseloadOffenderContactFormValidator implements Validator {

	/** Instantiates an implementation of 
	 * 	caseload offender contact form validator. */
	public CaseloadOffenderContactFormValidator() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CaseloadOffenderContactForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CaseloadOffenderContactForm form = (CaseloadOffenderContactForm) target;
		
		if (form.getContactDate() == null) {
			errors.rejectValue("contactDate", 
							"offenderContact.contactDate.empty");
		}
		if (form.getContactBy() == null) {
			errors.rejectValue("contactBy", "offenderContact.contactBy.empty");
		}
		if (form.getCategory() == null) {
			errors.rejectValue("category", "offenderContact.category.empty");
		}
	}
}