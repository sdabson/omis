package omis.substanceuse.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.substanceuse.dao.UseAffirmationDao;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseAffirmation;
import omis.substanceuse.domain.UseAffirmationSource;

/**
 * Use affirmation data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 18, 2016)
 * @since OMIS 3.0 
 *
 */
public class UseAffirmationDaoHibernateImpl
	extends GenericHibernateDaoImpl<UseAffirmation> 
	implements UseAffirmationDao {

	/* Query names. */
	
	private static final String FIND_AFFIRMATIONS_BY_USE_QUERY_NAME
		= "findAffirmationsBySubstanceUse";
	
	private static final String FIND_QUERY_NAME = "findUseAffirmation";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = "findUseAffirmationExcluding";
	
	/* Parameter names. */
	
	private static final String SUBSTANCE_USE_PARAM_NAME = "substanceUse";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String SOURCE_PARAM_NAME = "source";
	
	private static final String AFFIRMATION_PARAM_NAME = "affirmation";
	
	/**
	 * Instantiates an instance of use affirmation data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public UseAffirmationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public List<UseAffirmation> findBySubstanceUse(SubstanceUse use) {
		@SuppressWarnings("unchecked")
		List<UseAffirmation> affirmations = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_AFFIRMATIONS_BY_USE_QUERY_NAME)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.list();
		return affirmations;
	}

	/** {@inheritDoc} */
	@Override
	public UseAffirmation find(final Date date, final SubstanceUse use, 
			final UseAffirmationSource source) {
		UseAffirmation useAffirmation = (UseAffirmation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.setParameter(SOURCE_PARAM_NAME, source);
		return useAffirmation;
	}

	/** {@inheritDoc} */
	@Override
	public UseAffirmation findExcluding(final UseAffirmation affirmation, 
			final Date date, final SubstanceUse use,
			final UseAffirmationSource source) {
		UseAffirmation useAffirmation = (UseAffirmation) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(AFFIRMATION_PARAM_NAME, affirmation)
				.setDate(DATE_PARAM_NAME, date)
				.setParameter(SUBSTANCE_USE_PARAM_NAME, use)
				.setParameter(SOURCE_PARAM_NAME, source);
		return useAffirmation;
	}

}