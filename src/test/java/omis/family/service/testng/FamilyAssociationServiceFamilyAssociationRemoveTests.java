package omis.family.service.testng;

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
import omis.family.service.delegate.FamilyAssociationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests family association removal
 * 
 *@author Yidong Li 
 *@version 0.1.0 (June 29, 2017)
 *@since OMIS 3.0
 *
 */
@Test(groups = {"family"})
public class FamilyAssociationServiceFamilyAssociationRemoveTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegate */
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
	
	/* Service */
	@Autowired
	@Qualifier("familyAssociationService")
	private FamilyAssociationService familyAssociationService;
	
	@Test
	public void testFamilyAssociationRemove() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException, 
		FamilyAssociationConflictException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Obama",
			"Kevin", "Johns", "Mr.");
		Person familyMember = personDelegate.create("Li", "Yidong", "CIC311", 
			"Mr.");
		DateRange dateRange = new DateRange();
		Date startDate = new Date(11111111);
		Date endDate = new Date(12345678);
		dateRange.setEndDate(endDate);
		dateRange.setStartDate(startDate);
		FamilyAssociationCategory category 
			= this.familyAssociationCategoryDelegate.create("testName", 
			(Boolean)true, new Short((short) 23), 
			FamilyAssociationCategoryClassification.CHILD);
		FamilyAssociationFlags flags = new FamilyAssociationFlags();
		flags.setCohabitant(true);
		flags.setDependent(true);
		flags.setEmergencyContact(true);
		Date marriageDate = new Date(1113333);
		Date divorceDate = new Date(2113333);
		FamilyAssociation familyAssociation= this.familyAssociationService
			.associate(offender, familyMember, dateRange, category, flags, 
			marriageDate, divorceDate);
		
		// Action
		this.familyAssociationService.remove(familyAssociation);
		
		// Assertion
		assert !(this.familyAssociationDelegate.findByOffender(offender)
			.contains(familyAssociation)): "Family association was not removed!";
	}
}
