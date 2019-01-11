package omis.placement.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.location.exception.LocationExistsException;
import omis.location.exception.LocationNotAllowedException;
import omis.location.service.delegate.LocationDelegate;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationReasonTerm;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.exception.LocationReasonExistsException;
import omis.locationterm.exception.LocationTermConflictException;
import omis.locationterm.exception.LocationTermExistsException;
import omis.locationterm.service.delegate.LocationReasonDelegate;
import omis.locationterm.service.delegate.LocationReasonTermDelegate;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.exception.OrganizationExistsException;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.region.domain.City;
import omis.region.domain.County;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.CountyExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.CountyDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.CorrectionalStatusExistsException;
import omis.supervision.exception.CorrectionalStatusTermExistsException;
import omis.supervision.exception.OffenderNotUnderSupervisionException;
import omis.supervision.exception.PlacementTermChangeReasonExistsException;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.exception.SupervisoryOrganizationExistsException;
import omis.supervision.exception.SupervisoryOrganizationTermExistsException;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateRangeUtility;
import omis.util.DateUtility;
import omis.util.PropertyValueAsserter;

/**
 * Tests for placing at location.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 16, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class ChangeCorrectionalStatusServicePlaceAtLocationTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service to test. */
	
	@Autowired
	@Qualifier("changeCorrectionalStatusService")
	private ChangeCorrectionalStatusService changeCorrectionalStatusService;
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate
	supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("locationTermDelegate")
	private LocationTermDelegate locationTermDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("countyDelegate")
	private CountyDelegate countyDelegate;
	
	@Autowired
	@Qualifier("locationReasonDelegate")
	private LocationReasonDelegate locationReasonDelegate;
	
	@Autowired
	@Qualifier("locationReasonTermDelegate")
	private LocationReasonTermDelegate locationReasonTermDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;
	
	@Autowired
	@Qualifier("dateRangeUtility")
	private DateRangeUtility dateRangeUtility;
	
	/**
	 * Verifies that {@code OffenderNotUnderSupervisionException} is thrown
	 * when an attempt is made to place and offender at a location while
	 * not under supervision.
	 * 
	 * @throws OffenderNotUnderSupervisionException if offender is not
	 * under supervision - asserted
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws LocationExistsException if location exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws LocationTermConflictException if location term exists
	 * @throws LocationNotAllowedException if location is not allowed 
	 * @throws LocationTermExistsException if location term exists
	 */
	@Test(expectedExceptions = {OffenderNotUnderSupervisionException.class})
	public void testOffenderNotUnderSupervisionException()
			throws OffenderNotUnderSupervisionException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				SupervisoryOrganizationExistsException,
				LocationExistsException,
				LocationTermExistsException,
				LocationNotAllowedException,
				LocationTermConflictException {
		
		// Arranges offender unsupervised
		Offender offender = this.createOffender();
		
		// Action - attempts to place offender at State prison
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		Location mtStatePrison = this.createStatePrisonLocation(
				this.createStatePrisonOrganization(
						helenaMt59601.getCity().getState()),
				helenaMt59601);
		Date effectiveDate = this.dateUtility.parseDateText("12/12/2017");
		this.changeCorrectionalStatusService.placeAtLocation(
				offender, mtStatePrison, effectiveDate, null, null);
	}
	
	/**
	 * Verifies that {@code LocationNotAllowedException} is thrown when
	 * locations are not allowed for placement.
	 * 
	 * @throws LocationNotAllowedException if location is not allowed for
	 * placement - asserted
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws CityExistsException if city exists
	 * @throws LocationExistsException if location exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws LocationTermConflictException if location term exists 
	 * @throws OffenderNotUnderSupervisionException if offender is not
	 * under supervision
	 * @throws LocationTermExistsException if location term exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	@Test(expectedExceptions = {LocationNotAllowedException.class})
	public void testLocationNotAllowedException()
			throws LocationNotAllowedException,
				CorrectionalStatusExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				SupervisoryOrganizationExistsException,
				LocationExistsException,
				LocationTermExistsException,
				OffenderNotUnderSupervisionException,
				LocationTermConflictException,
				SupervisoryOrganizationTermExistsException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender on parole
		Offender offender = this.createOffender();
		CorrectionalStatus parole = this.createParole();
		DateRange dateRange = this.dateRangeUtility.parseDateTexts(
				"12/12/2010", "12/12/2015");
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		SupervisoryOrganization pnpOffice = this.createCityPnpOffice(
				helenaMt59601.getCity());
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(offender, parole, pnpOffice, newSentenceReason,
				dateRange);
		
		// Action - attempts to locate offender in prison
		Location location = this.createStatePrisonLocation(
				this.createStatePrisonOrganization(
						helenaMt59601.getCity().getState()),
				helenaMt59601);
		this.changeCorrectionalStatusService
			.placeAtLocation(offender, location, dateRange.getStartDate(),
					null, null);
	}
	
	/**
	 * Verifies that conflicting locations are prevented with
	 * {@code LocationTermConflictException} when other locations exist
	 * that would conflict other than location term on effective date.
	 * 
	 * @throws LocationTermConflictException if conflicting location terms
	 * exist - asserted
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists 
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws ZipCodeExistsException if ZIP code exists 
	 * @throws CityExistsException if city exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws CountyExistsException if county exists
	 * @throws LocationNotAllowedException if location is not allowed
	 * @throws OffenderNotUnderSupervisionException if offender is not
	 * under supervision
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 */
	@Test(expectedExceptions = {LocationTermConflictException.class})
	public void testLocationTermConflictExceptionWhenLocationTermExists()
			throws LocationTermConflictException,
				CorrectionalStatusExistsException,
				CountryExistsException,
				StateExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				LocationTermExistsException,
				OrganizationExistsException,
				CountyExistsException,
				OffenderNotUnderSupervisionException,
				LocationNotAllowedException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender securely with two, back to back, location terms
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecure();
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		SupervisoryOrganization prisonOrganization
			= this.createStatePrisonOrganization(montana);
		DateRange placementRange = this.dateRangeUtility
				.parseDateTexts("12/12/2010",  "12/12/2014");
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(
				offender, secure, prisonOrganization, newSentenceReason,
				placementRange);
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		Location prisonLocation = this.createStatePrisonLocation(
				prisonOrganization, helenaMt59601);
		DateRange prisonRange = new DateRange(placementRange.getStartDate(),
				this.dateUtility.parseDateText("12/12/2012"));
		this.createLocationTerm(offender, prisonRange, prisonLocation);
		DateRange hospitalRange = new DateRange(prisonRange.getEndDate(),
				this.dateUtility.parseDateText("12/12/2014"));
		this.createLocationTerm(offender, hospitalRange,
				this.createCityHospitalLocation(helenaMt59601));
		
		// Action - attempts to place offender in county jail in conflicting
		// period with hospital stay
		DateRange jailRange = this.dateRangeUtility
				.parseDateTexts("12/12/2011", "12/12/2013");
		County lewisAndClarkCountyMontana
			= this.createLewisAndClarkMontana(montana);
		Location countyJailLocation = this.createCountyJailLocation(
				lewisAndClarkCountyMontana, helenaMt59601);
		this.changeCorrectionalStatusService
			.placeAtLocation(offender, countyJailLocation,
					jailRange.getStartDate(),
					jailRange.getEndDate(),
					null);
	}
	
	/**
	 * Tests that existing location term with matching location is lengthened.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws CityExistsException if city exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision
	 * @throws LocationNotAllowedException if location is not allowed
	 * @throws LocationTermConflictException if conflicting location terms
	 * exist
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists 
	 */
	public void testExistingTermWithMatchingLocationIsLengthened()
			throws CorrectionalStatusExistsException,
				CountryExistsException,
				StateExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				LocationTermExistsException,
				OffenderNotUnderSupervisionException,
				LocationNotAllowedException,
				LocationTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender securely in prison
		Offender offender = this.createOffender();
		DateRange placementRange = this.dateRangeUtility
				.parseDateTexts("12/12/2010", "12/12/2013");
		CorrectionalStatus secure = this.createSecure();
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		SupervisoryOrganization prisonOrganization
			= this.createStatePrisonOrganization(montana);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(
				offender, secure, prisonOrganization, newSentenceReason,
				placementRange);
		DateRange prisonRange = new DateRange(
				placementRange.getStartDate(),
				this.dateUtility.parseDateText("12/12/2012"));
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		Location prison = this.createStatePrisonLocation(
				prisonOrganization, helenaMt59601);
		LocationTerm originalPrisonTerm
			= this.createLocationTerm(offender, prisonRange, prison);
		
		// Action - places offender at same location starting during existing
		// term
		DateRange secondPrisonRange = new DateRange(
				this.dateUtility.parseDateText("12/12/2011"),
				placementRange.getEndDate());
		LocationTerm updatedPrisonTerm
			= this.changeCorrectionalStatusService.placeAtLocation(
					offender, prison,
					secondPrisonRange.getStartDate(),
					secondPrisonRange.getEndDate(), null);
		
		// Asserts that original location term was expanded and used
		assert originalPrisonTerm.equals(updatedPrisonTerm)
			: String.format("Original prison term was not used - original"
				+ " is %s; new is %s", originalPrisonTerm, updatedPrisonTerm);
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange", placementRange)
			.addExpectedValue("offender", offender)
			.addExpectedValue("location", prison)
			.performAssertions(updatedPrisonTerm);
		
		// Asserts the no other location terms exist
		long otherTermCount = this.locationTermDelegate.countExcluding(offender,
				placementRange.getStartDate(),
				placementRange.getEndDate(),
				updatedPrisonTerm);
		assert otherTermCount == 0 : String.format("%d other terms found",
				otherTermCount);
	}
	
	/**
	 * Verifies that existing location term is ended when new one begins when
	 * locations are different.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status
	 * exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws CityExistsException if city exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws CountyExistsException if county exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision
	 * @throws LocationNotAllowedException if location is not allowed
	 * @throws LocationTermConflictException if location term exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	public void testExistingTermWithDifferentLocationIsEnded()
			throws CorrectionalStatusExistsException,
				CountryExistsException,
				StateExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				LocationTermExistsException,
				CountyExistsException,
				OrganizationExistsException,
				OffenderNotUnderSupervisionException,
				LocationNotAllowedException,
				LocationTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender securely and in prison
		Offender offender = this.createOffender();
		DateRange placementRange = this.dateRangeUtility
				.parseDateTexts("12/12/2010", null);
		CorrectionalStatus secure = this.createSecure();
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		SupervisoryOrganization prison
			= this.createStatePrisonOrganization(montana);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(offender, secure, prison, newSentenceReason,
				placementRange);
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		Location prisonLocation
			= this.createStatePrisonLocation(prison, helenaMt59601);
		LocationTerm prisonTerm = this.createLocationTerm(
				offender, placementRange, prisonLocation);
		
		// Action - sends offender to jail during prison term
		County lewisAndClarkCountyMontana
			= this.createLewisAndClarkMontana(montana);
		Location countyJail = this.createCountyJailLocation(
				lewisAndClarkCountyMontana, helenaMt59601);
		DateRange jailRange = new DateRange(
				this.dateUtility.parseDateText("12/12/2012"), null);
		LocationTerm jailTerm = this.changeCorrectionalStatusService
				.placeAtLocation(offender, countyJail,
						jailRange.getStartDate(),
						jailRange.getEndDate(),
						null);
		
		// Asserts that prison and jail terms are different
		assert !jailTerm.equals(prisonTerm) : "Jail and prison terms are equal";
		
		// Assert that prison term is ended when jail term begins
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate",
					placementRange.getStartDate())
			.addExpectedValue("dateRange.endDate",
					jailRange.getStartDate())
			.addExpectedValue("location", prisonLocation)
			.performAssertions(prisonTerm);
		
		// Assert that jail term begins when prison term ends
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate",
					jailRange.getStartDate())
			.addExpectedValue("dateRange.endDate",
					jailRange.getEndDate())
			.addExpectedValue("location", countyJail)
			.performAssertions(jailTerm);
	}
	
	/**
	 * Tests location placement without the existing of location terms.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws CityExistsException if city exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision
	 * @throws LocationNotAllowedException if location is not allowed
	 * @throws LocationTermConflictException if conflicting location terms
	 * exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	public void testCreationWithoutExisting()
			throws CorrectionalStatusExistsException,
				CountryExistsException,
				StateExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				LocationTermExistsException,
				OffenderNotUnderSupervisionException,
				LocationNotAllowedException,
				LocationTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender securely
		Offender offender = this.createOffender();
		DateRange placementRange = this.dateRangeUtility
				.parseDateTexts("12/12/2012", null);
		CorrectionalStatus secure = this.createSecure();
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		SupervisoryOrganization prison
			= this.createStatePrisonOrganization(montana);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(offender, secure, prison, newSentenceReason,
				placementRange);

		// Action - places offender in prison during placement
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		Location prisonLocation
			= this.createStatePrisonLocation(prison, helenaMt59601);
		LocationTerm prisonTerm = this.changeCorrectionalStatusService
				.placeAtLocation(offender, prisonLocation,
						placementRange.getStartDate(),
						placementRange.getEndDate(),
						null);
		
		// Asserts offender is located in prison during placement
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange", placementRange)
			.addExpectedValue("location", prisonLocation)
			.addExpectedValue("offender", offender)
			.performAssertions(prisonTerm);
	}
	
	/**
	 * Tests that placement at location when there is a conflicting existing
	 * location term is prevented with {@code LocationTermConflictException}.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists 
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws ZipCodeExistsException if ZIP code exists 
	 * @throws CityExistsException if city exists
	 * @throws CountyExistsException if county exists
	 * @throws LocationExistsException if location exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws LocationTermConflictException if conflicting location
	 * term exists - asserted
	 * @throws LocationNotAllowedException if location is not allowed 
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	@Test(expectedExceptions = {LocationTermConflictException.class})
	public void testLocationTermConflictExceptionWithConflictingExisting()
			throws CorrectionalStatusExistsException,
				CountryExistsException,
				StateExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CityExistsException,
				ZipCodeExistsException,
				CountyExistsException,
				OrganizationExistsException,
				LocationExistsException,
				LocationTermExistsException,
				OffenderNotUnderSupervisionException,
				LocationNotAllowedException,
				LocationTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender securely
		Offender offender = this.createOffender();
		DateRange placementRange = this.dateRangeUtility.parseDateTexts(
				"12/12/2010", null);
		CorrectionalStatus secure = this.createSecure();
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		SupervisoryOrganization prison = this.createStatePrisonOrganization(
				montana);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(offender, secure, prison, newSentenceReason,
				placementRange);
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		County lewisAndClarkMontana = this.createLewisAndClarkMontana(montana);
		Location jail = this.createCountyJailLocation(
				lewisAndClarkMontana, helenaMt59601);
		Date jailEffectiveDate = this.dateUtility.parseDateText("12/12/2013");
		this.locationTermDelegate.create(offender, jail,
				jailEffectiveDate, null, false, null);
		
		// Actions - attempts to place offender in prison while conflicting
		// jail term exists
		Location statePrison = this.createStatePrisonLocation(
				prison, helenaMt59601);
		this.changeCorrectionalStatusService.placeAtLocation(
				offender, statePrison,
				placementRange.getStartDate(),
				placementRange.getEndDate(), null);
	}
	
	/**
	 * Tests location placement with reason.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CountryExistsException if country exists
	 * @throws StateExistsException if State exists
	 * @throws CityExistsException if city exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws CountyExistsException if county exists
	 * @throws OrganizationExistsException if organization exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationReasonExistsException if location reason exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws OffenderNotUnderSupervisionException if offender is not under
	 * supervision
	 * @throws LocationNotAllowedException if location is not allowed
	 * @throws LocationTermConflictException if conflicting location terms
	 * exist
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	public void testCreationWithReason()
			throws CorrectionalStatusExistsException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				CountyExistsException,
				OrganizationExistsException,
				LocationExistsException,
				LocationReasonExistsException,
				LocationTermExistsException,
				OffenderNotUnderSupervisionException,
				LocationNotAllowedException,
				LocationTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender placed on parole
		Offender offender = this.createOffender();
		DateRange placementRange = this.dateRangeUtility.parseDateTexts(
				"12/12/2010", "12/12/2015");
		CorrectionalStatus secure = this.createSecure();
		Country usa = this.createUsa();
		State montana = this.createMontana(usa);
		SupervisoryOrganization statePrison
			= this.createStatePrisonOrganization(montana);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(offender, secure, statePrison,
				newSentenceReason, placementRange);
		ZipCode helenaMt59601 = this.createHelenaMt59601(montana);
		Location statePrisonLocation = this.createStatePrisonLocation(
				statePrison, helenaMt59601);
		this.locationTermDelegate.create(offender, statePrisonLocation,
				placementRange.getStartDate(),
				placementRange.getEndDate(), false, null);
		
		// Action - sends offender to jail pending new charges
		County lewisAndClarkCounty = this.createLewisAndClarkMontana(montana);
		Location countyJail = this.createCountyJailLocation(
				lewisAndClarkCounty, helenaMt59601);
		DateRange jailRange = this.dateRangeUtility.parseDateTexts(
				"12/12/2014", null);
		LocationReason pendingNewCharges = this.createPendingNewCharges();
		LocationTerm jailTerm = this.changeCorrectionalStatusService
				.placeAtLocation(offender, countyJail,
						jailRange.getStartDate(),
						jailRange.getEndDate(),
						pendingNewCharges);
		
		// Asserts location reason term exists on location dates
		LocationReasonTerm jailReasonTerm
			= this.locationReasonTermDelegate.findForLocationTermOnDate(
					jailTerm, jailRange.getStartDate());
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange", jailRange)
			.addExpectedValue("locationTerm", jailTerm)
			.addExpectedValue("reason", pendingNewCharges)
			.performAssertions(jailReasonTerm);
	}
	
	/* Helper methods. */

	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate
				.createWithoutIdentity("Grant", "Ernst", null, null);
	}
	
	// Returns States prison supervisory organization
	private SupervisoryOrganization createStatePrisonOrganization(
			final State state)
					throws SupervisoryOrganizationExistsException {
		return this.supervisoryOrganizationDelegate
				.create(String.format("%s State Prison",
							state.getName()),
						String.format("%sSP",
							state.getAbbreviation()),
						null);
	}
	
	// Returns prison location in State, creates address in city of ZIP code
	private Location createStatePrisonLocation(
			final SupervisoryOrganization prisonOrganization,
			final ZipCode zipCode)
			throws LocationExistsException {
		return this.locationDelegate.create(
				prisonOrganization, null,
				this.addressDelegate.findOrCreate(
						"1000 1000TH ST #1000", null, null, null, zipCode));
	}
	
	// Returns city hospital location
	private Location createCityHospitalLocation(final ZipCode zipCode)
			throws OrganizationExistsException,
				LocationExistsException {
		Organization hospitalOrganization = this.organizationDelegate
				.create(
					String.format(
							"%s City Hospital", zipCode.getCity().getName()),
					String.format("%sCH", zipCode.getCity().getName()),
					null);
		Address address = this.addressDelegate.findOrCreate(
				"2000 2000TH ST #2000", null, null, null, zipCode);
		return this.locationDelegate.create(
				hospitalOrganization, null, address);
	}
	
	// Returns county jail location
	private Location createCountyJailLocation(
			final County county, final ZipCode zipCode)
					throws OrganizationExistsException,
						LocationExistsException {
		Organization countyJailOrganization = this.organizationDelegate
				.create(String.format("%s County Jail", county.getName()),
						String.format("%sCJ", county.getName()), null);
		Address address = this.addressDelegate
				.findOrCreate("3000 3000TH ST 3000", null, null, null, zipCode);
		return this.locationDelegate.create(
				countyJailOrganization, null, address);
	}
	
	// Returns USA
	private Country createUsa() throws CountryExistsException {
		return this.countryDelegate.create(
				"United States of America", "US", true);
	}
	
	// Returns Montana
	private State createMontana(final Country usa) throws StateExistsException {
		return this.stateDelegate.create("Montana", "MT", usa, true, true);
	}
	
	// Returns Helena 59601 ZIP code in Montana
	private ZipCode createHelenaMt59601(final State montana)
			throws CityExistsException, ZipCodeExistsException {
		City helena = this.cityDelegate.create(
				"Helena", true, montana, montana.getCountry());
		return this.zipCodeDelegate.create(helena, "59601", null, true);
	}
	
	// Creates P&P office in city
	private SupervisoryOrganization createCityPnpOffice(final City city)
			throws SupervisoryOrganizationExistsException {
		return this.supervisoryOrganizationDelegate
				.create(String.format("%s P&P", city.getName()),
						String.format("%sPNP", city.getName()), 
						null);
	}
	
	// Returns secure status
	private CorrectionalStatus createSecure()
			throws CorrectionalStatusExistsException {
		return this.correctionalStatusDelegate.create(
				"Secure", "SEC", true, (short) 1, true);
	}
	
	// Returns parole status
	private CorrectionalStatus createParole()
			throws CorrectionalStatusExistsException {
		return this.correctionalStatusDelegate.create(
				"Parole", "PAR", false, (short) 1, true);
	}
	
	// Returns placement term
	private PlacementTerm createPlacementTerm(
			final Offender offender,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final PlacementTermChangeReason startReason,
			final DateRange dateRange)
					throws CorrectionalStatusTermExistsException,
						PlacementTermExistsException,
						SupervisoryOrganizationTermExistsException {
		return this.placementTermDelegate
				.create(offender, dateRange,
					this.supervisoryOrganizationTermDelegate
						.create(offender, dateRange, supervisoryOrganization),
					this.correctionalStatusTermDelegate
						.create(offender, dateRange, correctionalStatus),
					startReason, null, false);
	}
	
	// Returns location term
	private LocationTerm createLocationTerm(
			final Offender offender,
			final DateRange dateRange,
			final Location location)
					throws LocationTermExistsException {
		return this.locationTermDelegate.create(offender, location,
				DateRange.getStartDate(dateRange),
				DateRange.getEndDate(dateRange), false, null);
	}
	
	// Returns Lewis and Clark county in Montana
	private County createLewisAndClarkMontana(final State montana)
			throws CountyExistsException {
		return this.countyDelegate.create("Lewis and Clark", montana, true);
	}
	
	// Returns pending new charges reason
	private LocationReason createPendingNewCharges()
			throws LocationReasonExistsException {
		return this.locationReasonDelegate.create(
				"Pending New Charges", (short) 1, true);
	}
	
	// Creates new placement term change reason
	private PlacementTermChangeReason createNewSentenceReason()
			throws PlacementTermChangeReasonExistsException {
		return this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
	}
}