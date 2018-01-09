package omis.supervisionfee.web.validator;

import omis.supervisionfee.web.form.FeeRequirementItem;
import omis.supervisionfee.web.form.MonthlySupervisionFeeForm;
import omis.supervisionfee.web.form.SupervisionFeeRequirementItemAuthority;
import omis.supervisionfee.web.form.SupervisionFeeRequirementItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Monthly supervision fee form validator.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 12, 2014)
 * @since OMIS 3.0
 */
public class MonthlySupervisionFeeFormValidator implements Validator {

	/** Instantiates a default monthly supervision fee form validator. */
	public MonthlySupervisionFeeFormValidator(){
		//Default constructor
	}
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return MonthlySupervisionFeeForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target,  final Errors errors) {
		MonthlySupervisionFeeForm supervisionFeeForm 
			= (MonthlySupervisionFeeForm) target;

		if(supervisionFeeForm.getFee() == null) {
			errors.rejectValue("fee", "monthlySupervisionFee.fee.empty");
		}else{
			if(supervisionFeeForm.getFee().scale() > 2){
				errors.rejectValue("fee", 
						"supervisionFeeRequirement.fee.value.improper.scale");
			}
		}
		if(supervisionFeeForm.getAuthorityCategory() == null) {
			errors.rejectValue("authorityCategory", 
					"monthlySupervisionFee.authorityCategory.empty");
		}
		if(supervisionFeeForm.getStartDate() != null  
				&& supervisionFeeForm.getEndDate() != null
				&& supervisionFeeForm.getStartDate().getTime() 
				> supervisionFeeForm.getEndDate().getTime()) {
			errors.rejectValue("startDate", 
					"dateRange.startDateGreaterThanEndDate");
		}
		if(supervisionFeeForm.getStartDate() == null) {
			errors.rejectValue("startDate", 
					"monthlySupervisionFee.startDate.empty");
		}
		if(supervisionFeeForm.getFeeRequirements() != null) {
			int count = 0;
			for (FeeRequirementItem item : supervisionFeeForm
					.getFeeRequirements()) {
				if (item.getOperation() != null 
						&& !(SupervisionFeeRequirementItemOperation.REMOVE
								.equals(item.getOperation()))) {		
					if (item.getFee() == null) {
						errors.rejectValue("feeRequirements[" + count
								+ "].fee", 
								"supervisionFeeRequirement.fee.empty");
					}
					if(item.getStartDate() != null  && item.getEndDate() != null
							&& item.getStartDate().getTime() 
							> item.getEndDate().getTime()) {
						errors.rejectValue("feeRequirements[" + count
								+ "].startDate", 
								"dateRange.startDateGreaterThanEndDate");
					}
					
					if (item.getStartDate() == null) {
						errors.rejectValue("feeRequirements[" + count
								+ "].startDate", 
								"supervisionFeeRequirement.startDate.empty");
					}
					if (item.getReason() == null) {
						errors.rejectValue("feeRequirements[" + count
								+ "].reason", 
								"supervisionFeeRequirement.reason.empty");
					}
					if (item.getOfficer() == null 
							&& SupervisionFeeRequirementItemAuthority.OFFICER
							.equals(item.getItemAuthority())) {
						errors.rejectValue("feeRequirements[" + count
								+ "].officer", 
								"supervisionFeeRequirement.officer.empty");
					}
					if (item.getCourt() == null 
							&& SupervisionFeeRequirementItemAuthority.COURT
							.equals(item.getItemAuthority())) {
						errors.rejectValue("feeRequirements[" + count
								+ "].court", 
								"supervisionFeeRequirement.court.empty");
					}
				}
				count ++;
			}
		}
	}
}
