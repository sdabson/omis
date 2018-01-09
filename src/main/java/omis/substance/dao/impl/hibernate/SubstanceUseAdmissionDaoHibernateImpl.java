package omis.substance.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substance.dao.SubstanceUseAdmissionDao;
import omis.substance.domain.SubstanceUseAdmission;

import org.hibernate.SessionFactory;

/**
 * Substance use admission DAO hibernate impl.
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public class SubstanceUseAdmissionDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SubstanceUseAdmission>
	implements SubstanceUseAdmissionDao {
	
	/**
	 * Instantiates a data access object for substance use admission with
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SubstanceUseAdmissionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
}