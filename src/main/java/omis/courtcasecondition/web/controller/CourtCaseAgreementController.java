package omis.courtcasecondition.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.condition.domain.Agreement;
import omis.condition.domain.AgreementAssociableDocument;
import omis.condition.domain.AgreementCategory;
import omis.condition.domain.Condition;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.courtcasecondition.service.CourtCaseAgreementService;
import omis.courtcasecondition.web.form.AgreementAssociableDocumentItem;
import omis.courtcasecondition.web.form.CourtCaseAgreementForm;
import omis.courtcasecondition.web.form.CourtCaseConditionItemOperation;
import omis.courtcasecondition.web.validator.CourtCaseAgreementFormValidator;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/** Controller for court case condition related operations.
 * @author Jonny Santy
 * @author Annie Wahl
 * @version 0.1.2 (Nov 29, 2017)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/courtCaseCondition")
@PreAuthorize("hasRole('USER')")
public class CourtCaseAgreementController {
	
	/* Model keys. */

	private static final String COURT_CASE_AGREEMENT_FORM_MODEL_KEY =
			"courtCaseAgreementForm";
	
	private static final String DOCKETS_MODEL_KEY = "dockets";
	
	private static final String COURT_CASE_AGREEMENT_MODEL_KEY =
			"courtCaseAgreement";
	
	private static final String COURT_CASE_AGREEMENT_CATEGORY_MODEL_KEY =
			"courtCaseAgreementCategory";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String
		AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY =
			"agreementAssociableDocumentItemIndex";

	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY =
			"documentTagItem";

	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY =
			"documentTagItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";

	private static final String AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_MODEL_KEY =
			"agreementAssociableDocumentItem";
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "/courtCaseCondition/edit";
	
	private static final String AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_ROW_VIEW_NAME
		= "/courtCaseCondition/includes/"
				+ "agreementAssociableDocumentItemContent";

	private static final String DOCUMENT_TAG_ITEM_ROW_VIEW_NAME =
			"/courtCaseCondition/includes/documentTagItemContent";

	/* Action menu view names. */

	private static final String COURT_CASE_CONDITION_ACTION_MENU_VIEW_NAME =
			"/courtCaseCondition/includes/courtCaseConditionActionMenu";
	
	/* Redirect view names. */
	
	private static final String LIST_REDIRECT 
		= "redirect:/courtCaseCondition/list.html?offender=%d";

	/* Message Keys */
	
	private static final String COURT_CASE_CONDITION_EXISTS_MESSAGE_KEY
		= "courtCaseAgreement.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.courtcasecondition.msgs.form";
	
	/* Delegates. */
	
	//Used for the Offender header
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("courtCaseConditionDocumentPersister")
	private DocumentPersister courtCaseConditionDocumentPersister;
	
	@Autowired
	@Qualifier("courtCaseConditionDocumentRetriever")
	private DocumentRetriever courtCaseConditionDocumentRetriever;
	
	@Autowired
	@Qualifier("courtCaseConditionDocumentRemover")
	private FileRemover courtCaseConditionDocumentRemover;	
	
	/* Services. */
	@Autowired
	private CourtCaseAgreementService courtCaseAgreementService;

	
	/* Property editors. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionGroupPropertyEditorFactory")
	private  PropertyEditorFactory conditionGroupPropertyEditorFactory;
	
	@Autowired
	@Qualifier("courtCaseAgreementPropertyEditorFactory")
	private PropertyEditorFactory courtCaseAgreementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("docketPropertyEditorFactory")
	private PropertyEditorFactory docketPropertyEditorFactory;

	@Autowired
	@Qualifier("courtCaseAgreementCategoryPropertyEditorFactory")
	private PropertyEditorFactory
		courtCaseAgreementCategoryPropertyEditorFactory;

	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("conditionPropertyEditorFactory")
	private PropertyEditorFactory conditionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionClausePropertyEditorFactory")
	private PropertyEditorFactory conditionClausePropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionTitlePropertyEditorFactory")
	private PropertyEditorFactory conditionTitlePropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	@Autowired
	@Qualifier("agreementAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory
		agreementAssociableDocumentPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("courtCaseAgreementFormValidator")
	private CourtCaseAgreementFormValidator courtCaseAgreementFormValidator;
	
	/** Default Constructor for CourtCaseAgreementController. */
	public CourtCaseAgreementController() {
	}
	
	/**
	 * Screen for the creation of a Court Case Agreement.
	 * 
	 * @param offender - Offender to create a Court Case Agreement for
	 * @param courtCaseAgreementCategory - Court Case Agreement Category to
	 * associate with the Court Case Agreement
	 * @return model and view to create Court Case Agreement. */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(
			value = "offender", required = true)
		final Offender offender, @RequestParam(
				value = "courtCaseAgreementCategory", required = true)
			final CourtCaseAgreementCategory courtCaseAgreementCategory) {
		return this.prepareEditMav(offender, courtCaseAgreementCategory,
				new CourtCaseAgreementForm(), new ModelMap());
		
	}
	
	/**
	 * Saves a new court case condition agreement.
	 * 
	 * @param offender - Offender to create a Court Case Agreement for
	 * @param courtCaseAgreementCategory - Court Case Agreement Category to
	 * associate with the Court Case Agreement
	 * @param form - Court Case Agreement Form
	 * @param bindingResult - Binding Result
	 * @return model and view to redirect to list url.
	 * @throws DuplicateEntityFoundException - When a duplicate agreement or
	 * document tag is found */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "courtCaseAgreementCategory", required = true)
				final CourtCaseAgreementCategory courtCaseAgreementCategory,
			final CourtCaseAgreementForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.courtCaseAgreementFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(offender, courtCaseAgreementCategory,
					form, new ModelMap());
		} else {
			Agreement agreement = this.courtCaseAgreementService
					.createAgreement(offender, form.getStartDate(),
							form.getEndDate(), form.getDescription(),
							AgreementCategory.COURT_CASE);
			CourtCaseAgreement courtCaseAgreement =
					this.courtCaseAgreementService.createCourtCaseAgreement(
					agreement, form.getDocket(), form.getAcceptedDate(),
					courtCaseAgreementCategory);
			this.processItems(agreement,
					form.getAgreementAssociableDocumentItems());
			return new ModelAndView(String.format(LIST_REDIRECT,
					courtCaseAgreement.getAgreement().getOffender().getId()));
		}
	}
	
	/**
	 * Screen for modification of a Court Case Condition.
	 * 
	 * @param courtCaseAgreement - court Case Agreement to edit.
	 * @return model and view to create Court Case Condition. */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "courtCaseAgreement",
			required = true) final CourtCaseAgreement courtCaseAgreement) {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute(COURT_CASE_AGREEMENT_MODEL_KEY, 
				courtCaseAgreement);
		return this.prepareEditMav(
				courtCaseAgreement.getAgreement().getOffender(),
				courtCaseAgreement.getCourtCaseAgreementCategory(),
				this.populateForm(courtCaseAgreement),
				modelMap);
	}
	
	/**
	 * Updates the specified Court Case Agreement.
	 * 
	 * @param courtCaseAgreement - CourtCaseAgreement being updated 
	 * @param form - Court Case agreement Form
	 * @param bindingResult - BindingResult
	 * @return model and view to redirect to list url. 
	 * @throws DuplicateEntityFoundException - When duplicate agreement or
	 * document tag is found
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView update(@RequestParam(value = "courtCaseAgreement",
			required = true) final CourtCaseAgreement courtCaseAgreement,
			final CourtCaseAgreementForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.courtCaseAgreementFormValidator.validate(
				form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			ModelMap modelMap = new ModelMap();
			modelMap.addAttribute(COURT_CASE_AGREEMENT_MODEL_KEY, 
					courtCaseAgreement);
			return this.prepareEditMav(
					courtCaseAgreement.getAgreement().getOffender(),
					courtCaseAgreement.getCourtCaseAgreementCategory(),
					form, modelMap);
		} else {
			this.courtCaseAgreementService.updateAgreement(
				courtCaseAgreement.getAgreement(), form.getStartDate(),
				form.getEndDate(), form.getDescription(),
				AgreementCategory.COURT_CASE);
			this.courtCaseAgreementService.updateCourtCaseAgreement(
				courtCaseAgreement, form.getDocket(), form.getAcceptedDate(),
				courtCaseAgreement.getCourtCaseAgreementCategory());
			this.processItems(courtCaseAgreement.getAgreement(),
					form.getAgreementAssociableDocumentItems());
			
			return new ModelAndView(String.format(LIST_REDIRECT, 
				courtCaseAgreement.getAgreement().getOffender().getId()));
		}
	}

	/**
	 * Removes specified CourtCaseAgreement and redirects to the 
	 * Court Case Conditions list screen.
	 * 
	 * @param courtCaseAgreement - courtCaseAgreement to remove
	 * @return redirect to Court Case Conditions list screen.
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "courtCaseAgreement", required = true)
			final CourtCaseAgreement courtCaseAgreement) {
		for (Condition condition : this.courtCaseAgreementService
				.findConditionsByAgreement(courtCaseAgreement.getAgreement())) {
			this.courtCaseAgreementService.removeCondition(condition);
		}
		this.courtCaseAgreementService.removeCourtCaseAgreement(
				courtCaseAgreement);
		this.courtCaseAgreementService.removeAgreement(courtCaseAgreement
				.getAgreement());
		
		return new ModelAndView(String.format(LIST_REDIRECT,
				courtCaseAgreement.getAgreement().getOffender().getId()));
	}

	/* Action menus. */
	
	/**
	 * Displays the action menu for CourtCaseCondition.
	 * @param offender - Offender
	 * @return ModelAndView - action menu for CourtCaseCondition
	 */
	@RequestMapping(value = "/courtCaseConditionActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayCourtCaseConditionActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(
				COURT_CASE_CONDITION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for an Agreement Associable Document Item Row.
	 * @param agreementAssociableDocumentItemIndex - Integer
	 * @return ModelAndView - View for an Agreement Associable
	 * Document Item Row.
	 */
	@RequestMapping(value = "createAgreementAssociableDocumentItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayAgreementAssociableDocumentItemRow(@RequestParam(
			value = "agreementAssociableDocumentItemIndex", required = true)
				final Integer agreementAssociableDocumentItemIndex) {
		ModelMap map = new ModelMap();
		AgreementAssociableDocumentItem item =
				new AgreementAssociableDocumentItem();
		item.setItemOperation(CourtCaseConditionItemOperation.CREATE);
		map.addAttribute(AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_MODEL_KEY, item);
		map.addAttribute(AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				agreementAssociableDocumentItemIndex);
		return new ModelAndView(
				AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a Document Tag item row.
	 * @param agreementAssociableDocumentItemIndex - Integer
	 * @param documentTagItemIndex - Integer
	 * @return ModelAndView - view for a Document Tag item row
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "agreementAssociableDocumentItemIndex", required = true)
				final Integer agreementAssociableDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setDocumentTagOperation(DocumentTagOperation.CREATE);
		map.addAttribute(AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				agreementAssociableDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_ROW_VIEW_NAME,
				map);
	}
	
	
	/* HELPER METHODS */
	
	/**
	 * Returns a prepared ModelAndView from the specified properties.
	 * @param offender - Offender
	 * @param category - CourtCaseAgreementCategory
	 * @param form - CourtCaseAgreementForm
	 * @param modelMap - ModelMap
	 * @return ModelAndView - prepared ModelAndView
	 */
	private ModelAndView prepareEditMav(
			final Offender offender, final CourtCaseAgreementCategory category,
			final CourtCaseAgreementForm form, final ModelMap modelMap) {
		this.offenderSummaryModelDelegate.add(modelMap, offender);
		
		modelMap.addAttribute(OFFENDER_MODEL_KEY, offender);
		modelMap.addAttribute(DOCKETS_MODEL_KEY,
				this.courtCaseAgreementService.findAllDocketsByOffender(
						offender));
		modelMap.addAttribute(COURT_CASE_AGREEMENT_FORM_MODEL_KEY, form);
		modelMap.addAttribute(COURT_CASE_AGREEMENT_CATEGORY_MODEL_KEY,
				category);
		
		List<Integer> dtIndexes = new ArrayList<Integer>();
		int counter = 0;
		for (AgreementAssociableDocumentItem item
				: form.getAgreementAssociableDocumentItems()) {
			dtIndexes.add(counter, item.getTagItems().size());
			counter++;
		}
		
		modelMap.addAttribute(
				AGREEMENT_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				form.getAgreementAssociableDocumentItems().size());
		modelMap.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, dtIndexes);
		
		return new ModelAndView(EDIT_VIEW_NAME, modelMap);
	}

	/**
	 * Populates a CourtCaseAgreementForm from a Court Case Agreement.
	 * @param courtCaseAgreement - Court Case Agreement to use to populate
	 * the form
	 * @return CourtCaseAgreementForm - populated form
	 */
	private CourtCaseAgreementForm populateForm(
			final CourtCaseAgreement courtCaseAgreement) {
		CourtCaseAgreementForm form = new CourtCaseAgreementForm();
		form.setAcceptedDate(courtCaseAgreement.getAcceptedDate());
		form.setStartDate(courtCaseAgreement.getAgreement().getDateRange()
				.getStartDate());
		form.setEndDate(courtCaseAgreement.getAgreement().getDateRange()
				.getEndDate());
		form.setDocket(courtCaseAgreement.getDocket());		
		form.setDescription(courtCaseAgreement.getAgreement().getDescription());
		List<AgreementAssociableDocumentItem> documentItems =
				new ArrayList<AgreementAssociableDocumentItem>();
		for (AgreementAssociableDocument agreementAssociableDocument
				: this.courtCaseAgreementService
				.findAgreementAssociableDocumentsByAgreement(
					courtCaseAgreement.getAgreement())) {
			AgreementAssociableDocumentItem item =
					new AgreementAssociableDocumentItem();
			item.setAgreementAssociableDocument(agreementAssociableDocument);
			item.setItemOperation(CourtCaseConditionItemOperation.UPDATE);
			item.setDate(agreementAssociableDocument.getDocument().getDate());
			item.setDocument(agreementAssociableDocument.getDocument());
			item.setFileExtension(agreementAssociableDocument.getDocument()
					.getFileExtension());
			item.setTitle(agreementAssociableDocument.getDocument().getTitle());
			item.setData(this.courtCaseConditionDocumentRetriever.retrieve(
					agreementAssociableDocument.getDocument()));
			List<DocumentTagItem> documentTagItems =
					new ArrayList<DocumentTagItem>();
			for (DocumentTag tag : this.courtCaseAgreementService
					.findDocumentTagsByDocument(
							agreementAssociableDocument.getDocument())) {
				DocumentTagItem tagItem = new DocumentTagItem();
				tagItem.setDocumentTag(tag);
				tagItem.setName(tag.getName());
				tagItem.setDocumentTagOperation(DocumentTagOperation.UPDATE);
				documentTagItems.add(tagItem);
			}
			item.setTagItems(documentTagItems);
			documentItems.add(item);
		}
		form.setAgreementAssociableDocumentItems(documentItems);
		
		return form;
	}
	
	/**
	 * Processes a list of Agreement Associable Document Items to be created,
	 * updated, or removed.
	 * @param agreement - Agreement
	 * @param documentItems - List of Agreement Associable Document Items
	 * @throws DuplicateEntityFoundException - When a duplicate document tag is
	 * found.
	 */
	private void processItems(final Agreement agreement,
			final List<AgreementAssociableDocumentItem> documentItems)
					throws DuplicateEntityFoundException {
		for (AgreementAssociableDocumentItem item : documentItems) {
			if (CourtCaseConditionItemOperation.CREATE.equals(
					item.getItemOperation())) {
				final String fileExtension = item.getFileExtension();
				this.documentFilenameGenerator
						.setExtension(fileExtension);
				final String filename =
						this.documentFilenameGenerator.generate();
				Document document = this.courtCaseAgreementService
						.createDocument(item.getDate(),
								filename, item.getFileExtension(),
								item.getTitle());
				this.courtCaseConditionDocumentPersister
						.persist(document, item.getData());
				this.courtCaseAgreementService
					.createAgreementAssociableDocument(agreement, document);
				if (item.getTagItems() != null) {
					for (DocumentTagItem tagItem : item.getTagItems()) {
						if (DocumentTagOperation.CREATE.equals(
								tagItem.getDocumentTagOperation())) {
							this.courtCaseAgreementService.createDocumentTag(
									document, tagItem.getName());
						}
					}
				}
			} else if (CourtCaseConditionItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				Document document = this.courtCaseAgreementService
						.updateDocument(item.getDocument(),
								item.getDate(), item.getTitle());
				this.courtCaseAgreementService
					.updateAgreementAssociableDocument(
						item.getAgreementAssociableDocument(),
						agreement, document);
				if (item.getTagItems() != null) {
					for (DocumentTagItem tagItem : item.getTagItems()) {
						if (DocumentTagOperation.CREATE.equals(
								tagItem.getDocumentTagOperation())) {
							this.courtCaseAgreementService.createDocumentTag(
									document, tagItem.getName());
						}
						if (DocumentTagOperation.UPDATE.equals(
								tagItem.getDocumentTagOperation())) {
							this.courtCaseAgreementService.updateDocumentTag(
									tagItem.getDocumentTag(),
									tagItem.getName());
						}
						if (DocumentTagOperation.REMOVE.equals(
								tagItem.getDocumentTagOperation())) {
							this.courtCaseAgreementService.removeDocumentTag(
									tagItem.getDocumentTag());
						}
					}
				}
			} else if (CourtCaseConditionItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				for (DocumentTag tag : this.courtCaseAgreementService
						.findDocumentTagsByDocument(item.getDocument())) {
					this.courtCaseAgreementService.removeDocumentTag(tag);
				}
				this.courtCaseAgreementService
					.removeAgreementAssociableDocument(
						item.getAgreementAssociableDocument());
				this.courtCaseConditionDocumentRemover.remove(
						item.getDocument().getFilename());
				this.courtCaseAgreementService.removeDocument(
						item.getDocument());
			}
		}
	}
	
	/** Retrieves document file.
	 * @param document - document
	 * @param httpServletResponse - HTTP Servlet response. */
	@RequestMapping(value = "retrieveFile.html", method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.courtCaseConditionDocumentRetriever.retrieve(
				document);
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setHeader("Content-Disposition", 
				"attachment; filename=\"" + document.getFilename() + "\"");
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		} catch (IOException ioException) {
			throw new RuntimeException(String.format(ERROR_WRITING_FILE_MSG, 
					document.getFilename()));
		}
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
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				COURT_CASE_CONDITION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/** Init binder.
	 * @param binder - web data binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(CourtCaseAgreement.class, 
				this.courtCaseAgreementPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(UserAccount.class, 
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Docket.class, 
				this.docketPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(CourtCaseAgreementCategory.class, 
				this.courtCaseAgreementCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Document.class,
				this.documentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class,
				this.documentTagPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(AgreementAssociableDocument.class,
				this.agreementAssociableDocumentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
