package omis.presentenceinvestigation.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.bedplacement.web.form.BedPlacementForm;

/**
 * Biographic and contact section form validator.
 * 
 * @author Jonny Santy
 * @version 0.1.0 ()
 * @since OMIS 3.0
 */
public class BiographicAndContactSectionFormValidator implements Validator {

	
	/**
	 * Instantiates a default Biographic And Contact Section form validator.
	 * 
	 */
	public BiographicAndContactSectionFormValidator() {
		super();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return BedPlacementForm.class.isAssignableFrom(clazz);
	}


	/** {@inheritDoc} */
	@Override
	public void validate(Object obj, Errors errors) {
		//I left the following comments as a guideline on validation which *may* be required in the future.
//		ValidationUtils.rejectIfEmpty(errors, "name", "Name is required");
//		ValidationUtils.rejectIfEmpty(errors, "dateOfReport", "Date of report required");
	}
}