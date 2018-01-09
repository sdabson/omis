package omis.judge.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.judge.dao.JudgeTermDao;
import omis.judge.domain.JudgeTerm;
import omis.person.domain.Person;

/**
 * Hibernate implementation of data access object for judge terms.
 *
 * @author Josh Divine
 * @version 0.0.1 (Apr 10, 2017)
 * @since OMIS 3.0
 */
public class JudgeTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<JudgeTerm>
	implements JudgeTermDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findJudgeTerm";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findJudgeTermExcluding";
	
	private static final String FIND_JUDGES_QUERY_ON_DATE_QUERY_NAME
		= "findJudgesQueryOnDate";
	
	private static final String FIND_JUDGES_BY_NAME_ON_DATE_QUERY_NAME
		= "findJudgesByNameOnDate";
	
	/* Parameter names. */
	
	private static final String JUDGE_PARAM_NAME = "judge";
	
	private static final String JUDGE_TERM_PARAM_NAME = "judgeTerm";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String END_DATE_PARAM_NAME = "endDate";
	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	private static final String QUERY_PARAM_NAME = "query";
	
	private static final String FIRST_NAME_PARAM_NAME = "firstName";
	
	private static final String LAST_NAME_PARAM_NAME = "lastName";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for judge 
	 * terms.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public JudgeTermDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public JudgeTerm find(final Person judge, final DateRange dateRange) {
		JudgeTerm judgeTerm = (JudgeTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(JUDGE_PARAM_NAME, judge)
				.setParameter(START_DATE_PARAM_NAME, 
						DateRange.getStartDate(dateRange))
				.setParameter(END_DATE_PARAM_NAME, 
						DateRange.getEndDate(dateRange))
				.uniqueResult();
		return judgeTerm;
	}

	/** {@inheritDoc} */
	@Override
	public JudgeTerm findExcluding(final JudgeTerm excludedJudgeTerm, 
			final Person judge, final DateRange dateRange) {
		JudgeTerm judgeTerm = (JudgeTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(JUDGE_PARAM_NAME, judge)
				.setParameter(START_DATE_PARAM_NAME, 
						DateRange.getStartDate(dateRange))
				.setParameter(END_DATE_PARAM_NAME, 
						DateRange.getEndDate(dateRange))
				.setParameter(JUDGE_TERM_PARAM_NAME, excludedJudgeTerm)
				.uniqueResult();
		return judgeTerm;
	}

	/** {@inheritDoc} */
	@Override
	public List<Person> findJudgesQueryOnDate(final String query, 
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<Person> judges = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_JUDGES_QUERY_ON_DATE_QUERY_NAME)
				.setParameter(QUERY_PARAM_NAME, query)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return judges;
	}

	/** {@inheritDoc} */
	@Override
	public List<Person> findJudgesByNameOnDate(final String lastName, 
			final String firstName, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<Person> judges = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_JUDGES_BY_NAME_ON_DATE_QUERY_NAME)
				.setParameter(FIRST_NAME_PARAM_NAME, firstName)
				.setParameter(LAST_NAME_PARAM_NAME, lastName)
				.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
				.list();
		return judges;
	}
}
