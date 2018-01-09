package omis.visitation.web.validator.delegate;

import org.springframework.validation.Errors;

import omis.visitation.web.form.VisitationAssociationFields;

/**
 * Delegate to handle validation of visitation association fields.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Mar 3, 2016)
 * @since OMIS 3.0
 */
public class VisitationAssociationFieldsValidatorDelegate {

	/* Constructor. */
	
	/**
	 * Instantiates a default instance of visitation association 
	 * fields validator.
	 */
	public VisitationAssociationFieldsValidatorDelegate() {
		//Default constructor.
	}
	
	public Errors validateVisiationAssociationFields(
			final VisitationAssociationFields visitationAssociationFields,
			final String visitationAssociationFieldsVariableName,
			final Errors errors) {
		
		if (visitationAssociationFields.getCategory() == null) {
			errors.rejectValue(visitationAssociationFieldsVariableName
					+ ".category", 
					"visitationAssociationFields.category.empty");
		}
		return errors;
	}
}