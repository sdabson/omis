package omis.paroleeligibility.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleeligibility.dao.EligibilityStatusReasonDao;
import omis.paroleeligibility.domain.EligibilityStatusReason;

/**
 * Hibernate implementation of data access object for eligibility status reason.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public class EligibilityStatusReasonDaoHibernateImpl 
	extends GenericHibernateDaoImpl<EligibilityStatusReason>
	implements EligibilityStatusReasonDao {

	/* Query names */
	
	private static final String FIND_ELIGIBILITY_STATUS_REASONS_QUERY_NAME
		= "findEligibilityStatusReasons";
	
	private static final String FIND_ELIGIBILITY_STATUS_REASON_QUERY_NAME
		= "findEligibilityStatusReason";
	
	private static final String 
		FIND_ELIGIBILITY_STATUS_REASON_EXLCUDING_QUERY_NAME
			= "findEligibilityStatusReasonExcluding";
	
	/* Parameter names */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String EXCLUDED_REASON_PARAM_NAME
		= "excludedReason";
	
	/* Constructor */
	
	/**
	 * Instantiates a hibernate implementation of data access object for
	 * parole eligibility note.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EligibilityStatusReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations */
	
	/** {@inheritDoc} */
	@Override
	public List<EligibilityStatusReason> findEligibilityStatusReasons() {
		@SuppressWarnings("unchecked")
		List<EligibilityStatusReason> reasons = this.getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_ELIGIBILITY_STATUS_REASONS_QUERY_NAME)
		.list();
		return reasons;
	}

	/** {@inheritDoc} */
	@Override
	public EligibilityStatusReason findEligibilityStatusReason(
			final String name) {
		EligibilityStatusReason reason = (EligibilityStatusReason)
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ELIGIBILITY_STATUS_REASON_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public EligibilityStatusReason findEligibilityStatusReasonExcluding(
			final EligibilityStatusReason excludedReason,
			final String name) {
		EligibilityStatusReason reason = (EligibilityStatusReason)
		this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ELIGIBILITY_STATUS_REASON_EXLCUDING_QUERY_NAME)
			.setParameter(NAME_PARAM_NAME, name)
			.setParameter(EXCLUDED_REASON_PARAM_NAME, 
					excludedReason)
			.uniqueResult();
		return reason;
	}

}
