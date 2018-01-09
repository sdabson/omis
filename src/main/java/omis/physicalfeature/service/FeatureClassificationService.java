package omis.physicalfeature.service;

import java.util.List;

import omis.physicalfeature.domain.FeatureClassification;

/**
 * Service for Feature classification.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface FeatureClassificationService {

	/**
	 * Returns the feature classification with the specified id.
	 * @param id id
	 * @return featureClassification FeatureClassification
	 */
	FeatureClassification findById(Long id);
	
	/**
	 * Saves the specified feature classification.
	 * @param featureClassification feature classification
	 * @return featureClassification FeatureClassification
	 */
	FeatureClassification save(FeatureClassification featureClassification);
	
	/**
	 * Removes the specified feature classification.
	 * @param featureClassification feature classification.
	 */
	void remove(FeatureClassification featureClassification);
	
	/**
	 * Returns a list of all feature classifications.
	 * @return list of feature classifications
	 */
	List<FeatureClassification> findAll();

	/**
	 * Returns a list of all valid feature classifications.
	 * @return list of feature classifications
	 */
	List<FeatureClassification> findValid();
}