package omis.education.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagOperation;
import omis.education.domain.EducationAssociableDocument;
import omis.education.domain.EducationDocumentCategory;
import omis.education.service.EducationDocumentService;
import omis.education.web.form.EducationDocumentForm;
import omis.education.web.validator.EducationDocumentFormValidator;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.document.web.form.DocumentTagItem;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * EducationDocumentController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 14, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/education/")
@PreAuthorize("hasRole('USER')")
public class EducationDocumentController {
	
	/* VIEW NAMES */
	
	private static final String EDIT_VIEW_NAME = "education/editEducationDocument";
	
	private static final String EDUCATION_DOCUMENT_ACTION_MENU_VIEW_NAME 
		= "/education/includes/educationDocumentActionMenu";
	
	
	/* MODEL KEYS */
	
	private static final String FORM_MODEL_KEY = "educationDocumentForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY =
			"educationAssociableDocument";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	
	/* REDIRECT VIEW NAMES */
	
	private static final String LIST_REDIRECT 
		= "redirect:/education/list.html?offender=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY
	= "documentEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME
	= "omis.education.msgs.form";
	
	/* Services */
	
	@Autowired
	@Qualifier("educationDocumentService")
	private EducationDocumentService educationDocumentService;
	
	
	/* Property Editors */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	

	@Autowired
	@Qualifier("educationAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory educationAssociableDocumentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("educationDocumentCategoryPropertyEditorFactory")
	private PropertyEditorFactory educationDocumentCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("documentFilenameGenerator")
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("educationDocumentPersister")
	private DocumentPersister educationDocumentPersister;
	
	@Autowired
	@Qualifier("educationDocumentRetriever")
	private DocumentRetriever educationDocumentRetriever;
	
	@Autowired
	@Qualifier("educationDocumentRemover")
	private FileRemover educationDocumentRemover;
	
	/* Validator */
	
	@Autowired
	@Qualifier("educationDocumentFormValidator")
	private EducationDocumentFormValidator educationDocumentFormValidator;
	
	
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
	 * Returns a model and view to create an education associable document
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/document/create.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(
			value = "offender", required = true)
		final Offender offender){
		
		return this.prepareEditMav(offender, new EducationDocumentForm(),
				new ModelMap());
	
	}
	
