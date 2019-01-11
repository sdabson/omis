package omis.residence.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.datatype.DateRange;
import omis.residence.web.form.ExistingResidenceOperation;
import omis.residence.web.form.ResidenceForm;
import omis.residence.web.form.ResidenceStatusOption;
import omis.web.validator.StringLengthChecks;

/**
 * Residence form validator.
 * 
 * @author Sheronda Vaughn
 * @author Joel Norris
 * @version 0.1.1 (January 19, 2018)
 * @since OMIS 3.0
 */
public class ResidenceFormValidator implements Validator {
	
	/* Resources */
	
	private final StringLengthChecks stringLengthChecks;
	
	/** Instantiates a default residence form validator. */
	public ResidenceFormValidator(final StringLengthChecks stringLengthChecks) {
		this.stringLengthChecks = stringLengthChecks;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return ResidenceForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		ResidenceForm residenceForm = (ResidenceForm) target;
		if (residenceForm.getStartDate() != null
				&& residenceForm.getEndDate() != null
				&& residenceForm.getStartDate().getTime()
				> residenceForm.getEndDate().getTime()) {
			errors.rejectValue("startDate", 
					"dateRange.startDateGreaterThanEndDate");
		}
		
		if (ResidenceStatusOption.PRIMARY_RESIDENCE
				.equals(residenceForm.getStatusOption())
				|| ResidenceStatusOption.SECONDARY_RESIDENCE
				.equals(residenceForm.getStatusOption())
				|| ResidenceStatusOption.FOSTER_CARE
				.equals(residenceForm.getStatusOption())) {	
			if (residenceForm.getValue() == null 
					|| "".equals(residenceForm.getValue())) {
				errors.rejectValue("value", 
						"residenceTerm.address.value.empty");
			} 
			if (residenceForm.getCreateNewZipCode() != null
					&& residenceForm.getCreateNewZipCode()) {			
				if (residenceForm.getZipCodeValue() == null 
						|| residenceForm.getZipCodeValue().length() < 1) {
					errors.rejectValue("zipCodeValue", "residenceTerm.address.zipCode.value.empty");
				} else {
					if (residenceForm.getZipCodeValue() != null) {
						if (residenceForm.getZipCodeValue().length() > 12) {
						errors.rejectValue("zipCodeValue", "residenceForm.zipCodeValue.valid");
							} 
						if (residenceForm.getZipCodeExtension() != null 
								&& residenceForm.getZipCodeExtension().length() > 8) {
							errors.rejectValue("zipCodeExtension", "residenceForm.zipCodeExtension.valid");
						}
					}
				}
			} else {
				if (residenceForm.getZipCode() == null) {
					errors.rejectValue("zipCode", "residenceTerm.address.zipCode.empty");
				} 
			}
					
			if (residenceForm.getCreateNewCity()) {
				if (residenceForm.getCityName().isEmpty()) {
					errors.rejectValue("cityName", 
							"residenceTerm.address.city.name.empty");
				}
			} else {
				if (residenceForm.getCity() == null) {
					errors.rejectValue("city", 
							"r"
							+ "esidenceTerm.address.city.empty");
				}
			}
			if (residenceForm.getState() == null) {
				errors.rejectValue("state", 
						"residenceTerm.address.state.empty");
			}
		} else if (ResidenceStatusOption.GROUP_HOME
			.equals(residenceForm.getStatusOption()) 
			|| ResidenceStatusOption.HOTEL
			.equals(residenceForm.getStatusOption())) {
	
			if (residenceForm.getCreateNewCity()) {
				if (residenceForm.getCityName().isEmpty()) {
					errors.rejectValue("cityName", 
							"residenceTerm.address.city.name.empty");
				}
			} else {
				if (residenceForm.getCity() == null) {
					errors.rejectValue("city", 
							"residenceTerm.address.city.empty");
				}
			}
			if (residenceForm.getState() == null) {
				errors.rejectValue("state", 
						"residenceTerm.address.state.empty");
			}
			if (!residenceForm.getCreateNewLocation()) {
				if (residenceForm.getLocation() == null) {
					errors.rejectValue("location", 
							"nonResidenceTerm.location.empty");
				}
			}
		} else if (ResidenceStatusOption.HOMELESS
			.equals(residenceForm.getStatusOption())) {
			if(residenceForm.getCreateNewCity()){
				if (residenceForm.getCityName().isEmpty()) {
						errors.rejectValue("cityName", 
								"residenceTerm.address.city.name.empty");
				}
			} else {
				if (residenceForm.getCityName() == null) {
				errors.rejectValue("city", 
						"residenceTerm.address.city.name.empty");
				}
			}
			if (residenceForm.getState() == null) {
				errors.rejectValue("state", 
						"residenceTerm.address.state.empty");
			}
		} else {
			if (residenceForm.getStatusOption() == null) {
				errors.rejectValue("statusOption", 
						"residence.statusOption.empty");
			}
		}			
		if (residenceForm.getExistingPrimaryResidence() != null) {
			if (residenceForm.getExistingResidenceOperation() == null) {
				errors.rejectValue("existingResidenceOperation", 
						"residenceTerm.primaryExists.empty");
			} else {
				if (ExistingResidenceOperation.HISTORICAL.equals(
						residenceForm.getExistingResidenceOperation())) {
					if (residenceForm.getExistingHistoricalEndDate() == null) {
						errors.rejectValue("existingHistoricalEndDate", 
								"residenceTerm.primaryExistsDate.empty");
					} else if (residenceForm.getExistingHistoricalEndDate()
							.getTime() < DateRange.getStartDate(
									residenceForm.getExistingPrimaryResidence()
									.getDateRange()).getTime()) {
						errors.rejectValue("existingHistoricalEndDate",
							"residenceTerm.primaryExistsDate.priorToStartDate");
					}
				}
			}
		}
		if (residenceForm.getCreateNewLocation()) {
			if (residenceForm.getValue() == null || residenceForm.getValue().isEmpty()) {
				errors.rejectValue("value", "residenceTerm.address.value.empty");
			}
			if (residenceForm.getCreateNewZipCode() != null
					&& residenceForm.getCreateNewZipCode()) {
				if (residenceForm.getZipCodeValue() == null 
						|| residenceForm.getZipCodeValue().length() < 1) {
					errors.rejectValue("zipCodeValue", "residenceTerm.address.zipCode.value.empty");
				}  else {
				if (residenceForm.getZipCodeValue() != null) {
					if (residenceForm.getZipCodeValue().length() > 12) {
					errors.rejectValue("zipCodeValue", "residenceForm.zipCodeValue.valid");
						} 
					if (residenceForm.getZipCodeExtension() != null 
							&& residenceForm.getZipCodeExtension().length() > 8) {
						errors.rejectValue("zipCodeExtension", "residenceForm.zipCodeExtension.valid");
					}
				}
			}	
			} else {
				if (residenceForm.getZipCode() == null) {
					errors.rejectValue("zipCode", "residenceTerm.address.zipCode.empty");
				}
			}
		}
		if (residenceForm.getResidenceComment() != null) {
			this.stringLengthChecks.getVeryHugeCheck().check(
					"residenceComment", residenceForm.getResidenceComment(), errors);
		}
		this.stringLengthChecks.getLargeCheck().check(
				"value", residenceForm.getValue(), errors);	
		this.stringLengthChecks.getSmallCheck().check("cityName", residenceForm.getCityName(), errors);
	}
}