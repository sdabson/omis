
package omis.detainernotification.service.impl;

import java.util.Date;
import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.AddressUnitDesignatorDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.detainernotification.dao.DetainerNoteDao;
import omis.detainernotification.dao.DetainerTypeDao;
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
import omis.detainernotification.service.DetainerService;
import omis.detainernotification.service.delegate.DetainerActivityCategoryDelegate;
import omis.detainernotification.service.delegate.DetainerAgencyDelegate;
import omis.detainernotification.service.delegate.DetainerDelegate;
import omis.detainernotification.service.delegate.DetainerNoteDelegate;
import omis.detainernotification.service.delegate.DetainerWarrantProcessStatusDelegate;
import omis.detainernotification.service.delegate.InterstateAgreementDetainerDelegate;
import omis.detainernotification.service.delegate.InterstateDetainerActivityDelegate;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.service.delegate.DocumentDelegate;
import omis.document.service.delegate.DocumentTagDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.ComplexDao;
import omis.facility.dao.UnitDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;
import omis.facility.service.delegate.FacilityDelegate;
import omis.offender.domain.Offender;
import omis.region.dao.StateDao;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;

/**
 * DetainerServiceImpl.java
 * 
 *@author Annie Jacques
 *@author Joel Norris 
 *@version 0.1.2 (May 26, 2017)
 *@since OMIS 3.0
 *
 */
public class DetainerServiceImpl implements DetainerService {
	
	private final DetainerDelegate detainerDelegate;
	private final DetainerAgencyDelegate detainerAgencyDelegate;
	private final InterstateAgreementDetainerDelegate 
		interstateAgreementDetainerDelegate;
	private final DetainerWarrantProcessStatusDelegate 
		detainerWarrantProcessStatusDelegate;
	private final InterstateDetainerActivityDelegate
		interstateDetainerActivityDelegate;
	private final DetainerActivityCategoryDelegate
		detainerActivityCategoryDelegate;
	private final AddressDelegate addressDelegate;
	private final ZipCodeDelegate zipCodeDelegate;
	private final AddressUnitDesignatorDelegate addressUnitDesignatorDelegate;
	private final StreetSuffixDelegate streetSuffixDelegate;
	private final StateDao stateDao;
	private final CountryDelegate countryDelegate;
	private final CityDelegate cityDelegate;
	private final DetainerTypeDao detainerTypeDao;
	private final DocumentDelegate documentDelegate;
	private final DocumentTagDelegate documentTagDelegate;
	private final FacilityDelegate facilityDelegate;
	private final DetainerNoteDelegate detainerNoteDelegate;
	private final DetainerNoteDao detainerNoteDao;
	private final UnitDao unitDao;
	private final ComplexDao complexDao;
	
