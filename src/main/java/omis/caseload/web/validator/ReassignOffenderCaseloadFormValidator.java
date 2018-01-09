package omis.caseload.web.validator;

import omis.caseload.web.form.ReassignOffenderCaseloadForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Reassign offender caseload form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 3, 2016)
 * @since OMIS 3.0
 */
public class ReassignOffenderCaseloadFormValidator implements Validator {

	/** Instantiates an implementation of 
	 * ReassignOffenderCaseloadFormValidator. */
	public ReassignOffenderCaseloadFormValidator() {
		// Default constructor.
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ReassignOffenderCaseloadForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ReassignOffenderCaseloadForm form 
						= (ReassignOffenderCaseloadForm) target;
		
		if (form.getReassignDate() == null) {
			errors.rejectValue("reassignDate", 
							"reassignOffender.reassignDate.empty");
		}
		if (form.getCaseload() == null) {
			errors.rejectValue("caseload", "reassignOffender.caseload.empty");
		}
		//need this to only be required if temporary transfering offenders
		if (form.getEndDate() == null) {
			errors.rejectValue("endDate", "reassignOffender.endDate.empty");
		}
	}
}