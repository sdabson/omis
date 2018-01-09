package omis.detainernotification.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.DetainerTypeDao;
import omis.detainernotification.domain.DetainerType;

/**
 * DetainerTypeDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 12, 2016)
 *@since OMIS 3.0
 *
 */
public class DetainerTypeDaoHibernateImpl 
	extends GenericHibernateDaoImpl<DetainerType> implements DetainerTypeDao {

	/* Query names */
	
	private static final String FIND_DETAINER_TYPE_QUERY_NAME =  
			"findDetainerType";
	
	private static final String FIND_DETAINER_TYPE_EXCLUDING_QUERY_NAME =  
			"findDetainerTypeExcluding";
	
	/* Parameter names */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String DETAINER_TYPE_PARAM_NAME = "detainerType";
	
	
	
	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 * @param entityName - entity name
	 */
	public DetainerTypeDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method Implementations */
	
	/**{@inheritDoc} */
	@Override
	public DetainerType find(String name) {
		DetainerType detainerType = (DetainerType) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_DETAINER_TYPE_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return detainerType;
	}

	/**{@inheritDoc} */
	@Override
	public DetainerType findExcluding(String name, 
			DetainerType excludedDetainerType) {
		DetainerType detainerType = (DetainerType) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_DETAINER_TYPE_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(DETAINER_TYPE_PARAM_NAME, excludedDetainerType)
				.uniqueResult();
		return detainerType;
	}

}
