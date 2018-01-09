package omis.citation.service;

import java.util.List;

import omis.citation.domain.MisdemeanorCitation;
import omis.citation.domain.MisdemeanorDisposition;
import omis.citation.domain.MisdemeanorOffense;
import omis.datatype.Month;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 5, 2016)
 * @since OMIS 3.0
 */

public interface MisdemeanorCitationService {

	
	/**
	 * Creates a new offense.
	 * 
	 * @param name name
	 * @return created new offense
	 * @throws DuplicateEntityFoundException if new offense already exists.
	 */
	MisdemeanorOffense createOffense(String name, Boolean valid) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Saves a misdemeanor citation.
	 * 
	 * @param offender offender
	 * @param misdemeanorCitation misdemeanor citation
	 * @param state state
	 * @param city city
	 * @param day day
	 * @param month month
	 * @param year year
	 * @param counts counts
	 * @param offense offense
	 * @param disposition misdemeanor disposition
	 * @return saves a misdemeanor citation.
	 * @throws DuplicateEntityFoundException thrown when a duplicate misdemeanor
	 * citation is found
	 */	
	MisdemeanorCitation save(Offender offender, State state, City city, 
			Integer day, Month month, Integer year, Integer counts,
			MisdemeanorOffense offense, MisdemeanorDisposition disposition)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates a misdemeanor citation for the specified offender.
	 * 
	 * @param misdemeanorCitation misdemeanor citation
	 * @param state state
	 * @param city city
	 * @param day day
	 * @param month month
	 * @param year year
	 * @param counts counts
	 * @return updates a misdemeanor citation.
	 * @throws DuplicateEntityFoundException thrown when a duplicate misdemeanor
	 * citation is found
	 */	
	MisdemeanorCitation update(MisdemeanorCitation citation, State state, 
			City city, Integer day, Month month, Integer year, Integer counts,
			MisdemeanorOffense offense, MisdemeanorDisposition disposition)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes a misdemeanor citation for the specified offender.
	 * 
	 * @param citation misdemeanor citation
	 */		
	void remove(MisdemeanorCitation citation);
	
	/**
	 * Returns a list of misdemeanor citations for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of misdemeanor citations.
	 */
	List<MisdemeanorCitation> findByOffender(Offender offender);
	
	/**
	 * Returns a list of misdemeanor offenses for the specified offender.
	 * 
	 * @return list of misdemeanor offenses.
	 */
	List<MisdemeanorOffense> findOffenses();
	
	/**
	 * Returns a list of states.
	 * 
	 * @return list of states.
	 */
	List<State> findStates();
	
	/**
	 * Returns a specific state.
	 * 
	 * @return a specific state.
	 */
	State findHomeState();
	
	/**
	 * Returns a list of cities based on state.
	 * 
	 * @return list of cities.
	 */
	List<City> findCitiesByState(final State state);

}
