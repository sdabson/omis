package omis.family.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.family.dao.FamilyAssociationCategoryDao;
import omis.family.domain.FamilyAssociationCategory;

import org.hibernate.SessionFactory;

/**
 * Implementation of data access object for family association category.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 5, 2013)
 * @since OMIS 3.0
 */
public class FamilyAssociationCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<FamilyAssociationCategory>
	implements FamilyAssociationCategoryDao {
	
	/* Query names. */
	private static final String FIND_CATEGORIES_QUERY_NAME
		= "findFamilyAssociationCategroies";
	private static final String FIND_CATEGORY_QUERY_NAME
		= "findFamilyAssociationCategory";
	
	/* Parameter names. */
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	/**
	 * Instnatiates a default instance of family association category
	 * data access object hibernate entity implementation.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FamilyAssociationCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method Implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<FamilyAssociationCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<FamilyAssociationCategory> categories = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CATEGORIES_QUERY_NAME)
			.list();
		return categories;
	}
	
	/** {@inheritDoc} */
	@Override
	public FamilyAssociationCategory find(final String name) {
		FamilyAssociationCategory category = (FamilyAssociationCategory) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_CATEGORY_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.uniqueResult();
		return category;
	}
}
