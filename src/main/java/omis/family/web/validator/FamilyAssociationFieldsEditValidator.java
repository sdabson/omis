package omis.family.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.family.web.form.FamilyAssociationForm;
import omis.family.web.form.FamilyAssociationNoteItem;
import omis.family.web.form.FamilyAssociationNoteItemOperation;

/**
 * Validator for family association edit screen.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 10, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationFieldsEditValidator implements Validator {
	/** Instantiates a validator for family form. */
	public FamilyAssociationFieldsEditValidator() {
		// Default instantiation
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return FamilyAssociationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		FamilyAssociationForm familyAssociationFields 
			= (FamilyAssociationForm) target;
		
		int index = 0;
		if (familyAssociationFields.getFamilyAssociationNoteItems() != null) {
			for (FamilyAssociationNoteItem item : familyAssociationFields
				.getFamilyAssociationNoteItems()) {
				if (FamilyAssociationNoteItemOperation.CREATE.equals(
						item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue(
								"familyAssociationNoteItems[" + index + "]"
										+ ".date", 
								"familyAssociationFields.familyAssociationNote"
								+ ".date.empty");
					}
					if (item.getNote() == null || item.getNote().isEmpty()) {
						errors.rejectValue(
								"familyAssociationNoteItems[" + index + "]"
										+ ".note", 
								"familyAssociationFields.familyAssociationNote"
										+ ".note.empty");
					}
				}
				index++;
			}
		}
		
		if (familyAssociationFields.getCategory() == null) {
			errors.rejectValue("category", 
				"familyAssociationFields.category.empty");
		}
	}
}  