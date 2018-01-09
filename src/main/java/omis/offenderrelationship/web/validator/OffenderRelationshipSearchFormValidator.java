package omis.offenderrelationship.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.offenderrelationship.web.form.OffenderRelationshipSearchForm;
import omis.offenderrelationship.web.form.OffenderRelationshipSearchType;

public class OffenderRelationshipSearchFormValidator implements Validator {

	/* Constructors. */
	public OffenderRelationshipSearchFormValidator() {
		//Default instantiation
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return OffenderRelationshipSearchForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OffenderRelationshipSearchForm offenderRelationshipSearchForm 
			= (OffenderRelationshipSearchForm) target;
		
		if (offenderRelationshipSearchForm.getType() == null) {
			errors.rejectValue("type",
					"offenderRelationshipSearchType.empty");
		} else if (OffenderRelationshipSearchType.NAME.equals(
				offenderRelationshipSearchForm.getType())) {
			if ((offenderRelationshipSearchForm.getLastName() == null
					|| offenderRelationshipSearchForm.getLastName().isEmpty())
				&& (offenderRelationshipSearchForm.getFirstName() == null
					|| offenderRelationshipSearchForm.getFirstName().isEmpty())) 
			{
				errors.rejectValue("lastName",
						"offenderRelationshipSearchName.empty");
			} else if ((offenderRelationshipSearchForm.getLastName() != null 
				&& offenderRelationshipSearchForm.getLastName().length() < 2) 
				&& (offenderRelationshipSearchForm.getFirstName() != null 
				&& offenderRelationshipSearchForm.getFirstName().length() < 2)) 
			{
				errors.rejectValue("lastName", 
						"offenderRelationshipSearchName.notEnoughCharacters");
			}
		} else if (OffenderRelationshipSearchType.OFFENDER_NUMBER.equals(
				offenderRelationshipSearchForm.getType())) {
			if (offenderRelationshipSearchForm.getOffenderNumber() == null) {
				errors.rejectValue("offenderNumber",
						"offenderRelationshipSearchOffenderNumber.empty");
			}
		} else if (OffenderRelationshipSearchType.SOCIAL_SECURITY_NUMBER.equals(
				offenderRelationshipSearchForm.getType())) {
			if (offenderRelationshipSearchForm.getSocialSecurityNumber() == null
				|| offenderRelationshipSearchForm.getSocialSecurityNumber().isEmpty()) {
				errors.rejectValue("socialSecurityNumber",
						"offenderRelationshipSearchSocialSecurityNumber.empty");
			}
		} else if (OffenderRelationshipSearchType.BIRTH_DATE.equals(
				offenderRelationshipSearchForm.getType())) {
			if (offenderRelationshipSearchForm.getBirthDate() == null) {
				errors.rejectValue("birthDate",
						"offenderRelationshipSearchBirthDate.empty");
			}
		} else {
			throw new UnsupportedOperationException(String.format(
					"Unsupported search type: %s",
					offenderRelationshipSearchForm.getType()));
		}
	}

}
