package omis.user.report;

import omis.person.domain.Person;

/** Profile item for user account.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 18, 2016)
 * @since OMIS 3.0 */
public interface UserAccountProfileItemReportService {
	/** Finds user account count by user and date.
	 * @param user - user.
	 * @return count. */
	Integer findUserAccountCountByUser(Person user);
}
