package omis.search.report.service;

import java.util.List;

import omis.search.report.UserSearchResult;

/** Offender person search report service.
 * @author Ryan Johns
 * @version 0.1.0 (Jan 7, 2013)
 * @since OMIS 3.0 */
public interface UserPersonSearchReportService {

	/** find user by id.
	 * @param id id.
	 * @return user search result. */
	UserSearchResult findById(Long id);

	/** find users' names by unspecified search criteria.
	 * @param searchCriteria search criteria string.
	 * @return list of offender names. */
	List<UserSearchResult> findPersonNamesByUnspecified(
			final String searchCriteria);

	/** Returns users' names with specified name search criteria.
	 * @param name1 first or last
	 * @param name2 first or last
	 * @return list of users' names. */
	List<UserSearchResult> findPersonNamesByNameSearch(final String name1,
			final String name2);

	/** Returns users' names with specified first middle last names.
	 * @param first first name.
	 * @param middle middle name.
	 * @param last last name.
	 * @return list of users' names. */
	List<UserSearchResult> findPersonNamesByNameSearch(final String first,
			final String middle, final String last);

	/** Returns users' names with last name equaling search criteria.
	 * @param name name.
	 * @return list of users' names. */
	List<UserSearchResult> findPersonNamesByLastName(final String name);

	/** Returns user person names with first last, or username search criteria.
	 * @param searchCriteria search criteria.
	 * @return list of person names. */
	List<UserSearchResult> findUserPersonNamesByUnspecified(
			final String searchCriteria);

	/** Returns person names with associated username search criteria.
	 * @param username username.
	 * @return list of person names. */
	List<UserSearchResult> findPersonNamesByUsernameSearch(
			final String username);
}
