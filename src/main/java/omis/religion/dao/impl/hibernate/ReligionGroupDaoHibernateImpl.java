package omis.religion.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.religion.dao.ReligionGroupDao;
import omis.religion.domain.ReligionGroup;

public class ReligionGroupDaoHibernateImpl 
	extends GenericHibernateDaoImpl<ReligionGroup> implements ReligionGroupDao {

	private static final String FIND_QUERY_NAME = "findReligionGroup";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findReligionGroupExcluding";
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_RELIGION_GROUP_PARAM_NAME 
		= "excludedReligionGroup";

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * religion groups.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ReligionGroupDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public ReligionGroup find(final String name) {
		ReligionGroup religionGroup = (ReligionGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return religionGroup;
	}

	/** {@inheritDoc} */
	@Override
	public ReligionGroup findExcluding(final String name, 
			final ReligionGroup excludedReligionGroup) {
		ReligionGroup religionGroup = (ReligionGroup) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_RELIGION_GROUP_PARAM_NAME, 
						excludedReligionGroup)
				.uniqueResult();
		return religionGroup;
	}

}
