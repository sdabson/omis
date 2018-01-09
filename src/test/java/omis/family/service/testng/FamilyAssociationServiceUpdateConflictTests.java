package omis.family.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationCategoryClassification;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.family.service.FamilyAssociationService;
import omis.family.service.delegate.FamilyAssociationCategoryDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests "update" of family association
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceUpdateConflictTests
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
	
	/* Service to test. */
	@Autowired
	@Qualifier("familyAssociationService")
	private FamilyAssociationService familyAssociationService;
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date null, new start date null, new 
	 * end date null.
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingStartDateOnlyNewDateRangeOfForever() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException 
	{
		// Arrangements
		Date existingStartDate = this.parseDateText("01/01/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, null, null, 
			null);
	}	
	
	/**
	 * Tests overlapped family association on update case with existing 
	 * start date is not null, existing end date not null, new start date null, 
	 * new end date null.
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingDateRangeNewDateRangeOfForever() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, existingEndDate, 
			null, null);
	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is null, existing end date not null, new start date null, 
	 * new end date null.
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingEndDateOnlyNewDateRangeOfForever() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = null;
		Date existingEndDate = this.parseDateText("11/30/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, existingEndDate, 
			null, null);
	}	
	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date null, new start date not null, 
	 * new end date null. Existing start date is earlier than new start date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingStartDateNewStartDateOnlyAndExistingStartDateEarlier() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, null, 
			newStartDate, null);
	}	
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date null, new start date not null, 
	 * new end date null. Existing start date is later than new start date
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingStartDateNewStartDateOnlyAndExistingStartDateLater() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, null, 
			newStartDate, null);
	}
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is null, existing end date not null, new start date not null, 
	 * new end date null.
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingEndDateNewStartDateOnly() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingEndDate = this.parseDateText("11/20/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(null, existingEndDate, 
			newStartDate, null);
	}
	
	/**
	 * Tests overlapped family association on update with existing 
	 * start date is not null, existing end date null, new start date null, 
	 * new end date not null.
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingStartDateNewEndDateOnly() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/20/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, null, null,
			newEndDate);
	}
	
	/**
	 * Tests overlapped family association on update with existing 
	 * date range not null, new end date not null. Existing date range is earlier 
	 * than new date range 
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingDateRangeEarlierThanNewDateRange() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/20/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, existingEndDate,
			newStartDate, newEndDate);
	}
	
	/**
	 * Tests overlapped family association on update with existing 
	 * date range not null, new end date not null. Existing date range is later 
	 * than new date range 
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingDateRangeLaterThanNewDateRange() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/20/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, existingEndDate,
			newStartDate, newEndDate);
	}
	
	/**
	 * Tests overlapped family association on update with existing 
	 * date range not null, new end date not null. Existing date range includes 
	 * than new date range 
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithExistingDateRangeIncludesNewDateRange() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/01/2017");
		Date existingEndDate = this.parseDateText("11/30/2017");
		Date newStartDate = this.parseDateText("11/15/2017");
		Date newEndDate = this.parseDateText("11/20/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, existingEndDate,
			newStartDate, newEndDate);
	}
	
	/**
	 * Tests overlapped family association on update with existing 
	 * date range not null, new end date not null. New date range includes 
	 * than existing date range 
	 * @throws ReflexiveRelationshipException 
	 * @throws FamilyAssociationConflictException 
	 * @throws DuplicateEntityFoundException 
	 */
	@Test(expectedExceptions = {FamilyAssociationConflictException.class})
	public void testWithNewDateRangeIncludesExistingDateRange() 
		throws DuplicateEntityFoundException, FamilyAssociationConflictException, 
		ReflexiveRelationshipException {
		// Arrangements
		Date existingStartDate = this.parseDateText("11/15/2017");
		Date existingEndDate = this.parseDateText("11/20/2017");
		Date newStartDate = this.parseDateText("11/01/2017");
		Date newEndDate = this.parseDateText("11/30/2017");
		// Action
		this.testConflictingDatesOnUpdateImpl(existingStartDate, existingEndDate,
			newStartDate, newEndDate);
	}
	
	private FamilyAssociation testConflictingDatesOnUpdateImpl(
		Date existingStartDate, Date existingEndDate, Date newStartDate, 
		Date newEndDate) throws DuplicateEntityFoundException, 
		FamilyAssociationConflictException, ReflexiveRelationshipException 
	{
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange existingDateRange = new DateRange(existingStartDate, 
			existingEndDate);
		DateRange updatedDateRange = new DateRange(
			this.parseDateText("11/15/2015"), this.parseDateText("12/15/2015"));
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate.create("testName", 
			true, (short) 23, FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags();
		flags.setCohabitant(true);
		flags.setDependent(true);
		flags.setEmergencyContact(true);
		Date marriageDate = new Date(182345678);
		Date divorceDate = new Date(1382345678);
		this.familyAssociationService.associate(offender, familyMember, 
			existingDateRange, category, flags,	marriageDate, divorceDate);
		
		FamilyAssociation updatedFamilyAssociation 
		= this.familyAssociationService.associate(offender, familyMember, 
			updatedDateRange, category, flags,	marriageDate, divorceDate);
		
		DateRange newDateRange = new DateRange(newStartDate, newEndDate);
		return this.familyAssociationService.update(updatedFamilyAssociation, 
			newDateRange, category, flags, marriageDate, divorceDate);
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