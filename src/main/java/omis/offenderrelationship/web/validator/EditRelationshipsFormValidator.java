package omis.offenderrelationship.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.address.web.validator.delegate.AddressFieldsValidatorDelegate;
import omis.contact.web.form.OnlineAccountContactItemOperation;
import omis.contact.web.form.OnlineAccountFields;
import omis.contact.web.form.TelephoneNumberFields;
import omis.contact.web.form.TelephoneNumberItemOperation;
import omis.contact.web.validator.delegate.OnlineAccountFieldsValidatorDelegate;
import omis.contact.web.validator.delegate.PoBoxFieldsValidatorDelegate;
import omis.contact.web.validator.delegate.TelephoneNumberFieldsValidatorDelegate;
import omis.offenderrelationship.web.controller.OffenderRelationshipAddressOperation;
import omis.offenderrelationship.web.form.EditRelationshipsForm;
import omis.offenderrelationship.web.form.OnlineAccountContactItem;
import omis.offenderrelationship.web.form.TelephoneNumberItem;
import omis.person.web.validator.delegate.PersonFieldsValidatorDelegate;

/**
 * Edit offender relationships form validator.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Oct 27, 2017)
 * @since OMIS 3.0
 */
public class EditRelationshipsFormValidator implements Validator {
	/* Validator Delegates. */
	private PersonFieldsValidatorDelegate personFieldsValidatorDelegate;
	private AddressFieldsValidatorDelegate addressFieldsValidatorDelegate;
	private PoBoxFieldsValidatorDelegate poBoxFieldsValidatorDelegate;
	private TelephoneNumberFieldsValidatorDelegate 
		telephoneNumberFieldsValidatorDelegate;
	private OnlineAccountFieldsValidatorDelegate 
		onlineAccountFieldsValidatorDelegate;
	
