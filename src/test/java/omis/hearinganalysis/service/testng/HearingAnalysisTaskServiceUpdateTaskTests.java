/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package omis.hearinganalysis.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.service.HearingAnalysisTaskService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.task.domain.Task;
import omis.task.service.delegate.TaskDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update tasks.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskServiceUpdateTaskTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;

	@Autowired
	@Qualifier("taskDelegate")
	private TaskDelegate taskDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("hearingAnalysisTaskService")
	private HearingAnalysisTaskService hearingAnalysisTaskService;

	/* Test methods. */

	/**
	 * Tests the update of the controller name for a task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTaskControllerName() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		String newControllerName = "Controller2";
		
		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, 
				newControllerName, methodName, description, sourceAccountUser, 
				originationDate, completionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", newControllerName)
			.addExpectedValue("methodName", methodName)
			.addExpectedValue("description", description)
			.addExpectedValue("sourceAccount", sourceAccountUser)
			.addExpectedValue("originationDate", originationDate)
			.addExpectedValue("completionDate", completionDate)
			.performAssertions(task);
	}
	
	/**
	 * Tests the update of the method name for a task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTaskMethodName() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		String newMethodName = "method2";
		
		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, controllerName, 
				newMethodName, description, sourceAccountUser, originationDate, 
				completionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", controllerName)
			.addExpectedValue("methodName", newMethodName)
			.addExpectedValue("description", description)
			.addExpectedValue("sourceAccount", sourceAccountUser)
			.addExpectedValue("originationDate", originationDate)
			.addExpectedValue("completionDate", completionDate)
			.performAssertions(task);
	}
	
	/**
	 * Tests the update of the description for a task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTaskDescription() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		String newDescription = "New description";
		
		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, controllerName, 
				methodName, newDescription, sourceAccountUser, originationDate, 
				completionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", controllerName)
			.addExpectedValue("methodName", methodName)
			.addExpectedValue("description", newDescription)
			.addExpectedValue("sourceAccount", sourceAccountUser)
			.addExpectedValue("originationDate", originationDate)
			.addExpectedValue("completionDate", completionDate)
			.performAssertions(task);
	}
	
	/**
	 * Tests the update of the source account user for a task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTaskSourceAccountUser() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		Person newUser = this.personDelegate.create("Smith", "Jeff", null, 
				null);
		UserAccount newSourceAccountUser = this.userAccountDelegate.create(
				newUser, "Username2", "password", null, 0, true);
		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, controllerName, 
				methodName, description, newSourceAccountUser, originationDate, 
				completionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", controllerName)
			.addExpectedValue("methodName", methodName)
			.addExpectedValue("description", description)
			.addExpectedValue("sourceAccount", newSourceAccountUser)
			.addExpectedValue("originationDate", originationDate)
			.addExpectedValue("completionDate", completionDate)
			.performAssertions(task);
	}
	
	/**
	 * Tests the update of the origination date for a task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTaskOriginationDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		Date newOriginationDate = this.parseDateText("01/02/2018");
		
		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, controllerName, 
				methodName, description, sourceAccountUser, newOriginationDate, 
				completionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", controllerName)
			.addExpectedValue("methodName", methodName)
			.addExpectedValue("description", description)
			.addExpectedValue("sourceAccount", sourceAccountUser)
			.addExpectedValue("originationDate", newOriginationDate)
			.addExpectedValue("completionDate", completionDate)
			.performAssertions(task);
	}
	
	/**
	 * Tests the update of the completion date for a task.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateTaskCompletionDate() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		Date newCompletionDate = this.parseDateText("01/03/2018");
		
		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, controllerName, 
				methodName, description, sourceAccountUser, originationDate, 
				newCompletionDate);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("controllerName", controllerName)
			.addExpectedValue("methodName", methodName)
			.addExpectedValue("description", description)
			.addExpectedValue("sourceAccount", sourceAccountUser)
			.addExpectedValue("originationDate", originationDate)
			.addExpectedValue("completionDate", newCompletionDate)
			.performAssertions(task);
	}

	/**
	 * Test that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		String controllerName = "Controller";
		String methodName = "method";
		String description = "Description";
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccountUser = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Date originationDate = this.parseDateText("01/01/2018");
		Date completionDate = this.parseDateText("01/02/2018");
		this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, originationDate, 
				completionDate);
		Date secondOrigDate = this.parseDateText("01/03/2018");
		Task task = this.taskDelegate.create(controllerName, methodName, 
				description, sourceAccountUser, secondOrigDate, null);

		// Action
		task = this.hearingAnalysisTaskService.updateTask(task, controllerName, 
				methodName, description, sourceAccountUser, originationDate, 
				completionDate);
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