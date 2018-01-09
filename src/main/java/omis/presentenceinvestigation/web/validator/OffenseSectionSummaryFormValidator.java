package omis.presentenceinvestigation.web.validator;

import java.util.HashSet;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.document.validator.DocumentTagItemValidator;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.OffenseSectionSummaryAssociableDocumentItem;
import omis.presentenceinvestigation.web.form.OffenseSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.web.validator.StringLengthChecks;

/**
 * OffenseSectionSummaryFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 3, 2017)
 *@since OMIS 3.0
 *
 */
public class OffenseSectionSummaryFormValidator implements Validator {
	
	private final StringLengthChecks stringLengthChecks;

	private static final String TEXT_REQUIRED_MSG =
			"offenseSectionSummary.text.empty";
	
	private static final String CHARGE_REASON_REQUIRED_MSG =
			"offenseSectionSummary.chargeReason.empty";
	
	private static final String INVOLVEMENT_REASON_REQUIRED_MSG =
			"offenseSectionSummary.involvementReason.empty";
	
	private static final String COURT_RECOMMENDATION_REQUIRED_MSG =
			"offenseSectionSummary.courtRecommendation.empty";
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String NAME_FIELD_NAME = "name";
	
	private static final String DOCUMENT_TAG_ITEM_FIELD_NAME = 
			"offenseSectionSummaryAssociableDocumentItems[%d]"
											+ ".documentTagItems[%d]";
	
	private static final String NAME_REQUIRED_MSG = "tag.name.empty";
	
	private static final String NAME_DUPLICATED_MSG = "tag.name.duplicate";
	
	
	private final DocumentTagItemValidator documentTagItemValidator =
			new DocumentTagItemValidator();
	
	public OffenseSectionSummaryFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return OffenseSectionSummaryForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		OffenseSectionSummaryForm form = (OffenseSectionSummaryForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "text", TEXT_REQUIRED_MSG);
		this.stringLengthChecks.getDocumentCheck().check("text", 
				form.getText(), errors);
		ValidationUtils.rejectIfEmpty(errors, "chargeReason",
				CHARGE_REASON_REQUIRED_MSG);
		this.stringLengthChecks.getDocumentCheck().check("chargeReason", 
				form.getChargeReason(), errors);
		ValidationUtils.rejectIfEmpty(errors, "involvementReason",
				INVOLVEMENT_REASON_REQUIRED_MSG);
		this.stringLengthChecks.getDocumentCheck().check("involvementReason", 
				form.getInvolvementReason(), errors);
		ValidationUtils.rejectIfEmpty(errors, "courtRecommendation",
				COURT_RECOMMENDATION_REQUIRED_MSG);
		this.stringLengthChecks.getDocumentCheck().check(
				"courtRecommendation", form.getCourtRecommendation(), errors);
		
		if((form.getFileExtension() != null 
				&& form.getFileExtension().trim().length() > 0)
				|| form.getDate() != null
				|| form.getDocumentTagItems() != null){
			ValidationUtils.rejectIfEmpty(errors, "title",
					TITLE_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors, "date",
					FILE_DATE_REQUIRED_MSG_KEY);
				ValidationUtils.rejectIfEmpty(errors, "data",
					DOCUMENT_REQUIRED_MSG_KEY);
			List<omis.document.web.form.DocumentTagItem> documentTagItems =
					form.getDocumentTagItems();
			if (documentTagItems != null) {
				this.documentTagItemValidator.validate(documentTagItems,
						errors);
			}
		}
		
		if(form.getOffenseSectionSummaryAssociableDocumentItems() != null){
			int i = 0;
			for(OffenseSectionSummaryAssociableDocumentItem item :
					form.getOffenseSectionSummaryAssociableDocumentItems()){
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation()) ||
					PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())){
					
					ValidationUtils.rejectIfEmpty(errors,
						"offenseSectionSummaryAssociableDocumentItems["+i+"]"
								+ ".title",
							TITLE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
						"offenseSectionSummaryAssociableDocumentItems["+i+"]"
								+ ".fileDate",
							FILE_DATE_REQUIRED_MSG_KEY);
					ValidationUtils.rejectIfEmpty(errors,
							"offenseSectionSummaryAssociableDocumentItems["+i+"]"
									+ ".data", 
							DOCUMENT_REQUIRED_MSG_KEY);
					
					if(item.getDocumentTagItems() != null){
						List<DocumentTagItem> documentTags =
								item.getDocumentTagItems();
						HashSet<String> documentTagNames = 
								new HashSet<String>(documentTags.size());
						for (int x = 0; x < documentTags.size(); x++) {
							DocumentTagItem documentTagItem = documentTags.get(x);
							PresentenceInvestigationItemOperation
								documentTagOperation = documentTagItem
									.getItemOperation();
							if (documentTagOperation ==
									PresentenceInvestigationItemOperation.CREATE 
									|| documentTagOperation ==
									PresentenceInvestigationItemOperation.UPDATE){
								errors.pushNestedPath(String.format(
										DOCUMENT_TAG_ITEM_FIELD_NAME, i, x));
								if (documentTagNames.add(documentTagItem
										.getName())) {
									ValidationUtils.rejectIfEmpty(
											errors, NAME_FIELD_NAME, 
											NAME_REQUIRED_MSG);
								}
								else {
									errors.rejectValue(NAME_FIELD_NAME, 
											NAME_DUPLICATED_MSG);
								}
								errors.popNestedPath();
							}
						}
					}
				}
				i++;
			}
		}
	}
}
