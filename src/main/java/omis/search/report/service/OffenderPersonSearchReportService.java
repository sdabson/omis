package omis.search.report.service;

import java.util.List;

import omis.search.report.OffenderSearchResult;

/** Offender person search report service.
 * @author Ryan Johns
 * @version 0.1.0 (Jan 7, 2013)
 * @since OMIS 3.0 */
public interface OffenderPersonSearchReportService {

	/** find offender by id.
	 * @param id id.
	 * @return person search result. */
	OffenderSearchResult findById(Long id);

	/** find offender names by unspecified search criteria.
	 * @param searchCriteria search criteria string.
	 * @return list of offender names. */
	List<OffenderSearchResult> findPersonNamesByUnspecified(
			final String searchCriteria);

	/** Returns offender names with specified name search criteria.
	 * @param name1 first or last
	 * @param name2 first or last
	 * @return list of offender names. */
	List<OffenderSearchResult> findPersonNamesByNameSearch(final String name1,
			final String name2);

	/** Returns offender names with specified first middle last names.
	 * @param first first name.
	 * @param middle middle name.
	 * @param last last name.
	 * @return list of offender names. */
	List<OffenderSearchResult> findPersonNamesByNameSearch(final String first,
			final String middle, final String last);

	/** Returns offender names with last name equaling search criteria.
	 * @param name name.
	 * @return list of offender names. */
	List<OffenderSearchResult> findPersonNamesByLastName(final String name);
}
