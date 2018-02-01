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
package omis.unitmanagerreview.web.controller;

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
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.probationterm.exception.ProbationTermExistsException;
import omis.staff.domain.StaffAssignment;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.domain.UnitManagerReviewDocumentAssociation;
import omis.unitmanagerreview.service.UnitManagerReviewService;
import omis.unitmanagerreview.web.form.DocumentTagItem;
import omis.unitmanagerreview.web.form.UnitManagerReviewDocumentAssociationItem;
import omis.unitmanagerreview.web.form.UnitManagerReviewItemOperation;
import omis.unitmanagerreview.web.form.UnitManagerReviewForm;
import omis.unitmanagerreview.web.validator.UnitManagerReviewFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for managing unit manager reviews.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/unitManagerReview")
public class ManageUnitManagerReviewController {
	
	/* View names. */
	
	private static final String VIEW_NAME = "unitManagerReview/edit";

	/* Action menus. */

	private static final String ACTION_MENU_VIEW_NAME = 
			"unitManagerReview/includes/unitManagerReviewActionMenu";
	
	/* Partial views. */
	
	private static final String DOCUMENT_ASSOCIATION_ITEM_VIEW_NAME = 
			"unitManagerReview/includes/documentAssociationItemContent";
	
	private static final String DOCUMENT_TAG_ITEM_VIEW_NAME = 
			"unitManagerReview/includes/documentAssociationTagItemContent";
	
	/* Redirects. */
	
	private static final String REDIRECT_URL = 
			"redirect:/unitManagerReview/list.html?offender=%d";
	
	/* Model keys. */
	
	private static final String UNIT_MANAGER_REVIEW_FORM_MODEL_KEY = 
			"unitManagerReviewForm";
	
	private static final String UNIT_MANAGER_REVIEW_MODEL_KEY = 
			"unitManagerReview";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String 
			UNIT_MANAGER_REVIEW_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY = 
			"unitManagerReviewDocumentAssociationItem";
	
	private static final String DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY = 
			"documentAssociationItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY = 
			"documentTagItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";
	
	/* Message keys. */


