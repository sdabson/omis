package omis.workassignment.web.validator;
import java.util.List;

import omis.web.validator.StringLengthChecks;
import omis.workassignment.web.form.WorkAssignmentForm;
import omis.workassignment.web.form.WorkAssignmentNoteItem;
import omis.workassignment.web.form.WorkAssignmentNoteItemOperation;
import omis.workassignment.web.validator.delegate.WorkAssignmentNoteItemValidatorDelegate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for work assignment form.
 * 
 * @author Yidong Li
 * @version 0.1.1 (Aug 23, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentFormValidator implements Validator {
	private static final String WORK_ASSIGNMENT_DATE_EMPTY_ERROR_KEY
		= "WorkAssignment.assignedDate.empty";
	private static final String ASSIGNED_DATE_PROPERTY_NAME = "assignmentDate";
	private static final String FENCE_RESTRICTION_EMPTY_ERROR_KEY
		= "WorkAssignment.fenceRestriction.empty";
	private static final String FENCE_RESTRICTION_PROPERTY_NAME 
		= "fenceRestriction";
	private static final String WORK_ASSIGNMENT_CATEGORY_EMPTY_ERROR_KEY
		= "WorkAssignment.category.empty";
	private static final String CATEGORY_PROPERTY_NAME 
		= "workAssignmentCategory";
	private static final String WORK_ASSIGNMENT_CHANGE_REASON_EMPTY_ERROR_KEY
		= "WorkAssignment.changeReason.empty";
	private static final String CHANGE_REASON_PROPERTY_NAME 
		= "workAssignmentChangeReason";
	private static final String COMMENTS_PROPERTY_NAME 
		= "comments";
	private static final String ASSIGNMENT_DATE_PROPERTY_NAME 
		= "assignmentDate";
	private static final String ASSIGNMENT_DATE_GREATER_THAN_EMPTY_ERROR_KEY
		= "workAssignmentForm.assignmentDate.assignmentDateGreaterThanTerminationDate";
	private final StringLengthChecks stringLengthChecks;
	private final WorkAssignmentNoteItemValidatorDelegate workAssignmentNoteItemValidatorDelegate;
	
	/** Instantiates a validator for offender work assignment form. */
	public WorkAssignmentFormValidator(
			final StringLengthChecks stringLengthChecks,
			final WorkAssignmentNoteItemValidatorDelegate 
			workAssignmentNoteItemValidatorDelegate) {
		this.stringLengthChecks = stringLengthChecks;
		this.workAssignmentNoteItemValidatorDelegate 
			= workAssignmentNoteItemValidatorDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return WorkAssignmentForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		WorkAssignmentForm workAssignmentForm
			= (WorkAssignmentForm) target;
		if (workAssignmentForm.getAssignmentDate() == null) {
			errors.rejectValue(ASSIGNED_DATE_PROPERTY_NAME,
			WORK_ASSIGNMENT_DATE_EMPTY_ERROR_KEY);
		} 
		if (workAssignmentForm.getFenceRestriction() == null) {
			errors.rejectValue(FENCE_RESTRICTION_PROPERTY_NAME, 
			FENCE_RESTRICTION_EMPTY_ERROR_KEY);
		}
		if (workAssignmentForm.getWorkAssignmentCategory() == null) {
			errors.rejectValue(CATEGORY_PROPERTY_NAME,
			WORK_ASSIGNMENT_CATEGORY_EMPTY_ERROR_KEY);
		}
		if (workAssignmentForm.getWorkAssignmentChangeReason() == null) {
			errors.rejectValue(CHANGE_REASON_PROPERTY_NAME, 
			WORK_ASSIGNMENT_CHANGE_REASON_EMPTY_ERROR_KEY);
		}
		this.stringLengthChecks.getVeryHugeCheck().check(
			COMMENTS_PROPERTY_NAME, workAssignmentForm.getComments(), errors);
		
		if (workAssignmentForm.getAssignmentDate() != null
			&& workAssignmentForm.getTerminationDate() != null
			&& workAssignmentForm.getAssignmentDate().getTime()
			> workAssignmentForm.getTerminationDate().getTime()) {
			errors.rejectValue(ASSIGNMENT_DATE_PROPERTY_NAME,
			ASSIGNMENT_DATE_GREATER_THAN_EMPTY_ERROR_KEY);
		}
			
		int index = 0;
		if(workAssignmentForm.getWorkAssignmentNoteItems()!=null){
			List<WorkAssignmentNoteItem> workAssignmentNoteItems 
				= workAssignmentForm.getWorkAssignmentNoteItems();
			for(WorkAssignmentNoteItem noteItem : workAssignmentNoteItems){
				if(noteItem.getOperation()!=null){
					if(noteItem.getOperation().equals(
						WorkAssignmentNoteItemOperation.UPDATE)||
						noteItem.getOperation().equals(
							WorkAssignmentNoteItemOperation.CREATE)){
						workAssignmentNoteItemValidatorDelegate.validate(noteItem, 
							index, errors);
					}
					index = index + 1;
				}
			}
		}
	}
}