package omis.detainernotification.service;

import java.util.Date;
import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerActivityCategory;
import omis.detainernotification.domain.DetainerAgency;
import omis.detainernotification.domain.DetainerJurisdictionCategory;
import omis.detainernotification.domain.DetainerNote;
import omis.detainernotification.domain.DetainerType;
import omis.detainernotification.domain.DetainerWarrantCancellationReason;
import omis.detainernotification.domain.DetainerWarrantProcessingStatus;
import omis.detainernotification.domain.Direction;
import omis.detainernotification.domain.InterstateAgreementDetainer;
import omis.detainernotification.domain.InterstateAgreementInitiatedByCategory;
import omis.detainernotification.domain.InterstateDetainerActivity;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Service for detainers.
 *
 * @author Sheronda Vaughn
 * @author Annie Jacques
 * @author Joel Norris
 * @version 0.1.2 (May 16, 2017)
 * @since OMIS 3.0
 */
public interface DetainerService {
	
	/** 
	 * Creates a detainer.
	 * @param offender - offender
	 * @param alternateOffenderId - alternate offender ID
	 * @param offenseDescription - offense description
	 * @param courtCaseNumber - court case number
	 * @param detainerType - detainer type
	 * @param detainerAgency - detainer agency
	 * @param jurisdiction - detainer jurisdiction category
	 * @param receivedDate - receive date
	 * @param issuedDate - issue date
	 * @param warrantNumber - warrant number
	 * @return detainer - detainer
	 * @throws DuplicateEntityFoundException - when Detainer exists for given 
	 * offender, detainer type, and received date.
	 */
	public Detainer create(Offender offender, String alternateOffenderId, 
			String offenseDescription, String courtCaseNumber, 
			DetainerType detainerType, DetainerAgency detainerAgency, 
			DetainerJurisdictionCategory jurisdiction, Date receivedDate,
			Date issuedDate, String warrantNumber)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing detainer.
	 * @param detainer - detainer
	 * @param alternateOffenderId - alternate offender ID
	 * @param offenseDescription - offense description
	 * @param courtCaseNumber - court case number
	 * @param detainerType - detainer type
	 * @param detainerAgency - detainer agency
	 * @param jurisdiction - detainer jurisdiction category
	 * @param receivedDate - receive date
	 * @param issuedDate - issue date
	 * @param warrantNumber - warrant number
	 * @return detainer
	 * @throws DuplicateEntityFoundException - when Detainer exists for given 
	 * offender, detainer type, and received date.
	 */
	public Detainer update(Detainer detainer, String alternateOffenderId, 
			String offenseDescription, String courtCaseNumber, 
			DetainerType detainerType, DetainerAgency detainerAgency, 
			DetainerJurisdictionCategory jurisdiction, Date receivedDate,
			Date issuedDate, String warrantNumber)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates an interstate agreement detainer
	 * @param detainer - detainer
	 * @param prosecutorReceivedDate - prosecutor received date
	 * @param initiatedBy - initiated by
	 * @throws DuplicateEntityFoundException - when Interstate Agreement Detainer 
	 * exists for given detainer 
	 */
	public InterstateAgreementDetainer createInterstateAgreementDetainer(
			Detainer detainer, Date prosecutorReceivedDate,
			InterstateAgreementInitiatedByCategory initiatedBy)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing interstate agreement detainer
	 * @param interstateAgreementDetainer - interstate agreement detainer
	 * @param prosecutorReceivedDate - prosecutor received date
	 * @param initiatedBy - initiated by
	 * @throws DuplicateEntityFoundException - when Interstate Agreement Detainer 
	 * exists for given detainer 
	 */
	public InterstateAgreementDetainer updateInterstateAgreementDetainer(
			InterstateAgreementDetainer interstateAgreementDetainer,
			Date prosecutorReceivedDate,
			InterstateAgreementInitiatedByCategory initiatedBy)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an interstate agreement detainer
	 * @param interstateAgreementDetainer - interstate agreement detainer
	 */
	public void removeInterstateAgreementDetainer(
			InterstateAgreementDetainer interstateAgreementDetainer);
	
	/**
	 * Finds and returns an interstate agreement detainer by the specified detainer
	 * @param detainer - detainer
	 * @return Interstate Agreement Detainer
	 */
	public InterstateAgreementDetainer 
		findInterstateAgreementDetainerByDetainer(Detainer detainer);
	
