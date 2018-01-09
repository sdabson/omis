package omis.physicalfeature.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.physicalfeature.domain.FeatureClassification;

/**
 * Data access object for feature classification.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface FeatureClassificationDao 
	extends GenericDao<FeatureClassification> {

	/**
	 * Returns a list of all valid feature classifications.
	 * @return list of feature classifications
	 */
	List<FeatureClassification> findValid();
}