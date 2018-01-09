package omis.presentenceinvestigation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskSourceDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationUsageCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.TaskTemplate;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskSourceDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskSourceDaoHibernateImpl
		extends GenericHibernateDaoImpl<PresentenceInvestigationTaskSource>
		implements PresentenceInvestigationTaskSourceDao {
	
	private static final String
		FIND_PRESENTENCE_INVESTIGATION_TASK_SOURCE_QUERY_NAME =
			"findPresentenceInvestigationTaskSource";
	
	private static final String
		FIND_PRESENTENCE_INVESTIGATION_TASK_SOURCE_EXCLUDING_QUERY_NAME =
			"findPresentenceInvestigationTaskSourceExcluding";
	
	private static final String
		FIND_TASK_SOURCES_BY_TASK_TEMPLATE_GROUP_QUERY_NAME =
			"findPresentenceInvestigationTaskSourcesByTaskTemplateGroup";
	
	private static final String TASK_TEMPLATE_MODEL_KEY = "taskTemplate";
	
	private static final String USAGE_MODEL_KEY = "usage";
	
	private static final String CATEGORY_MODEL_KEY = "category";
	
	private static final String PRESENTENCE_INVESTIGATION_TASK_SOURCE_MODEL_KEY =
			"presentenceInvestigationTaskSource";
	
	private static final String TASK_TEMPLATE_GROUP_MODEL_GROUP = "group";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PresentenceInvestigationTaskSourceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskSource find(
			final TaskTemplate taskTemplate,
			final PresentenceInvestigationTaskAssociationUsageCategory usage,
			final PresentenceInvestigationTaskAssociationCategory category) {
		PresentenceInvestigationTaskSource taskSource =
				(PresentenceInvestigationTaskSource) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_PRESENTENCE_INVESTIGATION_TASK_SOURCE_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_MODEL_KEY, taskTemplate)
				.setParameter(USAGE_MODEL_KEY, usage)
				.setParameter(CATEGORY_MODEL_KEY, category)
				.uniqueResult();
		
		return taskSource;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskSource findExcluding(
			final TaskTemplate taskTemplate,
			final PresentenceInvestigationTaskAssociationUsageCategory usage,
			final PresentenceInvestigationTaskAssociationCategory category,
			final PresentenceInvestigationTaskSource
				presentenceInvestigationTaskSourceExcluding) {
		PresentenceInvestigationTaskSource taskSource =
				(PresentenceInvestigationTaskSource) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
				FIND_PRESENTENCE_INVESTIGATION_TASK_SOURCE_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_MODEL_KEY, taskTemplate)
				.setParameter(USAGE_MODEL_KEY, usage)
				.setParameter(CATEGORY_MODEL_KEY, category)
				.setParameter(PRESENTENCE_INVESTIGATION_TASK_SOURCE_MODEL_KEY,
						presentenceInvestigationTaskSourceExcluding)
				.uniqueResult();
		
		return taskSource;
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationTaskSource>
		findByTaskTemplateGroup(final TaskTemplateGroup group) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationTaskSource> taskSources =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_TASK_SOURCES_BY_TASK_TEMPLATE_GROUP_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_GROUP_MODEL_GROUP, group)
				.list();
		
		return taskSources;
	}
}
