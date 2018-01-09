package omis.separationneed.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.separationneed.web.form.SeparationNeedForm;
import omis.separationneed.web.form.SeparationNeedNoteItem;
import omis.separationneed.web.form.SeparationNeedNoteItemOperation;
import omis.web.validator.StringLengthChecks;

/**
 * Separation Need Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 31, 2013)
 * @since OMIS 3.0
 */
public class SeparationNeedFormValidator implements Validator {
	
	/* Helpers. */
	
	private StringLengthChecks stringLengthChecks;
	
	/**
	 * Instantiates a default instance of separation need form validator.
	 */
	public SeparationNeedFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SeparationNeedForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SeparationNeedForm form = (SeparationNeedForm) target;
		
		if (form.getTargetOffender() == null) {
			errors.rejectValue("targetOffender", 
					"separationNeed.targetOffender.empty");
		}
		if (form.getDate() == null) {
			errors.rejectValue("date", "separationNeed.date.empty");
		}
		if (form.getCreationComment() != null) {
			this.stringLengthChecks.getHumongousCheck().check("creationComment",
					form.getCreationComment(), errors);
		}
		int noteItemCount = 0;
		for (SeparationNeedNoteItem item : form.getSeparationNeedNoteItems()) {
			if (item.getOperation() != null && 
					!SeparationNeedNoteItemOperation.REMOVE.equals(
							item.getOperation())) {
				if (item.getDate() == null) {
					errors.rejectValue("separationNeedNoteItems[" 
							+ noteItemCount + "].date",
							"separationNeed.noteItem.date.empty");
				}
				if (item.getNote() == null || item.getNote().isEmpty()) {
					errors.rejectValue("separationNeedNoteItems[" 
							+ noteItemCount + "].note",
							"separationNeed.noteItem.note.empty");
				} else {
					this.stringLengthChecks.getVeryHugeCheck().check(
							"separationNeedNoteItems[" + noteItemCount 
							+ "].note", item.getNote(), errors);
				}
			}
			noteItemCount++;
		}
		if (form.getRemovalDate() != null || form.getRemovalReason() != null
				|| form.getRemovalComment() != null 
				&& form.getRemovalComment().length() > 0) {
			this.stringLengthChecks.getHumongousCheck().check("removalComment",
					form.getRemovalComment(), errors);
			if (form.getRemovalDate() == null) {
				errors.rejectValue("removalDate",
						"separationNeed.removalDate.empty");
			}
			if (form.getRemovalReason() == null) {
				errors.rejectValue("removalReason",
						"separationNeed.removalReason.empty");
			}
			if (form.getDate() != null && form.getDate().getTime() 
					> form.getRemovalDate().getTime()) {
				errors.rejectValue("removalDate",
						"separationNeed.date.afterRemovalDate");
				errors.rejectValue("date",
						"separationNeed.date.afterRemovalDate");
			}
		}
		if (form.getSeparationNeedReasons() == null 
				|| form.getSeparationNeedReasons().size() < 1) {
			errors.rejectValue("separationNeedReasons",
					"seaprationNeed.separationNeedReasons.empty");
		}
	}
}