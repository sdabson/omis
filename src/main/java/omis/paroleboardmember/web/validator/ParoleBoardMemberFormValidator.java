package omis.paroleboardmember.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.paroleboardmember.web.form.ParoleBoardMemberForm;

/**
 * Validator for parole board members.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberFormValidator implements Validator {

	/**
	 * Instantiates a default parole board member form validator.
	 */
	public ParoleBoardMemberFormValidator() {
		// Default instantiation
	} 
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ParoleBoardMemberForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ParoleBoardMemberForm form = (ParoleBoardMemberForm) target;
		
		if (form.getStartDate() == null) {
			errors.rejectValue("startDate", 
					"paroleBoardMember.dateRange.startDate.empty");
		}
		if (form.getStartDate() != null && form.getEndDate() != null && 
				form.getStartDate().getTime() > form.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"paroleBoardMember.dateRange.startDateGreaterThanEndDate");
		}
		if (form.getStaffAssignment() == null) {
			errors.rejectValue("staffAssignment", 
					"paroleBoardMember.staffAssignment.empty");
		}
		
	}

}
