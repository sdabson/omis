package omis.victim.web.controller;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
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
import omis.person.domain.Person;
import omis.victim.domain.VictimDocumentAssociation;
import omis.victim.service.VictimDocumentAssociationService;
import omis.victim.web.controller.delegate.VictimSummaryModelDelegate;
import omis.victim.web.form.VictimDocumentItem;
import omis.victim.web.form.VictimDocumentItemOperation;
import omis.victim.web.form.VictimDocumentsForm;
import omis.victim.web.validator.VictimDocumentsFormValidator;

/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Victim document controller
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/victim/document")
public class VictimDocumentController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "victim/document/edit";
	private static final String VICTIM_DOCUMENTS_ACTION_MENU_VIEW_NAME
	= "victim/document/includes/victimDocumentsActionMenu";
	private static final String DOCUMENT_TAG_ITEM_VIEW_NAME = "victim/document/includes/documentTagItem";
	private static final String VICTIM_DOCUMENT_ITEM_VIEW_NAME = "victim/document/includes/victimDocumentItem";
	
	/* Redirects. */
	
	private static final String VICTIM_PROFILE_VIEW_REDIRECT_URL_FORMAT
	= "redirect:../profile.html?victim=%d";
	
	/* Model keys. */
	
	private static final String VICTIM_MODEL_KEY = "victim";
	private static final String DOCKET_MODEL_KEY = "docket";
	private static final String FORM_MODEL_KEY = "victimDocumentsForm";
	private static final String VICTIM_DOCUMENT_ITEM_INDEX_MODEL_KEY = "victimDocumentItemIndex";
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY = "documentTagItemIndex";
	private static final String VICTIM_DOCUMENT_ITEM_MODEL_KEY = "victimDocumentItem";
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY = "documentTagItemIndexes";
	private static final String DOCKETS_MODEL_KEY = "dockets";
	
	/* Services. */
	
	@Autowired
	@Qualifier("victimDocumentAssociationService")
	private VictimDocumentAssociationService victimDocumentService;
	
	/* Validator. */
	
	@Autowired
	@Qualifier("victimDocumentsFormValidator")
	private VictimDocumentsFormValidator victimDocumentsFormValidator;
	
	/* Property editors */
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("docketPropertyEditorFactory")
	private PropertyEditorFactory docketPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	@Autowired
	@Qualifier("victimDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory victimDocumentAssociationPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("victimSummaryModelDelegate")
	private VictimSummaryModelDelegate victimSummaryModelDelegate;

	@Autowired
	@Qualifier("victimDocumentPersister")
	private DocumentPersister victimDocumentPersister;
	
	@Autowired
	@Qualifier("victimDocumentRetriever")
	private DocumentRetriever victimDocumentRetriever;
	
	@Autowired
	@Qualifier("victimDocumentRemover")
	private FileRemover victimDocumentRemover;
	
	@Autowired
	@Qualifier("documentFilenameGenerator")
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of victim document controller.
	 */
	public VictimDocumentController() {
		//Default constructor
	}
	
	/* URL mapped methods. */
	
	/**
	 * Presents a view, with appropriate model attributes, for editing victim documents.
	 * 
	 * @param victim person
	 * @param docket docket
	 * @return model and view for editing victim documents
	 */
	@RequestContentMapping(nameKey = "victimDocumentsFormScreenName",
			descriptionKey = "victimDocumentsFormDescription",
			messageBundle = "omis.victim.msgs.victim",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_DOCUMENT_VIEW')")
	public ModelAndView edit(@RequestParam(value = "victim", required = true)
		final Person victim,
		@RequestParam(value = "docket", required = false) final Docket docket) {
		ModelMap map = new ModelMap();
		VictimDocumentsForm form = new VictimDocumentsForm();
		final List<VictimDocumentAssociation> associations;
		if (docket != null) {
			associations = this.victimDocumentService.findByDocketAndVictim(docket, victim);
			map.addAttribute(DOCKET_MODEL_KEY, docket);
		} else {
			associations = this.victimDocumentService.findByVictim(victim);
		}
		List<VictimDocumentItem> items = new ArrayList<VictimDocumentItem>();
		for (VictimDocumentAssociation assoc : associations) {
			List<DocumentTag> tags = this.victimDocumentService.findTagsByDocument(
					assoc.getDocument());
			List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
			for (DocumentTag tag : tags) {
				DocumentTagItem item = new DocumentTagItem(tag.getName(), DocumentTagOperation.UPDATE, tag);
				tagItems.add(item);
			}
			VictimDocumentItem item = new VictimDocumentItem(assoc,
					assoc.getDocument().getTitle(), assoc.getDocument().getDate(),
					assoc.getDocket(), tagItems, VictimDocumentItemOperation.UPDATE);
			items.add(item);
		}
		form.setDocumentItems(items);
		map.addAttribute(FORM_MODEL_KEY, form);
		return this.prepareEditMav(form, victim, map);
	}
	
	/**
	 * Saves victim documents and redirects to the victim profile.
	 * 
	 * @param victim person
	 * @return model and view to redirect to victim profile
	 * @throws DuplicateEntityFoundException thrown when a duplicate victim document association, or document is found 
	 */
	@RequestMapping(value = "/edit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_DOCUMENT_EDIT')")
	public ModelAndView update(@RequestParam(value = "victim", required = true) final Person victim,
			final VictimDocumentsForm form, final BindingResult result)
		throws DuplicateEntityFoundException {
		this.victimDocumentsFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(FORM_MODEL_KEY, form);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ FORM_MODEL_KEY, result);
			return this.prepareEditMav(form, victim, map);
		}
		this.processVictimDocumentItems(form.getDocumentItems(), victim);
		return new ModelAndView(String.format(VICTIM_PROFILE_VIEW_REDIRECT_URL_FORMAT, victim.getId()));
	}
	
	/**
	 * Displays the victim documents action menu.
	 * 
	 * @param victim victim
	 * @param docket docket
	 * @return model and view to display victim documents action menu
	 */
	@RequestMapping(value = "/displayVictimDocumentsActionMenu.html", method = RequestMethod.GET)
	public ModelAndView displayVictimDocumentsActionMenu(@RequestParam(value = "victim", required = true)
			final Person victim,
			@RequestParam(value = "docket", required = false)
			final Docket docket) {
		ModelMap map = new ModelMap();
		map.addAttribute(DOCKET_MODEL_KEY, docket);
		map.addAttribute(VICTIM_MODEL_KEY, victim);
		return new ModelAndView(VICTIM_DOCUMENTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays a document tag item.
	 * 
	 * @param financialDocumentItemIndex financial document item index 
	 * @param documentTagItemIndex  document tag item index
	 * @return ModelAndView model and view for creating a document tag
	 */
	@RequestMapping(value = "/createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "victimDocumentItemIndex",
				required = true) final Integer victimDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setDocumentTagOperation(DocumentTagOperation.CREATE);
		map.addAttribute(VICTIM_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				victimDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,	
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * displays a new document tag item for creation.
	 * 
	 * @param victimDocumentItemIndex victim document item index
	 * @param documentTagItemIndex document tag item index
	 * @return model and view for new document tag item
	 */
	@RequestMapping(value = "/createDocumentItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentItem
		(@RequestParam(value = "victimDocumentItemIndex", required = true)
				final Integer victimDocumentItemIndex,
				@RequestParam(value = "victim", required = true)
				final Person victim,
				@RequestParam(value = "docket", required = false)final Docket docket) {
		ModelMap map = new ModelMap();
		VictimDocumentItem item = new VictimDocumentItem();
		item.setOperation(VictimDocumentItemOperation.CREATE);
		map.addAttribute(VICTIM_DOCUMENT_ITEM_MODEL_KEY, item);
		map.addAttribute(VICTIM_DOCUMENT_ITEM_INDEX_MODEL_KEY, victimDocumentItemIndex);
		if (docket != null) {
			map.addAttribute(DOCKET_MODEL_KEY, docket);
		}
		map.addAttribute(DOCKETS_MODEL_KEY, this.victimDocumentService
				.findDocketsByVictim(victim));
		return new ModelAndView(VICTIM_DOCUMENT_ITEM_VIEW_NAME, map);
	}
	
	/** Retrieves document file.
	 * @param document - document
	 * @param httpServletResponse - HTTP Servlet response. */
	@RequestMapping(value = "/retrieveFile.html", 
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.victimDocumentRetriever.retrieve(document);
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setHeader("Content-Disposition", 
				"attachment; filename=\"" + document.getFilename() + "\"");
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		} catch (IOException ioException) {
			throw new RuntimeException("Unable to write file to disk.");
		}
	}
	
	/* Helper methods. */
	
	/*
	 * Prepares the edit model and view for display.
	 * 
	 * @param form victim documents form
	 * @param victim person
	 * @param map model map
	 * @return model and view for editing victim documents
	 */
	private ModelAndView prepareEditMav(final VictimDocumentsForm form, final Person victim,
			final ModelMap map) {
		this.victimSummaryModelDelegate.add(map, victim);
		map.addAttribute(VICTIM_DOCUMENT_ITEM_INDEX_MODEL_KEY, form.getDocumentItems().size());
		
		int documentItemIndex = 0;
		List<Integer> tagItemIndexes = new ArrayList<Integer>();
		tagItemIndexes.add(0,0);
		for (VictimDocumentItem item : form.getDocumentItems()) {
			final int tagItemIndex;
			if (item.getTagItems() != null) {
				tagItemIndex = item.getTagItems().size();
			} else {
				tagItemIndex = 0;
			}
			tagItemIndexes.add(documentItemIndex, tagItemIndex);
			documentItemIndex++;
		}
		map.addAttribute(DOCKETS_MODEL_KEY, this.victimDocumentService
				.findDocketsByVictim(victim));
		map.addAttribute(VICTIM_MODEL_KEY, victim);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, tagItemIndexes);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Processes the specified list of victim document items according to their option value
	 * 
	 * @param items victim document items
	 * @throws UnsupportedOperationException thrown when an operation other than CREATE, UPDATE, or REMOVE is specified
	 * @throws DuplicateEntityFoundException thrown when a duplicate document, document tag, or victim document
	 * association is found.
	 */
	private void processVictimDocumentItems(final List<VictimDocumentItem> items, final Person victim)
		throws UnsupportedOperationException, DuplicateEntityFoundException {
		if (items != null) {
			for (VictimDocumentItem item : items) {
				if (VictimDocumentItemOperation.CREATE.equals(item.getOperation())) {
					final String extension = item.getFileExtension();
						this.documentFilenameGenerator.setExtension(extension);
					final String filename = this.documentFilenameGenerator.generate();
					Document doc = this.victimDocumentService.createDocument(item.getDate(),
							filename, extension, item.getTitle());
					this.victimDocumentPersister.persist(doc, item.getDocumentData());
					this.processDocumentTagItems(item.getTagItems(), doc);
					this.victimDocumentService.create(victim, doc, item.getDocket());
				} else if (VictimDocumentItemOperation.UPDATE.equals(item.getOperation())) {
					this.victimDocumentService.update(item.getAssociation(), item.getTitle(), item.getDate(),
							item.getDocket());
					this.processDocumentTagItems(item.getTagItems(), item.getAssociation().getDocument());
				} else if (VictimDocumentItemOperation.REMOVE.equals(item.getOperation())) {
					for (DocumentTagItem tagItem : item.getTagItems()) {
						if (tagItem.getDocumentTag() != null) {
							this.victimDocumentService.removeDocumentTag(tagItem.getDocumentTag());
						}
					}
					Document doc = item.getAssociation().getDocument();
					this.victimDocumentService.remove(item.getAssociation());
					this.victimDocumentRemover.remove(doc.getFilename());
					this.victimDocumentService.removeDocument(doc);
				}
			}
		}
	}
	
	/*
	 * Processes document tag items according to their specified operation.
	 * 
	 * @param items document tag items
	 * @param document document
	 * @throws DuplicateEntityFoundException thrown when a duplicate document tag item is found
	 */
	private void processDocumentTagItems(final List<DocumentTagItem> items, final Document document)
			throws DuplicateEntityFoundException {
		for (DocumentTagItem tagItem : items) {
			if (DocumentTagOperation.CREATE.equals(tagItem.getDocumentTagOperation())) {
				this.victimDocumentService.createDocumentTag(document, tagItem.getName());
			} else  if (DocumentTagOperation.UPDATE.equals(tagItem.getDocumentTagOperation())) {
				this.victimDocumentService.updateDocumentTag(tagItem.getDocumentTag(), tagItem.getName());
			} else if (DocumentTagOperation.REMOVE.equals(tagItem.getDocumentTagOperation())) {
				this.victimDocumentService.removeDocumentTag(tagItem.getDocumentTag());
			}
		}
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Docket.class,
				this.docketPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Document.class,
				this.documentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				DocumentTag.class,
				this.documentTagPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				VictimDocumentAssociation.class,
				this.victimDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
