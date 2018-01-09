package omis.locationterm.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.AddressUnitDesignatorDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DateRangeOutOfBoundsException;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.exception.LocationTermConflictException;
import omis.locationterm.exception.LocationTermExistsAfterException;
import omis.locationterm.exception.LocationTermLockedException;
import omis.locationterm.service.LocationTermService;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Location term service update tests.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"locationTerm", "service"})
public class LocationTermServiceUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;

	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("streetSuffixDelegate")
	private StreetSuffixDelegate streetSuffixDelegate;
	
	@Autowired
	@Qualifier("locationTermDelegate")
	private LocationTermDelegate locationTermDelegate;
	
	@Autowired
	@Qualifier("addressUnitDesignatorDelegate")
	private AddressUnitDesignatorDelegate addressUnitDesignatorDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;

	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate 
			supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("locationTermService")
	private LocationTermService locationTermService;
	
	/* Property editor factory. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Tests. */
	
	/** Test update of location term. 
	 * 
	 * @throws DuplicateEntityFoundException if location term exists
	 * @throws DateRangeOutOfBoundsException if location term is out of bounds
	 * @throws LocationTermConflictException if location term conflicts
	 * @throws LocationTermExistsAfterException if end date is null and other 
	 * location terms exist after the start date
	 * @throws LocationTermLockedException if location term is locked
	 * @throws OffenderNotUnderSupervisionException if offender is not under 
	 * supervision
	 */
	public void testUpdateLocation()
			throws DuplicateEntityFoundException,
			LocationTermConflictException,
			DateRangeOutOfBoundsException,
			LocationTermExistsAfterException,
			LocationTermLockedException, 
			OffenderNotUnderSupervisionException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address megaJailAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		Organization megaJailOrg = this.organizationDelegate
				.create("Mega Jail", "MEGA", null);
		Organization megaPnpOrg = this.organizationDelegate
				.create("Mega PnP", "MPNP", null);
		Location megaJailLoc = this.locationDelegate.create(
				megaJailOrg, null, megaJailAddress);
		Location megaPnpLoc = this.locationDelegate
				.create(megaPnpOrg, null, megaJailAddress);
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2016");
		DateRange dateRange = new DateRange(this.parseDateText("09/12/2001"), 
				this.parseDateText("12/03/2016"));
		SupervisoryOrganization supervisoryOrganization 
				= this.supervisoryOrganizationDelegate.create("SO", "SO", null);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
				= this.supervisoryOrganizationTermDelegate.create(offender,
						dateRange, supervisoryOrganization);
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Status", "S", false, (short) 1, true);
		CorrectionalStatusTerm correctionalStatusTerm 
				= this.correctionalStatusTermDelegate.create(offender, 
						dateRange, correctionalStatus );
		this.placementTermDelegate.create(offender, dateRange, 
				supervisoryOrganizationTerm, correctionalStatusTerm, null, null, 
				false);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, megaJailLoc, startDate, endDate, false);
		
		// Action
		locationTerm = this.locationTermService.update(locationTerm, megaPnpLoc,
				new DateRange(startDate, endDate));

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("location", megaPnpLoc)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.performAssertions(locationTerm);
	}
	
	/** 
	 * Tests whether business exception {@code LocationTermConflictException} is
	 * thrown. 
	 * 
	 * @throws DuplicateEntityFoundException if location term exists
	 * @throws DateRangeOutOfBoundsException if location term is out of bounds
	 * @throws LocationTermConflictException if location term conflicts
	 * @throws LocationTermExistsAfterException if end date is null and other 
	 * location terms exist after the start date
	 * @throws LocationTermLockedException if location term is locked
	 * @throws OffenderNotUnderSupervisionException if offender is not under 
	 * supervision
	 */
	@Test(expectedExceptions = {LocationTermConflictException.class})
	public void testLocationTermConflictException()
			throws DuplicateEntityFoundException,
			LocationTermConflictException,
			DateRangeOutOfBoundsException,
			LocationTermExistsAfterException,
			LocationTermLockedException,
			OffenderNotUnderSupervisionException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address megaJailAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		Organization megaJailOrg = this.organizationDelegate
				.create("Mega Jail", "MEGA", null);
		Organization megaPnpOrg = this.organizationDelegate
				.create("Mega PnP", "MPNP", null);
		Location megaJailLoc = this.locationDelegate.create(
				megaJailOrg, null, megaJailAddress);
		Location megaPnpLoc = this.locationDelegate
				.create(megaPnpOrg, null, megaJailAddress);
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2016");
		DateRange dateRange = new DateRange(startDate, 
				this.parseDateText("1/1/2018"));
		SupervisoryOrganization supervisoryOrganization 
				= this.supervisoryOrganizationDelegate.create("SO", "SO", null);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
				= this.supervisoryOrganizationTermDelegate.create(offender,
						dateRange, supervisoryOrganization);
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Status", "S", false, (short) 1, true);
		CorrectionalStatusTerm correctionalStatusTerm 
				= this.correctionalStatusTermDelegate.create(offender, 
						dateRange, correctionalStatus );
		this.placementTermDelegate.create(offender, dateRange, 
				supervisoryOrganizationTerm, correctionalStatusTerm, null, null, 
				false);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, megaJailLoc, startDate, endDate, false);
		
		Date conflictStartDate = endDate;
		Date conflictEndDate = this.parseDateText("1/1/2017");
		this.locationTermDelegate.create(offender, megaJailLoc, 
				conflictStartDate, conflictEndDate, false);
		
		endDate = this.parseDateText("1/1/2018");
		
		// Action
		locationTerm = this.locationTermService.update(locationTerm, megaPnpLoc,
				new DateRange(startDate, endDate));
	}
	
	/** 
	 * Tests whether business exception {@code LocationTermExistsAfterException}
	 * is thrown. 
	 * 
	 * @throws DuplicateEntityFoundException if location term exists
	 * @throws DateRangeOutOfBoundsException if location term is out of bounds
	 * @throws LocationTermConflictException if location term conflicts
	 * @throws LocationTermExistsAfterException if end date is null and other 
	 * location terms exist after the start date
	 * @throws LocationTermLockedException if location term is locked
	 * @throws OffenderNotUnderSupervisionException if offender is not under 
	 * supervision
	 */
	@Test(expectedExceptions = {LocationTermExistsAfterException.class})
	public void testLocationTermExistsAfterException()
			throws DuplicateEntityFoundException,
			LocationTermConflictException,
			DateRangeOutOfBoundsException,
			LocationTermExistsAfterException,
			LocationTermLockedException, 
			OffenderNotUnderSupervisionException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address megaJailAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		Organization megaJailOrg = this.organizationDelegate
				.create("Mega Jail", "MEGA", null);
		Organization megaPnpOrg = this.organizationDelegate
				.create("Mega PnP", "MPNP", null);
		Location megaJailLoc = this.locationDelegate.create(
				megaJailOrg, null, megaJailAddress);
		Location megaPnpLoc = this.locationDelegate
				.create(megaPnpOrg, null, megaJailAddress);
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2016");
		DateRange dateRange = new DateRange(startDate, 
				this.parseDateText("1/1/2018"));
		SupervisoryOrganization supervisoryOrganization 
				= this.supervisoryOrganizationDelegate.create("SO", "SO", null);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
				= this.supervisoryOrganizationTermDelegate.create(offender,
						dateRange, supervisoryOrganization);
		CorrectionalStatus correctionalStatus = this.correctionalStatusDelegate
				.create("Status", "S", false, (short) 1, true);
		CorrectionalStatusTerm correctionalStatusTerm 
				= this.correctionalStatusTermDelegate.create(offender, 
						dateRange, correctionalStatus );
		this.placementTermDelegate.create(offender, dateRange, 
				supervisoryOrganizationTerm, correctionalStatusTerm, null, null, 
				false);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, megaJailLoc, startDate, endDate, false);
		
		Date conflictStartDate = endDate;
		Date conflictEndDate = this.parseDateText("1/1/2017");
		this.locationTermDelegate.create(offender, megaJailLoc, 
				conflictStartDate, conflictEndDate, false);
		
		// Action
		locationTerm = this.locationTermService.update(locationTerm, megaPnpLoc,
				new DateRange(startDate, null));
	}
	
	/**
	 * Tests prevention of update to locked location terms.
	 * 
	 * @throws DuplicateEntityFoundException if location term exists
	 * @throws DateRangeOutOfBoundsException if date range is out of bounds 
	 * @throws LocationTermConflictException if conflicting location terms
	 * exist
	 * @throws LocationTermExistsAfterException if end date is null and other 
	 * location terms exist after the start date
	 * @throws LocationTermLockedException if location term is locked
	 * @throws OffenderNotUnderSupervisionException if offender is not under 
	 * supervision
	 */
	@Test(expectedExceptions = {LocationTermLockedException.class})
	public void testLocationTermLockedException()
			throws LocationTermLockedException,
					DuplicateEntityFoundException,
					LocationTermConflictException,
					DateRangeOutOfBoundsException,
					LocationTermExistsAfterException, 
					OffenderNotUnderSupervisionException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Ernst", null, null);
		Organization organization = this.organizationDelegate.create(
				"Lewis and Clark County Jail", "LCCJ", null);
		Country country = this.countryDelegate.create(
				"United States", "US", false);
		State state = this.stateDelegate.create(
				"Montana", "MT", country, true, true);
		City city = this.cityDelegate.create("Helena", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(
				city, "59601", null, true);
		Address address = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT, zipCode);
		Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("15/05/2011"), null), address);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, location, this.parseDateText("15/05/2011"),
						null, true);
		
		// Action
		this.locationTermService.update(locationTerm,
				locationTerm.getLocation(),
				new DateRange(locationTerm.getDateRange().getStartDate(),
						this.parseDateText("06/23/2012")));
	}
	
	/** 
	 * Tests whether business exception 
	 * {@code OffenderNotUnderSupervisionException} is thrown. 
	 * 
	 * @throws DuplicateEntityFoundException if location term exists
	 * @throws DateRangeOutOfBoundsException if location term is out of bounds
	 * @throws LocationTermConflictException if location term conflicts
	 * @throws LocationTermExistsAfterException if end date is null and other 
	 * location terms exist after the start date
	 * @throws LocationTermLockedException if location term is locked
	 * @throws OffenderNotUnderSupervisionException if offender is not under 
	 * supervision
	 */
	@Test(expectedExceptions = {OffenderNotUnderSupervisionException.class})
	public void testOffenderNotUnderSupervisionException()
			throws DuplicateEntityFoundException,
			LocationTermConflictException,
			DateRangeOutOfBoundsException,
			LocationTermExistsAfterException,
			LocationTermLockedException,
			OffenderNotUnderSupervisionException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernst", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address megaJailAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		Organization megaJailOrg = this.organizationDelegate
				.create("Mega Jail", "MEGA", null);
		Organization megaPnpOrg = this.organizationDelegate
				.create("Mega PnP", "MPNP", null);
		Location megaJailLoc = this.locationDelegate.create(
				megaJailOrg, null, megaJailAddress);
		Location megaPnpLoc = this.locationDelegate
				.create(megaPnpOrg, null, megaJailAddress);
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2016");
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, megaJailLoc, startDate, endDate, false);
		
		// Action
		locationTerm = this.locationTermService.update(locationTerm, megaPnpLoc,
				new DateRange(startDate, endDate));
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
	
}