package omis.identificationnumber.service;

import java.util.Date;
import java.util.List;

import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.domain.IdentificationNumberCategory;
import omis.identificationnumber.domain.IdentificationNumberIssuer;
import omis.offender.domain.Offender;

/**
 * Service for identification numbers.
 *
 * @author Stephen Abson
 * @author Annie Jacques
 * @version 0.0.2
 * @since OMIS 3.0
 */
public interface IdentificationNumberService {

	/**
	 * Creates identification number.
	 * 
	 * @param offender offender
	 * @param issuer issuer
	 * @param category category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @return created identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException - if Identification Number Category is
	 * 'SINGLE' and date range conflicts with an existing identification number
	 * of the same category
 	 */
	IdentificationNumber create(
			Offender offender, IdentificationNumberIssuer issuer,
			IdentificationNumberCategory category,
			String value, Date issueDate, Date expireDate)
				throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Updates identification number.
	 * 
	 * @param identificationNumber identification number to update
	 * @param issuer issuer
	 * @param category category
	 * @param value value
	 * @param issueDate issue date
	 * @param expireDate expire date
	 * @return updated identification number
	 * @throws DuplicateEntityFoundException if identification number exists
	 * @throws DateConflictException - if Identification Number Category is
	 * 'SINGLE' and date range conflicts with an existing identification number
	 * of the same category
	 */
	IdentificationNumber update(
			IdentificationNumber identificationNumber,
			IdentificationNumberIssuer issuer,
			IdentificationNumberCategory category,
			String value, Date issueDate, Date expireDate)
				throws DuplicateEntityFoundException, DateConflictException;
	
	/**
	 * Removes identification number.
	 * 
	 * @param identificationNumber identification number
	 */
	void remove(IdentificationNumber identificationNumber);
	
	/**
	 * Returns issuers.
	 * 
	 * @return issuers
	 */
	List<IdentificationNumberIssuer> findIssuers();
	
	/**
	 * Returns categories.
	 * 
	 * @return categories
	 */
	List<IdentificationNumberCategory> findCategories();
}