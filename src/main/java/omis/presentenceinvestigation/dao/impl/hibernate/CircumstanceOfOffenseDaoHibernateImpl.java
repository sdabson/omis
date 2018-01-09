package omis.presentenceinvestigation.dao.impl.hibernate;


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.presentenceinvestigation.dao.CircumstanceOfOffenseDao;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;

/**
 * CircumstanceOfOffenseDaoHibernateImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public class CircumstanceOfOffenseDaoHibernateImpl
	extends GenericHibernateDaoImpl<CircumstanceOfOffense>
		implements CircumstanceOfOffenseDao {
	
	private static final String FIND_CIRCUMSTANCE_OF_OFFENSE_QUERY_NAME =
			"findCircumstanceOfOffense";
	
	private static final String FIND_CIRCUMSTANCE_OF_OFFENSE_EXCLUDING_QUERY_NAME =
			"findCircumstanceOfOffenseExcluding";
	
	private static final String OFFENSE_SECTION_SUMMARY_PARAM_NAME =
			"offenseSectionSummary";
	
	private static final String CIRCUMSTANCE_OF_OFFENSE_PARAM_NAME =
			"circumstanceOfOffense";
	
	/**
	 * @param sessionFactory
	 * @param entityName
	 */
	protected CircumstanceOfOffenseDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public CircumstanceOfOffense find(
			final OffenseSectionSummary offenseSectionSummary) {
		CircumstanceOfOffense circumstanceOfOffense = (CircumstanceOfOffense)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CIRCUMSTANCE_OF_OFFENSE_QUERY_NAME)
				.setParameter(OFFENSE_SECTION_SUMMARY_PARAM_NAME,
						offenseSectionSummary)
				.uniqueResult();
		
		return circumstanceOfOffense;
	}

	/**{@inheritDoc} */
	@Override
	public CircumstanceOfOffense findExcluding(
			final OffenseSectionSummary offenseSectionSummary,
			final CircumstanceOfOffense circumstanceOfOffenseExcluded) {
		CircumstanceOfOffense circumstanceOfOffense = (CircumstanceOfOffense)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_CIRCUMSTANCE_OF_OFFENSE_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENSE_SECTION_SUMMARY_PARAM_NAME,
						offenseSectionSummary)
				.setParameter(CIRCUMSTANCE_OF_OFFENSE_PARAM_NAME,
						circumstanceOfOffenseExcluded)
				.uniqueResult();
		
		return circumstanceOfOffense;
	}

}
