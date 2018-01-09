package omis.search.report.service;

import omis.search.report.EmployerSearchResult;

import java.util.List;

/** Service for employer searches.
 * @author Yidong Li
 * @version 0.1.0 (Feb 26, 2015)
 * @since OMIS 3.0 */
public interface EmployerSearchService {

	/** Find employer by name.
	 * @param searchCriteria search criteria.
	 * @return employer search result. */
	List<EmployerSearchResult> findEmployerByUnspecified(String searchCriteria);
}
