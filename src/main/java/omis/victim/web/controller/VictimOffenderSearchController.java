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
package omis.victim.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.offender.report.OffenderSummary;
import omis.offender.web.form.OffenderSearchFields;
import omis.offender.web.form.OffenderSearchType;
import omis.person.domain.Person;
import omis.victim.report.VictimOffenderSearchReportService;
import omis.victim.web.controller.delegate.VictimSummaryModelDelegate;
import omis.victim.web.form.VictimOffenderSearchForm;
import omis.victim.web.validator.VictimOffenderSearchFormValidator;

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

/**
 * Controller to search for offender while victim is in scope.
 *
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.0.1 (Aug 11, 2015)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/victim")
public class VictimOffenderSearchController {
	
	/* View names. */
	
	private static final String VIEW_NAME = "victim/offender/search";

	/* Model keys. */
	
	private static final String FORM_MODEL_KEY = "victimOffenderSearchForm";

	private static final String OFFENDER_SUMMARIES_MODEL_KEY
		= "offenderSummaries";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("victimOffenderSearchReportService")
	private VictimOffenderSearchReportService victimOffenderSearchReportService;
	
	/* Property editor factories. */

	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("victimOffenderSearchFormValidator")
	private VictimOffenderSearchFormValidator victimOffenderSearchFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("victimSummaryModelDelegate")
	private VictimSummaryModelDelegate victimSummaryModelDelegate;
	
	/* Constructors. */
	
	/**
	 * Instantiates controller to search for offender while victim is in scope.
	 */
	public VictimOffenderSearchController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays form to search for offenders while victim is in scope.
	 * 
	 * @param victim victim
	 * @return form to search for offenders while victim is in scope
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_OFFENDER_SEARCH')")
	@RequestMapping(value = "/offender/search.html", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "victim", required = true)
				final Person victim) {
		OffenderSearchFields offenderSearchFields
			= new OffenderSearchFields();
		offenderSearchFields.setType(OffenderSearchType.NAME);
		VictimOffenderSearchForm victimOffenderSearchForm
			= new VictimOffenderSearchForm();
		victimOffenderSearchForm.setOffenderSearchFields(offenderSearchFields);
		return this.prepareMav(victim, victimOffenderSearchForm);
	}

	/**
	 * Searches for offenders while victim is in scope.
	 * 
	 * @param victim victim
	 * @param victimOffenderSearchForm form to search for offenders while
	 * victim is in scope
	 * @param result binding results
	 * @return results of search for offenders while victim is in scope
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_ASSOCIATION_LIST')")
	@RequestMapping(
			value = "/offender/search.html", method = RequestMethod.POST)
	public ModelAndView performSearch(
			@RequestParam(value = "victim", required = true)
				final Person victim,
			final VictimOffenderSearchForm victimOffenderSearchForm,
			final BindingResult result) {
		this.victimOffenderSearchFormValidator
			.validate(victimOffenderSearchForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(
					victim, victimOffenderSearchForm, result);
		}
		OffenderSearchFields offenderSearchFields
			= victimOffenderSearchForm.getOffenderSearchFields();
		List<OffenderSummary> offenderSummaries;
		if (OffenderSearchType.NAME.equals(offenderSearchFields.getType())) {
			offenderSummaries = this.victimOffenderSearchReportService
					.summarizeByName(
							offenderSearchFields.getLastName(),
							offenderSearchFields.getFirstName());
		} else if (OffenderSearchType.OFFENDER_NUMBER.equals(
				offenderSearchFields.getType())) {
			offenderSummaries = this.victimOffenderSearchReportService
					.summarizeByOffenderNumber(
							offenderSearchFields.getOffenderNumber());
		} else if (OffenderSearchType.SOCIAL_SECURITY_NUMBER.equals(
				offenderSearchFields.getType())) {
			offenderSummaries = this.victimOffenderSearchReportService
					.summarizeBySocialSecurityNumber(
							Integer.valueOf(offenderSearchFields
									.getSocialSecurityNumber()));
		} else if (OffenderSearchType.BIRTH_DATE.equals(
				offenderSearchFields.getType())) {
			offenderSummaries = this.victimOffenderSearchReportService
					.summarizeByBirthDate(offenderSearchFields.getBirthDate());
		} else {
			throw new UnsupportedOperationException(String.format(
					"Unknown type %s", victimOffenderSearchForm
						.getOffenderSearchFields().getType()));
		}
		ModelAndView mav = this.prepareMav(victim, victimOffenderSearchForm);
		mav.addObject(OFFENDER_SUMMARIES_MODEL_KEY, offenderSummaries);
		return mav;
	}

	/* Helper methods. */
	
	// Prepares model and view to search
	private ModelAndView prepareMav(
			final Person victim,
			final VictimOffenderSearchForm victimOffenderSearchForm) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(FORM_MODEL_KEY, victimOffenderSearchForm);
		this.victimSummaryModelDelegate.add(mav.getModelMap(), victim);
		return mav;
	}
	
	// Prepares model and view to redisplay search
	private ModelAndView prepareRedisplayMav(
			final Person victim,
			final VictimOffenderSearchForm victimOffenderSearchForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(victim, victimOffenderSearchForm);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	/* Ajax invokable methods. */
	
	/**
	 * Shows action menu for offender search screen.
	 * 
	 * @param victim victim
	 * @return action menu for offender search screen
	 */
	@RequestMapping(
			value = "/offender/showSearchActionMenu.html",
			method = RequestMethod.POST)
	public ModelAndView showActionMenu(
			@RequestParam(value = "victim", required = true)
				final Person victim) {
		
		// TODO: Implement - SA
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}