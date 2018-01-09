package omis.placement.web.controller;

import java.beans.PropertyEditor;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.locationterm.report.LocationTermSummary;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.placement.report.AllowedCorrectionalStatusChangeSummary;
import omis.placement.report.LocationTermChangeActionSummary;
import omis.placement.report.PlacementReportService;
import omis.placement.report.PlacementSummary;
import omis.placement.report.PlacementTermChangeActionSummary;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.report.CorrectionalStatusTermSummary;
import omis.supervision.report.SupervisoryOrganizationTermSummary;
import omis.util.DateManipulator;

/**
 * Controller for placements. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Nov 17, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/placement")
@PreAuthorize("hasRole('USER')")
public class PlacementController {

	/* View names. */
	
	private static final String HOME_VIEW_NAME = "placement/home";

	private static final String PLACEMENT_ACTION_MENU_VIEW_NAME
		= "placement/includes/placementActionMenu";
	
	private static final String LOCATION_TERM_SUMMARY_LIST_VIEW_NAME
		= "placement/includes/locationTermSummaryList";
	
	/* Model keys. */
	
	private static final String
	SUPERVISORY_ORGANIZATION_TERM_SUMMARIES_MODEL_KEY
		= "supervisoryOrganizationTermSummaries";

	private static final String CORRECTIONAL_STATUS_TERM_SUMMARIES_MODEL_KEY
		= "correctionalStatusTermSummaries";

	private static final String PLACEMENT_SUMMARY_MODEL_KEY
		= "placementSummary";

	private static final String EFFECTIVE_DATE_MODEL_KEY = "effectiveDate";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String ALLOWED_CORRECTIONAL_STATUS_CHANGE_SUMMARIES
		= "allowedCorrectionalStatusChangeSummaries";

	private static final String EFFECTIVE_DATE = "effectiveDate";

	private static final String FROM_CORRECTIONAL_STATUS_MODEL_KEY
		= "fromCorrectionalStatus";

	private static final String LOCATION_TERM_SUMMARIES_MODEL_KEY
		= "locationTermSummaries";

	private static final String CORRECTIONAL_STATUS_CHANGE_ALLOWED_MODEL_KEY
		= "correctionalStatusChangeAllowed";

	private static final String LOCATION_TERM_CHANGE_ACTION_SUMMARIES_MODEL_KEY
		= "locationTermChangeActionSummaries";

	private static final String HAS_ACTIVE_PLACEMENT_TERM_MODEL_KEY
		= "hasActivePlacementTerm";

	private static final String
	PLACEMENT_TERM_CHANGE_ACTIONS_FOR_SUPERVISORY_ORGANIZATION_CHANGE_MODEL_KEY
		= "placementTermChangeActionsForSupervisoryOrganizationChange";

	private static final String FROM_SUPERVISORY_ORGANIZATION_MODEL_KEY
		= "fromSupervisoryOrganization";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("placementReportService")
	private PlacementReportService placementReportService;
	
	/* Property editor factory. */
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermPropertyEditorFactory")
	private PropertyEditorFactory
	supervisoryOrganizationTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("correctionalStatusPropertyEditorFactory")
	private PropertyEditorFactory correctionalStatusPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructors. */
	
	/** Instantiates a controller for placements. */
	public PlacementController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays placement home page.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param defaultEffectiveDate default effective date
	 * @param defaultEffectiveTime default effective time
	 * @return model and view to index page
	 */
	@RequestMapping("/home.html")
	@PreAuthorize("hasRole('PLACEMENT_PROFILE_VIEW') or hasRole('ADMIN')")
	public ModelAndView home(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "startDate", required = false)
				final Date startDate,
			@RequestParam(value = "endDate", required = false)
				final Date endDate,
			@RequestParam(value = "effectiveDate", required = false)
				final Date defaultEffectiveDate,
			@RequestParam(value = "effectiveTime", required = false)
				final String defaultEffectiveTimeText) {
		Date effectiveDate;
		if (defaultEffectiveDate != null) {
			effectiveDate = defaultEffectiveDate;
		} else {
			effectiveDate = new Date();
		}
		Date effectiveTime = this.parseTimeText(defaultEffectiveTimeText);
		if (effectiveTime != null) {
			effectiveDate = DateManipulator
					.getDateAtTimeOfDay(effectiveDate, effectiveTime);
		}
		PlacementSummary placementSummary = this.placementReportService
				.findPlacementSummary(offender, effectiveDate);
		List<SupervisoryOrganizationTermSummary>
			supervisoryOrganizationTermSummaries
				= this.placementReportService
					.findSupervisoryOrganizationTermSummaries(
							offender, startDate, endDate, effectiveDate);
		List<CorrectionalStatusTermSummary>
			correctionalStatusTermSummaries
				= this.placementReportService
					.findCorrectionalStatusTermSummaries(
							offender, startDate, endDate, effectiveDate);
		ModelAndView mav = new ModelAndView(HOME_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(PLACEMENT_SUMMARY_MODEL_KEY, placementSummary);
		mav.addObject(SUPERVISORY_ORGANIZATION_TERM_SUMMARIES_MODEL_KEY,
				supervisoryOrganizationTermSummaries);
		mav.addObject(CORRECTIONAL_STATUS_TERM_SUMMARIES_MODEL_KEY,
				correctionalStatusTermSummaries);
		mav.addObject(EFFECTIVE_DATE_MODEL_KEY, effectiveDate);
		if (placementSummary != null
				&& placementSummary.getCorrectionalStatusEndDate() != null) {
			mav.addObject(CORRECTIONAL_STATUS_CHANGE_ALLOWED_MODEL_KEY, false);
		} else {
			mav.addObject(CORRECTIONAL_STATUS_CHANGE_ALLOWED_MODEL_KEY, true);
		}
		this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		return mav;
	}
	
	/**
	 * Action menu for placement.
	 * 
	 * @param offender offender
	 * @param correctionalStatus correctional status of offender
	 * @param effectiveDate effective date
	 * @param effectiveTime effective time
	 * @param correctionalStatusChangeAllowed whether correctional status change
	 * is allowed
	 * @param supervisoryOrganization supervisory organization
	 * @return returns action menu for placement
	 */
	@RequestMapping("/placementActionMenu.html")
	public ModelAndView showPlacementActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "correctionalStatus", required = false)
				final CorrectionalStatus correctionalStatus,
			@RequestParam(value = "effectiveDate", required = true)
				final Date effectiveDate,
			@RequestParam(value = "effectiveTime", required = true)
				final String effectiveTimeText,
			@RequestParam(value = "correctionalStatusChangeAllowed",
					required = false)
				final Boolean correctionalStatusChangeAllowed,
			@RequestParam(value = "supervisoryOrganization", required = false)
				final SupervisoryOrganization supervisoryOrganization) {
		List<AllowedCorrectionalStatusChangeSummary>
		allowedCorrectionalStatusChangeSummaries
			= this.placementReportService
				.findAllowedChangeSummariesFromCorrectionalStatus(
						correctionalStatus);
		List<LocationTermChangeActionSummary> locationTermChangeActionSummaries
			= this.placementReportService
				.findLocationTermChangeActionSummariesForCorrectionalStatus(
						correctionalStatus);
		List<PlacementTermChangeActionSummary>
			placementTermChangeActionsForSupervisoryOrganizationChange
			= this.placementReportService
				.findPlacementTermChangeActionSummariesForSupervisoryOrganizationChange(
						correctionalStatus, supervisoryOrganization);
		Date effectiveTime = this.parseTimeText(effectiveTimeText);
		Date effectiveDateTime = DateManipulator.getDateAtTimeOfDay(
				effectiveDate, effectiveTime);
		boolean hasActivePlacementTerm = this.placementReportService
				.hasActivePlacementTerm(offender, effectiveDateTime);
		ModelAndView mav = new ModelAndView(PLACEMENT_ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(EFFECTIVE_DATE, effectiveDateTime);
		mav.addObject(ALLOWED_CORRECTIONAL_STATUS_CHANGE_SUMMARIES,
				allowedCorrectionalStatusChangeSummaries);
		mav.addObject(FROM_CORRECTIONAL_STATUS_MODEL_KEY, correctionalStatus);
		mav.addObject(FROM_SUPERVISORY_ORGANIZATION_MODEL_KEY,
				supervisoryOrganization);
		mav.addObject(CORRECTIONAL_STATUS_CHANGE_ALLOWED_MODEL_KEY,
				correctionalStatusChangeAllowed);
		mav.addObject(LOCATION_TERM_CHANGE_ACTION_SUMMARIES_MODEL_KEY,
				locationTermChangeActionSummaries);
		mav.addObject(HAS_ACTIVE_PLACEMENT_TERM_MODEL_KEY,
				hasActivePlacementTerm);
		mav.addObject(
		PLACEMENT_TERM_CHANGE_ACTIONS_FOR_SUPERVISORY_ORGANIZATION_CHANGE_MODEL_KEY,
				placementTermChangeActionsForSupervisoryOrganizationChange);
		return mav;
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Lists location term summaries for supervisory organization term. 
	 * 
	 * @param supervisoryOrganizationTerm supervisory organization term
	 * @param effectiveDate effective date
	 * @return model and view to list location term summaries for supervisory
	 * organization term
	 */
	@RequestMapping("/locationTermSummaries.html")
	public ModelAndView listLocationTermSummaries(
			@RequestParam(
					value = "supervisoryOrganizationTerm", required = true)
				final SupervisoryOrganizationTerm supervisoryOrganizationTerm,
			@RequestParam(value = "effectiveDate", required = true)
				final Date effectiveDate) {
		List<LocationTermSummary> locationTermSummaries
			= this.placementReportService
				.findLocationTermSummariesDuringSupervisoryOrganizationTerm(
						supervisoryOrganizationTerm, effectiveDate);
		ModelAndView mav = new ModelAndView(
				LOCATION_TERM_SUMMARY_LIST_VIEW_NAME);
		mav.addObject(LOCATION_TERM_SUMMARIES_MODEL_KEY,
				locationTermSummaries);
		return mav;
	}
	
	/* Helper methods. */
	
	// Parses time
	private Date parseTimeText(final String timeText) {
		if (timeText != null && !timeText.isEmpty()) {
			PropertyEditor propertyEditor = this.datePropertyEditorFactory
					.createCustomTimeOnlyEditor(true);
			propertyEditor.setAsText(timeText);
			return (Date) propertyEditor.getValue();
		} else {
			return null;
		}
	}
	
	/* Init binders. */
	
	/** Registers property editors. */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(SupervisoryOrganizationTerm.class,
				this.supervisoryOrganizationTermPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(CorrectionalStatus.class,
				this.correctionalStatusPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(SupervisoryOrganization.class,
				this.supervisoryOrganizationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.datePropertyEditorFactory
					.createCustomDateOnlyEditor(true));
	}
}