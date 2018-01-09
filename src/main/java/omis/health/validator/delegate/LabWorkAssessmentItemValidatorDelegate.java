package omis.health.validator.delegate;

import omis.health.web.form.LabWorkAssessmentItem;

import org.springframework.validation.Errors;

/**
 * Delegate to handle validation of a single lab work assessment item that
 * is part of a collection.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 13, 2014)
 * @since OMIS 3.0
 */
public class LabWorkAssessmentItemValidatorDelegate {

	/**
	 * Returns {@code errors} with any additional rejected values for the
	 * specified lab work assessment item. (Assumes that the item resides in
	 * collection of lab work assessment items called "labWorkAssessmentItems".
	 * 
	 * @param item lab work assessment item
	 * @param errors errors
	 * @param LabWorkAssessmentItemIndex lab work assessment item 
	 * @return errors with rejected values
	 */
	public Errors validateLabWorkAssessmentItem(
			final LabWorkAssessmentItem item, final Errors errors,
			final int LabWorkAssessmentItemIndex) {
		return errors;
	}
}