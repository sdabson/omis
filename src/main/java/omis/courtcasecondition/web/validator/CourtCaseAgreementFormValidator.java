package omis.courtcasecondition.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.courtcasecondition.web.form.AgreementAssociableDocumentItem;
import omis.courtcasecondition.web.form.CourtCaseAgreementForm;
import omis.courtcasecondition.web.form.CourtCaseConditionItemOperation;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;

/**
 * Court Case Agreement Form Validator.
 * 
 * @author Annie Wahl
 * @version 0.1.2 (Dec 7, 2017)
 * @since OMIS 3.0
 * 
 */
public class CourtCaseAgreementFormValidator implements Validator {
	
	private static final String START_DATE_REQUIRED_MSG_KEY = 
			"startDate.empty";
	
	private static final String DOCKET_REQUIRED_MSG_KEY = 
			"courtCaseCondition.docket.empty";
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String TAG_REQUIRED_MSG_KEY = "tag.required";
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CourtCaseAgreementForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "startDate",
				START_DATE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "docket",
				DOCKET_REQUIRED_MSG_KEY);
		
		CourtCaseAgreementForm form = (CourtCaseAgreementForm) target;
		
		int i = 0;
		for (AgreementAssociableDocumentItem item
				: form.getAgreementAssociableDocumentItems()) {
			if (CourtCaseConditionItemOperation.CREATE.equals(
					item.getItemOperation()) 
					|| CourtCaseConditionItemOperation.UPDATE.equals(
					 item.getItemOperation())) {
				
				ValidationUtils.rejectIfEmpty(errors,
						"agreementAssociableDocumentItems[" + i + "].title",
						TITLE_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors,
						"agreementAssociableDocumentItems[" + i + "].date",
						FILE_DATE_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors,
						"agreementAssociableDocumentItems[" + i + "].data", 
						DOCUMENT_REQUIRED_MSG_KEY);
				
				if (item.getTagItems() != null) {
					int j = 0;
					for (DocumentTagItem tagItem : item.getTagItems()) {
						if (DocumentTagOperation.CREATE.equals(
								tagItem.getDocumentTagOperation())
								|| DocumentTagOperation.UPDATE.equals(
								tagItem.getDocumentTagOperation())) {
							ValidationUtils.rejectIfEmpty(errors,
									"agreementAssociableDocumentItems["
											+ i + "].tagItems[" + j + "].name",
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
