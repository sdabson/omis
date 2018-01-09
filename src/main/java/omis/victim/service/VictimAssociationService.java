package omis.victim.service;

import java.util.Date;
import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.country.domain.Country;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;
import omis.victim.domain.component.VictimAssociationFlags;

/**
 * Service for victim associations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 26, 2015)
 * @since OMIS 3.0
 */
public interface VictimAssociationService {

	/**
	 * Associates offender with a victim.
	 * 
	 * @param offender offender
	 * @param victim victim
	 * @param registeredDate date registered
	 * @param packetSent whether packet is sent
	 * @param packetSendDate date packet is sent
	 * @param flags flags
	 * @return victim association
	 * @throws DuplicateEntityFoundException if association exists
	 * @throws ReflexiveRelationshipException if offender and victim are the
	 * same person
	 */
	VictimAssociation associate(Offender offender, Person victim,
			Date registeredDate, Boolean packetSent,
			Date packetSendDate, VictimAssociationFlags flags)
				throws DuplicateEntityFoundException,
					ReflexiveRelationshipException;
	
	/**
	 * Updates association of offender with a victim.
	 * 
	 * @param association association
	 * @param registeredDate date registered
	 * @param packetSent whether packet is sent
	 * @param packetSendDate date packet is sent
	 * @param flags flags
	 * @return updated victim association
	 * @throws DuplicateEntityFoundException if association exists
	 */
	VictimAssociation update(VictimAssociation association,
			Date registeredDate, Boolean packetSent, Date packetSendDate,
			VictimAssociationFlags flags)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes association.
	 * 
	 * @param association association to remove
	 */
	void remove(VictimAssociation association);
	
	/**
	 * Creates a new victim.
	 * 
	 * <p>An identity will not be associated with the victim if all of the
	 * identity properties are {@code null}.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @param sex sex
	 * @param birthDate date of birth
	 * @param birthCounty country of birth
	 * @param birthState State of birth
	 * @param birthCity city of birth
	 * @param socialSecurityNumber social security number
	 * @param stateId state ID
	 * @param deceased whether person is deceased
	 * @param deathDate date of death
	 * @return new victim
	 */
	Person createVictim(String lastName, String firstName, String middleName,
			String suffix, Sex sex, Date birthDate, Country birthCountry,
			State birthState, City birthCity, Integer socialSecurityNumber,
			String stateId, Boolean deceased, Date deathDate);
	
	/**
	 * Returns victim associations by offender.
	 * 
	 * @param offender offender
	 * @return victim associations by offender
	 */
	List<VictimAssociation> findByOffender(Offender offender);
	
	/**
	 * Adds association note.
	 * 
	 * @param association association
	 * @param category category
	 * @param date date
	 * @param value value
	 * @return created association note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	VictimNote addNote(VictimAssociation association,
			VictimNoteCategory category, Date date, String value)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates association note.
	 * 
	 * @param victimNote victim note
	 * @param category category
	 * @param date date
	 * @param value value
	 * @return updated association note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	VictimNote updateNote(VictimNote victimNote, VictimNoteCategory category,
			Date date, String value)
				throws DuplicateEntityFoundException;
	
	/**
	 * Returns victim note categories.
	 * 
	 * @return victim note categories
	 */
	List<VictimNoteCategory> findNoteCategories();
	
	/**
	 * Removes victim note.
	 * 
	 * @param victimNote victim note to remove
	 */
	void removeNote(VictimNote victimNote);
	
	/**
	 * Disassociates note from victim association.
	 * 
	 * @param victimNote victim note to disassociate
	 * @return disassociated note
	 */
	VictimNote disassociateNote(VictimNote victimNote);
	
	/**
	 * Returns notes by association.
	 * 
	 * @param association association
	 * @return notes by association
	 */
	List<VictimNote> findNotesByAssociation(VictimAssociation association);

	/**
	 * Removes association and all associated notes.
	 * 
	 * @param victimAssociation association to remove 
	 */
	void removeWithNotes(VictimAssociation victimAssociation);

	/**
	 * Removes association and disassociates all associated notes.
	 * 
	 * @param victimAssociation association to remove
	 */
	void removeAndDisassociateNotes(VictimAssociation victimAssociation);

	/**
	 * Create address.
	 * 
	 * @param number number
	 * @param zipCode ZIP code
	 * @return created address
	 * @throws DuplicateEntityFoundException if address exists
	 */
	Address createAddress(String number, ZipCode zipCode)
					throws DuplicateEntityFoundException;
	
	/**
	 * Returns street suffixes.
	 * 
	 * @return street suffixes
	 */
	List<StreetSuffix> findStreetSuffixes();
	
	/**
	 * Returns address unit designators.
	 * 
	 * @return address unit designators
	 */
	List<AddressUnitDesignator> findAddressUnitDesignators();
	
	/**
	 * Returns countries.
	 * 
	 * @return countries
	 */
	List<Country> findCountries();
	
	/**
	 * Returns States.
	 * 
	 * @param country country
	 * @return States
	 */
	List<State> findStates(Country country);

