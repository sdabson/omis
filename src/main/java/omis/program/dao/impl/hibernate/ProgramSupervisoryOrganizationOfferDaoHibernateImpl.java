package omis.program.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.program.dao.ProgramSupervisoryOrganizationOfferDao;
import omis.program.domain.ProgramSupervisoryOrganizationOffer;

/**
 * Hibernate implementation of data access object offering program by
 * supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 13, 2016)
 * @since OMIS 3.0
 */
public class ProgramSupervisoryOrganizationOfferDaoHibernateImpl
		extends GenericHibernateDaoImpl<ProgramSupervisoryOrganizationOffer>
		implements ProgramSupervisoryOrganizationOfferDao {

	/**
	 * Instantiates Hibernate implementation of data access object offering
	 * program by supervisory organization.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public ProgramSupervisoryOrganizationOfferDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
}