package omis.offenderflag.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offenderflag.dao.OffenderFlagCategoryDao;
import omis.offenderflag.domain.OffenderFlagCategory;

/**
 * Hibernate implementation of data access object for flag categories.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public class OffenderFlagCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<OffenderFlagCategory>
		implements OffenderFlagCategoryDao {

	/* Query names. */
	
	private static final String FIND_REQUIRED_QUERY_NAME
		= "findRequiredOffenderFlagCategories";
	
	private static final String FIND_ALL = "findAllOffenderFlagCategories";
	
	private static final String FIND_OFFENDER_FLAG_CATEGORY_BY_NAME_QUERY_NAME 
		= "findOffenderFlagCategoryByName";
	
	private static final String 
	FIND_OFFENDER_FLAG_CATEGORY_BY_NAME_EXCLUDING_QUERY_NAME 
		= "findOffenderFlagCategoryByNameExcluding";
	
	/* Parameters. */
	
	private static final String OFFENDER_FLAG_CATEGORY_PARAM_NAME 
		= "offenderFlagCategory";
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * offender flag categories.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenderFlagCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<OffenderFlagCategory> findRequired() {
		@SuppressWarnings("unchecked")
		List<OffenderFlagCategory> categories = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_REQUIRED_QUERY_NAME)
				.list();
		return categories;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<OffenderFlagCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<OffenderFlagCategory> categories = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL).list();
		return categories;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlagCategory find(final String name) {
		OffenderFlagCategory category = (OffenderFlagCategory) 
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_OFFENDER_FLAG_CATEGORY_BY_NAME_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return category;
	}

	/** {@inheritDoc} */
	@Override
	public OffenderFlagCategory findExcluding(
			final OffenderFlagCategory offenderFlagCategory, String name) {
		OffenderFlagCategory category = (OffenderFlagCategory)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(
						FIND_OFFENDER_FLAG_CATEGORY_BY_NAME_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_FLAG_CATEGORY_PARAM_NAME, 
						offenderFlagCategory)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return category;
	}
}