	/**
	 * Attempts to save an education associable document and returns to the 
	 * list screen on successful save
	 * @param offender - Offender
	 * @param form - EducationDocumentForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to the list screen after successful save, 
	 * or back to edit screen on form error
	 * @throws DuplicateEntityFoundException - When EducationAssociableDocument
	 * already exists with provided document and offender (should never happen
	 * under normal circumstances)
	 */
	@RequestMapping(value = "/document/create.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('EDUCATION_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(@RequestParam(value = "offender", required = true)
		final Offender offender, final EducationDocumentForm form,
		final BindingResult bindingResult)
				throws DuplicateEntityFoundException{
		
		this.educationDocumentFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return prepareEditMav(offender, form, new ModelMap());
		}
		else{
			final String fileExtension = form.getFileExtension();
			this.documentFilenameGenerator
					.setExtension(fileExtension);
			final String filename =
					this.documentFilenameGenerator.generate();
			Document document = this.educationDocumentService.createDocument(
					form.getDate(), filename, fileExtension, form.getTitle());
			this.educationDocumentPersister.persist(document, form.getData());
			this.processDocumentTags(form.getDocumentTagItems(), document);
			
			this.educationDocumentService.create(offender, document,
					form.getCategory());
			
			
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					offender.getId()));
		}
	}
	
	/**
	 * Returns ModelAndView to edit a specified education associable document
	 * @param educationAssociableDocument - education associable document being
	 * edited
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/document/edit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "educationAssociableDocument",
			required = true)
			final EducationAssociableDocument educationAssociableDocument){
		ModelMap map = new ModelMap();
		map.addAttribute(EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY,
				educationAssociableDocument);
		
		return this.prepareEditMav(educationAssociableDocument.getOffender(),
				this.prepareEditForm(educationAssociableDocument), map);
	}
	
	/**
	 * Attempts to update an EducationAssociableDocument and return to the list
	 * screen
	 * @param educationAssociableDocument - EducationAssociableDocument to be 
	 * edited
	 * @param form - EducationDocumentForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to the list screen after successful update, 
	 * or back to edit screen on form error
	 * @throws DuplicateEntityFoundException - When EducationAssociableDocument
	 * already exists with provided document and offender (should never happen
	 * under normal circumstances)
	 */
	@RequestMapping(value = "/document/edit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('EDUCATION_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView update(@RequestParam(value = "educationAssociableDocument", 
			required = true)
			final EducationAssociableDocument educationAssociableDocument,
			final EducationDocumentForm form, final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.educationDocumentFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			ModelMap map = new ModelMap();
			map.addAttribute(EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY,
					educationAssociableDocument);
			return prepareEditMav(educationAssociableDocument.getOffender(),
					form, map);
		}
		else{
			Document document = this.educationDocumentService.updateDocument(
					educationAssociableDocument.getDocument(), form.getDate(),
					form.getTitle());
			this.processDocumentTags(form.getDocumentTagItems(), document);
			this.educationDocumentService.update(educationAssociableDocument,
					document, form.getCategory());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					educationAssociableDocument.getOffender().getId()));
		}
	}
	
	/**
	 * Removes a specified EducationAssociableDocument
	 * @param educationAssociableDocument - EducationAssociableDocument to be 
	 * removed
	 * @return ModelAndView - Returns to the list screen after successful 
	 * removal
	 */
	@RequestMapping(value = "/document/remove.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_REMOVE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "educationAssociableDocument", 
			required = true)
			final EducationAssociableDocument educationAssociableDocument){
		List<DocumentTag> documentTags = this.educationDocumentService
				.findDocumentTagsByDocument(educationAssociableDocument
						.getDocument());
		for(DocumentTag documentTag : documentTags){
			this.educationDocumentService.removeDocumentTag(documentTag);
		}
		this.educationDocumentService.remove(educationAssociableDocument);
		this.educationDocumentRemover.remove(educationAssociableDocument
				.getDocument().getFilename());
		this.educationDocumentService.removeDocument
		(educationAssociableDocument.getDocument());
		
		return new ModelAndView(String.format(LIST_REDIRECT,
				educationAssociableDocument.getOffender().getId()));
	}
	
	/* Action Menus */
	
	/**
	 * Returns model and view for education document action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/document/educationDocumentActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayEducationDocumentActionMenu(@RequestParam(
			value = "offender", required = true) final Offender offender){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(EDUCATION_DOCUMENT_ACTION_MENU_VIEW_NAME, map);
	}
	
	
	
	/* Helpers */
	
	/**
	 * Prepares a ModelAndView for creation/editing of a EducationAssociableDocument
	 * @param offender - Offender for which EducationAssociableDocument is being
	 * made
	 * @param form - EducationDocumentForm
	 * @param map - Modelmap
	 * @return ModelAndView - edit screen for an EducationAssociableDocument
	 */
	private ModelAndView prepareEditMav(final Offender offender,
			final EducationDocumentForm form, final ModelMap map){
		
		List<EducationDocumentCategory> categories = this.educationDocumentService
				.findAllEducationDocumentCategories();
		
		map.addAttribute(CATEGORIES_MODEL_KEY, categories);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(FORM_MODEL_KEY, form);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates an EducationDocumentForm with an existing 
	 * EducationAssociableDocument's properties 
	 * @param educationAssociableDocument - EducationAssociableDocument to be
	 * edited
	 * @return Populated EducationDocumentForm
	 */
	private EducationDocumentForm prepareEditForm(
			final EducationAssociableDocument educationAssociableDocument){
		
		EducationDocumentForm form = new EducationDocumentForm();
		
		List<DocumentTag> documentTags = this.educationDocumentService
				.findDocumentTagsByDocument(
						educationAssociableDocument.getDocument());
		List<DocumentTagItem> documentTagItems = new ArrayList<DocumentTagItem>();
		for(DocumentTag tag : documentTags){
			DocumentTagItem item = new DocumentTagItem();
			
			item.setDocumentTag(tag);
			item.setName(tag.getName());
			item.setDocumentTagOperation(DocumentTagOperation.UPDATE);
			
			documentTagItems.add(item);
		}
		
		form.setCategory(educationAssociableDocument.getCategory());
		form.setDate(educationAssociableDocument.getDocument().getDate());
		form.setDocument(educationAssociableDocument.getDocument());
		form.setDocumentTagItems(documentTagItems);
		form.setFileExtension(educationAssociableDocument.getDocument()
				.getFileExtension());
		form.setTitle(educationAssociableDocument.getDocument().getTitle());
		form.setData(this.educationDocumentRetriever.retrieve(
				educationAssociableDocument.getDocument()));
		
		return form;
	}
	
	
	/**
	 * Processes a list of documentTag items for creation, updating, or removal
	 * @param documentTagItems - List of DocumentTagItems
	 * @param document - Document for which the documentTags are being processed
	 * for
	 * @throws DuplicateEntityFoundException - When a document tag already exists
	 * with given name and document
	 */
	private void processDocumentTags(
			final List<DocumentTagItem> documentTagItems, 
			final Document document) throws DuplicateEntityFoundException {
		if(documentTagItems != null){
			Iterator<DocumentTagItem> documentTagIterator = 
					documentTagItems.iterator();
			while (documentTagIterator.hasNext()) {
				final DocumentTagItem documentTagItem = documentTagIterator.next();
				final DocumentTagOperation documentTagOperation = 
						documentTagItem.getDocumentTagOperation();
				if (documentTagOperation == DocumentTagOperation.UPDATE) {
					this.educationDocumentService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.CREATE) {
					this.educationDocumentService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.REMOVE) {
					this.educationDocumentService.removeDocumentTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}
	
	
	/** Retrieves document file.
	 * @param document - document
	 * @param httpServletResponse - HTTP Servlet response. */
	@RequestMapping(value = "document/retrieveFile.html", method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.educationDocumentRetriever.retrieve(document);
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
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(
				Offender.class, this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(EducationAssociableDocument.class, 
				this.educationAssociableDocumentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(EducationDocumentCategory.class, 
				this.educationDocumentCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	
	
}
