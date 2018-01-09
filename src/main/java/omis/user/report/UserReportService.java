package omis.user.report;

import java.util.List;

/**
 * Report service for users.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 7, 2015)
 * @since OMIS 3.0
 */
public interface UserReportService {

	/**
	 * Summarizes users by name.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @return user summaries by name
	 */
	List<UserSummary> summarizeByName(String lastName, String firstName);
	
	/**
	 * Summarizes users by username.
	 * 
	 * @param username user name
	 * @return user summaries by username
	 */
	List<UserSummary> summarizeByUsername(String username);
}