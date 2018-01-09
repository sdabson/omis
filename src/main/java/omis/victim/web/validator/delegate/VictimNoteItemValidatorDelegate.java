package omis.victim.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.victim.web.form.VictimNoteItem;

/**
 * Delegate to validator victim note items.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteItemValidatorDelegate {
	
	/** Instantiates delegate to validate victim note items. */
	public VictimNoteItemValidatorDelegate() {
		// Default instantiation
	}

	/**
	 * Validatates victim note item.
	 * 
	 * @param victimNoteItem victim note item
	 * @param itemIndex index of victim note item 
	 * @param errors errors
	 */
	public void validate(final VictimNoteItem victimNoteItem,
			final int itemIndex, final Errors errors) {
		if (victimNoteItem.getCategory() == null) {
			errors.rejectValue("noteItems[" + itemIndex + "].category",
					"victimNoteCategory.empty");
		}
		if (victimNoteItem.getDate() == null) {
			errors.rejectValue("noteItems[" + itemIndex + "].date",
					"victimNoteDate.empty");
		}
		if (victimNoteItem.getValue() == null
				|| victimNoteItem.getValue().isEmpty()) {
			errors.rejectValue("noteItems[" + itemIndex + "].value",
					"victimNoteValue.empty");
		}
	}
}