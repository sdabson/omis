package omis.courtcase.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.domain.Charge;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.exception.ChargeExistsException;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.CourtCaseService;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;
import omis.offense.service.delegate.OffenseDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

public class CourtCaseServiceUpdateChargeTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
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
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("courtDelegate")
	private CourtDelegate courtDelegate;
	
	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
	@Autowired
	@Qualifier("courtCaseDelegate")
	private CourtCaseDelegate courtCaseDelegate;

	@Autowired
	@Qualifier("offenseDelegate")
	private OffenseDelegate offenseDelegate;	
	
	@Autowired
	@Qualifier("chargeDelegate")
	private ChargeDelegate chargeDelegate;	
	
	/* Services. */

	@Autowired
	@Qualifier("courtCaseService")
	private CourtCaseService courtCaseService;

	/* Test methods. */

	/**
	 * Tests the update of the offense for a charge.
	 * 
	 * @throws ChargeExistsException if charge already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateChargeOffense() throws ChargeExistsException, 
			DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O",
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy Street", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(person, court, "ABC-123");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				null, null, null, null, null, null, null);
		Offense offense = this.offenseDelegate.create("Offense", "Off", null, 
				OffenseClassification.WEAPON, "1234", true);
		Date date = this.parseDateText("01/01/2017");
		Date fileDate = this.parseDateText("03/01/2017");
		Integer counts = 2;
		Charge charge = this.chargeDelegate.create(courtCase, offense, date, 
				fileDate, counts);
		Offense newOffense = this.offenseDelegate.create("Offense 2", "Off", 
				null, OffenseClassification.WEAPON, "12345", true);
		
		// Action
		charge = this.courtCaseService.updateCharge(charge, newOffense, date, 
				fileDate, counts);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("offense", newOffense)
			.addExpectedValue("date", date)
			.addExpectedValue("fileDate", fileDate)
			.addExpectedValue("counts", counts)
			.performAssertions(charge);
	}
	
	/**
	 * Tests the update of the date for a charge.
	 * 
	 * @throws ChargeExistsException if charge already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateChargeDate() throws ChargeExistsException, 
			DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O",
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy Street", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(person, court, "ABC-123");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				null, null, null, null, null, null, null);
		Offense offense = this.offenseDelegate.create("Offense", "Off", null, 
				OffenseClassification.WEAPON, "1234", true);
		Date date = this.parseDateText("01/01/2017");
		Date fileDate = this.parseDateText("03/01/2017");
		Integer counts = 2;
		Charge charge = this.chargeDelegate.create(courtCase, offense, date, 
				fileDate, counts);
		Date newDate = this.parseDateText("02/01/2017");
		
		// Action
		charge = this.courtCaseService.updateCharge(charge, offense, newDate, 
				fileDate, counts);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("offense", offense)
			.addExpectedValue("date", newDate)
			.addExpectedValue("fileDate", fileDate)
			.addExpectedValue("counts", counts)
			.performAssertions(charge);
	}
	
	/**
	 * Tests the update of the file date for a charge.
	 * 
	 * @throws ChargeExistsException if charge already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateChargeFileDate() throws ChargeExistsException, 
			DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O",
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy Street", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(person, court, "ABC-123");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				null, null, null, null, null, null, null);
		Offense offense = this.offenseDelegate.create("Offense", "Off", null, 
				OffenseClassification.WEAPON, "1234", true);
		Date date = this.parseDateText("01/01/2017");
		Date fileDate = this.parseDateText("03/01/2017");
		Integer counts = 2;
		Charge charge = this.chargeDelegate.create(courtCase, offense, date, 
				fileDate, counts);
		Date newFileDate = this.parseDateText("03/07/2017");
		
		// Action
		charge = this.courtCaseService.updateCharge(charge, offense, date, 
				newFileDate, counts);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("offense", offense)
			.addExpectedValue("date", date)
			.addExpectedValue("fileDate", newFileDate)
			.addExpectedValue("counts", counts)
			.performAssertions(charge);
	}
	
	/**
	 * Tests the update of the counts for a charge.
	 * 
	 * @throws ChargeExistsException if charge already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateChargeCounts() throws ChargeExistsException, 
			DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O",
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy Street", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(person, court, "ABC-123");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				null, null, null, null, null, null, null);
		Offense offense = this.offenseDelegate.create("Offense", "Off", null, 
				OffenseClassification.WEAPON, "1234", true);
		Date date = this.parseDateText("01/01/2017");
		Date fileDate = this.parseDateText("03/01/2017");
		Integer counts = 2;
		Charge charge = this.chargeDelegate.create(courtCase, offense, date, 
				fileDate, counts);
		Integer newCounts = 24;
		
		// Action
		charge = this.courtCaseService.updateCharge(charge, offense, date, 
				fileDate, newCounts);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("offense", offense)
			.addExpectedValue("date", date)
			.addExpectedValue("fileDate", fileDate)
			.addExpectedValue("counts", newCounts)
			.performAssertions(charge);
	}

	/**
	 * Tests {@code ChargeExistsException} is thrown.
	 * 
	 * @throws ChargeExistsException if charge already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test(expectedExceptions = {ChargeExistsException.class})
	public void testChargeExistsException() throws ChargeExistsException, 
			DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", "John", "Jay", 
				null);
		Organization organization = this.organizationDelegate.create("Org", "O",
				null);
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 Easy Street", 
				null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization, 
				new DateRange(), address);
		Court court = this.courtDelegate.create("Court", CourtCategory.CITY, 
				location);
		Docket docket = this.docketDelegate.create(person, court, "ABC-123");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				null, null, null, null, null, null, null);
		Offense offense = this.offenseDelegate.create("Offense", "Off", null, 
				OffenseClassification.WEAPON, "1234", true);
		Date date = this.parseDateText("01/01/2017");
		Date fileDate = this.parseDateText("03/01/2017");
		Integer counts = 2;
		this.chargeDelegate.create(courtCase, offense, date, fileDate, counts);
		Integer secondCounts = 24;
		Charge charge = this.chargeDelegate.create(courtCase, offense, date, 
				fileDate, secondCounts);
		
		// Action
		this.courtCaseService.updateCharge(charge, offense, date, fileDate, 
				counts);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}