	/**
	 * @param detainerDelegate
	 * @param detainerAgencyDelegate
	 * @param interstateAgreementDetainerDelegate
	 * @param detainerWarrantProcessStatusDelegate
	 * @param interstateDetainerActivityDelegate
	 * @param detainerActivityCategoryDelegate
	 * @param addressDelegate
	 * @param zipCodeDelegate
	 * @param addressUnitDesignatorDelegate
	 * @param streetSuffixDelegate
	 * @param countryDelegate
	 * @param stateDao
	 * @param cityDelegate
	 * @param detainerTypeDao
	 * @param documentDelegate
	 * @param documentTagDelegate
	 * @param facilityDelegate
	 */
	public DetainerServiceImpl(final DetainerDelegate detainerDelegate,
			final DetainerAgencyDelegate detainerAgencyDelegate,
			final InterstateAgreementDetainerDelegate 
				interstateAgreementDetainerDelegate, 
			final DetainerWarrantProcessStatusDelegate 
				detainerWarrantProcessStatusDelegate,
			final InterstateDetainerActivityDelegate
			interstateDetainerActivityDelegate,
			final DetainerActivityCategoryDelegate
			detainerActivityCategoryDelegate, final AddressDelegate
				addressDelegate, final ZipCodeDelegate zipCodeDelegate, 
			final AddressUnitDesignatorDelegate addressUnitDesignatorDelegate,
			final StreetSuffixDelegate streetSuffixDelegate,
			final CountryDelegate countryDelegate,
			final StateDao stateDao,
			final CityDelegate cityDelegate, final DetainerTypeDao 
				detainerTypeDao,
			final DocumentDelegate documentDelegate,
			final DocumentTagDelegate documentTagDelegate,
			final FacilityDelegate facilityDelegate,
			final DetainerNoteDelegate detainerNoteDelegate,
			final DetainerNoteDao detainerNoteDao,
			final UnitDao unitDao, final ComplexDao complexDao){
		this.detainerDelegate = detainerDelegate;
		this.detainerAgencyDelegate = detainerAgencyDelegate;
		this.interstateAgreementDetainerDelegate =
				interstateAgreementDetainerDelegate;
		this.detainerWarrantProcessStatusDelegate =
				detainerWarrantProcessStatusDelegate;
		this.interstateDetainerActivityDelegate =
				interstateDetainerActivityDelegate;
		this.detainerActivityCategoryDelegate =
				detainerActivityCategoryDelegate;
		this.addressDelegate = addressDelegate;
		this.zipCodeDelegate = zipCodeDelegate;
		this.addressUnitDesignatorDelegate = addressUnitDesignatorDelegate;
		this.streetSuffixDelegate = streetSuffixDelegate;
		this.countryDelegate = countryDelegate;
		this.stateDao = stateDao;
		this.cityDelegate = cityDelegate;
		this.detainerTypeDao = detainerTypeDao;
		this.documentDelegate = documentDelegate;
		this.documentTagDelegate = documentTagDelegate;
		this.facilityDelegate = facilityDelegate;
		this.detainerNoteDelegate = detainerNoteDelegate;
		this.detainerNoteDao = detainerNoteDao;
		this.unitDao = unitDao;
		this.complexDao = complexDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public Detainer create(final Offender offender, final String alternateOffenderId, 
			final String offenseDescription,
			final String courtCaseNumber, final DetainerType detainerType, 
			final DetainerAgency detainerAgency,
			final DetainerJurisdictionCategory jurisdiction,
			final Date warrantReceivedDate,
			final Date warrantIssuedDate, final String warrantNumber) 
					throws DuplicateEntityFoundException {

		return this.detainerDelegate.create(offender, alternateOffenderId, 
				offenseDescription, courtCaseNumber, detainerType, 
				detainerAgency, jurisdiction, 
				warrantReceivedDate, warrantIssuedDate, warrantNumber);
	}

	/** {@inheritDoc} */
	@Override
	public Detainer update(final Detainer detainer,
			final String alternateOffenderId, 
			final String offenseDescription, final String courtCaseNumber,
			final DetainerType detainerType, final DetainerAgency detainerAgency, 
			final DetainerJurisdictionCategory jurisdiction,
			final Date warrantReceivedDate, final Date warrantIssuedDate,
			final String warrantNumber)
					throws DuplicateEntityFoundException {
		
		return this.detainerDelegate.update(detainer, alternateOffenderId, 
				offenseDescription, courtCaseNumber, detainerType, 
				detainerAgency, jurisdiction, 
				warrantReceivedDate, warrantIssuedDate, warrantNumber);

	}

	/** {@inheritDoc} */
	@Override
	public InterstateAgreementDetainer createInterstateAgreementDetainer(
			final Detainer detainer,
			final Date prosecutorReceivedDate, 
			final InterstateAgreementInitiatedByCategory initiatedBy)
			throws DuplicateEntityFoundException {
		
		return this.interstateAgreementDetainerDelegate.create(detainer,
				prosecutorReceivedDate, initiatedBy);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateAgreementDetainer updateInterstateAgreementDetainer(
			final InterstateAgreementDetainer interstateAgreementDetainer, 
			final Date prosecutorReceivedDate,
			final InterstateAgreementInitiatedByCategory initiatedBy) 
					throws DuplicateEntityFoundException {
		
		return this.interstateAgreementDetainerDelegate.update(
				interstateAgreementDetainer,
				prosecutorReceivedDate, initiatedBy);
	}

	/** {@inheritDoc} */
	@Override
	public ZipCode createZipCode(final City city, final String value,
			final String extension,final boolean active)
					throws DuplicateEntityFoundException {

		return this.zipCodeDelegate.create(city, value, extension, active);
	}

	/** {@inheritDoc} */
	@Override
	public DetainerAgency createDetainerAgency(final String agencyName,
			final Boolean valid, final String telephoneNumber,
			final Address address)
					throws DuplicateEntityFoundException {

		return this.detainerAgencyDelegate.create(agencyName, valid, 
				telephoneNumber, address);
	}

	/** {@inheritDoc} */
	@Override
	public DetainerAgency updateDetainerAgency(final DetainerAgency detainerAgency, 
			final String agencyName, final Boolean valid,
			final String telephoneNumber, final Address address) 
					throws DuplicateEntityFoundException {

		return this.detainerAgencyDelegate.update(detainerAgency, agencyName, 
				valid, telephoneNumber, address);
	}

	/** {@inheritDoc} */
	@Override
	public DetainerWarrantProcessingStatus createDetainerWarrantProcessingStatus(
			final Detainer detainer,
			final Date sentToFacilityDate, final Facility facility,
			final Unit unit, final Complex complex,
			final Date inmateServedDate, final boolean refusedToSign,
			final boolean waiverRequired, final Date cancellationDate,
			final DetainerWarrantCancellationReason
				detainerWarrantCancellationReason, 
			final String refusedToSignComment,
			final String waiverRequiredComment,
			final String facilityName)
					throws DuplicateEntityFoundException {

		return this.detainerWarrantProcessStatusDelegate.create(detainer,
				sentToFacilityDate, facility, unit, complex, inmateServedDate,
				refusedToSign, waiverRequired, cancellationDate, 
				detainerWarrantCancellationReason,
				refusedToSignComment, waiverRequiredComment, facilityName);
	}

	/** {@inheritDoc} */
	@Override
	public DetainerWarrantProcessingStatus updateDetainerWarrantProcessingStatus(
			final DetainerWarrantProcessingStatus detainerWarrantProcessingStatus, 
			final Date sentToFacilityDate, final Facility facility,
			final Unit unit, final Complex complex,
			final Date inmateServedDate, final boolean refusedToSign,
			final boolean waiverRequired, 
			final Date cancellationDate,
			final DetainerWarrantCancellationReason
				detainerWarrantCancellationReason,
			final String refusedToSignComment,
			final String waiverRequiredComment,
			final String facilityName)
					throws DuplicateEntityFoundException {

		return this.detainerWarrantProcessStatusDelegate.update(
				detainerWarrantProcessingStatus, sentToFacilityDate, facility,
				unit, complex, inmateServedDate, refusedToSign, waiverRequired, 
				cancellationDate, detainerWarrantCancellationReason, 
				refusedToSignComment, waiverRequiredComment, facilityName);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDetainerWarrantProcessingStatus(
			final DetainerWarrantProcessingStatus detainerWarrantProcessingStatus) {
		this.detainerWarrantProcessStatusDelegate.remove(
				detainerWarrantProcessingStatus);

	}

	/** {@inheritDoc} */
	@Override
	public List<DetainerType> findAllDetainerTypes() {
		return this.detainerTypeDao.findAll(); 
	}

	/** {@inheritDoc} */
	@Override
	public List<DetainerAgency> findAllDetainerAgencies() {
		return this.detainerAgencyDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<AddressUnitDesignator> findAllAddressUnitDesignators() {
		return this.addressUnitDesignatorDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<StreetSuffix> findAllStreetSuffixes() {
		return this.streetSuffixDelegate.findAll();
	}

	/**{@inheritDoc} */
	public List<Country> findAllCountries(){
		return this.countryDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findAllStates(final Country country) {
		return this.stateDao.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		return this.cityDelegate.findByState(state);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByCountry(final Country country) {
		return this.cityDelegate.findByCountry(country);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<City> findCitiesByCountryWithoutState(final Country country) {
		return this.cityDelegate.findByCountryWithoutState(country);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findZipCodes(final City city) {
		return this.zipCodeDelegate.findByCity(city);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Detainer detainer) {
		this.detainerDelegate.remove(detainer);

	}

	/**{@inheritDoc} */
	@Override
	public void removeInterstateAgreementDetainer(
			final InterstateAgreementDetainer interstateAgreementDetainer) {
		this.interstateAgreementDetainerDelegate
			.remove(interstateAgreementDetainer);
	}

	/**{@inheritDoc} */
	@Override
	public InterstateAgreementDetainer findInterstateAgreementDetainerByDetainer(
			final Detainer detainer) {
		return this.interstateAgreementDetainerDelegate.find(detainer);
	}

	/**{@inheritDoc} */
	@Override
	public DetainerWarrantProcessingStatus
		findDetainerWarrantProcessingStatusByDetainer(final Detainer detainer) {
		return this.detainerWarrantProcessStatusDelegate.find(detainer);
	}

	/**{@inheritDoc} */
	@Override
	public Document createDocument(final Date documentDate, final String filename,
				final String fileExtension, final String title)
						throws DuplicateEntityFoundException {
		return this.documentDelegate.create(documentDate, filename,
				fileExtension, title);
	}

	/**{@inheritDoc} */
	@Override
	public Document updateDocument(final Document document,
			final Date documentDate, final String title)
					throws DuplicateEntityFoundException {
		return this.documentDelegate.update(document, title, documentDate);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocument(final Document document) {
		this.documentDelegate.remove(document);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag createDocumentTag(final Document document,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.tagDocument(document, name);
	}

	/**{@inheritDoc} */
	@Override
	public DocumentTag updateDocumentTag(final DocumentTag documentTag,
			final String name) throws DuplicateEntityFoundException {
		return this.documentTagDelegate.update(documentTag, name);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDocumentTag(final DocumentTag documentTag) {
		this.documentTagDelegate.removeTag(documentTag);
	}

	/**{@inheritDoc} */
	@Override
	public InterstateDetainerActivity createDetainerActivity(
			final InterstateAgreementDetainer interstateAgreementDetainer,
			final Date activityDate, final Direction direction,
			final Document document, final DetainerActivityCategory category)
					throws DuplicateEntityFoundException {
		return this.interstateDetainerActivityDelegate.create(
				interstateAgreementDetainer, activityDate, direction, document,
				category);
	}

	/**{@inheritDoc} */
	@Override
	public InterstateDetainerActivity updateDetainerActivity(
			final InterstateDetainerActivity interstateDetainerActivity,
			final Date activityDate, final Direction direction,
			final Document document, final DetainerActivityCategory category)
			throws DuplicateEntityFoundException {
		return this.interstateDetainerActivityDelegate.update(
				interstateDetainerActivity, activityDate, direction, document,
				category);
	}

	/**{@inheritDoc} */
	@Override
	public void removeDetainerActivity(
			final InterstateDetainerActivity interstateDetainerActivity) {
		this.interstateDetainerActivityDelegate.remove(
				interstateDetainerActivity);
	}

	/**{@inheritDoc} */
	@Override
	public List<InterstateDetainerActivity>
			findDetainerActivityByInterstateAgreementDetainer(
			final InterstateAgreementDetainer interstateAgreementDetainer) {
		return this.interstateDetainerActivityDelegate
				.findByInterstateAgreementDetainer(interstateAgreementDetainer);
	}

	/**{@inheritDoc} */
	@Override
	public List<DetainerActivityCategory> findDetainerActivityCategoriesByInitiatedBy(
			final InterstateAgreementInitiatedByCategory initiatedBy) {
		return this.detainerActivityCategoryDelegate.findByInitiatedBy(
				initiatedBy);
	}
	
	/**{@inheritDoc} */
	@Override
	public List<DocumentTag> findDocumentTagsByDocument(final Document document) {
		return this.documentTagDelegate.findByDocument(document);
	}

	/**{@inheritDoc} */
	@Override
	public List<Facility> findAllFacilities() {
		return this.facilityDelegate.findAll();
	}

	/**{@inheritDoc} */
	@Override
	public Address createAddress(
			final String value,final ZipCode zipCode)
					throws DuplicateEntityFoundException {
		return this.addressDelegate.findOrCreate(
				value, null, null, null, zipCode);
	}

	/**{@inheritDoc} */
	@Override
	public City createCity(final String name, final Boolean valid,
			final State state, final Country country)
					throws DuplicateEntityFoundException {
		return this.cityDelegate.create(name, valid, state, country);
	}

	/**{@inheritDoc} */
	@Override
	public List<DetainerNote> findNotesByDetainer(final Detainer detainer) {
		return this.detainerNoteDao.findNotesByDetainer(detainer);
	}

	/**{@inheritDoc} */
	@Override
	public DetainerNote createNote(final Detainer detainer, 
			final Date date, final String value)
		throws DuplicateEntityFoundException {
		return this.detainerNoteDelegate.create(detainer, value, date);
	}

	/**{@inheritDoc} */
	@Override
	public DetainerNote updateNote(final DetainerNote note, final Date date,
			final String value) throws DuplicateEntityFoundException {
		return this.detainerNoteDelegate.update(value, date, note);
	}

	/**{@inheritDoc} */
	@Override
	public void removeNote(final DetainerNote note) {
		this.detainerNoteDelegate.remove(note);
	}

	/**{@inheritDoc} */
	@Override
	public List<Unit> findUnitsByFacility(final Facility facility) {
		return this.unitDao.findByFacility(facility);
	}

	/**{@inheritDoc} */
	@Override
	public List<Unit> findUnitsByComplex(final Complex complex) {
		return this.unitDao.findByComplex(complex);
	}

	/**{@inheritDoc} */
	@Override
	public List<Complex> findComplexesByFacility(final Facility facility) {
		return this.complexDao.findByFacility(facility);
	}

	/**{@inheritDoc} */
	@Override
	public List<Unit> findUnits(Facility facility, Complex complex) {
		return this.unitDao.findUnits(facility, complex);
	}
}