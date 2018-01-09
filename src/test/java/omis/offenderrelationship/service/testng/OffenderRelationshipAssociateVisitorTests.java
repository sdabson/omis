package omis.offenderrelationship.service.testng;

import java.util.Date;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.offenderrelationship.service.CreateRelationshipsService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.visitation.domain.VisitationApproval;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationCategory;
import omis.visitation.domain.VisitationAssociationFlags;
import omis.visitation.service.delegate.VisitationAssociationCategoryDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * Test association of visitor.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"offenderrelationship"})
public class OffenderRelationshipAssociateVisitorTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("visitationAssociationCategoryDelegate")
	private VisitationAssociationCategoryDelegate 
		visitationAssociationCategoryDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	/* Services */
	@Autowired
	@Qualifier("createRelationshipsService")
	private CreateRelationshipsService createRelationshipsService;
	
	/**
	 * Tests associate visitor.
	 * @throws DuplicateEntityFoundException DuplicateEntityFoundException
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 * @throws DateConflictException DateConflictException
	 */
	@Test
	public void testAssociateVisitor() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		DateConflictException {
		// Arrangement
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wang",
			"Kevin", "Johns", "Mr.");
		Person visitor = this.personDelegate.create("lastName", "firstName", 
			"middleName", "Mr.");
		final int endDateInt = 1000000;
		Date startDate = new Date(1);
		Date endDate = new Date(endDateInt);
		VisitationAssociationFlags flags = new VisitationAssociationFlags(true, 
			true, true, true);
		String notes = new String("This a test for notes");
		final int decisionDateInt = 444444;
		VisitationApproval visitationApproval = new VisitationApproval(true, 
			new Date(decisionDateInt));
		final int shortInt = 23;
		VisitationAssociationCategory category 
			= this.visitationAssociationCategoryDelegate.create("Parent", 
			new Short((short) shortInt), true);
		String guardianship = new String("Guardian name");
		// Action
		VisitationAssociation association = this.createRelationshipsService
			.associateVisitor(offender, visitor, category, visitationApproval, 
			startDate, endDate, flags, notes, guardianship);		
		
		// Assertions
		assert offender.equals((Offender) association.getRelationship()
				.getFirstPerson())
			: String.format("Wrong Offender: #%s expected; #%s found",
				offender.getOffenderNumber(), ((Offender) association
				.getRelationship().getFirstPerson()).getOffenderNumber()); 
		assert visitor.equals(association.getRelationship().getSecondPerson())
			: String.format("Wrong Visitor: #%s expected; #%s found",
				visitor.getName(), 
				association.getRelationship().getSecondPerson().getName());
		assert startDate.equals(association.getDateRange().getStartDate())
		: String.format("Wrong Start Date: #%s expected; #%s found",
			startDate, association.getDateRange().getStartDate());
		assert endDate.equals(association.getDateRange().getEndDate())
		: String.format("Wrong End Date: #%s expected; #%s found",
			endDate, association.getDateRange().getEndDate());
		assert flags.getCourtOrder().equals(association.getFlags()
			.getCourtOrder())
		: String.format("Wrong Court Order Flag: #%s expected; #%s found",
			flags.getCourtOrder().toString(), 
			association.getFlags().getCourtOrder().toString());
		assert flags.getMoney().equals(association.getFlags().getMoney())
		: String.format("Wrong Money Flag: #%s expected; #%s found",
			flags.getMoney().toString(), 
			association.getFlags().getMoney().toString());
		assert flags.getNonContact().equals(association.getFlags()
			.getNonContact())
		: String.format("Wrong NonContact Flag: #%s expected; #%s found",
			flags.getNonContact().toString(), 
			association.getFlags().getNonContact().toString());
		assert flags.getSpecialVisit().equals(association.getFlags()
			.getSpecialVisit())
		: String.format("Wrong SpecialVisit Flag: #%s expected; #%s found",
			flags.getSpecialVisit().toString(), 
			association.getFlags().getSpecialVisit().toString());
		assert notes.equals(association.getNotes())
		: String.format("Wrong Notes: #%s expected; #%s found",
			notes, association.getNotes());
		assert visitationApproval.getApproved().equals(association.getApproval()
			.getApproved())
		: String.format("Wrong Approved of Visitation Approval: #%s expected; "
				+ "#%s found",
			visitationApproval.getApproved(), association.getApproval()
			.getApproved());
		assert visitationApproval.getDecisionDate().equals(association
			.getApproval().getDecisionDate())
			: String.format("Wrong Decision Date of Visitation Approval: #%s "
					+ "expected; "	+ "#%s found",
				visitationApproval.getDecisionDate(), association.getApproval()
				.getDecisionDate());
		assert category.equals(association.getCategory())
		: String.format("Wrong Category: #%s expected; #%s found",
			category.getName(), association.getCategory().getName());
	}
	
	/**
	 * Tests duplicate visitation associations.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate visitation 
	 * associations exist
	 * @throws ReflexiveRelationshipException ReflexiveRelationshipException
	 * @throws DateConflictException DateConflictException
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class, 
		ReflexiveRelationshipException.class, DateConflictException.class })
	public void testDuplicateAssociateVisitor() 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		DateConflictException {
		// Assignment
		Offender offender = this.offenderDelegate.createWithoutIdentity("Wang",
			"Kevin", "Johns", "Mr.");
		Person visitor = this.personDelegate.create("lastName", "firstName", 
			"middleName", "Mr.");
		Date startDate = new Date(1);
		final int endDateInt = 1000000;
		Date endDate = new Date(endDateInt);
		VisitationAssociationFlags flags = new VisitationAssociationFlags(true, 
			true, true, true);
		String notes = new String("This a test for notes");
		final int decisionDate = 444444;
		VisitationApproval visitationApproval = new VisitationApproval(true, 
			new Date(decisionDate));
		final int shortInt = 23;
		VisitationAssociationCategory category 
			= this.visitationAssociationCategoryDelegate.create("Parent", 
			new Short((short) shortInt), true);
		String guardianship = new String("This a test for guardianship");
		// Action
		this.createRelationshipsService.associateVisitor(offender, visitor, 
			category, visitationApproval, startDate, endDate, flags, notes,
			guardianship);	
		this.createRelationshipsService.associateVisitor(offender, visitor, 
			category, visitationApproval, startDate, endDate, flags, notes,
			guardianship);
	}
}