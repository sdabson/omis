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
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummary;
import omis.presentenceinvestigation.domain.EvaluationRecommendationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.EvaluationRecommendationSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.EvaluationRecommendationSectionSummaryForm;
import omis.presentenceinvestigation.web.form.EvaluationRecommendationSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.EvaluationRecommendationSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;
/**
 * Evaluation Recommendation Section Summary Controller
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 12, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/evaluationRecommendationSummary/")
@PreAuthorize("hasRole('USER')")
public class EvaluationRecommendationSectionSummaryController {
/* View Names */
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/evaluationRecommendationSummary/edit";
	
	private static final String
		EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
			"/presentenceInvestigation/evaluationRecommendationSummary/includes/"
			+ "evaluationRecommendationSectionSummaryNoteTableRow";
	
	private static final String
		EVALUATION_RECOMMENDATION_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/evaluationRecommendationSummary/includes/"
			+ "evaluationRecommendationSectionSummaryActionMenu";
	
	private static final String
	EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/evaluationRecommendationSummary/includes/"
			+ "evaluationRecommendationSectionSummaryNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String
		EVALUATION_RECOMMENDATION_SECTION_SUMMARY_MODEL_KEY =
			"evaluationRecommendationSectionSummary";
	
	private static final String
		EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY =
			"evaluationRecommendationSectionSummaryNoteItem";
	
	private static final String
		EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
			"evaluationRecommendationSectionSummaryNoteItemIndex";
	
	private static final String FORM_MODEL_KEY =
			"evaluationRecommendationSectionSummaryForm";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"evaluationRecommendationSectionSummaryEntity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("evaluationRecommendationSectionSummaryService")
	private EvaluationRecommendationSectionSummaryService
		evaluationRecommendationSectionSummaryService;
	

	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("evaluationRecommendationSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory
		evaluationRecommendationSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("evaluationRecommendationSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
		evaluationRecommendationSectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	PropertyEditorFactory presentenceInvestigationRequestPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSummaryModelDelegate")
	private PresentenceInvestigationRequestSummaryModelDelegate
			presentenceInvestigationRequestSummaryModelDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("evaluationRecommendationSectionSummaryFormValidator")
	private EvaluationRecommendationSectionSummaryFormValidator
		evaluationRecommendationSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for EvaluationRecommendationSectionSummaryController
	 */
	public EvaluationRecommendationSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a
	 * Evaluation Recommendation Section Summary
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * @return ModelAndView for editing a Evaluation Recommendation Section Summary
	 * @throws DuplicateEntityFoundException - When a
	 * Evaluation Recommendation Section Summary already exists for the
	 * presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EVALUATION_RECOMMENDATION_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('EVALUATION_RECOMMENDATION_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
						throws DuplicateEntityFoundException{
		return this.prepareEditMav(presentenceInvestigationRequest,
				this.prepareForm(presentenceInvestigationRequest));
	}
	
	/**
	 * Saves an Evaluation Recommendation Section Summary and Notes and returns to the
	 * Presentence Investigation Request Home screen
	 * @param presentenceInvestigationRequest - Presentence InvestigationR equest
	 * @param form - Evaluation Recommendation Section Summary Form
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to Presentence Investigation Request Home 
	 * on successful save, or back to Evaluation Recommendation Section Summary
	 * on form error
	 * @throws DuplicateEntityFoundException - When a
	 * Evaluation Recommendation Section Summary Note already exists for the
	 * Evaluation Recommendation Section Summary with provided description and date
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('EVALUATION_RECOMMENDATION_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final EvaluationRecommendationSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.evaluationRecommendationSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			
			EvaluationRecommendationSectionSummary
				evaluationRecommendationSectionSummary =
				this.evaluationRecommendationSectionSummaryService
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
			
			if(evaluationRecommendationSectionSummary == null){
				evaluationRecommendationSectionSummary =
						this.evaluationRecommendationSectionSummaryService
						.createEvaluationRecommendationSectionSummary(
								form.getDescription(),
								presentenceInvestigationRequest);
			}
			else{
				evaluationRecommendationSectionSummary = 
						this.evaluationRecommendationSectionSummaryService
						.updateEvaluationRecommendationSectionSummary(
								evaluationRecommendationSectionSummary,
								form.getDescription());
			}
			
			if(form.getEvaluationRecommendationSectionSummaryNoteItems() != null){
				for(EvaluationRecommendationSectionSummaryNoteItem item :
					form.getEvaluationRecommendationSectionSummaryNoteItems()){
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						this.evaluationRecommendationSectionSummaryService
							.createEvaluationRecommendationSectionSummaryNote(
									evaluationRecommendationSectionSummary,
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.UPDATE
							.equals(item.getItemOperation())){
						if(this.isNoteChanged(item
								.getEvaluationRecommendationSectionSummaryNote(),
								item.getDate(), item.getDescription())) {
							this.evaluationRecommendationSectionSummaryService
								.updateEvaluationRecommendationSectionSummaryNote(
									item.getEvaluationRecommendationSectionSummaryNote(),
									item.getDescription(), item.getDate());
						}
					}
					else if(PresentenceInvestigationItemOperation.REMOVE
							.equals(item.getItemOperation())){
						this.evaluationRecommendationSectionSummaryService
							.removeEvaluationRecommendationSectionSummaryNote(
							item.getEvaluationRecommendationSectionSummaryNote());
					}
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a Evaluation Recommendation Section Summary Note item row
	 * @param evaluationRecommendationSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - Evaluation Recommendation Section Summary Note item row
	 */
	@RequestMapping(value =
			"createEvaluationRecommendationSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayEvaluationRecommendationSectionSummaryNoteItem(
			@RequestParam(
			value = "evaluationRecommendationSectionSummaryNoteItemIndex",
			required = true)
				final Integer
				evaluationRecommendationSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		EvaluationRecommendationSectionSummaryNoteItem noteItem =
				new EvaluationRecommendationSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(
				EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
			EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				evaluationRecommendationSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
			EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME,
			map);
	}
	
	
	/* Action Menus */
	
	
	/**
	 * Displays the Evaluation Recommendation Section Summary Action Menu
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * @return ModelAndView - Evaluation Recommendation Section Summary Action Menu
	 */
	@RequestMapping(value="/evaluationRecommendationSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEvaluationRecommendationSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				EVALUATION_RECOMMENDATION_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the Evaluation Recommendation Section Summary Note Items Action Menu
	 * @return ModelAndView - Evaluation Recommendation Section Summary Note Items
	 * Action Menu
	 */
	@RequestMapping(value=
			"/evaluationRecommendationSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView
		displayEvaluationRecommendationSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
		EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	/* Helper Methods */
	
	
	/**
	 * Prepares a ModelAndView for editing a Evaluation Recommendation Section Summary
	 * @param presentenceInvestigationRequest - Presentence Investigation Request
	 * from which the associated Evaluation Recommendation Summary will be found
	 * for editing
	 * @param form - Evaluation Recommendation Section Summary Form
	 * @return ModelAndView for editing a Evaluation Recommendation Section Summary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final EvaluationRecommendationSectionSummaryForm form){
		
		ModelMap map = new ModelMap();
		
		EvaluationRecommendationSectionSummary
			evaluationRecommendationSectionSummary =
				this.evaluationRecommendationSectionSummaryService
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		map.addAttribute(EVALUATION_RECOMMENDATION_SECTION_SUMMARY_MODEL_KEY,
				evaluationRecommendationSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
			EVALUATION_RECOMMENDATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
			form.getEvaluationRecommendationSectionSummaryNoteItems().size());
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		this.offenderSummaryModelDelegate.add(map, (Offender)
				presentenceInvestigationRequest.getDocket().getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				presentenceInvestigationRequest);
				
				
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a EvaluationRecommendationSectionSummaryForm from a 
	 * EvaluationRecommendationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated EvaluationRecommendationSummary will be found
	 * @return populated EvaluationRecommendationSectionSummaryForm
	 */
	private EvaluationRecommendationSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		
		EvaluationRecommendationSectionSummaryForm form =
				new EvaluationRecommendationSectionSummaryForm();
		
		EvaluationRecommendationSectionSummary
			evaluationRecommendationSectionSummary =
				this.evaluationRecommendationSectionSummaryService
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(evaluationRecommendationSectionSummary != null){
			form.setDescription(evaluationRecommendationSectionSummary
					.getText());
			
			List<EvaluationRecommendationSectionSummaryNote>
				evaluationRecommendationSectionSummaryNotes =
				this.evaluationRecommendationSectionSummaryService
				.findSectionSummaryNotesByEvaluationRecommendationSectionSummary(
						evaluationRecommendationSectionSummary);
			
			List<EvaluationRecommendationSectionSummaryNoteItem> noteItems =
				new ArrayList<EvaluationRecommendationSectionSummaryNoteItem>();
			
			for(EvaluationRecommendationSectionSummaryNote note :
						evaluationRecommendationSectionSummaryNotes){
				EvaluationRecommendationSectionSummaryNoteItem item =
						new EvaluationRecommendationSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setEvaluationRecommendationSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				noteItems.add(item);
			}
			form.setEvaluationRecommendationSectionSummaryNoteItems(noteItems);
		}
		
		return form;
	}
	
	/**
	 * Checks if a Evaluation Recommendation Section Summary Note has been
	 * changed and returns true if it has
	 * @param note - Evaluation Recommendation Section Summary Note to check for
	 * change
	 * @param date - Note Date from Form
	 * @param value - Note Value from Form
	 * @return Boolean - true if either the form's note date or value is different
	 * from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(
			final EvaluationRecommendationSectionSummaryNote note,
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
		binder.registerCustomEditor(EvaluationRecommendationSectionSummary.class, 
				this.evaluationRecommendationSectionSummaryPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(EvaluationRecommendationSectionSummaryNote
				.class, 
			this.evaluationRecommendationSectionSummaryNotePropertyEditorFactory.
				createPropertyEditor());
	}
}
