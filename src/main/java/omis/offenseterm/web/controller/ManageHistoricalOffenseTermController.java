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
package omis.offenseterm.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
import omis.contact.web.controller.delegate.ContactSummaryModelDelegate;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offenseterm.service.HistoricalOffenseTermService;
import omis.offenseterm.web.form.HistoricalOffenseTermForm;
import omis.offenseterm.web.validator.HistoricalOffenseTermFormValidator;
import omis.person.domain.Person;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.TermRequirement;
import omis.sentence.exception.SentenceExistsException;
import omis.term.domain.component.Term;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/** 
 * Controller to manage historical offense terms.
 * 
 * <p>Historical offense terms are represented by inactive sentences. 
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/offenseTerm")
public class ManageHistoricalOffenseTermController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "offenseTerm/editHistoricalOffenseTerm";
	
	/* Redirects. */
	
	private static final String REDIRECT = "redirect:/offenseTerm"
			+ "/listHistoricalOffenseTerms.html?conviction=%d";

	/* Action menu views. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "offenseTerm/includes/historicalOffenseTermActionMenu";
	
	/* Partial view names. */
	
	private static final String SENTENCE_CATEGORY_VIEW_NAME
		= "offenseTerm/json/sentenceCategory";
	
	private static final String INTEGER_VALUE_VIEW_NAME
		= "common/json/integerValue";
	
	private static final String DATE_VALUE_VIEW_NAME = "common/json/dateValue";
	
	private static final String SENTENCE_TERMS_VIEW_NAME
		= "sentence/includes/sentenceTerms";
	
	/* Model keys. */
	
	private static final String HISTORICAL_OFFENSE_TERM_FORM_MODEL_KEY
		= "historicalOffenseTermForm";

	private static final String CONVICTION_MODEL_KEY = "conviction";
	
	private static final String SENTENCE_CATEGORIES_MODEL_KEY
		= "sentenceCategories";

	private static final String LEGAL_DISPOSITION_CATEGORIES_MODEL_KEY
		= "legalDispositionCategories";

	private static final String SENTENCE_LENGTH_CLASSIFICATIONS_MODEL_KEY
		= "sentenceLengthClassifications";

	private static final String SENTENCE_CATEGORY_MODEL_KEY
		= "sentenceCategory";
	
	private static final String INTEGER_VALUE_MODEL_KEY = "integerValue";
	
	private static final String DATE_VALUE_MODEL_KEY = "dateValue";
	
	private static final String PRISON_YEARS_MODEL_KEY = "prisonYears";

	private static final String PRISON_MONTHS_MODEL_KEY = "prisonMonths";

	private static final String PRISON_DAYS_MODEL_KEY = "prisonDays";

	private static final String PROBATION_YEARS_MODEL_KEY = "probationYears";

	private static final String PROBATION_MONTHS_MODEL_KEY = "probationMonths";

	private static final String PROBATION_DAYS_MODEL_KEY = "probationDays";

	private static final String DEFERRED_YEARS_MODEL_KEY = "deferredTermYears";

	private static final String DEFERRED_MONTHS_MODEL_KEY
		= "deferredTermMonths";

	private static final String DEFERRED_DAYS_MODEL_KEY	= "deferredTermDays";

	private static final String SHOW_PRISON_TERM_MODEL_KEY = "showPrisonTerm";

	private static final String SHOW_PROBATION_TERM_MODEL_KEY
		= "showProbationTerm";

	private static final String SHOW_DEFERRED_TERM_MODEL_KEY
		= "showDeferredTerm";

	private static final String SENTENCE_MODEL_KEY = "sentence";
	
	/* Message keys. */
	
	private static final String SENTENCE_EXISTS_MESSAGE_KEY 
		= "offenseTermForm.sentence.exists";

	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME = "omis.offenseterm.msgs.form";
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("sentencePropertyEditorFactory")
	private PropertyEditorFactory sentencePropertyEditorFactory;
	
	@Autowired
	@Qualifier("convictionPropertyEditorFactory")
	private PropertyEditorFactory convictionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sentenceCategoryPropertyEditorFactory")
	private PropertyEditorFactory sentenceCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("legalDispositionCategoryPropertyEditorFactory")
	private PropertyEditorFactory legalDispositionCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("historicalOffenseTermService")
	private HistoricalOffenseTermService historicalOffenseTermService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("contactSummaryModelDelegate")
	private ContactSummaryModelDelegate contactSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("historicalOffenseTermFormValidator")
	private HistoricalOffenseTermFormValidator
	historicalOffenseTermFormValidator;
	
	/* Constructors. */
	
	/** Instantiates controller to manage historical offense terms. */
	public ManageHistoricalOffenseTermController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create historical offense term.
	 * 
	 * <p>Historical offense terms are represented as inactive sentences
	 * 
	 * @param conviction conviction
	 * @return screen to create historical offense term
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_CREATE') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/createHistoricalOffenseTerm.html",
			method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction) {
		HistoricalOffenseTermForm historicalOffenseTermForm
			= new HistoricalOffenseTermForm();
		ModelAndView mav = this.prepareEditMav(
				historicalOffenseTermForm, conviction);
		return mav;
	}
	
	/**
	 * Saves historical offense term.
	 * 
	 * <p>Historical offense terms are represented as inactive sentences.
	 * 
	 * @param conviction conviction
	 * @param historicalOffenseTermForm form for historical offense terms
	 * @param bindingResult binding result
	 * @return redirect to listing screen
	 * @throws SentenceExistsException 
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_CREATE') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/createHistoricalOffenseTerm.html",
			method = RequestMethod.POST)
	public ModelAndView save(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction,
			final HistoricalOffenseTermForm historicalOffenseTermForm,
			final BindingResult bindingResult)
					throws SentenceExistsException {
		this.historicalOffenseTermFormValidator.validate(
				historicalOffenseTermForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return this.prepareRedirectMav(conviction,
					historicalOffenseTermForm,
					bindingResult);
		}
		this.historicalOffenseTermService.create(conviction,
				historicalOffenseTermForm.getSentenceFields()
					.getCategory(),
				historicalOffenseTermForm.getSentenceFields()
					.getLengthClassification(),
				historicalOffenseTermForm.getSentenceFields()
					.getLegalDispositionCategory(),
				historicalOffenseTermForm.getSentenceFields()
					.getPronouncementDate(),
				new ConvictionCredit(
						historicalOffenseTermForm.getSentenceFields()
							.getJailTimeCredit(),
						historicalOffenseTermForm.getSentenceFields()
							.getStreetTimeCredit()),
				historicalOffenseTermForm.getSentenceFields()
					.getEffectiveDate(),
				historicalOffenseTermForm.getSentenceFields()
					.getTurnSelfInDate(),
				new Term(
						historicalOffenseTermForm.getSentenceFields()
							.getPrisonYears(),
						historicalOffenseTermForm.getSentenceFields()
							.getPrisonMonths(),
						historicalOffenseTermForm.getSentenceFields()
							.getPrisonDays()),
				new Term(
						historicalOffenseTermForm.getSentenceFields()
							.getProbationYears(),
						historicalOffenseTermForm.getSentenceFields()
							.getProbationMonths(),
						historicalOffenseTermForm.getSentenceFields()
							.getProbationDays()),
				new Term(
						historicalOffenseTermForm.getSentenceFields()
							.getDeferredYears(),
						historicalOffenseTermForm.getSentenceFields()
							.getDeferredMonths(),
						historicalOffenseTermForm.getSentenceFields()
							.getDeferredDays()));
		return new ModelAndView(
				String.format(REDIRECT, conviction.getId()));
	}

	/**
	 * Shows screen to edit historical offense term.
	 * 
	 * <p>Historical offense terms are represented as inactive sentences.
	 *  
	 * @param sentence sentence
	 * @return screen to edit historical offense term
	 * @throws DuplicateEntityFoundException if sentence exists
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_VIEW') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/editHistoricalOffenseTerm.html",
			method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "sentence", required = true)
				final Sentence sentence) {
		HistoricalOffenseTermForm form = new HistoricalOffenseTermForm();
		form.getSentenceFields().setCategory(sentence.getCategory());
		form.getSentenceFields().setLengthClassification(
				sentence.getLengthClassification());
		form.getSentenceFields().setLegalDispositionCategory(
				sentence.getLegalDispositionCategory());
		form.getSentenceFields().setPronouncementDate(
				sentence.getPronouncementDate());
		if (sentence.getCredit() != null) {
			form.getSentenceFields().setJailTimeCredit(
					sentence.getCredit().getJailTime());
			form.getSentenceFields().setStreetTimeCredit(
					sentence.getCredit().getStreetTime());
		}
		form.getSentenceFields().setEffectiveDate(sentence.getEffectiveDate());
		form.getSentenceFields().setTurnSelfInDate(
				sentence.getTurnSelfInDate());
		if (sentence.getPrisonTerm() != null) {
			form.getSentenceFields().setPrisonYears(
					sentence.getPrisonTerm().getYears());
			form.getSentenceFields().setPrisonMonths(
					sentence.getPrisonTerm().getMonths());
			form.getSentenceFields().setPrisonDays(
					sentence.getPrisonTerm().getDays());
			form.getSentenceFields().setPrisonTotalDays(
					this.historicalOffenseTermService
						.calculateTotalDays(sentence.getPrisonTerm()));
		}
		if (sentence.getProbationTerm() != null) {
			form.getSentenceFields().setProbationYears(
					sentence.getProbationTerm().getYears());
			form.getSentenceFields().setProbationMonths(
					sentence.getProbationTerm().getMonths());
			form.getSentenceFields().setProbationDays(
					sentence.getProbationTerm().getDays());
			form.getSentenceFields().setProbationTotalDays(
					this.historicalOffenseTermService
						.calculateTotalDays(
								sentence.getProbationTerm()));
		}
		if (sentence.getDeferredTerm() != null) {
			form.getSentenceFields().setDeferredYears(
					sentence.getDeferredTerm().getYears());
			form.getSentenceFields().setDeferredMonths(
					sentence.getDeferredTerm().getMonths());
			form.getSentenceFields().setDeferredDays(
					sentence.getDeferredTerm().getDays());
			form.getSentenceFields().setDeferredTotalDays(
					this.historicalOffenseTermService
						.calculateTotalDays(
								sentence.getDeferredTerm()));
		}
		ModelAndView mav = this.prepareEditMav(form, sentence.getConviction());
		mav.addObject(SENTENCE_MODEL_KEY, sentence);
		return mav;
	}
	
	/**
	 * Updates historical offense term.
	 * 
	 * <p>Historical offense terms are represented as inactive sentences.
	 * 
	 * @param sentence inactive sentence to update
	 * @param historicalOffenseTermForm form for historical offense terms
	 * @param bindingResult binding result
	 * @return redirect to listing screen
	 * @throws SentenceExistsException 
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_EDIT') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/editHistoricalOffenseTerm.html",
			method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "sentence", required = true)
				final Sentence sentence,
			final HistoricalOffenseTermForm historicalOffenseTermForm,
			final BindingResult bindingResult) throws SentenceExistsException {
		this.historicalOffenseTermFormValidator.validate(
				historicalOffenseTermForm, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedirectMav(sentence.getConviction(),
					historicalOffenseTermForm,
					bindingResult);
			mav.addObject(SENTENCE_MODEL_KEY, sentence);
			return mav;
		}
		this.historicalOffenseTermService.update(sentence,
				historicalOffenseTermForm.getSentenceFields()
					.getCategory(),
				historicalOffenseTermForm.getSentenceFields()
					.getLengthClassification(),
				historicalOffenseTermForm.getSentenceFields()
					.getLegalDispositionCategory(),
				historicalOffenseTermForm.getSentenceFields()
					.getPronouncementDate(),
				new ConvictionCredit(
						historicalOffenseTermForm.getSentenceFields()
							.getJailTimeCredit(),
						historicalOffenseTermForm.getSentenceFields()
							.getStreetTimeCredit()),
				historicalOffenseTermForm.getSentenceFields()
					.getEffectiveDate(),
				historicalOffenseTermForm.getSentenceFields()
					.getTurnSelfInDate(),
				new Term(
						historicalOffenseTermForm.getSentenceFields()
							.getPrisonYears(),
						historicalOffenseTermForm.getSentenceFields()
							.getPrisonMonths(),
						historicalOffenseTermForm.getSentenceFields()
							.getPrisonDays()),
				new Term(
						historicalOffenseTermForm.getSentenceFields()
							.getProbationYears(),
						historicalOffenseTermForm.getSentenceFields()
							.getProbationMonths(),
						historicalOffenseTermForm.getSentenceFields()
							.getProbationDays()),
				new Term(
						historicalOffenseTermForm.getSentenceFields()
							.getDeferredYears(),
						historicalOffenseTermForm.getSentenceFields()
							.getDeferredMonths(),
						historicalOffenseTermForm.getSentenceFields()
							.getDeferredDays()));
		return new ModelAndView(
				String.format(REDIRECT, sentence.getConviction().getId()));
	}
	
	/**
	 * Removes historical offense term.
	 * 
	 * @param sentence inactive sentence to remove
	 * @return redirect to listing screen
	 */
	@RequestMapping(
			value = "/removeHistoricalOffenseTerm.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_TERM_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "sentence", required = true)
				final Sentence sentence) {
		Conviction conviction = sentence.getConviction();
		this.historicalOffenseTermService.remove(sentence);
		return new ModelAndView(String.format(REDIRECT,
				conviction.getId()));
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Returns sentence category.
	 * 
	 * @param sentenceCategory sentence category to return 
	 * @return sentence category
	 */
	@RequestMapping(
			value = "/findHistoricalSentenceCategory.json",
			method = RequestMethod.GET)
	public ModelAndView findSentencCategory(
			@RequestParam(value = "sentenceCategory", required = true)
				final SentenceCategory sentenceCategory) {
		ModelAndView mav = new ModelAndView(SENTENCE_CATEGORY_VIEW_NAME);
		mav.addObject(SENTENCE_CATEGORY_MODEL_KEY, sentenceCategory);
		return mav;
	}
	
	/**
	 * Calculates total days.
	 * 
	 * @param years years
	 * @param months months
	 * @param days days
	 * @return calculation for total days
	 */
	@RequestMapping(
			value = "/calculateHistoricalTotalDays.json",
			method = RequestMethod.GET)
	public ModelAndView calculateTotalDays(
			@RequestParam(value = "years", required = false)
				final Integer years,
			@RequestParam(value = "months", required = false)
				final Integer months,
			@RequestParam(value = "days", required = false)
				final Integer days) {
		Integer totalDays = this.historicalOffenseTermService
				.calculateTotalDays(new Term(years, months, days));
		ModelAndView mav = new ModelAndView(INTEGER_VALUE_VIEW_NAME);
		mav.addObject(INTEGER_VALUE_MODEL_KEY, totalDays);
		return mav;
	}
	
	/**
	 * Shows sentence terms.
	 * 
	 * @param category category
	 * @param prisonYears prison years
	 * @param prisonMonths prison months
	 * @param prisonDays prison days
	 * @param probationYears probation years
	 * @param probationMonths probation months
	 * @param probationDays probation days
	 * @param deferredYears deferred years
	 * @param deferredMonths deferred months
	 * @param deferredDays deferred days
	 * @return sentence terms
	 */
	@RequestMapping(
			value = "/showHistoricalSentenceTerms.html",
			method = RequestMethod.GET)
	public ModelAndView showSentenceTerms(
			@RequestParam(value = "category", required = true)
				final SentenceCategory category,
			@RequestParam(value = "prisonYears", required = false)
				final Integer prisonYears,
			@RequestParam(value = "prisonMonths", required = false)
				final Integer prisonMonths,
			@RequestParam(value = "prisonDays", required = false)
				final Integer prisonDays,
			@RequestParam(value = "probationYears", required = false)
				final Integer probationYears,
			@RequestParam(value = "probationMonths", required = false)
				final Integer probationMonths,
			@RequestParam(value = "probationDays", required = false)
				final Integer probationDays,
			@RequestParam(value = "deferredYears", required = false)
				final Integer deferredYears,
			@RequestParam(value = "deferredMonths", required = false)
				final Integer deferredMonths,
			@RequestParam(value = "deferredDays", required = false)
				final Integer deferredDays) {
		ModelAndView mav = new ModelAndView(SENTENCE_TERMS_VIEW_NAME);
		if (category != null) {
			boolean bShowPrisonTerm = (TermRequirement.REQUIRED.equals(
					category.getPrisonRequirement()) || 
					TermRequirement.OPTIONAL.equals(category
							.getPrisonRequirement()));
			boolean bShowProbationTerm = (TermRequirement.REQUIRED.equals(
					category.getProbationRequirement()) || 
					TermRequirement.OPTIONAL.equals(category
							.getProbationRequirement()));
			boolean bShowDeferredTerm = (TermRequirement.REQUIRED.equals(
					category.getDeferredRequirement()) || 
					TermRequirement.OPTIONAL.equals(category
							.getDeferredRequirement()));
			mav.addObject(SHOW_PRISON_TERM_MODEL_KEY, bShowPrisonTerm);
			mav.addObject(SHOW_PROBATION_TERM_MODEL_KEY, bShowProbationTerm);
			mav.addObject(SHOW_DEFERRED_TERM_MODEL_KEY, bShowDeferredTerm);
			if (bShowPrisonTerm) {
				mav.addObject(PRISON_YEARS_MODEL_KEY, prisonYears);
				mav.addObject(PRISON_MONTHS_MODEL_KEY, prisonMonths);
				mav.addObject(PRISON_DAYS_MODEL_KEY, prisonDays);
			}
			if (bShowProbationTerm) {
				mav.addObject(PROBATION_YEARS_MODEL_KEY, probationYears);
				mav.addObject(PROBATION_MONTHS_MODEL_KEY, probationMonths);
				mav.addObject(PROBATION_DAYS_MODEL_KEY, probationDays);
			}
			if (bShowDeferredTerm) {
				mav.addObject(DEFERRED_YEARS_MODEL_KEY, deferredYears);
				mav.addObject(DEFERRED_MONTHS_MODEL_KEY, deferredMonths);
				mav.addObject(DEFERRED_DAYS_MODEL_KEY, deferredDays);
			}
		}
		return mav;
	}
	
	/**
	 * Calculates sentence effective date.
	 * 
	 * @param pronouncementDate pronouncement date
	 * @param jailTimeCredit jail time credit
	 * @param streetTimeCredit street time credit
	 * @return calculated sentence effective date
	 */
	@RequestMapping(
			value = "/calculateHistoricalSentenceEffectiveDate.json",
			method = RequestMethod.GET)
	public ModelAndView calculateSentenceEffectiveDate(
			@RequestParam(value = "pronouncementDate", required = true)
				final Date pronouncementDate,
			@RequestParam(value = "jailTimeCredit", required = true)
				final Integer jailTimeCredit,
			@RequestParam(value = "streetTimeCredit", required = true)
				final Integer streetTimeCredit) {
		Date effectiveDate = this.historicalOffenseTermService
				.calculateSentenceEffectiveDate(
						pronouncementDate,
						new ConvictionCredit(
								jailTimeCredit,
								streetTimeCredit));
		ModelAndView mav = new ModelAndView(DATE_VALUE_VIEW_NAME);
		mav.addObject(DATE_VALUE_MODEL_KEY, effectiveDate);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu.
	 * 
	 * @param conviction conviction
	 * @return action menu
	 */
	@RequestMapping(
			value = "/historicalOffenseTermActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(CONVICTION_MODEL_KEY, conviction);
		return mav;
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles sentence exists exceptions.
	 * 
	 * @param exception sentence exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(SentenceExistsException.class)
	public ModelAndView handleSentenceExistsException(
			final SentenceExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				SENTENCE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareEditMav(
			final HistoricalOffenseTermForm historicalOffenseTermForm,
			final Conviction conviction) {
		Person person = conviction.getCourtCase().getDocket().getPerson();
		List<SentenceCategory> categories = this.historicalOffenseTermService
				.findSentenceCategories();
		List<LegalDispositionCategory> legalDispositionCategories
			= this.historicalOffenseTermService
				.findLegalDispositionCategories();
		OffenderSummary offenderSummary = this.offenderReportService
				.summarizeIfOffender(person);
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(HISTORICAL_OFFENSE_TERM_FORM_MODEL_KEY,
				historicalOffenseTermForm);
		mav.addObject(CONVICTION_MODEL_KEY, conviction);
		mav.addObject(LEGAL_DISPOSITION_CATEGORIES_MODEL_KEY,
				legalDispositionCategories);
		mav.addObject(SENTENCE_CATEGORIES_MODEL_KEY, categories);
		mav.addObject(SENTENCE_LENGTH_CLASSIFICATIONS_MODEL_KEY,
				SentenceLengthClassification.values());
		if (offenderSummary != null) {
			
			// TODO - Add service method that returns offender form person - SA
			this.offenderSummaryModelDelegate.add(mav.getModelMap(),
					(Offender) person);
		} else {
			this.contactSummaryModelDelegate.add(mav.getModelMap(), person);
		}
		return mav;
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedirectMav(
			final Conviction conviction,
			final HistoricalOffenseTermForm historicalOffenseTermForm,
			final BindingResult bindingResult) {
		ModelAndView mav = this.prepareEditMav(
				historicalOffenseTermForm, conviction);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ HISTORICAL_OFFENSE_TERM_FORM_MODEL_KEY, bindingResult);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Sentence.class,
				this.sentencePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Conviction.class,
				this.convictionPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(SentenceCategory.class,
				this.sentenceCategoryPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(LegalDispositionCategory.class,
				this.legalDispositionCategoryPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}