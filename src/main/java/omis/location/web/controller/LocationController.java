package omis.location.web.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.web.controller.delegate.AddressFieldsControllerDelegate;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.LocationService;
import omis.location.web.form.LocationForm;
import omis.location.web.validator.LocationFormValidator;
import omis.organization.domain.Organization;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Controller for locations.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.1 (July 29, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/location")
@PreAuthorize("hasRole('USER')")
public class LocationController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "location/list";

	private static final String EDIT_VIEW_NAME = "location/edit";
	
	private static final String ADDRESSES_VIEW_NAME = "address/json/addresses";
	
	/* Model keys. */
	
	private static final String ORGANIZATION_MODEL_KEY = "organization";

	private static final String LOCATIONS_MODEL_KEY = "locations";

	private static final String LOCATION_FORM_MODEL_KEY = "locationForm";
	
	private static final String LOCATION_MODEL_KEY = "location";
	
	private static final String ADDRESSES_MODEL_KEY = "addresses";

	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:list.html?organization=%d";

	/* Property names. */
	
	private static final String ADDRESS_FIELDS_PROPERTY_NAME = "addressFields";

	/* Services. */
	
	@Autowired
	@Qualifier("locationService")
	private LocationService locationService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("locationPropertyEditorFactory")
	private PropertyEditorFactory locationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("organizationPropertyEditorFactory")
	private PropertyEditorFactory organizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("addressPropertyEditorFactory")
	private PropertyEditorFactory addressPropertyEditorFactory;
	
	@Autowired
	@Qualifier("streetSuffixPropertyEditorFactory")
	private PropertyEditorFactory streetSuffixPropertyEditorFactory;
	
	@Autowired
	@Qualifier("addressUnitDesignatorPropertyEditorFactory")
	private PropertyEditorFactory addressUnitDesignatorPropertyEditorFactory;
	
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
	@Qualifier("zipCodePropertyEditorFactory")
	private PropertyEditorFactory zipCodePropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("addressFieldsControllerDelegate")
	private AddressFieldsControllerDelegate addressFieldsControllerDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("locationFormValidator")
	private LocationFormValidator locationFormValidator;
	
	/* Constructors. */
	
	/** Instantiates controller for locations. */
	public LocationController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows a list of locations.
	 * 
	 * @param organization optional organization the locations of which to
	 * show
	 * @return model and view to show locations
	 */
	@PreAuthorize("hasRole('LOCATION_LIST') or hasRole('ADMIN')")
	@RequestMapping("/list.html")
	public ModelAndView list(
			@RequestParam(value = "organization", required = false)
				final Organization organization) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<Location> locations;
		if (organization != null) {
			locations = this.locationService.findByOrganization(organization);
			mav.addObject(ORGANIZATION_MODEL_KEY, organization);
		} else {
			locations = this.locationService.findAll();
		}
		mav.addObject(LOCATIONS_MODEL_KEY, locations);
		return mav;
	}
	
	/**
	 * Shows a form to create a location.
	 * 
	 * @return model and view to show form to create location
	 */
	@PreAuthorize("hasRole('LOCATION_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		LocationForm locationForm = new LocationForm();
		locationForm.setAddressOperation(LocationAddressOperation.USE_EXISTING);
		return this.prepareEditMav(locationForm);
	}
	
	/**
	 * Saves a location and redirects to the listing screen.
	 * 
	 * <p>The organization name passed will be looked up. If no organization
	 * with the specified name is found, a new one will be created.
	 * 
	 * @param locationForm form containing location new location property values
	 * @param result binding result
	 * @return model and view to redirect to listing screen
	 * @throws DuplicateEntityFoundException if location exists
	 */
	@PreAuthorize("hasRole('LOCATION_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
				final LocationForm locationForm,
				final BindingResult result)
			throws DuplicateEntityFoundException {
		this.locationFormValidator.validate(locationForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(locationForm, result);
		}
		Organization organization =
				this.locationService
					.findOrganization(locationForm.getOrganizationName());
		if (organization == null) {
			try {
				organization = this.locationService.createOrganization(
					locationForm.getOrganizationName());
			} catch (DuplicateEntityFoundException exception) {
				throw new AssertionError(exception);
			}
		}
		Address address;
		if (LocationAddressOperation.CREATE_NEW.equals(
				locationForm.getAddressOperation())) {
			City city;
			if (locationForm.getAddressFields().getNewCity() != null
					&& locationForm.getAddressFields().getNewCity()) {
				city = this.locationService.createCity(
						locationForm.getAddressFields().getCityName(),
						locationForm.getAddressFields().getState(),
						locationForm.getAddressFields().getCountry());
			} else {
				city = locationForm.getAddressFields().getCity();
			}
			ZipCode zipCode;
			if (locationForm.getAddressFields().getNewZipCode() != null
					&& locationForm.getAddressFields().getNewZipCode()) {
				zipCode = this.locationService.createZipCode(
						locationForm.getAddressFields().getZipCodeValue(),
						locationForm.getAddressFields().getZipCodeExtension(),
						city);
			} else {
				zipCode = locationForm.getAddressFields().getZipCode();
			}
			address = this.locationService.createAddress(
					locationForm.getAddressFields().getValue(),	zipCode);
		} else if (LocationAddressOperation.USE_EXISTING.equals(
				locationForm.getAddressOperation())) {
			address = locationForm.getAddress();
		} else {
			throw new UnsupportedOperationException(
					String.format("Unsupported address operation: %s",
							locationForm.getAddressOperation()));
		}
		Location location = this.locationService.create(organization,
				new DateRange(
						locationForm.getStartDate(),
						locationForm.getEndDate()),
				address);
		return new ModelAndView(String.format(LIST_REDIRECT,
				location.getOrganization().getId()));
	}
	
	/**
	 * Shows a form to edit an existing location.
	 * 
	 * @param location location to edit
	 * @return model and view to edit existing location
	 */
	@PreAuthorize("hasRole('LOCATION_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "location", required = true)
				final Location location) {
		LocationForm locationForm = new LocationForm();
		locationForm.setOrganizationName(location.getOrganization().getName());
		if (location.getDateRange() != null) {
			locationForm.setStartDate(location.getDateRange().getStartDate());
			locationForm.setEndDate(location.getDateRange().getEndDate());
		}
		locationForm.setAddressOperation(LocationAddressOperation.USE_CURRENT);
		locationForm.setAddress(location.getAddress());
		ModelAndView mav = this.prepareEditMav(locationForm);
		mav.addObject(LOCATION_MODEL_KEY, location);
		return mav;
	}
	
	/**
	 * Updates a location and redirects to the listing screen.
	 * 
	 * <p>The organization name passed will be looked up. If no organization
	 * with the specified name is found, a new one will be created.
	 * 
	 * @param location location to update
	 * @param locationForm form containing location updated location property
	 * values
	 * @param result binding result
	 * @return model and view to redirect to listing screen
	 * @throws DuplicateEntityFoundException if location exists
	 */
	@PreAuthorize("hasRole('LOCATION_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "location", required = true)
				final Location location,
			final LocationForm locationForm,
			final BindingResult result)
					throws DuplicateEntityFoundException {
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(locationForm, result);
			mav.addObject(LOCATION_MODEL_KEY, location);
			return mav;
		}
		Organization organization =
				this.locationService
					.findOrganization(locationForm.getOrganizationName());
		if (organization == null) {
			try {
				organization = this.locationService.createOrganization(
					locationForm.getOrganizationName());
			} catch (DuplicateEntityFoundException exception) {
				throw new AssertionError(exception);
			}
		}
		Address address;
		if (LocationAddressOperation.CREATE_NEW.equals(
				locationForm.getAddressOperation())) {
			City city;
			if (locationForm.getAddressFields().getNewCity() != null
					&& locationForm.getAddressFields().getNewCity()) {
				city = this.locationService.createCity(
						locationForm.getAddressFields().getCityName(),
						locationForm.getAddressFields().getState(),
						locationForm.getAddressFields().getCountry());
			} else {
				city = locationForm.getAddressFields().getCity();
			}
			ZipCode zipCode;
			if (locationForm.getAddressFields().getNewZipCode() != null
					&& locationForm.getAddressFields().getNewZipCode()) {
				zipCode = this.locationService.createZipCode(
						locationForm.getAddressFields().getZipCodeValue(),
						locationForm.getAddressFields().getZipCodeExtension(),
						city);
			} else {
				zipCode = locationForm.getAddressFields().getZipCode();
			}
			address = this.locationService.createAddress(
					locationForm.getAddressFields().getValue(),
					zipCode);
		} else if (LocationAddressOperation.USE_CURRENT.equals(
				locationForm.getAddressOperation())) {
			address = location.getAddress();
		} else if (LocationAddressOperation.USE_EXISTING.equals(
				locationForm.getAddressOperation())) {
			address = locationForm.getAddress();
		} else {
			throw new UnsupportedOperationException(
					String.format("Unsupported address operation: %s",
							locationForm.getAddressOperation()));
		}
		Location updatedLocation
			= this.locationService.update(location, organization,
					new DateRange(
							locationForm.getStartDate(),
							locationForm.getEndDate()),
					address);
		return new ModelAndView(String.format(LIST_REDIRECT,
				updatedLocation.getOrganization().getId()));
	}
	
	/**
	 * Removes a location.
	 * 
	 * @param location location
	 * @return model and view to redirect to listing screen
	 */
	@PreAuthorize("hasRole('LOCATION_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "location", required = true)
				final Location location) {
		Long organizationId = location.getOrganization().getId();
		this.locationService.remove(location);
		return new ModelAndView(String.format(LIST_REDIRECT, organizationId));
	}
	
	/**
	 * Returns a list of sorted organizations matching the partial name.
	 * @param partialName partial name
	 * @return model and view to show sorted organizations
	 */
	@RequestMapping(
			value = "/findOrganizationsByPartialName.json",
			method = RequestMethod.GET)
	public ModelAndView findOrganizationByParitalName(
			@RequestParam(value = "term") final String partialName) {
		ModelAndView mav = new ModelAndView("organization/json/names");
		List<Organization> organizations = this.locationService
				.findOrganizationsByPartialName(partialName);
		mav.addObject("organizations", organizations);
		return mav;
	}
	
	/**
	 * Returns States by country.
	 * 
	 * @param country country
	 * @return States by country
	 */
	@RequestMapping(value = "/findStates.html", method = RequestMethod.GET)
	public ModelAndView findStates(
			@RequestParam(value = "country", required = true)
				final Country country) {
		List<State> states = this.locationService.findStates(country);
		return this.addressFieldsControllerDelegate
				.showStateOptions(states, ADDRESS_FIELDS_PROPERTY_NAME);
	}
	
	/**
	 * Returns cities by State.
	 * 
	 * @param state State
	 * @return cities by State
	 */
	@RequestMapping(value = "/findCities.html", method = RequestMethod.GET)
	public ModelAndView findCitiesByState(
			@RequestParam(value = "state", required = true)
				final State state,
			@RequestParam(value = "country", required = true)
				final Country country) {
		List<City> cities;
		if (state != null) {
			cities = this.locationService.findCitiesByState(state);
		} else if (country != null) {
			cities = this.locationService.findCitiesByCountry(country);
		} else {
			cities = Collections.emptyList();
		}
		return this.addressFieldsControllerDelegate
				.showCityOptions(cities, ADDRESS_FIELDS_PROPERTY_NAME);
	}
	
	/**
	 * Returns ZIP codes.
	 * 
	 * @param city city
	 * @return ZIP codes
	 */
	@RequestMapping(value = "/findZipCodes.html", method = RequestMethod.GET)
	public ModelAndView findZipCode(
			@RequestParam(value = "city", required = true)
				final City city) {
		List<ZipCode> zipCodes
			= this.locationService.findZipCodes(city);
		return this.addressFieldsControllerDelegate
				.showZipCodeOptions(zipCodes, ADDRESS_FIELDS_PROPERTY_NAME);
	}
	
	/**
	 * Returns addresses by query.
	 * 
	 * @param query query
	 * @return addresses by query
	 */
	@RequestMapping(value = "/findAddresses.json", method = RequestMethod.GET)
	public ModelAndView findAddresses(
			@RequestParam(value = "term", required = false)
				final String query) {
		List<Address> addresses;
		if (query != null && !query.isEmpty()) {
			addresses = this.locationService.findAddressesByQuery(query);
		} else {
			addresses = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView(ADDRESSES_VIEW_NAME);
		mav.addObject(ADDRESSES_MODEL_KEY, addresses);
		return mav;
	}
	
	/* Helper methods. */
	
	// Returns model and view for form to edit locations
	private ModelAndView prepareEditMav(
			final LocationForm locationForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(LOCATION_FORM_MODEL_KEY, locationForm);
		List<Country> countries = this.locationService.findCountries();
		List<State> states;
		List<City> cities;
		List<ZipCode> zipCodes;
		if (locationForm.getAddressFields() != null) {
			states = this.locationService.findStates(
					locationForm.getAddressFields().getCountry());
			cities = this.locationService.findCitiesByState(
					locationForm.getAddressFields().getState());
			zipCodes = this.locationService.findZipCodes(
					locationForm.getAddressFields().getCity());
		} else {
			states = Collections.emptyList();
			cities = Collections.emptyList();
			zipCodes = Collections.emptyList();
		}
		this.addressFieldsControllerDelegate.prepareEditAddressFields(
			mav.getModelMap(), countries, states, cities, zipCodes, 
			ADDRESS_FIELDS_PROPERTY_NAME);
		return mav;
	}
	
	// Returns model and view to redisplay form to edit locations
	private ModelAndView prepareRedisplayMav(
			final LocationForm locationForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(locationForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + LOCATION_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Location.class, 
				this.locationPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Organization.class,
				this.organizationPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Address.class,
				this.addressPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(StreetSuffix.class,
				this.streetSuffixPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(AddressUnitDesignator.class,
				this.addressUnitDesignatorPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Country.class,
				this.countryPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(State.class,
				this.statePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(City.class,
				this.cityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ZipCode.class,
				this.zipCodePropertyEditorFactory.createPropertyEditor());
	}
}