	/**
	 * Creates a zipcode
	 * @param city - city
	 * @param value - value
	 * @param extension - extension
	 * @param active - active
	 * @return ZipCode - zipcode
	 * @throws DuplicateEntityFoundException - when zipcode already exists for
	 * given parameters
	 */
	public ZipCode createZipCode(City city, String value, String extension, 
			boolean active)throws DuplicateEntityFoundException;

	/**
	 * Creates detainer agency
	 * @param agencyName - agency name
	 * @param valid - valid
	 * @param telephoneNumber - telephone number
	 * @param address - address
	 * @return detainerAgency - detainer agency
	 * @throws DuplicateEntityFoundException - when detainer agency exists
	 * with given name
	 */
	public DetainerAgency createDetainerAgency(String agencyName, Boolean valid,
			String telephoneNumber, Address address)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates an existing detainer agency
	 * @param detainerAgency - detainer agency
	 * @param agencyName - agency name
	 * @param valid - valid
	 * @param telephoneNumber - telephone number
	 * @param address - address
	 * @return detainerAgency - detainer agency
	 * @throws DuplicateEntityFoundException - when detainer agency exists
	 * with given name
	 */
	public DetainerAgency updateDetainerAgency(DetainerAgency detainerAgency,
			String agencyName, Boolean valid,String telephoneNumber,
			Address address)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates a detainer warrant processing status
	 * @param detainer - detainer
	 * @param sentToFacilityDate - sent to facility date
	 * @param facility - facility
	 * @param unit unit
	 * @param complex complex
	 * @param inmateServedDate - inmate served date
	 * @param refusedToSign - refused to sign
	 * @param waiverRequired - waiver required
	 * @param cancellationDate - cancellation date
	 * @param detainweWarrantCancellationReason - detainer warrant cancellation reason
	 * @param comments - comments
	 * @return DetainerWarrantProcessingStatus - Detainer Warrant Processing Status
	 * @throws DuplicateEntityFoundException - when given Detainer Warrant
	 *  Processing Status exists for given detainer
	 */
	public DetainerWarrantProcessingStatus createDetainerWarrantProcessingStatus(
			Detainer detainer, Date sentToFacilityDate, Facility facility, 
			Unit unit, Complex complex, Date inmateServedDate,
			boolean refusedToSign, boolean waiverRequired,
			Date cancellationDate, 
			DetainerWarrantCancellationReason detainerWarrantCancellationReason, 
			String refusedToSignComment, String waiverRequiredComment,
			String facilityName)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates existing detainer warrant processing status
	 * @param detainerWarrantProcessingStatus - detainer warrant processing status
	 * @param sentToFacilityDate - sent to facility date
	 * @param facility - facility
	 * @param inmateServedDate - inmate served date
	 * @param refusedToSign - refused to sign
	 * @param waiverRequired - waiver required
	 * @param unit - unit
	 * @param cancellationDate - cancellation date
	 * @param detainweWarrantCancellationReason - detainer warrant cancellation reason
	 * @param comments - comments
	 * @return DetainerWarrantProcessingStatus - Detainer Warrant Processing Status
	 * @throws DuplicateEntityFoundException - when given Detainer Warrant
	 *  Processing Status exists for given detainer
	 */
	public DetainerWarrantProcessingStatus updateDetainerWarrantProcessingStatus(
			DetainerWarrantProcessingStatus detainerWarrantProcessingStatus, 
			Date sentToFacilityDate, Facility facility,
			Unit unit, Complex complex,
			Date inmateServedDate, boolean refusedToSign,
			boolean waiverRequired, Date cancellationDate, 
			DetainerWarrantCancellationReason detainerWarrantCancellationReason,
			String refusedToSignComment, String waiverRequiredComment,
			String facilityName)
					throws DuplicateEntityFoundException;
	
	
	/**
	 * Removes a detainer warrant processing status
	 * @param detainerWarrantProcessingStatus - detainer warrant processing status
	 */
	public void removeDetainerWarrantProcessingStatus(
			DetainerWarrantProcessingStatus detainerWarrantProcessingStatus);
	
