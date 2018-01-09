package omis.workassignment.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.workassignment.dao.FenceRestrictionDao;
import omis.workassignment.domain.FenceRestriction;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for fence restriction.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class FenceRestrictionDaoHibernateImpl
		extends GenericHibernateDaoImpl<FenceRestriction>
		implements FenceRestrictionDao {
	/* Queries. */
	private static final String FIND_FENCE_RESTRICTIONS_QUERY_NAME 
		= "findFenceRestrictions";
	
	private static final String FIND_FENCE_RESTRICTION_QUERY_NAME 
		= "findFenceRestriction";
	
	private static final String FIND_FENCE_RESTRICTION_EXCLUDING_QUERY_NAME 
		= "findFenceRestrictionExcluding";

	/* Parameters. */
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_FENCE_RESTRICTION_PARAM_NAME 
		= "excludedFenceRestriction";
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * fence restriction
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FenceRestrictionDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<FenceRestriction> findAll() {
		@SuppressWarnings("unchecked")
		List<FenceRestriction> fenceRestrictions = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_FENCE_RESTRICTIONS_QUERY_NAME)
			.list();
		return fenceRestrictions;
	}

	/** {@inheritDoc} */
	@Override
	public FenceRestriction find(final String name) {
		FenceRestriction fenceRestriction = 
				(FenceRestriction) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_FENCE_RESTRICTION_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return fenceRestriction;
	}

	/** {@inheritDoc} */
	@Override
	public FenceRestriction findExcluding(final String name, 
			final FenceRestriction excludedFenceRestriction) {
		FenceRestriction fenceRestriction = 
				(FenceRestriction) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_FENCE_RESTRICTION_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_FENCE_RESTRICTION_PARAM_NAME, 
						excludedFenceRestriction)
				.uniqueResult();
		return fenceRestriction;
	}
}