package omis.prisonterm.web.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.document.validator.DocumentTagItemValidator;
import omis.document.web.form.DocumentTagItem;
import omis.prisonterm.web.form.PrisonTermForm;

/**
 * Validator for prison terms.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.3 (Feb 5, 2019)
 * @since OMIS 3.0
 */

public class PrisonTermFormValidator implements Validator {

	private static final String DATE_REQUIRED_MSG = "date.empty";
	
	private static final String TITLE_REQUIRED_MSG = "document.title.empty";
	
	private static final String DOCUMENT_REQUIRED_MSG = "document.required";

	private static final String INCORRECT_FILE_EXTENSION_MSG = 
			"document.incorrectType";
	
	private final DocumentTagItemValidator documentTagItemValidator =
			new DocumentTagItemValidator();
	
	/**
	 * Instantiates a default prison term form validator.
	 */
	public PrisonTermFormValidator() {
		// Default instantiation
	} 
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return PrisonTermForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		PrisonTermForm form = (PrisonTermForm) target;
		
		if (form.getActionDate() == null) {
			errors.rejectValue("actionDate", "prisonTerm.actionDate.empty");
		}
		if (form.getStatus() == null) {
			errors.rejectValue("status", "prisonTerm.status.empty");
		}
		if (((form.getTitle() != null && !form.getTitle().isEmpty())
				|| form.getDate() != null
				|| form.getData().length > 0)
				&& (form.getRemoveSentenceCalculation() == null
				 || !form.getRemoveSentenceCalculation())) {
			ValidationUtils.rejectIfEmpty(errors, "date",
					DATE_REQUIRED_MSG);
			ValidationUtils.rejectIfEmpty(errors, "title",
					TITLE_REQUIRED_MSG);
			ValidationUtils.rejectIfEmpty(errors, "data",
					DOCUMENT_REQUIRED_MSG);
			if (form.getFileExtension() != null && 
					!form.getFileExtension().toUpperCase().equals("PDF")) {
				errors.rejectValue("data", INCORRECT_FILE_EXTENSION_MSG);
			}
			List<DocumentTagItem> documentTagItems = form.getDocumentTagItems();
			if (documentTagItems != null) {
				this.documentTagItemValidator.validate(
						documentTagItems, errors);
			}
		} 
		if (((form.getTitle() != null && !form.getTitle().isEmpty())
				|| form.getDate() != null
				|| (form.getReplaceData() != null 
					&& form.getReplaceData().length > 0))
				&& (form.getRemoveSentenceCalculation() != null
					&& form.getRemoveSentenceCalculation())) {
			ValidationUtils.rejectIfEmpty(errors, "date",
					DATE_REQUIRED_MSG);
			ValidationUtils.rejectIfEmpty(errors, "title",
					TITLE_REQUIRED_MSG);
			ValidationUtils.rejectIfEmpty(errors, "replaceData",
					DOCUMENT_REQUIRED_MSG);
			if (form.getFileExtension() != null && 
					!form.getFileExtension().toUpperCase().equals("PDF")) {
				errors.rejectValue("replaceData", INCORRECT_FILE_EXTENSION_MSG);
			}
			List<DocumentTagItem> documentTagItems = form.getDocumentTagItems();
			if (documentTagItems != null) {
				this.documentTagItemValidator.validate(
						documentTagItems, errors);
			}
		}
	}
}
