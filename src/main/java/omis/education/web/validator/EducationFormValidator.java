package omis.education.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.education.web.form.EducationForm;
import omis.education.web.form.EducationNoteItem;
import omis.education.web.form.EducationalAchievementItem;
import omis.education.web.form.ItemOperation;

/**
 * EducationFormValidator.java
 * 
 *@author Annie Jacques 
 *@author Ryan Johns
 *@version 0.1.1 (Nov 21, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationFormValidator implements Validator{
	
	/* Message Keys */
	
	private static final String VERIFICATION_METHOD_REQUIRED_MSG_KEY 
		= "verificationMethod.empty";
	
	private static final String ACHIEVEMENT_DATE_REQUIRED_MSG_KEY 
		= "date.empty";
	
	private static final String ACHIEVEMENT_DESCRIPTION_REQUIRED_MSG_KEY 
		= "description.empty";
	
	private static final String NOTE_DATE_REQUIRED_MSG_KEY = "date.empty";
	
	private static final String NOTE_DESCRIPTION_REQUIRED_MSG_KEY 
		= "description.empty";

	private static final String END_DATE_BEFORE_MSG_KEY 
		= "attendedEndDate.beforeAttendedStartDate";
	
	private static final String START_DATE_REQUIRED_MSG_KEY 
		= "attendedStartDate.empty";

	private static final String VERIFICATION_DATE_AFTER_TODAY_MSG_KEY 
		= "verificationDate.after";

	private static final String END_DATE_AFTER_TODAY_MSG_KEY 
		= "attendedEndDate.after";

	private static final String ACHIEVEMENT_DATE_OUT_OF_ATTENDED_RANGE_MSG_KEY 
		= "achievementDate.outOfRange";

	private static final String START_DATE_AFTER_TODAY_MSG_KEY 
		= "attendedStartDate.after";
	
	private static final String INSTITUTE_OR_DESCRIPTION_REQUIRED_MSG_KEY = "instituteOrDescriptionRequired";
	
	public EducationFormValidator(){
		//Default
	}
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return EducationForm.class.isAssignableFrom(clazz);
	}


	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		EducationForm form = (EducationForm) target;
		
		if ((form.getInstitute() == null || "".equals(form.getInstitute().getName())) && (form.getDescription() == null || "".equals(form.getDescription()))) {
			errors.	rejectValue("institute", INSTITUTE_OR_DESCRIPTION_REQUIRED_MSG_KEY);
		}		
		
		if(form.getGraduated()){
			ValidationUtils.rejectIfEmpty(errors, "achievementDate", 
					ACHIEVEMENT_DATE_REQUIRED_MSG_KEY);
		}
		
		if(form.getAchievementItems() != null){
			//check achievements list
			int index = 0;
			for (EducationalAchievementItem item 
					: form.getAchievementItems()){
				if (ItemOperation.CREATE.equals(item
						.getOperation()) || ItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("achievementItems[" + index 
								+ "].date", ACHIEVEMENT_DATE_REQUIRED_MSG_KEY);
					}
					if (item.getDescription() == null ||
							item.getDescription().length() < 1) {
						errors.rejectValue("achievementItems[" + index 
								+ "].description", 
								ACHIEVEMENT_DESCRIPTION_REQUIRED_MSG_KEY);
					}
				}
				index++;
			}
		}
		if(form.getNoteItems() != null){
			//check notes list
			int index = 0;
			for (EducationNoteItem item 
					: form.getNoteItems()) {
				if (ItemOperation.CREATE.equals(item
						.getOperation()) || ItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("noteItems[" + index 
								+ "].date", NOTE_DATE_REQUIRED_MSG_KEY);
					}
					if (item.getDescription() == null || 
							item.getDescription().length() < 1) {
						errors.rejectValue("noteItems[" + index 
								+ "].description", 
								NOTE_DESCRIPTION_REQUIRED_MSG_KEY);
					}
				}
				index++;
			}
		}
		
		if(form.getVerified()){
			//check verification signature fields
			ValidationUtils.rejectIfEmpty(errors, "verificationMethod", 
					VERIFICATION_METHOD_REQUIRED_MSG_KEY);
			
			//check if verification date after today's date
			if(form.getVerifiedDate() != null){
				if(form.getVerifiedDate().getTime() > new Date().getTime()){
					errors.rejectValue("verifiedDate", 
							VERIFICATION_DATE_AFTER_TODAY_MSG_KEY);
				}
			}
		}

		if(form.getAttendedStartDate() != null){
			if(form.getAttendedStartDate().getTime() > new Date().getTime()){
				//start date is greater than today's date
				errors.rejectValue("attendedStartDate", 
						START_DATE_AFTER_TODAY_MSG_KEY);
			}
		}
		
		
		if (form.getAttendedStartDate() != null 
				&& form.getAttendedEndDate() != null) {
			//check if end date before start date
			if (form.getAttendedStartDate().getTime() 
					> form.getAttendedEndDate().getTime()) {
				errors.rejectValue("attendedEndDate",
						END_DATE_BEFORE_MSG_KEY);
			}
			else if(!(form.getAttendedStartDate().getTime() 
					> form.getAttendedEndDate().getTime()) && 
			form.getAttendedEndDate().getTime() > new Date().getTime()){
					//end date is greater than today's date
				errors.rejectValue("attendedEndDate", 
						END_DATE_AFTER_TODAY_MSG_KEY);
			}
			else{//DateRange valid
				//check if achievements are within attended date range
				if(form.getAchievementItems() != null){
					//check achievements list
					int index = 0;
					for (EducationalAchievementItem item 
							: form.getAchievementItems()){
						if (ItemOperation.CREATE.equals(item
								.getOperation()) || ItemOperation
								.UPDATE.equals(item.getOperation())) {
							if (item.getDate().getTime() 
									< form.getAttendedStartDate().getTime()
									|| item.getDate().getTime() 
									> form.getAttendedEndDate().getTime() ) {
								errors.rejectValue("achievementItems[" + index 
										+ "].date", 
								ACHIEVEMENT_DATE_OUT_OF_ATTENDED_RANGE_MSG_KEY);
							}
							index++;
						}
					}
				}
				if(form.getGraduated()){
					//check graduated achievement date
					if (form.getAchievementDate() != null && (form.getAchievementDate().getTime() 
							< form.getAttendedStartDate().getTime()
							|| form.getAchievementDate().getTime() 
							> form.getAttendedEndDate().getTime()) ) {
						errors.rejectValue("achievementDate", 
						ACHIEVEMENT_DATE_OUT_OF_ATTENDED_RANGE_MSG_KEY);
					}
				}
				
			}
		}
		
		
		if(form.getAttendedStartDate() == null 
				&& form.getAttendedEndDate() != null){
			//check if end date with no start date
			errors.rejectValue("attendedStartDate",START_DATE_REQUIRED_MSG_KEY);
		}
	}
	
	
	
	
	
}
