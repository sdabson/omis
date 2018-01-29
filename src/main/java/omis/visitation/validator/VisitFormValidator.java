package omis.visitation.validator;

import omis.visitation.web.form.VisitForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Visit form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 12, 2013)
 * @since OMIS 3.0
 */
public class VisitFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VisitForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VisitForm form = (VisitForm) target;
		
		if (form.getBadgeNumber() == null || form.getBadgeNumber()
				.length() < 1) {
			errors.rejectValue("badgeNumber", "visit.badgeNumber.empty");
		}
		if (form.getDate() == null) {
			errors.rejectValue("date", "visit.date.empty");
		}
		if (form.getStartTime() == null) {
			errors.rejectValue("startTime", "visit.startTime.empty");
		} else if (form.getEndTime() != null) {
			if (form.getStartTime().getTime() >= form.getEndTime().getTime()) {
				errors.rejectValue("startTime", "visit.startTime.afterEndTime");
			}
		}
		if (form.getVisitationAssociation() == null) {
			errors.rejectValue("visitationAssociation", 
					"visit.visitationAssociation.empty");
		}
		if (form.getVisitMethod()== null) {
			errors.rejectValue("visitMethod", "visit.visitMethod.empty");
		}
	}
}
