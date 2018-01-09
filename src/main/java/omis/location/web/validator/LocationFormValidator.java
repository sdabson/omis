package omis.location.web.validator;

import omis.location.web.form.LocationForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for location form.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 29, 2015)
 * @since OMIS 3.0
 */
public class LocationFormValidator
		implements Validator {

	/* Constructors. */
	
	public LocationFormValidator() {
		// Default instantiation
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return LocationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		LocationForm locationForm = (LocationForm) target;
		if (locationForm.getOrganizationName() == null
				|| locationForm.getOrganizationName().isEmpty()) {
			errors.rejectValue("organizationName", "organizationName.empty");
		}
	}
}