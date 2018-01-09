package omis.violationevent.web.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.violationevent.web.form.DisciplinaryCodeViolationItem;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.violationevent.web.form.ViolationEventDocumentItem;
import omis.violationevent.web.form.ViolationEventForm;
import omis.violationevent.web.form.ViolationEventItemOperation;
import omis.violationevent.web.form.ViolationEventNoteItem;

/**
 * ViolationEventFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventFormValidator implements Validator {
	
	private static final String EVENT_DATE_REQUIRED_MSG_KEY =
			"eventDate.required";
	
	private static final String EVENT_DATE_AFTER_MSG_KEY = "eventDate.after";
	
	private static final String EVENT_DETAILS_REQUIRED_MSG_KEY =
			"eventDetails.required";
	
	private static final String DISCIPLINARY_CODE_REQUIRED_MSG_KEY =
			"disciplinaryCode.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String TAG_REQUIRED_MSG_KEY = "tag.required";

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return ViolationEventForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ViolationEventForm form = (ViolationEventForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "eventDate",
				EVENT_DATE_REQUIRED_MSG_KEY);
		if(form.getEventDate() != null &&
				form.getEventDate().getTime() > new Date().getTime()){
			errors.rejectValue("eventDate", EVENT_DATE_AFTER_MSG_KEY);
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventDetails",
				EVENT_DETAILS_REQUIRED_MSG_KEY);
		
		if(form.getDisciplinaryCodeViolationItems() != null){
			int i = 0;
			for(DisciplinaryCodeViolationItem item :
					form.getDisciplinaryCodeViolationItems()){
				if(ViolationEventItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					ViolationEventItemOperation.UPDATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmpty(errors,
						"disciplinaryCodeViolationItems["+i+"].disciplinaryCode",
						DISCIPLINARY_CODE_REQUIRED_MSG_KEY);
				}
				i++;
			}
		}
		
		if(form.getViolationEventNoteItems() != null){
			int i = 0;
			for(ViolationEventNoteItem item :
					form.getViolationEventNoteItems()){
				if(ViolationEventItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					ViolationEventItemOperation.UPDATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmpty(errors,
						"violationEventNoteItems["+i+"].date",
						DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"violationEventNoteItems["+i+"].description",
						DESCRIPTION_REQUIRED_MSG_KEY);
				}
				i++;
			}
		}
		
		if(form.getViolationEventDocumentItems() != null){
			int i = 0;
			for(ViolationEventDocumentItem item :
					form.getViolationEventDocumentItems()){
				if(ViolationEventItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					ViolationEventItemOperation.UPDATE.equals(
						item.getItemOperation())){
					
					ValidationUtils.rejectIfEmpty(errors,
							"violationEventDocumentItems["+i+"].title",
							TITLE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"violationEventDocumentItems["+i+"].date",
							FILE_DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"violationEventDocumentItems["+i+"].data", 
							DOCUMENT_REQUIRED_MSG_KEY);
					
					if(item.getTagItems() != null){
						int j = 0;
						for(DocumentTagItem tagItem : item.getTagItems()){
							if(DocumentTagOperation.CREATE.equals(
									tagItem.getDocumentTagOperation()) ||
								DocumentTagOperation.UPDATE.equals(
									tagItem.getDocumentTagOperation())){
								ValidationUtils.rejectIfEmpty(errors,
										"violationEventDocumentItems["+i+"]"
											+ ".tagItems["+j+"].name",
										TAG_REQUIRED_MSG_KEY);
							}
							j++;
						}
					}
				}
				i++;
			}
		}
	}
}
