package omis.substance.service;

import java.util.List;

import omis.substance.domain.SubstanceUseAdmission;

/**
 * Substance use admission service.
 * @author Joel Norris
 * @version 0.1.0 (Jun 24, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceUseAdmissionService {

	/**
	 * Save the specified substance.
	 * 
	 * @param substanceUseAdmission substance use admission
	 * @return substance use admission
	 */
	SubstanceUseAdmission save(SubstanceUseAdmission substanceUseAdmission);
	
	/**
	 * Remove the specified substance use admission.
	 * 
	 * @param substanceUseAdmission substance use admission
	 */
	void remove(SubstanceUseAdmission substanceUseAdmission);
	
	/**
	 * List all substance use admissions.
	 * 
	 * @return list of substance use admission
	 */
	List<SubstanceUseAdmission> findAll();
}
