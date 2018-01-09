package omis.hearing.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.hearing.domain.HearingCategory;
import omis.hearing.web.form.HearingForm;
import omis.hearing.web.form.HearingNoteItem;
import omis.hearing.web.form.ItemOperation;
import omis.hearing.web.form.StaffAttendanceItem;

/**
 * HearingFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Apr 28, 2017)
 *@since OMIS 3.0
 *
 */
public class HearingFormValidator implements Validator {
	
	private static final String LOCATION_REQUIRED_MSG_KEY = "location.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String OFFICER_REQUIRED_MSG_KEY = "officer.required";
	
	private static final String STATUS_REQUIRED_MSG_KEY = "status.required";
	
	private static final String CATEGORY_REQUIRED_MSG_KEY = "category.empty";
	
	private static final String HELD_IN_FUTURE_MSG_KEY =
			"hearingStatus.inFuture";
	
	private static final String OFFENDER_ATTENDANCE_FUTURE_MSG_KEY =
			"offenderAttendance.future";
	
	private static final String STAFF_REQUIRED_MSG_KEY = "staff.required";
	
	private static final String STAFF_EMPTY_IF_HELD_MSG_KEY =
			"staff.emptyIfHeld";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String CATEGORY_UNMATCHED_MSG_KEY = 
			"hearing.category.unmatched";
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return HearingForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		HearingForm form = (HearingForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "location", 
				LOCATION_REQUIRED_MSG_KEY);
		
		ValidationUtils.rejectIfEmpty(errors, "date", DATE_REQUIRED_MSG_KEY);
		
		ValidationUtils.rejectIfEmpty(errors, "officer", 
				OFFICER_REQUIRED_MSG_KEY);
		
		ValidationUtils.rejectIfEmpty(errors, "status", 
				STATUS_REQUIRED_MSG_KEY);
		
		ValidationUtils.rejectIfEmpty(errors, "category", 
				CATEGORY_REQUIRED_MSG_KEY);
		
		//ensuring that HearingCategory and infractions ViolationEventCategory
		//match, which the user would only be able to change via source-code
		//manipulation
		if(!form.getInfractionItems().isEmpty()) {
			if((form.getInfractionItems().get(0)
					.getConditionViolation() != null
					&& !(HearingCategory.SUPERVISION.equals(
							form.getCategory())))
				|| form.getInfractionItems().get(0)
					.getDisciplinaryCodeViolation() != null
					&& !(HearingCategory.DISCIPLINARY.equals(
							form.getCategory()))){
				errors.rejectValue("category",
						CATEGORY_UNMATCHED_MSG_KEY);
			}
		}
		
		if(form.getDate() != null){
			if(form.getDate().getTime() > new Date().getTime()){
				if(form.getStatus() != null) {
					if(form.getStatus().getAdjudicated()){
						errors.rejectValue("status",
								HELD_IN_FUTURE_MSG_KEY);
					}
				}
				if(form.getInAttendance() == true){
					errors.rejectValue("inAttendance",
							OFFENDER_ATTENDANCE_FUTURE_MSG_KEY);
				}
				if(!form.getStaffAttendanceItems().isEmpty()){
					errors.rejectValue("staffAttendanceItems",
							STAFF_EMPTY_IF_HELD_MSG_KEY);
				}
			}
		}
		
		if(form.getHearingNoteItems() != null){
			int i = 0;
			for(HearingNoteItem item : form.getHearingNoteItems()){
				if(ItemOperation.CREATE.equals(item.getItemOperation())
						|| ItemOperation.UPDATE.equals(item.getItemOperation())){
					if (item.getDate() == null) {
						errors.rejectValue("hearingNoteItems[" + i 
								+ "].date", DATE_REQUIRED_MSG_KEY);
					}
					if (item.getDate() == null) {
						errors.rejectValue(
								"hearingNoteItems[" + i + "].description",
								DESCRIPTION_REQUIRED_MSG_KEY);
					}
				}
				i++;
			}
		}
		if(form.getStaffAttendanceItems() != null){
			int i = 0;
			for(StaffAttendanceItem item : form.getStaffAttendanceItems()){
				if(ItemOperation.CREATE.equals(item.getItemOperation())
					|| ItemOperation.UPDATE.equals(item.getItemOperation())){
					if (item.getStaff() == null) {
						errors.rejectValue(
								"staffAttendanceItems[" + i + "].staff",
								STAFF_REQUIRED_MSG_KEY);
					}
				}
				i++;
			}
		}
	}
}
