package omis.adaaccommodation.service;

import java.util.List;

import omis.adaaccommodation.domain.Disability;
import omis.adaaccommodation.domain.DisabilityClassificationCategory;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;

/**
 * Service for an ADA disability.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 17, 2015)
 * @since OMIS 3.0
 */
public interface DisabilityService {

	/**
	 * Creates a new disability.
	 * 
	 * @param offender offender
	 * @param description description
	 * @param disabilityClassificationCategory disability 
	 * classification category
	 * @return a new disability
	 * @throws DuplicateEntityFoundException
	 */
	Disability create(Offender offender, String description, 
			DisabilityClassificationCategory disabilityClassificationCategory) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Update a disability.
	 * 
	 * @param disability disability
	 * @param description description
	 * @param disabilityClassificationCategory disability 
	 * classification category
	 * @return updated disability
	 * @throws DuplicateEntityFoundException
	 */
	Disability update(Disability disability, String description, 
			DisabilityClassificationCategory disabilityClassificationCategory) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Remove a disability.
	 * 
	 * @param disability disability
	 */
	void remove(Disability disability);
	
	/**
	 * Returns a list of all disability classification categories.
	 * 
	 * @return list of disability classification categories
	 */
	List<DisabilityClassificationCategory> 
		findAllDisabilityClassificationCategories();
}