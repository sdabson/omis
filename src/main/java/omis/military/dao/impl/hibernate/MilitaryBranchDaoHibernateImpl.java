package omis.military.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.military.dao.MilitaryBranchDao;
import omis.military.domain.MilitaryBranch;

import org.hibernate.SessionFactory;

/**
 * Military branch data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 14, 2015)
 * @since OMIS 3.0
 */
public class MilitaryBranchDaoHibernateImpl 
extends GenericHibernateDaoImpl<MilitaryBranch>
implements MilitaryBranchDao {
	
	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findMilitaryBranch";
	
	private static final String FIND_ALL_MILITARY_BRANCHES_QUERY_NAME
		= "findAllMilitaryBranches";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	private static final String SHORT_NAME_PARAM_NAME = "shortName";
	
	private static final String VALID_PARAM_NAME = "valid";
	
	/* Constructors. */
	
	/**
	 * Instantiates an instance of military branch data access object with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MilitaryBranchDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<MilitaryBranch> findAll() {
		@SuppressWarnings("unchecked")
		List<MilitaryBranch> branches = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALL_MILITARY_BRANCHES_QUERY_NAME)
				.list();
		return branches;
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryBranch find(String name, String shortName, Boolean valid) {
		MilitaryBranch branch = (MilitaryBranch) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SHORT_NAME_PARAM_NAME, shortName)
				.setParameter(VALID_PARAM_NAME, valid)
				.uniqueResult();
		return branch;
	}
}