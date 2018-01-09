package omis.education.web.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.document.validator.DocumentTagItemValidator;
import omis.document.web.form.DocumentTagItem;
import omis.education.web.form.EducationDocumentForm;

/**
 * EducationDocumentFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 14, 2017)
 *@since OMIS 3.0
 *
 */
public class EducationDocumentFormValidator implements Validator {
	
	private static final String CATEGORY_REQUIRED_MSG = "category.empty";
	
	private static final String DATE_REQUIRED_MSG = "date.empty";
	
	private static final String TITLE_REQUIRED_MSG = "document.title.empty";
	
	private static final String DOCUMENT_REQUIRED_MSG = "document.required";
	
	private final DocumentTagItemValidator documentTagItemValidator =
			new DocumentTagItemValidator();
	
	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return EducationDocumentForm.class.isAssignableFrom(clazz);
	}

	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		EducationDocumentForm form = (EducationDocumentForm) target;
		
		ValidationUtils.rejectIfEmpty(errors, "date", 
				DATE_REQUIRED_MSG);
		ValidationUtils.rejectIfEmpty(errors, "title", 
				TITLE_REQUIRED_MSG);
		ValidationUtils.rejectIfEmpty(errors, "category", 
				CATEGORY_REQUIRED_MSG);
		ValidationUtils.rejectIfEmpty(errors, "data", 
				DOCUMENT_REQUIRED_MSG);
		
		List<DocumentTagItem> documentTagItems = form.getDocumentTagItems();
		if (documentTagItems != null) {
			this.documentTagItemValidator.validate(documentTagItems, errors);
		}
	}

}
