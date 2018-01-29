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

import java.beans.PropertyEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.hearinganalysis.service.HearingAnalysisTaskService;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.service.delegate.TaskDelegate;
import omis.task.service.delegate.TaskParameterValueDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create task parameter values.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskServiceCreateTaskParameterValueTests
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
	
	@Autowired
	@Qualifier("taskParameterValueDelegate")
	private TaskParameterValueDelegate taskParameterValueDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Services. */

	@Autowired
	@Qualifier("hearingAnalysisTaskService")
	private HearingAnalysisTaskService hearingAnalysisTaskService;

	/* Test methods. */

	/**
	 * Tests the creation of task parameter values.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreateTaskParameterValue() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccount = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Task task = this.taskDelegate.create("controllerName", "methodName", 
				"description", sourceAccount, this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		Short order = (short) 1;
		String typeName = Date.class.getTypeName();
		String instanceValue = convertDateToString(
				this.parseDateText("01/01/2018"));

		// Action
		TaskParameterValue taskParameterValue = this.hearingAnalysisTaskService
				.createTaskParameterValue(task, order, typeName, instanceValue);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("task", task)
			.addExpectedValue("order", order)
			.addExpectedValue("typeName", typeName)
			.addExpectedValue("instanceValue", instanceValue)
			.performAssertions(taskParameterValue);
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
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccount = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Task task = this.taskDelegate.create("controllerName", "methodName", 
				"description", sourceAccount, this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		Short order = (short) 1;
		String typeName = Date.class.getTypeName();
		String instanceValue = convertDateToString(
				this.parseDateText("01/01/2018"));
		this.taskParameterValueDelegate.create(task, typeName, instanceValue, 
				order);
		
		// Action
		this.hearingAnalysisTaskService.createTaskParameterValue(task, order, 
				typeName, instanceValue);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}		
	
	// Converts date to string
	private String convertDateToString(final Date date) {
		PropertyEditor editor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		editor.setValue(date);
		return editor.getAsText();
	}
}