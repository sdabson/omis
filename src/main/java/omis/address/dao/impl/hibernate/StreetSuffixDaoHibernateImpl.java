package omis.address.dao.impl.hibernate;

import java.util.List;

import omis.address.dao.StreetSuffixDao;
import omis.address.domain.StreetSuffix;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for street suffixes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public class StreetSuffixDaoHibernateImpl
		extends GenericHibernateDaoImpl<StreetSuffix>
		implements StreetSuffixDao {

	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findAllStreetSuffixes";
	
	private static final String FIND_QUERY_NAME = "findStreetSuffix";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructor. */
	
	/**
	 * Instantiates an Hibernate implementation of access object for street
	 * suffixes with specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public StreetSuffixDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<StreetSuffix> findAll() {
		@SuppressWarnings("unchecked")
		List<StreetSuffix> suffixes = getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return suffixes;
	}

	/** {@inheritDoc} */
	@Override
	public StreetSuffix find(final String name) {
		StreetSuffix streetSuffix = (StreetSuffix) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return streetSuffix;
	}
}