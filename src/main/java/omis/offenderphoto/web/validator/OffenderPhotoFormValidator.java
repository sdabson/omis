package omis.offenderphoto.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenderphoto.web.form.OffenderPhotoAssociationNoteItem;
import omis.offenderphoto.web.form.OffenderPhotoAssociationNoteItemOperation;
import omis.offenderphoto.web.form.OffenderPhotoForm;
import omis.web.validator.StringLengthChecks;

/**
 * Validator for offender photo form.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 28, 2014)
 * @since OMIS 3.0
 */
public class OffenderPhotoFormValidator
		implements Validator {

	private final boolean requirePhotoData;
	
	private final StringLengthChecks stringLengthChecks;
	
	/**
	 * Instantiates a validator for offender photo forms.
	 * 
	 * @param requirePhotoData whether photo data is required
	 * @param stringLengthChecks string length checks
	 */
	public OffenderPhotoFormValidator(
			final boolean requirePhotoData,
			final StringLengthChecks stringLengthChecks) {
		this.requirePhotoData = requirePhotoData;
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderPhotoForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderPhotoForm offenderPhotoForm = (OffenderPhotoForm) target;
		if (requirePhotoData && (offenderPhotoForm.getPhotoData() == null
				|| offenderPhotoForm.getPhotoData().length == 0)) {
			errors.rejectValue("photoData", "photo.empty");
		}
		if (offenderPhotoForm.getPhotoDate() == null) {
			errors.rejectValue("photoDate", "photoDate.empty");
		}
		int index = 0;
		if (offenderPhotoForm.getNoteItems() != null) {
			for (OffenderPhotoAssociationNoteItem item 
					: offenderPhotoForm.getNoteItems()) {
				if (OffenderPhotoAssociationNoteItemOperation.CREATE.equals(item
						.getOperation()) 
						|| OffenderPhotoAssociationNoteItemOperation.UPDATE
						.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("noteItems[" 
								+ index + "].date",
								"offenderPhotoAssociationNote.date.empty");
					}
					if (item.getValue() == null || item.getValue().isEmpty()) {
						errors.rejectValue("noteItems["
								+ index + "].value",
								"offenderPhotoAssociationNote.value.empty");
					} else {
						stringLengthChecks.getHumongousCheck().check(
								"offenderPhotoAssociationNoteItems["
								+ index + "].value", item.getValue(), errors);
					}
					index++;
				}
			}
		}
	}
}