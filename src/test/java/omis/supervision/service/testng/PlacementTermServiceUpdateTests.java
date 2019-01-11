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
package omis.supervision.service.testng;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.AddressUnitDesignatorDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.delegate.LocationDelegate;
import omis.locationterm.domain.LocationTerm;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
import omis.supervision.domain.PlacementStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.domain.SupervisoryOrganizationTerm;
import omis.supervision.exception.CorrectionalStatusExistsException;
import omis.supervision.exception.CorrectionalStatusTermConflictException;
import omis.supervision.exception.CorrectionalStatusTermExistsException;
import omis.supervision.exception.PlacementTermChangeReasonExistsException;
import omis.supervision.exception.PlacementTermConflictException;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.exception.PlacementTermLockedException;
import omis.supervision.exception.SupervisoryOrganizationExistsException;
import omis.supervision.exception.SupervisoryOrganizationTermConflictException;
import omis.supervision.exception.SupervisoryOrganizationTermExistsException;
import omis.supervision.service.PlacementTermService;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateManipulator;
import omis.util.PropertyValueAsserter;

/**
 * Tests for placement term service update method.
 *
 * @author Josh Divine 
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"supervision", "service"})
public class PlacementTermServiceUpdateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("locationTermDelegate")
	private LocationTermDelegate locationTermDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	

	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("streetSuffixDelegate")
	private StreetSuffixDelegate streetSuffixDelegate;
	
	@Autowired
	@Qualifier("addressUnitDesignatorDelegate")
	private AddressUnitDesignatorDelegate addressUnitDesignatorDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	
	/* Services. */
	
	@Autowired
	@Qualifier("placementTermService")
	private PlacementTermService placementTermService;
	
	/* Property editor factory. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Tests for overlapping correctional status terms
	 * 
	 * @throws SupervisoryOrganizationTermConflictException 
	 * @throws CorrectionalStatusTermConflictException 
	 * @throws DuplicateEntityFoundException 
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	@Test(expectedExceptions = {CorrectionalStatusTermConflictException.class})
	public void testCorrectionalStatusTermConflictException() throws 
		DuplicateEntityFoundException, CorrectionalStatusTermConflictException, 
		SupervisoryOrganizationTermConflictException,
		PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("12/03/2013");
		Date endDate = this.parseDateText("12/04/2014");
		DateRange dateRange = new DateRange(startDate, endDate);

		this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
	
		Date secondStartDate = this.parseDateText("12/02/2012");
		Date secondEndDate = this.parseDateText("12/03/2013");
		DateRange secondDateRange = new DateRange(
				secondStartDate, secondEndDate);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, 
					secondDateRange, correctionalStatus);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
					secondDateRange, supervisoryOrganization);
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, secondDateRange, 
					supervisoryOrganizationTerm, correctionalStatusTerm, 
					changeReason, null, false);
		
		PlacementStatus updatedStatus = PlacementStatus.PLACED;
		DateRange updatedStatusDate = null;
		
		// Attempt to set end date of correctional status term of placement
		// term to this should cause conflict exception
		Date conflictingEndDate = this.parseDateText("06/04/2014");
		
		// Action
		this.placementTermService.update(placementTerm,
				conflictingEndDate, updatedStatus,
				updatedStatusDate, changeReason, null);
	}
	
	/**
	 * Tests that {@code CorrectionalStatusTermConflictException} is thrown when
	 * a placement term's end date is set to {@code null} using
	 * {@code PlacementTermServce#update(...)}, causing it to conflict with a
	 * future correctional status term.
	 * 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists (asserted)
	 * @throws DuplicateEntityFoundException if duplicate entities are found
	 * @throws PlacementTermLockedException  if placement term is locked when
	 * update is attempted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists
	 */
	@Test(expectedExceptions = {CorrectionalStatusTermConflictException.class})
	public void
	testCorrectionalStatusTermConflictExceptionWithNullConflictingEndDate()
			throws CorrectionalStatusTermConflictException,
				DuplicateEntityFoundException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arrangements - secure Blofeld in prison
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", "Stavro", null);
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRI", null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 2, true);
		DateRange secureDateRange = new DateRange(
				this.parseDateText("12/02/2012"),
				this.parseDateText("12/03/2013"));
		SupervisoryOrganizationTerm prisonOrganizationTerm
			= this.supervisoryOrganizationTermDelegate.create(
					blofeld, secureDateRange, prison);
		CorrectionalStatusTerm secureStatusTerm
			= this.correctionalStatusTermDelegate.create(
					blofeld, secureDateRange, secure);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(blofeld, secureDateRange,
						prisonOrganizationTerm, secureStatusTerm,
						newSentenceReason, null, false);
		
		// More arrangements - parole Blofeld
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		this.correctionalStatusTermDelegate.create(
					blofeld,
					new DateRange(
							this.parseDateText("12/03/2013"),
							this.parseDateText("12/04/2014")),
					parole);
		
		// Action - updates secure placement term with null end date
		// This should conflict with parole status term and be caught in
		// service layer check
		this.placementTermService.update(placementTerm, null,
				PlacementStatus.PLACED, null,
				newSentenceReason, null);
	}
	
	/**
	 * Tests that {@code CorrectionalStatusTermConflictException} is thrown when
	 * a placement term's end date is set to {@code null} using
	 * {@code PlacementTermService#update(...)}, causing it to conflict with a
	 * future correctional status term with a {@code null} end date.
	 * 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists (asserted)
	 * @throws DuplicateEntityFoundException if duplicate entities are found
	 * @throws PlacementTermLockedException  if placement term is locked when
	 * update is attempted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists
	 */
	@Test(expectedExceptions = {CorrectionalStatusTermConflictException.class})
	public void
	testCorrectionalStatusTermConflictExceptionWithNullConflictingEndDates()
			throws CorrectionalStatusTermConflictException,
				DuplicateEntityFoundException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arranges offender securely
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecureStatus();
		DateRange secureRange = new DateRange(
				this.parseDateText("01/01/2001"),
				this.parseDateText("02/02/2002"));
		CorrectionalStatusTerm secureStatusTerm
			= this.correctionalStatusTermDelegate
				.create(offender, secureRange, secure);
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, secureRange, null, secureStatusTerm,
						newSentenceReason, null, false);
		
		// Arranges offender on parole immediately after secure placement
		// indefinitely
		CorrectionalStatus parole = this.createParoleStatus();
		DateRange paroleRange = new DateRange(
				this.parseDateText("02/02/2002"), null);
		this.correctionalStatusTermDelegate.create(
				offender, paroleRange, parole);
		
		// Action - attempts to updated secure placement term with null end date
		// this should be prevented with an exception as it conflicts with the
		// parole term that follows
		this.placementTermService.update(placementTerm, null,
				placementTerm.getStatus(),
				placementTerm.getStatusDateRange(),
				placementTerm.getStartChangeReason(),
				placementTerm.getEndChangeReason());
	}
	
	/**
	 * Tests that {@code CorrectionalStatusTermConflictException} is thrown when
	 * a placement term's end date is set to a date using
	 * {@code PlacementTermServce#update()}, causing it to conflict with a
	 * future correctional status term that has a {@code null} end date.
	 * 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists (asserted)
	 * @throws DuplicateEntityFoundException if duplicate entities are found
	 * @throws PlacementTermLockedException  if placement term is locked when
	 * update is attempted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists
	 */
	@Test(expectedExceptions = {CorrectionalStatusTermConflictException.class})
	public void
	testCorrectionalStatusTermConflictExceptionNullOriginalEndDate()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arrangements - place Largo at a pre-release
		Offender largo = this.offenderDelegate
				.createWithoutIdentity("Largo", "Emilio", null, null);
		DateRange preReleaseDateRange = new DateRange(
				this.parseDateText("12/02/2012"),
				this.parseDateText("12/03/2013"));
		SupervisoryOrganization preRelease
			= this.supervisoryOrganizationDelegate.create(
					"Pre-release", "PRC", null);
		SupervisoryOrganizationTerm preReleaseOrganizationTerm
			= this.supervisoryOrganizationTermDelegate
				.create(largo, preReleaseDateRange, preRelease);
		CorrectionalStatus altSecure = this.correctionalStatusDelegate
				.create("Alt-secure", "ALT", true, (short) 1, false);
		CorrectionalStatusTerm altStatusTerm
			= this.correctionalStatusTermDelegate
				.create(largo, preReleaseDateRange, altSecure);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(largo, preReleaseDateRange, preReleaseOrganizationTerm,
						altStatusTerm, newSentenceReason, null, false);
		
		// More arrangements - parole Largo
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		this.correctionalStatusTermDelegate.create(largo,
				new DateRange(this.parseDateText("12/03/2013"), null), parole);
		
		// Action - updates pre-release placement term with end date that's
		// after parole start date
		// This should conflict with parole status term and be caught in
		//service layer check
		this.placementTermService.update(placementTerm,
				this.parseDateText("06/04/2014"), PlacementStatus.PLACED,
				null, newSentenceReason, null);
	}
	
	/**
	 * Tests for overlapping supervisory organization terms
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CorrectionalStatusTermConflictException
	 * @throws SupervisoryOrganizationTermConflictException
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	@Test(expectedExceptions = 
		{SupervisoryOrganizationTermConflictException.class})
	public void testSupervisoryOrganizationTermConflictException() 
			throws DuplicateEntityFoundException, 
			CorrectionalStatusTermConflictException, 
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("12/02/2012");
		Date endDate = this.parseDateText("12/03/2013");
		DateRange dateRange = new DateRange(startDate, endDate);
		this.supervisoryOrganizationTermDelegate.create(offender, dateRange, 
				supervisoryOrganization);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
		
		Date secondStartDate = this.parseDateText("12/01/2011");
		Date secondEndDate = this.parseDateText("12/02/2012");
		DateRange secondDateRange
			= new DateRange(secondStartDate, secondEndDate);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, 
					secondDateRange, correctionalStatus);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
					secondDateRange, supervisoryOrganization);
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, secondDateRange, 
					supervisoryOrganizationTerm, correctionalStatusTerm, 
					changeReason, null, false);
		
		PlacementStatus updatedStatus = PlacementStatus.PLACED;
		DateRange updatedStatusDate = null;
		
		Date conflictingEndDate = this.parseDateText("06/06/2013");
		
		// Action
		this.placementTermService.update(placementTerm, 
				conflictingEndDate, updatedStatus, updatedStatusDate,
				changeReason, null);
	}
	
	/**
	 * Tests that {@code SupervisoryOrganizationTermConflictException} is thrown
	 * when a placement term's end date is set to {@code null} using
	 * {@code PlacementTermService#update(...)}, causing it to conflict with a
	 * future supervisory organization term.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities are found
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists (asserted)
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	@Test(expectedExceptions
			= {SupervisoryOrganizationTermConflictException.class})
	public void
	testSupervisoryOrganizationTermConflictExceptionWithNullConflictingEndDate()
			throws DuplicateEntityFoundException,
			CorrectionalStatusTermConflictException,
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		
		// Arrangements - secure Blofeld in prison
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", "Stavro", null);
		DateRange secureDateRange = new DateRange(
				this.parseDateText("12/02/2012"),
				this.parseDateText("12/03/2013"));
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonOrganizationTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, secureDateRange, prison);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureStatusTerm
			= this.correctionalStatusTermDelegate.create(
					blofeld, secureDateRange, secure);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(blofeld, secureDateRange, prisonOrganizationTerm,
						secureStatusTerm, newSentenceReason, null, false);
		
		// More arrangements - supervise Blofeld at P&P office after secure
		// placement
		SupervisoryOrganization pnpOffice
			= this.supervisoryOrganizationDelegate
				.create("P&P Office", "PNP", null);
		this.supervisoryOrganizationTermDelegate.create(blofeld,
				new DateRange(
						this.parseDateText("12/03/2013"),
						this.parseDateText("12/04/2014")), pnpOffice);
		
		// Action - updates secure placement term with null end date
		// This should conflict with P&P supervisory organization term and be
		// caught in service layer check
		this.placementTermService.update(
				placementTerm, null, PlacementStatus.PLACED, null,
				newSentenceReason, null);
	}
	
	/**
	 * Tests that {@code SupervisoryOrganizationTermConflictException} is thrown
	 * when a placement term's end date is set to a date using
	 * {@code PlacementTermService#update(...)}, causing it to conflict with a
	 * future correctional status that has a {@code null} end date.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist (asserted)
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	@Test(expectedExceptions = {
			SupervisoryOrganizationTermConflictException.class})
	public void
	testSupervisoryOrganizationTermConflictExceptionWithNullOriginalEndDate()
			throws DuplicateEntityFoundException,
			CorrectionalStatusTermConflictException,
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		
		// Arrangements - place Dr Blofeld at a treatment center
		Offender drBlofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange treatmentCenterDateRange = new DateRange(
				this.parseDateText("12/02/2012"),
				this.parseDateText("12/03/2013"));
		SupervisoryOrganization treatmentCenter
			= this.supervisoryOrganizationDelegate
				.create("Treatment Center", "TC", null);
		SupervisoryOrganizationTerm treatmentCenterStay
			= this.supervisoryOrganizationTermDelegate
				.create(drBlofeld, treatmentCenterDateRange, treatmentCenter);
		CorrectionalStatus altSecure
			= this.correctionalStatusDelegate.create(
					"Alt-Secure", "ALT", true, (short) 1, true);
		CorrectionalStatusTerm treatmentCenterStatusTerm
			= this.correctionalStatusTermDelegate
				.create(drBlofeld, treatmentCenterDateRange, altSecure);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(drBlofeld, treatmentCenterDateRange, treatmentCenterStay,
					treatmentCenterStatusTerm, newSentenceReason, null, false);
		
		// More arrangements - parole Dr No
		SupervisoryOrganization pnpOffice = this.supervisoryOrganizationDelegate
				.create("P&P Office", "PNP", null);
		this.supervisoryOrganizationTermDelegate.create(drBlofeld,
				new DateRange(
						this.parseDateText("12/03/2013"), null),
				pnpOffice);
		
		// Action - updates treatment center placement term with end date that
		// after parole start date
		// This should conflict with parole status term and be caugh in
		// service layer check
		this.placementTermService.update(placementTerm,
				this.parseDateText("06/04/2014"), PlacementStatus.PLACED,
				null, newSentenceReason, null);
	}
	
	/**
	 * Tests supervisory organization updates correctly
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CorrectionalStatusTermConflictException
	 * @throws SupervisoryOrganizationTermConflictException
	 * @throws PlacementTermConflictException
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void testUpdateSupervisoryOrganization() 
			throws DuplicateEntityFoundException, 
			CorrectionalStatusTermConflictException, 
			SupervisoryOrganizationTermConflictException, 
			PlacementTermConflictException,
			PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2012");
		DateRange dateRange = new DateRange(startDate, endDate);
		
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
				dateRange, supervisoryOrganization);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
		
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, dateRange,
				supervisoryOrganizationTerm, correctionalStatusTerm,  
				changeReason, null, false);

		PlacementStatus status = PlacementStatus.PLACED;
		DateRange statusDate = null;
		
		// Action
		placementTerm = this.placementTermService.update(placementTerm, 
				DateRange.getEndDate(dateRange), status, statusDate,
				changeReason, null);
		
		assertValues(placementTerm, supervisoryOrganization, 
				correctionalStatus, dateRange, changeReason, null);
	}
	
	/**
	 * Tests end date updates correctly
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CorrectionalStatusTermConflictException
	 * @throws SupervisoryOrganizationTermConflictException
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void testUpdateDateRange() 
			throws DuplicateEntityFoundException, 
			CorrectionalStatusTermConflictException, 
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2012");
		DateRange dateRange = new DateRange(startDate, endDate);
		
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
				dateRange, supervisoryOrganization);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
		
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, dateRange, 
					supervisoryOrganizationTerm, correctionalStatusTerm, 
					changeReason, null, false);
		
		Date newEndDate = this.parseDateText("12/01/2012");
		DateRange newDateRange = new DateRange(
				DateRange.getStartDate(dateRange), newEndDate);
		
		PlacementStatus status = PlacementStatus.PLACED;
		DateRange statusDate = null;
		
		// Action
		placementTerm = this.placementTermService.update(placementTerm, 
				newEndDate, status, statusDate,	changeReason, null);
		
		assertValues(placementTerm, supervisoryOrganization, 
				correctionalStatus, newDateRange, changeReason, null);
	}
	
	/**
	 * Tests start change reason updates correctly
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CorrectionalStatusTermConflictException
	 * @throws SupervisoryOrganizationTermConflictException
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void testUpdateStartChangeReason() 
			throws DuplicateEntityFoundException, 
			CorrectionalStatusTermConflictException, 
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2012");
		DateRange dateRange = new DateRange(startDate, endDate);
		
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
				dateRange, supervisoryOrganization);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
		
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, dateRange, 
					supervisoryOrganizationTerm, correctionalStatusTerm,
					changeReason, null, false);
		
		PlacementTermChangeReason newChangeReason = this.createChangeReason(
				"New Change Reason", (short) 2, true, true);
		
		PlacementStatus status = PlacementStatus.PLACED;
		DateRange statusDate = null;
		
		// Action
		placementTerm = this.placementTermService.update(placementTerm, 
				DateRange.getEndDate(dateRange), status, statusDate,
				newChangeReason, null);
		
		assertValues(placementTerm, supervisoryOrganization, 
				correctionalStatus, dateRange, newChangeReason, null);
	}
	
	/**
	 * Tests end change reason updates correctly
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CorrectionalStatusTermConflictException
	 * @throws SupervisoryOrganizationTermConflictException
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void testUpdateEndChangeReason() 
			throws DuplicateEntityFoundException, 
			CorrectionalStatusTermConflictException, 
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2012");
		DateRange dateRange = new DateRange(startDate, endDate);
		
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
				dateRange, supervisoryOrganization);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
		
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, dateRange, 
					supervisoryOrganizationTerm, correctionalStatusTerm, 
					changeReason, null, false);
		
		PlacementTermChangeReason newChangeReason = this.createChangeReason(
				"New Change Reason", (short) 2, true, true);

		PlacementStatus status = PlacementStatus.PLACED;
		DateRange statusDate = null;
		
		// Action
		placementTerm = this.placementTermService.update(placementTerm, 
				DateRange.getEndDate(dateRange), status, statusDate,
				changeReason, newChangeReason);
		
		assertValues(placementTerm, supervisoryOrganization, 
				correctionalStatus, dateRange, changeReason, 
				newChangeReason);
	}
	
	/**
	 * Tests that when a placement status is set to escaped that the underlying 
	 * location terms end date is set to the escapee status start date. 
	 * 
	 * @throws DuplicateEntityFoundException
	 * @throws CorrectionalStatusTermConflictException
	 * @throws SupervisoryOrganizationTermConflictException
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	@Test
	public void testUpdateEscapedStatus() 
			throws DuplicateEntityFoundException, 
			CorrectionalStatusTermConflictException, 
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		// Arrangements
		Offender offender = this.createOffender();
		
		SupervisoryOrganization supervisoryOrganization 
			= this.createSupervisoryOrganization("SuperOrg", "SO");
		
		CorrectionalStatus correctionalStatus = this.createCorrectionalStatus(
				"SECURE", "SEC", true, (short) 1);
		
		Date startDate = this.parseDateText("09/12/2001");
		Date endDate = this.parseDateText("12/03/2012");
		DateRange dateRange = new DateRange(startDate, endDate);
		
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
				dateRange, supervisoryOrganization);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, dateRange, 
				correctionalStatus);
		
		PlacementTermChangeReason changeReason = this.createChangeReason(
				"Sentence Imposed, Initial Status", (short) 1, true, true);
		
		PlacementTerm placementTerm 
			= this.placementTermDelegate.create(offender, dateRange, 
					supervisoryOrganizationTerm, correctionalStatusTerm, 
					changeReason, null, false);
		
		PlacementStatus status = PlacementStatus.ESCAPED;
		Date escapeStartDate = this.parseDateText("03/03/2010");
		DateRange statusDate = new DateRange(escapeStartDate, null);
		
		Country unitedStates = this.countryDelegate.create("United States", 
				"US", true);
		State mt = this.stateDelegate.create("Montana", "MT", unitedStates, 
				true, true);
		City helena = this.cityDelegate.create("Helena", true, mt, 
				unitedStates);
		ZipCode mt59602 = this.zipCodeDelegate.create(helena, "59602", null, 
				true);
		Address address = this.addressDelegate.findOrCreate("1000", null, null, 
				BuildingCategory.APARTMENT,  mt59602);
		Location location = this.locationDelegate.create(
				supervisoryOrganization, null, address);
		this.locationTermDelegate.create(offender, location, startDate, endDate, 
				false, null);
		
		// Action
		placementTerm = this.placementTermService.update(placementTerm, 
				DateRange.getEndDate(dateRange), status, statusDate,
				changeReason, null);
		
		// Assert
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("supervisoryOrganizationTerm", 
					supervisoryOrganizationTerm)
			.addExpectedValue("correctionalStatusTerm", correctionalStatusTerm)
			.addExpectedValue("locked", false)
			.addExpectedValue("startChangeReason", changeReason)
			.addExpectedValue("endChangeReason", null)
			.performAssertions(placementTerm);
		// The update method updates the underlying location terms end date, 
		// the following ensures that actually happened. The date manipulator is 
		// called to ensure we find the location term that was active prior to 
		// the escape date
		DateManipulator searchDate = new DateManipulator(escapeStartDate);
		searchDate.changeDate(-1);
		LocationTerm locationTerm = this.locationTermDelegate
				.findByOffenderOnDate(offender, searchDate.getDate());
		assert locationTerm != null : "Location term was not found";
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.endDate", escapeStartDate)
			.performAssertions(locationTerm);
	}
	
	/**
	 * Test that updates to locked placement terms are prevented
	 * 
	 * @throws PlacementTermLockedException if placement term is locked
	 * (assertion)
	 * @throws DuplicateEntityFoundException if placement term exists
	 * @throws SupervisoryOrganizationTermConflictException if supervisory
	 * organization terms conflict
	 * @throws CorrectionalStatusTermConflictException if correctional status
	 * terms conflict 
	 */
	@Test(expectedExceptions = {PlacementTermLockedException.class})
	public void testPlacementTermLockedException()
			throws PlacementTermLockedException,
					DuplicateEntityFoundException,
					CorrectionalStatusTermConflictException,
					SupervisoryOrganizationTermConflictException {
		
		// Arrangements
		Offender offender = this.createOffender();
		SupervisoryOrganization paroleOffice
			= this.createSupervisoryOrganization(
					"City Parole Office", "CiParO");
		CorrectionalStatus paroleStatus
			= this.createCorrectionalStatus(
					"Parole", "PAR", false, (short) 1);
		PlacementTermChangeReason startOfSupervisionStatus
			= this.createChangeReason("Start of Supervision", (short) 1,
					true, false);
		DateRange dateRange = new DateRange(
				this.parseDateText("05/23/2011"), null);
		SupervisoryOrganizationTerm supervisoryOrganizationTerm 
			= this.supervisoryOrganizationTermDelegate.create(offender, 
					dateRange, paroleOffice);
		CorrectionalStatusTerm correctionalStatusTerm 
			= this.correctionalStatusTermDelegate.create(offender, dateRange, 
					paroleStatus);
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, dateRange, supervisoryOrganizationTerm,
						correctionalStatusTerm, startOfSupervisionStatus, null,
						true);
		
		// Action
		this.placementTermService.update(placementTerm,
				this.parseDateText("07/23/2013"),
				placementTerm.getStatus(),
				placementTerm.getStatusDateRange(),
				placementTerm.getStartChangeReason(),
				placementTerm.getEndChangeReason());
	}
	
	/**
	 * Tests that supervisory organization term end date is updated.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 */
	public void testUpdateSupervisoryOrganizationTermEndDate()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arrangements - places Blofeld with an erroneous release date
		Offender offender = this.createOffender();
		CorrectionalStatus paroleStatus = this.createParoleStatus();
		SupervisoryOrganization pnpOffice = this.createPnpOffice();
		DateRange originalDateRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2016"));
		CorrectionalStatusTerm paroleTerm
			= this.correctionalStatusTermDelegate
				.create(offender, originalDateRange, paroleStatus);
		SupervisoryOrganizationTerm pnpTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, originalDateRange, pnpOffice);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm
			= this.placementTermDelegate.create(
					offender, originalDateRange, pnpTerm, paroleTerm,
					newSentenceReason, null, false);
		
		// Action - corrects release date
		Date updatedEndDate = this.parseDateText("12/12/2017");
		placementTerm = this.placementTermService.update(placementTerm,
				updatedEndDate, PlacementStatus.PLACED, null, null, null);
		
		// Assertions - verifies that end date of supervisory organization
		// term was also corrected
		assert placementTerm.getSupervisoryOrganizationTerm().getDateRange()
				.getEndDate().equals(updatedEndDate)
			: String.format("Wrong end date: %s expected; %s found",
				updatedEndDate,
				placementTerm.getSupervisoryOrganizationTerm().getDateRange()
					.getEndDate());
	}
	
	/**
	 * Tests that supervisory organization term {@code null} end date is updated
	 * to not {@code null}.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 */
	public void testUpdateSupervisoryOrganizationTermNullEndDate()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arrangements - places Blofeld with an unknown (null) end date
		Offender offender = this.createOffender();
		CorrectionalStatus paroleStatus = this.createParoleStatus();
		SupervisoryOrganization pnpOffice = this.createPnpOffice();
		DateRange originalDateRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(offender, originalDateRange, paroleStatus);
		SupervisoryOrganizationTerm pnpTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, originalDateRange, pnpOffice);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, originalDateRange, pnpTerm, paroleTerm,
						newSentenceReason, null, false);
		
		// Action - updates to known end date
		Date endDate = this.parseDateText("12/12/2017");
		placementTerm = this.placementTermService
				.update(placementTerm, endDate, PlacementStatus.PLACED,
						null, null, null);
		
		// Asserts that supervisory organization term was updated to known
		// end date
		assert placementTerm.getSupervisoryOrganizationTerm().getDateRange()
				.getEndDate().equals(endDate)
			: String.format("Wrong end date: %s expected; %s found",
					endDate, placementTerm.getSupervisoryOrganizationTerm()
						.getDateRange().getEndDate());
	}
	
	/**
	 * Tests that correctional status term end date is updated. 
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermLockedException  if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional stauts terms exist
	 */
	public void testUpdateCorrectionalStatusTermEndDate()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arrangements - places Blofeld with an erroneous release date
		Offender offender = this.createOffender();
		CorrectionalStatus paroleStatus = this.createParoleStatus();
		SupervisoryOrganization pnpOffice = this.createPnpOffice();
		DateRange originalDateRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2016"));
		CorrectionalStatusTerm paroleTerm
			= this.correctionalStatusTermDelegate
				.create(offender, originalDateRange, paroleStatus);
		SupervisoryOrganizationTerm pnpTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, originalDateRange, pnpOffice);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm
			= this.placementTermDelegate.create(
					offender, originalDateRange, pnpTerm, paroleTerm,
					newSentenceReason, null, false);
		
		// Action - corrects release date
		Date updatedEndDate = this.parseDateText("12/12/2017");
		placementTerm = this.placementTermService.update(placementTerm,
				updatedEndDate, PlacementStatus.PLACED, null, newSentenceReason,
				null);
		
		// Assertions - verifies that end date of correctional status
		// term was also corrected
		assert placementTerm.getCorrectionalStatusTerm().getDateRange()
				.getEndDate().equals(updatedEndDate)
			: String.format("Wrong end date: %s expected; %s found",
				updatedEndDate,
				placementTerm.getCorrectionalStatusTerm().getDateRange()
					.getEndDate());
	}
	
	/**
	 * Tests that correctional status term {@code null} end date is updated to
	 * not {@code null}.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermLockedException  if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms eixst 
	 */
	public void testUpdateCorrectionalStatusTermNullEndDate()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arrangements - places Blofeld with an unknown (null) end date
		Offender offender = this.createOffender();
		CorrectionalStatus paroleStatus = this.createParoleStatus();
		SupervisoryOrganization pnpOffice = this.createPnpOffice();
		DateRange originalDateRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(offender, originalDateRange, paroleStatus);
		SupervisoryOrganizationTerm pnpTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, originalDateRange, pnpOffice);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, originalDateRange, pnpTerm, paroleTerm,
						newSentenceReason, null, false);
		
		// Action - updates to known end date
		Date endDate = this.parseDateText("12/12/2017");
		placementTerm = this.placementTermService
				.update(placementTerm, endDate, PlacementStatus.PLACED,
						null, newSentenceReason, null);
		
		// Asserts that correctional status term was updated to known end date
		assert placementTerm.getCorrectionalStatusTerm().getDateRange()
				.getEndDate().equals(endDate)
			: String.format("Wrong end date: %s expected; %s found",
					endDate, placementTerm.getCorrectionalStatusTerm()
						.getDateRange().getEndDate());
	}
	
	/**
	 * Tests that first placement term of three can be updated with same
	 * values without a conflict being erroneously reported.
	 * 
	 * <p>Supervisory organization terms are not created.
	 * 
	 * @throws PlacementTermLockedException if placement term is locked 
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 */
	public void testUpdateFirstPlacementTermOfThreeWithSameValues()
			throws CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException,
				DuplicateEntityFoundException {
		
		// Arrangements - places Blofeld securely, on parole and then probation
		Offender offender = this.createOffender();
		DateRange secureRange = this.createDateRange(
				"01/01/2001", "02/02/2002");
		CorrectionalStatus secureStatus = this.createSecureStatus();
		PlacementTermChangeReason newSentenceReason	
				= this.createNewSentenceReason();
		PlacementTerm securePlacement = this.placementTermDelegate.create(
				offender, secureRange, null,
				this.correctionalStatusTermDelegate
					.create(offender, secureRange, secureStatus),
				newSentenceReason, null, false);
		DateRange paroleRange = this.createDateRange(
				"02/02/2002", "03/03/2003");
		PlacementTermChangeReason boardDecision = this.createBoardDecision();
		this.placementTermDelegate.create(offender, paroleRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, paroleRange, this.createParoleStatus()),
				boardDecision, null, false);
		DateRange probationRange = this.createDateRange(
				"03/03/2003", "04/04/2004");
		PlacementTermChangeReason secureTermExpiredReason
				= this.createSecureTermExpired();
		this.placementTermDelegate.create(offender, probationRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, probationRange, this.createProbationStatus()),
				secureTermExpiredReason, null, false);
		
		// Action - updates secure placement term with same values
		securePlacement = this.placementTermService.update(securePlacement,
				DateRange.getEndDate(secureRange),
				securePlacement.getStatus(),
				securePlacement.getStatusDateRange(),
				securePlacement.getStartChangeReason(),
				securePlacement.getEndChangeReason());
		
		// Assertions - verifies that properties are the same
		PropertyValueAsserter.create()
				.addExpectedValue("dateRange", secureRange)
				.addExpectedValue("correctionalStatusTerm.correctionalStatus",
						secureStatus)
				.addExpectedValue("startChangeReason",
						newSentenceReason)
				.performAssertions(securePlacement);
	}
	
	/**
	 * Tests that middle placement term of three can be updated with same
	 * values without a conflict being erroneously reported.
	 * 
	 * <p>Supervisory organization terms are not created.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void testUpdateSecondPlacementTermOfThreeWithSameValues()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
					SupervisoryOrganizationTermConflictException,
					PlacementTermLockedException {
		
		// Arrangements - places Blofeld securely, on parole and then probation
		Offender offender = this.createOffender();
		DateRange secureRange = this.createDateRange(
				"01/01/2001", "02/02/2002");
		PlacementTermChangeReason newSentenceReason
				= this.createNewSentenceReason();
		this.placementTermDelegate.create(offender, secureRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, secureRange, this.createSecureStatus()),
				newSentenceReason, null, false);
		DateRange paroleRange = this.createDateRange(
				"02/02/2002", "03/03/2003");
		CorrectionalStatus paroleStatus = this.createParoleStatus();
		PlacementTermChangeReason boardDecision = this.createBoardDecision();
		PlacementTerm parolePlacement = this.placementTermDelegate
				.create(offender, paroleRange, null,
						this.correctionalStatusTermDelegate.create(
								offender, paroleRange, paroleStatus), 
				boardDecision, null, false);
		DateRange probationRange = this.createDateRange(
				"03/03/2003", "04/04/2004");
		PlacementTermChangeReason secureTermExpiredReason
				= this.createSecureTermExpired();
		this.placementTermDelegate.create(offender, probationRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, probationRange, this.createProbationStatus()),
				secureTermExpiredReason, null, false);
		
		// Action - updates parole placement term with same values
		parolePlacement = this.placementTermService.update(parolePlacement,
				DateRange.getEndDate(paroleRange),
				parolePlacement.getStatus(), 
				parolePlacement.getStatusDateRange(),
				parolePlacement.getStartChangeReason(),
				parolePlacement.getEndChangeReason());
		
		// Assertions - verifies that properties are the same
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange", paroleRange)
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					paroleStatus)
			.addExpectedValue("startChangeReason", boardDecision)
			.performAssertions(parolePlacement);
	}
	
	/**
	 * Tests that last placement term of three can be updated with same values
	 * without a conflict being erroneously reported.
	 * 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 */
	public void testUpdateThirdPlacementTermOfThreeWithSameValues()
			throws CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException,
				DuplicateEntityFoundException {
		
		// Arrangements - places Blofeld securely, on parole and then probation
		Offender offender = this.createOffender();
		DateRange secureRange = this.createDateRange(
				"01/01/2001", "02/02/2002");
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		this.placementTermDelegate.create(offender, secureRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, secureRange, this.createSecureStatus()),
				newSentenceReason, null, false);
		DateRange paroleRange = this.createDateRange(
				"02/02/2002", "03/03/2003");
		PlacementTermChangeReason boardDecision = this.createBoardDecision();
		this.placementTermDelegate.create(offender, paroleRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, paroleRange, this.createParoleStatus()),
				boardDecision, null, false);
		DateRange probationRange = this.createDateRange(
				"03/03/2003", "04/04/2004");
		CorrectionalStatus probationStatus = this.createProbationStatus();
		PlacementTermChangeReason secureTermExpiredReason
				= this.createSecureTermExpired();
		PlacementTerm probationPlacement = this.placementTermDelegate
				.create(offender, probationRange, null,
						this.correctionalStatusTermDelegate
							.create(offender, probationRange, probationStatus),
						secureTermExpiredReason, null, false);
		
		// Action - updates probation placement term with same values
		probationPlacement = this.placementTermService
				.update(probationPlacement,
						DateRange.getEndDate(probationRange),
						probationPlacement.getStatus(),
						probationPlacement.getStatusDateRange(),
						probationPlacement.getStartChangeReason(),
						probationPlacement.getEndChangeReason());
		
		// Assertions - verifies that properties are the same
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange", probationRange)
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					probationStatus)
			.performAssertions(probationPlacement);
	}
	
	/**
	 * Tests that first placement term can be updated with original values
	 * when end date of second placement term is {@code null}.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void testUpdateFirstWithSameValuesWhenSecondEndDateIsNull()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arranges offender securely and then on parole without an end date
		Offender offender = this.createOffender();
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2014"));
		CorrectionalStatus secure = this.createSecureStatus();
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, secureRange, secure);
		SupervisoryOrganization prison = this.createPrison();
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, secureRange, prison);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm securePlacementTerm
			= this.placementTermDelegate
				.create(offender, secureRange, prisonTerm, secureTerm,
						newSentenceReason, null, false);
		DateRange paroleRange = new DateRange(
				secureRange.getEndDate(),
				null);
		CorrectionalStatus parole = this.createParoleStatus();
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(offender, paroleRange, parole);
		SupervisoryOrganization pnpOffice = this.createPnpOffice();
		SupervisoryOrganizationTerm pnpOfficeTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, paroleRange, pnpOffice);
		PlacementTermChangeReason boardDecision
			= this.createBoardDecision();
		this.placementTermDelegate.create(
				offender, paroleRange, pnpOfficeTerm, paroleTerm,
				boardDecision, null, false);
		
		// Action - updates secure term with same date values
		securePlacementTerm = this.placementTermService
				.update(securePlacementTerm,
						secureRange.getEndDate(),
						securePlacementTerm.getStatus(),
						securePlacementTerm.getStatusDateRange(), 
						securePlacementTerm.getStartChangeReason(),
						securePlacementTerm.getEndChangeReason());
		
		// Asserts that values remain the same
		PropertyValueAsserter.create()
			.addExpectedValue(
					"correctionalStatusTerm.correctionalStatus", secure)
			.addExpectedValue(
					"supervisoryOrganizationTerm.supervisoryOrganization",
					prison)
			.addExpectedValue("dateRange.startDate",
					secureRange.getStartDate())
			.addExpectedValue("dateRange.endDate",
					secureRange.getEndDate())
			.addExpectedValue("startChangeReason",
					newSentenceReason)
			.performAssertions(securePlacementTerm);
	}

	/**
	 * Tests that on update correctional status term on end date is used if
	 * correctional status matches.
	 * 
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 */
	@Test(enabled = false) // Doesn't work - SA
	public void testUseMatchingCorrectionalStatusOnEndDate()
			throws CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException,
				DuplicateEntityFoundException {
		
		// Arrangements - creates two correctional status terms that are
		// consecutive but have the same status, creates a placement term
		// for the first correctional status term
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecureStatus();
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("01/01/2013"));
		CorrectionalStatusTerm firstSecureTerm
			= this.correctionalStatusTermDelegate
				.create(offender, dateRange, secure);
		DateRange secondRange = new DateRange(
				this.parseDateText("01/01/2013"),
				this.parseDateText("02/02/2014"));
		CorrectionalStatusTerm secondSecureTerm
			= this.correctionalStatusTermDelegate
				.create(offender, secondRange, secure);
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, dateRange, null, firstSecureTerm,
						this.createNewSentenceReason(), null, false);
		
		// Action - changes the end date to some time during the second
		// correctional status term
		Date newEndDate = this.parseDateText("06/06/2013");
		placementTerm = this.placementTermService.update(
				placementTerm, newEndDate, PlacementStatus.PLACED, null,
				placementTerm.getStartChangeReason(), null);
		
		// Assertions - second correctional status term should be started
		// earlier and used first one should be discarded
		assert placementTerm.getCorrectionalStatusTerm()
				.equals(secondSecureTerm)
			: "Placement term uses wrong correctional status term";
	}
	
	/**
	 * Tests that attempts to update end date to that of start date are
	 * prevented by a {@code IllegalArgumentException} being thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptionIfStartDateEqualsEndDate()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arranges offender to be placed on secure status
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecureStatus();
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2013"));
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, dateRange, secure);
		PlacementTerm placementTerm = this.placementTermDelegate.create(
				offender, dateRange, null, secureTerm, null, null, false);
		
		// Action attempts to change end date of placement term to start date
		this.placementTermService.update(placementTerm,
				DateRange.getStartDate(dateRange), null, null, null, null);
	}
	
	/**
	 * Tests that, when placement term is updated to overlap and existing
	 * correctional status term with the same correctional status, the
	 * existing correctional status term is expanded and used and the old
	 * one removed.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory orgnaization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 */
	public void testCorrectionalStatusTermOnEndDateIsUsedWhenOverlapping()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arranges offender securely twice
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecureStatus();
		DateRange firstSecureRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2012"));
		CorrectionalStatusTerm firstSecureTerm
			= this.correctionalStatusTermDelegate.create(
					offender, firstSecureRange, secure);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, firstSecureRange,
						null, firstSecureTerm, newSentenceReason,
						null, false);
		DateRange secondSecureRange = new DateRange(
				this.parseDateText("12/12/2013"),
				this.parseDateText("12/12/2015"));
		this.correctionalStatusTermDelegate.create(
				offender, secondSecureRange, secure);
		
		// Action - adjusts end date of first placement term to be within
		// second correctional status term range
		placementTerm = this.placementTermService.update(
				placementTerm, DateRange.getStartDate(secondSecureRange),
				PlacementStatus.PLACED, null, newSentenceReason, null);
			
		// Asserts that only a single correctional status term exists within
		// range
		assert this.correctionalStatusTermDelegate
			.countForOffenderBetweenDatesExcluding(offender,
					DateRange.getStartDate(firstSecureRange),
					DateRange.getEndDate(secondSecureRange),
					placementTerm.getCorrectionalStatusTerm()) == 0;
		
		// Asserts that correctional status used by placement term spans
		// placement term
		assert DateRange.occursWithin(placementTerm.getDateRange(),
				placementTerm.getCorrectionalStatusTerm().getDateRange())
			: String.format("Placement term does not occur within"
					+ " correctional status term: %s and %s, respectively",
					placementTerm.getDateRange(),
					placementTerm.getCorrectionalStatusTerm().getDateRange());
	}
	
	/**
	 * Tests that, when placement term is updated to overlap and existing
	 * correctional status term with the same correctional status, the
	 * existing correctional status term is expanded and used and the old
	 * one removed.
	 * 
	 * <p>Original end date of placement term is {@code null}.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory orgnaization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 */
	public void
	testCorrectionalStatusTermOnEndDateIsUsedWhenOverlappingWithOriginalNull()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arranges offender on secure status with two correctional status
		// terms
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecureStatus();
		DateRange firstSecureRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2012"));
		CorrectionalStatusTerm firstSecureTerm
			= this.correctionalStatusTermDelegate
				.create(offender, firstSecureRange, secure);
		DateRange placementRange = new DateRange(
				this.parseDateText("12/12/2010"),
				null);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, placementRange, null, firstSecureTerm,
						newSentenceReason, null, false);
		DateRange secondSecureRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2014"));
		this.correctionalStatusTermDelegate.create(
				offender, secondSecureRange, secure);
		
		// Action updates placement term with none-null end date
		placementTerm = this.placementTermService.update(placementTerm,
				DateRange.getStartDate(secondSecureRange),
				PlacementStatus.PLACED, null, newSentenceReason, null);
		
		
		// Asserts that only a single correctional status term exists within
		// range
		assert this.correctionalStatusTermDelegate
			.countForOffenderBetweenDatesExcluding(offender,
					DateRange.getStartDate(firstSecureRange),
					DateRange.getEndDate(secondSecureRange),
					placementTerm.getCorrectionalStatusTerm()) == 0;
		
		// Asserts that correctional status used by placement term spans
		// placement term
		assert DateRange.occursWithin(placementTerm.getDateRange(),
				placementTerm.getCorrectionalStatusTerm().getDateRange())
			: String.format("Placement term does not occur within"
					+ " correctional status term: %s and %s, respectively",
					placementTerm.getDateRange(),
					placementTerm.getCorrectionalStatusTerm().getDateRange());
	}
	
	/**
	 * Tests that, when placement term is updated to overlap an existing
	 * supervisory organization term with the same supervisory organization,
	 * the existing supervisory organization term is expanded and used and
	 * the old one removed.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void
	testSupervisoryOrganizationTermOnEndDateIsUsedWhenOverlapping()
			throws DuplicateEntityFoundException,
			CorrectionalStatusTermConflictException,
			SupervisoryOrganizationTermConflictException,
			PlacementTermLockedException {
		
		
		// Arranges offender on secure status with two supervisory organization
		// terms
		Offender offender = this.createOffender();
		CorrectionalStatus secure = this.createSecureStatus();
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2015"));
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, secureRange, secure);
		DateRange firstPnpRange = new DateRange(
				secureRange.getStartDate(),
				this.parseDateText("12/12/2012"));
		SupervisoryOrganization pnpOffice = this.createPnpOffice();
		SupervisoryOrganizationTerm firstPnpTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, firstPnpRange, pnpOffice);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, firstPnpRange, firstPnpTerm, secureTerm,
						newSentenceReason, null, false);
		DateRange secondPnpRange = new DateRange(
				this.parseDateText("12/12/2013"),
				secureRange.getEndDate());
		this.supervisoryOrganizationTermDelegate
				.create(offender, secondPnpRange, pnpOffice);
		
		// Action - updates placement term to span both supervisory organization
		// terms
		placementTerm = this.placementTermService
				.update(placementTerm, secondPnpRange.getStartDate(),
						PlacementStatus.PLACED, null, newSentenceReason, null);
		
		// Asserts that only supervisory organization term used exists
		// within range of placement term
		assert this.supervisoryOrganizationTermDelegate
			.countForOffenderBetweenDatesExcluding(offender,
					DateRange.getStartDate(firstPnpRange),
					DateRange.getEndDate(secondPnpRange),
					placementTerm.getSupervisoryOrganizationTerm()) == 0;
		
		// Asserts that supervisory organization term used by placement term
		// spans placement term
		assert DateRange.occursWithin(placementTerm.getDateRange(),
				placementTerm.getSupervisoryOrganizationTerm().getDateRange())
			: String.format("Placement term does not occur within"
					+ " supervisory organization term: %s and %s, respectively",
					placementTerm.getDateRange(),
					placementTerm.getSupervisoryOrganizationTerm()
						.getDateRange());
	}
	
	/**
	 * Tests that, when placement term is updated to overlap and existing
	 * supervisory organization term with the same supervisory organization,
	 * the existing supervisory organization term is expanded and used and the
	 * old one removed.
	 * 
	 * <p>Original end date of placement term is {@code null}.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermLockedException if placement term is locked
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory orgnaization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 */
	public void
	testSupervisoryOrganizationTermOnEndDateIsUsedWhenOverlappingWithOriginalNull()
			throws DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermLockedException {
		
		// Arranges offender securely with prison supervisory organization
		// at during two terms
		Offender offender = this.createOffender();
		DateRange placementRange = new DateRange(
				this.parseDateText("12/12/2010"),
				null);
		CorrectionalStatus secure = this.createSecureStatus();
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, placementRange, secure);
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		DateRange firstPrisonRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2012"));
		SupervisoryOrganizationTerm firstPrisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, firstPrisonRange, prison);
		PlacementTermChangeReason newSentenceReason	
			= this.createNewSentenceReason();
		PlacementTerm placementTerm = this.placementTermDelegate
				.create(offender, placementRange, firstPrisonTerm, secureTerm,
						newSentenceReason, null, false);
		DateRange secondPrisonRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2014"));
		this.supervisoryOrganizationTermDelegate.create(
				offender, secondPrisonRange, prison);
		
		// Action updates placement term with none-null end date
		placementTerm = this.placementTermService
				.update(placementTerm,
						DateRange.getStartDate(secondPrisonRange),
						PlacementStatus.PLACED, null, newSentenceReason, null);
		
		// Asserts that only a single supervisory organization term exists
		// within range
		assert this.supervisoryOrganizationTermDelegate
			.countForOffenderBetweenDatesExcluding(offender,
					DateRange.getStartDate(firstPrisonRange),
					DateRange.getEndDate(secondPrisonRange), 
					placementTerm.getSupervisoryOrganizationTerm()) == 0;
		
		// Asserts that supervisory organization term used by placement term
		// spans placement term
		assert DateRange.occursWithin(placementTerm.getDateRange(),
				placementTerm.getSupervisoryOrganizationTerm().getDateRange())
			: String.format("Placement term does not occur within supervisory"
					+ " organization term: %s and %s, respectively",
					placementTerm.getDateRange(),
					placementTerm.getSupervisoryOrganizationTerm()
						.getSupervisoryOrganization());
	}
	
	/**
	 * Confirms that first supervisory organization term in a series of three
	 * consecutive can be updated with the same values without a conflict
	 * being reported incorrectly.
	 * 
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws CorrectionalStatusExistsException 
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws PlacementTermLockedException if placement term is locked
	 */
	public void
	testSupervisoryOrganizationTermWithStartingOnEndDateOfNextDoesNotConflict()
			throws SupervisoryOrganizationTermExistsException,
				SupervisoryOrganizationExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException,
				PlacementTermLockedException {
	
		// Arranges offender securely and then later on supervisory organization
		// indefinitely - the terms are not consecutive
		Offender offender = this.createOffender();
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2018"));
		PlacementTerm securePlacement 
			= this.placementTermDelegate.create(offender, secureRange,
				this.supervisoryOrganizationTermDelegate
					.create(offender, secureRange,
						this.supervisoryOrganizationDelegate
							.create("Prison", "PRSN", null)),
				this.correctionalStatusTermDelegate
					.create(offender, secureRange,
						this.correctionalStatusDelegate
							.create("Secure", "SEC", true, (short) 1, true)),
				this.placementTermChangeReasonDelegate
					.create("Initial Sentence", (short) 1, true, false),
				null, false);
		DateRange pnpOfficeRange = new DateRange(
				secureRange.getEndDate(),
				this.parseDateText("12/12/2020"));
		this.supervisoryOrganizationTermDelegate.create(
				offender, pnpOfficeRange,
				this.supervisoryOrganizationDelegate.create(
						"P&P Office", "PNPOFF", null));
		DateRange prereleaseRange = new DateRange(
				pnpOfficeRange.getEndDate(), null);
		this.supervisoryOrganizationTermDelegate.create(
				offender, prereleaseRange,
				this.supervisoryOrganizationDelegate.create(
						"Prerelease", "PRE", null));
		
		// Action - updates placement term to end on start date of later
		// supervisory organization term
		this.placementTermService.update(securePlacement,
				secureRange.getEndDate(),
				securePlacement.getStatus(),
				securePlacement.getStatusDateRange(),
				securePlacement.getStartChangeReason(),
				securePlacement.getEndChangeReason());
		
		// Asserts that end date remains the same
		PropertyValueAsserter.create()
			.addExpectedValue("supervisoryOrganizationTerm.dateRange.endDate",
					secureRange.getEndDate())
			.performAssertions(securePlacement);
	}
	
	/* Helper methods. */
	
	// Asserts values
	private void assertValues(PlacementTerm placementTerm, 
			SupervisoryOrganization supervisoryOrganization,
			CorrectionalStatus correctionalStatus, DateRange dateRange, 
			PlacementTermChangeReason startChangeReason, 
			PlacementTermChangeReason endChangeReason) {
		assert supervisoryOrganization.equals(placementTerm
			.getSupervisoryOrganizationTerm().getSupervisoryOrganization()) 
			: String.format("Wrong supervisory organization: %s found; %s "
				+ "expected", placementTerm.getSupervisoryOrganizationTerm()
				.getSupervisoryOrganization().getName(), 
				supervisoryOrganization.getName());
		assert correctionalStatus.equals(placementTerm
			.getCorrectionalStatusTerm().getCorrectionalStatus()) : String
			.format("Wrong correctional status: %s found; %s expected", 
				placementTerm.getCorrectionalStatusTerm()
				.getCorrectionalStatus().getName(), 
				correctionalStatus.getName());
		assert dateRange.equals(placementTerm.getDateRange()) : String.format(
			"Wrong date range: %s Found; %s expected", placementTerm
			.getDateRange().toString(), dateRange.toString());
		assert startChangeReason.equals(placementTerm.getStartChangeReason()) :
			String.format("Wrong start change reason: %s found; %s expected", 
				placementTerm.getStartChangeReason().getName(), 
				startChangeReason.getName());
		if (endChangeReason == null || placementTerm.getEndChangeReason() 
				== null) {
			assert endChangeReason == null && 
				placementTerm.getEndChangeReason() == null : String.format(
					"Mismatch end change reason nulls: %s found; %s expected", 
					placementTerm.getEndChangeReason() == null ? "null" : 
					"not null", endChangeReason == null ? "null" : "not null");
		} else {
			assert endChangeReason.equals(placementTerm.getEndChangeReason()) :
				String.format("Wrong end change reason: %s found; %s expected", 
					placementTerm.getEndChangeReason().getName(), 
					endChangeReason.getName());	
		}
	}
	
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
	
	// Creates an offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity("Blofeld", "Julius", 
				"Francisco", null);
	}
	
	// Creates a supervisory organization
	private SupervisoryOrganization createSupervisoryOrganization(
			final String name, final String alias) 
			throws DuplicateEntityFoundException {
		return this.supervisoryOrganizationDelegate.create(name, alias, null);
	}
	
	// Creates a correctional status
	private CorrectionalStatus createCorrectionalStatus(final String name, 
			final String abbreviation, final boolean locationRequired, 
			final short sortOrder) 
			throws DuplicateEntityFoundException {
		return this.correctionalStatusDelegate.create(name, abbreviation, 
				locationRequired, sortOrder, true);
	}
	
	// Creates a placement term change reason
	private PlacementTermChangeReason createChangeReason(final String name, 
			final short sortOrder, final boolean validStartReason, 
			final boolean validEndReason) 
			throws DuplicateEntityFoundException {
		return this.placementTermChangeReasonDelegate.create(name, sortOrder, 
				validStartReason, validEndReason);
	}
	
	// Returns new sentence reason
	private PlacementTermChangeReason createNewSentenceReason()
			throws DuplicateEntityFoundException {
		return this.createChangeReason("New Sentence", (short) 1, true, true);
	}
	
	// Returns board decision reason
	private PlacementTermChangeReason createBoardDecision()
			throws DuplicateEntityFoundException {
		return this.createChangeReason("Board Decision", (short) 1, true, true);
	}
	
	// Returns secure term expired reason
	private PlacementTermChangeReason createSecureTermExpired()
			throws DuplicateEntityFoundException {
		return this.createChangeReason(
				"Secure Term Expired", (short) 1, true, true);
	}
	
	// Returns parole status
	private CorrectionalStatus createParoleStatus()
			throws DuplicateEntityFoundException {
		return this.createCorrectionalStatus("Parole", "PAR", false, (short) 1);
	}
	
	// Returns secure status
	private CorrectionalStatus createSecureStatus()
			throws DuplicateEntityFoundException {
		return this.createCorrectionalStatus("Secure", "SEC", true, (short) 2);
	}
	
	// Returns probation status
	private CorrectionalStatus createProbationStatus()
			throws DuplicateEntityFoundException {
		return this.createCorrectionalStatus(
				"Probation", "PRO", true, (short) 3);
	}
	
	// Returns P & P office
	private SupervisoryOrganization createPnpOffice()
			throws DuplicateEntityFoundException {
		return this.createSupervisoryOrganization("PnP Office", "PNP");
	}
	
	// Returns prison organization
	private SupervisoryOrganization createPrison()
			throws DuplicateEntityFoundException {
		return this.createSupervisoryOrganization("Prison", "PRSN");
	}
	
	// Returns date range
	private DateRange createDateRange(
			final String startDateText,
			final String endDateText) {
		return new DateRange(
				this.parseDateText(startDateText),
				this.parseDateText(endDateText));
	}
}