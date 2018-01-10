package omis.trackeddocument.web.controller;

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
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.docket.domain.Docket;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.trackeddocument.exception.TrackedDocumentReceivalExistsException;
import omis.trackeddocument.report.DocketDocumentReceivalSummary;
import omis.trackeddocument.report.DocketDocumentTrackingReportService;
import omis.trackeddocument.service.DocumentTrackingService;
import omis.trackeddocument.service.delegate.TrackedDocumentReceivalDelegate;
import omis.trackeddocument.web.form.TrackedDocumentForm;
import omis.trackeddocument.web.form.TrackedDocumentReceivalItem;
import omis.trackeddocument.web.form.TrackedDocumentReceivalItemOperation;
import omis.trackeddocument.web.validator.TrackedDocumentFormValidator;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for tracked document.
 * 
 * @author Yidong Li
 * @version 0.1.5 (Dec 20, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/trackedDocument")
@PreAuthorize("hasRole('USER')")
public class TrackedDocumentController {
	/* views */
	private static final String EDIT_VIEW_NAME = "trackedDocument/edit";
	private static final String LIST_VIEW_NAME = "trackedDocument/list";
	private static final String 
		TRACKED_DOCUMENT_LIST_SCREEN_ACTION_MENU_VIEW_NAME
		= "trackedDocument/includes/trackedDocumentListScreenActionMenu";
	private static final String 
		TRACKED_DOCUMENT_LIST_ROW_ACTION_MENU_VIEW_NAME
		= "trackedDocument/includes/trackedDocumentListRowActionMenu";
	private static final String
		TRACKED_DOCUMENT_EDIT_TABLE_ACTION_MENU_VIEW_NAME
		= "trackedDocument/includes/trackedDocumentEditTableActionMenu";
	private static final String	
		TRACKED_DOCUMENT_EDIT_SCREEN_ACTION_MENU_VIEW_NAME
		= "trackedDocument/includes/trackedDocumentEditScreenActionMenu";
	private static final String 
	CREATE_TRACKED_DOCUMENTS_TABLE_ROW_VIEW_NAME 
		= "trackedDocument/includes/createTrackedDocumentsTableRow";
	
	/* model keys */
	private static final String TRACKED_DOCUMENT_EDIT_FORM_MODEL_KEY 
	= "trackedDocumentForm"; 
	private static final String TRACKED_DOCUMENT_CATEGORIES_MODEL_KEY
	= "categories";
	private static final String 
		TRACKED_DOCUMENT_RECEIVAL_EXISTS_EXCEPTION_MESSAGE_KEY
		= "trackedDocument.conflicts";
	private static final String DOCKET_MODEL_KEY = "docket";
	private static final String ITEM_INDEX_MODEL_KEY
		= "itemIndex";
	private static final String DOCKETS_MODEL_KEY = "dockets";
	private static final String TRACKED_DOCUMENT_ITEM_INDEX_MODEL_KEY 
		= "trackedDocumentReceivalIndex";
	private static final String TRACKED_DOCUMENT_RECEIVAL_ITEM_MODEL_KEY
		= "trackedDocumentReceivalItem";
	private static final String DOCKET_DOCUMENT_RECEIVAL_SUMMARIES_MODEL_KEY
	= "docketDocumentReceivalSummaries";
	private static final String OFFENDER_MODEL_KEY
	= "offender";
	private static final String CREATE_FLAG_MODEL_KEY
	= "createFlag";
	private static final String TRACKED_DOCUMENT_MODEL_KEY 
	= "trackeddocument";
	
	/* Message bundles. */
	private static final String ERROR_BUNDLE_NAME = "omis.family.msgs.form";
				
	/* Redirects. */
	private static final String LIST_REDIRECT
		= "redirect:/trackedDocument/list.html?offender=%d";
	
	/* Property editor. */
	@Autowired
	@Qualifier("trackedDocumentReceivalPropertyEditorFactory")
	private PropertyEditorFactory trackedDocumentReceivalPropertyEditorFactory;
	
	@Autowired
	@Qualifier("trackedDocumentCategoryPropertyEditorFactory")
	private PropertyEditorFactory trackedDocumentCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory1;
		
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
		
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("docketPropertyEditorFactory")
	private PropertyEditorFactory docketPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
		
	/* Services. */
	@Autowired
	@Qualifier("documentTrackingService")
	private DocumentTrackingService documentTrackingService;
	
	@Autowired
	@Qualifier("docketDocumentTrackingReportService")
	private DocketDocumentTrackingReportService
	docketDocumentTrackingReportService;
	
	/* Delegate */
	@Autowired
	@Qualifier("trackedDocumentReceivalDelegate")
	private TrackedDocumentReceivalDelegate trackedDocumentReceivalDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validator */
	@Autowired
	@Qualifier("trackedDocumentFormValidator")
	private TrackedDocumentFormValidator trackedDocumentFormValidator;	
	
	/* Constructor. */
	/** Instantiates a default tracked document controller. */
	public TrackedDocumentController() {
		// Default instantiation
	}
	
	/**
	 * Displays a list of tracked document.
	 * 
	 * @param offender offender
	 * @return view to display the list of tracked documents
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true) 
		final Offender offender) {
		List<DocketDocumentReceivalSummary> docketDocumentReceivalSummaries
			= new ArrayList<DocketDocumentReceivalSummary>();
		docketDocumentReceivalSummaries 
		= this.docketDocumentTrackingReportService.summarizedByDefendant(
			(Person) offender);
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(DOCKET_DOCUMENT_RECEIVAL_SUMMARIES_MODEL_KEY,
			docketDocumentReceivalSummaries);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays screen to create a new tracked document.
	 * 
	 * @param offender offender
	 * @return model and view to create a new tracked document
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(value = "offender", 
		required = true) final Offender offender) {
		List<TrackedDocumentCategory> categories
		= this.documentTrackingService.findCategories();
		TrackedDocumentForm trackedDocumentForm = new TrackedDocumentForm();
		Boolean createFlag = true;
		return prepareEditMav(trackedDocumentForm, categories, offender,
			createFlag, 0);
	}	
	
	/**
	 * Save a new tracked document.
	 * 
	 * @param offender offender
	 * @param trackedDocumentForm tracked document form
	 * @return model and view to create a new tracked document
	 * @throws TrackedDocumentReceivalExistsException 
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(@RequestParam(value = "offender", 
		required = true) final Offender offender,
		final TrackedDocumentForm trackedDocumentForm,
		final BindingResult result)
		throws TrackedDocumentReceivalExistsException {
		this.trackedDocumentFormValidator.validate(trackedDocumentForm,
			result);
		if(result.hasErrors()){
			List<TrackedDocumentCategory> categories
			= this.documentTrackingService.findCategories();
			int itemIndex = trackedDocumentForm
				.getTrackedDocumentReceivalItems().size();
			return prepareRedisplayEditMav(trackedDocumentForm, categories,
				offender, true, itemIndex, result);
		}
		
		List<TrackedDocumentReceivalItem> trackedDocumentReceivalItems 
		= new ArrayList<TrackedDocumentReceivalItem>();
		trackedDocumentReceivalItems
			= trackedDocumentForm.getTrackedDocumentReceivalItems();
		for (TrackedDocumentReceivalItem trackedDocumentReceivalItem
			: trackedDocumentReceivalItems) {
			if(TrackedDocumentReceivalItemOperation.CREATE.equals(
				trackedDocumentReceivalItem.getOperation())){
				this.documentTrackingService.trackReceival(
				trackedDocumentForm.getDocket(), 
				trackedDocumentReceivalItem.getCategory(), 
				trackedDocumentReceivalItem.getReceivedDate(), 
				trackedDocumentReceivalItem.getReceivedByUserAccount());
			} 
		}
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/**
	 * Edit tracked documents.
	 * 
	 * @param docket docket
	 * @return model and view to edit tracked documents
	 * 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "docket", 
		required = true) final Docket docket) {
		TrackedDocumentForm trackedDocumentForm = new TrackedDocumentForm();
		List<TrackedDocumentReceival> trackedDocumentReceivals 
			= this.documentTrackingService.findReceivalsByDocket(docket);
		for (TrackedDocumentReceival receival : trackedDocumentReceivals){
			TrackedDocumentReceivalItem item
				= new TrackedDocumentReceivalItem();
			item.setCategory(receival.getCategory());
			item.setOperation(TrackedDocumentReceivalItemOperation.EDIT);
			item.setReceivedByUserAccount(receival.getReceivedByUserAccount());
			item.setReceivedDate(receival.getReceivedDate());
			item.setTrackedDocumentReceival(receival);
			trackedDocumentForm.getTrackedDocumentReceivalItems().add(item);
		}
		List<TrackedDocumentCategory> categories
		= this.documentTrackingService.findCategories();
		int itemIndex = trackedDocumentForm.getTrackedDocumentReceivalItems()
			.size();
		trackedDocumentForm.setDocket(docket);
		return prepareEditMav(trackedDocumentForm, categories,
			(Offender)docket.getPerson(), false, itemIndex);
	}
	
	/**
	 * Save tracked documents edit screen.
	 * 
	 * @param docket docket
	 * @param trackedDocumentForm submitted form
	 * @return model and view to list screen
	 * @throws TrackedDocumentReceivalExistsException 
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_EDIT') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "docket", 
		required = true) final Docket docket,
		final TrackedDocumentForm trackedDocumentForm,
		final BindingResult result)
		throws TrackedDocumentReceivalExistsException {
		this.trackedDocumentFormValidator.validate(trackedDocumentForm, result);
		if(result.hasErrors()){
			List<TrackedDocumentCategory> categories
			= this.documentTrackingService.findCategories();
			int itemIndex = trackedDocumentForm
				.getTrackedDocumentReceivalItems().size();
			return prepareRedisplayEditMav(trackedDocumentForm, categories,
				(Offender)docket.getPerson(), false, itemIndex, result);
		}
		
		List<TrackedDocumentReceivalItem> trackedDocumentReceivalItems 
		= new ArrayList<TrackedDocumentReceivalItem>();
		trackedDocumentReceivalItems
			= trackedDocumentForm.getTrackedDocumentReceivalItems();
		for (TrackedDocumentReceivalItem trackedDocumentReceivalItem
			: trackedDocumentReceivalItems) {
			if(TrackedDocumentReceivalItemOperation.CREATE.equals(
				trackedDocumentReceivalItem.getOperation())){
			this.documentTrackingService.trackReceival(docket, 
				trackedDocumentReceivalItem.getCategory(), 
				trackedDocumentReceivalItem.getReceivedDate(), 
				trackedDocumentReceivalItem.getReceivedByUserAccount());
			} else if(TrackedDocumentReceivalItemOperation.REMOVE.equals(
				trackedDocumentReceivalItem.getOperation())){
				this.documentTrackingService.remove(trackedDocumentReceivalItem
					.getTrackedDocumentReceival());
			} else if(TrackedDocumentReceivalItemOperation.EDIT.equals(
				trackedDocumentReceivalItem.getOperation())){
					;
			} else if(trackedDocumentReceivalItem.getOperation()==null){
				;
			} else {
				throw new UnsupportedOperationException(
					String.format("Unsupported operation: %s",
					trackedDocumentReceivalItem.getOperation()));
			}
		}
		return new ModelAndView(String.format(LIST_REDIRECT, 
			((Offender)(docket.getPerson())).getId()));
	}
	
	/**
	 * Removes an existing tracked document receival.
	 * 
	 * @param familyAssociation family association
	 * @param trackedDocumentReceival tracked document receival
	 * @return redirect to list religious preferences
	 */
	@RequestMapping("/remove.html")
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
		@RequestParam(value = "docket", required = true)
			final Docket docket) {
		Offender offender = (Offender) docket.getPerson();
		this.documentTrackingService.removeByDocket(docket);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}	
	
	/**
	 * Returns a view for document tracking list screen action menu pertaining
	 * to the specified offender.
	 * 
	 * @param offender offender
	 * @return model and view for document tracking list screen action menu
	 */
	@RequestMapping(value = "/trackedDocumentListScreenActionMenu.html",
	method = RequestMethod.GET)
	public ModelAndView trackedDocumentListScreenActionMenu(@RequestParam(
		value = "offender",	required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
			TRACKED_DOCUMENT_LIST_SCREEN_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view for document tracking list screen row action menu 
	 * pertaining to the specified docket.
	 * 
	 * @param docket docket
	 * @return model and view for document tracking list screen action menu
	 */
	@RequestMapping(value = "/trackedDocumentListRowActionMenu.html",
	method = RequestMethod.GET)
	public ModelAndView trackedDocumentListRowActionMenu(@RequestParam(
		value = "docket",	required = true) final Docket docket) {
		ModelMap map = new ModelMap();
		map.addAttribute(DOCKET_MODEL_KEY, docket);
		return new ModelAndView(
			TRACKED_DOCUMENT_LIST_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view for action menu of the screen 
	 * pertaining to a specific offender.
	 * 
	 * @param offender offender
	 * @return model and view for for a list of tracked documents 
	 * pertaining to a specific offender.
	 */
	@RequestMapping(value = "/trackedDocumentEditScreenActionMenu.html",
	method = RequestMethod.GET)
	public ModelAndView trackedDocumentEditScreenActionMenu(@RequestParam(
		value = "offender",	required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
			TRACKED_DOCUMENT_EDIT_SCREEN_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns an action menu for the table of edit screen of tracked documents 
	 * pertaining to a specific offender.
	 * 
	 * @param offender offender
	 * @return model and view for an action menu for the table of edit screen of
	 * tracked documents
	 */
	@RequestMapping(value = "/trackedDocumentEditTableActionMenu.html",
	method = RequestMethod.GET)
	public ModelAndView trackedDocumentEditTableActionMenu(
		@RequestParam(value = "offender",	required = true) 
		final Offender offender	) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
			TRACKED_DOCUMENT_EDIT_TABLE_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Adds a row for a tracked document item.
	 * 
	 * @param trackedDocumentIndex tracked document index
	 * @return model and view for a row of tracked document
	 */
	@RequestMapping(value = "/addTrackedDocumentItem.html",
		method = RequestMethod.GET)
	public ModelAndView addTrackedDocumentItem(
		@RequestParam(value = "offender", required = true)
		final Offender offender,
		@RequestParam(value = "trackedDocumentItemIndex", required = true)
		final int trackedDocumentItemIndex) {
		ModelMap map = new ModelMap();
		map.addAttribute(TRACKED_DOCUMENT_ITEM_INDEX_MODEL_KEY,
			trackedDocumentItemIndex);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		List<TrackedDocumentCategory> categories
			= this.documentTrackingService.findCategories(); 
		map.addAttribute(TRACKED_DOCUMENT_CATEGORIES_MODEL_KEY, categories);
		
		TrackedDocumentReceivalItem trackedDocumentReceivalItem 
			= new TrackedDocumentReceivalItem();
		trackedDocumentReceivalItem.setOperation(
			TrackedDocumentReceivalItemOperation.CREATE);
		String username = SecurityContextHolder.getContext()
			.getAuthentication().getName();
		UserAccount userAccount
		= this.documentTrackingService.findUserAccount(username);
		
		trackedDocumentReceivalItem.setReceivedByUserAccount(userAccount);
		
		map.addAttribute(TRACKED_DOCUMENT_RECEIVAL_ITEM_MODEL_KEY, 
			trackedDocumentReceivalItem);
//		map.addAttribute(CREATE_FLAG_MODEL_KEY, true);
		return new ModelAndView(
			CREATE_TRACKED_DOCUMENTS_TABLE_ROW_VIEW_NAME, map);
	}
		
	private ModelAndView prepareEditMav(
		final TrackedDocumentForm trackedDocumentForm,
		final List<TrackedDocumentCategory> categories,
		final Offender offender, final Boolean createFlag,
		final int itemIndex) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(TRACKED_DOCUMENT_EDIT_FORM_MODEL_KEY,
			trackedDocumentForm);
		mav.addObject(TRACKED_DOCUMENT_CATEGORIES_MODEL_KEY, categories);
		mav.addObject(CREATE_FLAG_MODEL_KEY, createFlag);
		mav.addObject(ITEM_INDEX_MODEL_KEY, itemIndex);
		List<Docket> dockets = this.documentTrackingService
			.findDockets(offender);
		mav.addObject(DOCKETS_MODEL_KEY, dockets);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}	
	
	// Prepares redisplay edit/create screen
		private ModelAndView prepareRedisplayEditMav(
				TrackedDocumentForm trackedDocumentForm,
				final List<TrackedDocumentCategory> categories,
				final Offender offender, final Boolean createFlag,
				final int itemIndex, final BindingResult result) {
			ModelAndView mav = this.prepareEditMav(trackedDocumentForm,
				categories, offender,createFlag, itemIndex);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ TRACKED_DOCUMENT_MODEL_KEY, result);
			return mav;
		}	
		
	/**
	 * Handles {@code TrackedDocumentReceivalExistsException}.
	 * 
	 * @param TrackedDocumentReceivalExistsException exception thrown
	 * @return screen to handle {@code TrackedDocumentReceivalExistsException}
	 */
	@ExceptionHandler(TrackedDocumentReceivalExistsException.class)
	public ModelAndView handleTrackedDocumentReceivalExistsException(
		final TrackedDocumentReceivalExistsException
		trackedDocumentReceivalExistsException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			TRACKED_DOCUMENT_RECEIVAL_EXISTS_EXCEPTION_MESSAGE_KEY,
			ERROR_BUNDLE_NAME, trackedDocumentReceivalExistsException);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(UserAccount.class,
			this.userAccountPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Person.class,
			this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(TrackedDocumentReceival.class,
			this.trackedDocumentReceivalPropertyEditorFactory
			.createPropertyEditor());
		binder.registerCustomEditor(Docket.class,
			this.docketPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(TrackedDocumentCategory.class,
			this.trackedDocumentCategoryPropertyEditorFactory
			.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
			.createCustomDateOnlyEditor(true));
	}	
}