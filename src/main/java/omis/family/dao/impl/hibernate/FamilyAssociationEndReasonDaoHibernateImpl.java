package omis.family.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.family.dao.FamilyAssociationEndReasonDao;
import omis.family.domain.FamilyAssociationEndReason;

/**
 * Hibernate entity implementation of data access object for family association
 * end reason.
 * @author Joel Norris
 * @version 0.1.0 (Sep 9, 2013)
 * @since OMIS 3.0
 */
public class FamilyAssociationEndReasonDaoHibernateImpl 
	extends GenericHibernateDaoImpl<FamilyAssociationEndReason>
	implements FamilyAssociationEndReasonDao {

	/**
	 * Instantiates a default instance of family association end reason 
	 * data access object hibernate entity implementation.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FamilyAssociationEndReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
}
