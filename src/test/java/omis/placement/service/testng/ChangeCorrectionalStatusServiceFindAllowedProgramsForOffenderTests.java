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
package omis.placement.service.testng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.country.domain.Country;
import omis.country.exception.CountryExistsException;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.location.domain.Location;
import omis.location.exception.LocationExistsException;
import omis.location.service.delegate.LocationDelegate;
import omis.locationterm.exception.LocationTermExistsException;
import omis.locationterm.service.delegate.LocationTermDelegate;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.placement.service.ChangeCorrectionalStatusService;
import omis.program.domain.Program;
import omis.program.exception.ProgramExistsException;
import omis.program.exception.ProgramLocationOfferExistsException;
import omis.program.exception.ProgramSupervisoryOrganizationOfferExistsException;
import omis.program.service.delegate.ProgramDelegate;
import omis.program.service.delegate.ProgramLocationOfferDelegate;
import omis.program.service.delegate.ProgramSupervisoryOrganizationOfferDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.exception.StateExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTerm;
import omis.supervision.domain.PlacementTermChangeReason;
import omis.supervision.domain.SupervisoryOrganization;
import omis.supervision.exception.CorrectionalStatusExistsException;
import omis.supervision.exception.CorrectionalStatusTermExistsException;
import omis.supervision.exception.PlacementTermChangeReasonExistsException;
import omis.supervision.exception.PlacementTermExistsException;
import omis.supervision.exception.SupervisoryOrganizationExistsException;
import omis.supervision.exception.SupervisoryOrganizationTermExistsException;
import omis.supervision.service.delegate.CorrectionalStatusDelegate;
import omis.supervision.service.delegate.CorrectionalStatusTermDelegate;
import omis.supervision.service.delegate.PlacementTermChangeReasonDelegate;
import omis.supervision.service.delegate.PlacementTermDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationDelegate;
import omis.supervision.service.delegate.SupervisoryOrganizationTermDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.DateRangeUtility;
import omis.util.DateUtility;

