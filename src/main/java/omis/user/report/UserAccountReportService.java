package omis.user.report;

import java.util.List;

/**
 * Report service for user accounts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 28, 2014)
 * @since OMIS 3.0
 */
public interface UserAccountReportService {

	/**
	 * Returns user account search results matching query.
	 * 
	 * @param query query
	 * @return user account search results matching query
	 */
	List<UserAccountSearchResult> searchForUserAccount(String query);
}