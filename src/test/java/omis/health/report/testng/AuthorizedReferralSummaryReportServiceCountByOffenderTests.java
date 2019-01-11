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
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.demographics.domain.Sex;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.facility.service.delegate.FacilityDelegate;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderAssignmentCategory;
import omis.health.domain.ProviderTitle;
import omis.health.domain.component.ExternalReferralMedicalPanelReviewDecision;
import omis.health.report.AuthorizedReferralSummaryReportService;
import omis.health.service.delegate.ExternalReferralAuthorizationRequestDelegate;
import omis.health.service.delegate.ExternalReferralReasonDelegate;
import omis.health.service.delegate.MedicalFacilityDelegate;
import omis.health.service.delegate.ProviderAssignmentDelegate;
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
 * Test the counting for authorized referral summary by offender
 *
 * @author Yidong Li
 * @version 0.0.1 (Oct. 30, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"health", "report"})
public class AuthorizedReferralSummaryReportServiceCountByOffenderTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	/* Service delegates. */
	@Autowired
	@Qualifier("externalReferralAuthorizationRequestDelegate")
	private ExternalReferralAuthorizationRequestDelegate
	externalReferralAuthorizationRequestDelegate;
	
	@Autowired
	@Qualifier("externalReferralReasonDelegate")
	private ExternalReferralReasonDelegate
	externalReferralReasonDelegate;
	
	@Autowired
	@Qualifier("providerAssignmentDelegate")
	private ProviderAssignmentDelegate providerAssignmentDelegate;
	
	@Autowired
	@Qualifier("providerTitleDelegate")
	private ProviderTitleDelegate providerTitleDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired	
	private CustomDateEditorFactory customDateEditorFactory;
	
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
	@Qualifier("medicalFacilityDelegate")
	private MedicalFacilityDelegate medicalFacilityDelegate;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationDelegate")
	private ExternalReferralAuthorizationDelegate
	externalReferralAuthorizationDelegate;

	/* Service to test. */
	@Autowired
	@Qualifier("authorizedReferralSummaryReportService")
	private AuthorizedReferralSummaryReportService
	authorizedReferralSummaryReportService;
	
	/* Test methods. */
	
	/**
	 * Test the counting for authorized referral summary by offender
	 * @throws DuplicateEntityFoundException 
	 */
	@Test
	public void testCountingByOffender()
		throws DuplicateEntityFoundException {
		// Arrangements
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
		Location medicalFacilityLocation = this.locationDelegate
			.create(organization, dateRange, address);
		MedicalFacility medicalFacility = this.medicalFacilityDelegate
			.createMedicalFacility("asgnfgh", medicalFacilityLocation,
			"dtj", true, true, null);
		Person providerAssignmentProvider = this.personDelegate
			.create("abd", "srn", "adsgbr", "Mr.");
		
		ProviderTitle title = this.providerTitleDelegate.create(
			ProviderAssignmentCategory.MD, "xnx", "bzd",
			"Provider title", true, (short) 235);
		ProviderAssignment providerAssignment
			= this.providerAssignmentDelegate.create(
			providerAssignmentProvider, facility, dateRange,
			medicalFacility, title, true);
		
		ExternalReferralReason externalReferralReason
			= this.externalReferralReasonDelegate.create(
			"External referral reason", (short) 2324, true);
		
		ExternalReferralAuthorizationRequest request
			= this.externalReferralAuthorizationRequestDelegate.create(
			offender, this.parseDateText("12/23/2009"), 
			providerAssignment, medicalFacility, facility,
			externalReferralReason, "xnxf", providerAssignment,
			this.parseDateText("04/11/2018"));
		
		this.externalReferralAuthorizationDelegate.create(request,
			null, null, null,
			ExternalReferralAuthorizationStatus.APPROVED, null);	
		
		ExternalReferralMedicalPanelReviewDecision
			externalReferralMedicalPanelReviewDecision
			= new ExternalReferralMedicalPanelReviewDecision();
		externalReferralMedicalPanelReviewDecision.setStatus(
			ExternalReferralMedicalPanelReviewDecisionStatus.APPROVED);
		ExternalReferralAuthorizationRequest secondRequest
		= this.externalReferralAuthorizationRequestDelegate.create(
			offender, this.parseDateText("10/23/2001"),
			providerAssignment, medicalFacility, facility,
			externalReferralReason, "xnxf", providerAssignment,
			this.parseDateText("04/11/2018"));
		this.externalReferralAuthorizationDelegate.create(
			secondRequest, null, null, null,
			ExternalReferralAuthorizationStatus.PANEL_REVIEW_REQUIRED,
			externalReferralMedicalPanelReviewDecision);	
				
		// Action
		long count = this.authorizedReferralSummaryReportService
			.countUnscheduledByOffender(offender);
		
		// Assertions
		assert (count==2)
		: "Count is wrong";
	}
		
	// Parse date text
	private Date parseDateText(final String dateText) {
		PropertyEditor propertyEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		propertyEditor.setAsText(dateText);
		return (Date) propertyEditor.getValue();
	}
}
