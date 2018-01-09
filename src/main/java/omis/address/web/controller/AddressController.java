package omis.address.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetDirection;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.service.AddressService;
import omis.address.web.form.AddressForm;
import omis.address.web.validator.AddressFormValidator;
import omis.beans.factory.PropertyEditorFactory;
import omis.country.domain.Country;
import omis.exception.DuplicateEntityFoundException;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for addresses.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 7, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/address")
@PreAuthorize("hasRole('USER')")
public class AddressController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "address/list";
	
	private static final String EDIT_VIEW_NAME = "address/edit";
	
	private static final String STATE_OPTIONS_VIEW_NAME
		= "region/includes/stateOptions";

	private static final String CITY_OPTIONS_VIEW_NAME
		= "region/includes/cityOptions";
	
	private static final String ZIP_CODE_OPTIONS_VIEW_NAME
		= "address/includes/zipCodeOptions";
	
	/* Action menu views names. */
	
	private static final String ADDRESSES_ACTION_MENU_VIEW_NAME
		= "address/includes/addressesActionMenu";
	
	private static final String ADDRESS_ACTION_MENU_VIEW_NAME
		= "address/includes/addressActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT = "redirect:/address/list.html";
	
	/* Model keys. */
	
	private static final String ADDRESSES_MODEL_KEY = "addresses";

	private static final String ADDRESS_FORM_MODEL_KEY = "addressForm";
	
	private static final String ADDRESS_MODEL_KEY = "address";

	private static final String STREET_SUFFIXES_MODEL_KEY = "streetSuffixes";

	private static final String UNIT_DESIGNATORS_MODEL_KEY
		= "unitDesignators";
	
	private static final String ZIP_CODES_MODEL_KEY = "zipCodes";
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME = "omis.address.msgs.form";
	
	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_MESSAGE_KEY
		= "address.exists";

	private static final String COUNTRIES_MODEL_KEY = "countries";

	private static final String STATES_MODEL_KEY = "states";
	
	private static final String CITIES_MODEL_KEY = "cities";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("addressService")
	private AddressService addressService;

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("addressPropertyEditorFactory")
	private PropertyEditorFactory addressPropertyEditorFactory;

	@Autowired
	@Qualifier("streetDirectionPropertyEditorFactory")
	private PropertyEditorFactory streetDirectionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("streetSuffixPropertyEditorFactory")
	private PropertyEditorFactory streetSuffixPropertyEditorFactory;
	
	@Autowired
	@Qualifier("addressUnitDesignatorPropertyEditorFactory")
	private PropertyEditorFactory addressUnitDesignatorPropertyEditorFactory;
	
	@Autowired
	@Qualifier("zipCodePropertyEditorFactory")
	private PropertyEditorFactory zipCodePropertyEditorFactory;
	
	@Autowired
	@Qualifier("countryPropertyEditorFactory")
	private PropertyEditorFactory countryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("statePropertyEditorFactory")
	private PropertyEditorFactory statePropertyEditorFactory;
	
	@Autowired
	@Qualifier("cityPropertyEditorFactory")
	private PropertyEditorFactory cityPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("addressFormValidator")
	private AddressFormValidator addressFormValidator;
	
	/* Constructor. */
	
	/** Instantiates a controller for address. */
	public AddressController() {
		// Default instantiation
	}
	
	/* URL invokable methods.*/
	
	/**
	 * Lists addresses.
	 * 
	 * @return screen to list addresses
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADDRESS_LIST') or hasRole('ADMIN')")
	public ModelAndView list() {
		List<Address> addresses = this.addressService.findAll();
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(ADDRESSES_MODEL_KEY, addresses);
		return mav;
	}

	/**
	 * Shows form to create a new address.
	 * 
	 * @return form to create address
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADDRESS_CREATE') or hasRole('ADMIN')")
	public ModelAndView create() {
		return this.prepareEditMav(new AddressForm());
	}
	
	/**
	 * Shows form to update an existing address.
	 * 
	 * @param address address to edit
	 * @return form to update address
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADDRESS_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "address", required = true)
				final Address address) {
		AddressForm addressForm = new AddressForm();
		addressForm.setValue(address.getValue());
		addressForm.setCountry(address.getZipCode().getCity().getCountry());
		addressForm.setState(address.getZipCode().getCity().getState());
		addressForm.setCity(address.getZipCode().getCity());
		addressForm.setZipCode(address.getZipCode());
		ModelAndView mav = this.prepareEditMav(addressForm);
		mav.addObject(ADDRESS_MODEL_KEY, address);
		return mav;
	}
	
	// Prepares edit form
	private ModelAndView prepareEditMav(final AddressForm addressForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(ADDRESS_FORM_MODEL_KEY, addressForm);
		List<StreetSuffix> streetSuffixes = this.addressService
				.findStreetSuffixes();
		mav.addObject(STREET_SUFFIXES_MODEL_KEY, streetSuffixes);
		List<AddressUnitDesignator> unitDesignators = this.addressService
				.findUnitDesignators();
		mav.addObject(UNIT_DESIGNATORS_MODEL_KEY, unitDesignators);
		List<Country> countries = this.addressService.findCountries();
		mav.addObject(COUNTRIES_MODEL_KEY, countries);
		if (addressForm.getCountry() != null) {
			List<State> states = this.addressService.findStatesByCountry(
					addressForm.getCountry());
			mav.addObject(STATES_MODEL_KEY, states);
		}
		if (addressForm.getState() != null) {
			List<City> cities = this.addressService
					.findCitiesByState(addressForm.getState());
			mav.addObject(CITIES_MODEL_KEY, cities);
		} else if (addressForm.getCountry() != null
				&& !this.addressService.hasStates(addressForm.getCountry())) {
			List<City> cities = this.addressService
					.findCitiesByCountry(addressForm.getCountry());
			mav.addObject(CITIES_MODEL_KEY, cities);
		}
		if (addressForm.getCity() != null) {
			List<ZipCode> zipCodes = this.addressService
					.findZipCodesByCity(addressForm.getCity());
			mav.addObject(ZIP_CODES_MODEL_KEY, zipCodes);
		} else if (addressForm.getState() != null) {
			List<ZipCode> zipCodes = this.addressService
					.findZipCodesByState(addressForm.getState());
			mav.addObject(ZIP_CODES_MODEL_KEY, zipCodes);
		}
		return mav;
	}
	
	/**
	 * Saves a new address.
	 * 
	 * @param addressForm address form
	 * @param result binding result
	 * @return redirect to list addresses
	 * @throws DuplicateEntityFoundException if address exists
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADDRESS_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			final AddressForm addressForm, final BindingResult result)
					throws DuplicateEntityFoundException {
		this.addressFormValidator.validate(addressForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(addressForm, result);
		}
		this.addressService.create(addressForm.getValue(),
				addressForm.getZipCode());
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Updates an existing address.
	 * 
	 * @param address address to update
	 * @param addressForm address form
	 * @param result binding result
	 * @return redirect to list addresses
	 * @throws DuplicateEntityFoundException if address exists
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADDRESS_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "address", required = true)
				final Address address,
			final AddressForm addressForm, final BindingResult result)
					throws DuplicateEntityFoundException {
		this.addressFormValidator.validate(addressForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(addressForm, result);
			mav.addObject(ADDRESS_MODEL_KEY, address);
			return mav;
		}
		this.addressService.update(
				address, addressForm.getValue(),
				addressForm.getZipCode());
		return new ModelAndView(LIST_REDIRECT);
	}
	
	// Prepares form for redisplay
	private ModelAndView prepareRedisplayMav(
			final AddressForm addressForm, final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(addressForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + ADDRESS_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	/**
	 * Removes an address.
	 * 
	 * @param address address to remove
	 * @return redirect to list addresses
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADDRESS_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "address", required = true)
				final Address address) {
		this.addressService.remove(address);
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Returns options for States by country.
	 * 
	 * @param country country
	 * @return options for States by country
	 */
	@RequestMapping(value = "/findStatesByCountry.html",
			method = RequestMethod.GET)
	public ModelAndView findStatesByCountry(
			@RequestParam(value = "country", required = true)
				final Country country) {
		List<State> states = this.addressService
				.findStatesByCountry(country);
		ModelAndView mav = new ModelAndView(STATE_OPTIONS_VIEW_NAME);
		mav.addObject(STATES_MODEL_KEY, states);
		return mav;
	}
	
	/**
	 * Returns options for cities by country.
	 * 
	 * <p>By default, if the country has States, no cities will be returned.
	 * To return cities if the country has States, {@code allowIfHasStates}
	 * must be set to {@code true}.
	 * 
	 * @param country country for which to return cities
	 * @param allowIfHasStates whether to return cities when country has States
	 * @return options for cities by country
	 */
	@RequestMapping(value = "/findCitiesByCountry.html",
			method = RequestMethod.GET)
	public ModelAndView findCititesByCountry(
			@RequestParam(value = "country", required = true)
				final Country country,
			@RequestParam(value = "allowIfHasStates", required = false)
				final Boolean allowIfHasStates) {
		ModelAndView mav = new ModelAndView(CITY_OPTIONS_VIEW_NAME);
		if ((allowIfHasStates != null && allowIfHasStates)
				|| !this.addressService.hasStates(country)) {
			List<City> cities = this.addressService
					.findCitiesByCountry(country);
			mav.addObject(CITIES_MODEL_KEY, cities);
		}
		return mav;
	}
	
	/**
	 * Returns options for cities by State.
	 * 
	 * @param state State
	 * @return options for cities by State
	 */
	@RequestMapping(value = "/findCitiesByState.html",
			method = RequestMethod.GET)
	public ModelAndView findCitiesByState(
			@RequestParam(value = "state", required = true)
				final State state) {
		List<City> cities = this.addressService
				.findCitiesByState(state);
		ModelAndView mav = new ModelAndView(CITY_OPTIONS_VIEW_NAME);
		mav.addObject(CITIES_MODEL_KEY, cities);
		return mav;
	}
	
	/**
	 * Returns options for ZIP codes by State.
	 * 
	 * @param state State
	 * @return options for ZIP codes by State
	 */
	@RequestMapping(value = "/findZipCodesByState.html",
			method = RequestMethod.GET)
	public ModelAndView findZipCodesByState(
			@RequestParam(value = "state", required = true)
				final State state) {
		List<ZipCode> zipCodes = this.addressService
				.findZipCodesByState(state);
		ModelAndView mav = new ModelAndView(ZIP_CODE_OPTIONS_VIEW_NAME);
		mav.addObject(ZIP_CODES_MODEL_KEY, zipCodes);
		return mav;
	}
	
	/**
	 * Returns options for ZIP codes by city.
	 * 
	 * @param state State
	 * @return options for ZIP codes by city
	 */
	@RequestMapping(value = "/findZipCodesByCity.html",
			method = RequestMethod.GET)
	public ModelAndView findZipCodesByCity(
			@RequestParam(value = "city", required = true)
				final City city) {
		List<ZipCode> zipCodes = this.addressService
				.findZipCodesByCity(city);
		ModelAndView mav = new ModelAndView(ZIP_CODE_OPTIONS_VIEW_NAME);
		mav.addObject(ZIP_CODES_MODEL_KEY, zipCodes);
		return mav;
	}
	
	/* Actions menus. */
	
	/**
	 * Shows action menu for addresses.
	 * 
	 * @param address addresses
	 * @return addresses action menu
	 */
	@RequestMapping(value = "/addressesActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showAddressesActionMenu(
			@RequestParam(value = "address", required = false)
				final Address address) {
		ModelAndView mav = new ModelAndView(ADDRESSES_ACTION_MENU_VIEW_NAME);
		mav.addObject(ADDRESS_MODEL_KEY, address);
		return mav;
	}
	
	/**
	 * Shows action menu for address.
	 * 
	 * @param address address
	 * @return address action menu
	 */
	@RequestMapping(value = "/addressActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showAddressActionMenu(
			@RequestParam(value = "address", required = true)
				final Address address) {
		ModelAndView mav = new ModelAndView(ADDRESS_ACTION_MENU_VIEW_NAME);
		mav.addObject(ADDRESS_MODEL_KEY, address);
		return mav;
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles {@code DuplicateEntityFoundException}.
	 * 
	 * @param duplicateEntityFoundException exception thrown
	 * @return screen to handle {@code DuplicateEntityFoundException}
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException duplicateEntityFoundException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(DUPLICATE_ENTITY_FOUND_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, duplicateEntityFoundException);
	}
	
	/* Init binders */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Address.class,
				this.addressPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(StreetDirection.class,
				this.streetDirectionPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(StreetSuffix.class,
				this.streetSuffixPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(AddressUnitDesignator.class,
				this.addressUnitDesignatorPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ZipCode.class,
				this.zipCodePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Country.class,
				this.countryPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(City.class,
				this.cityPropertyEditorFactory.createPropertyEditor());
	}
}