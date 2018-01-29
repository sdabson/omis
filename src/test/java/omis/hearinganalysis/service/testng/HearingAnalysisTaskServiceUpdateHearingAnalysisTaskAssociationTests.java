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
import omis.hearinganalysis.domain.HearingAnalysis;
import omis.hearinganalysis.domain.HearingAnalysisCategory;
import omis.hearinganalysis.domain.HearingAnalysisTaskAssociation;
import omis.hearinganalysis.domain.ParoleHearingAnalysisTaskSource;
import omis.hearinganalysis.domain.ParoleHearingTaskCategory;
import omis.hearinganalysis.service.HearingAnalysisTaskService;
import omis.hearinganalysis.service.delegate.HearingAnalysisCategoryDelegate;
import omis.hearinganalysis.service.delegate.HearingAnalysisDelegate;
import omis.hearinganalysis.service.delegate.HearingAnalysisTaskAssociationDelegate;
import omis.hearinganalysis.service.delegate.ParoleHearingAnalysisTaskSourceDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.service.delegate.ParoleEligibilityDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;
import omis.task.service.delegate.TaskDelegate;
import omis.task.service.delegate.TaskTemplateDelegate;
import omis.task.service.delegate.TaskTemplateGroupDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update hearing analysis task associations.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HearingAnalysisTaskServiceUpdateHearingAnalysisTaskAssociationTests
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
	@Qualifier("hearingAnalysisDelegate")
	private HearingAnalysisDelegate hearingAnalysisDelegate;
	
	@Autowired
	@Qualifier("hearingAnalysisCategoryDelegate")
	private HearingAnalysisCategoryDelegate hearingAnalysisCategoryDelegate;
	
	@Autowired
	@Qualifier("paroleEligibilityDelegate")
	private ParoleEligibilityDelegate paroleEligibilityDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("paroleHearingAnalysisTaskSourceDelegate")
	private ParoleHearingAnalysisTaskSourceDelegate 
			paroleHearingAnalysisTaskSourceDelegate;
	
	@Autowired
	@Qualifier("taskTemplateDelegate")
	private TaskTemplateDelegate taskTemplateDelegate;
	
	@Autowired
	@Qualifier("taskTemplateGroupDelegate")
	private TaskTemplateGroupDelegate taskTemplateGroupDelegate;
	
	@Autowired
	@Qualifier("hearingAnalysisTaskAssociationDelegate")
	private HearingAnalysisTaskAssociationDelegate 
			hearingAnalysisTaskAssociationDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("hearingAnalysisTaskService")
	private HearingAnalysisTaskService hearingAnalysisTaskService;

	/* Test methods. */

	/**
	 * Tests the update of the task for a hearing analysis task association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateHearingAnalysisTaskAssociationTask() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccount = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Task task = this.taskDelegate.create("controllerName", "methodName", 
				"description", sourceAccount, this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		Offender offender = this.offenderDelegate.createWithoutIdentity("Jay", 
				"Ray", null, null);
		ParoleEligibility eligibility = this.paroleEligibilityDelegate.create(
				offender, this.parseDateText("01/01/2018"), null, null, null);
		HearingAnalysisCategory category = this.hearingAnalysisCategoryDelegate
				.create("Category", true);
		HearingAnalysis hearingAnalysis = this.hearingAnalysisDelegate.create(
				eligibility, null, category, null);
		TaskTemplateGroup group = this.taskTemplateGroupDelegate.create("Name");
		TaskTemplate taskTemplate = this.taskTemplateDelegate.create(group, 
				"Name", "controllerName", "methodName");
		ParoleHearingAnalysisTaskSource taskSource = this
				.paroleHearingAnalysisTaskSourceDelegate.create(taskTemplate, 
						ParoleHearingTaskCategory.ANALYSIS);
		HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation = this
				.hearingAnalysisTaskAssociationDelegate.create(task, 
				hearingAnalysis, taskSource);
		Task newTask = this.taskDelegate.create("controllerName", "methodName2", 
				"description", sourceAccount, this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		
		// Action
		hearingAnalysisTaskAssociation = this.hearingAnalysisTaskService
				.updateHearingAnalysisTaskAssociation(
						hearingAnalysisTaskAssociation, newTask, 
						hearingAnalysis);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("task", newTask)
			.addExpectedValue("hearingAnalysis", hearingAnalysis)
			.performAssertions(hearingAnalysisTaskAssociation);
	}
	
	/**
	 * Tests the update of the hearing analysis for a hearing analysis task 
	 * association.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateHearingAnalysisTaskAssociationHearingAnalysis() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccount = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Task task = this.taskDelegate.create("controllerName", "methodName", 
				"description", sourceAccount, this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		Offender offender = this.offenderDelegate.createWithoutIdentity("Jay", 
				"Ray", null, null);
		ParoleEligibility eligibility = this.paroleEligibilityDelegate.create(
				offender, this.parseDateText("01/01/2018"), null, null, null);
		HearingAnalysisCategory category = this.hearingAnalysisCategoryDelegate
				.create("Category", true);
		HearingAnalysis hearingAnalysis = this.hearingAnalysisDelegate.create(
				eligibility, null, category, null);
		TaskTemplateGroup group = this.taskTemplateGroupDelegate.create("Name");
		TaskTemplate taskTemplate = this.taskTemplateDelegate.create(group, 
				"Name", "controllerName", "methodName");
		ParoleHearingAnalysisTaskSource taskSource = this
				.paroleHearingAnalysisTaskSourceDelegate.create(taskTemplate, 
						ParoleHearingTaskCategory.ANALYSIS);
		HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation = this
				.hearingAnalysisTaskAssociationDelegate.create(task, 
				hearingAnalysis, taskSource);
		ParoleEligibility newEligibility = this.paroleEligibilityDelegate
				.create(offender, this.parseDateText("01/02/2018"), null, null, 
						null);
		HearingAnalysis newHearingAnalysis = this.hearingAnalysisDelegate
				.create(newEligibility, null, category, null);
		
		// Action
		hearingAnalysisTaskAssociation = this.hearingAnalysisTaskService
				.updateHearingAnalysisTaskAssociation(
						hearingAnalysisTaskAssociation, task,
						newHearingAnalysis);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("task", task)
			.addExpectedValue("hearingAnalysis", newHearingAnalysis)
			.performAssertions(hearingAnalysisTaskAssociation);
	}

	/**
	 * Test that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() throws DuplicateEntityFoundException {
		// Arrangements
		Person user = this.personDelegate.create("Smith", "John", "Jay", null);
		UserAccount sourceAccount = this.userAccountDelegate.create(user, 
				"Username", "password", null, 0, true);
		Task task = this.taskDelegate.create("controllerName", "methodName", 
				"description", sourceAccount, this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		Offender offender = this.offenderDelegate.createWithoutIdentity("Jay", 
				"Ray", null, null);
		ParoleEligibility eligibility = this.paroleEligibilityDelegate.create(
				offender, this.parseDateText("01/01/2018"), null, null, null);
		HearingAnalysisCategory category = this.hearingAnalysisCategoryDelegate
				.create("Category", true);
		HearingAnalysis hearingAnalysis = this.hearingAnalysisDelegate.create(
				eligibility, null, category, null);
		TaskTemplateGroup group = this.taskTemplateGroupDelegate.create("Name");
		TaskTemplate taskTemplate = this.taskTemplateDelegate.create(group, 
				"Name", "controllerName", "methodName");
		ParoleHearingAnalysisTaskSource taskSource = this
				.paroleHearingAnalysisTaskSourceDelegate.create(taskTemplate, 
						ParoleHearingTaskCategory.ANALYSIS);
		this.hearingAnalysisTaskAssociationDelegate.create(task, 
				hearingAnalysis, taskSource);
		Task secondTask = this.taskDelegate.create("controllerName", 
				"methodName2", "description", sourceAccount, 
				this.parseDateText("01/01/2018"), 
				this.parseDateText("01/02/2018"));
		HearingAnalysisTaskAssociation hearingAnalysisTaskAssociation = this
				.hearingAnalysisTaskAssociationDelegate.create(secondTask, 
				hearingAnalysis, taskSource);
		
		// Action
		hearingAnalysisTaskAssociation = this.hearingAnalysisTaskService
				.updateHearingAnalysisTaskAssociation(
						hearingAnalysisTaskAssociation, task, hearingAnalysis);
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