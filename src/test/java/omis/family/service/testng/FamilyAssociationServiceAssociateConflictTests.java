package omis.family.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.service.FamilyAssociationService;
import omis.family.service.delegate.FamilyAssociationCategoryDelegate;
import omis.family.service.delegate.FamilyAssociationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Test family association service conflicts.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 27, 2017)
 * @since OMIS 3.0
 */
@Test(groups = {"family", "service"})
public class FamilyAssociationServiceAssociateConflictTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
		
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("familyAssociationCategoryDelegate")
	private FamilyAssociationCategoryDelegate familyAssociationCategoryDelegate;	
	
	@Autowired
	@Qualifier("familyAssociationDelegate")
	private FamilyAssociationDelegate familyAssociationDelegate;
	
	@Autowired
	@Qualifier("relationshipDelegate")
	private RelationshipDelegate relationshipDelegate;
	
	/* Service to test. */
	@Autowired
	@Qualifier("familyAssociationService")
	private FamilyAssociationService familyAssociationService;
	
	/**
	 * Tests the method to associate when only a new start date is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void familyAssociationServiceAssociateConflictTestsOnlyNewStartDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newStartDate = this.parseDateText("11/01/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				null, null, newStartDate, null);			
	}
	
	/**
	 * Tests the method to associate when only new start and end date 
	 * is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsOnlyNewStartDateAndEndDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				null, null, newStartDate, newEndDate);			
	}
	
	/**
	 * Tests the method to associate when a new end date being supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void familyAssociationServiceAssociateConflictTestsOnlyNewEndDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newEndDate = this.parseDateText("11/30/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				null, null, null, newEndDate);			
	}
	
	/**
	 * Tests the method to associate when only a existing start 
	 * date is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsOnlyExistingStartDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, null, null, null);			
	}
	
	/**
	 * Tests the method to associate when only new start and end date 
	 * is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsOnlyExistingStartDateAndEndDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, existingEndDate, null, null);			
	}
	
	/**
	 * Tests the method to associate when a new end date being supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsOnlyExistingEndDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingEndDate = this.parseDateText("11/30/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				null, existingEndDate, null, null);			
	}
	//---------------------------
	
	/**
	 * Tests the method to associate when an existing and new start 
	 * date is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsExistingAndNewStartDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newStartDate = this.parseDateText("11/15/2017");
		Date existingStartDate = this.parseDateText("11/01/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, null, newStartDate, null);			
	}
	
	/**
	 * Tests the method to associate when an existing comes after a new start 
	 * date is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsExistingStartDateAfterNewStartDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newStartDate = this.parseDateText("11/01/2017");
		Date existingStartDate = this.parseDateText("11/15/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, null, newStartDate, null);			
	}
	
	/**
	 * Tests the method to associate when an existing end date comes after 
	 * new start date is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsExistingEndDateAfterNewStartDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/20/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				null, existingEndDate, newStartDate, null);			
	}
	
	/**
	 * Tests the method to associate when an existing start date and 
	 * new end date being supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsOnlyExistingStartDateNewEndDate() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date newEndDate = this.parseDateText("11/20/2017");
		Date existingStartDate = this.parseDateText("11/15/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, null, null, newEndDate);			
	}
	
	/**
	 * Tests the method to associate when only a existing start 
	 * date is supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsNewDatesOverlapExistingDates() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/20/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);			
	}
	
	/**
	 * Tests the method to associate when existing dates are after new dates
	 * supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsExistingDatesAfterNewDates() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newEndDate = this.parseDateText("11/20/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);			
	}
	
	/**
	 * Tests the method to associate when new date range is with in existing 
	 * date range being supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsNewDatesWithinExistingDates() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newEndDate = this.parseDateText("11/20/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);			
	}
	
	/**
	 * Tests the method to associate when existing date range is with in new 
	 * date range being supplied.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */	
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void 
	familyAssociationServiceAssociateConflictTestsExistingDatesWithinNewDates() 
			throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/20/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		//Actions		
		this.testConflictingDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);			
	}
	
	/**
	 * Test conflicting dates on association of family.
	 *
	 *
	 * @param existingStartDate existing start date
	 * @param existingEndDate existing end date
	 * @param newStartDate new start date
	 * @param newEndDate new end date
	 * @return family association
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	private FamilyAssociation testConflictingDatesOnAssociateImpl(
			Date existingStartDate, Date existingEndDate, Date newStartDate, 
			Date newEndDate) throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException, 
				ReflexiveRelationshipException {		
		DateRange newDateRange = new DateRange(newStartDate, newEndDate);
		DateRange exsitingDateRange = new DateRange(
				existingStartDate, existingEndDate);
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"OLast", "OFirst", "OMiddle", null);
		Person person = this.personDelegate.create("PLast", "PFirst", "PMiddle",
				null);
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate
			.create("testCategoryName", true, (short) 23, 
					FamilyAssociationCategoryClassification.EXTENDED_FAMILY);
		FamilyAssociationFlags flags = new FamilyAssociationFlags();
		flags.setCohabitant(false);
		flags.setDependent(true);
		flags.setEmergencyContact(false);
		Date marriageDate = new Date(16072008);
		Date divorceDate = new Date(15052017);		
		Relationship relationship = this.relationshipDelegate
				.create(offender, person);		
		this.familyAssociationDelegate
				.create(exsitingDateRange, category, flags, marriageDate, 
						divorceDate, relationship);
		FamilyAssociation newFamilyAssociation = this.familyAssociationService
				.associate(offender, person, newDateRange, category, flags, 
						marriageDate, divorceDate);
		return newFamilyAssociation;
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