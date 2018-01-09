package omis.family.service;

import java.util.Date;
import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.BuildingCategory;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.audit.domain.VerificationSignature;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.family.exception.FamilyAssociationConflictException;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationCategory;
import omis.family.domain.FamilyAssociationNote;
import omis.family.domain.component.FamilyAssociationFlags;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.PrimaryResidenceExistsException;
import omis.residence.exception.ResidenceStatusConflictException;

/**
 * Service for family association.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.2 (June 2, 2015)
 * @since OMIS 3.0
 */
public interface FamilyAssociationService {

	/**
	 * Creates a family association between the specified first and second 
	 * person.
	 * 
	 * @param offender offender
	 * @param familyMember family member
	 * @param dateRange date range
	 * @param category family association category
	 * @param flags family association flags
	 * @param marriageDate marriage date
	 * @param divorceDate divorce date
	 * @return newly created family association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws ReflexiveRelationshipException reflexive relationship exception
	 * @throws FamilyAssociationConflictException 
	 * family association conflict exception
	 * @throws omis.family.exception.FamilyAssociationConflictException 
	 */
	FamilyAssociation associate(Offender offender, Person familyMember, 
		DateRange dateRange, FamilyAssociationCategory category,
		FamilyAssociationFlags flags, Date marriageDate, Date divorceDate)
		throws DuplicateEntityFoundException, ReflexiveRelationshipException,
		FamilyAssociationConflictException, 
		omis.family.exception.FamilyAssociationConflictException;
	
	/**
	 * Updates a family association.
	 * 
	 * @param familyAssociation family association being updated
	 * @param dateRange date range
	 * @param category family association category
	 * @param flags family association flags
	 * @param marriageDate marriage date
	 * @param divorceDate divorce date
	 * @return newly updated family association
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws FamilyAssociationConflictException 
	 * family association conflict exception
	 */
	FamilyAssociation update(FamilyAssociation familyAssociation, 
			DateRange dateRange, FamilyAssociationCategory category, 
			FamilyAssociationFlags flags, Date marriageDate, Date divorceDate) 
				 throws DuplicateEntityFoundException,
		 FamilyAssociationConflictException;

	/**
	 * Removes the specified family association.
	 * @param familyAssociation family association
	 */
	void remove(FamilyAssociation familyAssociation);
	
	/**
	 * Find all family associations categories.
	 * @return list of family associations categories
	 */
	List<FamilyAssociationCategory> findCategories();
	
	/**
	 * Create family member.
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param sex sex
	 * @param birthDate birth date
	 * @param birthState birth state
	 * @param birthCountrhy birth country
	 * @param birthCity birth city
	 * @param socialSecurityNumber social security number
	 * @param stateId state id
	 * @param deceased deceased or not
	 * @param deathDate death date
	 * @return person
	 */
	Person createFamilyMember(String lastName, String firstName, 
		String middleName, String suffix, Sex sex, Date birthDate, 
		Country birthCountrhy, State birthState, City birthCity, 
		Integer socialSecurityNumber, String stateId, Boolean deceased, 
		Date deathDate);
	
	/**
	 * Find all name suffixes.
	 * @return a list of name suffixes
	 */
	List<Suffix> findNameSuffixes();
	
	/**
	 * Create address.
	 * @param value value
	 * @param designator designator
	 * @param buiildingCategory building category
	 * @param zipCode zip code
	 * @return address
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	Address createAddress(String value, String designator, 
		BuildingCategory buiildingCategory,	ZipCode zipCode)
				throws DuplicateEntityFoundException;
	
	/**
	 * Create residence term.
	 * @param person person
	 * @param address address
	 * @param verificationSignature verification signature	 
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 * @throws PrimaryResidenceExistsException 
	 * primary residence exists exception
	 * @throws ResidenceStatusConflictException 
	 * residence status conflict exception
	 * @return residence term
	 */
	ResidenceTerm createResidenceTerm(Person person, Address address, 
		VerificationSignature verificationSignature)
		throws DuplicateEntityFoundException, PrimaryResidenceExistsException, 
		ResidenceStatusConflictException;
	
	/**
	 * Add contact.
	 * @param person person
	 * @param mailingAddress mailing address
	 * @param poBox P.O. box
	 * @return contact
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	Contact addContact(Person person, Address mailingAddress, PoBox poBox)
		throws DuplicateEntityFoundException;

	/**
	 * Find a contact by person.
	 * @param person person
	 * @return contact
	 */
	Contact findContactByPerson(Person person);
	
	/**
	 * Find telephone numbers by contact.
	 *
	 * @param contact contact
	 * @return list of telephone numbers
	 */
	List<TelephoneNumber> findTelephoneNumbersByContact(Contact contact);
	
	/**
	 * Find online accounts by contact.
	 * @param contact contact
	 * @return list of online accounts
	 */
	List<OnlineAccount> findOnlineAccountsByContact(Contact contact);
	
