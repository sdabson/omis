package omis.criminalassociation.service;

import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.contact.domain.Contact;
import omis.criminalassociation.domain.CriminalAssociation;
import omis.criminalassociation.domain.CriminalAssociationCategory;
import omis.criminalassociation.domain.component.CriminalAssociationFlags;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.PrimaryResidenceExistsException;
import omis.residence.exception.ResidenceStatusConflictException;

/**
 * Services for association.
 * 
 * @author Joel Norris 
 * @author Yidong Li
 * @version 0.1.1 (Apr, 15 2015)
 * @since OMIS 3.0
 *
 */
public interface CriminalAssociationService {
	
	/**
	 * Checks for an existing relationship between two offenders
	 * if one does not exist, create new relationship 
	 * offender is always the "firstPerson" value of relationship. The
	 * relationship is then set to the specified association.
	 * 
	 * @param offender offender
	 * @param associate associate
	 * @param dateRange date range
	 * @param category criminal association category
	 * @param criminalAssociationFlags criminal association flags
	 * @return CriminalAssociation criminal association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws ReflexiveRelationshipException reflexive relationship exception
	 */
	CriminalAssociation associate(Offender offender, Person associate, 
		DateRange dateRange, CriminalAssociationCategory category, 
		CriminalAssociationFlags criminalAssociationFlags) 
		throws DuplicateEntityFoundException, ReflexiveRelationshipException;
	
	/**
	 * Updates an existing criminal association.
	 * 
	 * @param criminalAssociation criminal association
	 * @param  criminalAssociationCategory  criminal association category
	 * @param criminalAssociationFlags criminal association flags
	 * @param dateRange date range
	 * @return criminal association
	 */
	CriminalAssociation update(CriminalAssociation criminalAssociation, 
		DateRange dateRange,
		CriminalAssociationCategory criminalAssociationCategory, 
		CriminalAssociationFlags criminalAssociationFlags);
	
	/**
	 * Removes the specified criminal association.
	 * 
	 * @param criminalAssociation criminal association
	 */
	void remove(CriminalAssociation criminalAssociation);
	
	/**
	 * Find all criminal association categories.
	 * 
	 * @return list of criminal association categories 
	 */
	List<CriminalAssociationCategory> findCategories();
	
	/**
	 * Create criminal associate.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return Person person 
	 */
	Person createCriminalAssociate(String lastName, String firstName, 
		String middleName, String suffix);
		
	/**
	 * Update criminal associate.
	 * 
	 * @param criminalAssociate criminal associate that need to be updated
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return Person person 
	 */
	Person updateCriminalAssociate(Person criminalAssociate, String lastName, 
		String firstName, String middleName, String suffix);
	
	/**
	 * Create address with the specified value and zip code.
	 * 
	 * @param value value
	 * @param zipCode zip code
	 * @return address address
	 */
	Address createAddress(String value, ZipCode zipCode);
	
	/**
	 * Create residence term.
	 * 
	 * @param person person
	 * @param address address
	 * @return residenceTerm residence term
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws ResidenceStatusConflictException 
	 * residence status conflict exception
	 * @throws PrimaryResidenceExistsException 
	 * primary residence exists exception
	 */
	ResidenceTerm createResidenceTerm(Person person, Address address) 
		throws DuplicateEntityFoundException, PrimaryResidenceExistsException, 
		ResidenceStatusConflictException;
	
	/**
	 * Add telephone number.
	 * 
	 * @param person person
	 * @param telephoneNumber telephone number
	 * @return contact contact
	 * @throws DuplicateEntityFoundException 
	 */
	Contact addTelephoneNumber(Person person, Long telephoneNumber) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Returns a list of all criminal associations for the specified offender
	 * as the main offender.
	 * 
	 * @param offender offender
	 * @return list of criminal associations
	 */
	List<CriminalAssociation> findByOffender(Offender offender); 

	/**
	 * Returns a list of all criminal associations for the specified offender 
	 * as the other offender.
	 * 
	 * @param offender offender
	 * @return list of criminal associations
	 */
	List<CriminalAssociation> findByOtherOffender(Offender offender); 
}