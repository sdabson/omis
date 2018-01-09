package omis.courtdocument.validator;


import java.util.List;

import omis.courtdocument.web.form.CourtCaseDocumentAssociationForm;
import omis.document.validator.DocumentTagItemValidator;
import omis.document.web.form.DocumentTagItem;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** Validator for court document association form.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 23, 2015)
 * @since OMIS 3.0 */
public class CourtCaseDocumentAssociationFormValidator 
	implements Validator {
	
	/* Field names */
	private static final String COURT_CASE_FIELD_NAME = "courtCase";
	private static final String DATE_FIELD_NAME = "date";
	private static final String TITLE_FIELD_NAME = "title";
	
	/* Message keys */
	private static final String COURT_CASE_REQUIRED_MSG = "courtCase.empty";
	private static final String DATE_REQUIRED_MSG = "date.empty";
	private static final String TITLE_REQUIRED_MSG = 
			"document.title.empty";
	
	private final DocumentTagItemValidator documentTagItemValidator =
			new DocumentTagItemValidator();
			
	
	/** Constructor. */
	public CourtCaseDocumentAssociationFormValidator() { }
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return clazz.isAssignableFrom(CourtCaseDocumentAssociationForm.class);
		
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object obj, final Errors errors) {
		final CourtCaseDocumentAssociationForm 
			courtCaseDocumentAssociationForm = 
			(CourtCaseDocumentAssociationForm) obj;
		ValidationUtils.rejectIfEmpty(errors, COURT_CASE_FIELD_NAME,
				COURT_CASE_REQUIRED_MSG);
		ValidationUtils.rejectIfEmpty(errors, DATE_FIELD_NAME, 
				DATE_REQUIRED_MSG);
		ValidationUtils.rejectIfEmpty(errors, TITLE_FIELD_NAME, 
				TITLE_REQUIRED_MSG);
		List<DocumentTagItem> documentTagItems = 
				courtCaseDocumentAssociationForm.getDocumentTagItems();
		if (documentTagItems != null) {
			this.documentTagItemValidator.validate(documentTagItems, errors);
		}
		
		
	}
}