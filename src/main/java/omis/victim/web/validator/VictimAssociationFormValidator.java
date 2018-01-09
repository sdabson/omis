package omis.victim.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.address.web.validator.delegate.AddressFieldsValidatorDelegate;
import omis.contact.web.form.OnlineAccountFields;
import omis.contact.web.form.TelephoneNumberFields;
import omis.contact.web.validator.delegate.OnlineAccountFieldsValidatorDelegate;
import omis.contact.web.validator.delegate.PoBoxFieldsValidatorDelegate;
import omis.contact.web.validator.delegate.TelephoneNumberFieldsValidatorDelegate;
import omis.victim.web.form.VictimAssociationForm;
import omis.victim.web.form.VictimMailingAddressOperation;
import omis.victim.web.form.VictimNoteItem;
import omis.victim.web.form.VictimOnlineAccountItem;
import omis.victim.web.form.VictimOnlineAccountOperation;
import omis.victim.web.form.VictimTelephoneNumberItem;
import omis.victim.web.form.VictimTelephoneNumberOperation;
import omis.victim.web.validator.delegate.VictimNoteItemValidatorDelegate;

/**
 * Validator for form for family associations.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.2 (Oct 27, 2017)
 * @since OMIS 3.0
 */
public class VictimAssociationFormValidator
		implements Validator {

	private final VictimNoteItemValidatorDelegate
		victimNoteItemValidatorDelegate;
	
	private final TelephoneNumberFieldsValidatorDelegate
		telephoneNumberFieldsValidatorDelegate;
	
	private final OnlineAccountFieldsValidatorDelegate
		onlineAccountFieldsValidatorDelegate;
	
	private final AddressFieldsValidatorDelegate addressFieldsValidatorDelegate;
	
	private final PoBoxFieldsValidatorDelegate poBoxFieldsValidatorDelegate;

	/**
	 * Validator for form for victim associations.
	 * 
	 * @param victimNoteItemValidatorDelegate delegate for victim note items
	 * @param telephoneNumberFieldsValidatorDelegate validator for telephone
	 * number fields
	 * @param onlineAccountFieldsValidatorDelegate validator for online account
	 * fields
	 * @param addressFieldsValidatorDelegate validator for mailing address 
	 * fields
	 * @param poBoxFieldsValidatorDelegate validator for po box fields
	 */
	public VictimAssociationFormValidator(
			final VictimNoteItemValidatorDelegate
				victimNoteItemValidatorDelegate,
			final TelephoneNumberFieldsValidatorDelegate
				telephoneNumberFieldsValidatorDelegate,
			final OnlineAccountFieldsValidatorDelegate
				onlineAccountFieldsValidatorDelegate,
			final AddressFieldsValidatorDelegate addressFieldsValidatorDelegate,
			final PoBoxFieldsValidatorDelegate poBoxFieldsValidatorDelegate) {
		this.victimNoteItemValidatorDelegate = victimNoteItemValidatorDelegate;
		this.telephoneNumberFieldsValidatorDelegate
			= telephoneNumberFieldsValidatorDelegate;
		this.onlineAccountFieldsValidatorDelegate
			= onlineAccountFieldsValidatorDelegate;
		this.addressFieldsValidatorDelegate = addressFieldsValidatorDelegate;
		this.poBoxFieldsValidatorDelegate = poBoxFieldsValidatorDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean supports(final Class<?> clazz) {
		return VictimAssociationForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		
		VictimAssociationForm victimAssociationForm
			= (VictimAssociationForm) target;
		
		// Only validate person fields if shown 
		if (victimAssociationForm.getShowPersonFields() != null
				&& victimAssociationForm.getShowPersonFields()) {
			if (victimAssociationForm.getPersonFields() != null) {
				if (victimAssociationForm.getPersonFields().getLastName()
						== null
						|| victimAssociationForm.getPersonFields()
							.getLastName().isEmpty()) {
					errors.rejectValue(
							"personFields.lastName", "lastName.empty");
				}
				if (victimAssociationForm.getPersonFields().getFirstName()
						== null
						|| victimAssociationForm.getPersonFields()
							.getFirstName().isEmpty()) {
					errors.rejectValue(
							"personFields.firstName", "firstName.empty");
				}
			} else {
				errors.rejectValue("personFields.lastName", "lastName.empty");
				errors.rejectValue("personFields.firstName", "firstName.empty");
			}
		}
		
		// Validates mailing address fields
		if (victimAssociationForm.getShowMailingAddressFields() != null &&
				victimAssociationForm.getShowMailingAddressFields()) {
			if (victimAssociationForm.getEnterMailingAddressFields()) {
				if (victimAssociationForm.getMailingAddressFields() != null) {
					if (victimAssociationForm.getMailingAddressOperation().equals(
							VictimMailingAddressOperation.CREATE_NEW)) 
					{
						this.addressFieldsValidatorDelegate
							.validateAddressFields(
								victimAssociationForm.getMailingAddressFields(),
								"mailingAddressFields", errors);
					}
				}
			}
		}
		
		// Validates po box fields
		if (victimAssociationForm.getShowPoBoxFields() != null &&
				victimAssociationForm.getShowPoBoxFields()) {
			if (victimAssociationForm.getEnterPoBoxFields()) {
				if (victimAssociationForm.getPoBoxFields() != null) {
					this.poBoxFieldsValidatorDelegate.validatePoBoxFields(
							victimAssociationForm.getPoBoxFields(), 
							"poBoxFields", errors);
				}
			}
		}
		// Validates telephone numbers
		if (victimAssociationForm.getTelephoneNumberItems() != null) {
			boolean primaryFound = false;
			for (int itemIndex = 0; itemIndex
					< victimAssociationForm.getTelephoneNumberItems().size();
					itemIndex++) {
				VictimTelephoneNumberItem item = victimAssociationForm
						.getTelephoneNumberItems().get(itemIndex);
				if (item != null && item.getOperation() != null) {
					TelephoneNumberFields fields = item.getFields();
					this.telephoneNumberFieldsValidatorDelegate
						.validateTelephoneNumberFields(fields,
								"telephoneNumberItems[" + itemIndex + "].fields",
								errors);
					
					if (fields.getPrimary() != null && fields.getPrimary()) {
						if (fields.getActive() != null && fields.getActive()) {
							if (primaryFound) {
								errors.rejectValue(
										"telephoneNumberItems[" + itemIndex
											+ "].fields.primary",
										"telephoneNumber.primaryExists");
							} else {
								primaryFound = true;
							}
						} else {
							errors.rejectValue("telephoneNumberItems[" + 
									itemIndex + 
									"].fields.active", 
									"telephoneNumber.primaryNotActive");
						}
					}
					for (int innerIndex = 0; innerIndex < itemIndex; 
							innerIndex++) {
						if (fields.getValue() != null 
								&& fields.getValue().equals(
										victimAssociationForm
										.getTelephoneNumberItems()
										.get(innerIndex).getFields()
										.getValue()) 
								&& !VictimTelephoneNumberOperation.REMOVE
									.equals(victimAssociationForm
											.getTelephoneNumberItems()
											.get(innerIndex).getOperation())) {
							errors.rejectValue(
									"telephoneNumberItems[" + itemIndex
										+ "].fields.value",
									"telephoneNumber.duplicate");
							break;
						}
					}
				}
			}
			if (!primaryFound) {
				errors.rejectValue(
						"telephoneNumberItems", "telephoneNumberPrimary.empty");
			}
		}
		
		// Validates online accounts
		if (victimAssociationForm.getOnlineAccountItems() != null) {
			boolean primaryFound = false;
			for (int itemIndex = 0; itemIndex
					< victimAssociationForm.getOnlineAccountItems().size();
					itemIndex++) {
				VictimOnlineAccountItem item = victimAssociationForm
						.getOnlineAccountItems().get(itemIndex);
				if (item != null && item.getOperation() != null) {
					OnlineAccountFields fields = item.getFields();
					this.onlineAccountFieldsValidatorDelegate
						.validateOnlineAccountFields(fields,
								"onlineAccountItems[" + itemIndex + "].fields",
								errors);
					if (fields.getPrimary() != null && fields.getPrimary()) {
						if (fields.getActive() != null && fields.getActive()) {
							if (primaryFound) {
								errors.rejectValue(
										"onlineAccountItems[" + itemIndex
											+ "].fields.primary",
										"onlineAccount.primaryExists");
							} else {
								primaryFound = true;
							}	
						} else {
							errors.rejectValue("onlineAccountItems[" + 
									itemIndex + 
									"].fields.active", 
									"onlineAccount.primaryNotActive");
						}
					}
					for (int innerIndex = 0; innerIndex < itemIndex; 
							innerIndex++) {
						if (fields.getName() != null 
								&& fields.getName().equals(
										victimAssociationForm
										.getOnlineAccountItems()
										.get(innerIndex).getFields()
										.getName()) 
								&& !VictimOnlineAccountOperation.REMOVE
									.equals(victimAssociationForm
											.getOnlineAccountItems()
											.get(innerIndex).getOperation())) {
							errors.rejectValue(
									"onlineAccountItems[" + itemIndex
										+ "].fields.name",
									"onlineAccount.duplicate");
							break;
						}
					}
				}
			}
			if (!primaryFound) {
				errors.rejectValue(
						"onlineAccountItems", "onlineAccountPrimary.empty");
			}
		}
		
		// Validates note items
		if (victimAssociationForm.getNoteItems() != null) {
			for (int itemIndex = 0; itemIndex < victimAssociationForm
					.getNoteItems().size(); itemIndex++) {
				VictimNoteItem victimNoteItem = victimAssociationForm
						.getNoteItems().get(itemIndex);
				if (victimNoteItem != null
						&& victimNoteItem.getOperation() != null) {
					this.victimNoteItemValidatorDelegate
						.validate(victimNoteItem, itemIndex, errors);
				}
			}
		}
	}
}