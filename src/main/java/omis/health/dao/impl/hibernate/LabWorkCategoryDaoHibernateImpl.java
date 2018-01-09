package omis.health.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.domain.LabWorkCategory;

import org.hibernate.SessionFactory;

/**
 * Lab work category data access object hibernate implementaiton.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public class LabWorkCategoryDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabWorkCategory>
	implements LabWorkCategoryDao {

	/**
	 * Instantiates a lab work category dao with the specified session factory
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkCategoryDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkCategory> findAll() {
		@SuppressWarnings("unchecked")
		List<LabWorkCategory> categories = this.getSessionFactory()
			.getCurrentSession().getNamedQuery("findLabWorkCategories")
			.list();
		return categories;
	}
}