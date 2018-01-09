package omis.search.report.service.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import omis.search.report.OffenderSearchResult;
import omis.search.report.service.OffenderPersonSearchReportService;
import omis.search.util.PersonRegexUtility;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Offender search report service.
 * @author Ryan Johns
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0 */
public class OffenderPersonSearchReportServiceHibernateImpl
	extends AbstractPersonSearchReportServiceHibernateImpl<OffenderSearchResult>
	implements OffenderPersonSearchReportService {

	private static final String
	FIND_BY_FIRST_LAST_NAME_SEARCH_QUERY_NAME =
			"findAllOffenderNameByFirstLastNameSearch";
	private static final String[] FIND_BY_FIRST_LAST_NAME_SEARCH_PARAMS =
	{"name1" , "name2"};

	private static final String
	FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_QUERY_NAME =
			"findAllOffenderNameByFirstMiddleLastSearch";

	private static final String[] FIND_BY_FIRST_MIDDLE_LAST_NAME_SEARCH_PARAMS =
	{"first" , "middle" , "last"};

	private static final String FIND_PERSON_NAME_BY_OFFENDER_NUMBER =
			"findOffenderNameByOffenderNumberSearch";

	private static final String FIND_PERSON_NAME_BY_OFFENDER_NUMBER_PARAMS =
			"offenderNumber";

	private static final String FIND_PERSON_NAME_BY_NAME_SEARCH_QUERY =
			"findOffenderNamesByLastNameSearch";

	private static final String FIND_PERSON_NAME_BY_NAME_SEARCH_PARAM =
			"name";

	private static final String FIND_PERSON_SEARCH_BY_ID =
			"findOffenderSearchById";

	private static final String FIND_PERSON_SEARCH_BY_ID_PARAM = "id";

	/** constructor.
	 * @param sessionFactory session factory. */
	public OffenderPersonSearchReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	/** {@inheritDoc} */
	@Override
	public OffenderSearchResult findById(final Long id) {
		final Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PERSON_SEARCH_BY_ID);

		q.setParameter(FIND_PERSON_SEARCH_BY_ID_PARAM, id);

		return (OffenderSearchResult) q.uniqueResult();
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

	/** Returns person names with associated offender search.
	 * @param offenderNumber offender number string.
	 * @return list of person names. */
	public List<OffenderSearchResult> findPersonNamesByOffenderNumberSearch(
			final Long offenderNumber) {
		final Query q = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_PERSON_NAME_BY_OFFENDER_NUMBER);

		q.setLong(FIND_PERSON_NAME_BY_OFFENDER_NUMBER_PARAMS, offenderNumber);

		@SuppressWarnings("unchecked")
		final
		List<OffenderSearchResult> result = q.list();

		return result;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSearchResult> findPersonNamesByUnspecified(
			final String searchCriteria) {
		List<OffenderSearchResult> result =
				new ArrayList<OffenderSearchResult>();

		if (PersonRegexUtility.isOffenderNumber(searchCriteria)) {
			result = this.findPersonNamesByOffenderNumberSearch(
					Long.valueOf(searchCriteria));
		} else {
			result = super.findPersonNamesByUnspecified(searchCriteria);
		}

		return result;
	}
}