	private static final String UNIT_MANAGER_REVIEW_EXISTS_MESSAGE_KEY = 
			"unitManagerReview.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = 
			"omis.unitmanagerreview.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("unitManagerReviewService")
	private UnitManagerReviewService unitManagerReviewService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("unitManagerReviewPropertyEditorFactory")
	private PropertyEditorFactory unitManagerReviewPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("staffAssignmentPropertyEditorFactory")
	private PropertyEditorFactory staffAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("unitManagerReviewDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory 
			unitManagerReviewDocumentAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	/* Validators. */

	@Autowired
	@Qualifier("unitManagerReviewFormValidator")
	private UnitManagerReviewFormValidator unitManagerReviewFormValidator;
	
	/* Helpers. */

	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("unitManagerReviewDocumentPersister")
	private DocumentPersister unitManagerReviewDocumentPersister;
	
	@Autowired
	@Qualifier("unitManagerReviewDocumentRetriever")
	private DocumentRetriever unitManagerReviewDocumentRetriever;
	
	@Autowired
	@Qualifier("unitManagerReviewDocumentRemover")
	private FileRemover unitManagerReviewDocumentRemover;
	
	/* Constructors. */
	
	/** Instantiates controller for unit manager review. */
	public ManageUnitManagerReviewController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create a unit manager review.
	 * 
	 * @param offender offender
	 * @return screen to create unit manager review
	 */
	@PreAuthorize("hasRole('UNIT_MANAGER_REVIEW_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(value = "offender", required = true) 
					final Offender offender) {
		UnitManagerReviewForm unitManagerReviewForm = 
				new UnitManagerReviewForm();
		ModelAndView mav = this.prepareMav(unitManagerReviewForm, offender);
		return mav;
	}
	
	/**
	 * Shows screen to edit a unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 * @return screen to edit a unit manager review
	 */
	@PreAuthorize("hasRole('UNIT_MANAGER_REVIEW_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "unitManagerReview", required = true)
				final UnitManagerReview unitManagerReview) {
		UnitManagerReviewForm unitManagerReviewForm = 
				new UnitManagerReviewForm();
		unitManagerReviewForm.setStaffAssignment(
				unitManagerReview.getStaffAssignment());
		unitManagerReviewForm.setDate(unitManagerReview.getDate());
		unitManagerReviewForm.setText(unitManagerReview.getText());
		List<UnitManagerReviewDocumentAssociation> documentAssociations = this
				.unitManagerReviewService
				.findUnitManagerReviewDocumentAssociationsByUnitManagerReview(
						unitManagerReview);
		List<UnitManagerReviewDocumentAssociationItem> documentItems = 
				new ArrayList<>();
		for (UnitManagerReviewDocumentAssociation documentAssociation : 
			documentAssociations) {
			UnitManagerReviewDocumentAssociationItem item = 
					new UnitManagerReviewDocumentAssociationItem();
			List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
			List<DocumentTag> tags = this.unitManagerReviewService
					.findDocumentTagsByDocument(
							documentAssociation.getDocument());
			for(DocumentTag tag : tags){
				DocumentTagItem tagItem = new DocumentTagItem();
				tagItem.setDocumentTag(tag);
				tagItem.setName(tag.getName());
				tagItem.setItemOperation(
						UnitManagerReviewItemOperation
							.UPDATE);
				tagItems.add(tagItem);
			}
			item.setDocument(documentAssociation.getDocument());
			item.setDocumentTagItems(tagItems);
			item.setFileDate(documentAssociation.getDocument().getDate());
			item.setFileExtension(documentAssociation.getDocument()
					.getFileExtension());
			item.setFilename(documentAssociation.getDocument().getFilename());
			item.setTitle(documentAssociation.getDocument().getTitle());
			item.setUnitManagerReviewDocumentAssociation(documentAssociation);
			item.setItemOperation(
					UnitManagerReviewItemOperation.UPDATE);
			item.setData(this.unitManagerReviewDocumentRetriever.retrieve(
					item.getDocument()));
			
			documentItems.add(item);
		}
		unitManagerReviewForm.setUnitManagerReviewDocumentAssociationItems(
				documentItems);
		ModelAndView mav = this.prepareMav(unitManagerReviewForm, 
				unitManagerReview.getOffender());
		mav.addObject(UNIT_MANAGER_REVIEW_MODEL_KEY, unitManagerReview);
		return mav;
	}
	
	/**
	 * Saves a unit manager review.
	 * 
	 * @param unitManagerReviewForm unit manager review form
	 * @param bindingResult binding result
	 * @return redirect to unit manager review listing screen
	 * @throws DuplicateEntityFoundException if duplicate entity exists 
	 * @throws DateConflictException if date range is not within staff 
	 * assignment date range
	 */
	@PreAuthorize("hasRole('UNIT_MANAGER_REVIEW_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
			@RequestParam(value = "offender", required = true) 
					final Offender offender,
			final UnitManagerReviewForm unitManagerReviewForm,
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		this.unitManagerReviewFormValidator.validate(unitManagerReviewForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(unitManagerReviewForm, 
					offender, bindingResult);
			return mav;
			}
		UnitManagerReview unitManagerReview = this.unitManagerReviewService
				.createUnitManagerReview(
						unitManagerReviewForm.getStaffAssignment(),
						unitManagerReviewForm.getDate(),
						unitManagerReviewForm.getText(),
						offender);
		processDocumentAssociationItems(unitManagerReview, unitManagerReviewForm
				.getUnitManagerReviewDocumentAssociationItems());
		return new ModelAndView(String.format(REDIRECT_URL, offender.getId()));
	}
	
	/**
	 * Updates a unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 * @param unitManagerReviewForm unit manager review form
	 * @param bindingResult binding result
	 * @return redirect to unit manager review listing screen
	 * @throws DuplicateEntityFoundException if duplicate entity exists 
	 * @throws DateConflictException if date range is not within staff 
	 * assignment date range
	 */
	@PreAuthorize("hasRole('UNIT_MANAGER_REVIEW_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "unitManagerReview", required = true)
				final UnitManagerReview unitManagerReview,
			final UnitManagerReviewForm unitManagerReviewForm,
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException, 
					DateConflictException {
		this.unitManagerReviewFormValidator.validate(unitManagerReviewForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(unitManagerReviewForm, 
					unitManagerReview.getOffender(), bindingResult);
			return mav;
			}
		UnitManagerReview managerReview = this.unitManagerReviewService
				.updateUnitManagerReview(unitManagerReview, 
				unitManagerReviewForm.getStaffAssignment(), 
				unitManagerReviewForm.getDate(),
				unitManagerReviewForm.getText());
		processDocumentAssociationItems(managerReview, unitManagerReviewForm
				.getUnitManagerReviewDocumentAssociationItems());
		return new ModelAndView(String.format(REDIRECT_URL, 
				managerReview.getOffender().getId()));
	}
	
	/**
	 * Removes a unit manager review.
	 * 
	 * @param unitManagerReview unit manager review
	 * @return redirect to unit manager review listing screen
	 */
	@PreAuthorize("hasRole('UNIT_MANAGER_REVIEW_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "unitManagerReview", required = true)
				final UnitManagerReview unitManagerReview) {
		Long offenderId = unitManagerReview.getOffender().getId();
		List<UnitManagerReviewDocumentAssociation> associations = this
				.unitManagerReviewService
				.findUnitManagerReviewDocumentAssociationsByUnitManagerReview(
						unitManagerReview);
		for (UnitManagerReviewDocumentAssociation association : associations) {
			Document document = association.getDocument();
			List<DocumentTag> documentTags = this.unitManagerReviewService
					.findDocumentTagsByDocument(document);
			for(DocumentTag tag : documentTags) {
				this.unitManagerReviewService.removeDocumentTag(tag);
			}
			this.unitManagerReviewService
				.removeUnitManagerReviewDocumentAssociation(association);
			this.unitManagerReviewDocumentRemover.remove(document.getFilename());
			this.unitManagerReviewService.removeDocument(document);
		}
		this.unitManagerReviewService.removeUnitManagerReview(
				unitManagerReview);
		return new ModelAndView(String.format(REDIRECT_URL, offenderId)); 
	}
	
	/**
	 * Creates a unit manager review document association item.
	 * 
	 * @param documentAssociationItemIndex unit manager review 
	 * document association item index
	 * @return model and view
	 */
	@RequestMapping(value =
			"createUnitManagerReviewDocumentAssociationItem.html",
			method = RequestMethod.GET)
	public ModelAndView createUnitManagerReviewDocumentAssociationItem(
			@RequestParam(value = "documentAssociationItemIndex", 
				required = true)
			final Integer documentAssociationItemIndex){
		ModelMap map = new ModelMap();
		UnitManagerReviewDocumentAssociationItem associableDocumentItem =
				new UnitManagerReviewDocumentAssociationItem();
		associableDocumentItem.setItemOperation(
				UnitManagerReviewItemOperation.CREATE);
		map.addAttribute(
				UNIT_MANAGER_REVIEW_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY,
				associableDocumentItem);
		map.addAttribute(DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
				documentAssociationItemIndex);
		return new ModelAndView(
				DOCUMENT_ASSOCIATION_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays a document tag item.
	 * 
	 * @param documentAssociationItemIndex document association item index
	 * @param documentTagItemIndex document tag item index
	 * @return model and view
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView createDocumentTagItem(
			@RequestParam(value = "documentAssociationItemIndex",
					required = true)
				final Integer documentAssociationItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setItemOperation(
				UnitManagerReviewItemOperation.CREATE);
		map.addAttribute(DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
			documentAssociationItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_VIEW_NAME,
				map);
	}
	
	/* Action menus. */
	
	/**
	 * Displays action menu for screen to create/edit a unit manager review.
	 * 
	 * @return action menu for screen to create/edit a unit manager review
	 */
	@RequestMapping(value = "/unitManagerReviewActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = false)
					final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles duplicate entity exists exceptions.
	 * 
	 * @param exception duplicate entity exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleUnitManagerReviewExistsException(
			final ProbationTermExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				UNIT_MANAGER_REVIEW_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareMav(
			final UnitManagerReviewForm unitManagerReviewForm,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(UNIT_MANAGER_REVIEW_FORM_MODEL_KEY, 
				unitManagerReviewForm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
				(unitManagerReviewForm
						.getUnitManagerReviewDocumentAssociationItems() != null ? 
								unitManagerReviewForm
								.getUnitManagerReviewDocumentAssociationItems()
								.size() : 0));
			if(unitManagerReviewForm
					.getUnitManagerReviewDocumentAssociationItems() != null){
				List<Integer> tagIndexes = new ArrayList<Integer>();
				for(int i = 0; i < unitManagerReviewForm
						.getUnitManagerReviewDocumentAssociationItems().size(); 
						i++){
					if(unitManagerReviewForm
							.getUnitManagerReviewDocumentAssociationItems()
							.get(i) != null){
						tagIndexes.add(i,
							(unitManagerReviewForm
								.getUnitManagerReviewDocumentAssociationItems()
								.get(i).getDocumentTagItems() != null ?
									unitManagerReviewForm
									.getUnitManagerReviewDocumentAssociationItems()
									.get(i).getDocumentTagItems().size() : 0));
					}
					else{
						tagIndexes.add(i, 0);
					}
				}
				mav.addObject(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, tagIndexes);
			}
		this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		return mav;
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplay(
			final UnitManagerReviewForm unitManagerReviewForm,
			final Offender offender,
			final BindingResult bindingResult) {
		ModelAndView mav = this.prepareMav(unitManagerReviewForm, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ UNIT_MANAGER_REVIEW_FORM_MODEL_KEY, bindingResult);
		return mav;
	}
	
	// Processes document association items
	private void processDocumentAssociationItems(
			final UnitManagerReview unitManagerReview, 
			final List<UnitManagerReviewDocumentAssociationItem>
					unitManagerReviewDocumentAssociationItems) 
							throws DuplicateEntityFoundException {
		if(unitManagerReviewDocumentAssociationItems != null){
			for(UnitManagerReviewDocumentAssociationItem item : 
				unitManagerReviewDocumentAssociationItems){
				if(UnitManagerReviewItemOperation.CREATE
						.equals(item.getItemOperation())){
					final String fileExtension = item.getFileExtension();
					this.documentFilenameGenerator.setExtension(fileExtension);
					final String filename = 
							this.documentFilenameGenerator.generate();
					Document document = this.unitManagerReviewService
							.createDocument(item.getFileDate(),
									item.getTitle(), filename, 
									item.getFileExtension());
					this.unitManagerReviewDocumentPersister
							.persist(document, item.getData());
					this.unitManagerReviewService
							.createUnitReviewDocumentAssociation(
									document, unitManagerReview);
					processDocumentTagItems(document,
							item.getDocumentTagItems());
				}
				if(UnitManagerReviewItemOperation.UPDATE
						.equals(item.getItemOperation())){
					Document document = this.unitManagerReviewService
							.updateDocument(item.getDocument(),
									item.getFileDate(), item.getTitle());
					processDocumentTagItems(document,
							item.getDocumentTagItems());
				}
				if(UnitManagerReviewItemOperation.REMOVE
						.equals(item.getItemOperation())){
					for(DocumentTag tag : this.unitManagerReviewService
							.findDocumentTagsByDocument(item.getDocument())){
						this.unitManagerReviewService.removeDocumentTag(tag);
					}
					this.unitManagerReviewService
						.removeUnitManagerReviewDocumentAssociation(
						item.getUnitManagerReviewDocumentAssociation());
					this.unitManagerReviewDocumentRemover.remove(
							item.getDocument().getFilename());
					this.unitManagerReviewService.removeDocument(
							item.getDocument());
				}
			}
		}
	}
	
	// Processes document tag items
	private void processDocumentTagItems(final Document document, 
			final List<DocumentTagItem> documentTagItems) 
					throws DuplicateEntityFoundException {
		if(documentTagItems != null){
			for(DocumentTagItem tagItem : documentTagItems){
				if(UnitManagerReviewItemOperation
					.CREATE.equals(tagItem.getItemOperation())){
					this.unitManagerReviewService.createDocumentTag(
							tagItem.getName(), document);
				}
				if(UnitManagerReviewItemOperation
					.UPDATE.equals(tagItem.getItemOperation())){
					this.unitManagerReviewService.updateDocumentTag(
							tagItem.getDocumentTag(),
							tagItem.getName());
				}
				if(UnitManagerReviewItemOperation
					.REMOVE.equals(tagItem.getItemOperation())){
					this.unitManagerReviewService.removeDocumentTag(
							tagItem.getDocumentTag());
				}
			}
		}
	}
	
	/** 
	 * Retrieves document file.
	 * 
	 * @param document document
	 * @param httpServletResponse HTTP servlet response
	 */
	@RequestMapping(value = "retrieveFile.html", method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.unitManagerReviewDocumentRetriever
				.retrieve(document);
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
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(UnitManagerReview.class,
				this.unitManagerReviewPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(StaffAssignment.class,
				this.staffAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(UnitManagerReviewDocumentAssociation.class, 
				this.unitManagerReviewDocumentAssociationPropertyEditorFactory.
					createPropertyEditor());
			binder.registerCustomEditor(Document.class, 
					this.documentPropertyEditorFactory.createPropertyEditor());
			binder.registerCustomEditor(DocumentTag.class, 
					this.documentTagPropertyEditorFactory.createPropertyEditor());
			binder.registerCustomEditor(byte[].class,
					new ByteArrayMultipartFileEditor());
	}
}
