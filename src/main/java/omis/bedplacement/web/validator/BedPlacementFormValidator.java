package omis.bedplacement.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.bedplacement.web.form.BedPlacementForm;
import omis.util.DateManipulator;

/**
 * Bed placement form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 13, 2013)
 * @since OMIS 3.0
 */
public class BedPlacementFormValidator implements Validator {
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return BedPlacementForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		
		BedPlacementForm form = (BedPlacementForm) target;
		
		if (form.getRoom() == null) {
			errors.rejectValue("room", "placement.room.empty");
		}
		if (form.getBed() == null) {
			errors.rejectValue("bed", "placement.bed.empty");
		}
		if (form.getBedPlacementReason() == null) {
			errors.rejectValue("bedPlacementReason", 
					"placement.bedPlacementReason.empty");
		}
		if (form.getStartDate() == null) {
			errors.rejectValue("startDate", 
					"placement.startDate.empty");
		}
		if (form.getStartDate() != null 
				&& form.getEndDate() != null) {
			final Date startDate;
			if (form.getStartTime() != null) {
				startDate = DateManipulator.getDateAtTimeOfDay(
						form.getStartDate(), form.getStartTime());
			} else {
				startDate = form.getStartDate();
			}
			final Date endDate;
			if (form.getEndTime() != null) {
				endDate = DateManipulator.getDateAtTimeOfDay(
						form.getEndDate(), form.getEndTime());
			} else {
				endDate = form.getEndDate();
			}
			if (startDate.getTime() 
					> endDate.getTime()) {
				errors.rejectValue("startDate", 
						"placement.startDate.afterEndDate");
			}
		}
		if (form.getConfirmed()) {
			if (form.getStartTime() == null) {
				errors.rejectValue("startTime",
						"placement.startTime.empty");
			}
		}
		if (form.getEndDate() != null) {
			if (form.getEndTime() == null) {
				errors.rejectValue("endTime",
						"placement.endTime.empty");
			}
		}
		if (form.getEndActiveBedPlacement() != null && form.getEndActiveBedPlacement()) {
			if (form.getActiveBedPlacement() != null) {
				if ((form.getStartDate() != null && form.getStartTime() != null) 
						&& DateManipulator.getDateAtTimeOfDay(form.getStartDate(), form.getStartTime()).getTime()
						< form.getActiveBedPlacement().getDateRange().getStartDate().getTime()) {
					errors.rejectValue("startDate", "placement.startDate.beforeActiveBedPlacementStartDate");
				}
			} else {
				errors.rejectValue("endActiveBedPlacement",
						"placement.endExistingBedPlacement.noActiveBedPlacement");
			}
		}
	}
}