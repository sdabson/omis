package omis.disciplinaryCode.dao.impl.hibernate;





import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.disciplinaryCode.dao.DisciplinaryCodeDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeDaoHibernateImpl 
		extends GenericHibernateDaoImpl<DisciplinaryCode>
		implements DisciplinaryCodeDao {

	
	/* Query names */
	
	private static final String FIND_DISCIPLINARY_CODE_QUERY_NAME 
		= "findDisciplinaryCode";
	
	private static final String FIND_DISCIPLINARY_CODE_EXCLUDING_QUERY_NAME 
		= "findDisciplinaryCodeExcluding";
	
	private static final String 
FIND_DISCIPLINARY_CODES_BY_SUPERVISORY_ORGANIZATION_AND_EFFECTIVE_DATE_QUERY_NAME
		= "findDisciplinaryCodesBySupervisoryOrganizationAndEffectiveDate";
	
	/* Parameter names */
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String DISCIPLINARY_CODE_PARAM_NAME
		= "disciplinaryCode";
	
	private static final String SUPERVISORY_ORGANIZATION_PARAMETER_NAME 
		= "supervisoryOrganization";

	private static final String EFFECTIVE_DATE_PARAMETER_NAME = "effectiveDate";

	private static final String SEARCH_DISCIPLINARY_CODE_QUERY_NAME 
		= "searchDisciplinaryCode";

	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected DisciplinaryCodeDaoHibernateImpl(SessionFactory sessionFactory, 
			String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCode find(final String value) {
		DisciplinaryCode disciplinaryCode 
			= (DisciplinaryCode) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DISCIPLINARY_CODE_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.uniqueResult();
		return disciplinaryCode;
	}

	/**{@inheritDoc} */
	@Override
	public DisciplinaryCode findExcluding(final String value, 
			final DisciplinaryCode disciplinaryCode) {
		DisciplinaryCode disciplinaryCodeExcluding 
			= (DisciplinaryCode) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_DISCIPLINARY_CODE_EXCLUDING_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameter(DISCIPLINARY_CODE_PARAM_NAME, disciplinaryCode)
				.uniqueResult();
		return disciplinaryCodeExcluding;
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCode> search(String value) {
		@SuppressWarnings("unchecked")
		List<DisciplinaryCode> codes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(SEARCH_DISCIPLINARY_CODE_QUERY_NAME)
				.setParameter(VALUE_PARAM_NAME, value)
				.list();
		
		
		return codes;
	}

	/**{@inheritDoc} */
	@Override
	public List<DisciplinaryCode> findBySupervisoryOrganizationAndDate(
			final SupervisoryOrganization supervisoryOrganization,
			final Date effectiveDate) {
		@SuppressWarnings("unchecked")
		List<DisciplinaryCode> codes = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
FIND_DISCIPLINARY_CODES_BY_SUPERVISORY_ORGANIZATION_AND_EFFECTIVE_DATE_QUERY_NAME)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAMETER_NAME, 
						supervisoryOrganization)
				.setTimestamp(EFFECTIVE_DATE_PARAMETER_NAME, 
						effectiveDate)
				.list();
		
		
		return codes;
	}

}
