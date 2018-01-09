package omis.health.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import omis.beans.factory.PropertyEditorFactory;
import omis.health.domain.ExternalReferral;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Controller to display provider billing memo for an external referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 13, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external")
@PreAuthorize("hasRole('ADMIN')" 
		+ " or hasRole('HEALTH_ADMIN')" 
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ProviderBillingMemoExternalReferralController {
	
	/* Report names. */
	
	private static final String 
		PROVIDER_BILLING_MEMO_HWC_VERSION_REPORT_NAME 
			= "/Health/Provider_Billing_Memo_HWC_Version";

	/* Report parameter names. */
	
	private static final String APPOINTMENT_ID_REPORT_PARAM_NAME 
		= "APPOINTMENT_ID";

	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("externalReferralPropertyEditorFactory")
	private PropertyEditorFactory externalReferralPropertyEditorFactory;
	
	/* Constructor. */
	
	/** Instantiates a controller to display provider billing memo for external 
	 * referrals. */
	public ProviderBillingMemoExternalReferralController() {
		// Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Returns the report for the specified external referral.
	 * 
	 * @param externalReferral external referral
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/providerBillingMemo.rtf",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('REFERRAL_CENTER')")
	public ResponseEntity<byte []> reportSTGDetails(
			@RequestParam(value = "externalReferral", required = true)
			final ExternalReferral externalReferral,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(APPOINTMENT_ID_REPORT_PARAM_NAME,
				Long.toString(externalReferral
						.getOffenderAppointmentAssociation().getAppointment()
						.getId()));
		byte[] doc = this.reportRunner.runReport(
				PROVIDER_BILLING_MEMO_HWC_VERSION_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Init binder. */
	
	/** Registers property editors. */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(ExternalReferral.class,
				this.externalReferralPropertyEditorFactory
					.createPropertyEditor());
	}
}
