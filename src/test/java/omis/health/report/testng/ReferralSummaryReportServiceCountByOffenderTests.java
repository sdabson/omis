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
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.service.delegate.FacilityDelegate;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.MedicalFacility;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderAssignmentCategory;
import omis.health.domain.ProviderTitle;
import omis.health.domain.component.LabWorkOrder;
import omis.health.exception.ExternalReferralException;
import omis.health.exception.LabWorkException;
import omis.health.exception.ProviderInternalReferralAssociationExistsException;
import omis.health.report.ReferralSummaryReportService;
import omis.health.report.ReferralType;
import omis.health.service.delegate.InternalReferralDelegate;
import omis.health.service.delegate.InternalReferralReasonDelegate;
import omis.health.service.delegate.LabWorkCategoryDelegate;
import omis.health.service.delegate.LabWorkDelegate;
import omis.health.service.delegate.MedicalFacilityDelegate;
import omis.health.service.delegate.OffenderAppointmentAssociationDelegate;
import omis.health.service.delegate.ProviderAssignmentDelegate;
import omis.health.service.delegate.ProviderInternalReferralAssociationDelegate;
import omis.health.service.delegate.ProviderTitleDelegate;
import omis.health.service.delegate.ExternalReferralAuthorizationDelegate;
import omis.health.service.delegate.ExternalReferralAuthorizationRequestDelegate;
import omis.health.service.delegate.ExternalReferralDelegate;
import omis.health.service.delegate.ExternalReferralReasonDelegate;
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
 * Test the counting referral summary by offender
 *
 * @author Yidong Li
 * @version 0.0.1 (Nov. 9, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"health", "report"})
