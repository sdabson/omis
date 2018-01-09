package omis.identificationnumber.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.identificationnumber.dao.IdentificationNumberCategoryDao;
import omis.identificationnumber.domain.IdentificationNumberCategory;

/**
 * Hibernate implementation of data access object for identification number
 * categories.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public class IdentificationNumberCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<IdentificationNumberCategory>
		implements IdentificationNumberCategoryDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME
		= "findIdentificationNumberCategories";
	
	private static final String FIND_QUERY_NAME
		= "findIdentificationNumberCategory";

	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findIdentificationNumberCategoryExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	private static final String IDNTFCTION_NMBR_CATEGORIES_EXCLUDED_PARAM_NAME =
			"excludedIdentificationNumberCategories";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for
	 * identification numbers.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public IdentificationNumberCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<IdentificationNumberCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<IdentificationNumberCategory> categories
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return categories;
	}

	/** {@inheritDoc} */
	@Override
	public IdentificationNumberCategory find(final String name) {
		IdentificationNumberCategory category = (IdentificationNumberCategory)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(
						FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return category;
	}

	/**{@inheritDoc} */
	@Override
	public IdentificationNumberCategory findExcluding(final String name,
			final IdentificationNumberCategory...
				identificationNumberCategoriesExcluded) {
		IdentificationNumberCategory category = (IdentificationNumberCategory)
				this.getSessionFactory()
					.getCurrentSession().getNamedQuery(
						FIND_EXCLUDING_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.setParameterList(
							IDNTFCTION_NMBR_CATEGORIES_EXCLUDED_PARAM_NAME,
							identificationNumberCategoriesExcluded)
					.uniqueResult();
		return category;
	}
}