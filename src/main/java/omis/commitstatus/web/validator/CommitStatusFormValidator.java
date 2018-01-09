package omis.commitstatus.web.validator;

import omis.commitstatus.web.form.CommitStatusForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for commit status form.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 7, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusFormValidator	implements Validator {
	/**
	 * Instantiates a validator for commit status form.
	 * 
	 * @param familyAssociationFieldsValidatorDelegate family association fields
	 * validator delegate
	 */
	public CommitStatusFormValidator( ){}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CommitStatusForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CommitStatusForm commitStatusForm = (CommitStatusForm)target;
		if (commitStatusForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDateEmpty");
		} 
		if (commitStatusForm.getCommitStatus()== null) {
			errors.rejectValue("commitStatus", "statusEmpty");
		} 
		if (commitStatusForm.getStartDate() != null
			&& commitStatusForm.getEndDate() != null
			&& commitStatusForm.getStartDate().getTime()
			> commitStatusForm.getEndDate().getTime()) {
				errors.rejectValue("startDate", "startDateGreaterThanEndDate");
		}
	}
}