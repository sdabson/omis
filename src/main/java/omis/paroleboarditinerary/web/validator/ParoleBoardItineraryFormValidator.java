package omis.paroleboarditinerary.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.paroleboarditinerary.web.form.BoardMeetingSiteItem;
import omis.paroleboarditinerary.web.form.BoardMeetingSiteItemOperation;
import omis.paroleboarditinerary.web.form.ParoleBoardItineraryForm;
import omis.paroleboarditinerary.web.form.ParoleBoardItineraryNoteItem;
import omis.paroleboarditinerary.web.form.ParoleBoardItineraryNoteItemOperation;

/**
 * Validator for parole board itinerary.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 29, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardItineraryFormValidator implements Validator {

	/**
	 * Instantiates a default parole board itinerary form validator.
	 */
	public ParoleBoardItineraryFormValidator() {
		// Default instantiation
	} 
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ParoleBoardItineraryForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		ParoleBoardItineraryForm form = (ParoleBoardItineraryForm) target;
		
		if (form.getStartDate() == null) {
			errors.rejectValue("startDate", 
					"paroleBoardItinerary.dateRange.startDate.empty");
		}
		if (form.getStartDate() != null && form.getEndDate() != null && 
				form.getStartDate().getTime() > form.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"paroleBoardItinerary.dateRange.startDateGreaterThanEndDate");
		}
		if (form.getLocation() == null) {
			errors.rejectValue("location", 
					"paroleBoardItinerary.location.empty");
		}
		if (form.getBoardMeetingSiteItems() != null && 
				form.getBoardMeetingSiteItems().size() > 0) {
			for (int index = 0; index < form.getBoardMeetingSiteItems().size(); 
					index++) {
				BoardMeetingSiteItem siteItem = 
						form.getBoardMeetingSiteItems().get(index);
				if (BoardMeetingSiteItemOperation.CREATE.equals(
						siteItem.getOperation()) || 
						BoardMeetingSiteItemOperation.EDIT.equals(
								siteItem.getOperation())) {
					if (siteItem.getDate() == null) {
						errors.rejectValue("boardMeetingSiteItems[" + index + 
								"].date", 
								"paroleBoardItinerary.boardMeetingSite.date.empty");
					}
					if (siteItem.getLocation() == null) {
						errors.rejectValue("boardMeetingSiteItems[" + index + 
								"].location", 
								"paroleBoardItinerary.boardMeetingSite.location.empty");
					}
					if (siteItem.getOrder() == null) {
						errors.rejectValue("boardMeetingSiteItems[" + index + 
								"].order", 
								"paroleBoardItinerary.boardMeetingSite.order.empty");
					}
				}
			}
		}
		if (form.getBoardItineraryNoteItems() != null && 
				form.getBoardItineraryNoteItems().size() > 0) {
			for (int index = 0; index < 
					form.getBoardItineraryNoteItems().size(); index++) {
				ParoleBoardItineraryNoteItem noteItem = 
						form.getBoardItineraryNoteItems().get(index);
				if (ParoleBoardItineraryNoteItemOperation.CREATE.equals(
						noteItem.getOperation()) || 
						ParoleBoardItineraryNoteItemOperation.EDIT.equals(
								noteItem.getOperation())) {
					if (noteItem.getDate() == null) {
						errors.rejectValue("boardItineraryNoteItems[" + index + 
								"].date",
								"paroleBoardItinerary.boardItineraryNote.date.empty");
					}
					if (noteItem.getValue() == null || 
							noteItem.getValue().isEmpty()) {
						errors.rejectValue("boardItineraryNoteItems[" + index + 
								"].value",
								"paroleBoardItinerary.boardItineraryNote.value.empty");
					}
				}
			}
		}
	}

}
