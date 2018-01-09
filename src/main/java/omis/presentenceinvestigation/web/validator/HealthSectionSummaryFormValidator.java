package omis.presentenceinvestigation.web.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.HealthSectionSummaryDocumentAssociationItem;
import omis.presentenceinvestigation.web.form.HealthSectionSummaryForm;
import omis.presentenceinvestigation.web.form.HealthSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;

/**
 * Health section summary form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 10, 2017)
 * @since OMIS 3.0
 */
public class HealthSectionSummaryFormValidator implements Validator {
	
	
	
	private static final String DESCRIPTION_REQUIRED_MSG_KEY =
			"description.required";
	
	private static final String DATE_REQUIRED_MSG_KEY = "date.required";
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String TAG_REQUIRED_MSG_KEY = "tag.required";
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return HealthSectionSummaryForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		
		HealthSectionSummaryForm form = (HealthSectionSummaryForm) target;
		
		if (form.getHealthSectionSummaryNoteItems() != null) {
			int i = 0;
			for (HealthSectionSummaryNoteItem item 
					: form.getHealthSectionSummaryNoteItems()) {
				if(PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())
				|| PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors,
						"healthSectionSummaryNoteItems["+i+"].description",
						DESCRIPTION_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"healthSectionSummaryNoteItems["+i+"].date",
						DATE_REQUIRED_MSG_KEY);
				}
				
				i++;
			}
		}
		
		if (form.getHealthSectionSummaryDocumentAssociationItems() != null) {
			int i = 0;
			for (HealthSectionSummaryDocumentAssociationItem item 
					: form.getHealthSectionSummaryDocumentAssociationItems()) {
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())){
					
					ValidationUtils.rejectIfEmpty(errors,
						"healthSectionSummaryDocumentAssociationItems"
						+ "["+i+"].title",
							TITLE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"healthSectionSummaryDocumentAssociationItems"
						+ "["+i+"].fileDate",
							FILE_DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"healthSectionSummaryDocumentAssociationItems"
							+ "["+i+"].data", 
							DOCUMENT_REQUIRED_MSG_KEY);
					
					if(item.getDocumentTagItems() != null){
						int x = 0;
						List<DocumentTagItem> documentTags =
								item.getDocumentTagItems();
						for (DocumentTagItem documentTagItem : documentTags) {							
							if (PresentenceInvestigationItemOperation.CREATE
									.equals(documentTagItem
											.getItemOperation())
									|| PresentenceInvestigationItemOperation
									.UPDATE.equals(documentTagItem
									.getItemOperation())){
					ValidationUtils.rejectIfEmpty(errors,
							"healthSectionSummaryDocumentAssociationItems["+i+"]"
									+ ".documentTagItems["+x+"].name",
								TAG_REQUIRED_MSG_KEY);
							}
							x++;
						}
					}
				}
				i++;
			}
		}
	}	
}