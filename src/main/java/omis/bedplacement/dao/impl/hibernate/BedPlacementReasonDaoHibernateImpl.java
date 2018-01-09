package omis.bedplacement.dao.impl.hibernate;

import java.util.List;

import omis.bedplacement.dao.BedPlacementReasonDao;
import omis.bedplacement.domain.BedPlacementReason;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for movement reason.
 * 
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.1 (Aug 3, 2017)
 * @since OMIS 3.0
 */
public class BedPlacementReasonDaoHibernateImpl
	extends GenericHibernateDaoImpl<BedPlacementReason>
	implements BedPlacementReasonDao {

	/* Query names. */
	
	private static final String FIND_VALID_BED_PLACEMENT_REASONS_QUERY_NAME
		= "findValidBedPlacementReasons";
	
	private static final String FIND_QUERY_NAME = "findBedPlacementReason";
	
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findBedPlacementReasonExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_REASON_PARAM_NAME = "excludedReason";
	
	/**
	 * Instantiates a data access object for movement reason with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BedPlacementReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override 
	public List<BedPlacementReason> findAll() {
		@SuppressWarnings("unchecked")
		List<BedPlacementReason> bedPlacementReasons = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_VALID_BED_PLACEMENT_REASONS_QUERY_NAME)
				.list();
		return bedPlacementReasons;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacementReason find(final String name) {
		BedPlacementReason reason = (BedPlacementReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public BedPlacementReason findExcluding(final String name, 
			final BedPlacementReason excludedReason) {
		BedPlacementReason reason = (BedPlacementReason) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(EXCLUDED_REASON_PARAM_NAME, excludedReason)
				.uniqueResult();
		return reason;
	}
}