package omis.stg.web.validator;

import omis.stg.web.form.SecurityThreatGroupAffiliationForm;
import omis.stg.web.form.SecurityThreatGroupAffiliationNoteItem;
import omis.stg.web.form.SecurityThreatGroupAffiliationNoteItemOperation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validator for form for security threat group affiliations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationFormValidator
		implements Validator {

	/**
	 * Instantiates a validator for form for security threat group affiliations.
	 */
	public SecurityThreatGroupAffiliationFormValidator() {
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
		SecurityThreatGroupAffiliationForm securityThreatGroupAffiliationForm
			= (SecurityThreatGroupAffiliationForm) target;
		if (securityThreatGroupAffiliationForm.getStartDate() == null) {
			errors.rejectValue("startDate", "startDate.empty");
		}
		if (securityThreatGroupAffiliationForm.getStartDate() != null
				&& securityThreatGroupAffiliationForm.getEndDate() != null
				&& securityThreatGroupAffiliationForm.getStartDate().getTime()
				> securityThreatGroupAffiliationForm.getEndDate().getTime()) {
			errors.rejectValue("startDate",
					"dateRange.startDateGreaterThanEndDate");
		}
		if (securityThreatGroupAffiliationForm.getGroup() == null) {
			errors.rejectValue("group", "securityThreatGroup.empty");
		}
		if (securityThreatGroupAffiliationForm.getCreateNewChapter()) {
			if (securityThreatGroupAffiliationForm.getChapterName() == null ||
					securityThreatGroupAffiliationForm.getChapterName()
					.isEmpty()) {
				errors.rejectValue("chapterName", 
						"securityThreatGroupChapter.name.empty");
			}
		}
		if (securityThreatGroupAffiliationForm.getCreateNewRank()) {
			if (securityThreatGroupAffiliationForm.getRankName() == null ||
					securityThreatGroupAffiliationForm.getRankName()
					.isEmpty()) {
				errors.rejectValue("rankName", 
						"securityThreatGroupRank.name.empty");
			}
		}
		if (securityThreatGroupAffiliationForm.getCity() != null
				&& !securityThreatGroupAffiliationForm.getCity().getState()
					.equals(securityThreatGroupAffiliationForm.getState())) {
			errors.rejectValue("city", "city.notInState");
		}
		if (securityThreatGroupAffiliationForm.getVerificationResult() != null
				&& securityThreatGroupAffiliationForm.getVerificationResult()) {
			if (securityThreatGroupAffiliationForm
					.getVerificationUserAccount() == null) {
				errors.rejectValue("verificationUserAccount",
						"verificationUserAccount.empty");
			}
			if (securityThreatGroupAffiliationForm
					.getVerificationDate() == null) {
				errors.rejectValue("verificationDate",
						"verificationDate.empty");
			}
			if (securityThreatGroupAffiliationForm
					.getVerificationMethod() == null) {
				errors.rejectValue("verificationMethod",
						"verificationMethod.empty");
			}
		} else {
			if (securityThreatGroupAffiliationForm
					.getVerificationUserAccount() != null) {
				errors.rejectValue("verificationUserAccount",
						"verificationSignature.notEmpty");
			}
			if (securityThreatGroupAffiliationForm
					.getVerificationDate() != null) {
				errors.rejectValue("verificationDate",
						"verificationSignature.notEmpty");
			}
			if (securityThreatGroupAffiliationForm
					.getVerificationMethod() != null) {
				errors.rejectValue("verificationMethod",
						"verificationSignature.notEmpty");
			}
		}
		int index = 0;
		if (securityThreatGroupAffiliationForm.getAffiliationNoteItems() != null) {
			for (SecurityThreatGroupAffiliationNoteItem item 
					: securityThreatGroupAffiliationForm.getAffiliationNoteItems()) {
				if (SecurityThreatGroupAffiliationNoteItemOperation.CREATE.equals(item
						.getOperation()) || SecurityThreatGroupAffiliationNoteItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("affiliationNoteItems[" + index 
								+ "].date", "affiliationNote.date.empty");
					}
					if (item.getNote() == null || item.getNote().length() < 1) {
						errors.rejectValue("affiliationNoteItems[" + index 
								+ "].note", "affiliationNote.note.empty");
					}
					index++;
				}
			}
		}
	}
}