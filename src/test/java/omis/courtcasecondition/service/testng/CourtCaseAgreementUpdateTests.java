package omis.courtcasecondition.service.testng;

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
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementCategory;
import omis.condition.domain.AgreementNote;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.courtcasecondition.service.CourtCaseAgreementService;
import omis.courtcasecondition.service.delegate.CourtCaseAgreementCategoryDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
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

/**
 * Court Case Condition Update Tests.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 9, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseAgreementUpdateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("courtCaseAgreementService")
	private CourtCaseAgreementService courtCaseAgreementService;
	
	@Autowired
	private CourtCaseAgreementCategoryDelegate
		courtCaseAgreementCategoryDelegate;
	
	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private CourtDelegate courtDelegate;
	
	@Autowired
	private DocketDelegate docketDelegate;
	
	@Autowired
	private LocationDelegate locationDelegate;
	
	@Autowired
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	private AddressDelegate addressDelegate;
	
	@Autowired
	private CountryDelegate countryDelegate;
	
	@Autowired
	private StateDelegate stateDelegate;
	
	@Autowired
	private CityDelegate cityDelegate;
	
	@Autowired
	private ZipCodeDelegate zipCodeDelegate;
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is
	 * found
	 * @throws DocketExistsException - When a duplicate Docket is found
	 * @throws CourtCaseExistsException - When a duplicate Court Case is found
	 */
	@Test
	public void testCourtCaseAgreementUpdate()
			throws DuplicateEntityFoundException, DocketExistsException,
			CourtCaseExistsException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Person user = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final Agreement agreement = this.courtCaseAgreementService
				.createAgreement(offender, this.parseDateText("05/12/2017"), 
						this.parseDateText("05/15/2019"), null, null);
		final CourtCaseAgreementCategory courtCaseAgreementCategoryOld =
				this.courtCaseAgreementCategoryDelegate.create(
						"Court Case Agreement Category Old");
		final Organization organization = this.organizationDelegate.create(
				"Batcave", "TBC", null);
		final Country country = this.countryDelegate.create(
				"Country", "USA", true);
		final State state = this.stateDelegate.create(
				"State", "ST", country, true, true);
		final City city = this.cityDelegate.create(
				"City", true, state, country);
		final ZipCode zipCode = this.zipCodeDelegate.create(
				city, "12345", null, true);
		final Address address = this.addressDelegate.findOrCreate(
				"123", "321", null, null,  zipCode);
		final Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		final Court courtOld = this.courtDelegate.create("Court Of Injustice!",
				CourtCategory.FEDERAL, location);
		final Docket docketOld = this.docketDelegate.create(user, courtOld,
				"Docketty Doo Old n' Do");
		CourtCaseAgreement courtCaseAgreement =
				this.courtCaseAgreementService.createCourtCaseAgreement(
						agreement, docketOld, new Date(),
						courtCaseAgreementCategoryOld);
		final CourtCaseAgreementCategory courtCaseAgreementCategory =
				this.courtCaseAgreementCategoryDelegate.create(
						"Court Case Agreement Category");
		final Court court = this.courtDelegate.create("Court Of Justice!",
				CourtCategory.CITY, location);
		final Docket docket = this.docketDelegate.create(user, court,
				"Docketty Doo");
		
		courtCaseAgreement = this.courtCaseAgreementService
				.updateCourtCaseAgreement(courtCaseAgreement, docket,
						new Date(),
						courtCaseAgreementCategory);
		
		assert agreement.equals(courtCaseAgreement.getAgreement())
		: String.format("Wrong agreement for courtCaseAgreement: %d found; "
				+ "%d expected",
				courtCaseAgreement.getAgreement().getId(), agreement.getId());
		assert docket.equals(courtCaseAgreement.getDocket())
		: String.format("Wrong courtCase for courtCaseAgreement: %s found; "
				+ "%s expected",
				courtCaseAgreement.getDocket().getValue(),
				docket.getValue());
		assert courtCaseAgreementCategory.equals(
				courtCaseAgreement.getCourtCaseAgreementCategory())
		: String.format("Wrong courtCaseAgreementCategory for "
				+ "courtCaseAgreement: %s found; %s expected",
				courtCaseAgreement.getCourtCaseAgreementCategory().getName(),
				courtCaseAgreementCategory.getName());
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is
	 * found
	 */
	@Test
	public void testAgreementUpdate() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Date startDate = this.parseDateText("05/12/2017");
		final Date endDate = this.parseDateText("05/15/2019");
		final String description = "descriptionny-dooooo";
		final AgreementCategory category = AgreementCategory.COURT_CASE;
		
		Agreement agreement = this.courtCaseAgreementService
				.createAgreement(offender, this.parseDateText("01/12/2014"),
						this.parseDateText("03/10/2016"), "old",
						AgreementCategory.BOARD_PARDONS_PAROLE);
		
		agreement = this.courtCaseAgreementService.updateAgreement(
				agreement, startDate, endDate, description, category);
		
		assert offender.equals(agreement.getOffender())
		: String.format("Wrong offender for agreement: %d found; %d expected",
				agreement.getOffender().getId(), offender.getId());
		assert startDate.equals(agreement.getDateRange().getStartDate())
		: String.format("Wrong startDate for agreement: %s found; %s expected",
				agreement.getDateRange().getStartDate(), startDate);
		assert endDate.equals(agreement.getDateRange().getEndDate())
		: String.format("Wrong endDate for agreement: %s found; %s expected",
				agreement.getDateRange().getEndDate(), endDate);
		assert description.equals(agreement.getDescription())
		: String.format("Wrong description for agreement: %s found; "
				+ "%s expected",
				agreement.getDescription(), description);
		assert category.equals(agreement.getCategory())
		: String.format("Wrong category for agreement: %s found; %s expected",
				agreement.getCategory(), category);
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is
	 * found
	 */
	@Test
	public void testAgreementNoteUpdate() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.courtCaseAgreementService
				.createAgreement(offender, this.parseDateText("05/12/2017"), 
						this.parseDateText("05/15/2019"), null, null);
		final String description = "Agreement Note Description";
		final Date date = this.parseDateText("06/10/2017");
		AgreementNote agreementNote = this.courtCaseAgreementService
				.createNote(agreement, "Say what!",
						this.parseDateText("06/02/2012"));
		
		agreementNote = this.courtCaseAgreementService.updateNote(
				agreementNote, description, date);
		
		assert agreement.equals(agreementNote.getAgreement())
		: String.format("Wrong agreement for agreementNote: %d found; "
				+ "%d expected",
				agreementNote.getAgreement().getId(), agreement.getId());
		assert description.equals(agreementNote.getDescription())
		: String.format("Wrong description for agreementNote: %s found; "
				+ "%s expected",
				agreementNote.getDescription(), description);
		assert date.equals(agreementNote.getDate())
		: String.format("Wrong date for agreementNote: %s found; %s expected",
				agreementNote.getDate(), date);
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
}
