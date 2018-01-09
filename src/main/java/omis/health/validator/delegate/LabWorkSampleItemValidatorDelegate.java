package omis.health.validator.delegate;

import omis.health.web.form.LabWorkSampleItem;

import org.springframework.validation.Errors;

/**
 * Delegate to handle validation of lab work sample items.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 7, 2014)
 * @since OMIS 3.0
 */
public class LabWorkSampleItemValidatorDelegate {

	/**
	 * Returns {@code errors} with any additional rejected values for the
	 * specified lab work sample item. Errors are assumed to be for a collection
	 * of lab work sample items called {@code labWorkSampleItems}.
	 * 
	 * @param item lab work sample item
	 * @param errors errors
	 * @param labWorkSampleItemIndex lab work sample item index
	 * 
	 * @return errors with rejected values
	 */
	public Errors validateLabWorkSampleItem(final LabWorkSampleItem item, 
			final Errors errors, final int labWorkSampleItemIndex) {
		
		if (item.getProcess() != null && item.getProcess()) {
			if (item.getSampleLab() == null) {
				errors.rejectValue("labWorkSampleItems[" 
							+ labWorkSampleItemIndex + "].sampleLab", 
							"labWorkSampleItem.sampleLab.empty");
			}
			if (item.getSampleDate() == null) {
				errors.rejectValue("labWorkSampleItems[" 
						+ labWorkSampleItemIndex + "].sampleDate", 
						"labWorkSampleItem.sampleDate.empty");
			}
			if (item.getLabWorkCategory() == null) {
				errors.rejectValue("labWorkSampleItems["
						+ labWorkSampleItemIndex + "].labWorkCategory", 
						"labWorkSampleItem.labWorkCategory.empty");
			}
			if (item.getOrderDate() != null && item.getSampleDate() != null &&
					item.getOrderDate().getTime() > item.getSampleDate()
					.getTime()) {
				errors.rejectValue("labWorkSampleItems["
						+ labWorkSampleItemIndex + "].orderDate", 
						"labWorkSampleItem.orderDate.afterSampleDate");
			}
		}
		return errors;
	}
}