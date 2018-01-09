package omis.dna.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.dna.domain.DnaSample;
import omis.offender.domain.Offender;

/**
 * Data access object for offender dna samples.
 * @author Jason Nelson
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.1 (February 23, 2015)
 * @since OMIS 3.0
 */
public interface DnaSampleDao extends GenericDao<DnaSample> {
	
	/**
	 * Finds and returns a list of offender dna samples by offender.
	 * @param offender offender whose dna samples to find
	 * @return dna samples for specified offender
	 */
	List<DnaSample> findByOffender(Offender offender);
	
	/**
	 * Returns the DNA sample with matching properties.
	 * 
	 * @param offender offender
	 * @param collectionEmployee collection employee
	 * @param date date
	 * @param location location
	 * @return matching DNA sample
	 */
	DnaSample find(Offender offender, String collectionEmployee, Date date,
			String location);
	
	/**
	 * Returns the DNA sample with matching properties, excluding the specified
	 * DNA sample.
	 * 
	 * @param dnaSample DNA sample
	 * @param offender offender
	 * @param collectionEmployee collection employee
	 * @param date date
	 * @param location location
	 * @return matching DNA sample
	 */
	DnaSample findExcluding(DnaSample dnaSample, Offender offender, 
			String collectionEmployee, Date date, String location);
}