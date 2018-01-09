package omis.victim.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.victim.dao.VictimNoteCategoryDao;
import omis.victim.domain.VictimNoteCategory;

/**
 * Hibernate implementation of data access object for victim note categories.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 23, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteCategoryDaoHibernateImpl
		extends GenericHibernateDaoImpl<VictimNoteCategory>
		implements VictimNoteCategoryDao {
	
	/* Query names. */

	private static final String FINAL_ALL_QUERY_NAME
			= "findVictimNoteCategories";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for victim
	 * note categories.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public VictimNoteCategoryDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<VictimNoteCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<VictimNoteCategory> categories = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FINAL_ALL_QUERY_NAME).list();
		return categories;
	}
}