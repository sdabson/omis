package omis.citation.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.citation.service.MisdemeanorCitationService;
import omis.citation.service.delegate.MisdemeanorCitationDelegate;
import omis.citation.service.delegate.MisdemeanorOffenseDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.Month;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create misdemeanor citations.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"citation", "service"})
public class MisdemeanorCitationServiceUpdateTests
extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("misdemeanorOffenseDelegate")
	private MisdemeanorOffenseDelegate misdemeanorOffenseDelegate;

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
	@Qualifier("misdemeanorCitationDelegate")
	private MisdemeanorCitationDelegate misdemeanorCitationDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("misdemeanorCitationService")
	private MisdemeanorCitationService misdemeanorCitationService;
	
	/* Test methods. */
	
	/**
	 * Tests the update of the state of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateState() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State state = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City city = this.cityDelegate.create("Helena",
				true, state, unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		State newState = this.stateDelegate.create("New Statia", "NS", 
				unitedStates, false, true);
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, newState, city,
				day, month, year, counts, offense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", newState)
		.addExpectedValue("city", city)
		.addExpectedValue("day", day)
		.addExpectedValue("month", month)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the city of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateCity() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		City newCity = this.cityDelegate.create("Butte", true, state, 
				unitedStates);
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, 
				newCity, day, month, year, counts, offense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", newCity)
		.addExpectedValue("day", day)
		.addExpectedValue("month", month)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the day of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateDay() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		Integer newDay = 2;
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, city, 
				newDay, month, year, counts, offense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", city)
		.addExpectedValue("day", newDay)
		.addExpectedValue("month", month)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the month of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateMonth() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		Month newMonth = Month.FEBRUARY;
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, city, 
				day, newMonth, year, counts, offense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", city)
		.addExpectedValue("day", day)
		.addExpectedValue("month", newMonth)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the year of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateYear() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		Integer newYear = 2016;
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, city, 
				day, month, newYear, counts, offense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", city)
		.addExpectedValue("day", day)
		.addExpectedValue("month", month)
		.addExpectedValue("year", newYear)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the counts of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateCounts() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		Integer newCounts = 2;
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, city, 
				day, month, year, newCounts, offense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", city)
		.addExpectedValue("day", day)
		.addExpectedValue("month", month)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", newCounts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the offense of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateOffense() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		MisdemeanorOffense newOffense = this.misdemeanorOffenseDelegate.create(
				"Offense 2", true);
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, city, 
				day, month, year, counts, newOffense, disposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", city)
		.addExpectedValue("day", day)
		.addExpectedValue("month", month)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", newOffense)
		.addExpectedValue("disposition", disposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the update of the disposition of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testUpdateDisposition() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State state = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City city = this.cityDelegate.create("Helena", true, state, 
				unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		MisdemeanorDisposition newDisposition = MisdemeanorDisposition.AMENDED;
		
		// Action
		citation = this.misdemeanorCitationService.update(citation, state, city, 
				day, month, year, counts, offense, newDisposition);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("offender", offender)
		.addExpectedValue("state", state)
		.addExpectedValue("city", city)
		.addExpectedValue("day", day)
		.addExpectedValue("month", month)
		.addExpectedValue("year", year)
		.addExpectedValue("counts", counts)
		.addExpectedValue("offense", offense)
		.addExpectedValue("disposition", newDisposition)
		.performAssertions(citation);
	}
	
	/**
	 * Tests the {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Bob", null);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State state = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City city = this.cityDelegate.create("Helena",
				true, state, unitedStates);
		Integer day = 1;
		Month month = Month.JANUARY;
		Integer year = 2017;
		Integer counts = 1;
		MisdemeanorOffense offense = this.misdemeanorOffenseDelegate.create(
				"Offense", true);
		MisdemeanorDisposition disposition = MisdemeanorDisposition.CONVICTED;
		this.misdemeanorCitationDelegate.create(offender, state, city, day, 
				month, year, counts, offense, disposition);
		Integer secondDay = 2;
		MisdemeanorCitation citation = this.misdemeanorCitationDelegate.create(
				offender, state, city, secondDay, month, year, counts, offense, 
				disposition);
		
		// Action
		this.misdemeanorCitationService.update(citation, state, city, day, 
				month, year, counts, offense, disposition);
	}
}
