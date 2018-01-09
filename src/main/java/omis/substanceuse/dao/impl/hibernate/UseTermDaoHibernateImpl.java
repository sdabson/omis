package omis.substanceuse.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substanceuse.dao.UseTermDao;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseTerm;
import omis.substanceuse.domain.UseTermSource;

/**
 * Use term data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 18, 2016)
 * @since OMIS 3.0
 */
public class UseTermDaoHibernateImpl 
	extends GenericHibernateDaoImpl<UseTerm> 
	implements UseTermDao {
	
	/* Query names. */
	
	private static final String FIND_TERMS_BY_SUBSTANCE_USE_QUERY_NAME
		= "findUseTermsBySubstanceUse";
	private static final String FIND_QUERY_NAME = "findUseTerm";
	private static final String FIND_EXCLUDING_QUERY_NAME 
		= "findUseTermExcluding";
	private static final String FIND_BY_USE_IN_DATERANGE_QUERY_NAME
		= "findUseTermsByUseInDateRange";
	
	/* Parameter names. */
	
	private static final String SUBSTANCE_USE_PARAM_NAME = "substanceUse";
	private static final String START_DATE_PARAM_NAME = "startDate";
	private static final String END_DATE_PARAM_NAME = "endDate";
	private static final String TERM_SOURCE_PARAM_NAME = "source";
	private static final String USE_TERM_PARAM_NAME = "useTerm";

	/**
	 * Instantiates an instance of use term data access object with specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UseTermDaoHibernateImpl(
			final SessionFactory sessionFactory, 
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<UseTerm> findUseTermsBySubstanceUse(final SubstanceUse use) {
		@SuppressWarnings("unchecked")
		List<UseTerm> useTerms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_TERMS_BY_SUBSTANCE_USE_QUERY_NAME)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.list();
		return useTerms;
	}

	/** {@inheritDoc} */
	@Override
	public UseTerm find(Date startDate, SubstanceUse use, UseTermSource source) {
		UseTerm useTerm = (UseTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.setParameter(TERM_SOURCE_PARAM_NAME, source)
				.uniqueResult();
		return useTerm;
	}

	/** {@inheritDoc} */
	@Override
	public UseTerm findExcluding(UseTerm term, Date startDate, SubstanceUse use, UseTermSource source) {
		UseTerm useTerm = (UseTerm) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(USE_TERM_PARAM_NAME, term)
				.setParameter(START_DATE_PARAM_NAME, startDate)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.setParameter(TERM_SOURCE_PARAM_NAME, source)
				.uniqueResult();
		return useTerm;
	}

	/** {@inheritDoc} */
	@Override
	public List<UseTerm> findByUseInDateRange(final SubstanceUse use, 
			final Date startDate, final Date endDate) {
		@SuppressWarnings("unchecked")
		List<UseTerm> terms = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_USE_IN_DATERANGE_QUERY_NAME)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.list();
		return terms;
	}
}