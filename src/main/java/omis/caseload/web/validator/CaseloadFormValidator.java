package omis.caseload.web.validator;

import omis.caseload.web.form.CaseloadForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/** Validator for caseload form.
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 19, 2016)
 * @since OMIS 3.0
 */
public class CaseloadFormValidator implements Validator {

	/** Instantiates a validator for accommodation forms. */
	public CaseloadFormValidator() {
		// Default constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CaseloadForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CaseloadForm caseloadForm = (CaseloadForm) target;
		if (caseloadForm.getCaseworkCategory() == null) {
			errors.rejectValue("category", "caseload.category.empty");
		}
		if (caseloadForm.getCaseloadName() == null) {
			errors.rejectValue("name", "caseload.name.empty");
		}
		if (caseloadForm.getStartDate() != null
						&& caseloadForm.getEndDate() != null
						&& caseloadForm.getStartDate().getTime() 
						> caseloadForm.getEndDate().getTime()) {
			errors.rejectValue("startDate", "caseload.startDate.empty");
		}
		
	}
}