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
 * Tests method to create work assignments.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"workassignment", "service"})
public class WorkAssignmentServiceCreateTests
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
	 * Tests creation of a work assignment.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreate() throws DuplicateEntityFoundException {
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
		
		// Action
		WorkAssignment workAssignment = this.workAssignmentService.create(
				offender, fenceRestriction, category, changeReason, 
				assignedDate, terminationDate, notes, true);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("fenceRestriction", fenceRestriction)
			.addExpectedValue("category", category)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("assignedDate", assignedDate)
			.addExpectedValue("terminationDate", terminationDate)
			.addExpectedValue("comments", notes)
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
		// Action
		this.workAssignmentService.create(offender, fenceRestriction, category, 
				changeReason, assignedDate, terminationDate, notes, false);
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
