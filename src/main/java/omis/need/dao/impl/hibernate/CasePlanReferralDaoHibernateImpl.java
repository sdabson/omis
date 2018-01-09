package omis.need.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.need.dao.CasePlanReferralDao;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.CasePlanReferral;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDepartment;

import org.hibernate.SessionFactory;

/**
 * Case plan referral data access object hibernate implementation 
 * @author Kelly Churchill
 * @author Joel Norris
 * @version 0.1.0
 * @since OMIS 3.0
 */
public class CasePlanReferralDaoHibernateImpl 
	extends GenericHibernateDaoImpl<CasePlanReferral> 
	implements CasePlanReferralDao {
	
	/* Query names */
	private static final String FIND_CASEPLAN_REFERRAL_BY_OBJECTIVE_QUERY_NAME
	= "findReferralsByCasePlanObjective";
	private static final String FIND_CASEPLAN_REFERRAL_QUERY_NAME
	= "findCasePlanReferral";
	private static final String FIND_CASEPLAN_REFERRAL_EXCLUDING_QUERY_NAME
	= "findCasePlanReferralExcluding";
	
	/* Param names */
	private static final String OBJECTIVE_PARAM_NAME = "objective";
	private static final String DATE_PARAM_NAME = "date";
	private static final String ORGANIZATION_PARAM_NAME = "organization";
	private static final String ORGANIZATION_DEPARTMENT_PARAM_NAME 
		= "department";
	private static final String CASEPLAN_REFRRAL_PARAM_NAME = "referral";
	
	/*Property Names*/
	private static final String ORGANIZATION_DEPARTMENT_PROPERTY_NAME 
		= "department";
	
	/**
	 * Instantiates an instance of Case plan referral data access object with 
	 * the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CasePlanReferralDaoHibernateImpl(SessionFactory sessionFactory,
			String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<CasePlanReferral> findByObjective(CasePlanObjective objective) {
		@SuppressWarnings("unchecked")
		List<CasePlanReferral> referrals = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_CASEPLAN_REFERRAL_BY_OBJECTIVE_QUERY_NAME)
				.setParameter(OBJECTIVE_PARAM_NAME, objective)
				.list();
		return referrals;
	}

	/**{@inheritDoc}*/
	@Override
	public CasePlanReferral find(final CasePlanObjective objective, 
			final Date date, final Organization organization, 
			final OrganizationDepartment department) {
		CasePlanReferral referral = (CasePlanReferral)this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CASEPLAN_REFERRAL_QUERY_NAME)
			.setParameter(OBJECTIVE_PARAM_NAME, objective)
			.setDate(DATE_PARAM_NAME, date)
			.setParameter(ORGANIZATION_PARAM_NAME, organization)
			.setParameter(ORGANIZATION_DEPARTMENT_PARAM_NAME, department,
				this.getEntityPropertyType(
					ORGANIZATION_DEPARTMENT_PROPERTY_NAME))
			.uniqueResult();
		return referral;
	}
	
	/**{@inheritDoc}*/
	@Override
	public CasePlanReferral findExcluding(final CasePlanReferral referral, final CasePlanObjective objective, 
			final Date date, final Organization organization, 
			final OrganizationDepartment department) {
		CasePlanReferral casePlanReferral = (CasePlanReferral)this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_CASEPLAN_REFERRAL_EXCLUDING_QUERY_NAME)
			.setParameter(OBJECTIVE_PARAM_NAME, objective)
			.setDate(DATE_PARAM_NAME, date)
			.setParameter(ORGANIZATION_PARAM_NAME, organization)
			.setParameter(CASEPLAN_REFRRAL_PARAM_NAME, referral)
			.setParameter(ORGANIZATION_DEPARTMENT_PARAM_NAME, department,
				this.getEntityPropertyType(
					ORGANIZATION_DEPARTMENT_PROPERTY_NAME))
			.uniqueResult();
		return casePlanReferral;
	}
}