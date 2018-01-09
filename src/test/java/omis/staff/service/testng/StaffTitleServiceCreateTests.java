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
 * Tests method to create staff titles.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class StaffTitleServiceCreateTests
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
	 * Tests the creation of a staff title.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreate() throws DuplicateEntityFoundException {
		// Arrangements
		String name = "Name";
		Short sortOrder = (short) 1;
		Boolean valid = true;

		// Action
		StaffTitle staffTitle = this.staffTitleService.create(name, sortOrder, 
				valid);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("name", name)
			.addExpectedValue("sortOrder", sortOrder)
			.addExpectedValue("valid", valid)
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

		// Action
		this.staffTitleService.create(name, sortOrder, valid);

	}

}