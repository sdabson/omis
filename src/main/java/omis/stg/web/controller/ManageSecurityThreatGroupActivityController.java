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

package omis.stg.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.domain.SecurityThreatGroupActivityNote;
import omis.stg.exception.InvolvedOffenderRequiredException;
import omis.stg.exception.SecurityThreatGroupActivityExistsException;
import omis.stg.exception.SecurityThreatGroupActivityInvolvementExistsException;
import omis.stg.exception.SecurityThreatGroupActivityNoteExistsException;
import omis.stg.service.SecurityThreatGroupActivityService;
import omis.stg.web.form.SecurityThreatGroupActivityForm;
import omis.stg.web.form.SecurityThreatGroupActivityInvolvementItem;
import omis.stg.web.form.SecurityThreatGroupActivityInvolvementItemOperation;
import omis.stg.web.form.SecurityThreatGroupActivityNoteItem;
import omis.stg.web.form.SecurityThreatGroupActivityNoteItemOperation;
import omis.stg.web.validator.SecurityThreatGroupActivityFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for managing security threat group activities.
 * 
 * @author Trevor Isles
 * @author Sheronda Vaughn
 * @version 0.1.0 (Dec 7, 2016)
 * @since OMIS 3.0
 */
	@Controller
	@RequestMapping("/stg/activity")
	@PreAuthorize("hasRole('USER')")
	public class ManageSecurityThreatGroupActivityController {
	
	/* Model keys. */
		
	private static final String ACTIVITY_FORM_MODEL_KEY = "activityForm";
	
	private static final String ACTIVITY_MODEL_KEY = "activity";
	
	private static final String ACTIVITY_NOTE_ITEM_INDEX_MODEL_KEY
	= "activityNoteItemIndex";
	
	private static final String ACTIVITY_NOTE_ITEM_MODEL_KEY 
	= "activityNoteItem";
	
	private static final String ACTIVITY_INVOLVEMENT_ITEM_INDEX_MODEL_KEY
	= "activityInvolvementItemIndex";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String ACTIVITY_INVOLVEMENT_ITEM_MODEL_KEY
	= "activityInvolvementItem";
	
	private static final String INVOLVED_OFFENDERS_MODEL_KEY 
	= "involvedOffenders";
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "stg/activity/edit";
	
	private static final String ACTIVITY_NOTE_ITEM_ROW_VIEW_NAME 
	= "stg/activity/includes/activityNoteItemTableRow";
	
	private static final String ACTIVITY_INVOLVEMENT_ITEM_ROW_VIEW_NAME
	= "stg/activity/includes/activityInvolvementItemTableRow";
	
	private static final String INVOLVED_OFFENDERS_LIST_VIEW_NAME 
	= "stg/activity/includes/involvedOffenders";
	
	/* Action menu view names. */
	
	private static final String ACTIVITY_ACTION_MENU_VIEW_NAME
		= "stg/includes/stgActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
	= "redirect:/stg/list.html?offender=%d";
	
	/* Services. */
	
	@Autowired
	@Qualifier("securityThreatGroupActivityService")
	private SecurityThreatGroupActivityService 
		securityThreatGroupActivityService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityPropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupActivityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityInvolvementPropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupActivityInvolvementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityNotePropertyEditorFactory")
	private PropertyEditorFactory
	securityThreatGroupActivityNotePropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("securityThreatGroupActivityFormValidator")
	private SecurityThreatGroupActivityFormValidator
	securityThreatGroupActivityFormValidator;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Message Keys. */
	
	private static final String SECURITY_THREAT_GROUP_ACTIVITY_EXISTS_EXCEPTION
		= "stgActivity.exists";
	
	private static final String 
		SECURITY_THREAT_GROUP_ACTIVITY_NOTE_EXISTS_EXCEPTION 
		= "stgActivityNote.exists";
	
	private static final String 
		SECURITY_THREAT_GROUP_ACTIVITY_INVOLVEMENT_EXISTS_EXCEPTION 
		= "stgActivityInvolvement.exists";
	
	private static final String 
		INVOLVED_OFFENDER_REQUIRED_EXCEPTION 
		= "stgInvolvedOffenderRequired.exists";
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.stg.msgs.form";
	
	/* Constructor. */
	
	/** Instantiates a default controller for security threat group activities.
	*/
	public ManageSecurityThreatGroupActivityController() {
		// Default instantiation
	}
	
	/* Screens. */
	
	/**
	 * Displays form to create a new security threat group activity
	 * 
	 * @param reportedBy
	 * @return screen to display form to create a new security threat group
	 * activity
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('STG_ACTIVITY_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		SecurityThreatGroupActivityForm form 
			= new SecurityThreatGroupActivityForm();
				SecurityThreatGroupActivityInvolvementItem item 
					= new SecurityThreatGroupActivityInvolvementItem();
				item.setOperation(
						SecurityThreatGroupActivityInvolvementItemOperation
						.CREATE);
				item.setOffender(offender);
		List<SecurityThreatGroupActivityInvolvementItem> items 
			= new ArrayList<SecurityThreatGroupActivityInvolvementItem>();
		items.add(item);
		form.setInvolvementItems(items);
		return prepareEditMav(offender, form);
	}
	
	/**
	 * Displays form to update existing security group activity. 
	 * 
	 * @param activity security group activity to update
	 * @return screen for display form to update existing security group
	 * activity
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('STG_ACTIVITY_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "offender", required = true)
	final Offender offender,
			@RequestParam(value = "activity", required = true)
			final SecurityThreatGroupActivity activity) {
		SecurityThreatGroupActivityForm activityForm = 
				new SecurityThreatGroupActivityForm();
		activityForm.setReportDate(activity.getReportDate());
		activityForm.setReportedBy(activity.getReportedBy());
		activityForm.setSummary(activity.getSummary());
		ModelAndView mav = this.prepareEditMav(offender, activityForm);
		List<SecurityThreatGroupActivityInvolvement> involvements = 
				securityThreatGroupActivityService.findInvolvements(activity);
		List<SecurityThreatGroupActivityInvolvementItem> involvementItems 
			= new ArrayList<SecurityThreatGroupActivityInvolvementItem>();
		for (SecurityThreatGroupActivityInvolvement involvement : involvements) {
			SecurityThreatGroupActivityInvolvementItem item 
				= new SecurityThreatGroupActivityInvolvementItem();
			item.setOffender(involvement.getOffender());
			item.setActivityInvolvement(involvement);
			item.setNarrative(involvement.getNarrative());
			item.setOperation(
					SecurityThreatGroupActivityInvolvementItemOperation.UPDATE);
			involvementItems.add(item);
		}
		activityForm.setInvolvementItems(involvementItems);
		mav.addObject(ACTIVITY_INVOLVEMENT_ITEM_INDEX_MODEL_KEY, involvementItems.size());
		
		List<SecurityThreatGroupActivityNote> notes = 
				securityThreatGroupActivityService.findNotes(activity);
		List<SecurityThreatGroupActivityNoteItem> noteItems 
			= new ArrayList<SecurityThreatGroupActivityNoteItem>();
		for (SecurityThreatGroupActivityNote note : notes) {
			SecurityThreatGroupActivityNoteItem noteItem 
				= new SecurityThreatGroupActivityNoteItem();
			noteItem.setActivityNote(note);
			noteItem.setDate(note.getDate());
			noteItem.setValue(note.getValue());
			noteItem.setOperation(
					SecurityThreatGroupActivityNoteItemOperation.UPDATE);
			noteItems.add(noteItem);
		}
		activityForm.setNoteItems(noteItems);
		mav.addObject(ACTIVITY_NOTE_ITEM_INDEX_MODEL_KEY, noteItems.size());
		mav.addObject(ACTIVITY_MODEL_KEY, activity);
		return mav;
	}
	
	/**
	 * Displays a security threat group activity note item row.
	 * 
	 * @return model and view for security threat group activity note item row
	 */
	@RequestMapping(value = "createActivityNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayActivityNoteItemRow(@RequestParam(
			value = "activityNoteItemIndex", required = true)
			final Integer activityNoteItemIndex) {
		ModelMap map = new ModelMap();
		SecurityThreatGroupActivityNoteItem activityNoteItem
			= new SecurityThreatGroupActivityNoteItem();
		activityNoteItem.setOperation(
				SecurityThreatGroupActivityNoteItemOperation.CREATE);
		map.addAttribute(ACTIVITY_NOTE_ITEM_MODEL_KEY, activityNoteItem);
		map.addAttribute(ACTIVITY_NOTE_ITEM_INDEX_MODEL_KEY,
				activityNoteItemIndex);
		return new ModelAndView(ACTIVITY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a security threat group activity note item row.
	 * 
	 * @return model and view for security threat group activity note item row
	 */
	@RequestMapping(value = "createActivityInvolvementItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayActivityInvolvementItemRow(@RequestParam(
			value = "activityInvolvementItemIndex", required = true)
			final Integer activityInvolvementItemIndex) {
		ModelMap map = new ModelMap();
		SecurityThreatGroupActivityInvolvementItem activityInvolvementItem
			= new SecurityThreatGroupActivityInvolvementItem();
		activityInvolvementItem.setOperation(
				SecurityThreatGroupActivityInvolvementItemOperation.CREATE);
		map.addAttribute(ACTIVITY_INVOLVEMENT_ITEM_MODEL_KEY, activityInvolvementItem);
		map.addAttribute(ACTIVITY_INVOLVEMENT_ITEM_INDEX_MODEL_KEY,
				activityInvolvementItemIndex);
		return new ModelAndView(ACTIVITY_INVOLVEMENT_ITEM_ROW_VIEW_NAME, map);
	}
	
	// Returns a model and view suitable for editing the specified security 
	// threat group activity.
	private ModelAndView prepareEditMav(final Offender offender,
			final SecurityThreatGroupActivityForm activityForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(ACTIVITY_FORM_MODEL_KEY, activityForm);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		final Integer activityNoteIndex;
		if (activityForm.getNoteItems() != null) {
			activityNoteIndex = activityForm
					.getNoteItems().size(); 
		} else {
			activityNoteIndex = 0;
		}
		mav.addObject(ACTIVITY_NOTE_ITEM_INDEX_MODEL_KEY, 
				activityNoteIndex);
		final Integer activityInvolvementIndex;
		if (activityForm.getInvolvementItems() != null) {
			activityInvolvementIndex = activityForm
					.getInvolvementItems().size(); 
		} else {
			activityInvolvementIndex = 0;
		}
		mav.addObject(ACTIVITY_INVOLVEMENT_ITEM_INDEX_MODEL_KEY, 
				activityInvolvementIndex);
		return mav;
	}
	
	// Prepares redisplay edit model and view
		private ModelAndView prepareRedisplayEditMav(
				final Offender offender,
				final SecurityThreatGroupActivityForm
					securityThreatGroupActivityForm,
				final BindingResult result) {
			ModelAndView mav = prepareEditMav(offender,
					securityThreatGroupActivityForm);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ ACTIVITY_FORM_MODEL_KEY,
					result);
			return mav;
	}
	
	// Prepares security threat group activity screen redirect
	private ModelAndView prepareListRedirect(final Offender offender) {
		return new ModelAndView(String.format(LIST_REDIRECT, 
				offender.getId()));
	}
	
	/**
	 * Removes existing security group activity. 
	 * 
	 * @param activity security group activity to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "activityRemove",
			descriptionKey = "activityRemoveDescription",
			messageBundle = "omis.stg.msgs.activity",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("hasRole('STG_ACTIVITY_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "activity", required = true)
				final SecurityThreatGroupActivity activity,
			@RequestParam(value = "offender", required = true)
				final Offender offender) 
						throws InvolvedOffenderRequiredException {
		List<SecurityThreatGroupActivityInvolvement> involvements = 
				securityThreatGroupActivityService.findInvolvements(activity);
		for (SecurityThreatGroupActivityInvolvement involvement : involvements) {
			this.securityThreatGroupActivityService.removeInvolvement(
					involvement);
		}
		List<SecurityThreatGroupActivityNote> notes = 
				securityThreatGroupActivityService.findNotes(activity);
		for (SecurityThreatGroupActivityNote note : notes) {
			this.securityThreatGroupActivityService.removeNote(note);
		}
		this.securityThreatGroupActivityService.remove(activity);
		return this.prepareListRedirect(offender);
	}
	
	/**
	 * Saves a new security threat group activity for the offender.
	 * 
	 * @param offender offender
	 * @param securityThreatGroupActivityForm form for security threat group
	 * activity
	 * @param result binding result
	 * @return redirect to list security threat group by offender
	 * @throws InvolvedOffenderRequiredException 
	 * @throws SecurityThreatGroupActivityExistsException 
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 * @throws SecurityThreatGroupActivityInvolvementExistsException 
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('STG_ACTIVITY_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			final SecurityThreatGroupActivityForm activityForm,
			final BindingResult result) throws InvolvedOffenderRequiredException, 
				SecurityThreatGroupActivityExistsException, 
				SecurityThreatGroupActivityNoteExistsException, 
				SecurityThreatGroupActivityInvolvementExistsException {
		this.securityThreatGroupActivityFormValidator.validate(
				activityForm, result);
		if (result.hasErrors()) {
			return prepareRedisplayEditMav(offender,
					activityForm,
					result);
		}
		
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityService.create(
						activityForm.getReportDate(),
						activityForm.getReportedBy(),
						activityForm.getSummary());
		this.processSecurityThreatGroupActivityNoteItems(
				activityForm.getNoteItems(), activity);
		this.processSecurityThreatGroupActivityInvolvementItems(
				activityForm.getInvolvementItems(), activity);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Updates an existing security threat group activity involvement.
	 * 
	 * @param activity security threat group activity involvement
	 * @param securityThreatGroupActivityForm form for security threat group
	 * activity involvement
	 * @param result binding result
	 * @return redirect to list security threat activity by offender
	 * @throws InvolvedOffenderRequiredException 
	 * @throws SecurityThreatGroupActivityExistsException 
	 * @throws SecurityThreatGroupActivityInvolvementExistsException 
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('STG_ACTIVITY_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
				final SecurityThreatGroupActivityForm activityForm,
				final BindingResult result) 
						throws InvolvedOffenderRequiredException, 
						SecurityThreatGroupActivityExistsException, 
						SecurityThreatGroupActivityInvolvementExistsException, 
						SecurityThreatGroupActivityNoteExistsException {
		this.securityThreatGroupActivityFormValidator.validate(activityForm, 
				result);
		if (result.hasErrors()) {
			return prepareRedisplayEditMav(offender, activityForm, result);
		}
		SecurityThreatGroupActivity activity 
		= this.securityThreatGroupActivityService.update(
				activityForm.getActivity(), activityForm.getReportedBy(), 
				activityForm.getReportDate(), activityForm.getSummary());
			this.processSecurityThreatGroupActivityInvolvementItems(
					activityForm.getInvolvementItems(), activity);
			this.processSecurityThreatGroupActivityNoteItems(
					activityForm.getNoteItems(), activity);
			return new ModelAndView(String.format(LIST_REDIRECT, 
					offender.getId()));
	}
	
	/**
	 * Processes the specified list of security threat group activity note 
	 * items according to their specified operation values.
	 * 
	 * @param items security threat group affiliation note items
	 * @param activity security threat group activity
	 * @throws SecurityThreatGroupActivityExistsException thrown when a duplicate security
	 * threat group activity note is found
	 * @throws SecurityThreatGroupActivityNoteExistsException 
	 */
	private void processSecurityThreatGroupActivityNoteItems(
			final List<SecurityThreatGroupActivityNoteItem> items,
			final SecurityThreatGroupActivity activity)
		throws SecurityThreatGroupActivityExistsException, 
		SecurityThreatGroupActivityNoteExistsException {
		if (items != null) {
			for (SecurityThreatGroupActivityNoteItem item 
					: items) {
				if (SecurityThreatGroupActivityNoteItemOperation.CREATE
						.equals(item.getOperation())) {
					this.securityThreatGroupActivityService
						.addNote(activity, item.getDate(), 
								item.getValue());
				} else if (SecurityThreatGroupActivityNoteItemOperation
						.UPDATE.equals(item.getOperation())) {
					this.securityThreatGroupActivityService.updateNote(
							item.getActivityNote(), item.getDate(),
							item.getValue());
				} else if (SecurityThreatGroupActivityNoteItemOperation
						.REMOVE.equals(item.getOperation())) {
					this.securityThreatGroupActivityService.removeNote(
							item.getActivityNote());
				}
			}
		}
	}
	
	/**
	 * Processes the specified list of security threat group activity involvement 
	 * items according to their specified operation values.
	 * 
	 * @param items security threat group activity involvement items
	 * @param activity security threat group activity
	 * @throws InvolvedOffenderRequiredException 
	 * @throws SecurityThreatGroupActivityInvolvementExistsException thrown when a duplicate security
	 * threat group activity involvement is found
	 */
	private void processSecurityThreatGroupActivityInvolvementItems(
			final List<SecurityThreatGroupActivityInvolvementItem> items,
			final SecurityThreatGroupActivity activity)
		throws InvolvedOffenderRequiredException, 
			SecurityThreatGroupActivityInvolvementExistsException {
		if (items != null) {
			for (SecurityThreatGroupActivityInvolvementItem item 
					: items) {
				if (SecurityThreatGroupActivityInvolvementItemOperation.CREATE
						.equals(item.getOperation())) {
					this.securityThreatGroupActivityService
						.involveOffender(item.getOffender(), activity, 
								item.getNarrative());
				} else if (SecurityThreatGroupActivityInvolvementItemOperation
						.UPDATE.equals(item.getOperation())) {
					this.securityThreatGroupActivityService
						.updateInvolvementNarrative(
							item.getActivityInvolvement(), item.getNarrative());
				} else if (SecurityThreatGroupActivityInvolvementItemOperation
						.REMOVE.equals(item.getOperation())) {
					this.securityThreatGroupActivityService.removeInvolvement(
							item.getActivityInvolvement());
				}
			}
		}
	}
	
	/**
	 * Lists involved offenders for activities.
	 * 
	 * @param activity activity
	 * @return model and view to list activity summaries for involved activities
	 */
	@RequestMapping("/securityThreatGroupActivitySummaries.html")
	public ModelAndView listInvolvedOffenders(
			@RequestParam(value= "activity", required = true)
				final SecurityThreatGroupActivity activity) {
		List<SecurityThreatGroupActivityInvolvement> activityInvolvementSummaries 
			= this.securityThreatGroupActivityService
				.findInvolvements(activity);
		ModelAndView mav = new ModelAndView(INVOLVED_OFFENDERS_LIST_VIEW_NAME);
		mav.addObject(INVOLVED_OFFENDERS_MODEL_KEY, activityInvolvementSummaries);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Returns activity action menu. 
	 * 
	 * @param offender offender
	 * @return activity action menu
	 */
	@RequestMapping(
			value = "/activityActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showActivityActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTIVITY_ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Exception handlers. */
	
	/**
	 * Security threat group affiliation exists exception.
	 * 
	 * @param exception exception
	 * @return exception message
	 */
	@ExceptionHandler(SecurityThreatGroupActivityExistsException.class) 
	public ModelAndView handleSecurityThreatGroupActivityExistsException(
			final SecurityThreatGroupActivityExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				SECURITY_THREAT_GROUP_ACTIVITY_EXISTS_EXCEPTION, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Security threat group affiliation note exists exception.
	 * 
	 * @param exception exception
	 * @return exception message
	 */
	@ExceptionHandler(SecurityThreatGroupActivityNoteExistsException.class) 
	public ModelAndView handleSecurityThreatGroupActivityNoteExistsException(
			final SecurityThreatGroupActivityNoteExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				SECURITY_THREAT_GROUP_ACTIVITY_NOTE_EXISTS_EXCEPTION, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Security threat group rank exists exception.
	 * 
	 * @param exception exception
	 * @return exception message
	 */
	@ExceptionHandler(SecurityThreatGroupActivityInvolvementExistsException.class) 
	public ModelAndView handleSecurityThreatGroupActivityInvolvementExistsException(
			final SecurityThreatGroupActivityInvolvementExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				SECURITY_THREAT_GROUP_ACTIVITY_INVOLVEMENT_EXISTS_EXCEPTION, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Security threat group chapter exists exception.
	 * 
	 * @param exception exception
	 * @return exception message
	 */
	@ExceptionHandler(InvolvedOffenderRequiredException.class) 
	public ModelAndView handleInvolvedOffenderRequiredException(
			final InvolvedOffenderRequiredException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				INVOLVED_OFFENDER_REQUIRED_EXCEPTION, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binder.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(SecurityThreatGroupActivity.class,
				this.securityThreatGroupActivityPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupActivityNote.class, 
				this.securityThreatGroupActivityNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(SecurityThreatGroupActivityInvolvement.class, 
				this.securityThreatGroupActivityInvolvementPropertyEditorFactory
				.createPropertyEditor());
	}
}
