/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.health.report.testng;

import java.beans.PropertyEditor;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.service.delegate.FacilityDelegate;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderAssignmentCategory;
import omis.health.domain.ProviderTitle;
import omis.health.exception.ProviderInternalReferralAssociationExistsException;
import omis.health.report.InternalReferralReportService;
import omis.health.service.delegate.InternalReferralDelegate;
import omis.health.service.delegate.InternalReferralReasonDelegate;
import omis.health.service.delegate.MedicalFacilityDelegate;
import omis.health.service.delegate.OffenderAppointmentAssociationDelegate;
import omis.health.service.delegate.ProviderAssignmentDelegate;
import omis.health.service.delegate.ProviderInternalReferralAssociationDelegate;
import omis.health.service.delegate.ProviderTitleDelegate;
import omis.health.service.delegate.ExternalReferralAuthorizationDelegate;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Test the counting for resolved internal referral summary by facility
 *
 * @author Yidong Li
 * @version 0.0.1 (Nov. 5, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"health", "report"})
public class InternalReferralReportServiceCountResolvedInternalReferralsTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("providerInternalReferralAssociationDelegate")
	private ProviderInternalReferralAssociationDelegate
	ProviderInternalReferralAssociationDelegate;
	
	@Autowired
	@Qualifier("providerAssignmentDelegate")
	private ProviderAssignmentDelegate providerAssignmentDelegate;
	
	@Autowired
	@Qualifier("internalReferralDelegate")
	private InternalReferralDelegate internalReferralDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
	
	@Autowired
	@Qualifier("offenderAppointmentAssociationDelegate")
	private OffenderAppointmentAssociationDelegate
	offenderAppointmentAssociationDelegate;
	
	@Autowired
	@Qualifier("internalReferralReasonDelegate")
	private InternalReferralReasonDelegate
	internalReferralReasonDelegate;
	
	@Autowired
	@Qualifier("medicalFacilityDelegate")
	private MedicalFacilityDelegate	medicalFacilityDelegate;
	
	@Autowired
	@Qualifier("providerTitleDelegate")
	private ProviderTitleDelegate providerTitleDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
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
	@Qualifier("facilityDelegate")
	private FacilityDelegate facilityDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("organizationDelegate")
	private OrganizationDelegate organizationDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationDelegate")
	private ExternalReferralAuthorizationDelegate
	externalReferralAuthorizationDelegate;
	
	/* Service to test. */
	@Autowired
	@Qualifier("internalReferralReportService")
	private InternalReferralReportService
	internalReferralReportService;

	/* Test methods. */
	
	/**
	 * Test the counting for resolved internal referral summary by
	 * facility
	 * @throws DuplicateEntityFoundException if already exists 
	 * @throws ProviderInternalReferralAssociationExistsException if
	 * already exists 
	 */
	@Test
	public void testCountResolvedInternalReferrals()
		throws DuplicateEntityFoundException,
		ProviderInternalReferralAssociationExistsException {
		// Arrangements
		Person provider = this.personDelegate.create("sdbh", "zdbfd",
			"aedgb", "Mr.");
		Country birthCountry = this.countryDelegate.findOrCreate(
			"United States", "US", true);
		State birthState = this.stateDelegate.findOrCreate("Montana",
			"MT", birthCountry, true, true);
		City birthPlace = this.cityDelegate.findOrCreate("Helena",
			true, birthState, birthCountry);
		Offender offender = this.offenderDelegate.create("abc", 
			"def", "ghi", "Mr.", 123456789, "26",
			this.parseDateText("12/12/2017"), birthCountry, birthState,
			birthPlace, Sex.MALE);
		Organization organization = this.organizationDelegate.create(
			"asbvf", null, null);
		ZipCode zipCode = this.zipCodeDelegate.create(birthPlace,
			"59601", null, true);
		Address address = this.addressDelegate.findOrCreate("adbgfd",
			null, null, null, zipCode);
		Location location = this.locationDelegate.create(organization,
			null, address);
		Facility facility = this.facilityDelegate.create(location,
			"savgf", "rhrh", true, null);
		Date startDate = this.parseDateText("10/12/2016");
		Date endDate = this.parseDateText("10/12/2018");
		DateRange dateRange = new DateRange(startDate, endDate);
		MedicalFacility medicalFacility = this.medicalFacilityDelegate
			.createMedicalFacility("bdbd", location, "GHF", true, true,
			1234567890L);
		ProviderTitle title = this.providerTitleDelegate.create(
			ProviderAssignmentCategory.MD, "xnfbsd", "DF", "sgvgvs",
			true, (short) 45645);
		
		ProviderAssignment providerAssignment
			= this.providerAssignmentDelegate.create(provider,
			facility, dateRange, medicalFacility, title, true);
		
		Date date= this.parseDateText("03/23/2018");
		InternalReferralReason internalReferralReason
			= this.internalReferralReasonDelegate.create("shnsf",
			(short) 465, true);
		Date startDate2 = this.parseDateText("10/22/2000");
		Date endDate2 = this.parseDateText("2/12/2011");
		OffenderAppointmentAssociation offenderAppointmentAssociation
		= this.offenderAppointmentAssociationDelegate.create(offender,
			date, facility, startDate2, endDate2);
		InternalReferral internalReferral = this.internalReferralDelegate
			.create(offenderAppointmentAssociation, null,
			internalReferralReason, null, null, null, null, null,
			new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()),
			new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()), null);
		this.ProviderInternalReferralAssociationDelegate.create(
			providerAssignment, internalReferral, true, false);
		
		Person secondProvider = this.personDelegate.create("shrrt",
			"erhgt", "rtt", "Mr.");
		City secondBirthPlace = this.cityDelegate.findOrCreate("Butt",
			true, birthState, birthCountry);
		ZipCode secondZipCode = this.zipCodeDelegate.create(
			secondBirthPlace, "59701", null, true);
		Address secondAddress = this.addressDelegate.findOrCreate(
			"adbgfd", null, null, null, secondZipCode);
		Location secondLocation = this.locationDelegate.create(
			organization, null, secondAddress);
		Date secondStartDate = this.parseDateText("12/22/2006");
		Date secondEndDate = this.parseDateText("10/16/2008");
		DateRange secondDateRange = new DateRange(secondStartDate,
			secondEndDate);
		MedicalFacility secondMedicalFacility
			= this.medicalFacilityDelegate.createMedicalFacility(
			"ehrty", secondLocation, "DBFD", true, true, 123459789L);
		ProviderTitle secondTitle = this.providerTitleDelegate.create(
			ProviderAssignmentCategory.LAB_SPECIALIST, "eghe", "DFG",
			"dfbgd", true, (short) 35643);
		
		ProviderAssignment secondProviderAssignment
		= this.providerAssignmentDelegate.create(secondProvider,
			facility, secondDateRange, secondMedicalFacility,
			secondTitle, true);
		
		Date secondDate= this.parseDateText("03/23/2000");
		InternalReferralReason secondInternalReferralReason
			= this.internalReferralReasonDelegate.create("ykyuk",
			(short) 87, true);
		OffenderAppointmentAssociation secondOffenderAppointmentAssociation
			= this.offenderAppointmentAssociationDelegate.create(offender,
			secondDate, facility, null, null);
		InternalReferral secondInternalReferral
			= this.internalReferralDelegate
			.create(secondOffenderAppointmentAssociation, null,
			secondInternalReferralReason, null, null, null, null, null,
			new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()),
			new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()), null);
		this.ProviderInternalReferralAssociationDelegate.create(
			secondProviderAssignment, secondInternalReferral, true,
			false);
		
		// Action
		long count = this.internalReferralReportService
			.countResolvedInternalReferrals(facility);
		
		// Assertion
		assert (count == 2) : "the count is worng";
	}
		
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}
