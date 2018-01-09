package omis.need.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Criminogenic domain summary report service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 13, 2015)
 * @since OMIS 3.0
 */
public interface NeedDomainSummaryReportService {

	/**
	 * Returns a list of need domain summaries of the specified
	 * need domains for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of need domain summaries
	 */
	List<NeedDomainSummary> summarizeDomainsByOffender(Offender offender);
}