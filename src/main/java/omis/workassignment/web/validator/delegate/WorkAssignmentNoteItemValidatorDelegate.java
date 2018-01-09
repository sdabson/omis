package omis.workassignment.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.workassignment.web.form.WorkAssignmentNoteItem;

/**
 * Delegate to validator work assignment note items.
 *
 * @author Yidong Li
 * @version 0.0.1 (Sept 19, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentNoteItemValidatorDelegate {
	
	/** Instantiates delegate to validate work assignment note items. */
	public WorkAssignmentNoteItemValidatorDelegate() {
		// Default instantiation
	}

	/**
	 * Validatates work assignment note item.
	 * 
	 * @param workAssignmentNoteItem work assignment note item
	 * @param itemIndex index of work assignment note item 
	 * @param errors errors
	 */
	public void validate(final WorkAssignmentNoteItem workAssignmentNoteItem,
			final int itemIndex, final Errors errors) {
		if (workAssignmentNoteItem.getDate() == null) {
			errors.rejectValue("workAssignmentNoteItems[" + itemIndex + "].date",
				"WorkAssignmentNote.date.empty");
		}
		if (workAssignmentNoteItem.getNote() == null ||workAssignmentNoteItem
			.getNote().isEmpty()) {
			errors.rejectValue("workAssignmentNoteItems[" + itemIndex + "].note",
				"WorkAssignmentNote.note.empty");
		}
	}
}