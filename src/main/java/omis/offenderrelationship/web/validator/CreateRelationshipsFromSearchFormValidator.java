package omis.offenderrelationship.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.family.web.validator.delegate.FamilyAssociationFieldsCreateValidatorDelegate;
import omis.offenderrelationship.web.form.CreateRelationshipsForm;
import omis.visitation.web.validator.delegate.VisitationAssociationFieldsValidatorDelegate;

/**
 * Create relationships form validator.
 * 
 * @author Yidong Li
 * @version 0.1.0 (May 24, 2016)
 * @since OMIS 3.0
 */
public class CreateRelationshipsFromSearchFormValidator implements Validator {
	/* Validator Delegates. */
	private FamilyAssociationFieldsCreateValidatorDelegate 
		familyAssociationFieldsCreateValidatorDelegate;
	
	private VisitationAssociationFieldsValidatorDelegate
	visitationAssociationFieldsValidatorDelegate;
	
	/* Constructors. */
	
	/**
	 * Instantiates an instance of create relationships form validator with the
	 * specified delegates.
	 * 
	 * @param personFieldsValidatorDelegate person fields validator delegate
	 */
	public CreateRelationshipsFromSearchFormValidator(
			final FamilyAssociationFieldsCreateValidatorDelegate
				familyAssociationFieldsCreateValidatorDelegate, 
			final VisitationAssociationFieldsValidatorDelegate
				visitationAssociationFieldsValidatorDelegate) {
		this.familyAssociationFieldsCreateValidatorDelegate 
			= familyAssociationFieldsCreateValidatorDelegate;
		this.visitationAssociationFieldsValidatorDelegate 
			= visitationAssociationFieldsValidatorDelegate; 
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CreateRelationshipsForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CreateRelationshipsForm form = (CreateRelationshipsForm) target;
		// At least one relationship is required
		if(form.getCreateFamilyMember()!=true
			&&form.getCreateVictim()!=true
			&&form.getCreateVisitor()!=true){
			errors.rejectValue("createFamilyMember", "familyVictimVisitorSelection.empty");
			errors.rejectValue("createVictim", "familyVictimVisitorSelection.empty");
			errors.rejectValue("createVisitor", "familyVictimVisitorSelection.empty");
		}
		
		if(form.getCreateVisitor()!=null&&form.getCreateVisitor()==true){
			this.visitationAssociationFieldsValidatorDelegate
				.validateVisiationAssociationFields(
						form.getVisitationAssociationFields(), 
						"visitationAssociationFields", errors);
		}
		// Family association fields
		if (form.getCreateFamilyMember()!=null&&form.getCreateFamilyMember()==true) {
			this.familyAssociationFieldsCreateValidatorDelegate
				.validateFamilyAssociationFields(
						form.getFamilyAssociationFields(), 
						"familyAssociationFields", errors);
		}
	}		
}