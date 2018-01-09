package omis.substance.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substance.dao.SubstanceLabDao;
import omis.substance.domain.SubstanceLab;

import org.hibernate.SessionFactory;

/**
 * Substance lab data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0
 */
public class SubstanceLabDaoHibernateImpl
	extends GenericHibernateDaoImpl<SubstanceLab>
	implements SubstanceLabDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findValidSubstanceLabs";
	
	/**
	 * Instantiates an instance of substance lab data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceLabDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SubstanceLab> findAll() {
		@SuppressWarnings("unchecked")
		List<SubstanceLab> labs = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		return labs;
	}
}