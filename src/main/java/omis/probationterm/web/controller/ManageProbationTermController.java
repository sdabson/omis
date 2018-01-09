package omis.probationterm.web.controller;

import java.util.Date;

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
import omis.courtcase.domain.CourtCase;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.exception.ProbationTermConflictException;
import omis.probationterm.exception.ProbationTermExistsAfterException;
import omis.probationterm.exception.ProbationTermExistsException;
import omis.probationterm.service.ProbationTermService;
import omis.probationterm.web.form.ProbationTermForm;
import omis.probationterm.web.validator.ProbationTermFormValidator;
import omis.term.domain.component.Term;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for probation terms.
 *
 * @author Josh Divine
 * @version 0.0.1 (May 24, 2017)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/probationTerm")
public class ManageProbationTermController {

	/* View names. */
	
	private static final String VIEW_NAME = "probationTerm/edit";

	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME 
		= "probationTerm/includes/probationTermActionMenu";
	
	/* Partial view names. */
	
	private static final String DATE_VALUE_VIEW_NAME = "common/json/dateValue";
	
	private static final String INTEGER_VALUE_VIEW_NAME
		= "common/json/integerValue";
	
	/* Redirects. */
	
	private static final String REDIRECT_COURT_CASE_URL
		= "redirect:/probationTerm/list.html?courtCase=%d";
	
	private static final String REDIRECT_OFFENDER_URL
		= "redirect:/probationTerm/list.html?offender=%d";
	
	/* Model keys. */
	
	private static final String PROBATION_TERM_FORM_MODEL_KEY 
		= "probationTermForm";
	
	private static final String COURT_CASE_MODEL_KEY = "courtCase";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String PROBATION_TERM_MODEL_KEY = "probationTerm";
	
	private static final String INTEGER_VALUE_MODEL_KEY = "integerValue";
	
	private static final String DATE_VALUE_MODEL_KEY = "dateValue";
	
	/* Message keys. */
	
	private static final String PROBATION_TERM_CONFLICT_MESSAGE_KEY 
		= "probationTerm.conflict";

	private static final String PROBATION_TERM_EXISTS_MESSAGE_KEY 
		= "probationTerm.exists";

	private static final String PROBATION_TERM_EXISTS_AFTER_MESSAGE_KEY 
		= "probationTerm.existsAfter";

	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME 
		= "omis.probationterm.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("probationTermService")
	private ProbationTermService probationTermService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("courtCasePropertyEditorFactory")
	private PropertyEditorFactory courtCasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("probationTermPropertyEditorFactory")
	private PropertyEditorFactory probationTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Validators. */

	@Autowired
	@Qualifier("probationTermFormValidator")
	private ProbationTermFormValidator probationTermFormValidator;
	
	/* Helpers. */

	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructors. */
	
	/** Instantiates controller for probation terms. */
	public ManageProbationTermController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create a probation term.
	 * 
	 * @param courtCase court case
	 * @return screen to create court case
	 */
	@PreAuthorize("hasRole('PROBATION_TERM_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase) {
		ProbationTermForm probationTermForm = new ProbationTermForm();
		ModelAndView mav = this.prepareMav(probationTermForm, courtCase);
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		return mav;
	}
	
	/**
	 * Shows screen to edit a probation term.
	 * 
	 * @param probationTerm probation term
	 * @return screen to edit a probation term
	 */
	@PreAuthorize("hasRole('PROBATION_TERM_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "probationTerm", required = true)
				final ProbationTerm probationTerm,
			@RequestParam(value = "courtCase", required = false)
				final CourtCase courtCase) {
		ProbationTermForm probationTermForm = new ProbationTermForm();
		probationTermForm.setStartDate(probationTerm.getStartDate());
		probationTermForm.setTerminationDate(
				probationTerm.getTerminationDate());
		if (probationTerm.getTerm() != null) {
			probationTermForm.setYears(probationTerm.getTerm().getYears());
			probationTermForm.setMonths(probationTerm.getTerm().getMonths());
			probationTermForm.setDays(probationTerm.getTerm().getDays());
			probationTermForm.setTotalDays(this.probationTermService
					.calculateTotalProbationTermDays(probationTerm.getTerm()));
		}
		probationTermForm.setJailCredit(probationTerm.getJailCredit());
		probationTermForm.setExpirationDate(probationTerm.getExpirationDate());
		probationTermForm.setSentenceDays(probationTerm.getSentenceDays());
		ModelAndView mav = this.prepareMav(probationTermForm, 
				probationTerm.getCourtCase());
		if (courtCase != null) {
			mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		}
		mav.addObject(PROBATION_TERM_MODEL_KEY, probationTerm);
		return mav;
	}
	
