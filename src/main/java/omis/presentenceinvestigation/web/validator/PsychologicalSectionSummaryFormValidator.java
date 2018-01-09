package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.PsychologicalSectionSummaryDocumentItem;
import omis.presentenceinvestigation.web.form.PsychologicalSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PsychologicalSectionSummaryNoteItem;

/**
 * PsychologicalSectionSummaryFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
public class PsychologicalSectionSummaryFormValidator implements Validator {
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String TAG_REQUIRED_MSG_KEY = "tag.required";

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return PsychologicalSectionSummaryForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		PsychologicalSectionSummaryForm form =
				(PsychologicalSectionSummaryForm) target;
		
		if(form.getPsychologicalSectionSummaryNoteItems() != null){
			int i = 0;
			for(PsychologicalSectionSummaryNoteItem item :
				form.getPsychologicalSectionSummaryNoteItems()){
				if(PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())
				|| PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"psychologicalSectionSummaryNoteItems["+i+"].description",
						DESCRIPTION_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"psychologicalSectionSummaryNoteItems["+i+"].date",
						DATE_REQUIRED_MSG_KEY);
				}
				
				i++;
			}
		}
		
		
		if(form.getPsychologicalSectionSummaryDocumentItems() != null){
			int i = 0;
			for(PsychologicalSectionSummaryDocumentItem item :
					form.getPsychologicalSectionSummaryDocumentItems()){
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())){
					
					ValidationUtils.rejectIfEmpty(errors,
						"psychologicalSectionSummaryDocumentItems["+i+"].title",
							TITLE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"psychologicalSectionSummaryDocumentItems["+i+"].fileDate",
							FILE_DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"psychologicalSectionSummaryDocumentItems["+i+"].data", 
							DOCUMENT_REQUIRED_MSG_KEY);
					
					if(item.getDocumentTagItems() != null){
						int j = 0;
						for(DocumentTagItem tagItem : item.getDocumentTagItems()){
							if(PresentenceInvestigationItemOperation.CREATE
									.equals(tagItem.getItemOperation()) ||
								PresentenceInvestigationItemOperation.UPDATE
									.equals(tagItem.getItemOperation())){
								ValidationUtils.rejectIfEmpty(errors,
									"psychologicalSectionSummaryDocumentItems["+i+"]"
											+ ".documentTagItems["+j+"].name",
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
