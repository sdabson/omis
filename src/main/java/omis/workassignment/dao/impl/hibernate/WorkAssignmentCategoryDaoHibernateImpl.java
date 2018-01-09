package omis.workassignment.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workassignment.dao.WorkAssignmentCategoryDao;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentGroup;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for work assignment category.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<WorkAssignmentCategory>
		implements WorkAssignmentCategoryDao {
	/* Queries. */
	private static final String FIND_WORK_ASSIGNMENT_CATEGORIES_QUERY_NAME 
		= "findWorkAssignmentCategories";
	
	private static final String FIND_WORK_ASSIGNMENT_CATEGORY_QUERY_NAME 
		= "findWorkAssignmentCategory";
	
	private static final String 
		FIND_WORK_ASSIGNMENT_CATEGORY_EXCLUDING_QUERY_NAME 
			= "findWorkAssignmentCategoryExcluding";
	
	/* Parameters. */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String WORK_ASSIGNMENT_GROUP_PARAM_NAME 
		= "workAssignmentGroup";

	private static final String EXCLUDED_WORK_ASSIGNMENT_CATEGORY_PARAM_NAME 
		= "excludedWorkAssignmentCategory";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * work assignment category
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WorkAssignmentCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<WorkAssignmentCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<WorkAssignmentCategory> workAssignmentCategories = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_CATEGORIES_QUERY_NAME)
				.list();
		return workAssignmentCategories;
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignmentCategory find(final String name,  
			final WorkAssignmentGroup workAssignmentGroup) {
		WorkAssignmentCategory workAssignmentCategory = 
				(WorkAssignmentCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WORK_ASSIGNMENT_CATEGORY_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(WORK_ASSIGNMENT_GROUP_PARAM_NAME, 
						workAssignmentGroup)
				.uniqueResult();
		return workAssignmentCategory;
	}

	/** {@inheritDoc} */
	@Override
	public WorkAssignmentCategory findExcluding(final String name, 
			final WorkAssignmentGroup workAssignmentGroup,
			final WorkAssignmentCategory excludedWorkAssignmentCategory) {
		WorkAssignmentCategory workAssignmentCategory = 
				(WorkAssignmentCategory) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_WORK_ASSIGNMENT_CATEGORY_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(WORK_ASSIGNMENT_GROUP_PARAM_NAME, 
						workAssignmentGroup)
				.setParameter(EXCLUDED_WORK_ASSIGNMENT_CATEGORY_PARAM_NAME, 
						excludedWorkAssignmentCategory)
				.uniqueResult();
		return workAssignmentCategory;
	}
}