package omis.presentenceinvestigation.dao.impl.hibernate;


import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskAssociationDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.Task;
import omis.task.domain.TaskTemplate;

/**
 * PresentenceInvestigationTaskAssociationDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<PresentenceInvestigationTaskAssociation>
		implements PresentenceInvestigationTaskAssociationDao {
	
	private static final String FIND_TASK_ASSOCIATION_QUERY_NAME =
			"findPresentenceInvestigationTaskAssociation";
	
	private static final String FIND_TASK_ASSOCIATION_EXCLUDING_QUERY_NAME =
			"findPresentenceInvestigationTaskAssociationExcluding";

	private static final String
		FIND_TASKS_BY_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME =
			"findTasksByPresentenceInvestigationRequest";

	private static final String
		FIND_BY_TASK_TEMPLATE_AND_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME =
			"findTaskByTaskTemplateAndPresentenceInvestigationRequest";
	
	private static final String TASK_MODEL_KEY = "task";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String TASK_SOURCE_MODEL_KEY = "taskSource";
	
	private static final String
		PRESENTENCE_INVESTIGATION_TASK_ASSOCIATION_MODEL_KEY =
			"presentenceInvestigationTaskAssociation";

	private static final String PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME =
			"presentenceInvestigationRequest";

	private static final String TASK_TEMPLATE_PARAM_NAME = "taskTemplate";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PresentenceInvestigationTaskAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskAssociation find(final Task task,
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PresentenceInvestigationTaskSource taskSource) {
		PresentenceInvestigationTaskAssociation taskAssociation =
				(PresentenceInvestigationTaskAssociation)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_TASK_ASSOCIATION_QUERY_NAME)
				.setParameter(TASK_MODEL_KEY, task)
				.setParameter(TASK_SOURCE_MODEL_KEY, taskSource)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return taskAssociation;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskAssociation findExcluding(
			final Task task,
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PresentenceInvestigationTaskSource taskSource,
			final PresentenceInvestigationTaskAssociation
				presentenceInvestigationTaskAssociationExcluded) {
		PresentenceInvestigationTaskAssociation taskAssociation =
				(PresentenceInvestigationTaskAssociation)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_TASK_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(TASK_MODEL_KEY, task)
				.setParameter(TASK_SOURCE_MODEL_KEY, taskSource)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.setParameter(
						PRESENTENCE_INVESTIGATION_TASK_ASSOCIATION_MODEL_KEY,
						presentenceInvestigationTaskAssociationExcluded)
				.uniqueResult();
		
		return taskAssociation;
	}

	/**{@inheritDoc} */
	@Override
	public List<PresentenceInvestigationTaskAssociation>
		findTasksByPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		@SuppressWarnings("unchecked")
		List<PresentenceInvestigationTaskAssociation> tasks =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_TASKS_BY_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.list();
		
		return tasks;
	}
	

	/**{@inheritDoc} */
	@Override
	public Task findTaskByTaskTemplateAndPresentenceInvestigationRequest(
			final TaskTemplate taskTemplate,
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		Task task = (Task) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
			FIND_BY_TASK_TEMPLATE_AND_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME)
				.setParameter(TASK_TEMPLATE_PARAM_NAME, taskTemplate)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_PARAM_NAME,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return task;
	}
}
