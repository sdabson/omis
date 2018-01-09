package omis.sentence.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.sentence.domain.TermRequirement;
import omis.sentence.web.form.SentenceFields;

/**
 * Delegate to validator fields for sentence.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SentenceFieldsValidatorDelegate {
	
	private static final String FIELD_PROPERTY_FORMAT = "%s.%s";

	/** Instantiates validator for fields for sentence. */
	public SentenceFieldsValidatorDelegate() {
		// Default instantiation
	}
	
	/**
	 * Validates sentence fields.
	 * 
	 * <p>Returns count of rejected values.
	 * 
	 * @param fields fields
	 * @param sentenceFieldsPropertyName fields property name
	 * @param errors errors
	 * @return count of rejected value
	 */
	public int validate(final SentenceFields fields,
			final String sentenceFieldsPropertyName,
			final Errors errors) {
		int count = 0;
		if (fields.getLengthClassification() == null) {
			errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
					sentenceFieldsPropertyName, "lengthClassification"),
					"sentence.lengthClassification.empty");
			count++;
		}
		if (fields.getCategory() == null) {
			errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
					sentenceFieldsPropertyName, "category"),
					"sentence.category.empty");
			count++;
		}
		if (fields.getLegalDispositionCategory() == null) {
			errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
					sentenceFieldsPropertyName, "legalDispositionCategory"),
					"sentence.legalDispositionCategory.empty");
			count++;
		}
		if (fields.getCategory() != null && 
				(TermRequirement.REQUIRED.equals(fields.getCategory()
						.getPrisonRequirement()) ||
				 TermRequirement.OPTIONAL.equals(fields.getCategory()
						 .getPrisonRequirement()))) {
			boolean valid = false;
			if (fields.getPrisonYears() != null) {
				if (fields.getPrisonYears() < 0) {
					errors.rejectValue(
							String.format(FIELD_PROPERTY_FORMAT,
									sentenceFieldsPropertyName, "prisonYears"),
							"sentence.prisonYears.negative");
						count++;
				} else if (fields.getPrisonYears() > 0){
					valid = true;
				}
			}
			if (fields.getPrisonMonths() != null) {
				if (fields.getPrisonMonths() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "prisonMonths"),
							"sentence.prisonMonths.negative");
					count++;
				} else if (fields.getPrisonMonths() > 0){
					valid = true;
				}
			}
			if (fields.getPrisonDays() != null) {
				if (fields.getPrisonDays() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "prisonDays"),
							"sentence.prisonDays.negative");
					count++;
				} else if (fields.getPrisonDays() > 0) {
					valid = true;
				}
			}
			if (!valid && TermRequirement.REQUIRED.equals(fields.getCategory()
					.getPrisonRequirement())) {
				errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT, 
						sentenceFieldsPropertyName, "prisonDays"), 
						"sentence.prisonTerm.empty");
				count++;
			}
		}
		if (fields.getCategory() != null 
				&& (TermRequirement.REQUIRED.equals(fields.getCategory()
						.getProbationRequirement()) || 
					TermRequirement.OPTIONAL.equals(fields.getCategory()
							.getProbationRequirement()))) {
			boolean valid = false;
			if (fields.getProbationYears() != null) {
				if (fields.getProbationYears() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "probationYears"),
							"sentence.probationYears.negative");
					count++;	
				} else if (fields.getProbationYears() > 0) {
					valid = true;
				}
			}
			if (fields.getProbationMonths() != null) {
				if (fields.getProbationMonths() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "probationMonths"),
							"sentence.probationMonths.negative");
					count++;
				} else if (fields.getProbationMonths() > 0) {
					valid = true;
				}
			}
			if (fields.getProbationDays() != null) {
				if (fields.getProbationDays() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "probationDays"),
							"sentence.probationDays.negative");
					count++;	
				} else if (fields.getProbationDays() > 0) {
					valid = true;
				}
			}
			if (!valid && TermRequirement.REQUIRED.equals(fields.getCategory()
					.getProbationRequirement())) {
				errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT, 
						sentenceFieldsPropertyName, "probationDays"), 
						"sentence.probationTerm.empty");
				count++;
			}
		}
		if (fields.getCategory() != null
				&& (TermRequirement.REQUIRED.equals(fields.getCategory()
						.getDeferredRequirement()) || 
					TermRequirement.OPTIONAL.equals(fields.getCategory()
								.getDeferredRequirement()))) {
			boolean valid = false;
			if (fields.getDeferredYears() != null) {
				if (fields.getDeferredYears() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "deferredYears"),
							"sentence.deferredYears.negative");
					count++;
				} else if (fields.getDeferredYears() > 0) {
					valid = true;
				}
			}
			if (fields.getDeferredMonths() != null) {
				if (fields.getDeferredMonths() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "deferredMonths"),
							"sentence.deferredMonths.negative");
					count++;
				} else if (fields.getDeferredMonths() > 0){
					valid = true;
				}
			}
			if (fields.getDeferredDays() != null) {
				if (fields.getDeferredDays() < 0) {
					errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT,
							sentenceFieldsPropertyName, "deferredDays"),
							"sentence.deferredDays.negative");
					count++;
				} else if (fields.getDeferredDays() > 0){
					valid = true;
				}
			}
			if (!valid && TermRequirement.REQUIRED.equals(fields.getCategory()
					.getDeferredRequirement())) {
				errors.rejectValue(String.format(FIELD_PROPERTY_FORMAT, 
						sentenceFieldsPropertyName, "deferredDays"), 
						"sentence.deferredTerm.empty");
				count++;
			}
		}
		return count;
	}
}