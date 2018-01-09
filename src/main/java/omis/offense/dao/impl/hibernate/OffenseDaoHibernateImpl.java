package omis.offense.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offense.dao.OffenseDao;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;

import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Offense Dao Hibernate Implementation.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (Sept 15, 2017)
 * @since OMIS 3.0
 */
public class OffenseDaoHibernateImpl 
	extends GenericHibernateDaoImpl<Offense>
	implements OffenseDao {
	
	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME = "findOffenses";
	
	private static final String FIND_QUERY_NAME = "findOffense";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findOffenseExcluding";
	
	private static final String FIND_BY_QUERY_QUERY_NAME = 
			"findOffensesByQuery";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String SHORT_NAME_PARAM_NAME = "shortName";
	
	private static final String CLASSIFICATION_PARAM_NAME = "classification";
	
	private static final String VIOLATION_CODE_PARAM_NAME = "violationCode";
	
	private static final String EXCLUDED_OFFENSE_PARAM_NAME = "excludedOffense";
	
	private static final String QUERY_PARAM_NAME = "query";
	
	/* Constructors. */
	
	/**
	 * Instantiates a data access object for offense with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OffenseDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Offense> findAll() {
		@SuppressWarnings("unchecked")
		List<Offense> offenses = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return offenses;
	}

	/** {@inheritDoc} */
	@Override
	public Offense find(final String name, final String shortName, 
			final OffenseClassification classification, 
			final String violationCode) {
		Offense offense = (Offense) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SHORT_NAME_PARAM_NAME, shortName)
				.setParameter(CLASSIFICATION_PARAM_NAME, classification)
				.setParameter(VIOLATION_CODE_PARAM_NAME, violationCode)
				.uniqueResult();
		return offense;
	}

	/** {@inheritDoc} */
	@Override
	public Offense findExcluding(final String name, final String shortName,
			final OffenseClassification classification, 
			final String violationCode, final Offense excludedOffense) {
		Offense offense = (Offense) this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameter(SHORT_NAME_PARAM_NAME, shortName)
				.setParameter(CLASSIFICATION_PARAM_NAME, classification)
				.setParameter(VIOLATION_CODE_PARAM_NAME, violationCode)
				.setParameter(EXCLUDED_OFFENSE_PARAM_NAME, excludedOffense)
				.uniqueResult();
		return offense;
	}

	/** {@inheritDoc} */
	@Override
	public List<Offense> findByQuery(final String query) {
		@SuppressWarnings("unchecked")
		List<Offense> offenses = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_QUERY_QUERY_NAME)
				.setParameter(QUERY_PARAM_NAME, query)
				.setMaxResults(25)
				.list();
		return offenses;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Offense> findByQuery(final String query, final int maxResults) {
		@SuppressWarnings("unchecked")
		List<Offense> offenses = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_QUERY_QUERY_NAME)
				.setParameter(QUERY_PARAM_NAME, query)
				.setMaxResults(maxResults)
				.list();
		return offenses;
	}
}