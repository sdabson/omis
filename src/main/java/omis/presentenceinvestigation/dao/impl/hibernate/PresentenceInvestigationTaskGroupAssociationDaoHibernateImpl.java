package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.PresentenceInvestigationTaskGroupAssociationDao;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroupAssociation;

/**
 * PresentenceInvestigationTaskGroupAssociationDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskGroupAssociationDaoHibernateImpl
		extends
		GenericHibernateDaoImpl<PresentenceInvestigationTaskGroupAssociation>
		implements PresentenceInvestigationTaskGroupAssociationDao {

	private static final String FIND_TASK_GROUP_ASSOCIATION_QUERY_NAME =
			"findPresentenceInvestigationTaskGroupAssociation";
	
	private static final String FIND_TASK_GROUP_ASSOCIATION_EXCLUDING_QUERY_NAME =
			"findPresentenceInvestigationTaskGroupAssociationExcluding";

	private static final String
		FIND_BY_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME =
"findPresentenceInvestigationTaskGroupAssociationByPresentenceInvestigationRequest";
	
	private static final String GROUP_MODEL_KEY = "group";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String
		PRESENTENCE_INVESTIGATION_TASK_GROUP_ASSOCIATION_MODEL_KEY =
			"presentenceInvestigationTaskGroupAssociation";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected PresentenceInvestigationTaskGroupAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskGroupAssociation find(
			final PresentenceInvestigationTaskGroup group,
			final PresentenceInvestigationRequest presentenceInvestigationRequest) {
		PresentenceInvestigationTaskGroupAssociation taskGroupAssociation =
				(PresentenceInvestigationTaskGroupAssociation)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_TASK_GROUP_ASSOCIATION_QUERY_NAME)
				.setParameter(GROUP_MODEL_KEY, group)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return taskGroupAssociation;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskGroupAssociation findExcluding(
			final PresentenceInvestigationTaskGroup group,
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PresentenceInvestigationTaskGroupAssociation
				presentenceInvestigationTaskGroupAssociationExcluded) {
		PresentenceInvestigationTaskGroupAssociation taskGroupAssociation =
				(PresentenceInvestigationTaskGroupAssociation)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_TASK_GROUP_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(GROUP_MODEL_KEY, group)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.setParameter(
					PRESENTENCE_INVESTIGATION_TASK_GROUP_ASSOCIATION_MODEL_KEY,
						presentenceInvestigationTaskGroupAssociationExcluded)
				.uniqueResult();
		
		return taskGroupAssociation;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskGroupAssociation
		findByPresentenceInvestigationRequest(
				final PresentenceInvestigationRequest
					presentenceInvestigationRequest) {
		PresentenceInvestigationTaskGroupAssociation taskGroupAssociation =
				(PresentenceInvestigationTaskGroupAssociation)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_BY_PRESENTENCE_INVESTIGATION_REQUEST_QUERY_NAME)
				.setParameter(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
						presentenceInvestigationRequest)
				.uniqueResult();
		
		return taskGroupAssociation;
	}

}
