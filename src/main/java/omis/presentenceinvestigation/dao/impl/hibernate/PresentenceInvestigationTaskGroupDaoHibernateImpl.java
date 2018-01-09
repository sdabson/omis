package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskGroupDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskGroupDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskGroupDaoHibernateImpl
		extends GenericHibernateDaoImpl<PresentenceInvestigationTaskGroup>
		implements PresentenceInvestigationTaskGroupDao {
	
	private static final String FIND_TASK_GROUPS_MODEL_KEY =
			"findPresentenceInvestigationTaskGroups";
	
	private static final String
		FIND_TASK_GROUP_BY_PRESENTENCE_INVESTIGATION_CATEGORY_QUERY_NAME =
		"findPresentenceInvestigationTaskGroupByPresentenceInvestigationCategory";
	
	private static final String PRESENTENCE_INVESTIGATION_CATEGORY_MODEL_KEY =
			"presentenceInvestigationCategory";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PresentenceInvestigationTaskGroupDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public List<TaskTemplateGroup> findPresentenceInvestigationTaskGroups() {
		@SuppressWarnings("unchecked")
		List<TaskTemplateGroup> taskGroups = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TASK_GROUPS_MODEL_KEY)
				.list();
		
		return taskGroups
				;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskGroup
		findByPresentenceInvestigationCategory(
				final PresentenceInvestigationCategory
					presentenceInvestigationCategory) {
		PresentenceInvestigationTaskGroup presentenceInvestigationTaskGroup =
				(PresentenceInvestigationTaskGroup) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
				FIND_TASK_GROUP_BY_PRESENTENCE_INVESTIGATION_CATEGORY_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_CATEGORY_MODEL_KEY, 
						presentenceInvestigationCategory)
				.uniqueResult();
		
		return presentenceInvestigationTaskGroup;
	}

}
