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
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.CourtCaseService;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
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

public class CourtCaseServiceUpdateTests
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
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;	
	
	/* Services. */

	@Autowired
	@Qualifier("courtCaseService")
	private CourtCaseService courtCaseService;

	/* Test methods. */

	/**
	 * Tests the update of the interstate number for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateInterStateNumber() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		String newInterStateNumber = "123412134145";
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, newInterStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", newInterStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the interstate for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateInterState() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		State newInterState = this.stateDelegate.create("New State", "NS", 
				country, false, true);
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				newInterState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", newInterState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the pronouncement date for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdatePronouncementDate() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		Date newPronouncementDate = this.parseDateText("01/02/2017");
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, newPronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", newPronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the jurisdiction authority for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateJurisdictionAuthority() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		JurisdictionAuthority newJurisdictionAuthority = 
				JurisdictionAuthority.STATE;
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, pronouncementDate, newJurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", newJurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the sentence review date for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateSentenceReviewDate() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		Date newSentenceReviewDate = this.parseDateText("03/05/2017");
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				newSentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", newSentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the danger designator for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateDangerDesignator() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		OffenderDangerDesignator newDangerDesignator = 
				OffenderDangerDesignator.NON_DANGEROUS;
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, newDangerDesignator, personnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", newDangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the personnel for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdatePersonnel() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		CourtCasePersonnel newPersonnel = new CourtCasePersonnel(judge, 
				"Attorney C", "Attorney D");
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, newPersonnel, flags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", newPersonnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the flags for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateFlags() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		CourtCaseFlags newFlags = new CourtCaseFlags(true, true, true, true);
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, newFlags, 
				comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", newFlags)
			.addExpectedValue("comments", comments)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the comments for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 * @throws DocketExistsException if docket already exists
	 * @throws CourtCaseExistsException if court case already exists
	 */
	@Test
	public void testUpdateComments() 
			throws DuplicateEntityFoundException, DocketExistsException, 
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
		String docketValue = "ABC-123";
		String interStateNumber = "12345";
		State interState = this.stateDelegate.create("Other State", "OS", 
				country, false, true);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		JurisdictionAuthority jurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		Date sentenceReviewDate = this.parseDateText("01/05/2017");
		OffenderDangerDesignator dangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", "J", 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, 
				"Attorney A", "Attorney B");
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Comments";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				personnel, flags, comments);
		String newComments = "New comments";
		
		// Action
		courtCase = this.courtCaseService.update(courtCase, interStateNumber, 
				interState, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				newComments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("interState", interState)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("flags", flags)
			.addExpectedValue("comments", newComments)
			.performAssertions(courtCase);
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