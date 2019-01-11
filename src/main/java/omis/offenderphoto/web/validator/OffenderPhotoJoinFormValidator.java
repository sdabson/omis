package omis.offenderphoto.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenderphoto.web.form.OffenderPhotoAssociationNoteItem;
import omis.offenderphoto.web.form.OffenderPhotoAssociationNoteItemOperation;
import omis.offenderphoto.web.form.OffenderPhotoJoinForm;
import omis.web.validator.StringLengthChecks;

/**
 * Offender photo join form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (November 20, 2018)
 * @since OMIS 3.0
 */
public class OffenderPhotoJoinFormValidator implements Validator {
	
	private final StringLengthChecks stringLengthChecks;
	
	public OffenderPhotoJoinFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return OffenderPhotoJoinForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		OffenderPhotoJoinForm form = (OffenderPhotoJoinForm) target;
		
		if(form.getPhotoData() == null || form.getPhotoData().length == 0) {
			errors.rejectValue("photoData",
					"photo.empty");
		}
		if(form.getPhotoDate() == null) {
			errors.rejectValue("photoDate", "photoDate.empty");
		}
		if(form.getPhotoItems() != null) {
			//Check for new photo item flags, check for photo data and date
		}
		if (form.getNoteItems() != null) {
			int index = 0;
			for (OffenderPhotoAssociationNoteItem item 
					: form.getNoteItems()) {
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
				}
				index++;
			}
		}
	}
}
