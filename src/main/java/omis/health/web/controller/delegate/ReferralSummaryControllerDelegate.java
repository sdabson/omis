package omis.health.web.controller.delegate;

import java.util.Date;
import java.util.Map;

import omis.health.domain.HealthRequest;
import omis.health.exception.HealthRequestFollowsUpMultipleReferralsException;
import omis.health.report.ReferralSummary;
import omis.health.report.ReferralSummaryReportService;

/**
 * Delegate for referral summary controller functionality.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 16, 2014)
 * @since OMIS 3.0
 */
public class ReferralSummaryControllerDelegate {

	private static final String ORIGINAL_REFERRAL_SUMMARY_MODEL_KEY
		= "originalReferralSummary";
	
	private final ReferralSummaryReportService referralSummaryReportService;
	
	/**
	 * Instantiates a delegate for referral summary for controller
	 * functionality.
	 * 
	 * @param referralSummaryReportService report service for referral summaries
	 */
	public ReferralSummaryControllerDelegate(
			final ReferralSummaryReportService referralSummaryReportService) {
		this.referralSummaryReportService = referralSummaryReportService;
	}
	
	/**
	 * Adds original referral from which the action request resulted to the
	 * model map.
	 * 
	 * @param modelMap model map
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the action
	 * request follows up multiple referrals
	 */
	public void addOriginalForActionRequest(
			final Map<String, Object> modelMap,
			final HealthRequest actionRequest, final Date effectiveDate)
					throws HealthRequestFollowsUpMultipleReferralsException {
		ReferralSummary referralSummary = this.referralSummaryReportService
				.findForActionRequest(actionRequest, effectiveDate);
		modelMap.put(ORIGINAL_REFERRAL_SUMMARY_MODEL_KEY, referralSummary);
	}
}