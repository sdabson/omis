package omis.warrant.service.testng;

import java.math.BigDecimal;
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
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionTitle;
import omis.condition.service.delegate.AgreementDelegate;
import omis.condition.service.delegate.ConditionClauseDelegate;
import omis.condition.service.delegate.ConditionDelegate;
import omis.condition.service.delegate.ConditionTitleDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.court.service.delegate.CourtDelegate;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.courtcase.service.delegate.CourtCaseDelegate;
import omis.datatype.DateRange;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.docket.service.delegate.DocketDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.jail.domain.Jail;
import omis.jail.domain.JailCategory;
import omis.jail.service.delegate.JailDelegate;
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
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;
import omis.warrant.domain.WarrantCauseViolation;
import omis.warrant.domain.WarrantNote;
import omis.warrant.domain.WarrantReasonCategory;
import omis.warrant.service.WarrantService;
import omis.warrant.service.delegate.WarrantDelegate;

/**
 * Warrant Service Remove Tests.
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 15, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantServiceRemoveTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	@Autowired
	@Qualifier("warrantService")
	private WarrantService warrantService;
	
	@Autowired
	private WarrantDelegate warrantDelegate;
	
	@Autowired
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	private PersonDelegate personDelegate;
	
	//for condition and courtCase creating for WarrantCauseViolations:
	@Autowired
	private ConditionDelegate conditionDelegate;
	
	@Autowired
	private AgreementDelegate agreementDelegate;
	
	@Autowired
	private CourtCaseDelegate courtCaseDelegate;
	
	@Autowired
	private CourtDelegate courtDelegate;
	
	@Autowired
	private DocketDelegate docketDelegate;
	
	@Autowired
	private ConditionClauseDelegate conditionClauseDelegate;
	
	@Autowired
	private ConditionTitleDelegate conditionTitleDelegate;
	
	//All this stuff just so we can create a jail for WarrantArrest
	@Autowired
	private JailDelegate jailDelegate;
	
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
	 */
	@Test
	public void testWarrantRemove() throws DuplicateEntityFoundException {
		final Warrant warrant = this.createWarrant();
		
		this.warrantService.remove(warrant);
		
		assert !this.warrantDelegate.findByOffender(warrant.getOffender())
			.contains(warrant)
			: "Warrant was not removed.";
		
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 */
	@Test
	public void testWarrantArrestRemove() throws DuplicateEntityFoundException {
		final Warrant warrant = this.createWarrant();
		final Date date = this.parseDateText("05/30/2017");
		final Date contactBy = this.parseDateText("06/01/2017");
		final Organization organization = this.organizationDelegate.create(
				"Arkham Asylum", "AA", null);
		final Country country = this.countryDelegate.create(
				"Country", "USA", true);
		final State state = this.stateDelegate.create(
				"State", "ST", country, true, true);
		final City city = this.cityDelegate.create(
				"City", true, state, country);
		final ZipCode zipCode = this.zipCodeDelegate.create(
				city, "12345", null, true);
		final Address address = this.addressDelegate.findOrCreate(
				"123", "321", null,
				null, zipCode);
		final Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		final Jail jail = this.jailDelegate.create("Arkham Asylum",
				location, JailCategory.COUNTY, true);
		
		final WarrantArrest warrantArrest = this.warrantService.createArrest(
				warrant, date, jail, contactBy);
		
		this.warrantService.removeArrest(warrantArrest);
		
		assert this.warrantService.findWarrantArrestByWarrant(warrant) == null
		: "WarrantArrest was not removed.";
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 */
	@Test
	public void testWarrantNoteRemove() throws DuplicateEntityFoundException {
		final Warrant warrant = this.createWarrant();
		final String note = "This is a Note!";
		final Date date = this.parseDateText("05/05/2017");
		
		WarrantNote warrantNote = this.warrantService
				.createWarrantNote(warrant, note, date);
		
		this.warrantService.removeWarrantNote(warrantNote);
		
		assert !this.warrantService.findWarrantNotesByWarrant(warrant)
			.contains(warrantNote)
		: "WarrantNote was not removed.";
	}
	
	/**
	 * @throws DuplicateEntityFoundException - When a duplicate entity is found
	 * @throws DocketExistsException - When a duplicate Docket is found
	 * @throws CourtCaseExistsException - When a duplicate Court Case is found
	 */
	@Test
	public void testWarrantCauseViolationRemove()
			throws DuplicateEntityFoundException, DocketExistsException, 
			CourtCaseExistsException {
		final Warrant warrant = this.createWarrant();
		final Person person = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
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
				"123", "321", null,
				null, zipCode);
		final Location location = this.locationDelegate.create(organization,
				new DateRange(this.parseDateText("01/01/2001"),
						this.parseDateText("01/01/2020")), address);
		final Court court = this.courtDelegate.create("Gotham City Court",
				CourtCategory.CITY, location);
		final Docket docket = this.docketDelegate.create(
				person, court, "ARK-HAM9");
		final CourtCase courtCase = this.courtCaseDelegate.create(
				docket, null, null, null, null, null, null, null, null, null);
		final Agreement agreement = this.agreementDelegate
				.create(warrant.getOffender(),
						this.parseDateText("05/05/2017"),
						this.parseDateText("05/05/2018"), null, null);
		final ConditionTitle conditionTitle =
				this.conditionTitleDelegate.create("Condition Title");
		final ConditionClause conditionClause = this.conditionClauseDelegate
				.create("Condition Clause Description", conditionTitle, true);
		final Condition condition = this.conditionDelegate
				.create(agreement, "Clause For Condition", conditionClause,
						ConditionCategory.STANDARD, true);
		final String description = "WCV Description";
		
		
		final WarrantCauseViolation warrantCauseViolation = this.warrantService
				.createWarrantCauseViolation(warrant, courtCase, condition,
						description);
		
		this.warrantService.removeWarrantCauseViolation(warrantCauseViolation);
		
		assert !this.warrantService.findWarrantCauseViolationsByWarrant(warrant)
		.contains(warrantCauseViolation) : "WarrantCauseViolation was not "
				+ "removed";
		
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
	
	private Warrant createWarrant() throws DuplicateEntityFoundException {
		final Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Wayne", "Bruce", "Alen", null);
		final Date date = this.parseDateText("05/12/2017");
		final Person issuedBy = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final String addressee = "Addressed To Someone, Somewhere";
		final WarrantReasonCategory warrantReason = WarrantReasonCategory
				.AUTHORIZATION_TO_PICKUP_AND_HOLD;
		final Boolean bondable = true;
		final BigDecimal bondRecommendation = new BigDecimal("500");
		
		return this.warrantService.create(offender, date, addressee,
				issuedBy, bondable, bondRecommendation, warrantReason);
	}
	
}
