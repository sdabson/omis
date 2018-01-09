package omis.military.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.military.dao.MilitaryDischargeStatusDao;
import omis.military.domain.MilitaryDischargeStatus;

import org.hibernate.SessionFactory;

public class MilitaryDischargeStatusDaoHibernateImpl
extends GenericHibernateDaoImpl<MilitaryDischargeStatus>
implements MilitaryDischargeStatusDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findDischargeStatus";
	
	private static final String FIND_ALL_DISCHARGE_STATUSES_QUERY_NAME
		= "findAllMilitaryDischargeStatuses";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String VALID_PARAM_NAME = "valid";
	
	/* Constructors. */
	
	/**
	 * Instantiates a military discharge status data access object with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public MilitaryDischargeStatusDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@Override
	public List<MilitaryDischargeStatus> findAll() {
		@SuppressWarnings("unchecked")
		List<MilitaryDischargeStatus> dischargeStatuses = 
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_DISCHARGE_STATUSES_QUERY_NAME)
				.list();
		return dischargeStatuses;
	}

	/** {@inheritDoc} */
	@Override
	public MilitaryDischargeStatus find(final String name,
			final Boolean valid) {
		MilitaryDischargeStatus status = (MilitaryDischargeStatus)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(VALID_PARAM_NAME, valid)
				.uniqueResult();
		return status;
	}
}