public class ReferralSummaryReportServiceCountByOffenderTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Delegates. */
	@Autowired
	@Qualifier("providerInternalReferralAssociationDelegate")
	private ProviderInternalReferralAssociationDelegate
	providerInternalReferralAssociationDelegate;
	
	@Autowired
	@Qualifier("providerAssignmentDelegate")
	private ProviderAssignmentDelegate providerAssignmentDelegate;
	
	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("facilityDelegate")
	private FacilityDelegate facilityDelegate;
	
	@Autowired
	@Qualifier("providerTitleDelegate")
	private ProviderTitleDelegate providerTitleDelegate;
	
	@Autowired
	@Qualifier("medicalFacilityDelegate")
	private MedicalFacilityDelegate	medicalFacilityDelegate;
	
	@Autowired
	@Qualifier("internalReferralDelegate")
	private InternalReferralDelegate internalReferralDelegate;
	
	@Autowired
	@Qualifier("internalReferralReasonDelegate")
	private InternalReferralReasonDelegate
	internalReferralReasonDelegate;
	
	@Autowired
	@Qualifier("externalReferralDelegate")
	private ExternalReferralDelegate
	externalReferralDelegate;
	
	@Autowired
	@Qualifier("offenderAppointmentAssociationDelegate")
	private OffenderAppointmentAssociationDelegate
	offenderAppointmentAssociationDelegate;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationDelegate")
	private ExternalReferralAuthorizationDelegate
	externalReferralAuthorizationDelegate;
	
	@Autowired
	@Qualifier("externalReferralReasonDelegate")
	private ExternalReferralReasonDelegate
	externalReferralReasonDelegate;
	
	@Autowired
	@Qualifier("labWorkDelegate")
	private LabWorkDelegate labWorkDelegate;
	
	@Autowired
	@Qualifier("labWorkCategoryDelegate")
	private LabWorkCategoryDelegate labWorkCategoryDelegate;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationRequestDelegate")
	private ExternalReferralAuthorizationRequestDelegate
	externalReferralAuthorizationRequestDelegate;
	
	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
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
	
	/* Service to test. */
	@Autowired
	@Qualifier("referralSummaryReportService")
	private ReferralSummaryReportService
	referralSummaryReportService;

	/* Test methods. */
	
	/**
	 * Test the counting referral summary by offender
	 * @throws DuplicateEntityFoundException if already exists 
	 * @throws ProviderInternalReferralAssociationExistsException if
	 * already exists 
	 * @throws ExternalReferralException 
	 * @throws LabWorkException 
	 */
	@Test
	public void testCountingReferralSummaryByFacility()
		throws DuplicateEntityFoundException,
		LabWorkException, ProviderInternalReferralAssociationExistsException,
		ExternalReferralException {
		// Arrangements
		Person provider = this.personDelegate.create("adbg", "Dbgedbg",
			"dbfdf", "Mr.");
		Country birthCountry = this.countryDelegate.findOrCreate(
				"United States", "US", true);
		State birthState = this.stateDelegate.findOrCreate("Montana",
			"MT", birthCountry, true, true);
		City birthPlace = this.cityDelegate.findOrCreate("Helena",
			true, birthState, birthCountry);
		Offender offender = this.offenderDelegate.create("abc", 
			"def", "ghi", "Mr.", 123456789, "26",
			this.parseDateText("12/12/2017"), birthCountry,
			birthState, birthPlace, Sex.MALE);
		
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
		ProviderTitle providerTitle = this.providerTitleDelegate.create(
			ProviderAssignmentCategory.LAB_SPECIALIST, "dsbdb", "GRRG",
			"adbger", true, (short) 3543);
		MedicalFacility medicalFacility = this.medicalFacilityDelegate
			.createMedicalFacility("dbfb", location, "HNJH", true,
			true, null);
		ProviderAssignment providerAssignment
			= this.providerAssignmentDelegate.create(provider,
				facility, null, medicalFacility, providerTitle, true);
		InternalReferralReason internalReferralReason
			= this.internalReferralReasonDelegate.create("dbgferg",
			(short) 3636, true);
		
		Date date = this.parseDateText("11/11/2011");
		Date startTime = this.parseDateText("9/2/2000");
		Date endTime = this.parseDateText("9/12/2000");
		OffenderAppointmentAssociation offenderAppointmentAssociation
		= this.offenderAppointmentAssociationDelegate.create(offender,
			date, facility, startTime, endTime);
		
		CreationSignature creationSignature = new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate());
		UpdateSignature updateSignature = new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());  
				
		InternalReferral internalReferral = this.internalReferralDelegate
			.create(offenderAppointmentAssociation, null,
			internalReferralReason, null, null, null, null, null, 
			creationSignature, updateSignature, null);
		this.providerInternalReferralAssociationDelegate.create(
			providerAssignment, internalReferral, true, false);
		
		OffenderAppointmentAssociation secondOffenderAppointmentAssociation
		= this.offenderAppointmentAssociationDelegate.create(
			offender, date, facility, startTime, endTime);
		
		ExternalReferralReason reason = this.externalReferralReasonDelegate
			.create("edgb", (short) 7665, true);
		ExternalReferralAuthorizationRequest request
		= this.externalReferralAuthorizationRequestDelegate.create(
			offender, null, null, medicalFacility, facility,
			reason, null, null, null);
		ExternalReferralAuthorization authorization
		= this.externalReferralAuthorizationDelegate.create(request,
			null, null, null, null, null);
		this.externalReferralDelegate.create(
			secondOffenderAppointmentAssociation, null, true, null,
			null, null, null, authorization);
		
		LabWorkOrder order = new LabWorkOrder(null,
			this.parseDateText("1/1/2000")); 
		LabWorkCategory category = this.labWorkCategoryDelegate.create(
			"asgr", (short) 45654, true);
		this.labWorkDelegate.create(offenderAppointmentAssociation,
			false, null, null, order, null, category, null, null, null);
		
		HealthAppointmentStatus[] statuses
			= {HealthAppointmentStatus.CANCELLED, null,
				HealthAppointmentStatus.KEPT }; 
		
		ReferralType[] types = {ReferralType.EXTERNAL_MEDICAL,
			ReferralType.INTERNAL_MEDICAL, ReferralType.LAB};
	
		// Action
		long count = this.referralSummaryReportService
			.countByOffender(offender, null, null, types, statuses);
		
		// Assertion
		assert (count == 3) : "the count is worng";
	}
		
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}
