package omis.presentenceinvestigation.report;

import java.util.List;

import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.user.domain.UserAccount;

/** Report service for presentinvestigation request summary.
 * @author Ryan Johns
 * @author Annie Jacques
 * @version 0.1.1 (May 19, 2017)
 * @since OMIS 3.0 */
public interface PresentenceInvestigationRequestSummaryReportService {
	
	/** Finds presentence investigation request summaries by staff member.
	 * @param user - user.
	 * @return list of presentence investigation summaries. */
	List<PresentenceInvestigationRequestSummary> 
		findPresentenceInvestigationRequestSummariesByUser(
				UserAccount user);
	
	/**
	 * Returns a list of presentence investigation request summaries by offender
	 * @param offender - Person
	 * @return list of presentence investigation request summaries by offender
	 */
	List<PresentenceInvestigationRequestSummary> 
		findPresentenceInvestigationRequestSummariesByOffender(
				Person offender);
	
	/**
	 * Returns a presentence investigation request summary by specified
	 * presentence investigation request
	 * @param presentenceInvestigationRequest - presentence investigation 
	 * request
	 * @return presentence investigation request summary by specified
	 * presentence investigation request
	 */
	PresentenceInvestigationRequestSummary summarize(
			PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	

}
