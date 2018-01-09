package omis.person.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.person.dao.SuffixDao;
import omis.person.domain.Suffix;

/**
 * Hibernate implementation of data access object for suffixes.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 13, 2013)
 * @since OMIS 3.0
 */
public class SuffixDaoHibernateImpl
		extends GenericHibernateDaoImpl<Suffix>
		implements SuffixDao {
	
	private static final String FIND_ALL_QUERY_NAME = "findAllValidSuffixes";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * suffixes.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SuffixDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Suffix> findAll() {
		@SuppressWarnings("unchecked")
		List<Suffix> suffixes = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return suffixes;
	}
}