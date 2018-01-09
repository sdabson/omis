package omis.organization.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.organization.dao.OrganizationDivisionDao;
import omis.organization.domain.OrganizationDivision;

/**
 * Hibernate implementation of data access object for organization divisions.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 18, 2016)
 * @since OMIS 3.0
 */

public class OrganizationDivisionDaoHibernateImpl 
		extends GenericHibernateDaoImpl<OrganizationDivision>
		implements OrganizationDivisionDao {

	/**
	 * Instantiates hibernate implementation of data access object organization 
	 * division.
	 * 
	 * @param sessionFactory
	 * @param entityName
	 */
	public OrganizationDivisionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	//no addtional functionality
}