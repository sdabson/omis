package omis.commitstatus.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.commitstatus.dao.CommitStatusTermDao;
import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Commit status term data access object hibernate implementation.
 * 
 * @author Yidong Li
 * @version 0.1.0 (June 1, 2017)
 * @since OMIS 3.0
 */
public class CommitStatusTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CommitStatusTerm>	
	implements CommitStatusTermDao {

	/* Query names. */
	private static final String FIND_CMMIT_STATUS_TERM_QUERY_NAME
 		= "findCommitStatusTerm";
	private static final String FIND_CONFLICT_CMMIT_STATUS_TERM_QUERY_NAME
		= "findConflictCommitStatusTerm";
	private static final String FIND_AFTER_CMMIT_STATUS_TERM_QUERY_NAME
		= "findAfterCommitStatusTerm";
	private static final String FIND_CMMIT_STATUS_TERM_EXCLUDING_QUERY_NAME
		= "findCommitStatusTermExcluding";
	private static final String FIND_CONFLICT_CMMIT_STATUS_TERM_EXCLUDING_QUERY_NAME
		= "findConflictCommitStatusTermExcluding";
	private static final String FIND_AFTER_CMMIT_STATUS_TERM_EXCLUDING_QUERY_NAME
	= "findAfterCommitStatusTermExcluding";
	private static final String FIND_CMMIT_STATUS_TERM_FOR_OFFENDER_ON_DATE_QUERY_NAME
		= "findCommitStatusTermForOffenderOnDate";

	/* Parameter names. */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String COMMIT_STATUS_PARAM_NAME = "commitStatus";
	private static final String START_DATE_PARAM_NAME = "startDate";
	private static final String END_DATE_PARAM_NAME = "endDate";
	private static final String EXCLUDED_COMMIT_STATUS_TERM_PARAM_NAME 
		= "excludedCommitStatusTerm";
		
	/**
	 * Instantiates an instance of commit status term data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CommitStatusTermDaoHibernateImpl(
		final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CommitStatusTerm findExists(final Offender offender, 
		final CommitStatus status, final Date startDate){
		CommitStatusTerm commitStatusTerm;
		commitStatusTerm = (CommitStatusTerm) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CMMIT_STATUS_TERM_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(COMMIT_STATUS_PARAM_NAME, status)
			.setParameter(START_DATE_PARAM_NAME, startDate)
			.uniqueResult();
		return commitStatusTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CommitStatusTerm> findConflict(final Offender offender, 
		final DateRange dateRange){
		Date startDate = dateRange.getStartDate();
		Date endDate = dateRange.getEndDate();
		@SuppressWarnings("unchecked")
		List<CommitStatusTerm> commitStatusTerms = (List<CommitStatusTerm>) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CONFLICT_CMMIT_STATUS_TERM_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setDate(START_DATE_PARAM_NAME, startDate)
			.setDate(END_DATE_PARAM_NAME, endDate)
			.list();
		return commitStatusTerms;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CommitStatusTerm> findConflictExcluding(final Offender offender, 
		final DateRange dateRange, final CommitStatusTerm term){
		Date startDate = dateRange.getStartDate();
		Date endDate = dateRange.getEndDate();
		@SuppressWarnings("unchecked")
		List<CommitStatusTerm> commitStatusTerms = (List<CommitStatusTerm>) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CONFLICT_CMMIT_STATUS_TERM_EXCLUDING_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setDate(START_DATE_PARAM_NAME, startDate)
			.setDate(END_DATE_PARAM_NAME, endDate)
			.setParameter(EXCLUDED_COMMIT_STATUS_TERM_PARAM_NAME, term)
			.list();
		return commitStatusTerms;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CommitStatusTerm> findExistsAfter(final Offender offender, 
		final DateRange dateRange){
		@SuppressWarnings("unchecked")
		List<CommitStatusTerm> commitStatusTerms = (List<CommitStatusTerm>) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_AFTER_CMMIT_STATUS_TERM_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.list();
		return commitStatusTerms;
	}
	
	/** {@inheritDoc} */
	@Override
	public CommitStatusTerm findExcluding(final CommitStatusTerm commitStatusTerm, 
		final CommitStatus status, final DateRange dateRange, 
		final Offender offender){
		CommitStatusTerm foundCommitStatusTerm;
		Date startDate = dateRange.getStartDate();
		foundCommitStatusTerm = (CommitStatusTerm) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CMMIT_STATUS_TERM_EXCLUDING_QUERY_NAME)
			.setParameter(EXCLUDED_COMMIT_STATUS_TERM_PARAM_NAME, commitStatusTerm)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(COMMIT_STATUS_PARAM_NAME, status)
			.setDate(START_DATE_PARAM_NAME, startDate)
			.uniqueResult();
		return foundCommitStatusTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CommitStatusTerm> findExistsAfterExcluding(
		final CommitStatusTerm term, final Offender offender, 
		final DateRange dateRange){
		@SuppressWarnings("unchecked")
		List<CommitStatusTerm> commitStatusTerms = (List<CommitStatusTerm>) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_AFTER_CMMIT_STATUS_TERM_EXCLUDING_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setParameter(EXCLUDED_COMMIT_STATUS_TERM_PARAM_NAME, term)
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.list();
		return commitStatusTerms;
	}
	
	/** {@inheritDoc} */
	@Override
	public CommitStatusTerm findForOffenderOnDate(final Offender offender, 
		final Date effectiveDate){
		CommitStatusTerm commitStatusTerm = (CommitStatusTerm) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CMMIT_STATUS_TERM_FOR_OFFENDER_ON_DATE_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.setDate(START_DATE_PARAM_NAME, effectiveDate)
			.uniqueResult();
			return commitStatusTerm;
	}
}