/**
 * Tests look up of allowed programs for offender on changing of correctional
 * status.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 20, 2018)
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class ChangeCorrectionalStatusServiceFindAllowedProgramsForOffenderTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Service. */
	
	@Autowired
	@Qualifier("changeCorrectionalStatusService")
	private ChangeCorrectionalStatusService changeCorrectionalStatusService;
	
	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusDelegate")
	private CorrectionalStatusDelegate correctionalStatusDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationDelegate")
	private SupervisoryOrganizationDelegate supervisoryOrganizationDelegate;
	
	@Autowired
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate
	supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("placementTermChangeReasonDelegate")
	private PlacementTermChangeReasonDelegate placementTermChangeReasonDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("zipCodeDelegate")
	private ZipCodeDelegate zipCodeDelegate;
	
	@Autowired
	@Qualifier("addressDelegate")
	private AddressDelegate addressDelegate;
	
	@Autowired
	@Qualifier("locationDelegate")
	private LocationDelegate locationDelegate;
	
	@Autowired
	@Qualifier("locationTermDelegate")
	private LocationTermDelegate locationTermDelegate;
	
	@Autowired
	@Qualifier("programDelegate")
	private ProgramDelegate programDelegate;
	
	@Autowired
	@Qualifier("programLocationOfferDelegate")
	private ProgramLocationOfferDelegate programLocationOfferDelegate;
	
	@Autowired
	@Qualifier("programSupervisoryOrganizationOfferDelegate")
	private ProgramSupervisoryOrganizationOfferDelegate
	programSupervisoryOrganizationOfferDelegate;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("dateUtility")
	private DateUtility dateUtility;

	@Autowired
	@Qualifier("dateRangeUtility")
	private DateRangeUtility dateRangeUtility;
	
	/* Test cases. */
	
	/**
	 * Tests that programs offered by location are returned when offender is
	 * on a status that requires a location and located at a location.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists 
	 * @throws CountryExistsException if country exists 
	 * @throws StateExistsException if State exists
	 * @throws CityExistsException if city exists
	 * @throws ZipCodeExistsException if ZIP code exists
	 * @throws LocationExistsException if location exists
	 * @throws LocationTermExistsException if location term exists
	 * @throws ProgramExistsException if program exists
	 * @throws ProgramLocationOfferExistsException if program is already
	 * offered at location
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 */
	public void testWhenLocationRequiredAndLocated()
			throws CorrectionalStatusExistsException,
				SupervisoryOrganizationExistsException,
				SupervisoryOrganizationTermExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				CountryExistsException,
				StateExistsException,
				CityExistsException,
				ZipCodeExistsException,
				LocationExistsException,
				LocationTermExistsException,
				ProgramExistsException,
				ProgramLocationOfferExistsException,
				PlacementTermChangeReasonExistsException {
		
		// Places offender in alt-secure prerelease
		Offender offender = this.createOffender();
		DateRange dateRange = this.dateRangeUtility
				.parseDateTexts("12/12/2010", "12/12/2015");
		CorrectionalStatus altSecure = this.createAltSecure();
		SupervisoryOrganization preRelease = this.createPreRelease();
		this.createPlacementTerm(offender, dateRange, altSecure, preRelease,
				this.createNewSentenceReason());
		Country usa = this.countryDelegate.create(
				"United States", "US", true);
		State montana = this.stateDelegate.create(
				"Montana", "MT", usa, true, true);
		City helena = this.cityDelegate.create(
				"Helena", true, montana, usa);
		ZipCode mt59601 = this.zipCodeDelegate.create(
				helena, "59601", null, true);
		Address preReleaseAddress = this.addressDelegate.findOrCreate(
				"1000 1000ST #1000", null, null, null, mt59601);
		Location preReleaseLocation = this.locationDelegate
				.create(preRelease, dateRange, preReleaseAddress);
		this.locationTermDelegate.create(offender, preReleaseLocation,
				dateRange.getStartDate(), dateRange.getEndDate(), false, null);
		Program inmateWorker = this.programDelegate.create("Inmate Worker");
		this.programLocationOfferDelegate.create(
				inmateWorker, preReleaseLocation);
		
		// Action - finds offered programs for located offender
		List<Program> offeredPrograms
			= this.changeCorrectionalStatusService
				.findAllowedProgramsForOffenderOnDate(
						offender, dateRange.getStartDate());
		
		// Asserts that a single program is offered
		assert offeredPrograms.size() == 1
			: String.format(
					"Wrong number of programs offered - 1 expected; %d found",
					offeredPrograms.size());
		
		// Asserts that the single program offered is inmate worker
		assert offeredPrograms.get(0).equals(inmateWorker)
			: String.format("Wrong program offered - %s expected; %s found",
					inmateWorker, offeredPrograms.get(0));
	}
	
	/**
	 * Tests that programs offered by supervisory organization are returned
	 * when offender is on a status that requires a location but is not at
	 * a location.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists 
	 * @throws ProgramExistsException if program exists
	 * @throws ProgramSupervisoryOrganizationOfferExistsException if program
	 * is already offered by supervisory organization
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 */
	public void testWhenLocationRequiredAndNotLocated()
			throws CorrectionalStatusExistsException,
				SupervisoryOrganizationExistsException,
				SupervisoryOrganizationTermExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				ProgramExistsException,
				ProgramSupervisoryOrganizationOfferExistsException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender on alt-secure status at prerelease
		Offender offender = this.createOffender();
		CorrectionalStatus altSecure = this.createAltSecure();
		SupervisoryOrganization preRelease = this.createPreRelease();
		DateRange dateRange = this.dateRangeUtility
				.parseDateTexts("12/12/2012", "12/12/2014");
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		this.createPlacementTerm(offender, dateRange, altSecure, preRelease,
				newSentenceReason);
		
		// Action - allows inmate worker at pre-release
		Program inmateWorker = this.programDelegate.create("Inmate Worker");
		this.programSupervisoryOrganizationOfferDelegate.create(
				inmateWorker, preRelease);
		List<Program> offeredPrograms = this.changeCorrectionalStatusService
				.findAllowedProgramsForOffenderOnDate(offender,
					dateRange.getStartDate());
		
		// Asserts that a single program is offered
		assert offeredPrograms.size() == 1
				: String.format(
					"Wrong number of programs offered: 1 expected; %d found",
					offeredPrograms.size());
		
		// Asserts that ETP is offered
		assert offeredPrograms.get(0).equals(inmateWorker)
			: String.format("Wrong program offered - %s expected; %s found",
					inmateWorker, offeredPrograms.get(0));
	}
	
	/**
	 * Tests that programs offered by supervisory organization are returned
	 * when offender is on a status that does not require a location.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists 
	 * @throws ProgramExistsException if program exists
	 * @throws ProgramSupervisoryOrganizationOfferExistsException if program
	 * is already offered by supervisory organization
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	public void testWhenLocationNotRequired()
			throws CorrectionalStatusExistsException,
				SupervisoryOrganizationExistsException,
				SupervisoryOrganizationTermExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				ProgramExistsException,
				ProgramSupervisoryOrganizationOfferExistsException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender on probation at P&P office
		Offender offender = this.createOffender();
		CorrectionalStatus probation = this.createProbation();
		SupervisoryOrganization pnpOffice = this.createPnPOffice();
		PlacementTermChangeReason newSentenceReason
			= this.createNewSentenceReason();
		DateRange dateRange = this.dateRangeUtility
				.parseDateTexts("12/12/2012", "12/12/2014");
		this.createPlacementTerm(offender, dateRange, probation, pnpOffice,
				newSentenceReason);
		
		// Action - allows ETP at supervisory organization
		Program etp = this.programDelegate.create("ETP");
		this.programSupervisoryOrganizationOfferDelegate.create(etp, pnpOffice);
		List<Program> offeredPrograms = this.changeCorrectionalStatusService
				.findAllowedProgramsForOffenderOnDate(offender,
					dateRange.getStartDate());
		
		// Asserts that a single program is offered
		assert offeredPrograms.size() == 1
				: String.format(
					"Wrong number of programs offered: 1 expected; %d found",
					offeredPrograms.size());
		
		// Asserts that ETP is offered
		assert offeredPrograms.get(0).equals(etp)
			: String.format("Wrong program offered - %s expected; %s found",
					etp, offeredPrograms.get(0));
	}
	
	/**
	 * Tests that lookup of allowed programs while not placed returns and empty
	 * collection.
	 */
	public void testWhenNotPlaced() {
		assert this.changeCorrectionalStatusService
				.findAllowedProgramsForOffenderOnDate(
						this.createOffender(),
						this.dateUtility.parseDateText("12/12/2012"))
					.size() == 0
		: "No programs should be allowed for offender not under supervision";
	}
	
	/* Helper methods. */
	
	// Returns offender
	private Offender createOffender() {
		return this.offenderDelegate.createWithoutIdentity(
				"La Chiffre", "Franz", null, null);
	}
	
	// Returns probation status
	private CorrectionalStatus createProbation()
			throws CorrectionalStatusExistsException {
		return this.correctionalStatusDelegate
				.create("Probation", "PROB", false, (short) 1, true);
	}
	
	// Returns alt-secure status
	private CorrectionalStatus createAltSecure()
			throws CorrectionalStatusExistsException {
		return this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 1, true);
	}
	
	// Returns P&P office
	private SupervisoryOrganization createPnPOffice()
			throws SupervisoryOrganizationExistsException {
		return this.supervisoryOrganizationDelegate
				.create("P&P Office", "P&P", null);
	}
	
	// Returns pre-release
	private SupervisoryOrganization createPreRelease()
			throws SupervisoryOrganizationExistsException {
		return this.supervisoryOrganizationDelegate
				.create("Prerelease", "Prerelease", null);
	}
	
	// Returns placement term
	private PlacementTerm createPlacementTerm(
			final Offender offender, final DateRange dateRange,
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization,
			final PlacementTermChangeReason startChangeReason)
				throws SupervisoryOrganizationTermExistsException,
					CorrectionalStatusTermExistsException,
					PlacementTermExistsException {
		return this.placementTermDelegate.create(offender, dateRange,
				this.supervisoryOrganizationTermDelegate
					.create(offender, dateRange, supervisoryOrganization),
				this.correctionalStatusTermDelegate
					.create(offender, dateRange, correctionalStatus),
				startChangeReason, null, false);
	}
	
	// Returns new sentence reason
	private PlacementTermChangeReason createNewSentenceReason()
			throws PlacementTermChangeReasonExistsException {
		return this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
	}
}