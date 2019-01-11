package omis.offenderrelationship.report.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.contact.exception.ContactExistsException;
import omis.contact.service.delegate.ContactDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offenderrelationship.report.OffenderRelationshipReportService;
import omis.offenderrelationship.report.OffenderRelationshipSummary;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.residence.domain.ResidenceCategory;
import omis.residence.domain.ResidenceStatus;
import omis.residence.exception.ResidenceTermExistsException;
import omis.residence.service.delegate.NonResidenceTermDelegate;
import omis.residence.service.delegate.ResidenceTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Tests method to test offender relationship report service summaries by address.
 *
 * @author Sheronda Vaughn
 * @version 0.0.1 (Nov 9, 2018)
 * @since OMIS 3.0
 */
@Test(enabled=false)
public class OffenderRelationshipReportServiceSummarizeByAddressTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("nonResidenceTermDelegate")
	private NonResidenceTermDelegate nonResidenceTermDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("residenceTermDelegate")
	private ResidenceTermDelegate residenceTermDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	@Autowired
	@Qualifier("contactDelegate")
	private ContactDelegate contactDelegate;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/* Services. */

	@Autowired
	@Qualifier("offenderRelationshipReportService")
	private OffenderRelationshipReportService offenderRelationshipReportService;

	/* Test methods. */

	public void testSummarizeByResidneceTermAddress() throws ResidenceTermExistsException, 
		DuplicateEntityFoundException {
		// Arrangements
		String addressValue = ("	111 addressValue");
		Person offender = this.personDelegate.create("oLastName", "oFirstName", "oMiddleName", "oSuffix");
		Person offender2 = this.personDelegate.create("oLastName2", "oFirstName2", "oMiddleName", "oSuffix");
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, 
				true);
		Address residenceTermAddress = this.addressDelegate.findOrCreate("111 addressValue", 
				null, null, null, zipCode);
		Address residenceTermAddress2 = this.addressDelegate.findOrCreate("112 addressValue", 
				null, null, null, zipCode);
		
		//create residence- for address
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), null);
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
				this.residenceTermDelegate.createResidenceTerm(
				offender, dateRange, ResidenceCategory.PRIMARY, residenceTermAddress, 
				ResidenceStatus.RESIDENT, true, null,  verificationSignature);	
				this.residenceTermDelegate.createResidenceTerm(
				offender2, dateRange, ResidenceCategory.SECONDARY, residenceTermAddress2, 
				ResidenceStatus.RESIDENT, true, null,  verificationSignature);		
		
		// Action
		List<OffenderRelationshipSummary> summaries 
			= this.offenderRelationshipReportService.summarizeByAddress(addressValue, city.getName(), 
					state.getName(), zipCode.getValue());
		
		// Assertions
		assert !summaries.isEmpty() : "Residence address not found";	
	}

	//Creates an offender with a mailing address
	public void testSummarizeByMailingAddress() throws CountryExistsException, StateExistsException, 
		CityExistsException, ZipCodeExistsException, ContactExistsException {
		// Arrangements
		String addressValue = ("	111 addressValue");
		Person offender = this.personDelegate.create("oLastName", "oFirstName", "oMiddleName", "oSuffix");
		Person offender2 = this.personDelegate.create("oLastName2", "oFirstName2", "oMiddleName", "oSuffix");
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		City city = this.cityDelegate.create("City", true, state, country);
		ZipCode zipCode = this.zipCodeDelegate.create(city, "00001", null, 
				true);
		Address mailingAddress = this.addressDelegate.findOrCreate("111 addressValue", 
				null, null, null, zipCode);
		Address mailingAddress2 = this.addressDelegate.findOrCreate("112 addressValue", 
				null, null, null, zipCode);	
		this.contactDelegate.create(offender, mailingAddress, null);
		this.contactDelegate.create(offender2, mailingAddress2, null);
		 
		// Action
		List<OffenderRelationshipSummary> summaries 
			= this.offenderRelationshipReportService.summarizeByAddress(addressValue, city.getName(), 
					state.getName(), zipCode.getValue());
		 		
		// Assertions
		assert !summaries.isEmpty() : "Mailing address not found";
	}
	
	//Creates an offender with a residence
	
	// Parses date text
		private Date parseDateText(final String text) {
			try {
				return new SimpleDateFormat("MM/dd/yyyy").parse(text);
			} catch (ParseException e) {
				throw new RuntimeException("Parse error", e);
			}
		}
}