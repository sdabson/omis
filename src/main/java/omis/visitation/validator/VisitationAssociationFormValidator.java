package omis.visitation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.person.web.validator.delegate.PersonFieldsValidatorDelegate;
import omis.visitation.web.form.VisitationAssociationForm;
import omis.web.validator.StringLengthChecks;

/**
 * Visitation association form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 12, 2013)
 * @since OMIS 3.0
 */
public class VisitationAssociationFormValidator implements Validator {

	/* Form variable names */
	
	private final static String PERSON_FIELDS_VARIABLE_NAME = "personFields";
	
	/* Helpers. */
	
	private PersonFieldsValidatorDelegate personFieldsValidatorDelegate;
	
	private final StringLengthChecks stringLengthChecks;

	/* Constructor. */
	
	/**
	 * Instantiates an instance of visitation association form validator with
	 * the specified validator delegate.
	 * 
	 * @param personFieldsValidatorDelegate person fields validator delegate
	 * @param stringLengthChecks string length checks
	 */
	public VisitationAssociationFormValidator(
			final PersonFieldsValidatorDelegate personFieldsValidatorDelegate,
			final StringLengthChecks stringLengthChecks) {
		this.personFieldsValidatorDelegate = personFieldsValidatorDelegate;
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VisitationAssociationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VisitationAssociationForm form = (VisitationAssociationForm) target;
	
		if (form.getNewVisitor() != null && form.getNewVisitor()) {
			this.personFieldsValidatorDelegate.validatePersonFields(
					form.getPersonFields(), PERSON_FIELDS_VARIABLE_NAME,
					errors);
		} else {
			if (form.getPerson() == null) {
				errors.rejectValue("person", "person.empty");
			}
		}
		if (form.getAssociatedOffender() == null) {
			errors.rejectValue("offender", "offender.empty");
		}
		if (form.getAssociatedOffender() == form.getPerson()) {
			errors.rejectValue("visitor", "visitor.equalsOffender");
		}
		if (form.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (form.getApproved() && form.getDecisionDate() == null) {
			errors.rejectValue("decisionDate", "decisionDate.empty");
		}
		if (form.getApproved() && form.getDecisionDate() != null 
				&& form.getStartDate() != null) {
			int dateCompare = form.getDecisionDate().compareTo(form
					.getStartDate());
			if (dateCompare < 0) {
				errors.rejectValue("decisionDate", "decisionDate.tooLow");
			}
		}
		if (form.getCategory() == null) {
			errors.rejectValue("category", "category.empty");
		}
		if (form.getStartDate() != null 
				&& form.getEndDate() != null) {
			if (form.getStartDate().getTime() 
					> form.getEndDate().getTime()) {
				errors.rejectValue("startDate", 
						"visitor.startDate.afterEndDate");
			}
		}
		if (form.getApproved() && form.getSpecialVisit()) {
			errors.rejectValue("approved",
					"visitor.approved.approvedAndSpecialVisit");
		}
		if (form.getNotes() != null && !form.getNotes().isEmpty()) {
			this.stringLengthChecks.getVeryHugeCheck().check(
					"notes", form.getNotes(), errors);
		}
		if (form.getGuardianship() != null && !form.getGuardianship().isEmpty()) {
			this.stringLengthChecks.getHugeCheck().check("guardianship", form.getGuardianship(), errors);
		}
	}
}
