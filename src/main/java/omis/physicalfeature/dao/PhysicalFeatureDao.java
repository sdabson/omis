package omis.physicalfeature.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;

/**
 * Data access object for physical feature.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureDao
	extends GenericDao<PhysicalFeature> {

	/**
	 * Returns a list of all physical features with the specified feature
	 * classification.
	 * @param featureClassification feature classification
	 * @return list of physical features
	 */
	List<PhysicalFeature> findPhysicalFeatureByClassification(
			FeatureClassification featureClassification);
}