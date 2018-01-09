package omis.health.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.health.web.form.ResolveLabWorkForm;
import omis.health.web.form.ResolveLabWorkItem;

/**
 * Resolved lab work form validator.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 27, 2016)
 * @since OMIS 3.0
 */

public class ResolveLabWorkFormValidator implements Validator {

	/** Instantiates a validator for resolved lab work forms. */
	public ResolveLabWorkFormValidator() {
		// Default constructor
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ResolveLabWorkForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ResolveLabWorkForm form = (ResolveLabWorkForm) target;
		
		if (form.getLabWorkItems() != null) {
			int count = 0;
			for (ResolveLabWorkItem item : form.getLabWorkItems()) {
				if (item.getResultsDate() != null 
						&& item.getLabWork().getOffenderAppointmentAssociation()
						.getAppointment().getDate() != null
						&& item.getLabWork().getOffenderAppointmentAssociation()
						.getAppointment().getDate().getTime()
						> item.getResultsDate().getTime()) {
					errors.rejectValue("labWorkItems[" + count +"].resultsDate", 
							"labWork.resultsDateGreaterThanSampleDate");
				}
				count ++;
			}			
		}
	}

}