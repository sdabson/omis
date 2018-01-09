
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
import omis.condition.domain.AgreementNote;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.AgreementNoteDelegate;
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
import omis.courtcasecondition.service.delegate.CourtCaseAgreementDelegate;
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
 * Court Case Condition Remove Tests.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 9, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseAgreementRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	@Autowired
	@Qualifier("courtCaseAgreementService")
	private CourtCaseAgreementService courtCaseAgreementService;
	
	@Autowired
	private CourtCaseAgreementDelegate courtCaseAgreementDelegate;
	
	@Autowired
	private CourtCaseAgreementCategoryDelegate
		courtCaseAgreementCategoryDelegate;

	@Autowired
	private AgreementDelegate agreementDelegate;
	
	@Autowired
	private AgreementNoteDelegate agreementNoteDelegate;
	
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
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 * @throws DocketExistsException - When a duplicate Docket is found
	 * @throws CourtCaseExistsException - When a duplicate Court Case is found
	 */
	@Test
	public void testCourtCaseAgreementRemove()
			throws DuplicateEntityFoundException, DocketExistsException,
			CourtCaseExistsException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Person user = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final Agreement agreement = this.courtCaseAgreementService
				.createAgreement(offender, this.parseDateText("05/12/2017"), 
						this.parseDateText("05/15/2019"), null, null);
		final CourtCaseAgreementCategory courtCaseAgreementCategory =
				this.courtCaseAgreementCategoryDelegate.create(
						"Court Case Agreement Category");
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
				"123value", null, null,
				null, zipCode);
		final Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		final Court court = this.courtDelegate.create("Court Of Justice!",
				CourtCategory.CITY, location);
		final Docket docket = this.docketDelegate.create(user, court,
				"Docketty Doo");
		final CourtCaseAgreement courtCaseAgreement =
				this.courtCaseAgreementService.createCourtCaseAgreement(
						agreement, docket, new Date(),
						courtCaseAgreementCategory);
		
		this.courtCaseAgreementService.removeCourtCaseAgreement(
				courtCaseAgreement);
		
		assert !this.courtCaseAgreementDelegate.findByOffender(offender)
		.contains(courtCaseAgreement) : "CourtCaseAgreement was not removed!";
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 */
	@Test
	public void testAgreementRemove() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Date startDate = this.parseDateText("05/12/2017");
		final Date endDate = this.parseDateText("05/15/2019");
		
		final Agreement agreement = this.courtCaseAgreementService
				.createAgreement(offender, startDate, endDate, null, null);
		
		this.courtCaseAgreementService.removeAgreement(agreement);
		
		assert !this.agreementDelegate.findByOffender(offender)
			.contains(agreement) : "Agreement was not removed!";
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 */
	@Test
	public void testAgreementNoteRemove() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Agreement agreement = this.courtCaseAgreementService
				.createAgreement(offender, this.parseDateText("05/12/2017"), 
						this.parseDateText("05/15/2019"), null, null);
		final String description = "Agr eement Note Description";
		final Date date = this.parseDateText("06/10/2017");
		
		final AgreementNote agreementNote = this.courtCaseAgreementService
				.createNote(agreement, description, date);
		
		this.courtCaseAgreementService.removeNote(agreementNote);
		
		assert !this.agreementNoteDelegate.findByAgreement(agreement)
			.contains(agreementNote) : "Agreement Note Was Not Removed!";
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}
