package omis.dna.service;

import java.util.Date;
import java.util.List;

import omis.dna.domain.DnaSample;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Service for dna sample related operations.
 * @author Jason Nelson
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (February 20, 2015)
 * @since OMIS 3.0
 */
public interface DnaSampleService {
	
	/**
	 * Finds and returns a list of offender dna samples for the offender with
	 * the specified offender number.
	 * @param offender offender whose dna samples to find and return
	 * @return list of offender dna samples for offender with specified number
	 */
	List<DnaSample> findByOffender(Offender offender);
	
	/**
	 * Creates a new DNA sample for the specified offender.
	 * 
	 * @param offender offender
	 * @param collectionEmployee collection employee
	 * @param comments comments
	 * @param location location
	 * @param witness witness
	 * @param date date
	 * @param time time
	 * @return newly created DNA sample
	 * @throws DuplicateEntityFoundException thrown when a duplicate DNA sample
	 * is found.
	 */
	DnaSample create(Offender offender, String collectionEmployee, 
			String comments, String location, String witness, Date date, 
			Date time)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified DNA sample.
	 * 
	 * @param dnaSample DNA sample
	 * @param collectionEmployee collection employee
	 * @param comments comments
	 * @param location location
	 * @param witness witness
	 * @param date date
	 * @param time time
	 * @return updated DNA sample
	 * @throws DuplicateEntityFoundException
	 */
	DnaSample update(DnaSample dnaSample, String collectionEmployee, 
			String comments, String location, String witness, Date date,
			Date time)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the offender dna sample.
	 * @param dna sample offender dna sample to remove
	 */
	void remove(DnaSample offenderDnaSample);
}