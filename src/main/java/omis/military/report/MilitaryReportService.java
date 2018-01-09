package omis.military.report;

import java.util.List;

import omis.military.domain.MilitaryServiceTerm;
import omis.offender.domain.Offender;

/**
 * Military report service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public interface MilitaryReportService {

	/**
	 * Returns a list of military service term summaries for the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @return list of military service term summaries
	 */
	List<MilitaryServiceTermSummary> 
	findMilitaryServiceTermSummariesByOffender(Offender offender);
	
	/**
	 * Returns a list of military service term note summaries for the specified
	 * military service term.
	 * 
	 * @param serviceTerm military service term
	 * @return list of military service term note summaries
	 */
	List<MilitaryServiceTermNoteSummary> 
	findMilitaryServiceTermNotesByMilitaryServiceTerm(
			MilitaryServiceTerm serviceTerm);
}