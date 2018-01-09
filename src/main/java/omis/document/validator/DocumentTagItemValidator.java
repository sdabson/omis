package omis.document.validator;

import java.util.HashSet;
import java.util.List;

import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/** form validator for document tag.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 23, 2015)
 * @since OMIS 3.0 */
public class DocumentTagItemValidator implements Validator {
	/* Field names. */
	private static final String NAME_FIELD_NAME = "name";
	private static final String DOCUMENT_TAG_ITEM_FIELD_NAME = 
			"documentTagItems[%1d]";
	
	/* Error message keys. */
	private static final String NAME_REQUIRED_MSG = "tag.name.empty";
	private static final String NAME_DUPLICATED_MSG = "tag.name.duplicate";
	
	
	
	/** Constructor. */
	public DocumentTagItemValidator() { }
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) { 
		return clazz.isAssignableFrom(DocumentTagItem.class);
	}
	
	/** Validates collection of tags.
	 * @param documentTags - list of tags to validate.
	 * @param errors - errors. */
	public void validate(final List<DocumentTagItem> documentTags, 
			final Errors errors) {
		HashSet<String> documentTagNames = 
				new HashSet<String>(documentTags.size());
		for (int x = 0; x < documentTags.size(); x++) {
			DocumentTagItem documentTagItem = documentTags.get(x);
			DocumentTagOperation documentTagOperation = documentTagItem
					.getDocumentTagOperation();
			if (documentTagOperation == DocumentTagOperation.CREATE 
					|| documentTagOperation 
					==  DocumentTagOperation.UPDATE) {
				errors.pushNestedPath(String.format(
						DOCUMENT_TAG_ITEM_FIELD_NAME, x));
				if (documentTagNames.add(documentTagItem.getName())) {
					this.validate(documentTagItem, errors);
				} else {
					errors.rejectValue(NAME_FIELD_NAME, 
							NAME_DUPLICATED_MSG);
				}
				errors.popNestedPath();
			}
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object obj, final Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, NAME_FIELD_NAME, 
				NAME_REQUIRED_MSG);
	}
}
