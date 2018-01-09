package omis.physicalfeature.service;

import java.util.List;

import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;

/**
 * Service for physical feature.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeatureService {

	/**
	 * Returns the physical feature with the specified id.
	 * @param id id
	 * @return physical feature
	 */
	PhysicalFeature findById(Long id);
	
	/**
	 * Saves the specified physical feature.
	 * @param physicalFeature physical feature
	 * @return physical feature
	 */
	PhysicalFeature save(PhysicalFeature physicalFeature);
	
	/**
	 * Removes the specified physical feature.
	 * @param physicalFeature physical feature
	 */
	void remove(PhysicalFeature physicalFeature);
	
	/**
	 * Returns a list of all physical features.
	 * @return list of physical features
	 */
	List<PhysicalFeature> findAll();

	/**
	 * Returns a list of all physical features with the specified feature
	 * classification.
	 * @param featureClassification feature classification
	 * @return list of physical features
	 */
	List<PhysicalFeature> findPhysicalFeatureByClassification(
			FeatureClassification featureClassification);
}