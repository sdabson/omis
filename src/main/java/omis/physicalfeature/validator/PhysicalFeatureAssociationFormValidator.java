package omis.physicalfeature.validator;

import omis.physicalfeature.web.form.PhotoItemOperation;
import omis.physicalfeature.web.form.PhysicalFeatureAssociationForm;
import omis.physicalfeature.web.form.PhysicalFeatureAssociationNoteItem;
import omis.physicalfeature.web.form.PhysicalFeatureAssociationNoteItemOperation;
import omis.physicalfeature.web.form.PhysicalFeaturePhotoItem;

import java.util.Arrays;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Physical Feature Association Form Validator.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Dec 12, 2017)
 * @since OMIS 3.0
 */
public class PhysicalFeatureAssociationFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return PhysicalFeatureAssociationForm.class.isAssignableFrom(clazz);
	}
	

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		PhysicalFeatureAssociationForm form = 
				(PhysicalFeatureAssociationForm) target;
		if (form.getFeature() == null) {
			errors.rejectValue("feature", "physicalFeature.feature.empty");
		}
		if (form.getDescription() == null 
				|| form.getDescription().length() < 1) {
			errors.rejectValue("description", 
					"physicalFeature.description.empty");
		}
		int photoItemIndex = 0;
		for (PhysicalFeaturePhotoItem photoItem : form.getPhotoItems()) {
			if (PhotoItemOperation.CREATE.equals(photoItem.getOperation())
					|| PhotoItemOperation.UPDATE.equals(
							photoItem.getOperation())) {
				if (PhotoItemOperation.CREATE.equals(photoItem.getOperation())) {
					if (photoItem.getPhotoData() == null 
							|| photoItem.getPhotoData().length == 0) {
							errors.rejectValue("photoItems[" + photoItemIndex 
							+ "].photoData", "opf.photoItem.photoData.empty");					
					}
				}
				if (photoItem.getDate() == null) {
					errors.rejectValue("photoItems[" + photoItemIndex 
							+ "].date", "opf.photoItem.date.empty");
				}
				boolean foundDuplicate = false;
				for (int previousPhotoItemIndex = 0;
						previousPhotoItemIndex < photoItemIndex;
						previousPhotoItemIndex++) {
					PhysicalFeaturePhotoItem thatPhotoItem 
						= form.getPhotoItems().get(previousPhotoItemIndex);
					if (PhotoItemOperation.CREATE.equals(thatPhotoItem
							.getOperation()) || PhotoItemOperation.UPDATE
							.equals(thatPhotoItem.getOperation())) {
						if (photoItem.getPhotoData() != null
								&& photoItem.getPhotoData().length > 0
								&& Arrays.equals(photoItem.getPhotoData(), 
										thatPhotoItem.getPhotoData())) {
							foundDuplicate = true;
						}
					}
				}
				if (foundDuplicate) {
					errors.rejectValue("photoItems[" + photoItemIndex + "]", 
							"physicalFeature.photoItem.duplicate");
				}
			}
			photoItemIndex++;
		}
		int noteItemIndex = 0;
		for (PhysicalFeatureAssociationNoteItem item : form.getNoteItems()) {
			if (PhysicalFeatureAssociationNoteItemOperation.CREATE
					.equals(item.getOperation()) 
					|| PhysicalFeatureAssociationNoteItemOperation.UPDATE
					.equals(item.getOperation())) {
				if (item.getDate() == null) {
					errors.rejectValue("noteItems[" + noteItemIndex 
							+ "].date", "opf.noteItem.date.empty");
				}
				if (item.getNote() == null || item.getNote().length() < 1) {
					errors.rejectValue("noteItems[" + noteItemIndex 
							+ "].note", "opf.noteItem.note.empty");
				}
			}
			noteItemIndex++;
		}
	}
}