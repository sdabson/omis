package omis.sentence.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.service.SentenceService;
import omis.sentence.validator.SentenceFormValidator;
import omis.sentence.web.form.SentenceForm;

/**
 * Sentence controller.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Aug 14, 2013)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/sentence")
@PreAuthorize("hasRole('USER')")
public class SentenceController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "sentence/edit";
	
	private static final String CALCULATION_VIEW_NAME
		= "sentence/includes/calculation";

	/* Model keys. */
	
	private static final String SENTENCE_FORM_MODEL_KEY = "sentenceForm";

	private static final String CONVICTION_MODEL_KEY = "conviction";
	
	/* Property names. */
	
//	private static final String PRISON_TERM_YEARS_PROPERTY_NAME
//		= "prisonTermYears";
//	
//	private static final String PRISON_TERM_MONTHS_PROPERTY_NAME
//		= "prisonTermMonths";
//	
//	private static final String PRISON_TERM_DAYS_PROPERTY_NAME
//		= "prisonTermDays";
//
//	private static final String PRISON_DISCHARGE_DATE_PROPERTY_NAME
//		= "prisonDischargeDate";
//
//	private static final String PROBATION_TERM_YEARS_PROPERTY_NAME
//		= "probationTermYears";
//
//	private static final String PROBATION_TERM_MONTHS_PROPERTY_NAME
//		= "probationTermMonths";
//	
//	private static final String PROBATION_TERM_DAYS_PROPERTY_NAME
//		= "probationTermDays";
//	
//	private static final String PROBATION_DISCHARGE_DATE_PROPERTY_NAME
//		= "probationDischargeDate";
//	
	/* Services. */
	
	@Autowired
	@Qualifier("sentenceService")
	private SentenceService sentenceService;
		
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("convictionPropertyEditorFactory")
	private PropertyEditorFactory convictionPropertyEditorFactory;

	@Autowired
	@Qualifier("sentenceCategoryPropertyEditorFactory")
	private PropertyEditorFactory sentenceCategoryPropertyEditorFactory;

	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("sentenceFormValidator")
	private SentenceFormValidator sentenceFormValidator;
	
	/* Constructor. */
	
	/** Instantiates a default sentence controller. */
	public SentenceController() {
		// Default instantiation
	}
	
	/* Screens. */
	
	/**
	 * Displays a form to enter sentence details.
	 * 
	 * @param conviction conviction
	 * @return model and view for sentence detail form
	 */
	@PreAuthorize("hasRole('SENTENCE_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/createOrEdit.html", method = RequestMethod.GET)
	public ModelAndView createOrEdit(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction) {
		SentenceForm sentenceForm = new SentenceForm();
//		Sentence sentence = this.sentenceService.findByConviction(conviction);
//		if (sentence != null) {
//			sentenceForm.setConcurrent(sentence.getConcurrent());
//			if (sentence.getPrisonTerm() != null) {
//				sentenceForm.setPrisonTermYears(
//						sentence.getPrisonTerm().getYears());
//				sentenceForm.setPrisonTermMonths(
//						sentence.getPrisonTerm().getMonths());
//				sentenceForm.setPrisonTermDays(
//						sentence.getPrisonTerm().getDays());
//			}
//			sentenceForm.setPrisonDischargeDate(
//					sentence.getPrisonDischargeDate());
//			if (sentence.getProbationTerm() != null) {
//				sentenceForm.setProbationTermYears(
//						sentence.getProbationTerm().getYears());
//				sentenceForm.setProbationTermMonths(
//						sentence.getProbationTerm().getMonths());
//				sentenceForm.setProbationTermDays(
//						sentence.getProbationTerm().getDays());
//			}
//			sentenceForm.setProbationDischargeDate(
//					sentence.getProbationDischargeDate());
//			if (sentence.getDeferredTerm() != null) {
//				sentenceForm.setDeferredTermDays(
//						sentence.getDeferredTerm().getDays());
//				sentenceForm.setDeferredTermMonths(
//						sentence.getDeferredTerm().getMonths());
//				sentenceForm.setDeferredTermYears(
//						sentence.getDeferredTerm().getYears());
//			}
//			if (sentence.getCredit() != null) {
//				sentenceForm.setJailTimeCredit(
//						sentence.getCredit().getJailTime());
//				sentenceForm.setStreetTimeCredit(
//						sentence.getCredit().getStreetTime());
//			}
//			sentenceForm.setCategory(sentence.getCategory());
//			sentenceForm.setLengthClassification(
//					sentence.getLengthClassification());
//			sentenceForm.setEffectiveDate(sentence.getEffectiveDate());
//			sentenceForm.setPronouncementDate(sentence.getPronouncementDate());
//			sentenceForm.setTurnSelfInDate(sentence.getTurnSelfInDate());
//		}
		return prepareEditMav(sentenceForm, conviction);
	}
	
	// Prepares the edit model and view
	private ModelAndView prepareEditMav(final SentenceForm sentenceForm,
			final Conviction conviction) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(SENTENCE_FORM_MODEL_KEY, sentenceForm);
		mav.addObject(CONVICTION_MODEL_KEY, conviction);
		return mav;
	}
	
	/**
	 * Creates a new sentence or updates an existing one.
	 * 
	 * @param conviction conviction to sentence
	 * @param result binding result
	 * @param sentenceForm sentence form
	 * @return redirect to court case listing page
	 */
	@RequestMapping(value = "/createOrEdit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('SENTENCE_CREATE') or hasRole('ADMIN')")
	public ModelAndView saveOrUpdate(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction,
			final SentenceForm sentenceForm,
			final BindingResult result) {
		this.sentenceFormValidator.validate(sentenceForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(sentenceForm, conviction, result);
		}
//		Term prisonTerm = new Term(
//				sentenceForm.getPrisonTermYears(),
//				sentenceForm.getPrisonTermMonths(),
//				sentenceForm.getPrisonTermDays());
//		Term probationTerm = new Term(
//				sentenceForm.getProbationTermYears(),
//				sentenceForm.getProbationTermMonths(),
//				sentenceForm.getProbationTermDays());
//		Term deferredTerm = new Term(
//				sentenceForm.getDeferredTermYears(),
//				sentenceForm.getDeferredTermMonths(),
//				sentenceForm.getDeferredTermDays());
		ConvictionCredit credit = new ConvictionCredit();
		credit.setJailTime(sentenceForm.getJailTimeCredit());
		credit.setStreetTime(sentenceForm.getStreetTimeCredit());
//		if (sentenceForm.getConcurrent() != null
//				&& sentenceForm.getConcurrent()) {
//			this.sentenceService.sentenceConcurrently(conviction,
//					sentenceForm.getPrisonDischargeDate(), prisonTerm,
//					sentenceForm.getProbationDischargeDate(), probationTerm,
//					deferredTerm,
//					sentenceForm.getCategory(),
//					sentenceForm.getLengthClassification(),
//					sentenceForm.getLegalDispositionCategory(),
//					sentenceForm.getEffectiveDate(), 
//					sentenceForm.getPronouncementDate(), credit, 
//					sentenceForm.getTurnSelfInDate());
//		} else {
//			// TODO: What should value of consecutive be? - SA
//			this.sentenceService.sentenceConsecutively(conviction,
//					sentenceForm.getPrisonDischargeDate(), prisonTerm,
//					sentenceForm.getProbationDischargeDate(), probationTerm,
//					deferredTerm,
//					sentenceForm.getCategory(),
//					sentenceForm.getLengthClassification(),
//					sentenceForm.getLegalDispositionCategory(),
//					sentenceForm.getEffectiveDate(), 
//					sentenceForm.getPronouncementDate(), credit, 
//					sentenceForm.getTurnSelfInDate(), null);
//		}
		return new ModelAndView(
				"redirect:/courtCase/list.html?defendant="
					+ conviction.getCourtCase().getDocket().getPerson().getId());
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplayMav(
			final SentenceForm sentenceForm,
			final Conviction conviction,
			final BindingResult bindingResult) {
		ModelAndView mav = this.prepareEditMav(sentenceForm, conviction);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ SENTENCE_FORM_MODEL_KEY, bindingResult);
		return mav;
	}
	
	/* Partial screens. */
	
	/**
	 * Returns sentence calculation.
	 * 
	 * @param conviction conviction
	 * @param concurrent whether sentence is to be served concurrently
	 * @return sentence calculation
	 */
	@RequestMapping(value = "/showCalculation.html", method = RequestMethod.GET)
	public ModelAndView showCalculation(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction,
			@RequestParam(value = "commencementDate", required = true)
				final Date commencementDate,
			@RequestParam(value = "concurrent", required = true)
				final Boolean concurrent) {
//		Term prisonTerm = this.sentenceService.calculatePrisonTerm(conviction);
//		Date prisonDischargeDate = this.sentenceService
//				.calculatePrisonDischargeDate(commencementDate, prisonTerm);
//		Term probationTerm = this.sentenceService
//				.calculateProbationTerm(conviction);
//		Date probationDischargeDate = this.sentenceService
//				.calculateProbationDischargeDate(prisonDischargeDate,
//						probationTerm);
		Map<String, Object> sentenceForm = new HashMap<String, Object>();
//		sentenceForm.put(PRISON_TERM_YEARS_PROPERTY_NAME,
//				prisonTerm.getYears());
//		sentenceForm.put(PRISON_TERM_MONTHS_PROPERTY_NAME,
//				prisonTerm.getMonths());
//		sentenceForm.put(PRISON_TERM_DAYS_PROPERTY_NAME,
//				prisonTerm.getDays());
//		sentenceForm.put(PRISON_DISCHARGE_DATE_PROPERTY_NAME,
//				prisonDischargeDate);
//		sentenceForm.put(PROBATION_TERM_YEARS_PROPERTY_NAME,
//				probationTerm.getYears());
//		sentenceForm.put(PROBATION_TERM_MONTHS_PROPERTY_NAME,
//				probationTerm.getMonths());
//		sentenceForm.put(PROBATION_TERM_DAYS_PROPERTY_NAME,
//				probationTerm.getDays());
//		sentenceForm.put(PROBATION_DISCHARGE_DATE_PROPERTY_NAME,
//				probationDischargeDate);
		ModelAndView mav = new ModelAndView(CALCULATION_VIEW_NAME);
		mav.addObject(SENTENCE_FORM_MODEL_KEY, sentenceForm);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Initializes and binds property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Conviction.class,
				this.convictionPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(false));
		binder.registerCustomEditor(SentenceCategory.class,
				this.sentenceCategoryPropertyEditorFactory
				.createPropertyEditor());
	}
}