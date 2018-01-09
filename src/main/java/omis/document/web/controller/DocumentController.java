package omis.document.web.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import omis.beans.factory.PropertyEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.document.domain.Document;
import omis.document.io.DocumentRetriever;
import omis.document.report.DocumentSummaryReportService;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Document controller for document related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 24, 2015)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/documents")
@PreAuthorize("(hasRole('USER') and hasRole('DOCUMENT_MODULE')) "
		+ "or hasRole('ADMIN')")
public class DocumentController {
	private static final String DEFAULT_FILTER_VALUE = "all";
	
	/* Exception messages */
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* View names */
	private static final String ACTION_MENU_VIEW = 
			"/document/includes/actionMenu";
	private static final String DOCUMENT_PROFILE_VIEW = "/document/profile";
	private static final String DOCUMENT_LIST_VIEW = 
			"/document/includes/listTable";
	private static final String DOCUMENT_TAG_ITEM_VIEW = 
			"/document/includes/documentTagListItem";
	
	/* Model keys */
	private static final String DOCUMENT_CONTROLLER_ITEMS_MODEL_KEY = 
			"documentItems";
	private static final String DOCUMENT_ASSOCIATION_TYPE_MODEL_KEY = 
			"documentAssociationType";
	private static final String DOCUMENTS_MODEL_KEY = "documentSummaries";
	private static final String OFFENDER_MODEL_KEY = "offender";
	private static final String FILTER_MODEL_KEY = "filter";
	private static final String INDEX_MODEL_KEY = "index";
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	/* Controller delegate */
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	private OffenderDocumentAssociationItemRegistry 
		offenderDocumentAssociationItemRegistry;
	
	/* Service */
	@Autowired
	private DocumentSummaryReportService documentSummaryReportService;
	
	/* Property editor factories */
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	@Autowired
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	/* IO */
	@Autowired
	private DocumentRetriever documentRetriever;
	
	/** document profile.
	 * @param offender - offender.
	 * @param documentAssociationType - document association type.
	 * @return document profile view. */
	@RequestMapping(value = "profile.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "documentsProfileScreenName",
		descriptionKey = "documentsProfileScreenDescription",
		messageBundle = "omis.document.msgs.document",
		screenType = RequestContentType.PROFILE_SCREEN)
	@PreAuthorize("(hasRole('DOCUMENT_PROFILE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView profile(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "documentAssociationType", required = false)
			final String documentAssociationType) {
		final ModelAndView mav;
		ModelMap  modelMap = new ModelMap();
		this.prepareProfileMap(modelMap, offender);
		if (documentAssociationType != null) {
			modelMap.put(DOCUMENT_ASSOCIATION_TYPE_MODEL_KEY, 
					documentAssociationType);
		}
		mav = new ModelAndView(DOCUMENT_PROFILE_VIEW, modelMap);
		return mav;
	}
	
	/** list all.
	 * @param offender - offender.
	 * @return list of all documents. */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "documentListScreenName",
		descriptionKey = "documentListScreenDescription",
		messageBundle = "omis.document.msgs.document",
		screenType = RequestContentType.AJAX_REQUEST)
	@PreAuthorize("(hasRole('DOCUMENT_PROFILE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender) {
		final ModelMap modelMap = new ModelMap();
		final ModelAndView mav;
		this.prepareDocumentList(modelMap, offender);
		this.prepareFilterMap(modelMap);
		mav = new ModelAndView(DOCUMENT_LIST_VIEW, modelMap);
		return mav;
	}
		
	
	/** document profile action menu.
	 * @param offender - offender.
	 * @return action menu. */
	@RequestMapping(value = "actionMenu.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "documentsProfileActionMenuScreenName",
		descriptionKey = "documentsProfileActionMenudScreenDescription",
		messageBundle = "omis.document.msgs.document",
		screenType = RequestContentType.AJAX_REQUEST)
	@PreAuthorize("(hasRole('DOCUMENT_PROFILE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView actionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		this.prepareControllerItems(modelMap);
		this.prepareOffenderMap(modelMap, offender);
		mav = new ModelAndView(ACTION_MENU_VIEW, modelMap);
		return mav;
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
		final byte[] bytes = this.documentRetriever.retrieve(document);
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
	
	/** Adds document tag.
	 * @param index - index.
	 * @return document tag item. */
	@RequestMapping(value = "addDocumentTag.html", method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_TAG') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView addDocumentTag(
			@RequestParam(value = "index", required = true)
			final int index) {
		final ModelMap modelMap = new ModelMap();
		final ModelAndView modelAndView;
		final DocumentTagItem documentTagItem = new DocumentTagItem();
		documentTagItem.setDocumentTagOperation(DocumentTagOperation.CREATE);
		modelMap.put(INDEX_MODEL_KEY, index);
		modelMap.put(DOCUMENT_TAG_ITEM_MODEL_KEY, documentTagItem);
		modelAndView = new ModelAndView(DOCUMENT_TAG_ITEM_VIEW, modelMap);
		return modelAndView;
	}
	
	
	/** Init binder.
	 * @param binder - binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, this
				.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(Document.class, this
				.documentPropertyEditorFactory.createPropertyEditor());
	}
	

	/* Prepare profile model map. */
	private void prepareProfileMap(final ModelMap modelMap, 
			final Offender offender) {
		this.prepareControllerItems(modelMap);
		this.offenderSummaryModelDelegate.add(modelMap, offender);
	}
	
	/* Prepares document controller items. */
	private void prepareControllerItems(final ModelMap modelMap) {
		modelMap.put(DOCUMENT_CONTROLLER_ITEMS_MODEL_KEY, this
				.offenderDocumentAssociationItemRegistry
				.getDocumentAssociationItems());
	}
	
	/* Prepares filter model. */
	private void prepareFilterMap(final ModelMap modelMap) {
		modelMap.put(FILTER_MODEL_KEY, DEFAULT_FILTER_VALUE);
	}
	
	private void prepareOffenderMap(final ModelMap modelMap, 
			final Offender offender) {
		modelMap.put(OFFENDER_MODEL_KEY, offender);
	}
	
	/* Prepares list view. */
	private void prepareDocumentList(final ModelMap modelMap, 
			final Offender offender) {
		modelMap.put(DOCUMENTS_MODEL_KEY, this.documentSummaryReportService
				.findByOffender(offender));
		this.prepareOffenderMap(modelMap, offender);
	}
}
