package omis.criminalassociation.dao.impl.hibernate;

import java.util.List;

import omis.criminalassociation.dao.CriminalAssociationCategoryDao;
import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * criminal association category.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (Apr, 14 2015)
 * @since OMIS 3.0
 *
 */
public class CriminalAssociationCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CriminalAssociationCategory>
	implements CriminalAssociationCategoryDao {
	
	/* Query names.*/
	
	private static final String FIND_CRIMINAL_ASSOCIATION_CATEGORIES 
		= "findCriminalAssociationCategories";
	
	/* Constructors. */
	
	/**
	 * Instantiates a data access object for criminal association category with 
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CriminalAssociationCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<CriminalAssociationCategory> 
		findCriminalAssociationCategories() {
		@SuppressWarnings("unchecked")
		List<CriminalAssociationCategory> criminalAssociationCategories 
			= getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CRIMINAL_ASSOCIATION_CATEGORIES)
			.list();
		return criminalAssociationCategories;
	}
}