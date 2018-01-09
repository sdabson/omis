package omis.caseload.dao.impl.hibernate;

import java.util.List;

import omis.caseload.dao.CaseloadDao;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of the caseload data access object.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.0 (May 18, 2017)
 * @since OMIS 3.0
 */
public class CaseloadDaoHibernateImpl  
				extends GenericHibernateDaoImpl<Caseload> 
				implements CaseloadDao {
	
	/* Queries. */
	private static final String FIND_CASELOAD_QUERY_NAME = "findCaseload";
	private static final String FIND_CASELOAD_EXCLUDING_QUERY_NAME 
					= "findCaseloadExcluding";
	
	/* Parameters. */
	private static final String NAME_PARAM_NAME = "name";
	private static final String CATEGORY_PARAM_NAME = "category";
	private static final String CASELOAD_PARAM_NAME = "caseload";

	/**
	 * Instantiates a hibernate implementation of the data access object for
	 * caseload.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CaseloadDaoHibernateImpl(final SessionFactory sessionFactory,
					final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<Caseload> findAll() {
		// TODO Implement or remove - SV
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/** {@inheritDoc} */
	@Override
	public Caseload find(final String name, final CaseworkCategory category) {
		Caseload caseload = (Caseload) this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
								FIND_CASELOAD_QUERY_NAME)
						.setParameter(NAME_PARAM_NAME, name)
						.setParameter(CATEGORY_PARAM_NAME, category)
						.uniqueResult();
		return caseload;
		 
	}

	/** {@inheritDoc} */
	@Override
	public Caseload findExcluding(final String name, 
					final CaseworkCategory category, final Caseload caseload) {
		Caseload thisCaseload = (Caseload) this.getSessionFactory()
						.getCurrentSession().getNamedQuery(
						FIND_CASELOAD_EXCLUDING_QUERY_NAME)
						.setParameter(NAME_PARAM_NAME, name)
						.setParameter(CATEGORY_PARAM_NAME, category)
						.setParameter(CASELOAD_PARAM_NAME, caseload)
						.uniqueResult();
		return thisCaseload;
	}
}