package omis.stg.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.stg.web.form.SecurityThreatGroupActivityForm;
import omis.stg.web.form.SecurityThreatGroupActivityInvolvementItem;
import omis.stg.web.form.SecurityThreatGroupActivityInvolvementItemOperation;
import omis.stg.web.form.SecurityThreatGroupActivityNoteItem;
import omis.stg.web.form.SecurityThreatGroupActivityNoteItemOperation;
import omis.stg.web.form.SecurityThreatGroupAffiliationForm;

/**
 * Validator for form for security threat group activities.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Jan 4, 2017)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityFormValidator implements Validator {

	/**
	 * Instantiates a validator for form for security threat group affiliations.
	 */
	public SecurityThreatGroupActivityFormValidator() {
		// Default instantiation
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return SecurityThreatGroupAffiliationForm.class.isAssignableFrom(clazz);
	}
	
	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		SecurityThreatGroupActivityForm securityThreatGroupActivityForm
			= (SecurityThreatGroupActivityForm) target;
		if (securityThreatGroupActivityForm.getReportDate() == null) {
			errors.rejectValue("reportDate", "securityThreatGroupActivityReportDate.empty");
		}
		if (securityThreatGroupActivityForm.getReportedBy() == null ) {
			errors.rejectValue("reportedBy", 
					"securityThreatGroupActivityReportedBy.empty");
		}
		if (securityThreatGroupActivityForm.getSummary() == null ||
				securityThreatGroupActivityForm.getSummary()
				.isEmpty()) {
			errors.rejectValue("summary", 
					"securityThreatGroupActivitySummary.empty");
		}
		
		if (securityThreatGroupActivityForm.getInvolvementItems() == null 
				|| securityThreatGroupActivityForm.getInvolvementItems().size() 
				== 0) {
			errors.rejectValue("involvementItems", 
					"securityThreatGroupActivityInvolvedOffender.empty");
		}
		
		int index = 0;
		if (securityThreatGroupActivityForm.getInvolvementItems() != null) {
			for (SecurityThreatGroupActivityInvolvementItem item 
					: securityThreatGroupActivityForm.getInvolvementItems()) {
				if (SecurityThreatGroupActivityInvolvementItemOperation.CREATE.equals(item
						.getOperation()) || SecurityThreatGroupActivityInvolvementItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getOffender() == null) {
						errors.rejectValue("involvementItems[" + index 
								+ "].offender", "securityThreatGroupActivityInvolvedOffender.empty");
					}
					if (item.getNarrative() == null || item.getNarrative().length() < 1) {
						errors.rejectValue("involvementItems[" + index 
								+ "].narrative", "securityThreatGroupActivityInvolvementNarrative.empty");
					}
				}
				index++;
			}
		}
		
		index = 0;
		if (securityThreatGroupActivityForm.getNoteItems() != null) {
			for (SecurityThreatGroupActivityNoteItem item 
					: securityThreatGroupActivityForm.getNoteItems()) {
				if (SecurityThreatGroupActivityNoteItemOperation.CREATE.equals(item
						.getOperation()) || SecurityThreatGroupActivityNoteItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("noteItems[" + index 
								+ "].date", "securityThreatGroupActivityNote.date.empty");
					}
					if (item.getValue() == null || item.getValue().length() < 1) {
						errors.rejectValue("noteItems[" + index 
								+ "].value", "securityThreatGroupActivityNote.note.empty");
					}
				}
				index++;
			}
		}
	}
}