	/* Constructors. */
	/**
	 * Instantiates an instance of edit offender relationships form validator 
	 * with the specified delegates.
	 * 
	 * @param personFieldsValidatorDelegate person fields validator delegate
	 * @param addressFieldsValidatorDelegate address fields validator delegate
	 * @param poBoxFieldsValidatorDelegate poBoxFields validator delegate
	 * @param telephoneNumberFieldsValidatorDelegate telephoneNumberFields 
	 * validator delegate
	 * @param onlineAccountFieldsValidatorDelegate onlineAccountFields 
	 * validator delegate
	 */
	public EditRelationshipsFormValidator(
		final PersonFieldsValidatorDelegate personFieldsValidatorDelegate,
		final AddressFieldsValidatorDelegate addressFieldsValidatorDelegate,
		final PoBoxFieldsValidatorDelegate poBoxFieldsValidatorDelegate,
		final TelephoneNumberFieldsValidatorDelegate 
			telephoneNumberFieldsValidatorDelegate,
		final OnlineAccountFieldsValidatorDelegate 
			onlineAccountFieldsValidatorDelegate) {
		this.personFieldsValidatorDelegate = personFieldsValidatorDelegate;
		this.addressFieldsValidatorDelegate = addressFieldsValidatorDelegate;
		this.poBoxFieldsValidatorDelegate = poBoxFieldsValidatorDelegate;
		this.telephoneNumberFieldsValidatorDelegate = 
			telephoneNumberFieldsValidatorDelegate;
		this.onlineAccountFieldsValidatorDelegate 
			= onlineAccountFieldsValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return EditRelationshipsForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		EditRelationshipsForm form = (EditRelationshipsForm) target;
		this.personFieldsValidatorDelegate.validatePersonFields(
			form.getPersonFields(), "personFields", errors);
		if(form.getEnterAddress() != null && form.getEnterAddress() == true){ 
			if(form.getAddressOperation().equals(
				OffenderRelationshipAddressOperation.NEW)){
				this.addressFieldsValidatorDelegate.validateAddressFields(
					form.getAddressFields(), "addressFields", errors);
			}
			if(form.getAddressOperation().equals(
				OffenderRelationshipAddressOperation.EXISTING)){
				if (form.getAddress() == null) {
					errors.rejectValue("address", "address.empty");
				}
			}
		}
		if(form.getEnterPoBox() != null && form.getEnterPoBox() == true){
			this.poBoxFieldsValidatorDelegate.validatePoBoxFields(
				form.getPoBoxFields(), "poBoxFields", errors);
		}
		
		
		if(form.getTelephoneNumberItems().size()!=0){
			boolean primaryTelephoneExists = false;
			int nonRemoveCount = 0;
			for(int index=0; index < form.getTelephoneNumberItems().size(); 
					index++) {
				TelephoneNumberItem telephoneItem = form
						.getTelephoneNumberItems().get(index);
				if((telephoneItem.getOperation() != null) && (!telephoneItem
						.getOperation().equals(
								TelephoneNumberItemOperation.REMOVE))){
					TelephoneNumberFields fields = telephoneItem
							.getTelephoneNumberFields();
					this.telephoneNumberFieldsValidatorDelegate
						.validateTelephoneNumberFields(fields,
								"telephoneNumberItems[" + index + 
								"].telephoneNumberFields", errors);
					if(fields != null){
						if(fields.getPrimary() != null && 
								fields.getPrimary()) {
							if (fields.getActive() != null &&
								fields.getActive()) {
								if (primaryTelephoneExists) {
									errors.rejectValue("telephoneNumberItems[" + 
											index + 
											"].telephoneNumberFields.primary", 
											"telephoneNumberPrimary.extra");
								} else {
									primaryTelephoneExists = true;
								}
							} else {
								errors.rejectValue("telephoneNumberItems[" + 
										index + 
										"].telephoneNumberFields.active", 
										"telephoneNumberActive.true");
							}
						}
						for (int innerIndex = 0; innerIndex < index; 
								innerIndex++) {
							if (fields.getValue() != null && form
									.getTelephoneNumberItems().get(innerIndex)
									.getTelephoneNumberFields() != null &&
									fields.getValue().equals(form
											.getTelephoneNumberItems().get(
											innerIndex)
											.getTelephoneNumberFields()
											.getValue()) &&
									!TelephoneNumberItemOperation
									.REMOVE.equals(form
											.getTelephoneNumberItems().get(
													innerIndex)
											.getOperation())) {
								errors.rejectValue("telephoneNumberItems[" + 
													index + 
													"].telephoneNumberFields.value",
													"telephoneNumber.duplicate");
								break;
							}
						}
					}
					nonRemoveCount++;
				}
			}
			if(!primaryTelephoneExists && nonRemoveCount > 0){
				errors.rejectValue(
						"telephoneNumberItems", "telephoneNumberPrimary.empty");
			}
		}
		if(form.getOnlineAccountContactItems().size()!=0){
			boolean primaryOnlineAccountExists = false;
			int nonRemoveCount = 0;
			for(int index=0; index < form.getOnlineAccountContactItems().size(); 
					index++) {
				OnlineAccountContactItem accountItem = form
						.getOnlineAccountContactItems().get(index);
				if(accountItem.getOperation() != null &&
						!accountItem.getOperation().equals(
						OnlineAccountContactItemOperation.REMOVE)){
					OnlineAccountFields fields = accountItem
							.getOnlineAccountFields();
					this.onlineAccountFieldsValidatorDelegate
					.validateOnlineAccountFields(fields, 
							"onlineAccountContactItems[" + index + 
							"].onlineAccountFields",
							errors);
					if(fields != null){
						if(fields.getPrimary() != null && 
								fields.getPrimary().equals(true)){
							if (fields.getActive() != null && 
									fields.getActive()) {
								if (primaryOnlineAccountExists) {
									errors.rejectValue(
											"onlineAccountContactItems[" + index + 
											"].onlineAccountFields.primary", 
											"emailPrimary.extra");
								} else {
									primaryOnlineAccountExists = true;
								}
							} else {
								errors.rejectValue("onlineAccountContactItems[" + 
										index + "].onlineAccountFields.active", 
										"onlineAccountActive.true");
							}
						}
						for (int innerIndex = 0; innerIndex < index; 
								innerIndex++) {
							if (fields.getName() != null && form
									.getOnlineAccountContactItems()
									.get(innerIndex).getOnlineAccountFields() != 
									null && fields.getName().equals(form
											.getOnlineAccountContactItems()
											.get(innerIndex)
											.getOnlineAccountFields().getName()) 
									&& !OnlineAccountContactItemOperation
									.REMOVE.equals(form
											.getOnlineAccountContactItems().get(
											innerIndex).getOperation())) {
								errors.rejectValue("onlineAccountContactItems[" +
											index + 
											"].onlineAccountFields.name",
											"onlineAccount.duplicate");
								break;
							}
						}
					}
					nonRemoveCount++;
				}
			}
			if(!primaryOnlineAccountExists && nonRemoveCount > 0){
				errors.rejectValue(
					"onlineAccountContactItems", "onlineAccountPrimary.empty");
			}
		}
	}
}