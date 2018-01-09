package omis.condition.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.condition.dao.ConditionTitleDao;
import omis.condition.domain.ConditionTitle;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * ConditionTitleDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 15, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionTitleDaoHibernateImpl
	extends GenericHibernateDaoImpl<ConditionTitle>
		implements ConditionTitleDao {

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected ConditionTitleDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

}