	/**
	 * Returns cities by State.
	 * 
	 * @param state State
	 * @return cities by State
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Returns cities by country.
	 * 
	 * @param country country
	 * @return cities by country
	 */
	List<City> findCitiesByCountry(Country country);

	/**
	 * Returns cities by country without State.
	 * 
	 * @param country country without State
	 * @return cities by country without State
	 */
	List<City> findCitiesByCountryWithoutState(Country country);
	
	/**
	 * Returns whether country has States.
	 * 
	 * @param country country
	 * @return whether country has States
	 */
	boolean hasStates(Country country);

	/**
	 * Returns suffix names.
	 * 
	 * @return suffix names
	 */
	List<String> findSuffixNames();
	
	/**
	 * Changes contact.
	 * 
	 * <p>Updates contact if exists for relation, otherwise, creates new
	 * contact.
	 * 
	 * @param relation relation
	 * @param poBox PO box
	 * @param mailingAddress mailing address
	 * @return changes contact
	 */
	Contact changeContact(Person relation, PoBox poBox, Address mailingAddress);
	
	/**
	 * Returns mailing address for relation.
	 * 
	 * @param relation relation
	 * @return mailing address for relation
	 */
	Address findMailingAddress(Person relation);
	
	/**
	 * Returns PO box for relation.
	 * 
	 * @param relation relation
	 * @return PO box for relation
	 */
	PoBox findPoBox(Person relation);
	
	/**
	 * Returns telephone numbers for relation.
	 * 
	 * <p>Returns an empty list if no telephone numbers exist for the relation.
	 * 
	 * @param relation relation
	 * @return telephone numbers for relation or empty list
	 */
	List<TelephoneNumber> findTelephoneNumbers(Person relation);
	
	/**
	 * Returns online accounts for relation.
	 * 
	 * <p>Returns an empty list if no online accounts exist for relation.
	 * 
	 * @param relation relation
	 * @return online accounts for relation or empty list
	 */
	List<OnlineAccount> findOnlineAccounts(Person relation);
	
	/**
	 * Creates telephone number.
	 * 
	 * <p>If contact does not exist for relation, creates one.
	 * 
	 * @param relation relation
	 * @param value value
	 * @param extension extension
	 * @param primary whether primary
	 * @param category category
	 * @return created telephone number
	 * @throws DuplicateEntityFoundException if telephone number exists
	 */
	TelephoneNumber createTelephoneNumber(
			Person relation, Long value, Integer extension, Boolean primary,
			Boolean active, TelephoneNumberCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 * @param value value
	 * @param extension extension
	 * @param primary whether primary
	 * @param category category
	 * @return updated telephone number
	 * @throws DuplicateEntityFoundException if telephone number exists
	 */
	TelephoneNumber updateTelephoneNumber(
			TelephoneNumber telephoneNumber, Long value, Integer extension,
			Boolean primary, Boolean active, TelephoneNumberCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes telephone number.
	 * 
	 * @param telephoneNumber telephone number to remove
	 */
	void removeTelephoneNumber(TelephoneNumber telephoneNumber);
	
	/**
	 * Creates online account.
	 * 
	 * <p>If contact does not exist for person, creates one.
	 * 
	 * @param relation relation
	 * @param name name
	 * @param host host
	 * @param primary whether primary
	 * @return created online account
	 * @throws DuplicateEntityFoundException if online account exists
	 */
	OnlineAccount createOnlineAccount(
			Person relation, String name, OnlineAccountHost host,
			Boolean primary, Boolean active)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates online account.
	 * 
	 * @param onlineAccount online account to update
	 * @param name name
	 * @param host host
	 * @param primary whether primary
	 * @return updated online account
	 * @throws DuplicateEntityFoundException if online account exists
	 */
	OnlineAccount updateOnlineAccount(
			OnlineAccount onlineAccount, String name, OnlineAccountHost host,
			Boolean primary, Boolean active) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes online account.
	 * 
	 * @param onlineAccount online account to remove
	 */
	void removeOnlineAccount(OnlineAccount onlineAccount);
	
	/**
	 * Returns online account hosts.
	 * 
	 * @return online account hosts
	 */
	List<OnlineAccountHost> findOnlineAccountHosts();

	/**
	 * Creates city.
	 * 
	 * @param name name
	 * @param state State
	 * @param country country
	 * @return created city
	 * @throws DuplicateEntityFoundException if city exists
	 */
	City createCity(String name, State state, Country country)
			throws DuplicateEntityFoundException;

	/**
	 * Creates ZIP code.
	 * 
	 * @param value value
	 * @param extension extension
	 * @param city city
	 * @return created ZIP code
	 * @throws DuplicateEntityFoundException if ZIP code exists
	 */
	ZipCode createZipCode(String value, String extension, City city)
			throws DuplicateEntityFoundException;

	/**
	 * Returns ZIP codes by city.
	 * 
	 * @param city city
	 * @return ZIP codes by city
	 */
	List<ZipCode> findZipCodesByCity(City city);
	
	/**
	 * Returns addresses by query.
	 * 
	 * @param query query
	 * @return addresses by query
	 */
	List<Address> findAddressesByQuery(String query);
}