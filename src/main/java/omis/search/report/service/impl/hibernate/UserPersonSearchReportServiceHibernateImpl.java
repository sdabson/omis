package omis.search.report.service.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import omis.search.report.UserSearchResult;
import omis.search.report.service.UserPersonSearchReportService;
import omis.search.util.PersonRegexUtility;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** User search report service.
 * @author Ryan Johns
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0 */
public class UserPersonSearchReportServiceHibernateImpl
		extends AbstractPersonSearchReportServiceHibernateImpl<UserSearchResult>
		implements UserPersonSearchReportService {

	private static final String FIND_BY_FIRST_LAST_NAME_SEARCH_QUERY_NAME =
			"findUsersNamesByFirstLastNameSearch";

	private static final String[] FIND_BY_FIRST_LAST_NAME_SEARCH_PARAMS =
	{"name1", "name2"};

	private static final String
	FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_QUERY_NAME =
		"findUsersNameByFirstMiddleLastNameSearch";

	private static final String[] FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_PARAMS =
	{"first", "middle", "last"};

	private static final String FIND_BY_NAME_SEARCH_CRITERIA =
			"findUserNamesByLastNameSearchCriteria";

	private static final String FIND_BY_NAME_SEARCH_CRITERIA_PARAM =
		"name";


	private static final String FIND_BY_USERNAME_SEARCH_QUERY =
			"findUserNamesByUsernameSearchCriteria";

	private static final String FIND_BY_USERNAME_SEARCH_QUERY_PARAM =
			"username";

	private static final String FIND_PERSON_SEARCH_BY_ID =
			"findUserSearchById";

	private static final String FIND_PERSON_SEARCH_BY_ID_PARAM = "id";

	/** constructor.
	 * @param sessionFactory session factory. */
	public UserPersonSearchReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	/** {@inheritDoc} */
	@Override
	public UserSearchResult findById(final Long id) {
		final Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PERSON_SEARCH_BY_ID);

		q.setParameter(FIND_PERSON_SEARCH_BY_ID_PARAM, id);

		return (UserSearchResult) q.uniqueResult();
	}

	@Override
	public String getFindByFirstLastNameSearchQuery() {
		return FIND_BY_FIRST_LAST_NAME_SEARCH_QUERY_NAME;
	}

	@Override
	public String[] getFindByFirstLastNameSearchParams() {
		return FIND_BY_FIRST_LAST_NAME_SEARCH_PARAMS;
	}

	@Override
	public String getFindByFirstMiddleLastNameSearchQuery() {
		return FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_QUERY_NAME;
	}

	@Override
	public String[] getFindByFirstMiddleLastNameSearchParams() {
		return FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_PARAMS;
	}


	@Override
	public String getFindPersonNameByNameSearchQuery() {
		return FIND_BY_NAME_SEARCH_CRITERIA;
	}

	@Override
	public String getFindPersonNameByNameSearchParam() {
		return FIND_BY_NAME_SEARCH_CRITERIA_PARAM;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserSearchResult> findPersonNamesByUsernameSearch(
			final String username) {
		final Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_USERNAME_SEARCH_QUERY);
		q.setParameter(FIND_BY_USERNAME_SEARCH_QUERY_PARAM,
				username);

		@SuppressWarnings("unchecked")
		final
		List<UserSearchResult> result = q.list();
		return result;
	}



	/** {@inheritDoc} */
	@Override
	public List<UserSearchResult> findUserPersonNamesByUnspecified(
			final String searchCriteria) {
		List<UserSearchResult> result = new ArrayList<UserSearchResult>();

		if (PersonRegexUtility.isUserName(searchCriteria)) {
			result = this.findPersonNamesByUsernameSearch(searchCriteria);
		} else {
			result = super.findPersonNamesByUnspecified(searchCriteria);
		}
		return result;
	}
}
