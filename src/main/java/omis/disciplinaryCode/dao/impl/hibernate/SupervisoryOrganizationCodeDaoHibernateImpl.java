package omis.disciplinaryCode.dao.impl.hibernate;



import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.disciplinaryCode.dao.SupervisoryOrganizationCodeDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * SupervisoryOrganizationCodeDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class SupervisoryOrganizationCodeDaoHibernateImpl 
	extends GenericHibernateDaoImpl<SupervisoryOrganizationCode>
		implements SupervisoryOrganizationCodeDao {
	
	/* Query names */
	
	private static final String FIND_SUPERVISORY_ORGANIZATION_CODE_QUERY_NAME
		= "findSupervisoryOrganizationCode";
	
	private static final String 
		FIND_SUPERVISORY_ORGANIZATION_CODE_EXCLUDING_QUERY_NAME
			= "findSupervisoryOrganizationCodeExcluding";
	
	/* Parameter names */
	
	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME
		= "supervisoryOrganization";
	
	private static final String SUPERVISORY_ORGANIZATION_CODE_PARAM_NAME
	= "supervisoryOrganizationCode";

	private static final String CODE_PARAM_NAME = "code";
	
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected SupervisoryOrganizationCodeDaoHibernateImpl(
			SessionFactory sessionFactory, String entityName) {
		super(sessionFactory, entityName);
	}


	/**{@inheritDoc} */
	@Override
	public SupervisoryOrganizationCode find(
			final SupervisoryOrganization supervisoryOrganization, final DisciplinaryCode code) {
		SupervisoryOrganizationCode supervisoryOrganizationCode =
				(SupervisoryOrganizationCode) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_SUPERVISORY_ORGANIZATION_CODE_QUERY_NAME)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME, 
						supervisoryOrganization)
				.setParameter(CODE_PARAM_NAME, code)
				.uniqueResult();
		return supervisoryOrganizationCode;
	}

	/**{@inheritDoc} */
	@Override
	public SupervisoryOrganizationCode findExcluding(
			final SupervisoryOrganization supervisoryOrganization, final DisciplinaryCode code,
			final SupervisoryOrganizationCode supervisoryOrganizationCode) {
		SupervisoryOrganizationCode supervisoryOrganizationCodeExcluding =
				(SupervisoryOrganizationCode) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
						FIND_SUPERVISORY_ORGANIZATION_CODE_EXCLUDING_QUERY_NAME)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME, 
						supervisoryOrganization)
				.setParameter(CODE_PARAM_NAME, code)
				.setParameter(SUPERVISORY_ORGANIZATION_CODE_PARAM_NAME, 
						supervisoryOrganizationCode)
				.uniqueResult();
		return supervisoryOrganizationCodeExcluding;
	}

}
