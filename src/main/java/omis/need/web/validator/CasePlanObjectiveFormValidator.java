package omis.need.web.validator;

import omis.need.domain.ObjectiveSource;
import omis.need.web.form.CasePlanObjectiveForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Case plan objective form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 14, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveFormValidator implements Validator{
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CasePlanObjectiveForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CasePlanObjectiveForm form = (CasePlanObjectiveForm) target;
		
		if (form.getName() == null || form.getName().length() < 0) {
			errors.rejectValue("name", "casePlanObjective.name.empty");
		}
		if (form.getIdentifiedDate() == null) {
			errors.rejectValue("identifiedDate",
					"casePlanObjective.identifiedDate.empty");
		}
		if (form.getPriority() == null) {
			errors.rejectValue("priority",
					"casePlanObjective.objectivePriority.empty");
		}
		if (form.getSource() == null) {
			errors.rejectValue("source", "casePlanObjective.source.empty");
		}
		if (ObjectiveSource.STAFF.equals(form.getSource())) {
			if(form.getStaffMember() == null) {
				errors.rejectValue("staffMember",
						"casePlanObjective.staffMember.empty");
			}
		}
		if (form.getDomain() == null) {
			errors.rejectValue("domain", "casePlanObjective.domain.empty");
		}
	}
}