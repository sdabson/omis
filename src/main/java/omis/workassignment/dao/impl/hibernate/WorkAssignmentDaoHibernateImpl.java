package omis.workassignment.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.workassignment.dao.WorkAssignmentDao;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for work assignment.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentDaoHibernateImpl
		extends GenericHibernateDaoImpl<WorkAssignment>
		implements WorkAssignmentDao {
	/* Queries. */
	private static final String FIND_WORK_ASSIGNMENT_QUERY_NAME = "findWorkAssignment";
	private static final String FIND_EXISTING_WORK_ASSIGNMENT_QUERY_NAME 
		= "findWorkAssignmentByCategory";
	private static final String FIND_EXCLUDED_EXISTING_WORK_ASSIGNMENT_QUERY_NAME 
		= "findExcludedWorkAssignment";
	private static final String FIND_EXCLUDED_EXISTING_WORK_ASSIGNMENT_QUERY_NAME_BY_DATE
		= "findExistingWorkAssignmentByDate";

	/* Parameters. */
	private static final String OffENDER_PARAMETER_NAME = "offender";
	private static final String CATEGORY_PARAMETER_NAME = "category";
	private static final String ASSIGNED_DATE_PARAMETER_NAME = "assignedDate";
	private static final String EXCLUDED_WORK_ASSIGNMENT_PARAMETER_NAME 
		= "excludedWorkAssignment";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * addresses with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WorkAssignmentDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<WorkAssignment> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<WorkAssignment> workAssignments = this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_WORK_ASSIGNMENT_QUERY_NAME)
			.setParameter(OffENDER_PARAMETER_NAME, offender)
			.list();
		return workAssignments;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignment findExistingWorkAssignment(final Offender offender, 
		final WorkAssignmentCategory category, final Date assignedDate) {
		WorkAssignment workAssignment;
		workAssignment = (WorkAssignment) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_WORK_ASSIGNMENT_QUERY_NAME)
			.setParameter(OffENDER_PARAMETER_NAME, offender)
			.setParameter(CATEGORY_PARAMETER_NAME, category)
			.setParameter(ASSIGNED_DATE_PARAMETER_NAME, assignedDate)
			.uniqueResult();
		return workAssignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public WorkAssignment findExcludedExistingWorkAssignment(
		final WorkAssignment workAssignment, 
		final Offender offender, 
		final WorkAssignmentCategory category,	
		final Date assignedDate) {
		WorkAssignment excludedWorkAssignment;
		excludedWorkAssignment = (WorkAssignment) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXCLUDED_EXISTING_WORK_ASSIGNMENT_QUERY_NAME)
			.setParameter(OffENDER_PARAMETER_NAME, offender)
			.setParameter(CATEGORY_PARAMETER_NAME, category)
			.setParameter(ASSIGNED_DATE_PARAMETER_NAME, assignedDate)
			.setParameter(EXCLUDED_WORK_ASSIGNMENT_PARAMETER_NAME, workAssignment)
			.uniqueResult();
		return excludedWorkAssignment;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<WorkAssignment> findExistingWorkAssignmentByDate(final Offender offender, 
		final Date assignedDate) {
		@SuppressWarnings("unchecked")
		List<WorkAssignment> existingWorkAssignments = getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXCLUDED_EXISTING_WORK_ASSIGNMENT_QUERY_NAME_BY_DATE)
			.setParameter(OffENDER_PARAMETER_NAME, offender)
			.setParameter(ASSIGNED_DATE_PARAMETER_NAME, assignedDate)
			.list();
		return existingWorkAssignments;
	}
}