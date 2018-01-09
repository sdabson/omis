package omis.adaaccommodation.web.validator;

import omis.adaaccommodation.web.form.AccommodationForm;
import omis.adaaccommodation.web.form.AccommodationNoteItem;
import omis.adaaccommodation.web.form.AccommodationNoteItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Accommodation form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0
 */
public class AccommodationFormValidator 
	implements Validator {

	/** Instantiates a validator for accommodation forms. */
	public AccommodationFormValidator() {
		// Default constructor
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return AccommodationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		AccommodationForm accommodationForm = (AccommodationForm) target;
		if(accommodationForm.getStartDate() != null
				&& accommodationForm.getEndDate() != null
				&& accommodationForm.getStartDate().getTime()
				> accommodationForm.getEndDate().getTime()) {
			errors.rejectValue("startDate", 
					"dateRange.startDateGreaterThanEndDate");
		}
		if(accommodationForm.getStartDate() == null) {
			errors.rejectValue("startDate", 
					"authorization.authorizationTerm.startDate.empty");
		}
		
		if (accommodationForm.getTemporaryAuthorization() 
				&& accommodationForm.getEndDate() == null) {
			errors.rejectValue("endDate",
					"authorization.authorizationTerm.endDate.empty");
		}
		
		if(accommodationForm.getDisabilityCategory() == null) {
			errors.rejectValue("disabilityCategory", 
					"disability.disabilityClassificationCategory.empty");
		}
		if(accommodationForm.getDisabilityDescription() == "") {
			errors.rejectValue("disabilityDescription", 
					"disability.disabilityDescription.empty");
		}
		if(accommodationForm.getAuthorizationUser() == null) {
			errors.rejectValue("authorizationUser", 
					"authorization.authorizationUser.empty");
		}
		if(accommodationForm.getAuthorizationDate() == null) {
			errors.rejectValue("authorizationDate", 
					"authorization.authorizationDate.empty");
		}
		if(accommodationForm.getAccommodationDescription() == "") {
			errors.rejectValue("accommodationDescription", 
					"accommodation.description.empty");
		}
		if(accommodationForm.getAccommodationCategory() == null) {
			errors.rejectValue("accommodationCategory", 
					"accommodation.accommodationCategory.empty");
		}
		
		if(accommodationForm.getAuthorizationSourceCategory() == null) {
			errors.rejectValue("authorizationSourceCategory", 
					"authorization.authorizationSourceCategory.empty");
		}
		if (accommodationForm.getAccommodationNotes() != null) {
			int noteCount = 0;
			for (AccommodationNoteItem item : accommodationForm
					.getAccommodationNotes()) {
				if (item.getOperation() != null 
						&& !(AccommodationNoteItemOperation.REMOVE
								.equals(item.getOperation()))) {
					if (item.getText() == "") {
						errors.rejectValue(
								"accommodationNotes[" + noteCount + "].text",
								"accommodationNote.text.empty");
					}
					if (item.getDate() == null) {
						errors.rejectValue(
								"accommodationNotes[" + noteCount + "].date", 
								"accommodationNote.date.empty");
					}					
				}
				noteCount ++;
			}			
		}
	}
}