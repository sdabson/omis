package omis.detainernotification.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.detainernotification.dao.DetainerActivityCategoryDao;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;

/**
 * DetainerActivityCategoryDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 21, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerActivityCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<DetainerActivityCategory>
		implements DetainerActivityCategoryDao {
	
	private static final String FIND_CATEGORIES_BY_INITIATED_BY_QUERY_NAME =
			"findCategoriesByInitiatedBy";
	
	private static final String INITIATED_BY_PARAM_NAME = "initiatedBy";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected DetainerActivityCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public List<DetainerActivityCategory> findByInitiatedBy(
			final InterstateAgreementInitiatedByCategory initiatedBy) {
		@SuppressWarnings("unchecked")
		List<DetainerActivityCategory> categories = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CATEGORIES_BY_INITIATED_BY_QUERY_NAME)
				.setParameter(INITIATED_BY_PARAM_NAME, initiatedBy)
				.list();
		
		return categories;
	}

}
