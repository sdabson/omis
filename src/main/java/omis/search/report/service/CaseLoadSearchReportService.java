package omis.search.report.service;

import java.util.List;

import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.search.report.CaseLoadSearchResult;

/** Search caseloads service.
 * @author Ryan Johns (Nov 19, 2013)
 * @since OMIS 3.0 */
public interface CaseLoadSearchReportService {
	/** find by title.
	 * @param title title of caseload.
	 * @return caseLoadSearchResults case load search results. */
	List<CaseLoadSearchResult> findByTitle(String title);

	/** find by description.
	 * @param description description of caseload.
	 * @return caseLoadSearchResults case load search results. */
	List<CaseLoadSearchResult> findByDescription(String description);

	/** find by offender assignments.
	 * @param offender offender assignee of caseload.
	 * @return caseLoadSearchResults> case load search results. */
	List<CaseLoadSearchResult> findByOffender(Offender offender);

	/** find by case worker.
	 * @param person case worker.
	 * @return caseLoadSearchResults> case load search results. */
	List<CaseLoadSearchResult> findByCaseWorker(Person person);
}
