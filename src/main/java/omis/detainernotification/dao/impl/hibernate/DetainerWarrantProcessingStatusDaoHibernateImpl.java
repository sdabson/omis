package omis.detainernotification.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.DetainerWarrantProcessingStatusDao;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerWarrantProcessingStatus;

/**
 * DetainerWarrantProcessingStatusDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 12, 2016)
 *@since OMIS 3.0
 *
 */
public class DetainerWarrantProcessingStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<DetainerWarrantProcessingStatus> 
		implements DetainerWarrantProcessingStatusDao {

	/* Query names */
	
	private static final String 
		FIND_DETAINER_WARRANT_PROCESSING_STATUS_QUERY_NAME = 
		"findDetainerWarrantProcessingStatus";
	
	private static final String 
		FIND_DETAINER_WARRANT_PROCESSING_STATUS_EXCLUDING_QUERY_NAME = 
		"findDetainerWarrantProcessingStatusExcluding";
	
	/* Parameter names */
	
	private static final String DETAINER_PARAM_NAME = "detainer";
	
	private static final String 
		DETAINER_WARRANT_PROCESSING_STATUS_PARAM_NAME = 
		"detainerWarrantProcessStatus";
	
	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 * @param entityName - entity name
	 */
	public DetainerWarrantProcessingStatusDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method Implementations */
	
	/**{@inheritDoc} */
	@Override
	public DetainerWarrantProcessingStatus find(Detainer detainer) {
		DetainerWarrantProcessingStatus detainerWarrantProcessingStatus = 
				(DetainerWarrantProcessingStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_DETAINER_WARRANT_PROCESSING_STATUS_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.uniqueResult();
		return detainerWarrantProcessingStatus;
	}

	/**{@inheritDoc} */
	@Override
	public DetainerWarrantProcessingStatus findExcluding(Detainer detainer,
			DetainerWarrantProcessingStatus excludedDetainerWarrantProcessingStatus) {
		DetainerWarrantProcessingStatus detainerWarrantProcessingStatus = 
				(DetainerWarrantProcessingStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_DETAINER_WARRANT_PROCESSING_STATUS_EXCLUDING_QUERY_NAME)
				.setParameter(DETAINER_PARAM_NAME, detainer)
				.setParameter(DETAINER_WARRANT_PROCESSING_STATUS_PARAM_NAME, 
						excludedDetainerWarrantProcessingStatus)
				.uniqueResult();
		return detainerWarrantProcessingStatus;
	}

}
