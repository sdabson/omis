package omis.search.report.service.impl.hibernate;

import omis.search.report.PersonSearchResult;
import omis.search.report.service.PersonSearchReportService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Implementation for person search service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 24, 2013)
 * @since OMIS 3.0 */
public class PersonSearchReportServiceHibernateImpl
	extends AbstractPersonSearchReportServiceHibernateImpl<PersonSearchResult>
	implements PersonSearchReportService {

	private static final String
	FIND_BY_FIRST_LAST_NAME_SEARCH_QUERY_NAME =
			"findAllNameByFirstLastNameSearch";
	private static final String[] FIND_BY_FIRST_LAST_NAME_SEARCH_PARAMS =
	{"name1" , "name2"};

	private static final String
	FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_QUERY_NAME =
			"findAllNameByFirstMiddleLastSearch";

	private static final String[] FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_PARAMS =
	{"first" , "middle" , "last"};

	private static final String FIND_PERSON_NAME_BY_NAME_SEARCH_QUERY =
			"findNameByLastNameSearch";

	private static final String FIND_PERSON_NAME_BY_NAME_SEARCH_PARAM =
			"name";


	private static final String FIND_PERSON_SEARCH_BY_ID =
			"findPersonSearchById";

	private static final String FIND_PERSON_SEARCH_BY_ID_PARAM = "id";

	/** constructor.
	 * @param sessionFactory session factory. */
	public PersonSearchReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/** {@inheritDoc} */
	@Override
	public PersonSearchResult findById(final Long id) {
		final Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PERSON_SEARCH_BY_ID);

		q.setParameter(FIND_PERSON_SEARCH_BY_ID_PARAM, id);

		return (PersonSearchResult) q.uniqueResult();
	}



	/** {@inheritDoc} */
	@Override
	public String getFindByFirstLastNameSearchQuery() {
		return FIND_BY_FIRST_LAST_NAME_SEARCH_QUERY_NAME;
	}

	/** {@inheritDoc} */
	@Override
	public String[] getFindByFirstLastNameSearchParams() {
		return FIND_BY_FIRST_LAST_NAME_SEARCH_PARAMS;
	}

	/** {@inheritDoc} */
	@Override
	public String getFindByFirstMiddleLastNameSearchQuery() {
		return FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_QUERY_NAME;
	}

	/** {@inheritDoc} */
	@Override
	public String[] getFindByFirstMiddleLastNameSearchParams() {
		return FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_PARAMS;
	}

	/** {@inheritDoc} */
	@Override
	public String getFindPersonNameByNameSearchQuery() {
		return FIND_PERSON_NAME_BY_NAME_SEARCH_QUERY;
	}

	/** {@inheritDoc} */
	@Override
	public String getFindPersonNameByNameSearchParam() {
		return FIND_PERSON_NAME_BY_NAME_SEARCH_PARAM;
	}

}

