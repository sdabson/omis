package omis.workassignment.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workassignment.dao.WorkAssignmentChangeReasonDao;
import omis.workassignment.domain.WorkAssignmentChangeReason;

/**
 * Hibernate implementation of data access object for work assignment change
 * reason.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentChangeReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<WorkAssignmentChangeReason>
		implements WorkAssignmentChangeReasonDao {
	/* Queries. */
	private static final String FIND_WORK_ASSIGNMENT_CHANGE_REASONS_QUERY_NAME 
		= "findWorkAssignmentChangeReasons";
	
	private static final String FIND_WORK_ASSIGNMENT_CHANGE_REASON_QUERY_NAME 
		= "findWorkAssignmentChangeReason";
	
	private static final String 
		FIND_WORK_ASSIGNMENT_CHANGE_REASON_EXCLUDING_QUERY_NAME 
			= "findWorkAssignmentChangeReasonExcluding";

	/* Parameters. */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String 
		EXCLUDED_WORK_ASSIGNMENT_CHANGE_REASON_PARAM_NAME 
			= "excludedWorkAssignmentChangeReason";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * work assignment change reason
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WorkAssignmentChangeReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentChangeReason> findAll() {
		@SuppressWarnings("unchecked")
		List<WorkAssignmentChangeReason> workAssignmentChangeReasons = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_CHANGE_REASONS_QUERY_NAME)
				.list();
		return workAssignmentChangeReasons;
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignmentChangeReason find(final String name) {
		WorkAssignmentChangeReason workAssignmentChangeReason =
				(WorkAssignmentChangeReason) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_CHANGE_REASON_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return workAssignmentChangeReason;
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignmentChangeReason findExcluding(final String name, 
			final WorkAssignmentChangeReason excludedWorkAssignmentChangeReason) {
		WorkAssignmentChangeReason workAssignmentChangeReason =
				(WorkAssignmentChangeReason) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_WORK_ASSIGNMENT_CHANGE_REASON_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_WORK_ASSIGNMENT_CHANGE_REASON_PARAM_NAME, 
						excludedWorkAssignmentChangeReason)
				.uniqueResult();
		return workAssignmentChangeReason;
	}
}