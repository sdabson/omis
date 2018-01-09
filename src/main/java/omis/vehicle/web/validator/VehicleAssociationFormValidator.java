package omis.vehicle.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.vehicle.web.form.VehicleAssociationForm;
import omis.web.validator.StringLengthChecks;
import omis.web.validator.StringLengthChecks.StringLengthCheck;

/**
 * Validator for vehicle association form.
 * 
 * @author Yidong Li
 * @author Ryan Johns
 * @version 0.1.1 (Jun 1, 2016)
 * @since OMIS 3.0
 */
public class VehicleAssociationFormValidator implements Validator {
	private static final String OWNER_DESCRIPTION_PROPERTY_NAME 
		= "ownerDescription";
	private static final String START_DATE_PROPERTY_NAME = "startDate";
	private static final String STATE_PROPERTY_NAME = "state";
	private static final String VEHICLE_COLOR_PROPERTY_NAME 
		= "vehicleColor";
	private static final String VEHICLE_MAKE_PROPERTY_NAME 
		= "vehicleMake";
	private static final String VEHICLE_MODEL_PROPERTY_NAME = "vehicleModel";
	private static final String OWNER_DESCRIPTION_EMPTY_ERROR_KEY 
		= "vehicleAssociation.ownerDescription.empty";
	private static final String START_DATE_EMPTY_ERROR_KEY
		= "VehicleAssociation.startdate.empty";
	private static final String START_DATE_GREATER_THAN_END_DATE_ERROR_KEY
		= "dateRange.startDateGreaterThanEndDate";
	private static final String STATE_EMPTY_ERROR_KEY 
		= "VehicleAssociation.state.empty";
	private static final String VEHICLE_MAKE_EMPTY_ERROR_KEY
		= "VehicleAssociation.vehicleMake.empty";
	private static final String VEHICLE_MODEL_EMPTY_ERROR_KEY 
		= "VehicleAssociation.vehicleModel.empty";
	private static final String VEHICLE_COLOR_EMPTY_ERROR_KEY
		= "VehicleAssociation.vehicleColor.empty";
	
	private final StringLengthCheck stringLengthCheck;
	
	/** Instantiates a validator for offender vehicle association form. */
	public VehicleAssociationFormValidator(
			final StringLengthChecks stringLengthChecks) {
		this.stringLengthCheck = stringLengthChecks.getSmallCheck();
	}

	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VehicleAssociationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		VehicleAssociationForm vehicleAssociationForm
			= (VehicleAssociationForm) target;

		if (vehicleAssociationForm.getStartDate() == null) {
			errors.rejectValue(START_DATE_PROPERTY_NAME, 
					START_DATE_EMPTY_ERROR_KEY);
		} 
		if (vehicleAssociationForm.getState() == null) {
			errors.rejectValue(STATE_PROPERTY_NAME, STATE_EMPTY_ERROR_KEY);
		} 
		if (vehicleAssociationForm.getStartDate() != null
			&& vehicleAssociationForm.getEndDate() != null
			&& vehicleAssociationForm.getStartDate().getTime()
			> vehicleAssociationForm.getEndDate().getTime()) {
				errors.rejectValue(START_DATE_PROPERTY_NAME, 
						START_DATE_GREATER_THAN_END_DATE_ERROR_KEY);
		}
		if (vehicleAssociationForm.getVehicleModel() == null) {
			errors.rejectValue(VEHICLE_MODEL_PROPERTY_NAME,
					VEHICLE_MODEL_EMPTY_ERROR_KEY);
		}
		if (vehicleAssociationForm.getVehicleColor() == null) {
			errors.rejectValue(VEHICLE_COLOR_PROPERTY_NAME,
					VEHICLE_COLOR_EMPTY_ERROR_KEY);
		}
		if (vehicleAssociationForm.getVehicleMake() == null) {
			errors.rejectValue(VEHICLE_MAKE_PROPERTY_NAME, 
					VEHICLE_MAKE_EMPTY_ERROR_KEY);
		}
		if (vehicleAssociationForm.getOwnerDescription() == null
				|| vehicleAssociationForm.getOwnerDescription().isEmpty()) {
			errors.rejectValue(OWNER_DESCRIPTION_PROPERTY_NAME, 
					OWNER_DESCRIPTION_EMPTY_ERROR_KEY);
		}
		this.stringLengthCheck.check(
				OWNER_DESCRIPTION_PROPERTY_NAME, 
				vehicleAssociationForm.getOwnerDescription(), 
				errors);
	}
}