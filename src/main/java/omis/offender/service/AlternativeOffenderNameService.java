package omis.offender.service;

import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.AlternativeNameCategory;
import omis.person.domain.PersonName;
import omis.person.domain.Suffix;

/**
 * Service for alternative offender names.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 4, 2013)
 * @since OMIS 3.0
 */
public interface AlternativeOffenderNameService {

	/**
	 * Creates a new alternative name association between the specified
	 * offender and a person name with the specified name properties.
	 * 
	 * @param offender offender
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param dateRange date range
	 * @param category category
	 * @return new alternative name association
	 * @throws DuplicateEntityFoundException thrown when an alternative name
	 * association is found with the same name, category, and date range
	 * as the ones specified.
	 */
	AlternativeNameAssociation associate(Offender offender, String lastName, 
			String firstName, String middleName, String suffix, 
			DateRange dateRange, AlternativeNameCategory category) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified alternative name association with the specified
	 * properties.
	 * 
	 * @param association alternative name association
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param dateRange date range
	 * @param category category
	 * @return new alternative name association
	 * @throws DuplicateEntityFoundException thrown when another alternative 
	 * name association is found with the same name, category, and date range
	 * as the ones specified.
	 */
	AlternativeNameAssociation updateAssociation(
			AlternativeNameAssociation association, String lastName,
			String firstName, String middleName, String suffix, 
			DateRange dateRange, AlternativeNameCategory category)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes an alternative offender name.
	 * 
	 * @param alternativeNameAssociation association of alternative offender
	 * name to remove
	 */
	void remove(AlternativeNameAssociation alternativeNameAssociation);
	
	/**
	 * Returns alternative names for offender.
	 * 
	 * <p>Alternative offender names are names not equal to
	 * {@code offender.getName()}.
	 * 
	 * @param offender offender
	 * @return alternative names for offender
	 */
	List<PersonName> findAlternativeNames(Offender offender);
	
	/**
	 * Returns alternative name associations for offender.
	 * 
	 * @param offender offender
	 * @return alternative name associations for offender
	 */
	List<AlternativeNameAssociation> findAssociations(Offender offender);
	
	/**
	 * Returns alternative name categories.
	 * 
	 * @return alternative name categories
	 */
	List<AlternativeNameCategory> findCategories();
	
	/**
	 * Returns suffixes.
	 * 
	 * @return suffixes
	 */
	List<Suffix> findSuffixes();
}