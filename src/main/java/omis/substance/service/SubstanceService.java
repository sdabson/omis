package omis.substance.service;

import java.util.List;

import omis.substance.domain.Substance;

/**
 * Substance Service.
 * @author Joel Norris
 * @version 0.1.0 (Jun 19, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceService {
	
	/**
	 * Return the substance with the specified id.
	 * 
	 * @param id id
	 * @return substance
	 */
	Substance findById(final Long id);
	
	/**
	 * Save the specified substance.
	 * 
	 * @param substance substance
	 * @return substance
	 */
	Substance save(Substance substance);
	
	/**
	 * Remove the specified substance.
	 * 
	 * @param substance substance
	 */
	void remove(Substance substance);
	
	/**
	 * Return a list all substances.
	 * 
	 * @return list of substances
	 */
	List<Substance> findAll();

	/**
	 * Return a list of all valid substances.
	 * 
	 * @return list of substances
	 */
	List<Substance> findValid();
}