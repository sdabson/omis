package omis.military.web.validator;

import omis.military.web.form.MilitaryServiceTermForm;
import omis.military.web.form.MilitaryServiceTermNoteItem;
import omis.military.web.form.MilitaryServiceTermNoteItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Military service term form validator.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 15, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermFormValidator implements Validator {
	
	public MilitaryServiceTermFormValidator() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return MilitaryServiceTermForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		MilitaryServiceTermForm form = (MilitaryServiceTermForm) target;
		
		if (form.getStartDate() == null) {
			errors.rejectValue("startDate", "serviceTerm.startDate.empty");
		}
		if (form.getBranch() == null) {
			errors.rejectValue("branch", "serviceTerm.branch.empty");
		}
		if (form.getStartDate() != null && form.getEndDate() != null) {
			if (form.getStartDate().getTime() > form.getEndDate().getTime()) {
				errors.rejectValue("endDate",
						"serviceTerm.endDate.beforeStartDate");
			}
		}
		if (form.getEndDate() != null && form.getDischargeStatus() == null) {
			errors.rejectValue("dischargeStatus",
					"serviceTerm.dischargeStatus.emptyWithEndDate");
		}
		if (form.getDischargeStatus() != null && form.getEndDate() == null) {
			errors.rejectValue("endDate",
					"serviceTerm.endDate.emptyWithDischargeStatus");
		}
		int index = 0;
		if (form.getServiceTermNoteItems() != null) {
			for (MilitaryServiceTermNoteItem item 
					: form.getServiceTermNoteItems()) {
				if (MilitaryServiceTermNoteItemOperation.CREATE.equals(item
						.getOperation()) || MilitaryServiceTermNoteItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("serviceTermNoteItems[" + index 
								+ "].date", "serviceTermNote.date.empty");
					}
					if (item.getNote() == null || item.getNote().length() < 1) {
						errors.rejectValue("serviceTermNoteItems[" + index 
								+ "].note", "serviceTermNote.note.empty");
					}
				}
				index++;
			}
		}
	}
}
