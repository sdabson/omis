package omis.sentence.validator;

import omis.sentence.web.form.SentenceForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for sentence form.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 29, 2013)
 * @since OMIS 3.0
 */
public class SentenceFormValidator
		implements Validator {

	/** Instantiates a default validator for sentence form. */
	public SentenceFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SentenceForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SentenceForm sentenceForm = (SentenceForm) target;
		if (sentenceForm.getCategory() == null) {
			errors.rejectValue("category", "sentence.category.empty");
		}
		if (sentenceForm.getLengthClassification() == null) {
			errors.rejectValue("lengthClassification", 
					"sentence.lengthClassification.empty");
		}
	}
}