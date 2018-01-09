package omis.search.report.service.impl.hibernate;

import omis.search.report.service.EmployerSearchService;
import omis.search.report.EmployerSearchResult;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Query;

/** Implementation for employer search service.
 * @author Yidong Li
 * @version 0.1.0 (Feb 26, 2015)
 * @since OMIS 3.0 */
public class EmployerSearchServiceHibernateImpl
	implements EmployerSearchService {
	private static final String FIND_EMPLOYER_BY_NAME_QUERY_NAME 
		= "findEmployerByName";
	private static final String EMPLOYER_NAME_PARAMETER_NAME = "employerName";
	
	private SessionFactory sessionFactory;
	
	/* constructor */
	/** Instantiates an instance of employer search service
	 * @param sessionFactory session factory. 
	 */
	public EmployerSearchServiceHibernateImpl(final SessionFactory sessionFactory
		) {
		this.sessionFactory = sessionFactory;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployerSearchResult> findEmployerByUnspecified(
		final String searchCriteria) {
		final Query q= this.sessionFactory.getCurrentSession()
			.getNamedQuery(FIND_EMPLOYER_BY_NAME_QUERY_NAME)
			.setParameter(EMPLOYER_NAME_PARAMETER_NAME, searchCriteria);
		return q.list();
		
	}
}

