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
package omis.offendercontact.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.AddressExistsException;
import omis.address.exception.ZipCodeExistsException;
import omis.address.web.controller.delegate.AddressFieldsControllerDelegate;
import omis.address.web.form.AddressFields;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.config.util.FeatureToggles;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.contact.exception.ContactExistsException;
import omis.contact.exception.OnlineAccountExistsException;
import omis.contact.exception.TelephoneNumberExistsException;
import omis.contact.web.controller.delegate.PoBoxFieldsControllerDelegate;
import omis.contact.web.form.OnlineAccountFields;
import omis.contact.web.form.PoBoxFields;
import omis.contact.web.form.TelephoneNumberFields;
import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offendercontact.service.OffenderContactService;
import omis.offendercontact.web.form.OffenderContactForm;
import omis.offendercontact.web.form.OffenderContactMailingAddressOperation;
import omis.offendercontact.web.form.OffenderContactOnlineAccountItem;
import omis.offendercontact.web.form.OffenderContactOnlineAccountOperation;
import omis.offendercontact.web.form.OffenderContactTelephoneNumberItem;
import omis.offendercontact.web.form.OffenderContactTelephoneNumberOperation;
import omis.offendercontact.web.validator.OffenderContactFormValidator;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.NonResidenceTermConflictException;
import omis.residence.exception.ResidenceTermConflictException;
import omis.util.StringUtility;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to create, update and remove an offender contact.
 *
 * @author Josh Divine
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.1 (Dec 13, 2016)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/offenderContact")
public class OffenderContactController {

	/* View names. */
	
	private static final String VIEW_NAME = "offenderContact/edit";
	
	private static final String TELEPHONE_NUMBER_ITEM_VIEW_NAME
		= "offenderContact/includes/telephoneNumberEditTableRow";

	private static final String ONLINE_ACCOUNT_ITEM_VIEW_NAME
		= "offenderContact/includes/onlineAccountEditTableRow";
	
	private static final String ADDRESS_VIEW_NAME = "address/json/addresses";
	
	private static final String ACTION_MENU_VIEW_NAME 
		= "offenderContact/includes/offenderContactActionMenu";
	
	private static final String TELEPHONE_NUMBERS_ACTION_MENU_VIEW_NAME
		= "offenderContact/includes/offenderContactTelephoneNumbersActionMenu";

	private static final String ONLINE_ACCOUNTS_ACTION_MENU_VIEW_NAME
		= "offenderContact/includes/offenderContactOnlineAccountsActionMenu";

	/* Redirects. */
	
	private static final String REDIRECT
		= "redirect:/offender/profile.html?offender=%d";

	/* Model keys. */
	
	private static final String TELEPHONE_NUMBER_CATEGORIES_MODEL_KEY
		= "telephoneNumberCategories";

	private static final String TELEPHONE_NUMBER_ITEM_MODEL_KEY
		= "telephoneNumberItem";
	
	private static final String TELEPHONE_NUMBER_ITEM_INDEX_MODEL_KEY
		= "telephoneNumberItemIndex";
	
	private static final String ONLINE_ACCOUNT_HOSTS_MODEL_KEY
		= "onlineAccountHosts";
	
	private static final String ONLINE_ACCOUNT_ITEM_MODEL_KEY
		= "onlineAccountItem";
	
	private static final String ONLINE_ACCOUNT_ITEM_INDEX_MODEL_KEY
		= "onlineAccountItemIndex";
	
	private static final String OFFENDER_CONTACT_FORM_MODEL_KEY 
		= "offenderContactForm";	
	
	private static final String ADDRESSES_MODEL_KEY = "addresses";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Fields names. */
	
	private static final String MAILING_ADDRESS_FIELDS_NAME
		= "mailingAddressFields";
	
	private static final String PO_BOX_FIELDS_NAME = "poBoxFields";

	/* Toggle keys. */
	
	private static final String ALLOW_RESIDENCE_AT_MAILING_ADDRESS_TOGGLE_KEY
		= "allowResidenceAtMailingAddress";
	
	/* Services. */

	@Autowired
	@Qualifier("offenderContactService")
	private OffenderContactService offenderContactService;
	
	/* Helpers. */

	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("addressFieldsControllerDelegate")
	private AddressFieldsControllerDelegate addressFieldsControllerDelegate;
	
