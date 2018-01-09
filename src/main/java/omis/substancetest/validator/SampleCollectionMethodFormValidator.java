package omis.substancetest.validator;

import omis.substancetest.web.form.SampleCollectionMethodForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Sample Collection Method Form Validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 12, 2013)
 * @since OMIS 3.0
 */
public class SampleCollectionMethodFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SampleCollectionMethodForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SampleCollectionMethodForm form = (SampleCollectionMethodForm) target;
		
		if (form.getName().length() < 1 || form.getName() == null) {
			errors.rejectValue("name", "name.empty");
		}
		if (form.getTestKitParameters().isEmpty()) {
			errors.rejectValue("testKitParameters", "testKitParameters.empty");
		}
	}
}