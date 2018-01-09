package omis.visitation.service.testng;

import java.beans.PropertyEditor;
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
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
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
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateManipulator;
import omis.util.PropertyValueAsserter;
import omis.visitation.domain.Visit;
import omis.visitation.domain.VisitMethod;
import omis.visitation.domain.VisitationApproval;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationCategory;
import omis.visitation.domain.VisitationAssociationFlags;
import omis.visitation.domain.component.VisitFlags;
import omis.visitation.service.VisitService;
import omis.visitation.service.delegate.VisitDelegate;
import omis.visitation.service.delegate.VisitationAssociationCategoryDelegate;
import omis.visitation.service.delegate.VisitationAssociationDelegate;

/**
 * Tests method to log new visits.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"visitation", "service"})
public class VisitServiceLogTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("visitationAssociationCategoryDelegate")
	private VisitationAssociationCategoryDelegate 
		visitationAssociationCategoryDelegate;
	
	@Autowired
	@Qualifier("visitationAssociationDelegate")
	private VisitationAssociationDelegate visitationAssociationDelegate;
	
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;

	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;

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
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("visitDelegate")
	private VisitDelegate visitDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/* Services. */

	@Autowired
	@Qualifier("visitService")
	private VisitService visitService;

	/* Test methods. */

	/**
	 * Tests the creation of a new visit.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testLog() throws DuplicateEntityFoundException, 
			DateConflictException, ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, new VisitationApproval(true, 
						this.parseDateText("01/01/2017")), 
						this.parseDateText("01/01/2017"), null, 
						new VisitationAssociationFlags(false, false, false, 
								false), null, null);
		Date date = this.parseDateText("01/05/2017");
		Date startTime = this.parseTimeText("12:00 PM");
		Date endTime = this.parseTimeText("2:00 PM");
		String badgeNumber = "1";
		VisitFlags flags = new VisitFlags(false, false, 
				VisitMethod.PHYSICALLY_PRESENT);
		String notes = "Notes";
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 East St.", 
				null, null, null, zipCode );
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Location location = this.locationDelegate.create(organization, null, 
				address);

		// Action
		Visit visit = this.visitService.log(association, date, startTime, 
				endTime, badgeNumber, flags, notes, location);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("visitationAssociation", association)
			.addExpectedValue("dateRange", 
					new DateRange(DateManipulator.getDateAtTimeOfDay(date, 
							startTime), DateManipulator.getDateAtTimeOfDay(date, 
									endTime)))
			.addExpectedValue("badgeNumber", badgeNumber)
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", notes)
			.addExpectedValue("location", location)
			.performAssertions(visit);
	}

	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException, DateConflictException, 
			ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, new VisitationApproval(true, 
						this.parseDateText("01/01/2017")), 
						this.parseDateText("01/01/2017"), null, 
						new VisitationAssociationFlags(false, false, false, 
								false), null, null);
		Date date = this.parseDateText("01/05/2017");
		Date startTime = this.parseTimeText("12:00 PM");
		Date endTime = this.parseTimeText("2:00 PM");
		String badgeNumber = "1";
		VisitFlags flags = new VisitFlags(false, false, 
				VisitMethod.PHYSICALLY_PRESENT);
		String notes = "Notes";
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 East St.", 
				null, null, null, zipCode );
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Location location = this.locationDelegate.create(organization, null, 
				address);
		this.visitDelegate.create(association, 
				DateManipulator.getDateAtTimeOfDay(date, startTime), 
				DateManipulator.getDateAtTimeOfDay(date, endTime), badgeNumber, 
				flags, notes, location);
		
		// Action
		this.visitService.log(association, date, startTime, endTime, 
				badgeNumber, flags, notes, location);
	}

	/**
	 * Tests {@code DateConflictException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test(expectedExceptions = {DateConflictException.class})
	public void testDateConflictException() 
			throws DuplicateEntityFoundException, DateConflictException,
			ReflexiveRelationshipException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation association = this.visitationAssociationDelegate
				.create(relationship, category, new VisitationApproval(true, 
						this.parseDateText("01/01/2017")), 
						this.parseDateText("01/01/2017"), null, 
						new VisitationAssociationFlags(false, false, false, 
								false), null, null);
		Date date = this.parseDateText("01/05/2017");
		Date startTime = this.parseTimeText("12:00 PM");
		Date endTime = this.parseTimeText("2:00 PM");
		String badgeNumber = "1";
		VisitFlags flags = new VisitFlags(false, false, 
				VisitMethod.PHYSICALLY_PRESENT);
		String notes = "Notes";
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "S", country, false, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city , "12345", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("123 East St.", 
				null, null, null, zipCode );
		Organization organization = this.organizationDelegate.create("Org", "O", 
				null);
		Location location = this.locationDelegate.create(organization, null, 
				address);
		Date startDate = this.parseDateText("01/01/2017");
		this.visitDelegate.create(association, 
				DateManipulator.getDateAtTimeOfDay(startDate, startTime), 
				DateManipulator.getDateAtTimeOfDay(date, endTime), badgeNumber, 
				flags, notes, location);
		
		// Action
		this.visitService.log(association, date, startTime, endTime, 
				badgeNumber, flags, notes, location);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
	
	private Date parseTimeText(final String timeText) {
		if (timeText != null && !timeText.isEmpty()) {
			PropertyEditor propertyEditor = this.datePropertyEditorFactory
				.createCustomTimeOnlyEditor(true);
			propertyEditor.setAsText(timeText);
			return (Date) propertyEditor.getValue();
		} else {
			return null;
		}
	}
}