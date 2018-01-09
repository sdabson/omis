package omis.document.report;

import java.util.List;

import omis.offender.domain.Offender;

/** Report service for document summary.
 * @author Ryan Johns
 * @version 0.1.0 (Dec 9, 2015)
 * @since OMIS 3.0 */
public interface DocumentSummaryReportService {
	/** Lists documents.
	 * @param offender - offender.
	 * @return list of documents. */
	List<DocumentSummary> findByOffender(Offender offender);
}
