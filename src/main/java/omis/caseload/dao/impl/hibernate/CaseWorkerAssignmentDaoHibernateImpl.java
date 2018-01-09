package omis.caseload.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.caseload.dao.CaseWorkerAssignmentDao;
import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.domain.Person;

import org.hibernate.SessionFactory;

/** Hibernate implementation of the case worker assignment data access object.
 * @author Ryan Johns
 * @author Sheronda Vaughn
 * @version 0.1.1 (Jun 20, 2017)
 * @since OMIS 3.0 */
public class CaseWorkerAssignmentDaoHibernateImpl 
				extends GenericHibernateDaoImpl<CaseWorkerAssignment> 
				implements CaseWorkerAssignmentDao {
	
	/* Queries. */
	private static final String FIND_CASE_WORKER_ASSIGNMENT_QUERY_NAME 
					= "findCaseWorkerAssignment";
	private static final String 
					FIND_CASE_WORKER_ASSIGNMENT_EXCLUDING_QUERY_NAME 
					= "findCaseWorkerAssignmentExcluding";
	private static final String FIND_WITHIN_DATE_RANGE_QUERY_NAME 
					= "findCaseAssignmentByCaseWorkerCategoryAndDate";
	private static final String 
					FIND_ASSGN_BY_CASE_WITHN_DTES_EXCLDING_QUERY_NAME 
					= "findCaseAssignmentByCaseloadCategoryWithinDatesExcld";
	private static final String FIND_ALL_CASE_WORKER_ASSIGNMENTS_QUERY_NAME 
					= "findAllCaseWorkerAssignments";
	private static final String 
					FIND_CASE_ASSIGNMENTS_WITHIN_DATES_QUERY_NAME 
					= "findCaseAssignmentsByCaseloadCategoryWithinDates";
	
	/* Parameters. */
	private static final String WORKER_PARAM_NAME = "worker";
	private static final String CASELOAD_PARAM_NAME = "caseload";
	private static final String CASEWORK_CATEGORY_PARAM_NAME = "category";
	private static final String CASE_WORKER_ASSIGNMENT_PARAM_NAME 
					= "caseWorkerAssignment";
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	private static final String START_DATE_PARAMETER_NAME = "startDate";
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	

	/** Instantiates a hibernate implementation of the data access object for 
	 * 	case worker assignment.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CaseWorkerAssignmentDaoHibernateImpl(
					final SessionFactory sessionFactory, 
					final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CaseWorkerAssignment> findAll() {
		// TODO Implement or remove - SV
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment find(final Person worker, 
					final Caseload caseload, final Date startDate, 
					final Date endDate) {
		CaseWorkerAssignment caseWorkerAssignment 
						= (CaseWorkerAssignment) this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
								FIND_CASE_WORKER_ASSIGNMENT_QUERY_NAME)
						.setParameter(WORKER_PARAM_NAME, worker)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.setTimestamp(START_DATE_PARAMETER_NAME, 
								startDate)
						.setTimestamp(END_DATE_PARAMETER_NAME,
								endDate)
						.uniqueResult();
		return caseWorkerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment findExcluding(
					final CaseWorkerAssignment assignment, 
					final Person worker, final Caseload caseload, 
					final Date startDate, final Date endDate) {
		CaseWorkerAssignment caseWorkerAssignment 
						= (CaseWorkerAssignment) this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
						FIND_CASE_WORKER_ASSIGNMENT_EXCLUDING_QUERY_NAME)
						.setParameter(CASE_WORKER_ASSIGNMENT_PARAM_NAME, 
								assignment)
						.setParameter(WORKER_PARAM_NAME, worker)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.setTimestamp(START_DATE_PARAMETER_NAME, 
								startDate)
						.setTimestamp(END_DATE_PARAMETER_NAME,
								endDate)
						.uniqueResult();
		return caseWorkerAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseWorkerAssignment> 
					findCaseAssignmentsWithinDateRange(
							final Date startDate, final Date endDate, 
							final Caseload caseload, final Person worker) {
		@SuppressWarnings("unchecked")
		List<CaseWorkerAssignment> caseWorkerAssignments
						= this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
								FIND_CASE_ASSIGNMENTS_WITHIN_DATES_QUERY_NAME)
						.setParameter(WORKER_PARAM_NAME, worker)
						.setParameter(CASEWORK_CATEGORY_PARAM_NAME, 
								caseload.getCategory())
						.setTimestamp(START_DATE_PARAMETER_NAME, 
								startDate)
						.setTimestamp(END_DATE_PARAMETER_NAME, 
								endDate)
						.list();
		return caseWorkerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseWorkerAssignment> 
					findCaseAssignmentsByCaseCatWithnDatesExclding(
					final CaseWorkerAssignment caseWorkerAssignment,
					final Date startDate, final Date endDate, 
					final Caseload caseload, final Person worker) {
		@SuppressWarnings("unchecked")
		List<CaseWorkerAssignment> caseWorkerAssignments
						= this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
							FIND_ASSGN_BY_CASE_WITHN_DTES_EXCLDING_QUERY_NAME)
						.setParameter(WORKER_PARAM_NAME, worker)
						.setParameter(CASEWORK_CATEGORY_PARAM_NAME, 
								caseload.getCategory())
						.setTimestamp(START_DATE_PARAMETER_NAME, 
								startDate)
						.setTimestamp(END_DATE_PARAMETER_NAME, 
								endDate)
						.setParameter(CASE_WORKER_ASSIGNMENT_PARAM_NAME, 
								caseWorkerAssignment)
						.list();
		return caseWorkerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<CaseWorkerAssignment> findAllWorkerAssignments(
					final Caseload caseload, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<CaseWorkerAssignment> caseWorkerAssignments = 
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
						FIND_ALL_CASE_WORKER_ASSIGNMENTS_QUERY_NAME)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
						.list();
		return caseWorkerAssignments;
	}

	/** {@inheritDoc} */
	@Override
	public CaseWorkerAssignment  
					findSupervisoryCaseWorkerAssignmentByCaseWorkerAndDate(
					final Person caseWorker, final Date effectiveDate) {
		CaseWorkerAssignment assignment 
						= (CaseWorkerAssignment) this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
						FIND_WITHIN_DATE_RANGE_QUERY_NAME)
						.setParameter(WORKER_PARAM_NAME, caseWorker)
						.setParameter(CASEWORK_CATEGORY_PARAM_NAME, 
								CaseworkCategory.SUPERVISORY)
						.setDate(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
						.uniqueResult();
		return assignment;
	}
}