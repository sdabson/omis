package omis.earlyreleasetracking.service.testng;

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
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestCategory;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;
import omis.earlyreleasetracking.exception.EarlyReleaseRequestExistsException;
import omis.earlyreleasetracking.service.EarlyReleaseRequestService;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseJudgeResponseCategoryDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseRequestDelegate;
import omis.earlyreleasetracking.service.delegate.EarlyReleaseStatusCategoryDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update Early Release Requests.
 *
 * @author Annie Wahl
 * @version 0.0.1 (Feb 19, 2019)
 * @since OMIS 3.0
 */
@Test
public class EarlyReleaseRequestServiceUpdateEarlyReleaseRequestTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */


	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	private LocationDelegate locationDelegate;
	
	@Autowired
	private CountryDelegate countryDelegate;
	
	@Autowired
	private StateDelegate stateDelegate;
	
	@Autowired
	private CityDelegate cityDelegate;
	
	@Autowired
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	private AddressDelegate addressDelegate;
	
	@Autowired
	private CourtDelegate courtDelegate;
	
	@Autowired
	private DocketDelegate docketDelegate;
	
	@Autowired
	private EarlyReleaseStatusCategoryDelegate
				earlyReleaseStatusCategoryDelegate;
	
	@Autowired
	private EarlyReleaseJudgeResponseCategoryDelegate
				earlyReleaseJudgeResponseCategoryDelegate;
	
	@Autowired
	private UserAccountDelegate userAccountDelegate;

	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private EarlyReleaseRequestDelegate earlyReleaseRequestDelegate;

	/* Services. */

	@Autowired
	@Qualifier("earlyReleaseRequestService")
	private EarlyReleaseRequestService earlyReleaseRequestService;

	/* Test methods. */

	@Test
	public void testUpdateEarlyReleaseRequest()
			throws EarlyReleaseRequestExistsException,
			DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		EarlyReleaseRequest earlyReleaseRequest = this.getEarlyReleaseRequest();

		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Cowboy", "Woody", null, null);
		Organization organization = this.organizationDelegate.create(
				"Organization2", "org2", null);
		Country country = this.countryDelegate.create("Canada", "CAN", true);
		State state = this.stateDelegate.create("Canadian State", "Place",
				country, true, true);
		City city = this.cityDelegate.create("City in Canada", true,
				state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(
				city, "1215", null, true);
		Address address = this.addressDelegate.findOrCreate("ABC", "XYZ", null,
				null, zipCode);
		Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		Court court = this.courtDelegate.create(
				"Court Bort", CourtCategory.CITY, location);
		
		Docket docket = this.docketDelegate.create(offender, court, "Docket-o");
		Date requestDate = this.parseDateText("03/05/2019");
		EarlyReleaseRequestCategory category =
				EarlyReleaseRequestCategory.CONDITIONAL_DISCHARGE;
		EarlyReleaseStatusCategory requestStatus =
				this.earlyReleaseStatusCategoryDelegate.create(
						"New Category", true);
		EarlyReleaseJudgeResponseCategory judgeResponse = 
				this.earlyReleaseJudgeResponseCategoryDelegate.create(
						"New Response", true);
		Person user = this.personDelegate.create(
				"McOtherson", "Other", null, null);
		UserAccount requestBy = this.userAccountDelegate.create(
				user, "CIOTHER", "password1", this.parseDateText("01/01/2020"),
				0, true);
		Date approvalDate = this.parseDateText("05/10/2020");
		String comments = "Slightly different Early Releast Request Comments";

		// Action
		earlyReleaseRequest = this.earlyReleaseRequestService
				.updateEarlyReleaseRequest(earlyReleaseRequest, docket,
						requestDate, category, requestStatus, judgeResponse,
						requestBy, approvalDate, comments);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("docket", docket)
			.addExpectedValue("requestDate", requestDate)
			.addExpectedValue("category", category)
			.addExpectedValue("requestStatus", requestStatus)
			.addExpectedValue("judgeResponse", judgeResponse)
			.addExpectedValue("requestBy", requestBy)
			.addExpectedValue("approvalDate", approvalDate)
			.addExpectedValue("comments", comments)
			.performAssertions(earlyReleaseRequest);
	}

	@Test(expectedExceptions = {EarlyReleaseRequestExistsException.class})
	public void testEarlyReleaseRequestExistsException()
			throws EarlyReleaseRequestExistsException,
			DuplicateEntityFoundException, DocketExistsException {
		// Arrangements
		EarlyReleaseRequest earlyReleaseRequest = this.getEarlyReleaseRequest();
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Cowboy", "Woody", null, null);
		Organization organization = this.organizationDelegate.create(
				"Organization2", "org2", null);
		Country country = this.countryDelegate.create("Canada", "CAN", true);
		State state = this.stateDelegate.create("Canadian State", "Place",
				country, true, true);
		City city = this.cityDelegate.create("City in Canada", true,
				state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(
				city, "1215", null, true);
		Address address = this.addressDelegate.findOrCreate("ABC", "XYZ", null,
				null, zipCode);
		Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		Court court = this.courtDelegate.create(
				"Court Bort", CourtCategory.CITY, location);
		
		Docket docket = this.docketDelegate.create(offender, court, "Docket-o");
		Date requestDate = this.parseDateText("03/05/2019");
		EarlyReleaseRequestCategory category =
				EarlyReleaseRequestCategory.CONDITIONAL_DISCHARGE;
		EarlyReleaseStatusCategory requestStatus =
				this.earlyReleaseStatusCategoryDelegate.create(
						"New Category", true);
		EarlyReleaseJudgeResponseCategory judgeResponse = 
				this.earlyReleaseJudgeResponseCategoryDelegate.create(
						"New Response", true);
		Person user = this.personDelegate.create(
				"McOtherson", "Other", null, null);
		UserAccount requestBy = this.userAccountDelegate.create(
				user, "CIOTHER", "password1", this.parseDateText("01/01/2020"),
				0, true);
		Date approvalDate = this.parseDateText("05/10/2020");
		String comments = "Slightly different Early Releast Request Comments";
		this.earlyReleaseRequestDelegate.create(docket, requestDate, category,
				requestStatus, judgeResponse, requestBy,
				approvalDate, comments);
		
		//Action
		this.earlyReleaseRequestService.updateEarlyReleaseRequest(
				earlyReleaseRequest, docket, requestDate, category,
				requestStatus, judgeResponse, requestBy,
				approvalDate, comments);
	}

	private EarlyReleaseRequest getEarlyReleaseRequest()
			throws EarlyReleaseRequestExistsException,
			DuplicateEntityFoundException, DocketExistsException {
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Lightyear", "Buzz", null, null);
		Organization organization = this.organizationDelegate.create(
				"Organization", "org", null);
		Country country = this.countryDelegate.create("Country", "USA", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(
				city, "12345", null, true);
		Address address = this.addressDelegate.findOrCreate("123", "321", null,
				null, zipCode);
		Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		Court court = this.courtDelegate.create(
				"Court", CourtCategory.CITY, location);
		
		Docket docket = this.docketDelegate.create(offender, court, "Docket");
		Date requestDate = this.parseDateText("02/19/2019");
		EarlyReleaseRequestCategory category =
				EarlyReleaseRequestCategory.EARLY_RELEASE;
		EarlyReleaseStatusCategory requestStatus =
				this.earlyReleaseStatusCategoryDelegate.create(
						"Status Category", true);
		EarlyReleaseJudgeResponseCategory judgeResponse = 
				this.earlyReleaseJudgeResponseCategoryDelegate.create(
						"Judge Response", true);
		Person user = this.personDelegate.create(
				"McUserson", "User", null, null);
		UserAccount requestBy = this.userAccountDelegate.create(
				user, "CIUSER", "guest", this.parseDateText("01/01/2020"),
				0, true);
		Date approvalDate = this.parseDateText("02/28/2019");
		String comments = "Early Release Request Comments";
		return earlyReleaseRequestDelegate.create(docket, requestDate, category,
				requestStatus, judgeResponse, requestBy,
				approvalDate, comments);
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