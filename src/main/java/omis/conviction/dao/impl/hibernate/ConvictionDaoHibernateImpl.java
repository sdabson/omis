package omis.conviction.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.conviction.dao.ConvictionDao;
import omis.conviction.domain.Conviction;
import omis.courtcase.domain.CourtCase;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offense.domain.Offense;

/**
 * Hibernate implementation of data access object for convictions.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (May 2, 2017)
 * @since OMIS 3.0
 */
public class ConvictionDaoHibernateImpl
	extends GenericHibernateDaoImpl<Conviction>
	implements ConvictionDao {

	/* Query names. */
	
	private static final String FIND_BY_COURT_CASE_QUERY_NAME
		= "findConvictionsByCourtCase";

	private static final String COUNT_BY_COURT_CASE_QUERY_NAME
		= "countConvictionsByCourtCase";
	
	private static final String FIND_QUERY_NAME = "findConviction";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findConvictionExcluding";
	
	/* Parameter names. */
	
	private static final String COURT_CASE_PARAM_NAME = "courtCase";
	
	private static final String EXCLUDED_CONVICTION_PARAM_NAME 
		= "excludedConviction";
	
	private static final String OFFENSE_PARAM_NAME = "offense";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * convictions with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ConvictionDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<Conviction> findByCourtCase(final CourtCase courtCase) {
		@SuppressWarnings("unchecked")
		List<Conviction> convictions = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase).list();
		return convictions;
	}

	/** {@inheritDoc} */
	@Override
	public Long countByCourtCase(final CourtCase courtCase) {
		Long count = (Long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public Conviction find(final CourtCase courtCase, 
			final Offense offense) {
		Conviction conviction = (Conviction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(OFFENSE_PARAM_NAME, offense)
				.uniqueResult();
		return conviction;
	}

	/** {@inheritDoc} */
	@Override
	public Conviction findExcluding(final Conviction excludedConviction, 
			final CourtCase courtCase, final Offense offense) {
		Conviction conviction = (Conviction) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(OFFENSE_PARAM_NAME, offense)
				.setParameter(EXCLUDED_CONVICTION_PARAM_NAME, 
						excludedConviction)
				.uniqueResult();
		return conviction;
	}
}