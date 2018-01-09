package omis.conviction.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.AddressUnitDesignatorDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.conviction.service.ConvictionService;
import omis.conviction.service.delegate.ConvictionDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.delegate.CourtCaseDelegate;
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

/**
 * Tests method to update convictions.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"courtcase", "service"})
public class ConvictionServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service delegates. */
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
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
	@Qualifier("convictionDelegate")
	private ConvictionDelegate convictionDelegate;
	
	
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("convictionService")
	private ConvictionService convictionService;
	
	/* Test methods. */
	
	/**
	 * Tests the update of the offense of a conviction.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ConvictionExistsException thrown when convictions exists
	 * @throws DocketExistsException thrown when docket exists
	 * @throws CourtCaseExistsException thrown when court case exists
	 */
	@Test
	public void testUpdateOffense() throws DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT, mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		Offense newOffense = this.offenseDelegate.create("Some New Offense", 
				"Some New Offense", null, OffenseClassification.DRUG, 
				"Code-ABC", true);
		
		// Action
		conviction = this.convictionService.update(conviction, newOffense, 
				severity, date, counts, flags);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("courtCase", courtCase)
		.addExpectedValue("offense", newOffense)
		.addExpectedValue("severity", severity)
		.addExpectedValue("date", date)
		.addExpectedValue("counts", counts)
		.addExpectedValue("flags", flags)
		.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the severity of a conviction.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ConvictionExistsException thrown when convictions exists
	 * @throws DocketExistsException thrown when docket exists
	 * @throws CourtCaseExistsException thrown when court case exists
	 */
	@Test
	public void testUpdateSeverity() throws DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		OffenseSeverity newSeverity = OffenseSeverity.MISDEMEANOR;
		
		// Action
		conviction = this.convictionService.update(conviction, offense, 
				newSeverity, date, counts, flags);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("courtCase", courtCase)
		.addExpectedValue("offense", offense)
		.addExpectedValue("severity", newSeverity)
		.addExpectedValue("date", date)
		.addExpectedValue("counts", counts)
		.addExpectedValue("flags", flags)
		.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the date of a conviction.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ConvictionExistsException thrown when convictions exists
	 * @throws DocketExistsException thrown when docket exists
	 * @throws CourtCaseExistsException thrown when court case exists
	 */
	@Test
	public void testUpdateDate() throws DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		Date newDate = this.parseDateText("01/04/2017");
		
		// Action
		conviction = this.convictionService.update(conviction, offense, 
				severity, newDate, counts, flags);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("courtCase", courtCase)
		.addExpectedValue("offense", offense)
		.addExpectedValue("severity", severity)
		.addExpectedValue("date", newDate)
		.addExpectedValue("counts", counts)
		.addExpectedValue("flags", flags)
		.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the counts of a conviction.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ConvictionExistsException thrown when convictions exists
	 * @throws DocketExistsException thrown when docket exists
	 * @throws CourtCaseExistsException thrown when court case exists
	 */
	@Test
	public void testUpdateCounts() throws DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				 mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		Integer newCounts = 2;		
		
		// Action
		conviction = this.convictionService.update(conviction, offense, 
				severity, date, newCounts, flags);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("courtCase", courtCase)
		.addExpectedValue("offense", offense)
		.addExpectedValue("severity", severity)
		.addExpectedValue("date", date)
		.addExpectedValue("counts", newCounts)
		.addExpectedValue("flags", flags)
		.performAssertions(conviction);
	}

	/**
	 * Tests the update of the flags of a conviction.
	 * 
	 * @throws DuplicateEntityFoundException thrown when entity already exists 
	 * @throws ConvictionExistsException thrown when convictions exists
	 * @throws DocketExistsException thrown when docket exists
	 * @throws CourtCaseExistsException thrown when court case exists
	 */
	@Test
	public void testUpdateFlags() throws DuplicateEntityFoundException, 
			ConvictionExistsException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person defendant = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel courtCasePersonnel = new CourtCasePersonnel();
		courtCasePersonnel.setJudge(judge);
		Country unitedStates = this.countryDelegate.create(
				"United States", "US", true);
		State mt = this.stateDelegate.create(
				"Montana", "MT", unitedStates, true, true);
		City helena = this.cityDelegate.create("Helena",
				true, mt, unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate
				.create(helena, "59602", null, true);
		
		Address courtAddress = this.addressDelegate.findOrCreate(
				"1000", null, null, BuildingCategory.APARTMENT,
				 mt59602);
		Organization courtOrg = this.organizationDelegate
				.create("Some Court", "SC", null);
		Location courtLoc = this.locationDelegate.create(
				courtOrg, null, courtAddress);
		Court court = this.courtDelegate.create("Some Court", 
				CourtCategory.COUNTY, courtLoc);
		Docket docket = this.docketDelegate.create(defendant, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Offense offense = this.offenseDelegate.create("Some Offense", 
				"Some Offense", null, OffenseClassification.WEAPON, "Code-123", 
				true);
		OffenseSeverity severity = OffenseSeverity.FELONY;
		Date date = this.parseDateText("01/03/2017");
		Integer counts = 1;
		ConvictionFlags flags = new ConvictionFlags();
		flags.setParoleIneligible(false);
		flags.setSexualOffense(false);
		flags.setSupervisedReleaseIneligible(false);
		flags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, flags);
		ConvictionFlags newFlags = new ConvictionFlags();
		newFlags.setParoleIneligible(true);
		newFlags.setSexualOffense(false);
		newFlags.setSupervisedReleaseIneligible(false);
		newFlags.setViolentOffense(true);	
		
		// Action
		conviction = this.convictionService.update(conviction, offense, 
				severity, date, counts, newFlags);
		
		// Assertions
		PropertyValueAsserter.create()
		.addExpectedValue("courtCase", courtCase)
		.addExpectedValue("offense", offense)
		.addExpectedValue("severity", severity)
		.addExpectedValue("date", date)
		.addExpectedValue("counts", counts)
		.addExpectedValue("flags", newFlags)
		.performAssertions(conviction);
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
