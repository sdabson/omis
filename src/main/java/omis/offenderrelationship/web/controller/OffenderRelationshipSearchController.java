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
package omis.offenderrelationship.web.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.config.util.FeatureToggles;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.offenderrelationship.report.OffenderRelationshipReportService;
import omis.offenderrelationship.report.OffenderRelationshipSummary;
import omis.offenderrelationship.web.form.OffenderRelationshipSearchForm;
import omis.offenderrelationship.web.form.OffenderRelationshipSearchType;
import omis.offenderrelationship.web.validator.OffenderRelationshipSearchFormValidator;
import omis.person.domain.Person;
import omis.relationship.domain.Relationship;
import omis.web.form.SearchOperation;

/**
 * Controller to search for offender relationships.
 *
 * <p>The view can be configured to create/edit types of relationship entities
 * based on the {@code createUrl}, {@code editUrl} and {@code entityName}
 * parameters respectively. If none of these parameters are provided, the
 * controller will display a list of relationships for an offender without
 * the ability to perform operations on the relationships.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.0.1 (Jun 23, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offenderRelationship")
@PreAuthorize("hasRole('USER')")
public class OffenderRelationshipSearchController {
	
	/* View names */
	
	private static final String VIEW_NAME = "offenderRelationship/search";
	
	private static final String 
		OFFENDER_RELATIONSHIP_SEARCH_ROW_ACTION_MENU_VIEW_NAME 
			= "offenderRelationship/includes/offenderRelationshipSearchRowActionMenu";

	/* Model keys */
	
	private static final String OPTIONS_MODEL_KEY = "options";

	private static final String FORM_MODEL_KEY
		= "offenderRelationshipSearchForm";

	private static final String OFFENDER_RELATIONSHIP_SUMMARIES_MODEL_KEY
		= "offenderRelationshipSummaries";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String RELATION_MODEL_KEY = "relation";
	
	private static final String RELATIONSHIP_MODEL_KEY = "relationship";
	
	private static final String RELATIONSHIP_EXIST_MODEL_KEY 
		= "relationshipExist";
	
	private static final String RELATION_IS_OFFENDER_MODEL_KEY
		= "relationIsOffender";
	
	/* Constants. */
	
	private static final int FIRST_NAME_LENGTH_FIVE = 5;
	
	private static final int FIRST_NAME_LENGTH_TWO = 2;
	
	private static final int LAST_NAME_LENGTH_TWO = 2;
	
	private static final int LAST_NAME_LENGTH_FIVE = 5;
	
	private static final int LAST_NAME_LENGTH_SIX = 6;

	private static final String SHOW_ALL_RESULTS_OPTIONS_MODEL_KEY
		= "showAllResultsOptions";

	private static final String MAXIMUM_RESULTS_MODEL_KEY = "maximumResults";
	
	/* Toggle keys. */
	
	private static final String ALLOW_ADDRESS_SEARCH_TOGGLE_KEY
		= "allowAddressSearch";
	
	private static final String ALLOW_TELEPHONE_NUMBER_SEARCH_TOGGLE_KEY
		= "allowTelephoneNumberSearch";
	
	private static final String ALLOW_ONLINE_ACCOUNT_SEARCH_TOGGLE_KEY
		= "allowOnlineAccountSearch";
	
	/* Module name. */
	
	private static final String MODULE_NAME = "offenderrelationship";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("offenderRelationshipReportService")
	private OffenderRelationshipReportService offenderRelationshipReportService;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("relationshipPropertyEditorFactory")
	private PropertyEditorFactory relationshipPropertyEditorFactory;

	/* Validators. */
	
	@Autowired
	@Qualifier("offenderRelationshipSearchFormValidator")
	private OffenderRelationshipSearchFormValidator 
		offenderRelationshipSearchFormValidator;
	
	/* Feature toggles. */
	
	@Autowired
	@Qualifier("featureToggles")
	private FeatureToggles featureToggles;
	
	/* Constructors */
	
	/** Instantiates controller to search for offender relationships. */
	public OffenderRelationshipSearchController() {
		// Default constructor
	}
	
	/* URL invokable methods */
	
	/**
	 * Displays form to search for offender relationships.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @param options relationship options
	 * @return form to search for offender relationships
	 */
	@PreAuthorize("hasRole('OFFENDER_RELATIONSHIP_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate,
			@RequestParam(value = "option", required = false)
				final OffenderRelationshipOption[] options) {
		OffenderRelationshipSearchForm offenderRelationshipSearchForm
			= new OffenderRelationshipSearchForm();
		offenderRelationshipSearchForm.setType(
				OffenderRelationshipSearchType.NAME);
		ModelAndView mav = this.prepareMav(
				offenderRelationshipSearchForm, options);
		if (offender != null) {
			this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		}
		return mav;
	}
	
	/**
	 * Performs search for offender relationships, displays results.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @param options relationship options
	 * @param offenderRelationshipSearchForm form for searching for offender
	 * @param searchOperation search operation
	 * relationships
	 * @param result binding result
	 * @return form to search for relationships with results
	 */
	@PreAuthorize("hasRole('OFFENDER_RELATIONSHIP_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/search.html", method = RequestMethod.POST)
	public ModelAndView performSearch(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate,
			@RequestParam(value = "option", required = false)
				final OffenderRelationshipOption[] options,
			@RequestParam(value = "searchOperation", required = true)
				final SearchOperation searchOperation,
			final OffenderRelationshipSearchForm
				offenderRelationshipSearchForm,
			final BindingResult result) {
		if (SearchOperation.SEARCH.equals(searchOperation)) {
			this.offenderRelationshipSearchFormValidator.validate(
					offenderRelationshipSearchForm, result);
			if (result.hasErrors()) {
				ModelAndView mav = this.prepareRedisplayMav(
						offenderRelationshipSearchForm, options, result); 
				if (offender != null) {
					this.offenderSummaryModelDelegate.add(mav.getModel(),
							offender);
				}
				return mav;
			}		
		}
		ModelAndView mav = this.prepareMav(
				offenderRelationshipSearchForm, options);
		if (offender != null) {
			this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		}
		List<OffenderRelationshipSummary> offenderRelationshipSummaries;		
		if (OffenderRelationshipSearchType.NAME
				.equals(offenderRelationshipSearchForm.getType())) {			
			Boolean approximateMatch;
			if ((offenderRelationshipSearchForm.getFirstName().length() 
					<= FIRST_NAME_LENGTH_FIVE
				&& offenderRelationshipSearchForm.getFirstName().length()
					>= LAST_NAME_LENGTH_TWO)
				&& (offenderRelationshipSearchForm.getLastName().length()
					< LAST_NAME_LENGTH_SIX)
				|| (offenderRelationshipSearchForm.getFirstName().length()
						< FIRST_NAME_LENGTH_TWO
				&& (offenderRelationshipSearchForm.getLastName().length() 
					<= LAST_NAME_LENGTH_FIVE
				&& offenderRelationshipSearchForm.getLastName().length()
					>= LAST_NAME_LENGTH_TWO))) {
				approximateMatch = false;
			} else {
				approximateMatch = true;
			}
			if (SearchOperation.SEARCH.equals(searchOperation)) {	
				if (!this.offenderRelationshipReportService
						.exceedsMaximumResults(
								offenderRelationshipSearchForm.getLastName(), 
								offenderRelationshipSearchForm.getFirstName(),
								approximateMatch)) {
						offenderRelationshipSummaries
							= this.offenderRelationshipReportService
								.summarizeByName(
										offenderRelationshipSearchForm
											.getLastName(),
										offenderRelationshipSearchForm
											.getFirstName(),
										new Date(), approximateMatch);
				} else {
					mav.addObject(SHOW_ALL_RESULTS_OPTIONS_MODEL_KEY, true);
					Integer maximumResults
						= this.offenderRelationshipReportService
							.getMaximumResults();
					mav.addObject(MAXIMUM_RESULTS_MODEL_KEY, maximumResults);
					offenderRelationshipSummaries = Collections.emptyList();
				}
			} else {
				offenderRelationshipSummaries
					= this.offenderRelationshipReportService.summarizeByName(
							offenderRelationshipSearchForm.getLastName(),
							offenderRelationshipSearchForm.getFirstName(),
							new Date(), approximateMatch);
			}
		} else if (OffenderRelationshipSearchType.OFFENDER_NUMBER
				.equals(offenderRelationshipSearchForm.getType())) {
			offenderRelationshipSummaries
				= this.offenderRelationshipReportService
					.summarizeByOffenderNumber(
						offenderRelationshipSearchForm.getOffenderNumber(),
						new Date());
		} else if (OffenderRelationshipSearchType.SOCIAL_SECURITY_NUMBER
				.equals(offenderRelationshipSearchForm.getType())) {
			offenderRelationshipSummaries
				= this.offenderRelationshipReportService
					.summarizeBySocialSecurityNumber(
							Integer.valueOf(offenderRelationshipSearchForm
								.getSocialSecurityNumber().replace("-", "")),
							new Date());
		} else if (OffenderRelationshipSearchType.BIRTH_DATE
				.equals(offenderRelationshipSearchForm.getType())) {
			offenderRelationshipSummaries
				= this.offenderRelationshipReportService
					.summarizeByBirthDate(offenderRelationshipSearchForm
							.getBirthDate(), new Date());
		} else if (this.getAllowAddressSearch()
				&& OffenderRelationshipSearchType.ADDRESS.equals(
						offenderRelationshipSearchForm.getType())) {
			offenderRelationshipSummaries = this.offenderRelationshipReportService
					.summarizeByAddress(offenderRelationshipSearchForm.getAddressValue(),
							offenderRelationshipSearchForm.getCityName(), offenderRelationshipSearchForm.getStateName(),
							offenderRelationshipSearchForm.getZipCodeValue());
		} else if (OffenderRelationshipSearchType.TELEPHONE_NUMBER.equals(
						offenderRelationshipSearchForm.getType())) {
			offenderRelationshipSummaries = this.offenderRelationshipReportService.
					summarizeByTelephoneNumber(offenderRelationshipSearchForm.getTelephoneNumberValue());			
		} else if (this.getAllowOnlineAccountSearch()
				&& OffenderRelationshipSearchType.ONLINE_ACCOUNT
					.equals(offenderRelationshipSearchForm.getType())) {
			offenderRelationshipSummaries = this.offenderRelationshipReportService
					.summarizeByOnlineAccount(offenderRelationshipSearchForm.getOnlineAccountName());
		} else {
			throw new UnsupportedOperationException(String.format(
					"Search by %s not supported",
					offenderRelationshipSearchForm.getType()));
		}
		mav.addObject(OFFENDER_RELATIONSHIP_SUMMARIES_MODEL_KEY,
				offenderRelationshipSummaries);
		return mav; 
	}
	
	
	/**
	 * Model and view of offender relationship search row action menu.
	 *
	 * @param offender offender
	 * @param relation relation
	 * @param relationIsOffender whether relation is an offender
	 * @return model and view
	 */
	@RequestMapping(value="/offenderRelationshipSearchRowActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView offenderRelationshipSearchRowActionMenu(
			@RequestParam(value = "offender", required = false) 
				final Offender offender,
			@RequestParam(value = "relation", required = false) 
				final Person relation,
			@RequestParam(value = "relationIsOffender", required = false)
				final Boolean relationIsOffender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY,  offender);
		map.addAttribute(RELATION_MODEL_KEY, relation);
		map.addAttribute(RELATION_IS_OFFENDER_MODEL_KEY,
				relationIsOffender);
		map.addAttribute(RELATIONSHIP_EXIST_MODEL_KEY, 
				this.offenderRelationshipReportService.relationshipExists(
						offender, relation));
		map.addAttribute(RELATIONSHIP_MODEL_KEY, 
				this.offenderRelationshipReportService.findRelationship(
						offender, relation));
		return new ModelAndView(
				OFFENDER_RELATIONSHIP_SEARCH_ROW_ACTION_MENU_VIEW_NAME, map);
	}	
	
	/* Helper methods */
	
	// Prepares model and view
	private ModelAndView prepareMav(
			final OffenderRelationshipSearchForm offenderRelationshipSearchForm,
			final OffenderRelationshipOption[] options) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(OPTIONS_MODEL_KEY, options);
		mav.addObject(FORM_MODEL_KEY, offenderRelationshipSearchForm);
		mav.addObject(ALLOW_ADDRESS_SEARCH_TOGGLE_KEY,
				this.getAllowAddressSearch());
		mav.addObject(ALLOW_TELEPHONE_NUMBER_SEARCH_TOGGLE_KEY,
				this.getAllowTelephoneNumberSearch());
		mav.addObject(ALLOW_ONLINE_ACCOUNT_SEARCH_TOGGLE_KEY,
				this.getAllowOnlineAccountSearch());
		return mav;
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplayMav(
			final OffenderRelationshipSearchForm offenderRelationshipSearchForm,
			final OffenderRelationshipOption[] options,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(
				offenderRelationshipSearchForm, options);		
		mav.addObject(BindingResult.MODEL_KEY_PREFIX + FORM_MODEL_KEY, result);
		return mav;
	}
	
	/* Feature toggle lookup helpers. */
	
	// Returns whether address search is allowed
	private boolean getAllowAddressSearch() {
		return this.featureToggles.get(
				MODULE_NAME, ALLOW_ADDRESS_SEARCH_TOGGLE_KEY);
	}
	
	// Returns whether telephone number search is allowed
	private boolean getAllowTelephoneNumberSearch() {
		return this.featureToggles.get(
				MODULE_NAME, ALLOW_TELEPHONE_NUMBER_SEARCH_TOGGLE_KEY);
	}
	
	// Returns whether online account search is allowed
	private boolean getAllowOnlineAccountSearch() {
		return this.featureToggles.get(
				MODULE_NAME, ALLOW_ONLINE_ACCOUNT_SEARCH_TOGGLE_KEY);
	}
	
	/* Init binders */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Relationship.class,
				this.relationshipPropertyEditorFactory
				.createPropertyEditor());
	}
}