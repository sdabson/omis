package omis.caseload.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.caseload.dao.OffenderCaseAssignmentDao;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for offender case assignment.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (Jun 23, 2017)
 * @since OMIS 3.0
 */
public class OffenderCaseAssignmentDaoHibernateImpl 
				extends GenericHibernateDaoImpl<OffenderCaseAssignment> 
				implements OffenderCaseAssignmentDao {
	
	/* Queries. */
	private static final String FIND_OFFENDER_CASE_ASSIGNMENT_QUERY_NAME 
					= "findOffenderCaseAssignment";
	private static final String 
					FIND_OFFENDER_CASE_ASSIGNMENT_EXCLUDING_QUERY_NAME 
					= "findOffenderCaseAssignmentExcluding";
	private static final String FIND_WITHIN_DATE_RANGE_QUERY_NAME 
					= "findOffenderCaseAssignmentWithinDateRange";
	private static final String FIND_WITHIN_DATE_RANGE_EXCLUDING_QUERY_NAME 
					= "findOffenderCaseAssignmentWithinDateRangeExcluding";
	private static final String FIND_ALL_OFFENDER_CASE_ASSIGNMENTS_QUERY_NAME 
					= "findAllOffenderCaseAssignments";
	private static final String 
					FIND_ALL_OFFENDER_CASE_ASSIGNMENTS_BY_CASELOAD_DATERNGE
					= "findAllOffenderCaseAssignmentsByCaseloadAndDateRange";
	private static final String FIND_SUPERVISORY_BY_OFFENDER_AND_DATE_QUERY_NAME
					= "findSupervisoryOffenderCaseAssignmentByOffenderAndDate";
	
	/* Parameters. */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String CASELOAD_PARAM_NAME = "caseload";
	private static final String START_DATE_PARAMETER_NAME = "startDate";	
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	private static final String OFFENDER_CASE_ASSIGNMENT_PARAM_NAME 
					= "offenderCaseAssignment";	
	private static final String EFFECTIVE_DATE_PARAM_NAME = "effectiveDate";
	
	/** Instantiates a hibernate implementation of offender case
	 *  assignment data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderCaseAssignmentDaoHibernateImpl(
					final SessionFactory sessionFactory, 
					final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment find(final Offender offender, 
						final Caseload caseload) {
		OffenderCaseAssignment assignment = (OffenderCaseAssignment) 
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(FIND_OFFENDER_CASE_ASSIGNMENT_QUERY_NAME)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.uniqueResult();
		return assignment;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaseAssignment> findWithinDateRange(
					final DateRange dateRange, final Caseload caseload, 
					final Offender offender) {
		@SuppressWarnings("unchecked")
		List<OffenderCaseAssignment> assignments = this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
						FIND_WITHIN_DATE_RANGE_QUERY_NAME)
						.setTimestamp(START_DATE_PARAMETER_NAME, 
						DateRange.getStartDate(dateRange))
						.setTimestamp(END_DATE_PARAMETER_NAME, 
						DateRange.getEndDate(dateRange))
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.list();
		return assignments;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment findExcluding(final Offender offender, 
					final OffenderCaseAssignment assignment) {
		OffenderCaseAssignment thisAssignment = (OffenderCaseAssignment) 
						this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
						FIND_OFFENDER_CASE_ASSIGNMENT_EXCLUDING_QUERY_NAME)
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.setParameter(OFFENDER_CASE_ASSIGNMENT_PARAM_NAME, 
								assignment)
						.uniqueResult();
		return thisAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaseAssignment> findWithinDateRangeExcluding(
					final DateRange dateRange,
					final Offender offender, 
					final OffenderCaseAssignment assignment) {
		@SuppressWarnings("unchecked")
		List<OffenderCaseAssignment> assignments = this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
								FIND_WITHIN_DATE_RANGE_EXCLUDING_QUERY_NAME)
						.setTimestamp(START_DATE_PARAMETER_NAME, 
								dateRange.getStartDate())
						.setTimestamp(END_DATE_PARAMETER_NAME, 
								dateRange.getEndDate())
						.setParameter(OFFENDER_PARAM_NAME, offender)
						.list();
		return assignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaseAssignment> findAllCaseAssignments(
					final Caseload caseload, final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<OffenderCaseAssignment> assignments = this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
						FIND_ALL_OFFENDER_CASE_ASSIGNMENTS_QUERY_NAME)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate)
						.list();
		return assignments;
	}

	/** {@inheritDoc} */
	@Override
	public List<OffenderCaseAssignment> 
					findAllCaseAssignmentsByCaseloadAndDateRange(
						final Caseload caseload, 
						final Date  startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<OffenderCaseAssignment> assignments = this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
						FIND_ALL_OFFENDER_CASE_ASSIGNMENTS_BY_CASELOAD_DATERNGE)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.setDate(START_DATE_PARAMETER_NAME, startDate)
						.setDate(END_DATE_PARAMETER_NAME, endDate).list();
		return assignments;
	}
	
	/** {@inheritDoc} */
	@Override
	public OffenderCaseAssignment 
					findSupervisoryCaseAssignmentByOffenderAndDate(
							final Offender offender,
							final Date effectiveDate) {
		final Query q = this.getSessionFactory().getCurrentSession()
						.getNamedQuery(
						FIND_SUPERVISORY_BY_OFFENDER_AND_DATE_QUERY_NAME);
		q.setEntity(OFFENDER_PARAM_NAME, offender);
		q.setParameter(EFFECTIVE_DATE_PARAM_NAME, effectiveDate);
		return (OffenderCaseAssignment) q.uniqueResult();
	}
}