package omis.hearing.report;

import java.util.HashMap;
import java.util.List;

import omis.hearing.domain.Hearing;
import omis.offender.domain.Offender;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.DisciplinaryCodeViolation;

/**
 * ViolationSummaryReportService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 18, 2017)
 *@since OMIS 3.0
 *
 */
public interface ViolationSummaryReportService {
	
	/**
	 * Returns a list of unresolved Violation Summaries by specified Offender
	 * @param offender - Offender
	 * @return List of unresolved ViolationSummaries by specified Offender
	 */
	List<ViolationSummary> findUnresolvedViolationSummariesByOffender(
			Offender offender);
	
	/**
	 * Returns a HashMap of a HearingSummaries as a key and a List of that
	 * key's Hearing's Violation Summaries as their value for the specified Offender
	 * @param offender - Offender
	 * @return HashMap of a HearingSummaries as a key and a List of that
	 * key's Hearing's Violation Summaries as their value
	 */
	HashMap<HearingSummary, List<ViolationSummary>>
		findScheduledViolationSummariesByOffender(Offender offender);
	
	/**
	 * Returns a list of Violation Summaries of violations that are associated
	 * with a Hearing by specified Hearing
	 * @param hearing - Hearing
	 * @return List of Violation Summaries of violations that are associated
	 * with a Hearing by specified Hearing
	 */
	List<ViolationSummary> findAllViolationSummariesByHearing(
			Hearing hearing);
	
	/**
	 * Returns a list of unresolved disciplinary violation summaries by specified
	 * Offender
	 * @param offender - Offender
	 * @return List of unresolved disciplinary violation summaries by specified
	 * Offender
	 */
	List<ViolationSummary> findUnresolvedDisciplinaryViolationSummariesByOffender(
			Offender offender);
	
	/**
	 * Returns a list of unresolved condition violation summaries by specified
	 * Offender
	 * @param offender - Offender
	 * @return List of unresolved condition violation summaries by specified
	 * Offender
	 */
	List<ViolationSummary> findUnresolvedConditionViolationSummariesByOffender(
			Offender offender);
	
	/**
	 * Returns a list of resolved violation summaries by specified Offender
	 * @param offender - Offender
	 * @return List of resolved violation summaries by specified Offender
	 */
	List<ViolationSummary> findResolvedViolationSummariesByOffender(
			Offender offender);
	
	/**
	 * Returns a single ViolationSummary of specified ConditionViolation
	 * @param conditionViolation - ConditionViolation to summarize
	 * @return ViolationSummary of specified ConditionViolation
	 */
	ViolationSummary summarize(ConditionViolation conditionViolation);
	
	/**
	 * Returns a single ViolationSummary of specified DisciplinaryCodeViolation
	 * @param disciplinaryCodeViolation - DisciplinaryCodeViolation to summarize
	 * @return ViolationSummary of specified DisciplinaryCodeViolation
	 */
	ViolationSummary summarize(DisciplinaryCodeViolation
			disciplinaryCodeViolation);
	
}
