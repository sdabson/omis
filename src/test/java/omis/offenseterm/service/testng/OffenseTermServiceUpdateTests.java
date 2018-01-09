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
public class OffenseTermServiceUpdateTests 
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
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenseTermService")
	private OffenseTermService offenseTermService;
	
	
	/**
	 * Tests the update of the interstate number for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateInterStateNumber() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		String newInterStateNumber = "54321";
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, 
				newInterStateNumber, mt, pronouncementDate, sentenceReviewDate, 
				jurisdictionAuthority, dangerDesignator, personnel, flags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", newInterStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the interstate for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateInterState() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		State newInterState = this.stateDelegate.create(
				"Idaho", "ID", unitedStates, false, true);
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, 
				interStateNumber, newInterState, pronouncementDate, 
				sentenceReviewDate, jurisdictionAuthority, dangerDesignator, 
				personnel, flags, comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", newInterState)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the pronouncement date for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdatePronouncementDate() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Date newPronouncementDate = this.parseDateText("01/02/2017");
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, newPronouncementDate, sentenceReviewDate, 
				jurisdictionAuthority, dangerDesignator, personnel, flags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", newPronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the sentence review date for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateSentenceReviewDate() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Date newSentenceReviewDate = this.parseDateText("03/01/2017");
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, pronouncementDate, newSentenceReviewDate, 
				jurisdictionAuthority, dangerDesignator, personnel, flags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", newSentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the jurisdiction authority for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateJurisdictionAuthority() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		JurisdictionAuthority newJurisdictionAuthority = 
				JurisdictionAuthority.FEDERAL;
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, pronouncementDate, sentenceReviewDate, 
				newJurisdictionAuthority, dangerDesignator, personnel, flags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", newJurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the danger designator for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateDangerDesignator() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		OffenderDangerDesignator newDangerDesignator = 
				OffenderDangerDesignator.DANGEROUS;
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, pronouncementDate, sentenceReviewDate, 
				jurisdictionAuthority, newDangerDesignator, personnel, flags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", newDangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the personnel for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdatePersonnel() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		Person updateJudge = this.personDelegate.create("McJudgerson", "Judgey", 
				null, "Jr.");
		CourtCasePersonnel newPersonnel = new CourtCasePersonnel(updateJudge, 
				null, null);
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, pronouncementDate, sentenceReviewDate, 
				jurisdictionAuthority, dangerDesignator, newPersonnel, flags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", newPersonnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the flags for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateFlags() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		CourtCaseFlags newFlags = new CourtCaseFlags(true, true, true, true);
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, pronouncementDate, sentenceReviewDate, 
				jurisdictionAuthority, dangerDesignator, personnel, newFlags, 
				comments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", comments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", newFlags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
	}
	
	/**
	 * Tests the update of the comments for a court case.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DocketExistsException if docket exists
	 * @throws CourtCaseExistsException  if court case exists
	 */
	@Test
	public void testUpdateComments() 
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		// Arrangements
		Person person = this.personDelegate.create("Smith", 
				"John", "Jay", null);
		Date pronouncementDate = this.parseDateText("01/01/2017");
		Person judge = this.personDelegate.create("McJudgerson", "Judgey", null, 
				null);
		CourtCasePersonnel personnel = new CourtCasePersonnel(judge, null, 
				null);
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
		CourtCaseFlags flags = new CourtCaseFlags(false, false, false, false);
		String comments = "Words";
		Docket docket = this.docketDelegate.create(person, court, docketValue);
		CourtCase courtCase = this.courtCaseDelegate.create(docket, 
				interStateNumber, mt, pronouncementDate, jurisdictionAuthority, 
				sentenceReviewDate, dangerDesignator, personnel, flags, 
				comments);
		String newComments = "Other Words";
		
		// Action
		courtCase = this.offenseTermService.update(courtCase, interStateNumber, 
				mt, pronouncementDate, sentenceReviewDate, 
				jurisdictionAuthority, dangerDesignator, personnel, flags, 
				newComments);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("comments", newComments)
			.addExpectedValue("dangerDesignator", dangerDesignator)
			.addExpectedValue("docket.court", court)
			.addExpectedValue("docket.person", person)
			.addExpectedValue("docket.value", docketValue)
			.addExpectedValue("flags", flags)
			.addExpectedValue("interState", mt)
			.addExpectedValue("interStateNumber", interStateNumber)
			.addExpectedValue("jurisdictionAuthority", jurisdictionAuthority)
			.addExpectedValue("personnel", personnel)
			.addExpectedValue("pronouncementDate", pronouncementDate)
			.addExpectedValue("sentenceReviewDate", sentenceReviewDate)
			.performAssertions(courtCase);
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