	/**
	 * Saves a probation term.
	 * 
	 * @param courtCase court case
	 * @param probationTermForm probation term form
	 * @param bindingResult binding result
	 * @return redirect to probation term listing screen
	 * @throws ProbationTermExistsAfterException thrown when probation term 
	 * exists after start date and end date is null 
	 * @throws ProbationTermConflictException thrown when probation term 
	 * conflicts with another 
	 * @throws ProbationTermExistsException thrown when probation term exists
	 */
	@PreAuthorize("hasRole('PROBATION_TERM_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase,
			final ProbationTermForm probationTermForm,
			final BindingResult bindingResult) 
					throws ProbationTermExistsException, 
					ProbationTermConflictException, 
					ProbationTermExistsAfterException {
		this.probationTermFormValidator.validate(probationTermForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(
					probationTermForm, bindingResult, courtCase);
			return mav;
			}
		Term term = new Term(probationTermForm.getYears(), probationTermForm
				.getMonths(), probationTermForm.getDays());
		this.probationTermService.create(courtCase, term,
				probationTermForm.getStartDate(),
				probationTermForm.getTerminationDate(), 
				probationTermForm.getExpirationDate(), 
				probationTermForm.getJailCredit(), 
				probationTermForm.getSentenceDays());

		return new ModelAndView(String.format(REDIRECT_COURT_CASE_URL, 
				courtCase.getId()));
	}
	
	/**
	 * Updates a probation term.
	 * 
	 * @param probationTerm probation term
	 * @param probationTermForm probation term form
	 * @param bindingResult binding result
	 * @return redirect to probation term listing screen
	 * @throws ProbationTermExistsAfterException thrown when probation term 
	 * exists after start date and end date is null 
	 * @throws ProbationTermConflictException thrown when probation term 
	 * conflicts with another 
	 * @throws ProbationTermExistsException thrown when probation term exists
	 */
	@PreAuthorize("hasRole('PROBATION_TERM_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "probationTerm", required = true)
				final ProbationTerm probationTerm,
			@RequestParam(value = "courtCase", required = false)
				final CourtCase courtCase,
			final ProbationTermForm probationTermForm,
			final BindingResult bindingResult) 
					throws ProbationTermExistsException, 
					ProbationTermConflictException, 
					ProbationTermExistsAfterException {
		this.probationTermFormValidator.validate(probationTermForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav = this.prepareRedisplay(
					probationTermForm, bindingResult, 
					probationTerm.getCourtCase());
			return mav;
			}
		Term term = new Term(probationTermForm.getYears(), probationTermForm
				.getMonths(), probationTermForm.getDays());
		this.probationTermService.update(probationTerm, term,
				probationTermForm.getStartDate(),
				probationTermForm.getTerminationDate(),
				probationTermForm.getExpirationDate(), 
				probationTermForm.getJailCredit(), 
				probationTermForm.getSentenceDays());
		if (courtCase != null) {
			return new ModelAndView(String.format(REDIRECT_COURT_CASE_URL, 
					courtCase.getId()));
		}
		return new ModelAndView(String.format(REDIRECT_OFFENDER_URL, 
				probationTerm.getOffender().getId()));
	}
	
