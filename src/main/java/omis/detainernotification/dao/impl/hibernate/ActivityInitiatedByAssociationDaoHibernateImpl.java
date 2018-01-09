package omis.detainernotification.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.ActivityInitiatedByAssociationDao;
import omis.detainernotification.domain.ActivityInitiatedByAssociation;

/**
 * ActivityInitiatedByAssociationDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class ActivityInitiatedByAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<ActivityInitiatedByAssociation>
		implements ActivityInitiatedByAssociationDao {

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ActivityInitiatedByAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	

}
