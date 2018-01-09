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
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationReasonTerm;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.exception.LocationReasonTermConflictException;
import omis.locationterm.exception.LocationReasonTermExistsAfterException;
import omis.locationterm.service.LocationTermService;
import omis.locationterm.service.delegate.LocationReasonDelegate;
import omis.locationterm.service.delegate.LocationReasonTermDelegate;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Location term servce reason term creation tests.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"locationTerm", "service"})
public class LocationTermServiceUpdateReasonTermTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locatioDelegate;
	
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
	@Qualifier("locationReasonDelegate")
	private LocationReasonDelegate locationReasonDelegate;
	
	@Autowired
	@Qualifier("locationReasonTermDelegate")
	private LocationReasonTermDelegate locationReasonTermDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("locationTermService")
	private LocationTermService locationTermService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Tests. */
	
	/**
	 * Tests update of location reason term date range.
	 * 
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws DateRangeOutOfBoundsException if date range exists out side of 
	 * location term date range
	 * @throws LocationReasonTermConflictException if overlapping location 
	 * reason terms
	 * @throws LocationReasonTermExistsAfterException date range is not ended 
	 * and exists location reason terms exist after the start date
	 */
	public void testUpdateReasonTermDateRange()
			throws DuplicateEntityFoundException,
			LocationReasonTermConflictException, 
			DateRangeOutOfBoundsException, 
			LocationReasonTermExistsAfterException {
		
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates,
				true, true);
		City helena = this.cityDelegate
				.create("Helena", true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address prisonAddress = this.addressDelegate
				.findOrCreate("1000", null, null, BuildingCategory.APARTMENT,
						mt59602);
		Organization prison = this.organizationDelegate
				.create("Prison", "PRS", null);
		Location prisonLocation = this.locatioDelegate
				.create(prison, new DateRange(
						this.parseDateText("08/23/2016"), null),
						prisonAddress);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, prisonLocation,
						this.parseDateText("09/23/2016"), null, false);
		LocationReason reason = this.locationReasonDelegate
				.create("Initial Commit", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				null);
		LocationReasonTerm reasonTerm = this.locationReasonTermDelegate.create(
				locationTerm, dateRange, reason);
		dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				this.parseDateText("02/21/2017"));
		
		// Action
		this.locationTermService.updateReasonTerm(reasonTerm, dateRange, 
				reason);
		
		// Assert
		assertValues(reasonTerm, locationTerm, dateRange, reason);
	}
	
	
	/**
	 * Tests update of location reason term reason.
	 * 
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws DateRangeOutOfBoundsException if date range exists out side of 
	 * location term date range
	 * @throws LocationReasonTermConflictException if overlapping location 
	 * reason terms
	 * @throws LocationReasonTermExistsAfterException date range is not ended 
	 * and exists location reason terms exist after the start date
	 */
	public void testUpdateReasonTermReason()
			throws DuplicateEntityFoundException,
			LocationReasonTermConflictException, 
			DateRangeOutOfBoundsException, 
			LocationReasonTermExistsAfterException {
		
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates,
				true, true);
		City helena = this.cityDelegate
				.create("Helena", true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address prisonAddress = this.addressDelegate
				.findOrCreate("1000", null, null, BuildingCategory.APARTMENT,
						mt59602);
		Organization prison = this.organizationDelegate
				.create("Prison", "PRS", null);
		Location prisonLocation = this.locatioDelegate
				.create(prison, new DateRange(
						this.parseDateText("08/23/2016"), null),
						prisonAddress);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, prisonLocation,
						this.parseDateText("09/23/2016"), null, false);
		LocationReason reason = this.locationReasonDelegate
				.create("Initial Commit", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				null);
		LocationReasonTerm reasonTerm = this.locationReasonTermDelegate.create(
				locationTerm, dateRange, reason);
		
		LocationReason newReason = this.locationReasonDelegate
				.create("New Reason", (short) 2, true);
		
		// Action
		this.locationTermService.updateReasonTerm(reasonTerm, dateRange, 
				newReason);
		
		// Assert
		assertValues(reasonTerm, locationTerm, dateRange, newReason);
	}
	
	/**
	 * Tests duplicate entity exception is properly thrown.
	 * 
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws DateRangeOutOfBoundsException if date range exists out side of 
	 * location term date range
	 * @throws LocationReasonTermConflictException if overlapping location 
	 * reason terms
	 * @throws LocationReasonTermExistsAfterException date range is not ended 
	 * and exists location reason terms exist after the start date
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException()
			throws DuplicateEntityFoundException,
			LocationReasonTermConflictException, 
			DateRangeOutOfBoundsException, 
			LocationReasonTermExistsAfterException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates,
				true, true);
		City helena = this.cityDelegate
				.create("Helena", true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address prisonAddress = this.addressDelegate
				.findOrCreate("1000", null, null, BuildingCategory.APARTMENT,
						 mt59602);
		Organization prison = this.organizationDelegate
				.create("Prison", "PRS", null);
		Location prisonLocation = this.locatioDelegate
				.create(prison, new DateRange(
						this.parseDateText("08/23/2016"), null),
						prisonAddress);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, prisonLocation,
						this.parseDateText("09/23/2016"), null, false);
		LocationReason reason = this.locationReasonDelegate
				.create("Initial Commit", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				this.parseDateText("11/10/2016"));
		this.locationReasonTermDelegate.create(locationTerm, dateRange, reason);
		DateRange secondDateRange = new DateRange(
				this.parseDateText("11/10/2016"), null);
		LocationReasonTerm locationReasonTerm = this.locationReasonTermDelegate
				.create(locationTerm, secondDateRange, reason);
		
		// Action
		this.locationTermService.updateReasonTerm(locationReasonTerm, dateRange, 
				reason);
	}
	
	/**
	 * Tests date range out of bounds exception is properly thrown.
	 * 
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws DateRangeOutOfBoundsException if date range exists out side of 
	 * location term date range
	 * @throws LocationReasonTermConflictException if overlapping location 
	 * reason terms
	 * @throws LocationReasonTermExistsAfterException date range is not ended 
	 * and exists location reason terms exist after the start date
	 */
	@Test(expectedExceptions = {DateRangeOutOfBoundsException.class})
	public void testDateRangeOutOfBoundsException()
			throws DuplicateEntityFoundException,
			LocationReasonTermConflictException, 
			DateRangeOutOfBoundsException, 
			LocationReasonTermExistsAfterException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates,
				true, true);
		City helena = this.cityDelegate
				.create("Helena", true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address prisonAddress = this.addressDelegate
				.findOrCreate("1000", null, null, BuildingCategory.APARTMENT,
						mt59602);
		Organization prison = this.organizationDelegate
				.create("Prison", "PRS", null);
		Location prisonLocation = this.locatioDelegate
				.create(prison, new DateRange(
						this.parseDateText("08/23/2016"), null),
						prisonAddress);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, prisonLocation,
						this.parseDateText("09/23/2016"), null, false);
		LocationReason reason = this.locationReasonDelegate
				.create("Initial Commit", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				null);
		LocationReasonTerm reasonTerm = this.locationReasonTermDelegate
				.create(locationTerm, dateRange, reason);
		dateRange = new DateRange(this.parseDateText("09/23/2015"), 
				null);
		
		// Action
		this.locationTermService.updateReasonTerm(reasonTerm, dateRange,
				reason);
	}
	
	/**
	 * Tests location reason term conflict exception is properly thrown.
	 * 
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws DateRangeOutOfBoundsException if date range exists out side of 
	 * location term date range
	 * @throws LocationReasonTermConflictException if overlapping location 
	 * reason terms
	 * @throws LocationReasonTermExistsAfterException date range is not ended 
	 * and exists location reason terms exist after the start date
	 */
	@Test(expectedExceptions = {LocationReasonTermConflictException.class})
	public void testLocationReasonTermConflictException()
			throws DuplicateEntityFoundException,
			LocationReasonTermConflictException, 
			DateRangeOutOfBoundsException, 
			LocationReasonTermExistsAfterException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates,
				true, true);
		City helena = this.cityDelegate
				.create("Helena", true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address prisonAddress = this.addressDelegate
				.findOrCreate("1000", null, null, BuildingCategory.APARTMENT,
						 mt59602);
		Organization prison = this.organizationDelegate
				.create("Prison", "PRS", null);
		Location prisonLocation = this.locatioDelegate
				.create(prison, new DateRange(
						this.parseDateText("08/23/2016"), null),
						prisonAddress);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, prisonLocation,
						this.parseDateText("09/23/2016"), null, false);
		LocationReason reason = this.locationReasonDelegate
				.create("Initial Commit", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/23/2017"), 
				this.parseDateText("01/24/2018"));
		this.locationReasonTermDelegate.create(locationTerm, dateRange, reason);
		dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				this.parseDateText("01/23/2017"));
		LocationReasonTerm reasonTerm = this.locationReasonTermDelegate
				.create(locationTerm, dateRange, reason);
		dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				this.parseDateText("01/01/2018"));
		
		// Action
		this.locationTermService.updateReasonTerm(reasonTerm, dateRange,
				reason);
	}
	
	/**
	 * Tests location reason term exists after exception is properly thrown.
	 * 
	 * @throws DuplicateEntityFoundException if location reason term exists
	 * @throws DateRangeOutOfBoundsException if date range exists out side of 
	 * location term date range
	 * @throws LocationReasonTermConflictException if overlapping location 
	 * reason terms
	 * @throws LocationReasonTermExistsAfterException date range is not ended 
	 * and exists location reason terms exist after the start date
	 */
	@Test(expectedExceptions = {LocationReasonTermExistsAfterException.class})
	public void testLocationReasonTermExistsAfterException()
			throws DuplicateEntityFoundException,
			LocationReasonTermConflictException, 
			DateRangeOutOfBoundsException, 
			LocationReasonTermExistsAfterException {
		// Arrangements
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates,
				true, true);
		City helena = this.cityDelegate
				.create("Helena", true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address prisonAddress = this.addressDelegate
				.findOrCreate("1000", null, null, BuildingCategory.APARTMENT,
						mt59602);
		Organization prison = this.organizationDelegate
				.create("Prison", "PRS", null);
		Location prisonLocation = this.locatioDelegate
				.create(prison, new DateRange(
						this.parseDateText("08/23/2016"), null),
						prisonAddress);
		LocationTerm locationTerm = this.locationTermDelegate
				.create(offender, prisonLocation,
						this.parseDateText("09/23/2016"), null, false);
		LocationReason reason = this.locationReasonDelegate
				.create("Initial Commit", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/23/2017"), 
				null);
		this.locationReasonTermDelegate.create(locationTerm, dateRange, reason);
		dateRange = new DateRange(this.parseDateText("09/23/2016"), 
				this.parseDateText("01/23/2017"));
		LocationReasonTerm reasonTerm = this.locationReasonTermDelegate
				.create(locationTerm, dateRange, reason);
		dateRange.setEndDate(null);
		
		// Action
		this.locationTermService.updateReasonTerm(reasonTerm, dateRange,
				reason);
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
	
	// Asserts values are correct
	private void assertValues(final LocationReasonTerm locationReasonTerm, 
			final LocationTerm locationTerm, final DateRange dateRange, 
			final LocationReason reason) {
		assert locationTerm.equals(locationReasonTerm.getLocationTerm()) 
			: String.format("Wrong location term: %s found; %s expected", 
					locationReasonTerm.getLocationTerm().getId(),
					locationTerm.getId());
		assert DateRange.areEqual(dateRange, locationReasonTerm.getDateRange())
			: String.format("Wrong date range: %s found; %s expected", 
					locationReasonTerm.getDateRange().toString(),
					dateRange.toString());
		assert reason.equals(locationReasonTerm.getReason()) : String.format(
				"Wrong reason: %s found; %s expected", 
				locationReasonTerm.getReason().getName(), reason.getName());
	}
	
}