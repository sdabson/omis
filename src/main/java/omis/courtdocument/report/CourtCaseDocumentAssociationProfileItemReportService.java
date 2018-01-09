package omis.courtdocument.report;

import omis.offender.domain.Offender;

/** Profile item report service for court case documents.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 5, 2016)
 * @since OMIS 3.0 */
public interface CourtCaseDocumentAssociationProfileItemReportService {
	/** Finds court case document count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findCourtCaseDocumentCountByOffender(Offender offender);
}
