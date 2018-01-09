package omis.probationterm.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.courtcase.domain.CourtCase;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.probationterm.dao.ProbationTermDao;
import omis.probationterm.domain.ProbationTerm;

/**
 * Hibernate implementation of data access object for probation terms.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.1.2 (Nov 21, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ProbationTerm> 
	implements ProbationTermDao {

	/* Query names. */
	
	private static final String FIND_EXLUDING_QUERY_NAME 
		= "findExcludingProbationTerm";
	
	private static final String FIND_QUERY_NAME 
		= "findProbationTerm";
	
	private static final String 
		FIND_BY_OFFENDER_AND_COURT_CASE_ON_DATE_QUERY_NAME
			= "findProbationTermByOffenderAndCourtCaseOnDate";

	private static final String COUNT_AFTER_DATE_EXCLUDING_QUERY_NAME
		= "countProbationTermsAfterDateExcluding";
	
	private static final String COUNT_EXCLUDING_QUERY_NAME
		= "countProbationTermsExcluding";
	
	private static final String FIND_BY_COURT_CASE_QUERY_NAME 
		= "findProbationTermByCourtCase";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String COURT_CASE_PARAM_NAME = "courtCase";
	
	private static final String START_DATE_PARAM_NAME = "startDate";
	
	private static final String EXCLUDED_PROBATION_TERM_PARAM_NAME 
		= "excludedProbationTerm";
	
	private static final String TERMINATION_DATE_PARAM_NAME = "terminationDate";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String EXCLUDED_PROBATION_TERMS_PARAM_NAME
		= "excludedProbationTerms";
	
	/* Constructor.*/
	
	/**
	 * Instantiates a data access object for probation terms with the
	 * specified session factory and entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProbationTermDaoHibernateImpl(final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public ProbationTerm find(final Offender offender, 
			final CourtCase courtCase, final Date startDate) {
		ProbationTerm probationTerm = (ProbationTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.uniqueResult();
		return probationTerm;
	}

	/** {@inheritDoc} */
	@Override
	public ProbationTerm findExcluding(
			final ProbationTerm excludedProbationTerm, final Offender offender, 
			final CourtCase courtCase, final Date startDate) {
		ProbationTerm probationTerm = (ProbationTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(EXCLUDED_PROBATION_TERM_PARAM_NAME, 
						excludedProbationTerm)
				.uniqueResult();
		return probationTerm;
	}

	/** {@inheritDoc} */
	@Override
	public ProbationTerm findByOffenderAndCourtCaseOnDate(
			final Offender offender, final CourtCase courtCase, 
			final Date date) {
		ProbationTerm probationTerm = (ProbationTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_BY_OFFENDER_AND_COURT_CASE_ON_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(DATE_PARAM_NAME, date)
				.uniqueResult();
		return probationTerm;
	}

	/** {@inheritDoc} */
	@Override
	public long countAfterDateExcluding(final Offender offender, 
			final CourtCase courtCase, final Date date,
			final ProbationTerm excludedProbationTerm) {
		long count = (long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_AFTER_DATE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(EXCLUDED_PROBATION_TERM_PARAM_NAME, 
						excludedProbationTerm)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public long countExcluding(final Offender offender, 
			final CourtCase courtCase, final Date startDate,
			final Date terminationDate,
			final ProbationTerm... excludedProbationTerms) {
		long count = (long) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(COUNT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(TERMINATION_DATE_PARAM_NAME, terminationDate)
				.setParameterList(EXCLUDED_PROBATION_TERMS_PARAM_NAME, 
						excludedProbationTerms)
				.uniqueResult();
		return count;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProbationTerm> findByCourtCase(final CourtCase courtCase) {
		@SuppressWarnings("unchecked")
		List<ProbationTerm> probationTerms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_COURT_CASE_QUERY_NAME)
				.setParameter(COURT_CASE_PARAM_NAME, courtCase)
				.list();
		return probationTerms;
	}
}