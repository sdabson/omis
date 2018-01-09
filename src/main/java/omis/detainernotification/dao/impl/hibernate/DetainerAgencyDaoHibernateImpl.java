/**
 * DetainerAgencyDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Mar 22, 2017)
 *@since OMIS 3.0
 *
 */
package omis.detainernotification.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.DetainerAgencyDao;
import omis.detainernotification.domain.DetainerAgency;


public class DetainerAgencyDaoHibernateImpl 
	extends GenericHibernateDaoImpl<DetainerAgency> 
	implements DetainerAgencyDao {
	
	/* Query names */
	
	private static final String FIND_DETAINER_AGENCY_QUERY_NAME =  
			"findDetainerAgency";
	
	private static final String FIND_DETAINER_AGENCY_EXCLUDING_QUERY_NAME =  
			"findDetainerAgencyExcluding";
	
	private static final String FIND_ALL_DETAINER_AGENCIES_QUERY_NAME =
			"findAllDetainerAgencies";
	
	/* Parameter names */
	
	private static final String AGENCY_NAME_PARAM_NAME = "agencyName";
	
	private static final String DETAINER_AGENCY_PARAM_NAME = "detainerAgency";
	
	/**
	 * Constructor
	 * @param sessionFactory - session factory
	 * @param entityName - entity name
	 */
	public DetainerAgencyDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method Implementations */
	
	/**{@inheritDoc} */
	@Override
	public DetainerAgency find(String agencyName) {
		DetainerAgency detainerAgency = (DetainerAgency) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_DETAINER_AGENCY_QUERY_NAME)
				.setParameter(AGENCY_NAME_PARAM_NAME, agencyName)
				.uniqueResult();
		return detainerAgency;
	}

	/**{@inheritDoc} */
	@Override
	public DetainerAgency findExcluding(String agencyName, 
			DetainerAgency excludedDetainerAgency) {
		DetainerAgency detainerAgency = (DetainerAgency) 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_DETAINER_AGENCY_EXCLUDING_QUERY_NAME)
				.setParameter(AGENCY_NAME_PARAM_NAME, agencyName)
				.setParameter(DETAINER_AGENCY_PARAM_NAME, excludedDetainerAgency)
				.uniqueResult();
		return detainerAgency;
	}
	
	/**{@inheritDoc} */
	@Override
	public List<DetainerAgency> findAll(){
		@SuppressWarnings("unchecked")
		List<DetainerAgency> detainerAgencies = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_DETAINER_AGENCIES_QUERY_NAME)
				.list();
		
		return detainerAgencies;
	}

}
