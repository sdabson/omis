package omis.dna.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.dna.dao.DnaSampleExemptionDao;
import omis.dna.domain.DnaSampleExemption;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for DNA exemption samples.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 23, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleExemptionDaoHibernateImpl
		extends GenericHibernateDaoImpl<DnaSampleExemption>
		implements DnaSampleExemptionDao {
	
	/* Query names. */
	
	private static final String FIND_BY_OFFENDER_QUERY_NAME
		= "findDnaSampleExemptionByOffender";
	
	/* Parameter names. */
	
	private static final String OFFENDER_PARAM_NAME = "offender";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for DNA
	 * exemption samples.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DnaSampleExemptionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public DnaSampleExemption find(final Offender offender) {
		DnaSampleExemption exemption
			= (DnaSampleExemption) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.uniqueResult();
		return exemption;
	}
}