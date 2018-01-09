package omis.workassignment.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workassignment.dao.WorkAssignmentGroupDao;
import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Hibernate implementation of data access object for work assignment group
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentGroupDaoHibernateImpl
		extends GenericHibernateDaoImpl<WorkAssignmentGroup>
		implements WorkAssignmentGroupDao {
	/* Queries. */
	private static final String FIND_WORK_ASSIGNMENT_GROUP_QUERY_NAME 
		= "findWorkAssignmentGroup";
	
	private static final String FIND_WORK_ASSIGNMENT_GROUP_EXCLUDING_QUERY_NAME 
		= "findWorkAssignmentGroupExcluding";
	
	/* Parameters. */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_WORK_ASSIGNMENT_GROUP_PARAM_NAME 
		= "excludedWorkAssignmentGroup";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * work assignment group
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WorkAssignmentGroupDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignmentGroup find(final String name) {
		WorkAssignmentGroup workAssignmentGroup = 
				(WorkAssignmentGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_GROUP_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return workAssignmentGroup;
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignmentGroup findExcluding(final String name, 
			final WorkAssignmentGroup excludedWorkAssignmentGroup) {
		WorkAssignmentGroup workAssignmentGroup = 
				(WorkAssignmentGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_GROUP_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_WORK_ASSIGNMENT_GROUP_PARAM_NAME, 
						excludedWorkAssignmentGroup)
				.uniqueResult();
		return workAssignmentGroup;
	}
}