	/**
	 * Removes a probation term.
	 * 
	 * @param probationTerm probation term
	 * @param courtCase court case
	 * @return redirect to probation term listing screen
	 */
	@PreAuthorize("hasRole('PROBATION_TERM_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "probationTerm", required = true)
				final ProbationTerm probationTerm,
			@RequestParam(value = "courtCase", required = false)
				final CourtCase courtCase) {
		Long offenderId = probationTerm.getOffender().getId();
		this.probationTermService.remove(probationTerm);
		if (courtCase != null) {
			return new ModelAndView(String.format(REDIRECT_COURT_CASE_URL, 
					courtCase.getId())); 
					
		} else {
			return new ModelAndView(String.format(REDIRECT_OFFENDER_URL, 
					offenderId));
		}
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
			value = "/calculateTotalDays.json", method = RequestMethod.GET)
	public ModelAndView calculateTotalDays(
			@RequestParam(value = "years", required = false)
				final Integer years,
			@RequestParam(value = "months", required = false)
				final Integer months,
			@RequestParam(value = "days", required = false)
				final Integer days) {
		Integer totalDays = this.probationTermService
				.calculateTotalProbationTermDays(new Term(years, months, days));
		ModelAndView mav = new ModelAndView(INTEGER_VALUE_VIEW_NAME);
		mav.addObject(INTEGER_VALUE_MODEL_KEY, totalDays);
		return mav;
	}
	
	/**
	 * Calculates sentence effective date.
	 * 
	 * @param startDate start date
	 * @param sentenceDays sentence days
	 * @return calculated probation expiration date
	 */
	@RequestMapping(
			value = "/calculateProbationExpirationDate.json",
			method = RequestMethod.GET)
	public ModelAndView calculateProbationExpirationDate(
			@RequestParam(value = "startDate", required = true)
				final Date startDate,
			@RequestParam(value = "sentenceDays", required = true)
				final Integer sentenceDays) {
		Date effectiveDate = this.probationTermService
				.calculateProbationExpirationDate(startDate, sentenceDays);
		ModelAndView mav = new ModelAndView(DATE_VALUE_VIEW_NAME);
		mav.addObject(DATE_VALUE_MODEL_KEY, effectiveDate);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Displays action menu for screen to create/edit a probation term.
	 * 
	 * @param courtCase court case
	 * @param offender offender
	 * @return action menu for screen to create/edit a probation term
	 */
	@RequestMapping(
			value = "/probationTermActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "courtCase", required = false)
				final CourtCase courtCase,
			@RequestParam(value = "offender", required = false)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	/* Exception handler methods. */
	
	/**
	 * Handles probation term exists exceptions.
	 * 
	 * @param exception probation term exists exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ProbationTermExistsException.class)
	public ModelAndView handleProbationTermExistsException(
			final ProbationTermExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PROBATION_TERM_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles probation term exists after exceptions.
	 * 
	 * @param exception probation term exists after exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ProbationTermExistsAfterException.class)
	public ModelAndView handleProbationTermExistsAfterException(
			final ProbationTermExistsAfterException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PROBATION_TERM_EXISTS_AFTER_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles probation term conflict exceptions.
	 * 
	 * @param exception probation term conflict exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ProbationTermConflictException.class)
	public ModelAndView handleProbationTermConflictException(
			final ProbationTermConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PROBATION_TERM_CONFLICT_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Helper methods. */
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplay(
			final ProbationTermForm probationTermForm,
			final BindingResult bindingResult,
			final CourtCase courtCase) {
		ModelAndView mav = this.prepareMav(probationTermForm, courtCase);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ PROBATION_TERM_FORM_MODEL_KEY, bindingResult);
		return mav;
	}

	// Prepares model and view
	private ModelAndView prepareMav(
			final ProbationTermForm probationTermForm,
			final CourtCase courtCase) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(PROBATION_TERM_FORM_MODEL_KEY, probationTermForm);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), 
				(Offender) courtCase.getDocket().getPerson());
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(CourtCase.class,
				this.courtCasePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ProbationTerm.class, 
				this.probationTermPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
	}
}
