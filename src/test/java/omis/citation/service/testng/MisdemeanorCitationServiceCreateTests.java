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
public class MisdemeanorCitationServiceCreateTests
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
	 * Tests the creation of a misdemeanor citation.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 */
	@Test
	public void testCreate() throws DuplicateEntityFoundException {
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
		
		// Action
		MisdemeanorCitation citation = this.misdemeanorCitationService.save(
				offender, state, city, day, month, year, counts, offense, 
				disposition);
		
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
		.addExpectedValue("disposition", disposition)
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
		
		// Action
		this.misdemeanorCitationService.save(offender, state, city, day, month, 
				year, counts, offense, disposition);
	}
}