	/**
	 * Update contact.
	 * @param contact contact
	 * @param mailingAddress mailing address
	 * @param poBox P.O. box
	 * @return contact
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	Contact updateContact(Contact contact, Address mailingAddress, PoBox poBox)
			throws DuplicateEntityFoundException;
	
	/**
	 * Add telephone number.
	 * @param contact contact
	 * @param value phone number
	 * @param extension phone extension
	 * @param primary primary phone or not
	 * @param active active phone
	 * @param category telephone number category
	 * @return telephone number
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	TelephoneNumber addTelephoneNumber(Contact contact, Long value, 
		Integer extension, Boolean primary, Boolean active, 
		TelephoneNumberCategory category)
				throws DuplicateEntityFoundException;
	
	/**
	 * Update a telephone number.
	 * @param telephoneNumber telephone number to be updated
	 * @param value phone number
	 * @param extension phone extension
	 * @param active active
	 * @param primary primary phone or not
	 * @param category telephone number category
	 * @return updated telephone number
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	TelephoneNumber updateTelephoneNumber(TelephoneNumber telephoneNumber, 
		Long value, Integer extension, Boolean primary, Boolean active,
		TelephoneNumberCategory category) 
				throws DuplicateEntityFoundException;
	
	/**
	 * Remove a telephone number.
	 * @param telephoneNumber telephone number to be removed
	 */
	void removeTelephoneNumber(TelephoneNumber telephoneNumber);
	
	/**
	 * Add online account.
	 * @param contact contact
	 * @param name name
	 * @param primary primary email or not
	 * @param host online account host
	 * @param active active account or not
	 * @return online account
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	OnlineAccount addOnlineAccount(Contact contact, String name, 
			Boolean primary, OnlineAccountHost host, Boolean active)	
				throws DuplicateEntityFoundException;
	
	/**
	 * Update an online account.
	 * @param onlineAccount online account to be updated
	 * @param name name
	 * @param primary primary email or not
	 * @param host online account host
	 * @param active active account or not
	 * @return updated online account
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount, String name, 
		Boolean primary, OnlineAccountHost host, Boolean active)
				throws DuplicateEntityFoundException;
	
	/**
	 * Remove an online account.
	 * @param onlineAccount online account
	 */
	void removeOnlineAccount(OnlineAccount onlineAccount);
	
	/**
	 * Find email hosts.
	 *
	 * @return email hosts
	 */
	List<OnlineAccountHost> findOnlineAccountHosts();
	
	/**
	 * Add note.
	 * @param association family association
	 * @param date date
	 * @param value text
	 * @return family association note
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	FamilyAssociationNote addNote(FamilyAssociation association, Date date, 
		String value) throws DuplicateEntityFoundException;
	
	/**
	 * Update note.
	 * @param note family association note
	 * @param date date
	 * @param value text
	 * @return family association note
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	FamilyAssociationNote updateNote(FamilyAssociationNote note, Date date, 
		String value) throws DuplicateEntityFoundException;
	
	/**
	 * Find notes by association.
	 * @param association family association
	 * @return a list of family association notes
	 */
	List<FamilyAssociationNote> findNotesByAssociation(
			FamilyAssociation association);
	
	/**
	 * Remove note.
	 * @param note family association note
	 */
	void removeNote(FamilyAssociationNote note);
	
	/**
	 * Find contact.
	 * @param person person
	 * @return contact contact
	 */
	Contact findContact(Person person);
	
	/**
	 * Find residence term.
	 * @param person person
	 * @param effectiveDate effective date
	 * @return ResidenceTerm residence term
	 */
	ResidenceTerm findResidenceTerm(Person person, Date effectiveDate);
	
	/**
	 * Find countries.
	 * @return a list of countries
	 */
	List<Country> findCountries();
	
	/**
	 * Find zip codes by city.
	 * @param city city
	 * @return a list of zip codes
	 */
	List<ZipCode> findZipCodesByCity(City city);
	
	/**
	 * Find cities in state.
	 * @param state state
	 * @return a list of cities
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Create zip code.
	 * @param value zip code value
	 * @param extension zip code extension
	 * @param city city
	 * @return zip code
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	ZipCode createZipCode(String value, String extension, City city) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Find states by country.
	 * @param country country
	 * @return a list of states
	 */
	List<State> findStatesByCountry(Country country);
	
	/**
	 * Find cities by country.
	 * @param country country
	 * @return a list of cities
	 */
	List<City> findCitiesByCountry(Country country);
	
	/**
	 * Check if the country has states.
	 * @param country country
	 * @return yes or no
	 */
	Boolean hasStates(Country country);
	
	/**
	 * Find all address unit designators.
	 * @return a list of address unit designators
	 */
	List<AddressUnitDesignator> findAddressUnitDesignators();
	
	/**
	 * Find all street suffixes.
	 * @return a list of street suffixes
	 */
	List<StreetSuffix> findStreetSuffixes();
	
	/**
	 * Create city.
	 * @param name city name
	 * @param state state
	 * @param country country
	 * @return Created city
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	City createCity(String name, State state, Country country) 
		throws DuplicateEntityFoundException;
	
	/**
	 * Find addresses.
	 * @param addressQuery address query
	 * @return a list of addresses
	 */
	List<Address> findAddresses(String addressQuery);
	
	/**
	 * Find name suffixes.
	 * @return a list of name suffixes
	 */
	List<String> findSuffixNames();
	
	/**
	 * Returns home State.
	 * 
	 * @return home State
	 */
	State findHomeState();	
	
	
	/**
	 * Returns a list of cities.
	 * @param country country
	 * @return a list of cities
	 */
	List<City> findCitiesByCountryWithoutState(Country country);
}