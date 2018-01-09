package omis.health.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.facility.domain.Facility;
import omis.health.report.ExternalReferralRequestReportService;
import omis.health.report.ExternalReferralRequestSummary;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

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

/**
 * Controller to report external referral request summaries. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 1, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external/request")
@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ReportExternalReferralRequestSummaryController {
	
	/* Views. */
	
	private static final String LIST_VIEW_NAME
		= "health/referral/external/request/summary/list";
	
	/* Model keys. */
	
	private static final String SUMMARIES_MODEL_KEY = "summaries";

	private static final String FACILITY_MODEL_KEY = "facility";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("externalReferralRequestReportService")
	private ExternalReferralRequestReportService
	externalReferralRequestReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructors. */

	/** Instantiates a controller for reporting external referral requests. */
	public ReportExternalReferralRequestSummaryController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen of external referral request summaries by facility or
	 * offender if provided.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @return screen of external referral request summaries 
	 */
	@RequestMapping(value = "/summary/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
		+ " or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_VIEW')")
	public ModelAndView list(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "offender", required = false)
				final Offender offender) {
		final List<ExternalReferralRequestSummary> summaries;
		if (offender != null) {
			summaries = this.externalReferralRequestReportService
					.findByOffender(offender);
		} else {
			summaries = this.externalReferralRequestReportService
					.findByFacility(facility);
		}
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(SUMMARIES_MODEL_KEY, summaries);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		if (offender != null) {
			this.offenderSummaryModelDelegate.add(mav.getModel(), offender);
		}
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editor factories.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}