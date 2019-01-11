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
package omis.presentenceinvestigation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationUsageCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.presentenceinvestigation.service.PresentenceInvestigationTaskService;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationCategoryDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationRequestDelegate;
import omis.presentenceinvestigation.service.delegate.PresentenceInvestigationTaskSourceDelegate;
import omis.task.domain.Task;
import omis.task.domain.TaskAssignment;
import omis.task.domain.TaskParameterValue;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;
import omis.task.service.delegate.TaskDelegate;
import omis.task.service.delegate.TaskTemplateDelegate;
import omis.task.service.delegate.TaskTemplateGroupDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * PresentenceInvestigationTaskServiceRemoveTests.java
 * 
 * @author Annie Wahl
 * @author Josh Divine 
 * @version 0.1.3 (Oct 24, 2018)
 * @since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskServiceRemoveTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service being tested */
	
	@Autowired
	@Qualifier("presentenceInvestigationTaskService")
	private PresentenceInvestigationTaskService
		presentenceInvestigationTaskService;
	
	/* Helper delegates */
	
	@Autowired
	private PresentenceInvestigationRequestDelegate
		presentenceInvestigationRequestDelegate;
	
	@Autowired
	private PresentenceInvestigationCategoryDelegate
		presentenceInvestigationCategoryDelegate;
	
	@Autowired
	private PersonDelegate personDelegate;
	
	@Autowired
	private UserAccountDelegate userAccountDelegate;
	
	@Autowired
	private TaskDelegate taskDelegate;
	
	@Autowired
	private PresentenceInvestigationTaskSourceDelegate
		presentenceInvestigationTaskSourceDelegate;
	
	@Autowired
	private TaskTemplateDelegate taskTemplateDelegate;
	
	@Autowired
	private TaskTemplateGroupDelegate taskTemplateGroupDelegate;
	
	@Test
	public void testPresentenceInvestigationTaskAssociationRemove()
			throws DuplicateEntityFoundException, DocketExistsException {
		
		final Person person = this.personDelegate.create(
				"Wayne", "Bruce", "Alen", null);
		final Person user = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final UserAccount userAccount = this.userAccountDelegate.create(
				user, "ROBIN34", "password1", this.parseDateText("12/12/2299"),
				0, true);
		final PresentenceInvestigationCategory category =
				this.presentenceInvestigationCategoryDelegate
				.create("PSI Category", true);
		final PresentenceInvestigationRequest presentenceInvestigationRequest =
				this.presentenceInvestigationRequestDelegate.create(
						userAccount, this.parseDateText("01/01/2016"),
						this.parseDateText("12/31/2017"),
						person, this.parseDateText("03/25/2015"), category, 
						this.parseDateText("04/01/2017"));
		final TaskTemplateGroup group = this.taskTemplateGroupDelegate.create(
				"TaskTemplateGroup");
		final TaskTemplate taskTemplate = this.taskTemplateDelegate.create(
				group, "Taskeroo", "taskyTaskController", "edit");
		final PresentenceInvestigationTaskSource taskSource =
				this.presentenceInvestigationTaskSourceDelegate.create(
					taskTemplate,
					PresentenceInvestigationTaskAssociationUsageCategory.SUMMARY,
					PresentenceInvestigationTaskAssociationCategory.LEGAL);
		final Task task = this.taskDelegate.create(
				taskTemplate.getControllerName(), taskTemplate.getMethodName(),
				taskTemplate.getName(), userAccount,
				this.parseDateText("01/01/2017"), null);
		
		final PresentenceInvestigationTaskAssociation taskAssociation =
				this.presentenceInvestigationTaskService
				.createPresentenceInvestigationTaskAssociation(
						task, presentenceInvestigationRequest, taskSource);
		
		this.presentenceInvestigationTaskService
		.removePresentenceInvestigationTaskAssociation(taskAssociation);
		
		assert !(this.presentenceInvestigationTaskService
			.findPresentenceInvestigationTasksByPresentenceInvestigationRequest(
					presentenceInvestigationRequest).contains(taskAssociation))
		: "Task Association was not removed.";
	}
	
	@Test
	public void testTaskRemove()
			throws DuplicateEntityFoundException {
		final Person user = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final UserAccount sourceAccount = this.userAccountDelegate.create(
				user, "ROBIN34", "password1", this.parseDateText("12/12/2299"),
				0, true);
		final TaskTemplateGroup group = this.taskTemplateGroupDelegate.create(
				"TaskTemplateGroup");
		final TaskTemplate taskTemplate = this.taskTemplateDelegate.create(
				group, "Taskeroo", "taskyTaskController", "edit");
		final Date originationDate = this.parseDateText("01/01/2017");
		final Date completionDate = this.parseDateText("11/03/2018");
		final Task task = this.presentenceInvestigationTaskService.createTask(
				taskTemplate.getControllerName(), taskTemplate.getMethodName(),
				taskTemplate.getName(), sourceAccount, originationDate, completionDate);
		
		this.presentenceInvestigationTaskService.removeTask(task);
		
		assert !(this.taskDelegate.findByTaskTemplate(taskTemplate).contains(task))
		: "Task was not removed.";
	}
	
	@Test
	public void testTaskAssignmentRemove()
			throws DuplicateEntityFoundException {
		final Person user = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final UserAccount userAccount = this.userAccountDelegate.create(
				user, "ROBIN34", "password1", this.parseDateText("12/12/2299"),
				0, true);
		final TaskTemplateGroup group = this.taskTemplateGroupDelegate.create(
				"TaskTemplateGroup");
		final TaskTemplate taskTemplate = this.taskTemplateDelegate.create(
				group, "Taskeroo", "taskyTaskController", "edit");
		final Task task = this.taskDelegate.create(
				taskTemplate.getControllerName(), taskTemplate.getMethodName(),
				taskTemplate.getName(), userAccount,
				this.parseDateText("01/01/2017"), null);
		final Date assignedDate = this.parseDateText("07/07/2017");
		final Person assigneeUser = this.personDelegate.create(
				"Pennyworth", "Alfred", "J", null);
		final UserAccount assigneeAccount = this.userAccountDelegate.create(
				assigneeUser, "TheButler", "$e(ureP@$$\\/\\/0r|)",
				this.parseDateText("12/12/2299"), 0, true);
		final TaskAssignment taskAssignment =
				this.presentenceInvestigationTaskService.createTaskAssignment(
						task, assignedDate, assigneeAccount);
		
		this.presentenceInvestigationTaskService.removeTaskAssignment(
				taskAssignment);
		
		assert !(this.presentenceInvestigationTaskService
				.findTaskAssignmentsByTask(task).contains(taskAssignment))
		: "Task Assignment was not removed.";
	}
	
	@Test
	public void testTaskParameterValueRemove()
			throws DuplicateEntityFoundException {
		final Person user = this.personDelegate.create(
				"Grayson", "Richard", "J", null);
		final UserAccount sourceAccount = this.userAccountDelegate.create(
				user, "ROBIN34", "password1", this.parseDateText("12/12/2299"),
				0, true);
		final TaskTemplateGroup group = this.taskTemplateGroupDelegate.create(
				"TaskTemplateGroup");
		final TaskTemplate taskTemplate = this.taskTemplateDelegate.create(
				group, "Taskeroo", "taskyTaskController", "edit");
		final Date originationDate = this.parseDateText("01/01/2017");
		final Date completionDate = this.parseDateText("11/03/2018");
		final Task task = this.taskDelegate.create(
				taskTemplate.getControllerName(), taskTemplate.getMethodName(),
				taskTemplate.getName(), sourceAccount, originationDate, completionDate);
		final String typeName =
				"omis.presentenceinvestigation.domain.PresentenceInvestigationRequest";
		final String instanceValue = "63";
		final Short order = 1;
		final TaskParameterValue taskParameterValue =
				this.presentenceInvestigationTaskService
				.createTaskParameterValue(
						task, typeName, instanceValue, order);
		
		this.presentenceInvestigationTaskService.removeTaskParameterValue(
				taskParameterValue);
		
		assert !(this.presentenceInvestigationTaskService
				.findTaskParameterValuesByTask(task).contains(taskParameterValue))
		: "Task Parameter Value was not removed.";
	}
	
	private Date parseDateText(final String dateText) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(dateText);
		} catch (ParseException e) {
			throw new RuntimeException("Error parsing date", e);
		}
	}
}
