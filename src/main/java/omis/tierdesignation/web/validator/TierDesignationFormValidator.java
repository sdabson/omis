package omis.tierdesignation.web.validator;

import omis.tierdesignation.web.form.TierDesignationForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for tier designations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 13, 2013)
 * @since OMIS 3.0
 */
public class TierDesignationFormValidator
		implements Validator {

	/** Instantiates a validator for form for tier designation. */
	public TierDesignationFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return TierDesignationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		TierDesignationForm tierDesignationForm =
				(TierDesignationForm) target;
		if (tierDesignationForm.getStartDate() != null
				&& tierDesignationForm.getEndDate() != null
				&& tierDesignationForm.getStartDate().getTime()
				> tierDesignationForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (tierDesignationForm.getChangeReason() == null) {
			errors.rejectValue("changeReason",
					"tierDesignation.changeReason.empty");
		}
		if (tierDesignationForm.getLevel() == null) {
			errors.rejectValue("level", "tierDesignation.level.empty");
		}
		if (tierDesignationForm.getSource() == null) {
			errors.rejectValue("source", "tierDesignation.source.empty");
		}
	}
}