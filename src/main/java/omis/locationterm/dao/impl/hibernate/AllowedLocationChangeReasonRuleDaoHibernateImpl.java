package omis.locationterm.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.locationterm.dao.AllowedLocationChangeReasonRuleDao;
import omis.locationterm.domain.AllowedLocationChangeReasonRule;
import omis.locationterm.domain.LocationReason;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Hibernate implementation of data access object for allowed location change
 * reason rule.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class AllowedLocationChangeReasonRuleDaoHibernateImpl
		extends GenericHibernateDaoImpl<AllowedLocationChangeReasonRule>
		implements AllowedLocationChangeReasonRuleDao {
	
	/* Queries. */

	private static final String
	FIND_LOCATION_REASONS_FOR_CHANGE_ACTION_QUERY_NAME
		= "findLocationReasonsForChangeAction";
	
	/* Parameter names. */
	
	private static final String CHANGE_ACTION_PARAM_NAME = "changeAction";
	
	/* Constructors. */

	/**
	 * Instantiates Hibernate implementation of data access object for allowed
	 * location change.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AllowedLocationChangeReasonRuleDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<LocationReason> findLocationReasonsForChangeAction(
			final LocationTermChangeAction changeAction) {
		@SuppressWarnings("unchecked")
		List<LocationReason> reasons = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_LOCATION_REASONS_FOR_CHANGE_ACTION_QUERY_NAME)
				.setParameter(CHANGE_ACTION_PARAM_NAME, changeAction)
				.list();
		return reasons;
	}
}