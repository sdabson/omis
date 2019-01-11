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
package omis.presentenceinvestigation.web.controller;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationUsageCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummaryReportService;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.service.PresentenceInvestigationTaskService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationHomeForm;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationRequestNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationTaskItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationTaskItemOperation;
import omis.presentenceinvestigation.web.validator.PresentenceInvestigationHomeFormValidator;
import omis.task.domain.Task;
import omis.task.domain.TaskParameterValue;
import omis.task.domain.TaskTemplateParameterValue;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for presentence investigation home.
 * 
 * @author Annie Wahl 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.5 (Oct 24, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/")
@PreAuthorize("hasRole('USER')")
public class PresentenceInvestigationHomeController {
	
	/* View names. */
	
	private static final String HOME_VIEW_NAME 
					= "/presentenceInvestigation/home/home";
	
	private static final String HOME_ACTION_MENU_VIEW_NAME 
					= "/presentenceInvestigation/home/includes/homeActionMenu";
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/"
				+ "home.html?presentenceInvestigationRequest=%d";
	
	/* Model Keys */
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY 
					= "presentenceInvestigationRequest";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String FORM_MODEL_KEY =
			"presentenceInvestigationHomeForm";
	
	private static final String ASSIGNED_USER_MODEL_KEY = "assignedUser";
	
	private static final String USER_ACCOUNT_MODEL_KEY =
			"AuditComponentRetrieverSpringMvcImpl#auditUserAccount";
	
	private static final String
		PRESENTENCE_INVESTIGATION_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY =
			"presentenceInvestigationRequestNoteItemIndex";
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"presentenceInvestigationTaskEntity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSummaryReportService")
	private PresentenceInvestigationRequestSummaryReportService
						presentenceInvestigationRequestReportService;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestService")
	private PresentenceInvestigationRequestService 
						presentenceInvestigationRequestService;

	@Autowired
	private PresentenceInvestigationTaskService
						presentenceInvestigationTaskService;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	private PropertyEditorFactory 
					presentenceInvestigationRequestPropertyEditorFactry;
	
	@Autowired
	@Qualifier("presentenceInvestigationTaskAssociationPropertyEditorFactory")
	private PropertyEditorFactory
				presentenceInvestigationTaskAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("taskPropertyEditorFactory")
	private PropertyEditorFactory taskPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestNotePropertyEditorFactory")
	private PropertyEditorFactory 
					presentenceInvestigationRequestNotePropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSummaryModelDelegate")
	private PresentenceInvestigationRequestSummaryModelDelegate
					presentenceInvestigationRequestSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationHomeFormValidator")
	private PresentenceInvestigationHomeFormValidator
					presentenceInvestigationHomeFormValidator;
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_TYPE_NAME =
		"omis.presentenceinvestigation.domain.PresentenceInvestigationRequest";

	private static final String PERSON_TYPE_NAME =
		"omis.person.domain.Person";

	private static final String OFFENDER_TYPE_NAME =
		"omis.offender.domain.Offender";
	
	private static final String DATE_TYPE_NAME = "java.util.Date";
	
	private static final String RESIDENCE_TYPE_TYPE_NAME =
			"omis.residence.report.ResidenceType";
	
	private static final String COURT_CASE_TYPE_NAME = 
			"omis.courtcase.domain.CourtCase";
	
	private static final String STRING_TYPE_NAME = "java.lang.String";
	
	private static final String BOOLEAN_TYPE_NAME = "java.lang.Boolean";
	
	private static final String DOCKET_TYPE_NAME = "omis.docket.domain.Docket";
	
	/* Constructor */
	
	/**
	 * Default Constructor for 
	 * {@link PresentenceInvestigationRequestController}.
	 */
	public PresentenceInvestigationHomeController() {
	}
	
	/* View Methods*/
	
	/**
	 * Returns a view of the presentence investigation home. 
	 * @param presentenceInvestigationRequest - 
	 * {@link PresentenceInvestigationRequest}
	 * @return {@link ModelAndView} - view of the presentence investigation 
	 * home 
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestMapping(value = "/home.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_VIEW') or "
					+ "hasRole('ADMIN')")
	public ModelAndView showContent(
					@RequestParam(value = "presentenceInvestigationRequest", 
						required = true) final PresentenceInvestigationRequest 
					presentenceInvestigationRequest)
							throws DuplicateEntityFoundException {
		
		this.checkTasks(presentenceInvestigationRequest);
		
		ModelMap map = new ModelMap();
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
						presentenceInvestigationRequest);
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
						presentenceInvestigationRequest);
		
		if (this.presentenceInvestigationRequestService.isOffender(
				presentenceInvestigationRequest.getPerson())) {
			this.offenderSummaryModelDelegate.add(map, (Offender)
							presentenceInvestigationRequest.getPerson());
			map.addAttribute(OFFENDER_MODEL_KEY, (Offender)
							presentenceInvestigationRequest.getPerson());
		}
		
		List<PresentenceInvestigationTaskItem> basicInformationTaskItems =
				new ArrayList<PresentenceInvestigationTaskItem>();
		List<PresentenceInvestigationTaskItem> legalTaskItems =
				new ArrayList<PresentenceInvestigationTaskItem>();
		List<PresentenceInvestigationTaskItem> relationshipsTaskItems =
				new ArrayList<PresentenceInvestigationTaskItem>();
		List<PresentenceInvestigationTaskItem> complianceTaskItems =
				new ArrayList<PresentenceInvestigationTaskItem>();
		List<PresentenceInvestigationTaskItem> caseManagementTaskItems =
				new ArrayList<PresentenceInvestigationTaskItem>();
		List<PresentenceInvestigationTaskItem> summaryTaskItems =
				new ArrayList<PresentenceInvestigationTaskItem>();
		List<PresentenceInvestigationRequestNoteItem> noteItems =
				new ArrayList<PresentenceInvestigationRequestNoteItem>();
		for(PresentenceInvestigationTaskAssociation taskAssociation :
			this.presentenceInvestigationTaskService
			.findPresentenceInvestigationTasksByPresentenceInvestigationRequest(
					presentenceInvestigationRequest)) {
			PresentenceInvestigationTaskItem item =
					new PresentenceInvestigationTaskItem();
			
			item.setTaskAssociation(taskAssociation);
			if(taskAssociation.getTask().getCompletionDate() != null) {
				item.setItemOperation(
						PresentenceInvestigationTaskItemOperation.COMPLETE);
			}
			else {
				item.setItemOperation(
						PresentenceInvestigationTaskItemOperation.INCOMPLETE);
			}
			
			if(PresentenceInvestigationTaskAssociationUsageCategory.SECTION
					.equals(taskAssociation.getTaskSource().getUsage())){
				switch(taskAssociation.getTaskSource().getCategory()) {
					case BASIC_INFORMATION:
						basicInformationTaskItems.add(item);
						break;
					case CASE_MANAGEMENT:
						caseManagementTaskItems.add(item);
						break;
					case COMPLIANCE:
						complianceTaskItems.add(item);
						break;
					case LEGAL:
						legalTaskItems.add(item);
						break;
					case RELATIONSHIPS:
						relationshipsTaskItems.add(item);
						break;
					default:
						throw new UnsupportedOperationException("Presentence " +
								"Investigation Task Association Category Not " +
								"Supported.");
				}
			}
			else if(PresentenceInvestigationTaskAssociationUsageCategory.SUMMARY
					.equals(taskAssociation.getTaskSource().getUsage())){
				summaryTaskItems.add(item);
			}
		}
		for(PresentenceInvestigationRequestNote note :
			this.presentenceInvestigationRequestService
		.findPresentenceInvestigationRequestNotesByPresentenceInvestigationRequest(
				presentenceInvestigationRequest)) {
			PresentenceInvestigationRequestNoteItem noteItem =
					new PresentenceInvestigationRequestNoteItem();
			
			noteItem.setDate(note.getDate());
			noteItem.setDescription(note.getDescription());
			noteItem.setPresentenceInvestigationRequestNote(note);
			noteItem.setItemOperation(
					PresentenceInvestigationItemOperation.UPDATE);
			noteItems.add(noteItem);
		}
		
		PresentenceInvestigationHomeForm form =
				new PresentenceInvestigationHomeForm();
		form.setBasicInformationTaskItems(basicInformationTaskItems);
		form.setCaseManagementTaskItems(caseManagementTaskItems);
		form.setComplianceTaskItems(complianceTaskItems);
		form.setLegalTaskItems(legalTaskItems);
		form.setRelationshipsTaskItems(relationshipsTaskItems);
		form.setSummaryTaskItems(summaryTaskItems);
		form.setPresentenceInvestigationRequestNoteItems(noteItems);
		map.addAttribute(
				PRESENTENCE_INVESTIGATION_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getPresentenceInvestigationRequestNoteItems().size());
		map.addAttribute(FORM_MODEL_KEY, form);
		
		return new ModelAndView(HOME_VIEW_NAME, map);
	}
	
	/**
	 * Updates Presentence Investigation Tasks and returns to the 
	 * PresentenceInvestigationRequest home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - PresentenceInvestigationHomeForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView for PresentenceInvestigationRequest home screen
	 * @throws DuplicateEntityFoundException
	 */
	@RequestMapping(value = "/home.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_VIEW') or "
					+ "hasRole('ADMIN')")
	public ModelAndView showContent(
					@RequestParam(value = "presentenceInvestigationRequest", 
						required = true) final PresentenceInvestigationRequest 
					presentenceInvestigationRequest,
					final PresentenceInvestigationHomeForm form,
					final BindingResult bindingResult)
							throws DuplicateEntityFoundException {
		
		this.presentenceInvestigationHomeFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
							presentenceInvestigationRequest);
			if (this.presentenceInvestigationRequestService.isOffender(
					presentenceInvestigationRequest.getPerson())) {
				this.offenderSummaryModelDelegate.add(map, (Offender)
								presentenceInvestigationRequest.getPerson());
				map.addAttribute(OFFENDER_MODEL_KEY, (Offender)
								presentenceInvestigationRequest.getPerson());
			}
			map.addAttribute(
					PRESENTENCE_INVESTIGATION_REQUEST_NOTE_ITEM_INDEX_MODEL_KEY,
					form.getPresentenceInvestigationRequestNoteItems().size());
			map.addAttribute(FORM_MODEL_KEY, form);
			
			return new ModelAndView(HOME_VIEW_NAME, map);
		}
		else {
			for(PresentenceInvestigationRequestNoteItem noteItem :
				form.getPresentenceInvestigationRequestNoteItems()) {
				if(PresentenceInvestigationItemOperation.CREATE
						.equals(noteItem.getItemOperation())) {
					this.presentenceInvestigationRequestService
					.createPresentenceInvestigationRequestNote(
							presentenceInvestigationRequest,
							noteItem.getDescription(), noteItem.getDate());
				}
				else if(PresentenceInvestigationItemOperation.UPDATE
						.equals(noteItem.getItemOperation())) {
					if(this.isNoteChanged(
							noteItem.getPresentenceInvestigationRequestNote(),
							noteItem.getDate(), noteItem.getDescription())) {
						this.presentenceInvestigationRequestService
						.updatePresentenceInvestigationRequestNote(
								noteItem.getPresentenceInvestigationRequestNote(),
								noteItem.getDescription(), noteItem.getDate());
					}
				}
				else if(PresentenceInvestigationItemOperation.REMOVE
						.equals(noteItem.getItemOperation())) {
					this.presentenceInvestigationRequestService
					.removePresentenceInvestigationRequestNote(
							noteItem.getPresentenceInvestigationRequestNote());
				}
			}
			
			List<PresentenceInvestigationTaskItem> taskItems =
					new ArrayList<>();
			taskItems.addAll(form.getBasicInformationTaskItems());
			taskItems.addAll(form.getCaseManagementTaskItems());
			taskItems.addAll(form.getComplianceTaskItems());
			taskItems.addAll(form.getLegalTaskItems());
			taskItems.addAll(form.getRelationshipsTaskItems());
			taskItems.addAll(form.getSummaryTaskItems());
			
			for(PresentenceInvestigationTaskItem item : taskItems) {
				if(PresentenceInvestigationTaskItemOperation.SET_COMPLETE
						.equals(item.getItemOperation())) {
					Task task = item.getTaskAssociation().getTask(); 
					this.presentenceInvestigationTaskService.updateTask(task,
							task.getControllerName(), task.getMethodName(),
							task.getDescription(), task.getSourceAccount(),
							task.getOriginationDate(), new Date());
				}
			}
	
			return new ModelAndView(String.format(
					HOME_REDIRECT, presentenceInvestigationRequest.getId()));
		}
	}
	
	/**
	 * Returns a view of the home screen action menu.
	 * @param presentenceInvestigationRequest - 
	 * {@link PresentenceInvestigationRequest}
	 * @return {@link ModelAndView} - view of the home screen action menu
	 */
	@RequestMapping(value = "/homeActionMenu.html",
					method = RequestMethod.GET)
	public ModelAndView displayHomeActionMenu(
					@RequestParam(value = "presentenceInvestigationRequest", 
					required = true) final PresentenceInvestigationRequest 
					presentenceInvestigationRequest) {
		ModelMap map = new ModelMap();
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
						presentenceInvestigationRequest);
		map.addAttribute(OFFENDER_MODEL_KEY, (Offender)
				presentenceInvestigationRequest.getPerson());
		map.addAttribute(ASSIGNED_USER_MODEL_KEY, this.retrieveUserAccount());
		return new ModelAndView(HOME_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Helper Methods */
	
	/**
	 * Checks the PresentenceInvestigationRequest's TaskAssociations and creates
	 * them if they have not already been created
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @throws DuplicateEntityFoundException - When a Task, TaskParameterValue,
	 * or PresentenceInvestigationTaskAssocation already exists
	 */
	private void checkTasks(
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
					throws DuplicateEntityFoundException {
		if (this.presentenceInvestigationRequestService.isOffender(
				presentenceInvestigationRequest.getPerson())) {
			PresentenceInvestigationTaskGroup taskGroup =
					this.presentenceInvestigationTaskService
		.findPresentenceInvestigationTaskGroupByPresentenceInvestigationCategory(
					presentenceInvestigationRequest.getCategory());
							
			List<PresentenceInvestigationTaskSource> taskSources =
					this.presentenceInvestigationTaskService
					.findPresentenceInvestigationTaskSourceByTaskTemplateGroup(
							taskGroup.getGroup());
			
			for(PresentenceInvestigationTaskSource taskSource : taskSources) {
				Task task = this.presentenceInvestigationTaskService
						.findTaskByTaskTemplateAndPresentenceInvestigationRequest(
								taskSource.getTaskTemplate(),
								presentenceInvestigationRequest);
				
				if(task == null) {
					task = this.presentenceInvestigationTaskService.createTask(
							taskSource.getTaskTemplate().getControllerName(),
							taskSource.getTaskTemplate().getMethodName(),
							taskSource.getTaskTemplate().getName(),
							this.retrieveUserAccount(), new Date(), null);
					
					this.presentenceInvestigationTaskService
						.createPresentenceInvestigationTaskAssociation(
						task, presentenceInvestigationRequest, taskSource);
					
					this.presentenceInvestigationTaskService
						.createTaskAssignment(task, new Date(), 
								this.retrieveUserAccount());
				}
				List<TaskTemplateParameterValue> templateParameterValues =
						this.presentenceInvestigationTaskService
						.findTaskTemplateParameterValuesByTaskTemplate(
								taskSource.getTaskTemplate());
				
				for(TaskTemplateParameterValue templateParameterValue
						: templateParameterValues) {
					TaskParameterValue value =
						this.presentenceInvestigationTaskService
						.findTaskParameterValueByTaskTemplateParameterValueAndTask(
								templateParameterValue, task);
					String instanceValue = null;
					
					switch (templateParameterValue.getTypeName()) {
						case OFFENDER_TYPE_NAME:
						case PERSON_TYPE_NAME:
							instanceValue = presentenceInvestigationRequest
									.getPerson().getId().toString();
							break;
						case PRESENTENCE_INVESTIGATION_REQUEST_TYPE_NAME:
							instanceValue =
								presentenceInvestigationRequest.getId().toString();
							break;
						case DATE_TYPE_NAME:
							instanceValue = convertDateToString(new Date());
							break;
						case RESIDENCE_TYPE_TYPE_NAME:
							//TODO: This is a placeholder until the entity could be properly resolved
							instanceValue = "0";
							break;
						case DOCKET_TYPE_NAME:
						case COURT_CASE_TYPE_NAME:
						case STRING_TYPE_NAME:
						case BOOLEAN_TYPE_NAME:
							instanceValue = null;
							break;
						default:
							throw new UnsupportedOperationException("Task " +
									"Template Parameter Value Not Currently " +
									"Supported");
					}
					
					if(value == null) {
						value = this.presentenceInvestigationTaskService
								.createTaskParameterValue(task,
										templateParameterValue.getTypeName(),
										instanceValue,
										templateParameterValue.getOrder());
					}
				}
			}
		}
	}
	
	/**
	 * Checks if a Presentence Investigation Request Note has been changed and
	 * returns true if it has
	 * @param note - Presentence Investigation Request Note to check for change
	 * @param date - Note Date from Form
	 * @param value - Note Value from Form
	 * @return Boolean - true if either the form's note date or value is different
	 * from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(final PresentenceInvestigationRequestNote note,
			final Date date, final String value) {
		if(!note.getDescription().equals(value)) {
			return true;
		}
		if(!(note.getDate().getTime() == date.getTime())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Retrieves the current users account.
	 * @return current user
	 */
	private UserAccount retrieveUserAccount() {
		UserAccount userAccount = (UserAccount) RequestContextHolder
				.getRequestAttributes()
					.getAttribute(USER_ACCOUNT_MODEL_KEY,
						RequestAttributes.SCOPE_REQUEST);
		if (userAccount == null) {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			userAccount = 
					this.presentenceInvestigationRequestService
					.findUserAccountByUsername(username);
			RequestContextHolder.getRequestAttributes()
				.setAttribute(USER_ACCOUNT_MODEL_KEY, userAccount,
						RequestAttributes.SCOPE_REQUEST);
		}
		return userAccount;
	}
	
	// Converts date to string
	private String convertDateToString(final Date date) {
		PropertyEditor editor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		editor.setValue(date);
		return editor.getAsText();
	}
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView
		(ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Init binder.
	 * @param binder - web data binder.
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactry
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory 
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(PresentenceInvestigationRequestNote.class, 
				this.presentenceInvestigationRequestNotePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(PresentenceInvestigationTaskAssociation.class, 
				this.presentenceInvestigationTaskAssociationPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Task.class, 
				this.taskPropertyEditorFactory.
					createPropertyEditor());
	}
}