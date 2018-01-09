package omis.health.validator.delegate;

import omis.health.web.form.LabWorkAppointmentItem;

import org.springframework.validation.Errors;
/**
 * Delegate to handle validation of a lab work appointment.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2014)
 * @since OMIS 3.0
 */
public class LabWorkAppointmentItemValidatorDelegate {
	
	/**
	 * Returns {@code errors} with any additional rejected values for the
	 * specified list of lab work appointment items.
	 * 
	 * @param labWorkItem lab work appointment item
	 * @param errors errors
	 * @param labWorkAppointmentItemIndex lab work appointment item index
	 * 
	 * @return errors with rejected values
	 */
	public Errors validateLabWorkAppointmentItem(
			final LabWorkAppointmentItem labWorkItem, final Errors errors, 
			final int labWorkAppointmentItemIndex) {
		if (labWorkItem.getProcess() != null && labWorkItem.getProcess()) {
			if (labWorkItem.getDate() == null) {
				errors.rejectValue("labWork[" + labWorkAppointmentItemIndex 
						+ "].date", "labWork.sampleDate.empty");
			}
			if (labWorkItem.getLabWorkCategory() == null) {
				errors.rejectValue("labWork[" + labWorkAppointmentItemIndex 
						+ "].labWorkCategory",  
						"labWork.labWorkCategory.empty");
			}
			if (labWorkItem.getSampleLab() == null) {
				errors.rejectValue("labWork[" + labWorkAppointmentItemIndex 
						+ "].sampleLab",
						"labWork.sampleLab.empty");
			}
			if (labWorkItem.getOrderDate() != null && labWorkItem
						.getDate() != null) {
				if (labWorkItem.getOrderDate().getTime() 
						> labWorkItem.getDate().getTime()) {
					errors.rejectValue("labWork[" 
						+ labWorkAppointmentItemIndex + "].orderDate", 
							"labWork.orderDate.afterDate");
				}
			}
			if (labWorkItem.getResultsDate() != null && labWorkItem
					.getDate() != null) {
				if (labWorkItem.getResultsDate().getTime() 
						< labWorkItem.getDate().getTime()) {
					errors.rejectValue("labWork[" 
						+ labWorkAppointmentItemIndex + "].resultsDate", 
							"labWork.resultsDate.beforeDate");
				}
			}
		}
		return errors;
	}
}