package omis.visitation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.relationship.domain.Relationship;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.relationship.service.delegate.RelationshipDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.visitation.domain.VisitationApproval;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationCategory;
import omis.visitation.domain.VisitationAssociationFlags;
import omis.visitation.service.VisitationAssociationService;
import omis.visitation.service.delegate.VisitationAssociationCategoryDelegate;
import omis.visitation.service.delegate.VisitationAssociationDelegate;

/**
 * Tests method to dissociate a visitor.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"visitation", "service"})
public class VisitationAssociationServiceDissociateTests
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

	/* Services. */

	@Autowired
	@Qualifier("visitationAssociationService")
	private VisitationAssociationService visitationAssociationService;

	/* Test methods. */

	/**
	 * Tests the ending of a visitation association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws DateConflictException if overlapping dates exist
	 * @throws ReflexiveRelationshipException if reflexive relationship exists
	 */
	@Test
	public void testDissociate() throws DuplicateEntityFoundException, 
			ReflexiveRelationshipException, DateConflictException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		Person visitor = this.personDelegate.create("Smith", "John", "Jay", 
				"Jr.");
		VisitationAssociationCategory category = 
				this.visitationAssociationCategoryDelegate.create("Category", 
						(short) 1, true);
		VisitationApproval approval = new VisitationApproval(true, 
				this.parseDateText("01/01/2017"));
		Date startDate = this.parseDateText("01/01/2017");
		VisitationAssociationFlags flags = new VisitationAssociationFlags(false, 
				false, false, false);
		String notes = "Notes";
		String guardianship = new String("guardianship text");
		
		Relationship relationship = this.relationshipDelegate.create(offender, 
				visitor);
		VisitationAssociation visitationAssociation = 
				this.visitationAssociationDelegate.create(relationship, 
						category, approval, startDate, null, flags, notes, guardianship);
		Date endDate = this.parseDateText("05/05/2017");

		// Action
		visitationAssociation = this.visitationAssociationService.dissociate(
				visitationAssociation, endDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("relationship.firstPerson", offender)
			.addExpectedValue("relationship.secondPerson", visitor)
			.addExpectedValue("category", category)
			.addExpectedValue("approval", approval)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("flags", flags)
			.addExpectedValue("notes", notes)
			.performAssertions(visitationAssociation);
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