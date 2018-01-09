package omis.presentenceinvestigation.web.controller;

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
import omis.exception.DuplicateEntityFoundException;
import omis.hearing.report.ViolationSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.SupervisionHistoryNote;
import omis.presentenceinvestigation.domain.SupervisionHistorySectionSummary;
import omis.presentenceinvestigation.service.SupervisionHistorySummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.SupervisionHistoryNoteItem;
import omis.presentenceinvestigation.web.form.SupervisionHistorySectionSummaryForm;
import omis.presentenceinvestigation.web.validator.SupervisionHistorySectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Supervision history section summary controller.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 14, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/supervisionHistorySummary/")
@PreAuthorize("hasRole('USER')")
public class SupervisionHistorySectionSummaryController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME 
	= "/presentenceInvestigation/supervisionHistorySummary/edit";

	private static final String
	SUPERVISION_HISTORY_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME 
		= "/presentenceInvestigation/supervisionHistorySummary/includes/"
			+ "supervisionHistoryNoteTableRow";
	
	private static final String
	SUPERVISION_HISTORY_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME 
		= "/presentenceInvestigation/supervisionHistorySummary/includes/"
		+ "supervisionHistorySummaryActionMenu";
	
	private static final String
	SUPERVISION_HISTORY_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME 
		= "/presentenceInvestigation/supervisionHistorySummary/includes/"
				+ "supervisionHistoryNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String SUPERVISION_HISTORY_SECTION_SUMMARY_MODEL_KEY
		= "supervisionHistorySectionSummary";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY 
		= "presentenceInvestigationRequest";
	
	private static final String SUPERVISION_HISTORY_SECTION_SUMMARY_FORM_MODEL_KEY
		= "supervisionHistorySectionSummaryForm";
	
	private static final String SUPERVISION_HISTORY_NOTE_ITEM_MODEL_KEY
		= "supervisionHistoryNoteItem";
	
	private static final String SUPERVISION_HISTORY_NOTE_ITEM_INDEX_MODEL_KEY
	= "supervisionHistoryNoteItemIndex";
	
	/* Redirect View Names */
	
	private static final String HOME_REDIRECT 
		= "redirect:/presentenceInvestigation/home.html?"
		+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY 
		= "supervisionHistorySectionSummaryEntity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME 
		= "omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("supervisionHistorySummaryService")
	private SupervisionHistorySummaryService supervisionHistorySummaryService;
	
	@Autowired
	@Qualifier("violationSummaryReportService")
	private ViolationSummaryReportService violationSummaryReportService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("supervisionHistorySectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory
		supervisionHistorySectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisionHistorySectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
		supervisionHistorySectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	PropertyEditorFactory presentenceInvestigationRequestPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory 
					offenderPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSummaryModelDelegate")
	private PresentenceInvestigationRequestSummaryModelDelegate
			presentenceInvestigationRequestSummaryModelDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("supervisionHistorySectionSummaryFormValidator")
	private SupervisionHistorySectionSummaryFormValidator
		supervisionHistorySectionSummaryFormValidator;

	/** Instantiates an implementation of SupervisionHistorySectionSummary */
	public SupervisionHistorySectionSummaryController() {
		// Default constructor.
	}
	
	/**
	 * Returns a model and view to edit a supervision history section summary.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return model and view
	 * @throws DuplicateEntityFoundException
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPERVISION_HISTORY_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(
			value = "presentenceInvestigationRequest", required = true)
		final PresentenceInvestigationRequest presentenceInvestigationRequest)		
			throws DuplicateEntityFoundException {
		
		return this.prepareEditMav(	presentenceInvestigationRequest, 
				this.prepareForm(presentenceInvestigationRequest));
	}

	/**
	 * Returns model and view of supervision history section summary of 
	 * presentence investigation request.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param form form
	 * @param result result
	 * @return model and view
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISION_HISTORY_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "presentenceInvestigationRequest", 
			required = true) final PresentenceInvestigationRequest 
			presentenceInvestigationRequest,
			final SupervisionHistorySectionSummaryForm form, 
			final BindingResult result) throws DuplicateEntityFoundException {
		this.supervisionHistorySectionSummaryFormValidator.validate(
				form, result);
	
		if (result.hasErrors()) {
			System.out.println("ERRORS ARE IN SAVE:.... " + result);
			return this.prepareRedisplayMav(presentenceInvestigationRequest, 
					form, result);
		}
		
		SupervisionHistorySectionSummary summary 
		= this.supervisionHistorySummaryService
		.findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
				presentenceInvestigationRequest);
		if (summary == null) {
			summary = this.supervisionHistorySummaryService
					.createSupervisionHistorySectionSummary(form.getText(), 
							presentenceInvestigationRequest);
			} else {
				summary = this.supervisionHistorySummaryService
						.updateSupervisionHistorySection(summary, 
								form.getText(), 
								presentenceInvestigationRequest);
			}
		
		if (form.getSupervisionHistoryNoteItems() != null) {
			for (SupervisionHistoryNoteItem item 
					: form.getSupervisionHistoryNoteItems()) {
				if (item.getItemOperation() != null) {
				if (PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())) {
					this.supervisionHistorySummaryService
					.createSupervisionHistoryNote(
							item.getDescription(), item.getDate(), summary);
				} else if (PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())) {
					if (this.isNoteChanged(
							item.getSupervisionHistoryNote(), item.getDate(), 
							item.getDescription())) {
					this.supervisionHistorySummaryService
					.updateSupervisionHistoryNote(
							item.getSupervisionHistoryNote(), 
							item.getSupervisionHistoryNote()
								.getSupervisionHistorySectionSummary(), 
							item.getDescription(), item.getDate());
					}
				} else if (PresentenceInvestigationItemOperation.REMOVE.equals(
						item.getItemOperation())) {
					this.supervisionHistorySummaryService
					.removeSupervisionHistoryNote(
							item.getSupervisionHistoryNote());
				} else {
					throw new UnsupportedOperationException(
							"Operation not supported: " 
									+ item.getItemOperation());
				}
				}
			}
		}
		return new ModelAndView(String.format(HOME_REDIRECT, 
				presentenceInvestigationRequest.getId()));
	}
	
	/**
	 * Returns a model and view of new supervision history note item.
	 *
	 *
	 * @param supervisionHistoryNoteItemIndex supervision history 
	 * note item index
	 * @return model and view
	 */
	@RequestMapping(value = "createSupervisionHistoryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView createSupervisionHistoryNoteItem(@RequestParam(
			value = "supervisionHistoryNoteItemIndex", required = true)
			final Integer supervisionHistoryNoteItemIndex) {
		ModelMap map = new ModelMap();
		SupervisionHistoryNoteItem item = new SupervisionHistoryNoteItem();
		item.setItemOperation(PresentenceInvestigationItemOperation.CREATE);
		map.addAttribute(SUPERVISION_HISTORY_NOTE_ITEM_INDEX_MODEL_KEY, 
				supervisionHistoryNoteItemIndex);
		map.addAttribute(SUPERVISION_HISTORY_NOTE_ITEM_MODEL_KEY, item);
		return new ModelAndView(
				SUPERVISION_HISTORY_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, 
				map);
	}
	
	/**
	 * Returns a model and view of supervision history summary action menu.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return model and view
	 */
	@RequestMapping(value = "/supervisionHistorySummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView supervisionHistorySummaryActionMenu(
			@RequestParam(value = "presentenceInvestigationRequest", 
			required = true)
			final PresentenceInvestigationRequest 
			presentenceInvestigationRequest) {
		ModelMap map = new ModelMap();
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
				presentenceInvestigationRequest);
		return new ModelAndView(
				SUPERVISION_HISTORY_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Returns model and view of supervision history note items action menu.
	 *
	 *
	 * @return model and view
	 */
	@RequestMapping(value = "/supervisionHistoryNoteItemsActionMenu", 
			method = RequestMethod.GET)
	public ModelAndView supervisionHistoryNoteItemsActionMenu(@RequestParam(
			value = "supervisionHistoryNoteItemIndex", required = true)
			final Integer supervisionHistoryNoteItemIndex) {
		ModelMap map = new ModelMap();
		map.addAttribute(SUPERVISION_HISTORY_NOTE_ITEM_INDEX_MODEL_KEY, 
				supervisionHistoryNoteItemIndex);
		return new ModelAndView(
		 SUPERVISION_HISTORY_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME, 
			map);
	}
	
	/* Helper methods. */
	
	/**
	 * Populates the supervision history section summary form.
	 *
	 * @param summary summary
	 * @return supervision history section summary form
	 */
	private SupervisionHistorySectionSummaryForm prepareForm(
			//final SupervisionHistorySectionSummary summary
			final PresentenceInvestigationRequest request) {
		
		SupervisionHistorySectionSummaryForm form 
			= new SupervisionHistorySectionSummaryForm();
		SupervisionHistorySectionSummary summary = this
				.supervisionHistorySummaryService
				.findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
						request);
		List<SupervisionHistoryNote> notes 
				= this.supervisionHistorySummaryService
				.findSupervisionHistoryNotesBySupervisionHistorySectionSummary(
						summary);
		List<SupervisionHistoryNoteItem> noteItems 
			= new ArrayList<SupervisionHistoryNoteItem>();
		if (summary != null) {
			form.setText(summary.getText());
			for (SupervisionHistoryNote note : notes) {
				SupervisionHistoryNoteItem item 
					= new SupervisionHistoryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setSupervisionHistoryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				noteItems.add(item);
			}
		form.setSupervisionHistoryNoteItems(noteItems);
		}
		return form;
	}
	
	/**
	 * Prepares supervision history section summary form for edit.
	 *
	 * @param summary summary
	 * @param form form
	 * @return form
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest request, 
			final SupervisionHistorySectionSummaryForm form) {

		ModelMap map = new ModelMap();
		int noteIndex;
		if (form.getSupervisionHistoryNoteItems() == null) {
			noteIndex = 0;
		} else {
			noteIndex = form.getSupervisionHistoryNoteItems().size();
		}		
		SupervisionHistorySectionSummary summary 
			= this.supervisionHistorySummaryService
			.findSupervisionHistorySectionSummaryByPresentenceInvestigationRequest(
					request);

		map.addAttribute("resolvedViolationSummaries", 
				this.violationSummaryReportService
				.findResolvedViolationSummariesByOffender((Offender) 
						request
						.getDocket().getPerson()));
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
				request);
		this.presentenceInvestigationRequestSummaryModelDelegate.add(
				map, request);
		this.offenderSummaryModelDelegate.add(map, (Offender) 
				request
				.getDocket().getPerson());
		map.addAttribute(
				SUPERVISION_HISTORY_NOTE_ITEM_INDEX_MODEL_KEY, noteIndex);
		map.addAttribute(
				SUPERVISION_HISTORY_SECTION_SUMMARY_FORM_MODEL_KEY, form);
		map.addAttribute(
				SUPERVISION_HISTORY_SECTION_SUMMARY_MODEL_KEY, summary);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Prepares model and view of supervision history section summary 
	 * for redisplay.
	 *
	 *
	 * @param summary summary
	 * @param form form
	 * @param result result
	 * @return model and view
	 */
	private ModelAndView prepareRedisplayMav(
			final PresentenceInvestigationRequest request, 
			final SupervisionHistorySectionSummaryForm form, 
			final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(request, form);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX 
				+ SUPERVISION_HISTORY_SECTION_SUMMARY_FORM_MODEL_KEY, result);
		return mav;
	}
	
	/*
	 * Returns whether the military service term note has different
	 * values than the supplied value and date.
	 * 
	 * @param note military service term note
	 * @param date date
	 * @param value value
	 * @return whether note information is changed
	 */
	private boolean isNoteChanged(final SupervisionHistoryNote note,
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

	/* InitBinder */	

	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(SupervisionHistorySectionSummary.class, 
				this.supervisionHistorySectionSummaryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(SupervisionHistoryNote.class, 
				this.supervisionHistorySectionSummaryNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}