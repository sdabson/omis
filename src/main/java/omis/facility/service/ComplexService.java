package omis.facility.service;

import java.util.List;

import omis.facility.domain.Complex;
import omis.facility.domain.Facility;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public interface ComplexService {

	/**
	 * Finds the Complex with the specified ID.
	 * 
	 * @param id id
	 * @return complex
	 */
	Complex findById(Long id);
	
	/**
	 * Saves the specified complex.
	 * 
	 * @param complex complex
	 * @return the complex
	 */
	Complex save(Complex complex);
	
	/**
	 * Finds all complexes for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	List<Complex> findByFacility(Facility facility);

	/**
	 * Removes the specified complex.
	 * 
	 * @param complex complex
	 */
	void remove(final Complex complex);
}
