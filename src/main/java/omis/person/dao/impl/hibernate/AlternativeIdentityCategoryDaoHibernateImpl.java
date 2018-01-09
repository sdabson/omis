package omis.person.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.dao.AlternativeIdentityCategoryDao;
import omis.person.domain.AlternativeIdentityCategory;

/**
 * Hibernate implementation of data access object for alternative identity
 * categories.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 22, 2013)
 * @since OMIS 3.0
 */
public class AlternativeIdentityCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<AlternativeIdentityCategory>
		implements AlternativeIdentityCategoryDao {
	
	private static final String FIND_ALL_QUERY_NAME
			= "findAlternativeIdentityCategories";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * alternative identity categories.
	 * 
	 * @param sessionFactory
	 * @param entityName
	 */
	public AlternativeIdentityCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<AlternativeIdentityCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<AlternativeIdentityCategory> categories
				= this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_ALL_QUERY_NAME)
					.list();
		return categories;
	}
}