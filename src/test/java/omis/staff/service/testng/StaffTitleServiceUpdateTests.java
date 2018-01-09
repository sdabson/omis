package omis.staff.service.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.staff.domain.StaffTitle;
import omis.staff.service.StaffTitleService;
import omis.staff.service.delegate.StaffTitleDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update staff titles.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class StaffTitleServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("staffTitleDelegate")
	private StaffTitleDelegate staffTitleDelegate;

	/* Services. */

	@Autowired
	@Qualifier("staffTitleService")
	private StaffTitleService staffTitleService;

	/* Test methods. */

	/**
	 * Tests the update of the name for a staff title.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateName() throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Short sortOrder = (short) 1;
		Boolean valid = true;
		StaffTitle staffTitle = this.staffTitleDelegate.create(name, sortOrder, 
				valid);
		String newName = "New name";

		// Action
		staffTitle = this.staffTitleService.update(staffTitle, newName, 
				sortOrder, valid);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name", newName)
			.addExpectedValue("sortOrder", sortOrder)
			.addExpectedValue("valid", valid)
			.performAssertions(staffTitle);
	}

	/**
	 * Tests the update of the sort order for a staff title.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateSortOrder() throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Short sortOrder = (short) 1;
		Boolean valid = true;
		StaffTitle staffTitle = this.staffTitleDelegate.create(name, sortOrder, 
				valid);
		Short newSortOrder = (short) 2;
		
		// Action
		staffTitle = this.staffTitleService.update(staffTitle, name, 
				newSortOrder, valid);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name", name)
			.addExpectedValue("sortOrder", newSortOrder)
			.addExpectedValue("valid", valid)
			.performAssertions(staffTitle);
	}

	/**
	 * Tests the update of the valid state for a staff title.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateValid() throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Short sortOrder = (short) 1;
		Boolean valid = true;
		StaffTitle staffTitle = this.staffTitleDelegate.create(name, sortOrder, 
				valid);
		Boolean newValid = false;
		
		// Action
		staffTitle = this.staffTitleService.update(staffTitle, name, sortOrder, 
				newValid);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name", name)
			.addExpectedValue("sortOrder", sortOrder)
			.addExpectedValue("valid", newValid)
			.performAssertions(staffTitle);
	}

	/**
	 * Tests that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Short sortOrder = (short) 1;
		Boolean valid = true;
		this.staffTitleDelegate.create(name, sortOrder, valid);
		String secondName = "Second name";
		StaffTitle staffTitle = this.staffTitleDelegate.create(secondName, 
				sortOrder, valid);
		
		// Action
		staffTitle = this.staffTitleService.update(staffTitle, name, sortOrder, 
				valid);
	}

}