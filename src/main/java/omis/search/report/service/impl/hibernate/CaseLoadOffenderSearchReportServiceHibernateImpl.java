package omis.search.report.service.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.person.domain.Person;
import omis.search.report.OffenderSearchResult;
import omis.search.report.service.CaseLoadOffenderSearchReportService;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/** Implementation of case search summary service.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 10, 2013)
 * @since OMIS 3.0 */
public class CaseLoadOffenderSearchReportServiceHibernateImpl
	implements CaseLoadOffenderSearchReportService {

	private static final String FIND_CASELOAD_OFFENDER_BY_PERSON =
			"findCaseLoadOffenderByPerson";

	private final SessionFactory sessionFactory;

	/** Constructor.
	 * @param sessionFactory session factory. */
	public CaseLoadOffenderSearchReportServiceHibernateImpl(
			final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderSearchResult> findCaseLoadOffenderSearchByPerson(
			final Person person) {
		final Query q = this.sessionFactory.getCurrentSession()
				.getNamedQuery(FIND_CASELOAD_OFFENDER_BY_PERSON);

		q.setParameter("person", person);
		q.setDate("date", new Date());

		@SuppressWarnings("unchecked")
		final List<OffenderSearchResult> caseLoad = q.list();

		return caseLoad;
	}
}
