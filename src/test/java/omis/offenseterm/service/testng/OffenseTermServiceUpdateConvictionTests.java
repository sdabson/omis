package omis.offenseterm.service.testng;

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
import omis.conviction.service.delegate.ConvictionDelegate;
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
import omis.offenseterm.service.OffenseTermService;
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
 * Tests offense term service.
 *
 * @author Josh Divine 
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test
public class OffenseTermServiceUpdateConvictionTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
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
	@Qualifier("courtCaseDelegate")
	private CourtCaseDelegate courtCaseDelegate;
	
	@Autowired
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
	@Autowired
	@Qualifier("offenseDelegate")
	private OffenseDelegate offenseDelegate;
	
	@Autowired
	@Qualifier("convictionDelegate")
	private ConvictionDelegate convictionDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenseTermService")
	private OffenseTermService offenseTermService;
	
	
	/**
	 * Tests the update of the offense for a conviction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 */
	@Test
	public void testUpdateConvictionOffense() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
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
		String docketValue = "12345";
		String interStateNumber = "12345";
		Date sentenceReviewDate = this.parseDateText("04/01/2017");
		JurisdictionAuthority jurisdictionAuthority 
			= JurisdictionAuthority.STATE;
		OffenderDangerDesignator dangerDesignator 
			= OffenderDangerDesignator.NON_DANGEROUS;
		CourtCaseFlags flags = new CourtCaseFlags();
		flags.setConvictionOverturned(false);
		flags.setCriminallyConvictedYouth(false);
		flags.setDismissed(false);
		flags.setYouthTransfer(false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Date date = this.parseDateText("12/25/2016");
		Integer counts = 3;
		OffenseSeverity severity = OffenseSeverity.MISDEMEANOR;
		ConvictionFlags convictionFlags = new ConvictionFlags();
		convictionFlags.setParoleIneligible(false);
		convictionFlags.setSexualOffense(false);
		convictionFlags.setSupervisedReleaseIneligible(false);
		convictionFlags.setViolentOffense(false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, convictionFlags);
		Offense newOffense = this.offenseDelegate.create("Rugs", "Rugs", 
				null, OffenseClassification.PROPERTY, "4321", true);
		
		// Action
		conviction = this.offenseTermService.updateConviction(conviction, 
				newOffense, date, counts, severity, convictionFlags); 
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("counts", counts)
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("date", date)
			.addExpectedValue("flags", convictionFlags)
			.addExpectedValue("offense", newOffense)
			.addExpectedValue("severity", severity)
			.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the date for a conviction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 */
	@Test
	public void testUpdateConvictionDate() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
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
		String docketValue = "12345";
		String interStateNumber = "12345";
		Date sentenceReviewDate = this.parseDateText("04/01/2017");
		JurisdictionAuthority jurisdictionAuthority 
			= JurisdictionAuthority.STATE;
		OffenderDangerDesignator dangerDesignator 
			= OffenderDangerDesignator.NON_DANGEROUS;
		CourtCaseFlags flags = new CourtCaseFlags();
		flags.setConvictionOverturned(false);
		flags.setCriminallyConvictedYouth(false);
		flags.setDismissed(false);
		flags.setYouthTransfer(false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Date date = this.parseDateText("12/25/2016");
		Integer counts = 3;
		OffenseSeverity severity = OffenseSeverity.MISDEMEANOR;
		ConvictionFlags convictionFlags = new ConvictionFlags(false, false, 
				false, false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, convictionFlags);
		Date newDate = this.parseDateText("12/24/2016");
		
		// Action
		conviction = this.offenseTermService.updateConviction(conviction, 
				offense, newDate, counts, severity, convictionFlags); 
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("counts", counts)
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("date", newDate)
			.addExpectedValue("flags", convictionFlags)
			.addExpectedValue("offense", offense)
			.addExpectedValue("severity", severity)
			.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the counts for a conviction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 */
	@Test
	public void testUpdateConvictionCounts() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
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
		String docketValue = "12345";
		String interStateNumber = "12345";
		Date sentenceReviewDate = this.parseDateText("04/01/2017");
		JurisdictionAuthority jurisdictionAuthority 
			= JurisdictionAuthority.STATE;
		OffenderDangerDesignator dangerDesignator 
			= OffenderDangerDesignator.NON_DANGEROUS;
		CourtCaseFlags flags = new CourtCaseFlags();
		flags.setConvictionOverturned(false);
		flags.setCriminallyConvictedYouth(false);
		flags.setDismissed(false);
		flags.setYouthTransfer(false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Date date = this.parseDateText("12/25/2016");
		Integer counts = 3;
		OffenseSeverity severity = OffenseSeverity.MISDEMEANOR;
		ConvictionFlags convictionFlags = new ConvictionFlags(false, false, 
				false, false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, convictionFlags);
		Integer newCounts = 2;
		
		// Action
		conviction = this.offenseTermService.updateConviction(conviction, 
				offense, date, newCounts, severity, convictionFlags); 
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("counts", newCounts)
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("date", date)
			.addExpectedValue("flags", convictionFlags)
			.addExpectedValue("offense", offense)
			.addExpectedValue("severity", severity)
			.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the severity for a conviction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 */
	@Test
	public void testUpdateConvictionSeverity() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
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
		String docketValue = "12345";
		String interStateNumber = "12345";
		Date sentenceReviewDate = this.parseDateText("04/01/2017");
		JurisdictionAuthority jurisdictionAuthority 
			= JurisdictionAuthority.STATE;
		OffenderDangerDesignator dangerDesignator 
			= OffenderDangerDesignator.NON_DANGEROUS;
		CourtCaseFlags flags = new CourtCaseFlags();
		flags.setConvictionOverturned(false);
		flags.setCriminallyConvictedYouth(false);
		flags.setDismissed(false);
		flags.setYouthTransfer(false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Date date = this.parseDateText("12/25/2016");
		Integer counts = 3;
		OffenseSeverity severity = OffenseSeverity.MISDEMEANOR;
		ConvictionFlags convictionFlags = new ConvictionFlags(false, false, 
				false, false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, convictionFlags);
		OffenseSeverity newSeverity = OffenseSeverity.FELONY;
		
		// Action
		conviction = this.offenseTermService.updateConviction(conviction, 
				offense, date, counts, newSeverity, convictionFlags); 
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("counts", counts)
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("date", date)
			.addExpectedValue("flags", convictionFlags)
			.addExpectedValue("offense", offense)
			.addExpectedValue("severity", newSeverity)
			.performAssertions(conviction);
	}
	
	/**
	 * Tests the update of the flags for a conviction.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 * @throws ConvictionExistsException if conviction exists
	 */
	@Test
	public void testUpdateConvictionFlags() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException, ConvictionExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel();
		personnel.setJudge(judge);
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
		String docketValue = "12345";
		String interStateNumber = "12345";
		Date sentenceReviewDate = this.parseDateText("04/01/2017");
		JurisdictionAuthority jurisdictionAuthority 
			= JurisdictionAuthority.STATE;
		OffenderDangerDesignator dangerDesignator 
			= OffenderDangerDesignator.NON_DANGEROUS;
		CourtCaseFlags flags = new CourtCaseFlags();
		flags.setConvictionOverturned(false);
		flags.setCriminallyConvictedYouth(false);
		flags.setDismissed(false);
		flags.setYouthTransfer(false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Offense offense = this.offenseDelegate.create("Drugs", "Drugs", null,
				OffenseClassification.DRUG, "1234", true);
		Date date = this.parseDateText("12/25/2016");
		Integer counts = 3;
		OffenseSeverity severity = OffenseSeverity.MISDEMEANOR;
		ConvictionFlags convictionFlags = new ConvictionFlags(false, false, 
				false, false);
		Conviction conviction = this.convictionDelegate.create(courtCase, 
				severity, offense, date, counts, convictionFlags);
		ConvictionFlags newConvictionFlags = new ConvictionFlags(true, true, 
				true, true);
		
		// Action
		conviction = this.offenseTermService.updateConviction(conviction, 
				offense, date, counts, severity, newConvictionFlags); 
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("counts", counts)
			.addExpectedValue("courtCase", courtCase)
			.addExpectedValue("date", date)
			.addExpectedValue("flags", newConvictionFlags)
			.addExpectedValue("offense", offense)
			.addExpectedValue("severity", severity)
			.performAssertions(conviction);
	}
	
	/* Helper methods */
	
	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}
