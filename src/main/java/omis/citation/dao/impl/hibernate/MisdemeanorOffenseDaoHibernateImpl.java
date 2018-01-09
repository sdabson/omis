package omis.citation.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.citation.dao.MisdemeanorOffenseDao;
import omis.citation.domain.MisdemeanorOffense;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of data access object for misdemeanor offenses.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 09, 2016)
 * @since OMIS 3.0
 */

public class MisdemeanorOffenseDaoHibernateImpl 
		extends GenericHibernateDaoImpl<MisdemeanorOffense> 
		implements MisdemeanorOffenseDao {
	
	/* Query names. */
	
	private static final String FIND_OFFENSE_QUERY_NAME 
		= "findMisdemeanorOffense";
	
	private static final String FIND_ALL_QUERY_NAME 
		= "findMisdemeanorOffenses";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructor.*/
	
	/**
	 * Instantiates a data access object for misdemeanor offenses with the
	 * specified session factory and entity name.
	 *
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */

	public MisdemeanorOffenseDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	@Override
	public MisdemeanorOffense find(String name) {
		MisdemeanorOffense offense = (MisdemeanorOffense)getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_OFFENSE_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return offense;
	}
	
	@Override
	public List<MisdemeanorOffense> findAll() {
		@SuppressWarnings("unchecked")
		List<MisdemeanorOffense> offenses = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return offenses;
	}
}
