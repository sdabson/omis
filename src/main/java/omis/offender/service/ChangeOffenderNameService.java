package omis.offender.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.Suffix;

/**
 * ChangeOffenderNameService.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface ChangeOffenderNameService {
	
	/**
	 * Sets the offenders current name as an alternative name association, using
	 * the effective date as its end date, then creates a new PersonName, and sets
	 * the new name as the Offender's name
	 * @param offender - Offender whose name is being changed
	 * @param lastName - String
	 * @param firstName - String
	 * @param middleName - String
	 * @param suffix - String
	 * @param effectiveDate - Date
	 * @param previousNameCategory - AlternativeNameCategory
	 * @return Updated Offender
	 * @throws DuplicateEntityFoundException
	 */
	Offender change(Offender offender, String lastName, String firstName,
			String middleName, String suffix, Date effectiveDate,
			AlternativeNameCategory previousNameCategory)
					throws DuplicateEntityFoundException;
	
	/**
	 * Finds and returns a list of all AlternativeNameCategories
	 * @return list of all AlternativeNameCategories
	 */
	List<AlternativeNameCategory> findAlternativeNameCategories();
	
	/**
	 * Finds and returns a list of all Suffixes
	 * @return list of all Suffixes
	 */
	List<Suffix> findSuffixes();
	
}
