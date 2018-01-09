package omis.probationterm.service.testng;

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
import omis.offender.domain.Offender;
import omis.offender.exception.OffenderExistsException;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.exception.ProbationTermConflictException;
import omis.probationterm.exception.ProbationTermExistsAfterException;
import omis.probationterm.exception.ProbationTermExistsException;
import omis.probationterm.service.ProbationTermService;
import omis.probationterm.service.delegate.ProbationTermDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.term.domain.component.Term;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Unit tests for service operations to update probation terms.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.0.1 (Nov 21, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermServiceUpdateTests 
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
	@Qualifier("docketDelegate")
	private DocketDelegate docketDelegate;
	
	@Autowired
	@Qualifier("courtCaseDelegate")
	private CourtCaseDelegate courtCaseDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("probationTermDelegate")
	private ProbationTermDelegate probationTermDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("probationTermService")
	private ProbationTermService probationTermService;
	
	/* Tests. */
	
	/**
	 * Tests updating of a probation term.
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CourtCaseExistsException
	 * @throws DocketExistsException
	 * @throws ProbationTermExistsException
	 * @throws ProbationTermConflictException
	 * @throws ProbationTermExistsAfterException
	 * @throws OffenderExistsException
	 */
	@Test
	public void testUpdate() throws DuplicateEntityFoundException, 
		CourtCaseExistsException, DocketExistsException, 
		ProbationTermExistsException, ProbationTermConflictException, 
		ProbationTermExistsAfterException, OffenderExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
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
		Docket docket = this.docketDelegate.create(offender, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Term term = new Term(5, 0, 0);
		Date startDate = this.parseDateText("02/01/2017"); 
		Date terminationDate = null;
		Date expirationDate = this.parseDateText("02/01/2022");
		Integer jailCredit = 0;
		Integer sentenceDays = 1825;
		ProbationTerm probationTerm = this.probationTermDelegate.create(
				offender, courtCase, term, startDate, terminationDate,
				expirationDate, jailCredit, sentenceDays);
		terminationDate = this.parseDateText("02/01/2020");
		
		// Action
		probationTerm = this.probationTermService.update(probationTerm, term, 
				startDate, terminationDate, expirationDate, jailCredit,
				sentenceDays);
		
		// Assert
		assertValues(probationTerm, offender, courtCase, term, startDate,
				terminationDate, expirationDate, jailCredit, sentenceDays);
	}
	
	@Test(expectedExceptions = {ProbationTermExistsException.class})
	public void testProbationTermExistsException() 
			throws DuplicateEntityFoundException,
			CourtCaseExistsException, DocketExistsException,
			ProbationTermExistsException, ProbationTermConflictException,
			ProbationTermExistsAfterException, OffenderExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
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
		Docket docket = this.docketDelegate.create(offender, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Term term = new Term(5, 0, 0);
		Date startDate = this.parseDateText("02/01/2017"); 
		Date terminationDate = null;
		Date expirationDate = this.parseDateText("02/01/2027");
		Integer jailCredit = 0;
		Integer sentenceDays = 1825;
		this.probationTermDelegate.create(offender, courtCase, term, startDate,
				terminationDate, expirationDate, jailCredit, sentenceDays);
		Date secondStartDate = this.parseDateText("01/01/2017"); 
		Date secondTerminationDate = this.parseDateText("01/31/2017");
		ProbationTerm probationTerm = this.probationTermDelegate.create(
				offender, courtCase, term, secondStartDate,
				secondTerminationDate, expirationDate, jailCredit,
				sentenceDays);
		
		// Action
		this.probationTermService.update(probationTerm, term, startDate,
				terminationDate, expirationDate, jailCredit, sentenceDays);
	}
	
	@Test(expectedExceptions = {ProbationTermConflictException.class})
	public void testProbationTermConflictException() 
			throws DuplicateEntityFoundException,
			CourtCaseExistsException, DocketExistsException,
			ProbationTermExistsException, ProbationTermConflictException,
			ProbationTermExistsAfterException, OffenderExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
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
		Docket docket = this.docketDelegate.create(offender, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Term term = new Term(5, 0, 0);
		Date startDate = this.parseDateText("02/01/2017"); 
		Date terminationDate = this.parseDateText("02/05/2017");
		Date expirationDate = this.parseDateText("02/01/2027");
		Integer jailCredit = 0;
		Integer sentenceDays = 1825;
		this.probationTermDelegate.create(offender, courtCase, term, startDate, 
				terminationDate, expirationDate, jailCredit, sentenceDays);
		startDate = this.parseDateText("01/01/2017"); 
		terminationDate = this.parseDateText("01/31/2017");
		ProbationTerm probationTerm = this.probationTermDelegate.create(
				offender, courtCase, term, startDate, terminationDate,
				expirationDate, jailCredit, sentenceDays);
		terminationDate = this.parseDateText("03/01/2017");
		
		// Action
		this.probationTermService.update(probationTerm, term, startDate,
				terminationDate, expirationDate, jailCredit, sentenceDays);
		
	}
	
	@Test(expectedExceptions = {ProbationTermExistsAfterException.class})
	public void testProbationTermExistsAfterException() 
			throws DuplicateEntityFoundException,
			CourtCaseExistsException, DocketExistsException,
			ProbationTermExistsException, ProbationTermConflictException,
			ProbationTermExistsAfterException, OffenderExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
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
		Docket docket = this.docketDelegate.create(offender, court, 
				"Docket 1234");
		CourtCase courtCase = this.courtCaseDelegate.create(docket, null, null, 
				pronouncementDate, null, null, null, courtCasePersonnel, 
				new CourtCaseFlags(), null);
		Term term = new Term(5, 0, 0);
		Date startDate = this.parseDateText("02/01/2017"); 
		Date terminationDate = this.parseDateText("03/01/2017");
		Date expirationDate = this.parseDateText("02/01/2027");
		Integer jailCredit = 0;
		Integer sentenceDays = 1825;
		this.probationTermDelegate.create(offender, courtCase, term, startDate,
				terminationDate, expirationDate, jailCredit, sentenceDays);
		startDate = this.parseDateText("01/01/2017"); 
		terminationDate = this.parseDateText("01/31/2017");
		ProbationTerm probationTerm = this.probationTermDelegate.create(
				offender, courtCase, term, startDate, terminationDate,
				expirationDate, jailCredit, sentenceDays);
		terminationDate = null;
		
		// Action
		this.probationTermService.update(probationTerm, term, startDate,
				terminationDate, expirationDate, jailCredit, sentenceDays);
		
	}
	
	/* Helper methods. */

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
	
	// Asserts values match expected values
	private void assertValues(final ProbationTerm probationTerm, 
			final Offender offender, final CourtCase courtCase, final Term term,
			final Date startDate, Date terminationDate,
			final Date expirationDate, final Integer jailCredit,
			final Integer sentenceDays) {
		assert offender.equals(probationTerm.getOffender()) : String.format(
				"Wrong offender: %d found; %d expected", 
				probationTerm.getOffender().getOffenderNumber(), 
				offender.getOffenderNumber());
		assert courtCase.equals(probationTerm.getCourtCase()) : String.format(
				"Wrong court case: %s found; %s expected", 
				probationTerm.getCourtCase().getDocket().getValue(), 
				courtCase.getDocket().getValue());
		assert term.equals(probationTerm.getTerm()) : String.format(
				"Wrong term: %d,%d,%d found; %d,%d,%d expected", 
				probationTerm.getTerm().getYears(), 
				probationTerm.getTerm().getMonths(), 
				probationTerm.getTerm().getDays(), 
				term.getYears(), term.getMonths(), term.getDays());
		assert this.dateEquals(probationTerm.getStartDate(), startDate)
			: String.format("Wrong start date: %s found; %s expected",
				this.formatDate(probationTerm.getStartDate()),
				this.formatDate(startDate));
		assert this.dateEquals(probationTerm.getTerminationDate(),
			terminationDate)
			: String.format("Wrong termination date: %s found; %s expected",
				this.formatDate(probationTerm.getTerminationDate()),
				this.formatDate(terminationDate));
		assert expirationDate.equals(probationTerm.getExpirationDate()) :
				String.format("Wrong expiration date: %s found; %s expected", 
						formatDate(probationTerm.getExpirationDate()),
						formatDate(expirationDate));
		assert jailCredit.equals(probationTerm.getJailCredit()) : String.format(
				"Wrong jail credit: %d found; %d expected", 
				probationTerm.getJailCredit(), jailCredit);
		assert sentenceDays.equals(probationTerm.getSentenceDays()) : 
				String.format("Wrong sentence days: %d found; %d expected", 
						probationTerm.getSentenceDays(), sentenceDays);
	}
	
	// Formats a date
	private String formatDate(final Date date) {
		if (date == null) {
			return "null";
		}
		return String.format("%1$tm/%1$td/%1$ty", date);
	}
	
	// Performs null safe equals() operations on dates
	private boolean dateEquals(final Date date1, final Date date2) {
		if ((date1 != null && date1.equals(date2))
				|| (date1 == null && date2 == null)) {
			return true;
		} else {
			return false;
		}
	}
}