package omis.substanceuse.report;

import java.util.List;

import omis.offender.domain.Offender;
import omis.substanceuse.domain.SubstanceUse;

/**
 * Substance use summary service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 28, 2016)
 * @since OMIS 3.0
 */
public interface SubstanceUseSummaryService {
	
	/**
	 * Returns substance use summaries for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of substance use summaries
	 */
	List<SubstanceUseSummary> findUseSummaries(Offender offender);
	
	/**
	 * Returns use term summaries for the specified substance use.
	 * 
	 * @param substanceUse substance use
	 * @return list of use term summaries
	 */
	List<UseTermSummary> findUseTermSummaries(SubstanceUse substanceUse);
	
	/**
	 * Returns use affirmation summaries for the specified substance use.
	 * 
	 * @param substanceUse substance use
	 * @return list use affirmation summaries
	 */
	List<UseAffirmationSummary> findUseAffirmationSummaries(
			SubstanceUse substanceUse);
}