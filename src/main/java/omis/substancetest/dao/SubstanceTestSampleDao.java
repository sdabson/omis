package omis.substancetest.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Substance Test Sample DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestSampleDao  
	extends GenericDao<SubstanceTestSample> {

	/**
	 * Return a list of all substance test samples with the specified
	 * offender.
	 * @param offender offender
	 * @return list of substance test samples
	 */
	List<SubstanceTestSample> findSamplesByOffender(Offender offender);

	/**
	 * Returns the substance test sample with matching properties.
	 * 
	 * @param offender offender
	 * @param collectionMethod collection method
	 * @param collectionDate collection date
	 * @return substance test sample
	 */
	SubstanceTestSample find(Offender offender, 
			SampleCollectionMethod collectionMethod, Date collectionDate);
	
	/**
	 * Returns the substance test sample with matching properties, excluding the
	 * specified substance test sample.
	 * 
	 * @param sample substance test sample
	 * @param offender offender
	 * @param collectionMethod collection method
	 * @param collectionDate collection date
	 * @return substance test sample
	 */
	SubstanceTestSample findExcluding(SubstanceTestSample sample,
			Offender offender, SampleCollectionMethod collectionMethod, 
			Date collectionDate);
}