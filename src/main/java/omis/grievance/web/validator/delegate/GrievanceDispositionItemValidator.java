package omis.grievance.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.grievance.web.form.GrievanceDispositionItem;

/**
 * Validator for grievance disposition item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 6, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionItemValidator {

	/* Constructors. */
	
	/** Validator for grievance disposition items. */
	public GrievanceDispositionItemValidator() {
		// Default instantiation
	}
	
	/* Methods. */
	
	/**
	 * Validators grievance disposition item.
	 * 
	 * @param grievanceDispositionItem grievance disposition item to validate
	 * @param errors errors
	 */
	public void validate(
			final GrievanceDispositionItem grievanceDispositionItem,
			final Errors errors,
			final String grievanceDispositionItemName) {
		if (grievanceDispositionItem.getEdit() != null
				&& grievanceDispositionItem.getEdit()) {
			if (grievanceDispositionItem.getStatus() == null) {
				errors.rejectValue(
					grievanceDispositionItemName + ".status",
					"grievanceDispositionStatus.empty");
			}
			if (grievanceDispositionItem.getResponseDueDate() == null) {
				errors.rejectValue(
					grievanceDispositionItemName + ".responseDueDate",
					"grievanceDispositionResponseDueDate.empty");
			}
			if (grievanceDispositionItem.getStatus() != null
					&& grievanceDispositionItem.getStatus().getClosed()
					&& grievanceDispositionItem.getClosedDateAllowed() != null
					&& grievanceDispositionItem.getClosedDateAllowed()
					&& grievanceDispositionItem.getClosedDate() == null) {
				errors.rejectValue(
						grievanceDispositionItemName + ".closedDate",
							"grievanceDispositionClosedDate.requiredByStatus");
			}
		}
	}
}