	@Autowired
	@Qualifier("poBoxFieldsControllerDelegate")
	private PoBoxFieldsControllerDelegate poboxFieldsControllerDelegate;
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("contactPropertyEditorFactory")
	private PropertyEditorFactory contactPropertyEditorFactory;
	
	@Autowired
	@Qualifier("countryPropertyEditorFactory")
	private PropertyEditorFactory countryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("addressPropertyEditorFactory")
	private PropertyEditorFactory addressPropertyEditorFactory;
	 	
	@Autowired
	@Qualifier("zipCodePropertyEditorFactory")
	private PropertyEditorFactory zipCodePropertyEditorFactory;
	
	@Autowired
	@Qualifier("onlineAccountHostPropertyEditorFactory")
	private PropertyEditorFactory onlineAccountHostPropertyEditorFactory;
	
	@Autowired
	@Qualifier("telephoneNumberPropertyEditorFactory")
	private PropertyEditorFactory telephoneNumberPropertyEditorFactory;
	
	@Autowired
	@Qualifier("onlineAccountPropertyEditorFactory")
	private PropertyEditorFactory onlineAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Feature toggle repository. */
	
	@Autowired
	@Qualifier("featureToggles")
	private FeatureToggles featureToggles;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("offenderContactFormValidator")
	private OffenderContactFormValidator offenderContactFormValidator;
	
	/* Report names. */
	
	private static final String OFFENDER_CONTACT_DETAILS_REPORT_NAME 
		= "/CaseManagement/Offender_Contact_Information";

	/* Report parameter names. */
	
	private static final String OFFENDER_CONTACT_DETAILS_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Constructors. */

	/**
	 * Instantiates controller to create, update and remove an offender contact.
	 */
	public OffenderContactController() {
		// Default instantiation
	}
	
