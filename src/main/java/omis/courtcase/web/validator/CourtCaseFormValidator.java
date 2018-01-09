package omis.courtcase.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.courtcase.web.form.ChargeItem;
import omis.courtcase.web.form.ChargeItemOperation;
import omis.courtcase.web.form.CourtCaseForm;
import omis.courtcase.web.form.CourtCaseNoteItem;
import omis.courtcase.web.form.CourtCaseNoteItemOperation;

/**
 * Validator for court case form.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.2 (May 16, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseFormValidator
		implements Validator {

	/** Instantiates a default court case form validator. */
	public CourtCaseFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return CourtCaseForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		CourtCaseForm courtCaseForm = (CourtCaseForm) target;
		if (courtCaseForm.getAllowDocket() && (courtCaseForm.getDocketValue() == null
				|| courtCaseForm.getDocketValue().length() < 1)) {
			errors.rejectValue("docketValue", "courtCase.docket.empty");
		}
		if (courtCaseForm.getAllowCourt() && courtCaseForm.getCourt() == null) {
			errors.rejectValue("court", "courtCase.court.empty");
		}
		if (courtCaseForm.getJudge() == null) {
			errors.rejectValue("judge", "courtCase.judge.empty");
		}
		if (courtCaseForm.getCharges() == null 
				|| courtCaseForm.getCharges().size() < 1) {
			errors.rejectValue("charges", "courtCase.charges.empty");
		} else {
			Integer charges = 0;
			for (int index = 0; index < courtCaseForm.getCharges().size(); 
					index++) {
				ChargeItem chargeItem = courtCaseForm.getCharges().get(index);
				if (chargeItem.getOperation() != null) {
					if (ChargeItemOperation.CREATE.equals(
							chargeItem.getOperation()) || 
							ChargeItemOperation.EDIT.equals(
									chargeItem.getOperation())) {
						if (chargeItem.getDate() == null) {
							errors.rejectValue("charges[" + index + "].date", 
									"courtCase.charge.date.empty");
						}
						if (chargeItem.getFileDate() == null) {
							errors.rejectValue("charges[" + index + 
									"].fileDate", 
									"courtCase.charge.fileDate.empty");
						}
						if (chargeItem.getOffense() == null) {
							errors.rejectValue("charges[" + index + "].offense", 
									"courtCase.charge.offense.empty");
						}
						if (chargeItem.getCount() == null) {
							errors.rejectValue("charges[" + index + "].count", 
									"courtCase.charge.count.empty");
						}
						charges++;
					}
				}
			}
			if (charges == 0) {
				errors.rejectValue("charges", "courtCase.charges.empty");
			}
		}
		if (courtCaseForm.getNoteItems() != null) {
			for (int index = 0; index < courtCaseForm.getNoteItems().size(); 
					index++) {
				CourtCaseNoteItem noteItem 
					= courtCaseForm.getNoteItems().get(index);
				if (noteItem.getOperation() != null) {
					if (CourtCaseNoteItemOperation.CREATE.equals(
							noteItem.getOperation()) || 
							CourtCaseNoteItemOperation.EDIT.equals(
									noteItem.getOperation())) {
						if (noteItem.getDate() == null) {
							errors.rejectValue("noteItems[" + index + "].date", 
									"courtCase.note.date.empty");
						}
						if (noteItem.getValue() == null 
								|| noteItem.getValue().isEmpty()) {
							errors.rejectValue("noteItems[" + index + "].value", 
									"courtCase.note.value.empty");
						}
					}
				}
			}
		}
	}
}