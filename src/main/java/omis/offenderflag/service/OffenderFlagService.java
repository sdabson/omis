package omis.offenderflag.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offenderflag.domain.FlagUsage;
import omis.offenderflag.domain.OffenderFlag;
import omis.offenderflag.domain.OffenderFlagCategory;
import omis.user.domain.UserAccount;

/**
 * Service for offender flags.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public interface OffenderFlagService {

	/**
	 * Sets the flag for the offender.
	 * 
	 * <p>If the flag is already set, reset it.
	 * 
	 * @param offender offender
	 * @param category category of offender flag
	 * @param value value of flag
	 * @param userAccount account of user setting flag
	 * @param date date on which user set flag
	 * @return flag that was set
	 */
	OffenderFlag set(Offender offender, OffenderFlagCategory category,
			Boolean value, UserAccount userAccount, Date date);
	
	/**
	 * Unsets the flag for the offender.
	 * 
	 * <p>If the flag is not set, do nothing.
	 * 
	 * @param offender offender
	 * @param category category
	 */
	void unset(Offender offender, OffenderFlagCategory category);
	
	/**
	 * Returns required categories.
	 * 
	 * @return required categories
	 */
	List<OffenderFlagCategory> findRequiredCategories();
	
	/**
	 * Returns categories.
	 * 
	 * @return categories
	 */
	List<OffenderFlagCategory> findCategories();
	
	/**
	 * Finds offender flag.
	 * 
	 * @param offender flag offender
	 * @param category flag category
	 * @return offender flag; {@code null} if a matching flag does not
	 * exist
	 */
	OffenderFlag find(Offender offender, OffenderFlagCategory category);
	
	/**
	 * Returns whether the offender has the required flags.
	 * 
	 * @param offender offender
	 * @return whether offender has required flags
	 */
	boolean hasRequiredFlags(Offender offender);
	
	/**
	 * Creates an offender flag category.
	 *
	 * @param name name
	 * @param description description
	 * @param requried required
	 * @param sortOrder sort order
	 * @param usage usage
	 * @return offender flag category
	 * @throws DuplicateEntityFoundException
	 */
	OffenderFlagCategory createOffenderFlagCategory(String name, 
			String description, Boolean requried, Short sortOrder, 
			FlagUsage usage) throws DuplicateEntityFoundException;
	
	/**
	 * Updates an offender flag category.
	 *
	 * @param offenderFlagCategory offender flag category
	 * @param name name
	 * @param requried required
	 * @param sortOrder sort order
	 * @param usage usage
	 * @return updated offender flag category
	 * @throws DuplicateEntityFoundException
	 */
	OffenderFlagCategory updateOffenderFlagCategory(
			OffenderFlagCategory offenderFlagCategory, String name, 
			Boolean requried, Short sortOrder, FlagUsage usage) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an offender flag category.
	 *
	 * @param offenderFlagCategory offender flag category
	 */
	void removeOffenderFlagCategory(OffenderFlagCategory offenderFlagCategory);
}