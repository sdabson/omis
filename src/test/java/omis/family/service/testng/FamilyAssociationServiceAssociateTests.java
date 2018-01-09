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
 * Test family association service allowed.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Nov 27, 2017)
 * @since OMIS 3.0
 */
@Test(groups = {"family", "service"})
public class FamilyAssociationServiceAssociateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;	
	
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
	 * Tests allowed dates when existing end date and new start date supplied
	 * are the same.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllowedExistingEndDateNewStartDateSame() 
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingEndDate = this.parseDateText("11/01/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		//Actions
		this.testAllowedDatesOnAssociateImpl(
				null, existingEndDate, newStartDate, null);		
	}
	
	/**
	 * Tests allowed dates when existing end date and new start date supplied
	 * are the different.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllowedExistingEndDateNewStartDateDifferent() 
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingEndDate = this.parseDateText("11/01/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		//Actions
		this.testAllowedDatesOnAssociateImpl(
				null, existingEndDate, newStartDate, null);		
	}
	
	/**
	 * Tests allowed dates when existing start date and new end date supplied
	 * are the same.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllowedExistingStartDateNewEndDateSame() 
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/01/2017");
		//Actions
		this.testAllowedDatesOnAssociateImpl(
				existingStartDate, null, null, newEndDate);		
	}

	/**
	 * Tests allowed dates when new end date and existing start date supplied
	 * are the different.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllowedExistingStartDateAfterNewEndDateDifferent() 
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date newEndDate = this.parseDateText("11/01/2017");
		Date existingStartDate = this.parseDateText("11/15/2017");
		//Actions
		this.testAllowedDatesOnAssociateImpl(
				existingStartDate, null, null, newEndDate);		
	}
	
	/**
	 * Test allowed date ranges when all dates supplied and existing ends 
	 * on new start date.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllDateRangesAllowedExistingEndOnNewStart()
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/15/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/30/2017");		
		//Actions		
		this.testAllowedDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);	
	}
	
	/**
	 * Test allowed date ranges when all dates supplied and existing ends 
	 *  before new start date.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllDateRangesAllowedExistingEndBeforeNewStart()
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/15/2017");
		Date newStartDate = this.parseDateText("11/20/2017");
		Date newEndDate = this.parseDateText("11/30/2017");		
		//Actions		
		this.testAllowedDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);	
	}
	
	/**
	 * Test allowed date ranges when all dates supplied and existing date range
	 * after new date range and existing start begins on new end date.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllDateRangesAllowedExistingAfterNewDateRanges()
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/15/2017");		
		//Actions		
		this.testAllowedDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);	
	}
	
	/**
	 * Test allowed date ranges when all dates supplied and existing date ranges 
	 *  after new date ranges and existing start is after new end date.
	 *
	 *
	 * @throws DuplicateEntityFoundException
	 * @throws FamilyAssociationConflictException
	 * @throws ReflexiveRelationshipException
	 */
	@Test()
	public void 
	familyAssociationServiceAssociateTestsAllDateRangesAllowedExistingAfterNewDifferentDates()
			throws DuplicateEntityFoundException, 
			FamilyAssociationConflictException, 
			ReflexiveRelationshipException {
		//Arrangements
		Date existingStartDate = this.parseDateText("11/20/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/15/2017");		
		//Actions		
		this.testAllowedDatesOnAssociateImpl(
				existingStartDate, existingEndDate, newStartDate, newEndDate);	
	}
	
	/**
	 * Test allowed dates when associating a family association.
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
	private FamilyAssociation testAllowedDatesOnAssociateImpl(
			Date existingStartDate, Date existingEndDate, Date newStartDate, 
			Date newEndDate) throws DuplicateEntityFoundException, 
				FamilyAssociationConflictException,
				ReflexiveRelationshipException {
		//Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"OLastName", "OFirstName", "OMiddleName", null);
		Person familyMember = this.personDelegate.create(
				"PLastName", "PFirstName", "PMiddleName", null);
		DateRange dateRange = new DateRange(newStartDate, newEndDate);
		DateRange exsitingDateRange = new DateRange(
				existingStartDate, existingEndDate);
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
				.create(offender, familyMember);		
		this.familyAssociationDelegate
				.create(exsitingDateRange, category, flags, marriageDate, 
						divorceDate, relationship);
		FamilyAssociation familyAssocication
			= this.familyAssociationService
					.associate(offender, familyMember, dateRange, category, 
							flags, marriageDate, divorceDate);
		
		return familyAssocication;
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