/**
 * 
 */
package omis.specialneed.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.specialneed.dao.SpecialNeedSourceDao;
import omis.specialneed.domain.SpecialNeedSource;

import org.hibernate.SessionFactory;

/**
 * Hibernate entity configurable implementation of data access object for
 * source of special need.
 * 
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.2 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedSourceDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SpecialNeedSource> 
	implements SpecialNeedSourceDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_VALID_QUERY_NAME
		= "findAllValidSpecialNeedSources";
	
	private static final String FIND_QUERY_NAME 
		= "findSpecialNeedSource";
	
	private static final String FIND_EXCLDING_QUERY_NAME 
		= "findSpecialNeedSourceExcluding";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_SOURCE_PARAM_NAME 
		= "excludedSource";
	
	/* Constructor. */

	/**
	 * Instantiates a data access object for special need source with the 
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SpecialNeedSourceDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/*Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SpecialNeedSource> findAll() {
		@SuppressWarnings("unchecked")
		List<SpecialNeedSource> sources = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_VALID_QUERY_NAME)
				.list();
		return sources;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedSource find(final String name) {
		SpecialNeedSource specialNeedSource = 
				(SpecialNeedSource) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return specialNeedSource;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedSource findExcluding(final String name, 
			final SpecialNeedSource excludedSource) {
		SpecialNeedSource specialNeedSource = 
				(SpecialNeedSource) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_SOURCE_PARAM_NAME, excludedSource)
				.uniqueResult();
		return specialNeedSource;
	}
}