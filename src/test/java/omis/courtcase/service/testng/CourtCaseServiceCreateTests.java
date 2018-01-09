package omis.courtcase.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.service.CourtCaseService;
import omis.courtcase.service.delegate.ChargeDelegate;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.datatype.DateRange;
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

/**
 * Tests method to create court cases.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class CourtCaseServiceCreateTests
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
	@Qualifier("courtCaseDelegate")
	private CourtCaseDelegate courtCaseDelegate;

	@Autowired
	@Qualifier("offenseDelegate")
	private OffenseDelegate offenseDelegate;	
	
	@Autowired
	@Qualifier("chargeDelegate")
	private ChargeDelegate chargeDelegate;	
	
	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;	
	
	/* Services. */

	@Autowired
	@Qualifier("courtCaseService")
	private CourtCaseService courtCaseService;

	/* Test methods. */

	/**
	 * Tests the creation of court cases.
	 * 
	 * @throws DocketExistsException if docket already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testCreate() throws DocketExistsException, 
			DuplicateEntityFoundException {
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
		String docket = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Offense chargeOffense = this.offenseDelegate.create("Offense", "Off", 
				null, OffenseClassification.DRUG, "123", true);
		Date chargeDate = this.parseDateText("01/01/2017");
		Date chargeFileDate = this.parseDateText("01/01/2017");
		Integer chargeCount = 1;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";

		// Action
		CourtCase courtCase = this.courtCaseService.create(person, court, 
				docket, interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments, chargeOffense, chargeDate, 
				chargeFileDate, chargeCount);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.value", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
		List<Charge> charges = this.chargeDelegate.findByCourtCase(courtCase);
		if (charges.size() == 1) {
			PropertyValueAsserter.create()
				.addExpectedValue("courtCase", courtCase)
				.addExpectedValue("offense", chargeOffense)
				.addExpectedValue("date", chargeDate)
				.addExpectedValue("fileDate", chargeFileDate)
				.addExpectedValue("counts", chargeCount)
				.performAssertions(charges.get(0));
		}
	}

	/**
	 * Tests {@code DocketExistsException} is thrown.
	 * 
	 * @throws DocketExistsException if docket already exists
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test(expectedExceptions = {DocketExistsException.class})
	public void testDocketExistsException() throws DocketExistsException, 
			DuplicateEntityFoundException {
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
		String docket = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Offense chargeOffense = this.offenseDelegate.create("Offense", "Off", 
				null, OffenseClassification.DRUG, "123", true);
		Date chargeDate = this.parseDateText("01/01/2017");
		Date chargeFileDate = this.parseDateText("01/01/2017");
		Integer chargeCount = 1;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		this.docketDelegate.create(person, court, docket);

		// Action
		this.courtCaseService.create(person, court, docket, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments, chargeOffense, chargeDate, chargeFileDate, 
				chargeCount);
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