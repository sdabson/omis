package omis.visitation.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.visitation.dao.VisitationAssociationCategoryDao;
import omis.visitation.domain.VisitationAssociationCategory;

import org.hibernate.SessionFactory;

/**
 * Visitation association category data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (May 8, 2015)
 * @since OMIS 3.0
 */
public class VisitationAssociationCategoryDaoHibernateImpl
extends GenericHibernateDaoImpl<VisitationAssociationCategory>
implements VisitationAssociationCategoryDao {

	/* Query names. */
	
	private static final String FIND_CATEGORIES_QUERY_NAME 
		= "findVisitationAssociationCategories";
	private static final String FIND_CATEGORIES_BY_NAME_QUERY_NAME 
		= "findVisitationAssociationCategoryByName";
	
	/* Parameter names. */
	private static final String NAME_PARAMETER_NAME = "name";
	
	
	/**
	 * Instantiates a visitation association category data access object
	 * with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VisitationAssociationCategoryDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public VisitationAssociationCategory find(String name) {
		VisitationAssociationCategory category = 
			(VisitationAssociationCategory) this.getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_CATEGORIES_BY_NAME_QUERY_NAME)
		.setParameter(NAME_PARAMETER_NAME, name)
		.uniqueResult();
		return category;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociationCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<VisitationAssociationCategory> categories = 
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CATEGORIES_QUERY_NAME)
				.list();
		return categories;
	}
}