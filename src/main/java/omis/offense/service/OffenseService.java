package omis.offense.service;

import java.util.List;

import omis.offense.domain.Offense;

/**
 * Offense Service.
 * @author Joel Norris
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0
 */
public interface OffenseService {
	
	/**
	 * Returns an offense object with the specified id.
	 * 
	 * @param id id
	 * @return offense
	 */
	Offense findById(Long id);
	
	/**
	 * Save the specified offense.
	 * 
	 * @param offense offense
	 * @return offense
	 */
	Offense save(Offense offense);
	
	/**
	 * Removes the specified offense.
	 * 
	 * @param offense offense
	 */
	void remove(Offense offense);

	/**
	 * Finds all offenses.
	 * 
	 * @return list of offenses
	 */
	List<Offense> findAll();
}
