package omis.detainernotification.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import omis.address.web.validator.delegate.AddressFieldsValidatorDelegate;
import omis.detainernotification.web.form.DetainerNoteItem;
import omis.detainernotification.web.form.DetainerNoteItemOperation;
import omis.detainernotification.web.form.DetainerNotificationForm;
import omis.detainernotification.web.form.DetainerNotificationItemOperation;
import omis.detainernotification.web.form.DocumentTagItem;
import omis.detainernotification.web.form.InterstateDetainerActivityItem;
import omis.web.validator.StringLengthChecks;

/**
 * DetainerNotificationFormValidator.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerNotificationFormValidator implements Validator {
	
	/* Validator Delegates */
	
	private AddressFieldsValidatorDelegate addressFieldsValidatorDelegate;
	
	private final StringLengthChecks stringLengthChecks;
	
	/* Message Keys */
	
	private static final String RECEIVE_DATE_REQUIRED_MSG_KEY =
			"receivedDate.empty";
	
	private static final String DETAINER_TYPE_EMPTY_MSG_KEY =
			"detainerType.empty";
	
	private static final String DETAINER_AGENCY_NAME_EMPTY_MSG_KEY =
			"detainerAgencyName.empty";
	
	private static final String INITIATED_BY_EMPTY_MSG_KEY =
			"initiatedBy.empty";
	
	private static final String TELEPHONE_NUMBER_FORMAT_ERROR_MSG_KEY =
			"telephoneNumber.format";
	
	private static final String TITLE_REQUIRED_MSG_KEY = "title.required";
	
	private static final String FILE_DATE_REQUIRED_MSG_KEY =
			"fileDate.required";
	
	private static final String DOCUMENT_REQUIRED_MSG_KEY = "document.required";
	
	private static final String TAG_REQUIRED_MSG_KEY = "tag.required";
	
	private static final String DIRECTION_REQUIRED_MSG_KEY = "direction.empty";
	
	private static final String CATEGORY_REQUIRED_MSG_KEY = "category.empty";
	
	
	/**
	 * Constructor for detainer notification form validator.
	 * 
	 * @param addressFieldsValidatorDelegate - address fields validator delegate
	 * @param stringLengthChecks string length checks validator delegate
	 */
	public DetainerNotificationFormValidator(
			final AddressFieldsValidatorDelegate 
			addressFieldsValidatorDelegate,
			final StringLengthChecks stringLengthChecks) {
		this.addressFieldsValidatorDelegate = addressFieldsValidatorDelegate;
		this.stringLengthChecks = stringLengthChecks;
	}

	/**{@inheritDoc} */
	@Override
	public boolean supports(Class<?> clazz) {
		return DetainerNotificationForm.class.isAssignableFrom(clazz);
	}

	/* Validation */
	
	/**{@inheritDoc} */
	@Override
	public void validate(Object target, Errors errors) {
		DetainerNotificationForm form = (DetainerNotificationForm) target;
		
		if(form.getCreatingDetainerAgency()){
			ValidationUtils.rejectIfEmpty(errors, "agencyName", 
					DETAINER_AGENCY_NAME_EMPTY_MSG_KEY);
			if(form.getUsingAddress()){
				this.addressFieldsValidatorDelegate.validateAddressFields(
						form.getAddressFields(), "addressFields", errors);
			}
			if(!form.getTelephoneNumber().isEmpty()){
				if (!(form.getTelephoneNumber()
						.matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}"))){
					errors.rejectValue("telephoneNumber",
							TELEPHONE_NUMBER_FORMAT_ERROR_MSG_KEY);
				}
			}
		}
		if(form.getUsingInterstateAgreementDetainer()){
			ValidationUtils.rejectIfEmpty(errors, "initiatedBy",
					INITIATED_BY_EMPTY_MSG_KEY);
			if(form.getInterstateDetainerActivityItems() != null){
				int i = 0;
				for(InterstateDetainerActivityItem item :
					form.getInterstateDetainerActivityItems()){
					//required: direction and category
					//if any document field != null, all document fields required
					if(DetainerNotificationItemOperation.CREATE.equals(
							item.getItemOperation()) ||
						DetainerNotificationItemOperation.UPDATE.equals(
							item.getItemOperation())){
						
						ValidationUtils.rejectIfEmpty(errors,
								"interstateDetainerActivityItems["+i+"].direction",
									DIRECTION_REQUIRED_MSG_KEY);
						ValidationUtils.rejectIfEmpty(errors,
								"interstateDetainerActivityItems["+i+"].category",
									CATEGORY_REQUIRED_MSG_KEY);
						
						if((item.getFileExtension() != null 
								&& item.getFileExtension().trim().length() > 0)
								|| (item.getTitle() != null 
								&& item.getTitle().trim().length() > 0)
								|| item.getFileDate() != null
								|| item.getDocumentTagItems() != null){
						
							ValidationUtils.rejectIfEmpty(errors,
								"interstateDetainerActivityItems["+i+"].title",
									TITLE_REQUIRED_MSG_KEY);
							ValidationUtils.rejectIfEmpty(errors,
								"interstateDetainerActivityItems["+i+"].fileDate",
									FILE_DATE_REQUIRED_MSG_KEY);
							ValidationUtils.rejectIfEmpty(errors,
									"interstateDetainerActivityItems["+i+"].data", 
									DOCUMENT_REQUIRED_MSG_KEY);
							
							if(item.getDocumentTagItems() != null){
								int j = 0;
								for(DocumentTagItem tagItem :
										item.getDocumentTagItems()){
									if(DetainerNotificationItemOperation.CREATE
											.equals(tagItem.getItemOperation()) ||
											DetainerNotificationItemOperation.UPDATE
											.equals(tagItem.getItemOperation())){
										ValidationUtils.rejectIfEmpty(errors,
											"interstateDetainerActivityItems["+i+"]"
													+ ".documentTagItems["+j+"].name",
												TAG_REQUIRED_MSG_KEY);
									}
									j++;
								}
							}
						}
					}
					i++;
				}
			}
		}
		int index = 0;
		if (form.getNoteItems() != null) {
			for (DetainerNoteItem item 
					: form.getNoteItems()) {
				if (DetainerNoteItemOperation.CREATE.equals(item
						.getOperation()) || DetainerNoteItemOperation
						.UPDATE.equals(item.getOperation())) {
					if (item.getDate() == null) {
						errors.rejectValue("noteItems[" + index 
								+ "].date", "detainerNote.date.empty");
					}
					if (item.getValue() == null || item.getValue().isEmpty()) {
						errors.rejectValue("noteItems[" + index 
								+ "].value", "detainerNote.value.empty");
					} else {
						this.stringLengthChecks.getVeryHugeCheck().check("noteItems[" + index 
								+ "].value", item.getValue(), errors);
					}
					index++;
				}
			}
		}
		if (Boolean.TRUE.equals(form.getProcessed())) {
			if (Boolean.TRUE.equals(form.getDetainerWarrantProcessingStatusFields()
					.getOtherFacility())) {
				if (form.getDetainerWarrantProcessingStatusFields()
						.getFacilityName().isEmpty()) {
					errors.rejectValue(
						"detainerWarrantProcessingStatusFields.facilityName",
						"facilityName.empty");
				} else {
					this.stringLengthChecks.getMediumCheck()
					.check("detainerWarrantProcessingStatusFields.facilityName",
							form.getDetainerWarrantProcessingStatusFields()
							.getFacilityName(), errors);
				}
			} else if (Boolean.FALSE.equals(
					form.getDetainerWarrantProcessingStatusFields()
					.getOtherFacility())){
				if (form.getDetainerWarrantProcessingStatusFields()
						.getFacility() == null) {
					errors.rejectValue(
							"detainerWarrantProcessingStatusFields.facility",
							"facility.empty");
				}
			}
		}
		if((form.getCourtCaseNumber() == null 
				|| form.getCourtCaseNumber().isEmpty()) 
				&& (form.getWarrantNumber() == null 
				|| form.getWarrantNumber().isEmpty())) {
			errors.rejectValue("courtCaseNumber", "causeAndWarrantNumber.empty");
		}
		ValidationUtils.rejectIfEmpty(errors, "receiveDate", 
				RECEIVE_DATE_REQUIRED_MSG_KEY);
		ValidationUtils.rejectIfEmpty(errors, "detainerType",
				DETAINER_TYPE_EMPTY_MSG_KEY);
	}
}