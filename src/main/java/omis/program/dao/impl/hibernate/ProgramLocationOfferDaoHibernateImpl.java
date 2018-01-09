package omis.program.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.program.dao.ProgramLocationOfferDao;
import omis.program.domain.ProgramLocationOffer;

/**
 * Hibernate implementation offering program at location.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramLocationOfferDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProgramLocationOffer>
		implements ProgramLocationOfferDao {

	/**
	 * Instantiates Hibernate implementation offering program at location.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProgramLocationOfferDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
}