package omis.employment.web.validator;

import omis.employment.web.form.EmploymentForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for employment form.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Sept 2, 2015)
 * @since OMIS 3.0
 */
public class EmploymentFormNoEmployerValidator implements Validator {
	/** Instantiates a validator for employment form. */
	public EmploymentFormNoEmployerValidator() {
		// Default instantiation
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

		if ("".equals(employmentForm.getJobTitle())) {
			errors.rejectValue("jobTitle", "employmentTerm.job.jobTitle.empty");
		} 
		if ("".equals(employmentForm.getEmployerName())) {
			errors.rejectValue("employerName", "employmentForm.employerName");
		} 

		if (employmentForm.getStartDate() != null
			&& employmentForm.getEndDate() != null
			&& employmentForm.getStartDate().getTime()
			> employmentForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
			"employmentForm.startDate.startDateGreaterThanEndDate");
		}
	}
}  