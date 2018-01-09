package omis.health.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkRequirementRequestAssociationDao;
import omis.health.domain.LabWorkRequirementRequestAssociation;

/**
 * Hibernate implementation of data access object for lab work requirement
 * request associations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 3, 2014)
 * @since OMIS 3.0
 */
public class LabWorkRequirementRequestAssociationDaoHibernateImpl
		extends GenericHibernateDaoImpl<LabWorkRequirementRequestAssociation>
		implements LabWorkRequirementRequestAssociationDao {

	/**
	 * Instantiates a Hibernate implementation of data access object for lab
	 * work requirement request associations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkRequirementRequestAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
}