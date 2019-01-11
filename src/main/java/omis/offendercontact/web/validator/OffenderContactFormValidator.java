/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.offendercontact.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import omis.address.web.validator.delegate.AddressFieldsValidatorDelegate;
import omis.contact.web.form.OnlineAccountFields;
import omis.contact.web.form.TelephoneNumberFields;
import omis.contact.web.validator.delegate.OnlineAccountFieldsValidatorDelegate;
import omis.contact.web.validator.delegate.PoBoxFieldsValidatorDelegate;
import omis.contact.web.validator.delegate.TelephoneNumberFieldsValidatorDelegate;
import omis.offendercontact.web.form.OffenderContactForm;
import omis.offendercontact.web.form.OffenderContactMailingAddressOperation;
import omis.offendercontact.web.form.OffenderContactOnlineAccountItem;
import omis.offendercontact.web.form.OffenderContactOnlineAccountOperation;
import omis.offendercontact.web.form.OffenderContactTelephoneNumberItem;
import omis.offendercontact.web.form.OffenderContactTelephoneNumberOperation;

/**
 * Validator for form for offender contact.
 *
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.0.2 (Oct 27, 2017)
 * @since OMIS 3.0
 */
public class OffenderContactFormValidator implements Validator {

	private TelephoneNumberFieldsValidatorDelegate 
		telephoneNumberFieldsValidatorDelegate;
	
	private OnlineAccountFieldsValidatorDelegate 
		onlineAccountFieldsValidatorDelegate;
	
	private  AddressFieldsValidatorDelegate	addressFieldsValidatorDelegate;
	
	private PoBoxFieldsValidatorDelegate poBoxFieldsValidatorDelegate;
	
	/* Constructor */
	public OffenderContactFormValidator(final AddressFieldsValidatorDelegate
			addressFieldsValidatorDelegate,
			final PoBoxFieldsValidatorDelegate poBoxFieldsValidatorDelegate,
			final TelephoneNumberFieldsValidatorDelegate 
			telephoneNumberFieldsValidatorDelegate,
			final OnlineAccountFieldsValidatorDelegate 
			onlineAccountFieldsValidatorDelegate) {
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
		return OffenderContactForm.class.isAssignableFrom(clazz);
	}

	/** {@inheritDoc} */
	@Override
	public void validate(final Object target, final Errors errors) {
		
		OffenderContactForm offenderContactForm
			= (OffenderContactForm) target;
		
		// Validates mailing address fields
		if (offenderContactForm.getShowMailingAddressFields() != null &&
				offenderContactForm.getShowMailingAddressFields()) {
			if (offenderContactForm.getEnterMailingAddressFields()) {
				if (offenderContactForm.getMailingAddressFields() != null) {
					if (offenderContactForm.getMailingAddressOperation().equals(
							OffenderContactMailingAddressOperation.CREATE_NEW)) 
					{
						this.addressFieldsValidatorDelegate
							.validateAddressFields(
								offenderContactForm.getMailingAddressFields(),
								"mailingAddressFields", errors);
					}  else if (offenderContactForm.getMailingAddressOperation().equals(
							OffenderContactMailingAddressOperation.USE_EXISTING)) {	 						
						if (offenderContactForm.getExistingMailingAddress() == null) {
							errors.rejectValue("existingMailingAddressQuery", "existingMailingAddressQuery.empty");
						}
					} else {						
							errors.rejectValue("mailingAddressOperation", "mailingAddressOperation.empty");			
					}
				}				
			}			
		}
		
		// Ensures that if primary residence is to be created at mailing
		// address, effective date is supplied
		if (offenderContactForm.getResidentAtMailingAddress() != null
				&& offenderContactForm.getResidentAtMailingAddress()
				&& offenderContactForm
					.getResidentAtMailingAddressEffectiveDate() == null) {
			errors.rejectValue("residentAtMailingAddressEffectiveDate",
					"residentAtMailingAddressEffectiveDate.empty");
		}
		
		// Validates po box fields
		if (offenderContactForm.getShowPoBoxFields() != null &&
				offenderContactForm.getShowPoBoxFields()) {
			if (offenderContactForm.getEnterPoBoxFields()) {
				if (offenderContactForm.getPoBoxFields() != null) {
					this.poBoxFieldsValidatorDelegate.validatePoBoxFields(
							offenderContactForm.getPoBoxFields(), 
							"poBoxFields", errors);
				}
			}
		}
		
		// Validates telephone numbers
		if (offenderContactForm.getTelephoneNumberItems() != null) {
			boolean primaryFound = false;
			int nonRemoveCount = 0;
			for (int itemIndex = 0; itemIndex
					< offenderContactForm.getTelephoneNumberItems().size();
					itemIndex++) {
				OffenderContactTelephoneNumberItem numberItem = 
						offenderContactForm.getTelephoneNumberItems().get(
						itemIndex);
				if (numberItem.getOperation() != null && 
						!OffenderContactTelephoneNumberOperation.REMOVE.equals(
						numberItem.getOperation())) {
					TelephoneNumberFields fields = numberItem.getFields();
					
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
						if (fields.getValue() != null && offenderContactForm
								.getTelephoneNumberItems().get(innerIndex)
								.getFields() != null && fields.getValue()
								.equals(offenderContactForm
										.getTelephoneNumberItems().get(
										innerIndex).getFields().getValue()) &&
								!OffenderContactTelephoneNumberOperation.REMOVE
								.equals(offenderContactForm
										.getTelephoneNumberItems().get(
												innerIndex).getOperation())) {
							errors.rejectValue("telephoneNumberItems[" + 
												itemIndex + "].fields.value", 
									"telephoneNumber.duplicate");
							break;
						}
					}
					nonRemoveCount++;
				}
			}
			if (!primaryFound && nonRemoveCount > 0) {
				errors.rejectValue(
						"telephoneNumberItems", "telephoneNumberPrimary.empty");
			}
		}
		
		// Validates online accounts
		if (offenderContactForm.getOnlineAccountItems() != null) {
			boolean primaryFound = false;
			int nonRemoveCount = 0;
			for (int itemIndex = 0; itemIndex
					< offenderContactForm.getOnlineAccountItems().size();
					itemIndex++) {
				OffenderContactOnlineAccountItem accountItem = 
						offenderContactForm.getOnlineAccountItems().get(
								itemIndex);
				if (accountItem.getOperation() != null && 
						!OffenderContactOnlineAccountOperation.REMOVE.equals(
						accountItem.getOperation())) {
					OnlineAccountFields fields = accountItem.getFields();
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
						if (fields.getName() != null && 
								!fields.getName().isEmpty() && 
								offenderContactForm.getOnlineAccountItems()
								.get(innerIndex).getFields() != null && 
								fields.getName().equals(offenderContactForm
										.getOnlineAccountItems().get(innerIndex)
										.getFields().getName()) &&
								!OffenderContactOnlineAccountOperation.REMOVE
								.equals(offenderContactForm
										.getOnlineAccountItems().get(
										innerIndex).getOperation())) {
							errors.rejectValue("onlineAccountItems[" + itemIndex
									+ "].fields.name", 
									"onlineAccount.duplicate");
							break;
						}
					}
					nonRemoveCount++;
				}
			}
			if (!primaryFound && nonRemoveCount > 0) {
				errors.rejectValue(
						"onlineAccountItems", "onlineAccountPrimary.empty");
			}
		}
	}
}
