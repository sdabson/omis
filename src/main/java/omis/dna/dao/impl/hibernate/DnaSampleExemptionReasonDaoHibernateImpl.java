package omis.dna.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.dna.dao.DnaSampleExemptionReasonDao;
import omis.dna.domain.DnaSampleExemptionReason;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for DNA sample exemptions. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 23, 2015)
 * @since OMIS 3.0
 */
public class DnaSampleExemptionReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<DnaSampleExemptionReason>
		implements DnaSampleExemptionReasonDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME
		= "findDnaSampleExemptionReasons";
	
	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for DNA
	 * sample exemptions.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DnaSampleExemptionReasonDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<DnaSampleExemptionReason> findAll() {
		@SuppressWarnings("unchecked")
		List<DnaSampleExemptionReason> reasons
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME).list();
		return reasons;
	}
}