	/**
	 * Finds and returns a detainer warrant processing status by specified detainer
	 * @param detainer - detainer
	 * @return detainer warrant processing status
	 */
	public DetainerWarrantProcessingStatus 
		findDetainerWarrantProcessingStatusByDetainer(Detainer detainer);
	
	
	/**
	 * Returns a list of all detainer types
	 * @return detainer types
	 */
	public List<DetainerType> findAllDetainerTypes();
	
	/**
	 * Finds and returns a list of all detainer agencies
	 * @return detainer agencies
	 */
	public List<DetainerAgency> findAllDetainerAgencies();
	
	/**
	 * Finds and returns a list of all address unit designators
	 * @return address unit designators
	 */
	public List<AddressUnitDesignator> findAllAddressUnitDesignators();
	
	/**
	 * Finds and returns a list of all street suffixes
	 * @return street suffixes
	 */
	public List<StreetSuffix> findAllStreetSuffixes();
	
	/**
	 * Finds and returns a list of all countries
	 * @return countries
	 */
	public List<Country> findAllCountries();
	
	/**
	 * Finds and returns all states by country
	 * @param country - country
	 * @return states
	 */
	public List<State> findAllStates(Country country);
	
	/**
	 * Finds and returns a list of cities by state
	 * @param state - state
	 * @return cities by state
	 */
	public List<City> findCitiesByState(State state);
	
	/**
	 * Returns cities for the specified country.
	 * 
	 * @param country country
	 * @return list of cities
	 */
	public List<City> findCitiesByCountry(Country country);
	
	/**
	 * Returns cities by country that do not have a State.
	 * 
	 * @param country country
	 * @return cities by country that do not have a State
	 */
	public List<City> findCitiesByCountryWithoutState(Country country);
	
	/**
	 * Returns a list of zip codes for the specified city.
	 * 
	 * @param city city
	 * @return list of zip codes
	 */
	public List<ZipCode> findZipCodes(City city);
	
	
	/**
	 * Removes specified detainer
	 * @param detainer - detainer
	 */
	public void remove(final Detainer detainer);
	
	
	/** Create document.
	 * @param documentDate - date of document.
	 * @param filename - file name.
	 * @param fileExtension - file extension. 
	 * @param title - title. 
	 * @return document entity.
	 * @throws DuplicateEntityFoundException - when document with existing file
	 * name exists. */
	public Document createDocument(Date documentDate,
			String filename, String fileExtension, String title)
					throws DuplicateEntityFoundException;
	/** Updates document.
	 * @param document - document.
	 * @param title - title. 
	 * @param date - date. 
	 * @throws DuplicateEntityFoundException */
	public Document updateDocument(Document document, Date documentDate,
			String title)
					throws DuplicateEntityFoundException;

	/**
	 * Removes a specified document
	 * @param document - Document to remove
	 */
	public void removeDocument(Document document);

	/** Tag document.
	 * @param document - document.
	 * @param name - tag name. 
	 * @return document tag. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	public DocumentTag createDocumentTag(Document document, String name)
			throws DuplicateEntityFoundException;

	/** Update tag.
	 * @param documentTag - document tag.
	 * @param name - name. 
	 * @throws DuplicateEntityFoundException - when document is already tagged
	 * with given name. */
	public DocumentTag updateDocumentTag(DocumentTag documentTag, String name)
			throws DuplicateEntityFoundException;

	/** Remove tag.
	 * @param documentTag - document tag. */
	public void removeDocumentTag(DocumentTag documentTag);
	
	/**
	 * Creates an InterstateDetainerActivity with specified parameters
	 * @param interstateAgreementDetainer - InterstateAgreementDetainer
	 * @param activityDate - Date
	 * @param direction - Direction
	 * @param document - Document
	 * @param category - DetainerActivityCategory
	 * @return Newly created InterstateDetainerActivity
	 * @throws DuplicateEntityFoundException - When an InterstateDetainerActivity
	 * already exists with given InterstateAgreementDetainer, ActivityDate, and
	 * Document
	 */
	public InterstateDetainerActivity createDetainerActivity(
			InterstateAgreementDetainer interstateAgreementDetainer,
			Date activityDate, Direction direction,
			Document document, DetainerActivityCategory category)
					throws DuplicateEntityFoundException;
	/**
	 * Updates an InterstateDetainerActivity with specified parameters
	 * @param interstateDetainerActivity - InterstateDetainerActivity to update
	 * @param activityDate - Date
	 * @param direction - Direction
	 * @param document - Document
	 * @param category - DetainerActivityCategory
	 * @return Updated InterstateDetainerActivity
	 * @throws DuplicateEntityFoundException - When an InterstateDetainerActivity
	 * already exists with given InterstateAgreementDetainer, ActivityDate, and
	 * Document
	 */
	public InterstateDetainerActivity updateDetainerActivity(
			InterstateDetainerActivity interstateDetainerActivity,
			Date activityDate, Direction direction,
			Document document, DetainerActivityCategory category)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an InterstateDetainerActivity
	 * @param interstateDetainerActivity - InterstateDetainerActivity to remove
	 */
	public void removeDetainerActivity(
			InterstateDetainerActivity interstateDetainerActivity);
	
