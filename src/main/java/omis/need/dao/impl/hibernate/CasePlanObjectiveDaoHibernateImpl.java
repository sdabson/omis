package omis.need.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.need.dao.CasePlanObjectiveDao;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.NeedDomain;
import omis.offender.domain.Offender;

import org.hibernate.SessionFactory;

/**
 * Case plan objective data access object hibernate implementation.
 * 
 * @author Kelly Churchill
 * @author Joel Norris
 * @version 0.1.0 (Jul 15, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CasePlanObjective> 
	implements CasePlanObjectiveDao {
	
	/* Query names. */
	private static final String FIND_CASEPLAN_OBJECTIVE_QUERY_NAME
		= "findCasePlanObjective";
	
	private static final String FIND_CASEPLAN_OBJECTIVE_EXCLUDING_QUERY_NAME
		= "findCasePlanObjectiveExcluding";
	/* Parameter names. */
	private static final String OFFENDER_PARAM_NAME = "offender";
	private static final String NAME_PARAM_NAME = "name";
	private static final String DOMAIN_PARAM_NAME = "domain";
	private static final String CASEPLAN_OBJECTIVE_PARAM_NAME = "objective";

	/**
	 * Instantiates CasePlanObjectiveDao with the specified session 
	 * factory and entity name.
	 * @param sessionFactory
	 * @param entityName
	 */
	public CasePlanObjectiveDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public CasePlanObjective find(Offender offender, String name,
			NeedDomain domain) {

		CasePlanObjective caseplanObjective = 
				(CasePlanObjective) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CASEPLAN_OBJECTIVE_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(DOMAIN_PARAM_NAME, domain)
				.uniqueResult();
		return caseplanObjective;
	}

	/** {@inheritDoc} */
	@Override
	public CasePlanObjective findExcluding(CasePlanObjective objective,
			Offender offender, String name, NeedDomain domain) {
		CasePlanObjective caseplanObjective = 
				(CasePlanObjective) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CASEPLAN_OBJECTIVE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(DOMAIN_PARAM_NAME, domain)
				.setParameter(CASEPLAN_OBJECTIVE_PARAM_NAME, objective)
				.uniqueResult();
		return caseplanObjective;
	}
}