	/**
	 * Displays offender contact.
	 * 
	 * @param offender offender
	 * @return view to display offender contact
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_CONTACT_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "offender", required = true) 
		final Offender offender) {
		OffenderContactForm offenderContactForm = new OffenderContactForm();
		offenderContactForm.setShowMailingAddressFields(true);
		offenderContactForm.setShowPoBoxFields(true);
		offenderContactForm.setShowTelephoneNumberItems(true);
		offenderContactForm.setShowOnlineAccountItems(true);	
		offenderContactForm.setMailingAddressOperation(
					OffenderContactMailingAddressOperation.USE_EXISTING);
	Address mailingAddress = this.offenderContactService
				.findMailingAddress(offender);
		if (mailingAddress != null) {
			offenderContactForm.setEnterMailingAddressFields(true);
			String addressString;
			if (mailingAddress.getZipCode().getCity().getState() != null) {
				addressString = String.format("%s, %s, %s %s", 
						mailingAddress.getValue(),
						mailingAddress.getZipCode().getCity().getName(),
						mailingAddress.getZipCode().getCity().getState()
						.getAbbreviation(), 
						mailingAddress.getZipCode().getValue());
			} else {
				addressString = String.format("%s, %s %s", 
						mailingAddress.getValue(),
						mailingAddress.getZipCode().getCity().getName(),
						mailingAddress.getZipCode().getValue());
			}
			offenderContactForm.setExistingMailingAddress(mailingAddress);
			offenderContactForm.setExistingMailingAddressQuery(addressString);
			if (this.getAllowResidenceAtMailingAddress()) {
				Date effectiveDate = new Date();
				ResidenceTerm primaryResidenceTerm
					= this.offenderContactService.findPrimaryResidence(
						offender, effectiveDate);
				if (primaryResidenceTerm != null) {
					if (primaryResidenceTerm.getAddress()
							.equals(mailingAddress)) {
						offenderContactForm.setResidentAtMailingAddress(true);
						offenderContactForm
							.setResidentAtMailingAddressEffectiveDate(
									DateRange.getStartDate(
											primaryResidenceTerm
											.getDateRange()));
					}
				}
			}
		}
		PoBox poBox = this.offenderContactService.findPoBox(offender);
		if (poBox != null) {
			ZipCode zipCode = poBox.getZipCode();
			City city = zipCode.getCity();
			State state = city.getState();
			Country country = city.getCountry();
			PoBoxFields poBoxFields = new PoBoxFields(country, state, city, 
					zipCode, poBox.getValue());
			offenderContactForm.setEnterPoBoxFields(true);
			offenderContactForm.setPoBoxFields(poBoxFields);
		}
		List<OnlineAccount> onlineAccounts = this.offenderContactService
				.findOnlineAccounts(offender);
		List<OffenderContactOnlineAccountItem> onlineAccountItems 
			= new ArrayList<OffenderContactOnlineAccountItem>();
		for (OnlineAccount account : onlineAccounts) {
			OffenderContactOnlineAccountItem onlineAccountItem 
				= new OffenderContactOnlineAccountItem();
			onlineAccountItem.setOperation(OffenderContactOnlineAccountOperation
					.UPDATE);
			OnlineAccountFields fields = new OnlineAccountFields(
					account.getName(), account.getHost(), account.getActive(), 
					account.getPrimary());
			onlineAccountItem.setFields(fields);
			onlineAccountItem.setOnlineAccount(account);
			onlineAccountItems.add(onlineAccountItem);
		}
		offenderContactForm.setOnlineAccountItems(onlineAccountItems);
		List<TelephoneNumber> telephoneNumbers = this.offenderContactService
				.findTelephoneNumbers(offender);
		List<OffenderContactTelephoneNumberItem> telephoneNumberItems 
			= new ArrayList<OffenderContactTelephoneNumberItem>();
		for (TelephoneNumber telephoneNumber : telephoneNumbers) {
			OffenderContactTelephoneNumberItem telephoneNumberItem 
				= new OffenderContactTelephoneNumberItem();
			telephoneNumberItem.setOperation(
					OffenderContactTelephoneNumberOperation.UPDATE);
			telephoneNumberItem.setTelephoneNumber(telephoneNumber);
			TelephoneNumberFields fields = new TelephoneNumberFields(
					telephoneNumber.getValue(), telephoneNumber.getExtension(),
					telephoneNumber.getPrimary(), 
					telephoneNumber.getCategory(),
					telephoneNumber.getActive());
			telephoneNumberItem.setFields(fields);
			telephoneNumberItems.add(telephoneNumberItem);
		}
		offenderContactForm.setTelephoneNumberItems(telephoneNumberItems);
		return this.prepareEditMav(offenderContactForm, offender);
	 }
	
	/**
	 * Updates offender contact.
	 * 
	 * @param offender offender
	 * @param offenderContactForm offender contact form
	 * @param result result
	 * @return redirect to offender profile
	 * @throws NonResidenceTermConflictException if attempt is made to set
	 * residence to mailing address and conflicting homeless term exists with
	 * end date
	 * @throws ResidenceTermConflictException if attempt is made to set
	 * residence to mailing address and conflicting primary residence exists
	 * with end date 
	 * @throws CityExistsException 
	 * @throws ZipCodeExistsException 
	 * @throws AddressExistsException 
	 * @throws ContactExistsException 
	 * @throws TelephoneNumberExistsException 
	 * @throws OnlineAccountExistsException 
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('OFFENDER_CONTACT_EDIT')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			final OffenderContactForm offenderContactForm,
			final BindingResult result)
					throws ResidenceTermConflictException,
						NonResidenceTermConflictException, CityExistsException, ZipCodeExistsException, 
						AddressExistsException, TelephoneNumberExistsException, ContactExistsException, 
						OnlineAccountExistsException {
		this.offenderContactFormValidator.validate(offenderContactForm, 
				result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(offenderContactForm, result, 
					offender);			
		}		
		PoBox poBox;
		Address mailingAddress;
		City mailingCity;
		ZipCode mailingZipCode;	
		if (offenderContactForm.getShowMailingAddressFields() != null
				&& offenderContactForm.getShowMailingAddressFields()
				&& offenderContactForm.getEnterMailingAddressFields() != null
				&& offenderContactForm.getEnterMailingAddressFields()) {
			if (OffenderContactMailingAddressOperation.CREATE_NEW.equals(
					offenderContactForm.getMailingAddressOperation())) {
				if (offenderContactForm.getMailingAddressFields()
							.getNewCity() != null
						&& offenderContactForm.getMailingAddressFields()
							.getNewCity()) {
					mailingCity = this.offenderContactService
							.createCity(offenderContactForm
											.getMailingAddressFields()
											.getCityName(),
										offenderContactForm
											.getMailingAddressFields()
											.getState(),
										offenderContactForm
											.getMailingAddressFields()
											.getCountry());
				} else {
					mailingCity = offenderContactForm
							.getMailingAddressFields().getCity();
				}
				if (offenderContactForm.getMailingAddressFields()
						.getNewZipCode() != null
						&& offenderContactForm.getMailingAddressFields()
							.getNewZipCode()) {
					mailingZipCode = this.offenderContactService
							.createZipCode(offenderContactForm
										.getMailingAddressFields()
											.getZipCodeValue(),
									offenderContactForm
										.getMailingAddressFields()
											.getZipCodeExtension(),
									mailingCity);
				} else {
					mailingZipCode
						= offenderContactForm.getMailingAddressFields()
							.getZipCode();
				}
				mailingAddress = this.offenderContactService
						.createAddress(offenderContactForm
								.getMailingAddressFields().getValue(),
							mailingZipCode);
			} else if (OffenderContactMailingAddressOperation.USE_EXISTING
					.equals(offenderContactForm.getMailingAddressOperation())) {
				mailingAddress = offenderContactForm
						.getExistingMailingAddress();
				mailingZipCode = null;
				mailingCity = null;
			} else {
				
				// Prevents other mailing address operations
				throw new UnsupportedOperationException(String.format(
						"Unsupported mailing address operation: %s",
						offenderContactForm.getMailingAddressOperation()));
			}
			if (this.getAllowResidenceAtMailingAddress()) {
				if (offenderContactForm.getResidentAtMailingAddress() != null
						&& offenderContactForm.getResidentAtMailingAddress()) {
					this.offenderContactService.changePrimaryResidence(
							offender, mailingAddress,
							offenderContactForm
								.getResidentAtMailingAddressEffectiveDate());
				}
			}
		} else {
			mailingAddress = null;
			mailingCity = null;
			mailingZipCode = null;
		}
		if (offenderContactForm.getShowPoBoxFields() != null
				&& offenderContactForm.getShowPoBoxFields()
				&& offenderContactForm.getEnterPoBoxFields() != null
				&& offenderContactForm.getEnterPoBoxFields()) {
			ZipCode poBoxZipCode;
			City poBoxCity;
			if (offenderContactForm.getPoBoxFields().getNewCity() != null
					&& offenderContactForm.getPoBoxFields().getNewCity()) {
				if (mailingCity != null
							&& mailingCity.getName().equals(
								offenderContactForm.getMailingAddressFields()
									.getCityName())
							&& (mailingCity.getState().equals(
								offenderContactForm.getMailingAddressFields()
									.getState())
									|| (mailingCity.getState() == null
											&& offenderContactForm
												.getMailingAddressFields()
													.getState() == null))
							&& mailingCity.getCountry().equals(
								offenderContactForm.getMailingAddressFields()
									.getCountry())) {
					poBoxCity = mailingCity;
				} else {
					poBoxCity = this.offenderContactService
							.createCity(
									offenderContactForm.getPoBoxFields()
										.getCityName(),
									offenderContactForm.getPoBoxFields()
										.getState(),
									offenderContactForm.getPoBoxFields()
										.getCountry());
				}
			} else {
				poBoxCity = offenderContactForm.getPoBoxFields()
						.getCity();
			}
			if (offenderContactForm.getPoBoxFields()
						.getNewZipCode() != null
					&& offenderContactForm.getPoBoxFields()
						.getNewZipCode()) {
				if (mailingZipCode != null
						&& mailingZipCode.getValue().equals(
								offenderContactForm.getPoBoxFields()
									.getZipCodeValue())
						&& mailingZipCode.getExtension().equals(
								offenderContactForm.getPoBoxFields()
									.getZipCodeExtension())
						&& mailingZipCode.getCity().equals(poBoxCity)) {
					poBoxZipCode = mailingZipCode;
				} else {
					poBoxZipCode = this.offenderContactService
							.createZipCode(
								offenderContactForm
									.getPoBoxFields().getZipCodeValue(),
								offenderContactForm
									.getPoBoxFields().getZipCodeExtension(),
								poBoxCity);
				}
			} else {
				poBoxZipCode = offenderContactForm.getPoBoxFields()
						.getZipCode();
			}
			poBox = new PoBox(
					offenderContactForm.getPoBoxFields().getPoBoxValue(),
					poBoxZipCode);
		} else {
			poBox = null;
		}
		if (poBox != null || mailingAddress != null) {
			this.offenderContactService.changeContact(
					offender, mailingAddress, poBox);
		}
		processTelephoneNumberItems(offenderContactForm
				.getTelephoneNumberItems(), offender);
		processOnlineAccountItems(offenderContactForm.getOnlineAccountItems(), 
				offender);
		
		return new ModelAndView(String.format(REDIRECT, offender.getId()));
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for offender contact.
	 * 
	 * @param offender offender
	 * @return action menu for offender contact
	 */
	@RequestMapping(value = "/offenderContactActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	/**
	 * Returns telephone numbers action menu.
	 * 
	 * @return telephone numbers action menu
	 */
	@RequestMapping(value = "/telephoneNumbersActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showTelephoneNumbersActionMenu() {
		return new ModelAndView(TELEPHONE_NUMBERS_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Returns online accounts action menu.
	 * 
	 * @return online accounts action menu
	 */
	@RequestMapping(value = "/onlineAccountsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showOnlineAccountsActionMenu() {
		return new ModelAndView(ONLINE_ACCOUNTS_ACTION_MENU_VIEW_NAME);
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Returns fields to create telephone number.
	 * 
	 * @param itemIndex telephone number item index
	 * @return fields to create telephone number
	 */
	@RequestMapping(value = "/createTelephoneNumber.html",
			method = RequestMethod.GET)
	public ModelAndView createTelephoneNumber(
			@RequestParam(value = "itemIndex", required = true)
				final Integer itemIndex) {
		OffenderContactTelephoneNumberItem telephoneNumberItem
			= new OffenderContactTelephoneNumberItem();
		telephoneNumberItem.setOperation(
				OffenderContactTelephoneNumberOperation.CREATE);
		ModelAndView mav = new ModelAndView(TELEPHONE_NUMBER_ITEM_VIEW_NAME);
		mav.addObject(TELEPHONE_NUMBER_CATEGORIES_MODEL_KEY,
				TelephoneNumberCategory.values());
		mav.addObject(TELEPHONE_NUMBER_ITEM_INDEX_MODEL_KEY, itemIndex);
		mav.addObject(TELEPHONE_NUMBER_ITEM_MODEL_KEY, telephoneNumberItem);
		return mav;
	}
	
	/**
	 * Returns fields to create online account.
	 * 
	 * @param itemIndex online account item index
	 * @return fields to create online account
	 */
	@RequestMapping(value = "/createOnlineAccount.html",
			method = RequestMethod.GET)
	public ModelAndView createOnlineAccount(
			@RequestParam(value = "itemIndex", required = true)
				final Integer itemIndex) {
		OffenderContactOnlineAccountItem onlineAccountItem
			= new OffenderContactOnlineAccountItem();
		onlineAccountItem.setOperation(
				OffenderContactOnlineAccountOperation.CREATE);
		List<OnlineAccountHost> onlineAccountHosts
				= this.offenderContactService
					.findOnlineAccountHosts();
		ModelAndView mav = new ModelAndView(ONLINE_ACCOUNT_ITEM_VIEW_NAME);
		mav.addObject(ONLINE_ACCOUNT_HOSTS_MODEL_KEY, onlineAccountHosts);
		mav.addObject(ONLINE_ACCOUNT_ITEM_INDEX_MODEL_KEY, itemIndex);
		mav.addObject(ONLINE_ACCOUNT_ITEM_MODEL_KEY, onlineAccountItem);
		return mav;
	}
	
	/**
	 * Returns States.
	 * 
	 * @param fieldsName name of fields
	 * @param country country
	 * @return States
	 */
	@RequestMapping(
			value = "/{fieldsName}/findStates.html", method = RequestMethod.GET)
	public ModelAndView findStates(
			@PathVariable(value = "fieldsName")
				final String fieldsName,
			@RequestParam(value = "country", required = true)
				final Country country) {
		List<State> states = this.offenderContactService
				.findStates(country);
		if (MAILING_ADDRESS_FIELDS_NAME.equals(fieldsName)) {
			return this.addressFieldsControllerDelegate
					.showStateOptions(states, MAILING_ADDRESS_FIELDS_NAME);
		} else if (PO_BOX_FIELDS_NAME.equals(fieldsName)) {
			return this.poboxFieldsControllerDelegate
					.showStateOptions(states, PO_BOX_FIELDS_NAME);
		} else {
			throw new UnsupportedOperationException(
					String.format("Fields name not supported %s", fieldsName));
		}
	}
	
	/**
	 * Returns cities.
	 * 
	 * @param fieldsName name of fields
	 * @param state state
	 * @param country country
	 * @return cities
	 */
	@RequestMapping(
			value = "/{fieldsName}/findCities.html", method = RequestMethod.GET)
	public ModelAndView findCities(
			@PathVariable(value = "fieldsName")
				final String fieldsName,
			@RequestParam(value = "state", required = false)
				final State state,
			@RequestParam(value = "country", required = false)
				final Country country) {
		List<City> cities;
		if (state != null
				&& this.offenderContactService.hasStates(
						state.getCountry())) {
			cities = this.offenderContactService.findCitiesByState(state);
		} else if (country != null) {
			cities = this.offenderContactService.findCitiesByCountryWithoutState(country);
		} else {
			throw new IllegalArgumentException("State or country required");
		}
		if (MAILING_ADDRESS_FIELDS_NAME.equals(fieldsName)) {
			return this.addressFieldsControllerDelegate
					.showCityOptions(cities, MAILING_ADDRESS_FIELDS_NAME);
		} else if (PO_BOX_FIELDS_NAME.equals(fieldsName)) {
			return this.poboxFieldsControllerDelegate
					.showCityOptions(cities, PO_BOX_FIELDS_NAME);
		} else {
			throw new UnsupportedOperationException(
					String.format("Fields name not supported %s", fieldsName));
		}
	}
	
	/**
	 * Returns ZIP codes.
	 * 
	 * @param fieldsName name of fields
	 * @param city city
	 * @return ZIP codes
	 */
	@RequestMapping(
			value = "/{fieldsName}/findZipCodes.html",
			method = RequestMethod.GET)
	public ModelAndView findZipCodes(
			@PathVariable(value = "fieldsName")
				final String fieldsName,
			@RequestParam(value = "city", required = true)
				final City city) {
		List<ZipCode> zipCodes
			= this.offenderContactService.findZipCodesByCity(city);
		if (MAILING_ADDRESS_FIELDS_NAME.equals(fieldsName)) {
			return this.addressFieldsControllerDelegate
					.showZipCodeOptions(zipCodes, MAILING_ADDRESS_FIELDS_NAME);
		} else if (PO_BOX_FIELDS_NAME.equals(fieldsName)) {
			return this.poboxFieldsControllerDelegate
					.showZipCodeOptions(zipCodes, PO_BOX_FIELDS_NAME);
		} else {
			throw new UnsupportedOperationException(
					String.format("Fields name not supported %s", fieldsName));
		}
	}
	
	/**
	 * Returns addresses by query.
	 * 
	 * @param query query
	 * @return addresses by query
	 */
	@RequestMapping(
			value = "/findAddressesByQuery.json", method = RequestMethod.GET)
	public ModelAndView findAddressesByQuery(
			@RequestParam(value = "term", required = false)
				final String query) {
		ModelAndView mav = new ModelAndView(ADDRESS_VIEW_NAME);
		List<Address> addresses;
		if (StringUtility.hasContent(query)) {
			addresses
				= this.offenderContactService.findAddressesByQuery(query);
		} else {
			addresses = Collections.emptyList();
		}
		mav.addObject(ADDRESSES_MODEL_KEY, addresses);
		return mav;
	}
	
	/* Helpers */
	
	/**
	 * Prepares the model and view
	 * @param offenderContactForm offender contact form
	 * @param offender offender
	 * @return model and view to be displayed
	 */
	private ModelAndView prepareEditMav(
			final OffenderContactForm offenderContactForm,
			final Offender offender) {
		State homeState = this.offenderContactService.findHomeState();
		if (offenderContactForm.getPoBoxFields() == null) {
			if (homeState != null) {
				PoBoxFields poBoxFields = new PoBoxFields();
				 poBoxFields.setState(homeState);
				 poBoxFields.setCountry(homeState.getCountry());
				 offenderContactForm.setPoBoxFields(poBoxFields);
			}
		}
		
		if (offenderContactForm.getMailingAddressFields() == null) {
			if (homeState != null) {
				 AddressFields addressFields = new AddressFields();
				 addressFields.setState(homeState);
				 addressFields.setCountry(homeState.getCountry());
				 offenderContactForm.setMailingAddressFields(addressFields);			 
			}
		}
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(OFFENDER_CONTACT_FORM_MODEL_KEY, offenderContactForm);
		
		int offenderContactTelephoneNumberItemIndex;
		if (offenderContactForm.getTelephoneNumberItems() != null) {
			offenderContactTelephoneNumberItemIndex
				= offenderContactForm.getTelephoneNumberItems().size();
		} else {
			offenderContactTelephoneNumberItemIndex = 0;
		}
		mav.addObject(TELEPHONE_NUMBER_ITEM_INDEX_MODEL_KEY,
				offenderContactTelephoneNumberItemIndex);
		mav.addObject(TELEPHONE_NUMBER_CATEGORIES_MODEL_KEY,
				TelephoneNumberCategory.values());
		int offenderContactOnlineAccountItemIndex;
		if (offenderContactForm.getOnlineAccountItems() != null) {
			offenderContactOnlineAccountItemIndex
				= offenderContactForm.getOnlineAccountItems().size();
		} else {
			offenderContactOnlineAccountItemIndex = 0;
		}
		mav.addObject(ONLINE_ACCOUNT_ITEM_INDEX_MODEL_KEY,
				offenderContactOnlineAccountItemIndex);
		List<OnlineAccountHost> onlineAccountHosts
			= this.offenderContactService.findOnlineAccountHosts();
		mav.addObject(ONLINE_ACCOUNT_HOSTS_MODEL_KEY, onlineAccountHosts);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		List<Country> countries = this.offenderContactService
				.findCountries();
		List<State> mailingStates;
		List<City> mailingCities;
		List<ZipCode> mailingZipCodes;
		if (offenderContactForm.getMailingAddressFields() != null
				&& offenderContactForm.getMailingAddressFields()
					.getCountry() != null) {
			mailingStates = this.offenderContactService
					.findStates(offenderContactForm.getMailingAddressFields()
							.getCountry());
			if (offenderContactForm.getMailingAddressFields()
					.getState() != null) {
				mailingCities = this.offenderContactService
						.findCitiesByState(
								offenderContactForm.getMailingAddressFields()
									.getState());
			} else {
				mailingCities = this.offenderContactService
						.findCitiesByCountryWithoutState(
								offenderContactForm.getMailingAddressFields()
									.getCountry());
			}
			if (offenderContactForm.getMailingAddressFields()
					.getCity() != null) {
				mailingZipCodes = this.offenderContactService
						.findZipCodesByCity(
								offenderContactForm.getMailingAddressFields()
									.getCity());
			} else {
				mailingZipCodes = Collections.emptyList();
			}
		} else {
			mailingStates = Collections.emptyList();
			mailingCities = Collections.emptyList();
			mailingZipCodes = Collections.emptyList();
		}
		this.addressFieldsControllerDelegate.prepareEditAddressFields(
			mav.getModelMap(), countries, mailingStates, mailingCities, 
			mailingZipCodes, MAILING_ADDRESS_FIELDS_NAME);
		mav.addObject(
				ALLOW_RESIDENCE_AT_MAILING_ADDRESS_TOGGLE_KEY,
				this.getAllowResidenceAtMailingAddress());
		List<State> poBoxStates;
		List<City> poBoxCities;
		List<ZipCode> poBoxZipCodes;
		if (offenderContactForm.getPoBoxFields() != null
				&& offenderContactForm.getPoBoxFields()
					.getCountry() != null) {
			poBoxStates = this.offenderContactService
					.findStates(offenderContactForm.getPoBoxFields()
							.getCountry());
			if (offenderContactForm.getPoBoxFields()
					.getState() != null) {
				poBoxCities = this.offenderContactService
						.findCitiesByState(
								offenderContactForm.getPoBoxFields()
									.getState());
			} else {
				poBoxCities = this.offenderContactService
						.findCitiesByCountryWithoutState(
								offenderContactForm.getPoBoxFields()
									.getCountry());
			}
			if (offenderContactForm.getPoBoxFields().getCity() != null) {
				poBoxZipCodes = this.offenderContactService
						.findZipCodesByCity(
								offenderContactForm.getPoBoxFields()
									.getCity());
			} else {
				poBoxZipCodes = Collections.emptyList();
			}
		} else {
			poBoxStates = Collections.emptyList();
			poBoxCities = Collections.emptyList();
			poBoxZipCodes = Collections.emptyList();
		}
		this.poboxFieldsControllerDelegate.prepareEditPoBoxFields(
				mav.getModelMap(), countries, poBoxStates, poBoxCities,
				poBoxZipCodes, PO_BOX_FIELDS_NAME);
		return mav;
	}
	
	// Prepares model and view to redisplay screen
	private ModelAndView prepareRedisplayMav(
			final OffenderContactForm offenderContactForm,
			final BindingResult result, final Offender offender) {
		ModelAndView mav = this.prepareEditMav(
				offenderContactForm, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ OFFENDER_CONTACT_FORM_MODEL_KEY, result);
		return mav;
	}
	
	/* Process the telephone number items */
	private void processTelephoneNumberItems(
			final List<OffenderContactTelephoneNumberItem> items, 
			final Offender offender) throws TelephoneNumberExistsException, ContactExistsException {
		if (items != null) {
			for (OffenderContactTelephoneNumberItem telephoneNumberItem 
					: items) {
				if (OffenderContactTelephoneNumberOperation.CREATE.equals(
						telephoneNumberItem.getOperation())) {
					this.offenderContactService.createTelephoneNumber(offender, 
							telephoneNumberItem.getFields().getValue(), 
							telephoneNumberItem.getFields().getExtension(), 
							resolveCheckBoxValue(telephoneNumberItem.getFields()
									.getPrimary()), 
							telephoneNumberItem.getFields().getCategory(),
							resolveCheckBoxValue(telephoneNumberItem.getFields()
									.getActive()));
				} else if (OffenderContactTelephoneNumberOperation.UPDATE
						.equals(telephoneNumberItem.getOperation())) {
					this.offenderContactService.updateTelephoneNumber(
							telephoneNumberItem.getTelephoneNumber(), 
							telephoneNumberItem.getFields().getValue(), 
							telephoneNumberItem.getFields().getExtension(), 
							resolveCheckBoxValue(telephoneNumberItem.getFields()
									.getPrimary()), 
							telephoneNumberItem.getFields().getCategory(),
							resolveCheckBoxValue(telephoneNumberItem.getFields()
									.getActive()));
				} else if (OffenderContactTelephoneNumberOperation.REMOVE
						.equals(telephoneNumberItem.getOperation())) {
					this.offenderContactService.removeTelephoneNumber(
							telephoneNumberItem.getTelephoneNumber());
				}
			}
		}
	}
		
	/* Process online account items */
	private void processOnlineAccountItems(
			final List<OffenderContactOnlineAccountItem> items, 
			final Offender offender) throws OnlineAccountExistsException {
		if (items != null) {
			for (OffenderContactOnlineAccountItem accountItem : items) {
				if (OffenderContactOnlineAccountOperation.CREATE.equals(
						accountItem.getOperation())) {
					this.offenderContactService.createOnlineAccount(offender, 
							accountItem.getFields().getName(), 
							accountItem.getFields().getHost(), 
							resolveCheckBoxValue(accountItem.getFields()
									.getPrimary()),
							resolveCheckBoxValue(accountItem.getFields()
									.getActive()));
				} else if (OffenderContactOnlineAccountOperation.UPDATE.equals(
						accountItem.getOperation())) {
					this.offenderContactService.updateOnlineAccount(
							accountItem.getOnlineAccount(), 
							accountItem.getFields().getName(), 
							accountItem.getFields().getHost(), 
							resolveCheckBoxValue(accountItem.getFields()
									.getPrimary()),
							resolveCheckBoxValue(accountItem.getFields()
									.getActive()));
				} else if (OffenderContactOnlineAccountOperation.REMOVE.equals(
						accountItem.getOperation())) {
					this.offenderContactService.removeOnlineAccount(
							accountItem.getOnlineAccount());
				}
			}
		}
	}
	
	// Returns true if value is true; false otherwise
	private Boolean resolveCheckBoxValue(final Boolean value) {
		if (value != null && value) {
			return true;
		} else {
			return false;
		}
	}
	
	/* Feature toggle lookup helpers. */
	
	// Returns whether residences at mailing address are allowed
	private boolean getAllowResidenceAtMailingAddress() {
		return this.featureToggles.get(
						"offendercontact", "allowResidenceAtMailingAddress");
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/offenderContactDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_CONTACT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportOffenderContactDetails(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(OFFENDER_CONTACT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				OFFENDER_CONTACT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Init binders. */

	/**
	 * Registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Contact.class,
				this.contactPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Address.class,
				this.addressPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Country.class,
				this.countryPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(City.class,
				this.cityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ZipCode.class,
				this.zipCodePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(OnlineAccountHost.class,
				this.onlineAccountHostPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(TelephoneNumber.class,
				this.telephoneNumberPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(OnlineAccount.class,
				this.onlineAccountPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}