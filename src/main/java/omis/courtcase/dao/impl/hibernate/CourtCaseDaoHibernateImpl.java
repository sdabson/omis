package omis.courtcase.dao.impl.hibernate;

import java.util.List;

import omis.courtcase.dao.CourtCaseDao;
import omis.courtcase.domain.CourtCase;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for court cases.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.3 (Aug 24, 2017)
 * @since OMIS 3.0
 */
public class CourtCaseDaoHibernateImpl
		extends GenericHibernateDaoImpl<CourtCase>
		implements CourtCaseDao {

	/* Query names. */
	
	private static final String FIND_BY_DEFENDANT_QUERY_NAME
		= "findCourtCasesByDefendant";
	
	private static final String FIND_COURT_CASE_QUERY_NAME
		= "findCourtCase";
	
	private static final String FIND_COURT_CASE_EXCLUDING_QUERY_NAME
		= "findCourtCaseExcluding";
	
	private static final String FIND_BY_DOCKET_QUERY_NAME
		= "findCourtCasesByDocket";
	
	/* Parameter names. */
	
	private static final String DEFENDANT_PARAM_NAME = "defendant";
	
	private static final String DOCKET_PARAM_NAME = "docket";
	
	private static final String COURT_CASE_PARAM_NAME = "courtCase";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for court
	 * cases with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CourtCaseDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<CourtCase> findByDefendant(final Person defendant) {
		@SuppressWarnings("unchecked")
		List<CourtCase> defendants = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_DEFENDANT_QUERY_NAME)
				.setParameter(DEFENDANT_PARAM_NAME, defendant).list();
		return defendants;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase find(final Person defendant, final String docket) {
		CourtCase courtCase = (CourtCase) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_COURT_CASE_QUERY_NAME)
				.setParameter(DEFENDANT_PARAM_NAME, defendant)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
		return courtCase;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase findExcluding(final Person defendant, final String docket,
			final CourtCase courtCase) {
		CourtCase foundCourtCase = (CourtCase) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_COURT_CASE_EXCLUDING_QUERY_NAME)
				.setParameter(DEFENDANT_PARAM_NAME, defendant)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.uniqueResult();
		return foundCourtCase;
	}

	/** {@inheritDoc} */
	@Override
	public CourtCase findByDocket(Docket docket) {
		CourtCase courtCase = (CourtCase) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DOCKET_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.uniqueResult();
		return courtCase;
	}
}