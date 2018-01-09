package omis.workassignment.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;
import omis.workassignment.domain.FenceRestriction;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentChangeReason;
import omis.workassignment.domain.WorkAssignmentGroup;
import omis.workassignment.service.WorkAssignmentService;
import omis.workassignment.service.delegate.FenceRestrictionDelegate;
import omis.workassignment.service.delegate.WorkAssignmentCategoryDelegate;
import omis.workassignment.service.delegate.WorkAssignmentChangeReasonDelegate;
import omis.workassignment.service.delegate.WorkAssignmentDelegate;
import omis.workassignment.service.delegate.WorkAssignmentGroupDelegate;

/**
 * Tests method to update work assignments.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"workassignment", "service"})
public class WorkAssignmentServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("fenceRestrictionDelegate")
	private FenceRestrictionDelegate fenceRestrictionDelegate;
	
	@Autowired
	@Qualifier("workAssignmentCategoryDelegate")
	private WorkAssignmentCategoryDelegate workAssignmentCategoryDelegate;

	@Autowired
	@Qualifier("workAssignmentGroupDelegate")
	private WorkAssignmentGroupDelegate workAssignmentGroupDelegate;
	
	@Autowired
	@Qualifier("workAssignmentChangeReasonDelegate")
	private WorkAssignmentChangeReasonDelegate 
			workAssignmentChangeReasonDelegate;
	
	@Autowired
	@Qualifier("workAssignmentDelegate")
	private WorkAssignmentDelegate workAssignmentDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("workAssignmentService")
	private WorkAssignmentService workAssignmentService;
	
	/* Test methods. */
	
	/**
	 * Tests update of a fence restriction for a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateFenceRestriction() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		FenceRestriction newFenceRestriction = this.fenceRestrictionDelegate
				.create("Restriction", true);
		
		// Action
		workAssignment = this.workAssignmentService.update(workAssignment, 
				newFenceRestriction, category, changeReason, assignedDate, 
				terminationDate, notes, false);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", newFenceRestriction)
			.addExpectedValue("category", category)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("assignedDate", assignedDate)
			.addExpectedValue("terminationDate", terminationDate)
			.addExpectedValue("comments", notes)
			.performAssertions(workAssignment);
	}
	
	/**
	 * Tests update of a work assignment category for a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateWorkAssignmentCategory() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		WorkAssignmentCategory newCategory = 
				this.workAssignmentCategoryDelegate.create("Group2", true,
						workAssignmentGroup);
		
		// Action
		workAssignment = this.workAssignmentService.update(workAssignment, 
				fenceRestriction, newCategory, changeReason, assignedDate, 
				terminationDate, notes, false);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", fenceRestriction)
			.addExpectedValue("category", newCategory)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("assignedDate", assignedDate)
			.addExpectedValue("terminationDate", terminationDate)
			.addExpectedValue("comments", notes)
			.performAssertions(workAssignment);
	}
	
	/**
	 * Tests update of a work assignment change reason for a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateWorkAssignmentChangeReason() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		WorkAssignmentChangeReason newChangeReason = 
				this.workAssignmentChangeReasonDelegate.create("Reason", true);
		
		// Action
		workAssignment = this.workAssignmentService.update(workAssignment, 
				fenceRestriction, category, newChangeReason, assignedDate, 
				terminationDate, notes, false);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", fenceRestriction)
			.addExpectedValue("category", category)
			.addExpectedValue("changeReason", newChangeReason)
			.addExpectedValue("assignedDate", assignedDate)
			.addExpectedValue("terminationDate", terminationDate)
			.addExpectedValue("comments", notes)
			.performAssertions(workAssignment);
	}
	
	/**
	 * Tests update of an assigned date for a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateAssignedDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		Date newAssignedDate = this.parseDateText("02/01/2017");
		
		// Action
		workAssignment = this.workAssignmentService.update(workAssignment, 
				fenceRestriction, category, changeReason, newAssignedDate, 
				terminationDate, notes, false);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", fenceRestriction)
			.addExpectedValue("category", category)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("assignedDate", newAssignedDate)
			.addExpectedValue("terminationDate", terminationDate)
			.addExpectedValue("comments", notes)
			.performAssertions(workAssignment);
	}
	
	/**
	 * Tests update of an termination date for a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTerminationDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		Date newTerminationDate = this.parseDateText("07/01/2017");
		
		// Action
		workAssignment = this.workAssignmentService.update(workAssignment, 
				fenceRestriction, category, changeReason, assignedDate, 
				newTerminationDate, notes, false);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", fenceRestriction)
			.addExpectedValue("category", category)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("assignedDate", assignedDate)
			.addExpectedValue("terminationDate", newTerminationDate)
			.addExpectedValue("comments", notes)
			.performAssertions(workAssignment);
	}
	
	/**
	 * Tests update of comments for a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateComments() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		String comments = "New Comments";
		
		// Action
		workAssignment = this.workAssignmentService.update(workAssignment, 
				fenceRestriction, category, changeReason, assignedDate, 
				terminationDate, comments, false);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", fenceRestriction)
			.addExpectedValue("category", category)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("assignedDate", assignedDate)
			.addExpectedValue("terminationDate", terminationDate)
			.addExpectedValue("comments", comments)
			.performAssertions(workAssignment);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		FenceRestriction fenceRestriction = this.fenceRestrictionDelegate
				.create("No Restriction", true);
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupDelegate.create("Group", true);
		WorkAssignmentCategory category = this.workAssignmentCategoryDelegate
				.create("Library", true, workAssignmentGroup);
		WorkAssignmentChangeReason changeReason = 
				this.workAssignmentChangeReasonDelegate.create("Medical", true);
		Date assignedDate = this.parseDateText("01/01/2017");
		Date terminationDate = this.parseDateText("06/01/2017");
		String notes = "Comments";
		this.workAssignmentDelegate.create(offender, fenceRestriction, category, 
				changeReason, assignedDate, terminationDate, notes, false);
		assignedDate = this.parseDateText("02/01/2017");
		WorkAssignment workAssignment = this.workAssignmentDelegate.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, false);
		assignedDate = this.parseDateText("01/01/2017");
		
		// Action
		this.workAssignmentService.update(workAssignment, fenceRestriction, 
				category, changeReason, assignedDate, terminationDate, notes, 
				false);
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
