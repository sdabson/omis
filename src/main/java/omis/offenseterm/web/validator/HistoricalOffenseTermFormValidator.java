package omis.offenseterm.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenseterm.web.form.HistoricalOffenseTermForm;
import omis.sentence.web.validator.delegate.SentenceFieldsValidatorDelegate;

/**
 * Validator for form for historical offense terms.
 *
 * <p>Historical offense terms are represented by inactive offense terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HistoricalOffenseTermFormValidator implements Validator {
	
	private final SentenceFieldsValidatorDelegate
	sentenceFieldsValidatorDelegate; 

	/**
	 * Instantiates validator for form for historical offense terms.
	 * 
	 * @param sentenceFieldsValidatorDelegate delegate for sentence fields
	 */
	public HistoricalOffenseTermFormValidator(
			final SentenceFieldsValidatorDelegate
				sentenceFieldsValidatorDelegate) {
		this.sentenceFieldsValidatorDelegate = sentenceFieldsValidatorDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return HistoricalOffenseTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		HistoricalOffenseTermForm form = (HistoricalOffenseTermForm) target;
		this.sentenceFieldsValidatorDelegate.validate(
				form.getSentenceFields(), "sentenceFields", errors);
	}
}