	/**
	 * Returns a list of InterstateDetainerActivities found by specified
	 * InterstateAgreementDetainer
	 * @param interstateAgreementDetainer - InterstateAgreementDetainer
	 * @return List of InterstateDetainerActivities found by specified
	 * Detainer
	 */
	public List<InterstateDetainerActivity>
			findDetainerActivityByInterstateAgreementDetainer(
					InterstateAgreementDetainer interstateAgreementDetainer);
	
	/**
	 * Returns a list of DetainerActivityCategories found by
	 * InterstateAgreementInitiatedByCategory
	 * @param initiatedBy - InterstateAgreementInitiatedByCategory
	 * @return List of DetainerActivityCategories found by
	 * InterstateAgreementInitiatedByCategory
	 */
	public List<DetainerActivityCategory>
			findDetainerActivityCategoriesByInitiatedBy(
					InterstateAgreementInitiatedByCategory initiatedBy);
	
	/** Finds document tags by document.
	 * @param document - document.
	 * @return list of document tags. */
	public List<DocumentTag> findDocumentTagsByDocument(Document document);
	
	/**
	 * Returns facilities.
	 * 
	 * @return facilities
	 */
	public List<Facility> findAllFacilities();
	
	/**
	 * Returns units by the specified facility.
	 * 
	 * @param facility facility
	 * @return list of units
	 */
	public List<Unit> findUnitsByFacility(Facility facility);
	
	/**
	 * Returns list of units for the specified complex.
	 * 
	 * @param complex complex
	 * @return list of units
	 */
	public List<Unit> findUnitsByComplex(Complex complex);
	
	/**
	 * Returns list of complexes for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	public List<Complex> findComplexesByFacility(Facility facility);
	
	/**
	 * Creates address.
	 * 
	 * @param value value
	 * @param zipCode ZIP code
	 * @return created address
	 * @throws DuplicateEntityFoundException if address exists
	 */
	public Address createAddress(
			String value, ZipCode zipCode)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates city.
	 * 
	 * @param name name
	 * @param state State
	 * @param country country
	 * @return new city
	 * @throws DuplicateEntityFoundException if city exists
	 */
	public City createCity(
			String name, Boolean valid, State state,
			Country country) throws DuplicateEntityFoundException;

	/**
	 * Returns all detainer notes for the specified detainer.
	 * 
	 * @param detainer detainer
	 * @return detainer notes
	 */
	public List<DetainerNote> findNotesByDetainer(Detainer detainer);

	/**
	 * Creates a detainer note with the specified date and value for the
	 * specified detainer.
	 * 
	 * @param detainer detainer
	 * @param date date
	 * @param value value
	 * @return detainer note
	 * @throws DuplicateEntityFoundException thrown when a duplicate detainer
	 * note is found
	 */
	public DetainerNote createNote(Detainer detainer, Date date, String value)
			throws DuplicateEntityFoundException;

	/**
	 * Updates the specified detainer note with the specified date and value.
	 * 
	 * @param note note to update
	 * @param date date
	 * @param value value
	 * @return updated detainer note
	 * @throws DuplicateEntityFoundException thrown when a duplicate detainer
	 * note is found
	 */
	public DetainerNote updateNote(DetainerNote note, Date date, String value)
		throws DuplicateEntityFoundException;

	/**
	 * Removes the specified detainer note.
	 * 
	 * @param note detainer note
	 */
	public void removeNote(DetainerNote note);

	/**
	 * Find units for the specified facility and complex.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @return list of units
	 */
	public List<Unit> findUnits(Facility facility, Complex complex);
}