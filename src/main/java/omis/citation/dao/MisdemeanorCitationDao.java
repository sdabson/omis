package omis.citation.dao;

import java.util.List;

import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.dao.GenericDao;
import omis.datatype.Month;
import omis.offender.domain.Offender;
import omis.region.domain.State;

/**
 * Misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public interface MisdemeanorCitationDao extends GenericDao<MisdemeanorCitation>{

	/**
	 * Finds misdemeanor citations excluding the specified citation.
	 * 
	 * @param offender offender
	 * @param state - state
	 * @param city - city
	 * @param day - day
	 * @param month - month
	 * @param year - year
	 * @param offense - misdemeanor offense
	 * @param disposition - misdemeanor disposition
	 * @param excludedCitation - citation to exclude
	 * @return returns the citation with the specified properties or
	 * {@code null} if not found
	 */
	MisdemeanorCitation findExcluding(Offender offender, State state, 
			MisdemeanorOffense offense, Integer day, Month month, 
			Integer	year, MisdemeanorDisposition disposition,
			MisdemeanorCitation excludedCitation);

	/**
	 * Finds a misdemeanor citation.
	 * 
	 * @param citation - misdemeanor citation
	 * @param state - state
	 * @param city - city
	 * @param day - day
	 * @param month - month
	 * @param year - year
	 * @param offense - misdemeanor offense
	 * @param disposition - misdemeanor disposition
	 * @return Finds a misdemeanor citation.
	 */
	MisdemeanorCitation find(Offender offender,	MisdemeanorOffense offense, 
			State state, Integer day, Month month, Integer year, 
			MisdemeanorDisposition disposition);
	
	/**
	 * Returns a list of misdemeanor citations for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of misdemeanor citations by offender.
	 */
	List<MisdemeanorCitation> findByOffender(Offender offender);	

}
