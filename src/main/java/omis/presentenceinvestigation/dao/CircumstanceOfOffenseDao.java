package omis.presentenceinvestigation.dao;

import omis.dao.GenericDao;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;

/**
 * CircumstanceOfOffenseDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 31, 2017)
 *@since OMIS 3.0
 *
 */
public interface CircumstanceOfOffenseDao
		extends GenericDao<CircumstanceOfOffense> {
	
	/**
	 * Returns a CircumstanceOfOffense found with specified OffenseSectionSummary
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @return CircumstanceOfOffense found with specified OffenseSectionSummary
	 */
	public CircumstanceOfOffense find(OffenseSectionSummary offenseSectionSummary);
	
	/**
	 * Returns a CircumstanceOfOffense found with specified OffenseSectionSummary
	 * excluding specified CircumstanceOfOffense
	 * @param offenseSectionSummary - OffenseSectionSummary
	 * @param circumstanceOfOffenseExcluded - CircumstanceOfOffense to exclude
	 * @return CircumstanceOfOffense found with specified OffenseSectionSummary
	 * excluding specified CircumstanceOfOffense
	 */
	public CircumstanceOfOffense findExcluding(
			OffenseSectionSummary offenseSectionSummary,
			CircumstanceOfOffense circumstanceOfOffenseExcluded);
	
}
