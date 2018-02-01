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
import omis.contact.web.controller.delegate.ContactSummaryModelDelegate;
import omis.court.domain.Court;
import omis.courtcase.domain.CourtCase;
import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offenseterm.service.OffenseTermDocketService;
import omis.offenseterm.web.form.OffenseTermDocketForm;
import omis.offenseterm.web.validator.OffenseTermDocketFormValidator;
import omis.person.domain.Person;
import omis.sentence.exception.ConnectedSentenceExistsException;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller to manage a docket for an offense term.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/offenseTerm")
public class ManageOffenseTermDocketController {


	/* View names. */
	
	private static final String VIEW_NAME = "offenseTerm/editDocket";
	
	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "offenseTerm/includes/offenseTermDocketActionMenu";
	
	/* Redirects. */
	
	private static final String REDIRECT_URL
		= "redirect:/offenseTerm/edit.html?courtCase=%d";
	
	/* Model keys. */
	
	private static final String OFFENSE_TERM_DOCKET_FORM_MODEL_KEY 	
		= "offenseTermDocketForm";

	private static final String COURTS_MODEL_KEY = "courts";
	
	private static final String PERSON_MODEL_KEY = "person";
	
	private static final String COURT_CASE_MODEL_KEY = "courtCase";

	/* Message keys. */
	
	private static final String DOCKET_EXISTS_MESSAGE_KEY
		= "offenseTermForm.docket.exists";
	
	/* Error bundle names. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.offenseterm.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("offenseTermDocketService")
	private OffenseTermDocketService offenseTermDocketService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("courtCasePropertyEditorFactory")
	private PropertyEditorFactory courtCasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("courtPropertyEditorFactory")
	private PropertyEditorFactory courtPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("offenseTermDocketFormValidator")
	private OffenseTermDocketFormValidator offenseTermDocketFormValidator;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;
	
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

	/* Constructors. */
	
	/** Instantiates controller to manage dockets for offense terms. */
	public ManageOffenseTermDocketController() {
		// Default instantiation
	}
	
	/**
	 * Removes current offense.
	 * 
	 * <p>This includes conviction and all sentences of conviction.
	 * 
	 * @param courtCase court case
	 * @return redirect to listing screen
	 * @throws ConnectedSentenceExistsException if the conviction has an
	 * associated active sentence that is connected to by other sentences
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_DOCKET_VIEW') or hasRole('ADMIN')")
	@RequestMapping(
			value = "/editDocket.html",
			method = RequestMethod.GET)
	public ModelAndView editDocket(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase)
						throws ConnectedSentenceExistsException {
		OffenseTermDocketForm docketForm = new OffenseTermDocketForm();
		docketForm.setCourt(courtCase.getDocket().getCourt());
		docketForm.setDocketValue(courtCase.getDocket().getValue());
		ModelAndView mav = prepareMav(docketForm, 
				courtCase.getDocket().getPerson());
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		return mav;
	}
	
	/**
	 * Updates a docket.
	 * 
	 * @param courtCase court case
	 * @param offenseTermDocketForm form for offense term docket
	 * @param bindingResult binding result
	 * @return redirect to offense term listing screen
	 * @throws DuplicateEntityFoundException 
	 */
	@PreAuthorize("hasRole('OFFENSE_TERM_DOCKET_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/editDocket.html", method = RequestMethod.POST)
	public ModelAndView updateDocket(
			@RequestParam(value = "courtCase", required = true)
				final CourtCase courtCase,
			final OffenseTermDocketForm offenseTermDocketForm,
			final BindingResult bindingResult) throws DocketExistsException {
		this.offenseTermDocketFormValidator.validate(offenseTermDocketForm, 
				bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView mav =  this.prepareMav(offenseTermDocketForm, 
					courtCase.getDocket().getPerson());
			mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ OFFENSE_TERM_DOCKET_FORM_MODEL_KEY, bindingResult);
			mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
			return mav;
		}
		this.offenseTermDocketService.update(courtCase.getDocket(), 
				offenseTermDocketForm.getCourt(), 
				offenseTermDocketForm.getDocketValue());
		return new ModelAndView(String.format(REDIRECT_URL, courtCase.getId()));
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for screen to create/edit a docket.
	 * 
	 * @param courtCase court case
	 * @return action menu for screen to create/edit a docket
	 */
	@RequestMapping(
			value = "/offenseTermDocketActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "courtCase", required = true)
			final CourtCase courtCase) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		return mav;
	}
	
	/* Exception handlers. */
	
	/**
	 * Handles {@code DocketExistsException}.
	 * 
	 * @param docketExistsException exception thrown
	 * @return handler for {@code DocketExistsException}
	 */
	@ExceptionHandler
	public ModelAndView handleDocketExistsException(
			final DocketExistsException
				docketExistsException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(DOCKET_EXISTS_MESSAGE_KEY,
						ERROR_BUNDLE_NAME, docketExistsException);
	}
	
	/* Helper methods. */
	
	// Prepares model and view
	private ModelAndView prepareMav(final OffenseTermDocketForm docketForm, 
			final Person person) {
		OffenderSummary offenderSummary = this.offenderReportService
				.summarizeIfOffender(person);
		List<Court> courts = this.offenseTermDocketService.findCourts();
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(OFFENSE_TERM_DOCKET_FORM_MODEL_KEY, docketForm);
		mav.addObject(COURTS_MODEL_KEY, courts);
		mav.addObject(PERSON_MODEL_KEY, person);
		if (offenderSummary != null) {
			
			// TODO - Add service method that returns offender form person - SA
			this.offenderSummaryModelDelegate.add(mav.getModelMap(), 
					(Offender) person);
		} else {
			this.contactSummaryModelDelegate.add(mav.getModelMap(), person);
		}
		return mav;
	}
	
/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder data binder
	 */
	@InitBinder
	protected void registerCustomEditors(
			final WebDataBinder binder) {
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Court.class,
				this.courtPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(CourtCase.class,
				this.courtCasePropertyEditorFactory.createPropertyEditor());
		
	}
}
