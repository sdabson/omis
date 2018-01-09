package omis.warrant.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.warrant.dao.WarrantCauseViolationDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCauseViolation;

/**
 * WarrantCauseDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCauseViolationDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantCauseViolation>
	implements WarrantCauseViolationDao {
	
	private static final String FIND_WARRANT_CAUSE_VIOLATION_QUERY_NAME =
			"findWarrantCauseViolation";
	
	private static final String FIND_WARRANT_CAUSE_VIOLATION_EXCLUDING_QUERY_NAME =
			"findWarrantCauseViolationExcluding";
	
	private static final String FIND_WARRANT_CAUSE_VIOLATIONS_BY_WARRANT_QUERY_NAME =
			"findWarrantCauseViolationsByWarrant";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String CAUSE_PARAM_NAME = "cause";
	
	private static final String CONDITION_PARAM_NAME = "condition";
	
	private static final String WARRANT_CAUSE_VIOLATION_PARAM_NAME =
			"warrantCauseViolation";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected WarrantCauseViolationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCauseViolation find(
			final Warrant warrant,
			final CourtCase cause,
			final Condition condition) {
		WarrantCauseViolation warrantCauseViolation = (WarrantCauseViolation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CAUSE_VIOLATION_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(CAUSE_PARAM_NAME, cause)
				.setParameter(CONDITION_PARAM_NAME, condition)
				.uniqueResult();
		
		return warrantCauseViolation;
	}
	
	/**{@inheritDoc} */
	@Override
	public WarrantCauseViolation findExcluding(
			final Warrant warrant, final CourtCase cause,
			final Condition condition,
			final WarrantCauseViolation warrantCauseViolationExcluding) {
		WarrantCauseViolation warrantCauseViolation = (WarrantCauseViolation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CAUSE_VIOLATION_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(CAUSE_PARAM_NAME, cause)
				.setParameter(CONDITION_PARAM_NAME, condition)
				.setParameter(WARRANT_CAUSE_VIOLATION_PARAM_NAME,
						warrantCauseViolationExcluding)
				.uniqueResult();
		
		return warrantCauseViolation;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<WarrantCauseViolation> findByWarrant(final Warrant warrant) {
		@SuppressWarnings("unchecked")
		List<WarrantCauseViolation> warrantCauseViolations =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CAUSE_VIOLATIONS_BY_WARRANT_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.list();
		
		return warrantCauseViolations;
	}

}
