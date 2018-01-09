package omis.courtcasecondition.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.courtcasecondition.dao.CourtCaseConditionDao;
import omis.courtcasecondition.domain.CourtCaseCondition;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;


/**
 * Hibernate Implementation for CourtCaseCondition DAO.
 *  
 * @author Jonny Santy
 * @version 0.1.0 (Jul 18, 2016)
 * @since OMIS 3.0
 */
public class CourtCaseConditionDaoHibernateImpl extends GenericHibernateDaoImpl
<CourtCaseCondition> implements CourtCaseConditionDao{

	/* Query names. */
	

	/* Parameter names. */

	/* Constructor. */
	
	/**
	 * Instantiates a data access object for a court case condition with the
	 * specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	protected CourtCaseConditionDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

}
