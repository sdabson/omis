package omis.immigration.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.immigration.dao.AlienResidenceDao;
import omis.immigration.domain.AlienResidence;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for alien residences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 28, 2014)
 * @since OMIS 3.0
 */
public class AlienResidenceDaoHibernateImpl
		extends GenericHibernateDaoImpl<AlienResidence>
		implements AlienResidenceDao {

	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findAlienResidenceByOffender";
	
	/* Model keys. */
	
	private static final String OFFENDER_MODEL_KEY
		= "offender";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * alien residences.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AlienResidenceDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */ 
	
	/** {@inheritDoc} */
	@Override
	public AlienResidence findByOffender(final Offender offender) {
		AlienResidence residence = (AlienResidence) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_OFFENDER_QUERY_NAME)
					.setParameter(OFFENDER_MODEL_KEY, offender).uniqueResult();
		return residence;
	}
}