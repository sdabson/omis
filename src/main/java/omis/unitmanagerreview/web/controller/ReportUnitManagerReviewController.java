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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.unitmanagerreview.domain.UnitManagerReview;
import omis.unitmanagerreview.report.UnitManagerReviewSummary;
import omis.unitmanagerreview.report.UnitManagerReviewSummaryReportService;

/**
 * Controller for reporting unit manager reviews.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 29, 2018)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/unitManagerReview")
public class ReportUnitManagerReviewController {

	/* View names. */
	
	private static final String VIEW_NAME = "unitManagerReview/list";
	
	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME = 
			"unitManagerReview/includes/unitManagerReviewsActionMenu";

	/* Model keys. */
	
	private static final String UNIT_MANAGER_REVIEW_SUMMARIES_MODEL_KEY = 
			"unitManagerReviewSummaries";
	
	private static final String UNIT_MANAGER_REVIEW_MODEL_KEY = 
			"unitManagerReview";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("unitManagerReviewPropertyEditorFactory")
	private PropertyEditorFactory unitManagerReviewPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("unitManagerReviewSummaryReportService")
	private UnitManagerReviewSummaryReportService 
			unitManagerReviewSummaryReportService;
	
	/* Helpers. */

	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructors. */
	
	/** Instantiates controller for unit manager reviews. */
	public ReportUnitManagerReviewController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen that lists unit manager reviews
	 * 
	 * @return screen that lists unit manager reviews
	 */
	@PreAuthorize("hasRole('UNIT_MANAGER_REVIEW_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		List<UnitManagerReviewSummary> unitManagerReviews;
			unitManagerReviews = this.unitManagerReviewSummaryReportService
					.findUnitManagerReviewSummariesByOffender(offender);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(UNIT_MANAGER_REVIEW_SUMMARIES_MODEL_KEY, 
				unitManagerReviews);
		this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for unit manager reviews.
	 * 
	 * @param unitManagerReview unit manager review
	 * @return action menu for unit manager reviews
	 */
	@RequestMapping(value = "/unitManagerReviewsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "unitManagerReview", required = false)
				final UnitManagerReview unitManagerReview,
			@RequestParam(value = "offender", required = false)
				final Offender offender) {
		if (unitManagerReview == null && offender == null) {
			throw new IllegalArgumentException(
					"Unit manager review or offender required.");
		}
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		if (unitManagerReview != null) {
			mav.addObject(UNIT_MANAGER_REVIEW_MODEL_KEY, 
					unitManagerReview);
		}
		if (offender != null) {
			mav.addObject(OFFENDER_MODEL_KEY, offender);
		}
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
		binder.registerCustomEditor(UnitManagerReview.class, 
				this.unitManagerReviewPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
	}
}
