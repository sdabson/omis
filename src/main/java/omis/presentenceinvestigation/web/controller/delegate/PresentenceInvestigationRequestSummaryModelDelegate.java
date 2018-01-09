package omis.presentenceinvestigation.web.controller.delegate;

import java.util.Map;


import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummary;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummaryReportService;

/**
 * PresentenceInvestigationRequestSummaryHeaderDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 27, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestSummaryModelDelegate {
	
	private static final String SUMMARY_MODEL_KEY = "summary";
	
	private final PresentenceInvestigationRequestSummaryReportService
			presentenceInvestigationRequestSummaryReportService;

	/**
	 * @param reportService
	 */
	public PresentenceInvestigationRequestSummaryModelDelegate(
			final PresentenceInvestigationRequestSummaryReportService
				presentenceInvestigationRequestSummaryReportService) {
		this.presentenceInvestigationRequestSummaryReportService =
				presentenceInvestigationRequestSummaryReportService;
	}
	
	public void add(final Map<String, Object> modelMap,
			final PresentenceInvestigationRequest
			presentenceInvestigationRequest){
		
		PresentenceInvestigationRequestSummary summary =
				this.presentenceInvestigationRequestSummaryReportService
				.summarize(presentenceInvestigationRequest);
		
		modelMap.put(SUMMARY_MODEL_KEY, summary);
	}

}
