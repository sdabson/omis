package omis.guardianship.dao.impl.hibernate;

import java.util.Date;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.guardianship.dao.GuardianshipDao;
import omis.guardianship.domain.Guardianship;
import omis.relationship.domain.Relationship;

import org.hibernate.SessionFactory;

/**
 * Guardianship DAO Hibernate Impl.
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.1 (June 8, 2013)
 * @since OMIS 3.0
 */
public class GuardianshipDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Guardianship>
	implements GuardianshipDao {

	/**
	 * Instantiates a data access object for guardian with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory sesion factory
	 * @param entityName entity name
	 */
	public GuardianshipDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public Guardianship findGuardianship(Relationship relationship, 
		Date startDate, Date endDate) {
		Guardianship guardianship = (Guardianship) getSessionFactory()
			.getCurrentSession()
			.getNamedQuery("findGuardianship")
			.setParameter("relationship", relationship)
			.setParameter("startDate", startDate)
			.setParameter("endDate", endDate)
			.uniqueResult();
		return guardianship;
	}
}