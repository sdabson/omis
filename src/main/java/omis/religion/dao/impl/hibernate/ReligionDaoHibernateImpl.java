package omis.religion.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.religion.dao.ReligionDao;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligionGroup;

/**
 * Hibernate implementation of data access object for religions.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public class ReligionDaoHibernateImpl
		extends GenericHibernateDaoImpl<Religion> implements ReligionDao {

	private static final String FIND_ALL_QUERY_NAME = "findReligions";
	
	private static final String FIND_QUERY_NAME = "findReligion";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findReligionExcluding";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String GROUP_PARAM_NAME = "group";
	
	private static final String EXCLUDED_RELIGION_PARAM_NAME 
		= "excludedReligion";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * religions.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ReligionDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Religion> findAll() {
		@SuppressWarnings("unchecked")
		List<Religion> religions = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return religions;
	}

	/** {@inheritDoc} */
	@Override
	public Religion find(final String name, final ReligionGroup group) {
		Religion religion = (Religion) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(GROUP_PARAM_NAME, group)
				.uniqueResult();
		return religion;
	}

	/** {@inheritDoc} */
	@Override
	public Religion findExcluding(final String name, final ReligionGroup group, 
			final Religion excludedReligion) {
		Religion religion = (Religion) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(GROUP_PARAM_NAME, group)
				.setParameter(EXCLUDED_RELIGION_PARAM_NAME, excludedReligion)
				.uniqueResult();
		return religion;
	}
}