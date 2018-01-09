package omis.separationneed.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.separationneed.dao.SeparationNeedReasonAssociationDao;
import omis.separationneed.domain.SeparationNeed;
import omis.separationneed.domain.SeparationNeedReason;
import omis.separationneed.domain.SeparationNeedReasonAssociation;

import org.hibernate.SessionFactory;

/**
 * Separation need reason association data access object hibernate
 * implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonAssociationDaoHibernateImpl
	extends GenericHibernateDaoImpl<SeparationNeedReasonAssociation>
	implements SeparationNeedReasonAssociationDao {

	/* Query names. */
	
	private static final String FIND_BY_SEPARATION_NEED_QUERY_NAME
		= "findSeparationNeedReasonAssociationsBySeparationNeed";
	
	private static final String 
	FIND_SEPARATION_NEED_REASON_ASSOCIATION_QUERY_NAME
		= "findSeparationNeedReasonAssociation";
	
	private static final String
	FIND_SEPARATION_NEED_REASON_ASSOCIATION_EXCLUDING_QUERY_NAME
		="findSeparationNeedReasonAssociationExcluding";
	
	/* Parameter names. */
	
	private static final String SEPARATION_NEED_PARAM_NAME = "separationNeed";
	
	private static final String REASON_PARAM_NAME = "separationNeedReason";
	
	private static final String SEPARATION_NEED_REASON_ASSOCIATION_PARAM_NAME
		= "separationNeedReasonAssociation";
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of separation need reason association
	 * data access object.
	 */
	public SeparationNeedReasonAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SeparationNeedReasonAssociation> findBySeparationNeed(
			SeparationNeed separationNeed) {
		@SuppressWarnings("unchecked")
		List<SeparationNeedReasonAssociation> associations =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_SEPARATION_NEED_QUERY_NAME)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.list();
		return associations;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedReasonAssociation find(SeparationNeed separationNeed,
			SeparationNeedReason reason) {
		SeparationNeedReasonAssociation sepNeedReasonAssociation =
				(SeparationNeedReasonAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_SEPARATION_NEED_REASON_ASSOCIATION_QUERY_NAME)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.setParameter(REASON_PARAM_NAME, reason)
				.uniqueResult();
		return sepNeedReasonAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public SeparationNeedReasonAssociation findExcluding(
			SeparationNeed separationNeed, SeparationNeedReason reason,
			SeparationNeedReasonAssociation association) {
		SeparationNeedReasonAssociation sepNeedReasonAssociation =
				(SeparationNeedReasonAssociation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_SEPARATION_NEED_REASON_ASSOCIATION_EXCLUDING_QUERY_NAME)
				.setParameter(SEPARATION_NEED_PARAM_NAME, separationNeed)
				.setParameter(REASON_PARAM_NAME, reason)
				.setParameter(SEPARATION_NEED_REASON_ASSOCIATION_PARAM_NAME,
						association)
				.uniqueResult();
		return sepNeedReasonAssociation;
	}
}