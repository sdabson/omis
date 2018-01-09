package omis.courtdocument.report;

import java.util.List;

import omis.offender.domain.Offender;

/** Report service for court case document summaries.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 30, 2015)
 * @since OMIS 3.0 */
public interface CourtCaseDocumentAssociationSummaryReportService {
	/** Finds court case documents by offender.
	 * @param offender - offender. 
	 * @return list of court case document summaries. */
	List<CourtCaseDocumentAssociationSummary> findByOffender(Offender offender);
}
