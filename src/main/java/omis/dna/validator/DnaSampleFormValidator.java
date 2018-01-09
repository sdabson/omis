package omis.dna.validator;

import omis.dna.web.form.DnaSampleForm;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * DNA sample form validator.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 19, 2013)
 * @since OMIS 3.0
 */
public class DnaSampleFormValidator implements Validator {

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return DnaSampleForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		DnaSampleForm form = (DnaSampleForm) target;
		
		if (form.getCollectionEmployee() == null) {
			errors.rejectValue("collectionEmployee", 
					"dnaSample.collectionEmployee.empty");
		} else {
			if (form.getWitness() != null 
					&& form.getWitness().equals(form.getCollectionEmployee())) {
				errors.rejectValue("witness", 
						"dnaSample.witness.matchesEmployee");
			}
		}
		if (form.getDate() == null) {
			errors.rejectValue("date", "dnaSample.date.empty");
		} 
		if (form.getLocation() == null || "".equals(form.getLocation())) {
			errors.rejectValue("location", "dnaSample.location.empty");
		}
	}
}