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

import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.CorrectionalStatusTerm;
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
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create placement terms.
 *
 * <p>Verifies documented side effects of invoking method to create placement
 * terms.
 * 
 * <p>See {@link PlacementTermService#create}.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"placement", "service"})
public class PlacementTermServiceCreateTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Property editor factory. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Service delegates. */
	
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
	@Qualifier("correctionalStatusTermDelegate")
	private CorrectionalStatusTermDelegate correctionalStatusTermDelegate;
	
	@Autowired
	@Qualifier("supervisoryOrganizationTermDelegate")
	private SupervisoryOrganizationTermDelegate
	supervisoryOrganizationTermDelegate;
	
	@Autowired
	@Qualifier("placementTermDelegate")
	private PlacementTermDelegate placementTermDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("placementTermService")
	private PlacementTermService placementTermService;
	
	/* Test methods. */
	
	/**
	 * Tests creation of placement term.
	 * 
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws DuplicateEntityFoundException if other entities exist
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if supervisory
	 * organization term conflicts
	 * @throws CorrectionalStatusTermConflictException if correctional status
	 * term conflicts 
	 */
	public void testCreation()
			throws PlacementTermExistsException,
				DuplicateEntityFoundException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - creates Blofeld, parole status and office and reasons
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", null, null);
		SupervisoryOrganization supervisoryOrganization 
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "PAROFF", null);
		CorrectionalStatus correctionalStatus
			= this.correctionalStatusDelegate.create(
					"Parole", "PAR", false, (short) 1, true);
		DateRange dateRange = new DateRange(
				this.parseDateText("03/15/2012"),
				this.parseDateText("03/21/2017"));
		PlacementTermChangeReason startChangeReason
			= this.placementTermChangeReasonDelegate
				.create("Start of Supervision", (short) 1, true, false);
		PlacementTermChangeReason endChangeReason
			= this.placementTermChangeReasonDelegate
				.create("End of Supervision", (short) 2, true, false);
		
		// Action - places Blofeld in on parole at a parole office
		PlacementTerm placementTerm = this.placementTermService
				.create(offender, supervisoryOrganization, correctionalStatus,
						dateRange, startChangeReason, endChangeReason);
		
		// Assertions - verifies that Blofeld is placed
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("startChangeReason", startChangeReason)
			.addExpectedValue("endChangeReason", endChangeReason)
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					correctionalStatus)
			.addExpectedValue("supervisoryOrganizationTerm"
						+ ".supervisoryOrganization",
					supervisoryOrganization)
			.performAssertions(placementTerm);
	}
	
	/**
	 * Tests creation without supervisory organization.
	 * 
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 */
	public void testCreationWithoutSupervisoryOrganization()
			throws PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException,
				DuplicateEntityFoundException {
		
		// Arrangements - creates Blofeld and correctional status
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		
		// Action - places Blofeld on secure status without a supervisory
		// organization
		PlacementTerm placementTerm = this.placementTermService
				.create(offender, null, secure, dateRange, null, null);
		
		// Asserts that Blofeld is secure without a supervisory organization
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					secure)
			.addExpectedValue("supervisoryOrganizationTerm", null)
			.performAssertions(placementTerm);
	}
	
	/**
	 * Tests that an existing placement term without an end date is ended with
	 * the start date of the new placement term.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 */
	public void testEndExistingPlacementTermWithoutEndDateOnCreation()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Kananga securely, at prison
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Kananga", "Julius", "Auric", null);
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate.create(
					offender, dateRange, secure);
		SupervisoryOrganization prison
			= this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, dateRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		PlacementTerm existingPlacementTerm
			= this.placementTermDelegate.create(
				offender, dateRange, prisonTerm, secureTerm, newSentenceReason,
				null, false);
		
		// Action - places offender on alt-secure status
		CorrectionalStatus altSecure = this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 1, true);
		SupervisoryOrganization preRelease
			= this.supervisoryOrganizationDelegate
				.create("PreRelease", "PRE", null);
		DateRange altDateRange = new DateRange(
				this.parseDateText("12/12/2014"), null);
		this.placementTermService
			.create(offender, preRelease, altSecure, altDateRange, null, null);
		
		// Asserts that original secure term is unaffected
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate", dateRange.getStartDate())
			.addExpectedValue("dateRange.endDate", altDateRange.getStartDate())
			.addExpectedValue("offender", offender)
			.addExpectedValue(
					"supervisoryOrganizationTerm.supervisoryOrganization",
					prison)
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					secure)
			.performAssertions(existingPlacementTerm);
	}
	
	/**
	 * Tests that an existing placement term without an end date is ended with
	 * the start date of the new placement term when the new placement term
	 * is end dated without detecting a conflict with the existing placement
	 * term.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 */
	public void
	testEndExistingPlacementTermWithoutEndDateWithClosedPlacementOnCreation()
			throws CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender securely
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Kananga", "Julius", null, "XX");
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		this.placementTermDelegate.create(offender, secureRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, secureRange,
						this.correctionalStatusDelegate.create(
								"Secure", "SEC", true, (short) 1, true)),
				this.placementTermChangeReasonDelegate.create(
						"Initial Sentence", (short) 1, true, false),
				null, false);
		
		// Action - paroles offender for remaining two years of sentence
		DateRange paroleRange = new DateRange(
				this.parseDateText("12/12/2015"),
				this.parseDateText("12/12/2017"));
		this.placementTermService.create(offender, null,
				this.correctionalStatusDelegate.create(
						"Parole", "PAR", false, (short) 2, true),
				paroleRange,
				this.placementTermChangeReasonDelegate.create(
						"Board Decision", (short) 2, true, true),
				this.placementTermChangeReasonDelegate.create(
						"End of Sentence", (short) 3, false, true));
		
		// Asserts that end date of existing placement term is start date of
		// new one
		PlacementTerm existingPlacementTerm = this.placementTermDelegate
				.findForOffenderOnDate(offender, secureRange.getStartDate());
		assert paroleRange.getStartDate().equals(
				existingPlacementTerm.getDateRange().getEndDate())
			: String.format(
					"Wrong updated start date of existing placement term"
							+ " - %s expected; %s found",
					paroleRange.getStartDate(),
					existingPlacementTerm.getDateRange().getEndDate());
	}
	
	/**
	 * Tests that an existing placement term with an end date has its end date
	 * adjusted to that start date of the new placement term when the end date
	 * is after the start date.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 */
	public void testEndExistingPlacementTermWithEndDateOnCreation()
				throws DuplicateEntityFoundException,
					PlacementTermExistsException,
					CorrectionalStatusTermConflictException,
					SupervisoryOrganizationTermConflictException,
					PlacementTermConflictException {
		
		// Arrangements - places Le Chiffre securely, at prison
		Offender offender = this.offenderDelegate
				.createWithoutIdentity(
						"Le Chiffre", "Julius", "Fransisco", null);
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2015"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate.create(
					offender, dateRange, secure);
		SupervisoryOrganization prison
			= this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, dateRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		PlacementTerm existingPlacementTerm
			= this.placementTermDelegate.create(
				offender, dateRange, prisonTerm, secureTerm, newSentenceReason,
				null, false);
		
		// Action - places offender on alt-secure status
		CorrectionalStatus altSecure = this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 1, true);
		SupervisoryOrganization preRelease
			= this.supervisoryOrganizationDelegate
				.create("PreRelease", "PRE", null);
		DateRange altDateRange = new DateRange(
				this.parseDateText("12/12/2014"), null);
		this.placementTermService
			.create(offender, preRelease, altSecure, altDateRange, null, null);
		
		// Asserts that original secure term is unaffected
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate", dateRange.getStartDate())
			.addExpectedValue("dateRange.endDate", altDateRange.getStartDate())
			.addExpectedValue("offender", offender)
			.addExpectedValue(
					"supervisoryOrganizationTerm.supervisoryOrganization",
					prison)
			.addExpectedValue("correctionalStatusTerm.correctionalStatus",
					secure)
			.performAssertions(existingPlacementTerm);
	}
	
	/**
	 * Tests that when a correctional status term with a matching correctional
	 * status and date range exists, it is used for the placement term.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	public void testMatchingCorrectionalStatusTermIsUsed()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - puts Blofeld on parole status but don't place him
		// Creates parole office
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange dateRange = new DateRange(
				this.parseDateText("05/21/2012"),
				this.parseDateText("05/21/2017"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		CorrectionalStatusTerm paroleTerm
			= this.correctionalStatusTermDelegate
				.create(blofeld, dateRange, parole);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "POFF", null);
		
		// Action - place Blofeld on parole at the parole office
		PlacementTerm placementTerm = this.placementTermService
				.create(blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that matching correctional status term was used
		assert placementTerm.getCorrectionalStatusTerm().equals(paroleTerm)
			: "Matching correctional status term not used";
	}
	
	/**
	 * Tests that when a supervisory organization term with a matching
	 * supervisory organization and date range exists, its used for the
	 * placement term.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	public void testMatchingSupervisoryOrganizationTermIsUsed()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld at parole office but does not put him
		// on parole status
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange dateRange = new DateRange(
				this.parseDateText("12/31/2008"),
				this.parseDateText("12/31/2018"));
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "POFF", null);
		SupervisoryOrganizationTerm paroleOfficeTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, dateRange, paroleOffice);
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		
		// Action - places offender on parole at parole office
		PlacementTerm placementTerm = this.placementTermService
				.create(blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that matching supervisory organization term was used
		assert placementTerm.getSupervisoryOrganizationTerm()
			.equals(paroleOfficeTerm)
			: "Matching supervisory organization term not used";
	}
	
	/**
	 * Tests that overlapping supervisory organization term is left unchanged
	 * when supervisory organization is not specified.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	public void
	testCreationWithoutSupervisoryOrganizationLeavesExistingUnchanged()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Le Chiffre securely, at prison
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Le Chiffre", "Julius", "Julius", null);
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate.create(
					offender, dateRange, secure);
		SupervisoryOrganization prison
			= this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, dateRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
				offender, dateRange, prisonTerm, secureTerm, newSentenceReason,
				null, false);
		
		// Action - places offender on alt-secure status
		CorrectionalStatus altSecure = this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 1, true);
		DateRange altDateRange = new DateRange(
				this.parseDateText("12/12/2014"), null);
		this.placementTermService
			.create(offender, null, altSecure, altDateRange, null, null);
		
		// Asserts that original secure term is unaffected
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("offender", offender)
			.addExpectedValue("supervisoryOrganization", prison)
			.performAssertions(prisonTerm);
	}
	
	/**
	 * Tests that attempts to create duplicate placement terms are prevented
	 * when supervisory organization is {@code null}.
	 *  
	 * @throws PlacementTermExistsException if placement term exists - asserted
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional statuses exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organizations exist
	 * @throws PlacementTermConflictException if conflicting placement
	 * terms exist
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 */
	// Disabled as current implementation ends existing placement term of
	// which new one is a duplicate. This should be resolved by dropping the
	// correctional status and supervisory organization terms from the business
	// key of placement term and performing the check before existing placement
	// terms are adjusted.
	@Test(enabled = false,
			expectedExceptions = {PlacementTermExistsException.class})
	public void
	testPlacementTermExistsExceptionWithoutSupervisoryOrganization()
			throws PlacementTermExistsException,
			CorrectionalStatusTermConflictException,
			SupervisoryOrganizationTermConflictException,
			PlacementTermConflictException,
			DuplicateEntityFoundException {
	
		// Arrangements - creates Blofeld, places securely
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange dateRange = new DateRange(
				this.parseDateText("12/12/2012"), null);
		CorrectionalStatus secureStatus
			= this.correctionalStatusDelegate.create(
					"Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate
				.create(offender, dateRange, secureStatus);
		this.placementTermDelegate.create(offender, dateRange, null,
				secureTerm, null, null, false);
		
		// Action - attempts to create duplicate placement term
		this.placementTermService
			.create(offender, null, secureStatus, dateRange, null, null);
	}
	
	/**
	 * Tests that an existing correctional status term with a matching
	 * correctional status occuring earlier but finishing during a placement
	 * term is lengthened and used for placement.
	 * 
	 * <p>Start date must remain the same.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	public void testEarlierMatchingCorrectionalStatusTermIsLengthened()
				throws DuplicateEntityFoundException,
					PlacementTermExistsException,
					CorrectionalStatusTermConflictException,
					SupervisoryOrganizationTermConflictException,
					PlacementTermConflictException {
		
		// Arrangements - places Blofled on parole correctional status but
		// not at parole office
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange statusRange = new DateRange(
				this.parseDateText("12/21/2001"),
				this.parseDateText("12/21/2011"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(blofeld, statusRange, parole);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "POFF", null);
		
		// Action - places Blofeld at parole office
		DateRange dateRange = new DateRange(
				this.parseDateText("12/31/2005"),
				this.parseDateText("12/31/2015"));
		this.placementTermService.create(
				blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that start date is unaffected but end date is lengthened
		// of correctional status term
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate",
					DateRange.getStartDate(statusRange))
			.addExpectedValue("dateRange.endDate",
					DateRange.getEndDate(dateRange))
			.performAssertions(paroleTerm);
	}
	
	/**
	 * Tests that an existing correctional status term with a matching
	 * correctional status lasting later but starting during a placement
	 * term is lengthened and used for placement.
	 * 
	 * <p>End date must remain the same.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	public void testLaterMatchingCorretionalStatusTermIsLengthened()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld on correctional status but not at
		// parole office
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange statusRange = new DateRange(
				this.parseDateText("12/21/2001"),
				this.parseDateText("12/21/2011"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(blofeld, statusRange, parole);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "POFF", null);
		
		// Action - places Blofeld at parole office
		DateRange dateRange = new DateRange(
				this.parseDateText("12/21/1996"),
				this.parseDateText("12/21/2006"));
		this.placementTermService.create(
				blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that start date is lengthened of correctional status term
		// and that end date is unaffected
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate",
					DateRange.getStartDate(dateRange))
			.addExpectedValue("dateRange.endDate",
					DateRange.getEndDate(statusRange))
			.performAssertions(paroleTerm);
	}
	
	/**
	 * Tests that an existing supervisory organization term with a matching
	 * supervisory organization occuring earlier but finishing during a
	 * placement term is lengthened and used for placement.
	 * 
	 * <p>Start date must remain the same.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist 
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists 
	 * @throws PlacementTermExistsException if placement term exists
	 */
	public void testEarlierMatchingSupervisoryOrganizationTermIsLengthened()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld at a parole office but not on parole
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange paroleOfficeRange = new DateRange(
				this.parseDateText("12/21/2001"),
				this.parseDateText("12/21/2011"));
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "POFF", null);
		SupervisoryOrganizationTerm paroleOfficeTerm
			= this.supervisoryOrganizationTermDelegate.create(
					blofeld, paroleOfficeRange, paroleOffice);
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		
		// Action - places offender on parole
		DateRange dateRange = new DateRange(
				this.parseDateText("12/31/2005"),
				this.parseDateText("12/31/2015"));
		this.placementTermService.create(
				blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that start date is unaffected but end date is lengthened
		// of supervisory organization term
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate",
					DateRange.getStartDate(paroleOfficeRange))
			.addExpectedValue("dateRange.endDate",
					DateRange.getEndDate(dateRange))
			.performAssertions(paroleOfficeTerm);
	}
	
	/**
	 * Tests that an existing supervisory organization term with a matching
	 * supervisory organization lasting later but starting during a placement
	 * term is lengthened and used for placement.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement
	 * terms exist
	 */
	public void testLaterMatchingSupervisoryOrganizationTermIsLengthened()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld at parole office but not on
		// correctional status
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange officeRange = new DateRange(
				this.parseDateText("12/21/2001"),
				this.parseDateText("12/21/2011"));
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "PAR", null);
		SupervisoryOrganizationTerm paroleOfficeTerm
			= this.supervisoryOrganizationTermDelegate.create(
					blofeld, officeRange, paroleOffice);
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		
		// Action - places Blofeld
		DateRange dateRange = new DateRange(
				this.parseDateText("12/21/1996"),
				this.parseDateText("12/21/2006"));
		this.placementTermService.create(
				blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that start date is lengthened of supervisory organization
		// term and end date is unaffected
		PropertyValueAsserter.create()
			.addExpectedValue("dateRange.startDate",
					DateRange.getStartDate(dateRange))
			.addExpectedValue("dateRange.endDate",
					DateRange.getEndDate(officeRange))
			.performAssertions(paroleOfficeTerm);
	}
	
	/**
	 * Tests that an earlier correctional status term ending
	 * during the placement term but with a mismatching correctional status
	 * is ended and a new correctional status term began for use by the
	 * placement term.
	 *  
	 * @throws DuplicateEntityFoundException if entities exist 
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists 
	 * @throws PlacementTermExistsException if placement term exists
	 */
	public void testEarlierMismatchingCorrectionalStatusTermIsEnded()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - secures Blofeld
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange secureRange = new DateRange(
				this.parseDateText("06/30/2005"),
				this.parseDateText("06/30/2015"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, secureRange, secure);
		
		// Action - places Blofeld on parole during secure term
		DateRange dateRange = new DateRange(
				this.parseDateText("06/30/2010"),
				this.parseDateText("06/30/2020"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "PAOFF", null);
		PlacementTerm placementTerm = this.placementTermService.create(
				offender, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that a different correctional status term to secure is
		// used
		assert !placementTerm.getCorrectionalStatusTerm().equals(secureTerm)
			: "Term with mismatching correctional status term is used";

		// Asserts that original secure term was ended on start date of
		// placement term
		assert DateRange.getEndDate(secureTerm.getDateRange())
				.equals(DateRange.getStartDate(dateRange))
			: "Incorrect end date of original, mismatching status term";
	}
	
	/**
	 * Tests that a later correctional status term starting during the
	 * placement term but with a mismatching correctional status
	 * is adjusted to start when the placement term is finished and that
	 * a new correctional status term is used by the placement term.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 */
	public void testLaterMatchingCorrectionalStatusTermIsStartedLater()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - secures Blofeld
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange secureRange = new DateRange(
				this.parseDateText("12/21/2010"),
				this.parseDateText("12/21/2020"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate
				.create(blofeld, secureRange, secure);
		
		// Action - places Blofeld on Parole during secure term
		DateRange dateRange = new DateRange(
				this.parseDateText("12/21/2005"),
				this.parseDateText("12/21/2015"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "PAROFF", null);
		PlacementTerm placementTerm = this.placementTermService
			.create(blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that different correctional status term to secure is used
		assert !placementTerm.getCorrectionalStatusTerm().equals(secureTerm)
			: "Term with mismatching correctional status term is used";
		
		// Asserts that original secure term start date was adjusted to
		// end date of placement term
		assert DateRange.getStartDate(secureTerm.getDateRange())
			.equals(DateRange.getEndDate(dateRange))
			: "Incorrect start date of original, mismatching status term";
	}
	
	/**
	 * Tests that an earlier supervisory organization term ending
	 * during placement term but with a mismatching supervisory organization
	 * is ended and a new supervisory organization term began for use by the
	 * placement term
	 * 
	 * @throws DuplicateEntityFoundException if entities exist 
	 * @throws PlacementTermConflictException if placement term conflicts
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status exists 
	 * @throws PlacementTermExistsException if placement term exists
	 */
	public void testEarlierMismatchingSupervisoryOrganizationTermIsEnded()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - supervises Blofeld by prison
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange prisonRange = new DateRange(
				this.parseDateText("05/31/2000"),
				this.parseDateText("05/29/2010"));
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate.create(
					offender, prisonRange, prison);
		
		// Action - paroles Blofeld during secure term
		DateRange dateRange = new DateRange(
				this.parseDateText("03/21/2005"),
				this.parseDateText("12/30/2015"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "POF", null);
		PlacementTerm placementTerm = this.placementTermService
				.create(offender, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that a different supervisory organization was ended on start
		// date of placement term
		assert !placementTerm.getSupervisoryOrganizationTerm()
				.equals(prisonTerm)
			: "Term with mismatching supervisory organization term is used";
				
		// Asserts that original prison term was ended on start date of
		// placement term
		assert DateRange.getEndDate(prisonTerm.getDateRange()).equals(
				DateRange.getStartDate(dateRange))
			: "Incorrect end date of original, mismatching organization term";
	}
	
	/**
	 * Tests that a later supervisory organization term starting during the
	 * placement term but with a mismatching supervisory organization
	 * is adjusted to start when the placement term is finished and that a new
	 * supervisory organization term is used by the placement term. 
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entities exist
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement
	 * terms exist
	 */
	public void testLaterMatchingSupervisoryOrganizationTermIsStartedLater()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld at prison
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange secureRange = new DateRange(
				this.parseDateText("12/21/2010"),
				this.parseDateText("12/21/2020"));
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, secureRange, prison);
		
		// Action - places Blofeld on Parole during prison placement
		DateRange dateRange = new DateRange(
				this.parseDateText("12/21/2005"),
				this.parseDateText("12/21/2015"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", true, (short) 1, true);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "PROFF", null);
		PlacementTerm placementTerm = this.placementTermService.create(
				blofeld, paroleOffice, parole, dateRange, null, null);
		
		// Asserts that different supervisory organization term to prison
		// is used
		assert !placementTerm.getSupervisoryOrganizationTerm()
				.equals(prisonTerm)
			: "Term with mismatching supervisory organization is used";
				
		// Asserts that original prison term start date was adjusted to
		// end date of placement term
		assert DateRange.getStartDate(prisonTerm.getDateRange())
				.equals(DateRange.getEndDate(dateRange))
			: "Incorrect start date of original, mismatching organization term";
	}
	
	/**
	 * Tests that {@code PlacementTermConflictException} is thrown when a
	 * conflicting placement term exists entirely within created placement term.
	 * The start date of the new placement term is before the existing while
	 * the end date is after, respectively.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist 
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists - asserted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflict()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - secures Kananga at prison
		Offender offender = offenderDelegate.createWithoutIdentity(
				"Kananga", "Auric", null, null);
		DateRange prisonRange = new DateRange(
				this.parseDateText("07/23/2005"),
				this.parseDateText("06/21/2015"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, prisonRange, secure);
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate.create(
					offender, prisonRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(offender, prisonRange, prisonTerm,
				secureTerm, newSentenceReason, null, false);
		
		// Action - places Kananga on parole
		DateRange dateRange = new DateRange(
				this.parseDateText("12/21/2000"),
				this.parseDateText("01/12/2020"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate
				.create("Parole Office", "POF", null);
		this.placementTermService.create(
				offender, paroleOffice, parole, dateRange, null, null);
	}
	
	/**
	 * Tests that {@code PlacementTermConflictException} is thrown when a
	 * conflicting placement term exists entirely within created placement term.
	 * 
	 * <p>A placement term exists that overlaps the start date, this should be
	 * adjust to not conflict with the created placement term. The conflict
	 * should occur due to another existing placement within the date range
	 * of the created one.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist 
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists - asserted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictWithStartOverlap()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld in prison
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, null);
		DateRange prisonRange = new DateRange(
				this.parseDateText("06/30/2000"),
				this.parseDateText("06/30/2010"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate
				.create(blofeld, prisonRange, secure);
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, prisonRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
			blofeld, prisonRange, prisonTerm, secureTerm, newSentenceReason,
			null, false);
		
		// More arrangements - place Blofeld on parole
		DateRange paroleRange = new DateRange(
				this.parseDateText("06/30/2010"),
				this.parseDateText("06/30/2015"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 2, true);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(blofeld, paroleRange, parole);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "POFF", null);
		SupervisoryOrganizationTerm paroleOfficeTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, paroleRange, paroleOffice);
		PlacementTermChangeReason boppDecisionReason
			= this.placementTermChangeReasonDelegate
				.create("BOPP Decision", (short) 1, true, true);
		this.placementTermDelegate.create(
				blofeld, paroleRange, paroleOfficeTerm, paroleTerm,
				boppDecisionReason, null, false);
		
		// Action - places Blofeld on alt placement during prison term and
		// ending after parole placement
		DateRange altRange = new DateRange(
				this.parseDateText("06/31/2005"),
				this.parseDateText("06/31/2020"));
		CorrectionalStatus alt = this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 3, true);
		SupervisoryOrganization prerelease
			= this.supervisoryOrganizationDelegate
				.create("Prerelease", "PRER", null);
		this.placementTermService.create(
				blofeld, prerelease, alt, altRange, boppDecisionReason, null);
	}
	
	/**
	 * Tests that {@code PlacementTermConflictException} is thrown when a
	 * conflicting placement term exists entirely within created placement term.
	 * 
	 * <p>A placement term exists that overlaps the end date, this should be
	 * adjust to not conflict with the created placement term. The conflict
	 * should occur due to another existing placement within the date range
	 * of the created one.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist 
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists - asserted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictWithEndOverlap()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Blofeld in prison
		Offender blofeld = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", "Fransisco", null);
		DateRange prisonRange = new DateRange(
				this.parseDateText("06/31/2010"),
				this.parseDateText("06/31/2015"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate
				.create(blofeld, prisonRange, secure);
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, prisonRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
			blofeld, prisonRange, prisonTerm, secureTerm, newSentenceReason,
			null, false);
		
		// More arrangements - place Blofeld on parole
		DateRange paroleRange = new DateRange(
				this.parseDateText("06/31/2015"),
				this.parseDateText("06/31/2025"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 2, true);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(blofeld, paroleRange, parole);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "POFF", null);
		SupervisoryOrganizationTerm paroleOfficeTerm
			= this.supervisoryOrganizationTermDelegate
				.create(blofeld, paroleRange, paroleOffice);
		PlacementTermChangeReason boppDecisionReason
			= this.placementTermChangeReasonDelegate
				.create("BOPP Decision", (short) 1, true, true);
		this.placementTermDelegate.create(
				blofeld, paroleRange, paroleOfficeTerm, paroleTerm,
				boppDecisionReason, null, false);
		
		// Action - places Blofeld on alt placement during prison term and
		// ending after parole placement
		DateRange altRange = new DateRange(
				this.parseDateText("06/31/2005"),
				this.parseDateText("06/31/2020"));
		CorrectionalStatus alt = this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 3, true);
		SupervisoryOrganization prerelease
			= this.supervisoryOrganizationDelegate
				.create("Prerelease", "PRER", null);
		this.placementTermService.create(
				blofeld, prerelease, alt, altRange, null, null);
	}
	
	/**
	 * Tests that {@code PlacementTermConflictException} is thrown when a
	 * conflicting placement term exists entirely within created placement term.
	 * 
	 * <p>A placement term exists that overlaps the start date, another the end
	 * date, these should be adjust to not conflict with the created placement
	 * term. The conflict should occur due to another existing placement within
	 * the date range of the created one.
	 * 
	 * @throws DuplicateEntityFoundException if entities exist 
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists - asserted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictWithStartAndEndOverlap()
			throws DuplicateEntityFoundException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arrangements - places Le Chiffre in prison
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Le Chiffre", "Julius", null, null);
		DateRange prisonRange = new DateRange(
				this.parseDateText("06/30/2000"),
				this.parseDateText("06/30/2010"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate
				.create(offender, prisonRange, secure);
		SupervisoryOrganization prison = this.supervisoryOrganizationDelegate
				.create("Prison", "PRSN", null);
		SupervisoryOrganizationTerm prisonTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, prisonRange, prison);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
			offender, prisonRange, prisonTerm, secureTerm, newSentenceReason,
			null, false);
		
		// More arrangements - place Le Chiffre on parole
		DateRange paroleRange = new DateRange(
				this.parseDateText("06/30/2010"),
				this.parseDateText("06/30/2015"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 2, true);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(offender, paroleRange, parole);
		SupervisoryOrganization paroleOffice
			= this.supervisoryOrganizationDelegate.create(
					"Parole Office", "PAFF", null);
		SupervisoryOrganizationTerm paroleOfficeTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, paroleRange, paroleOffice);
		PlacementTermChangeReason boppDecisionReason
			= this.placementTermChangeReasonDelegate
				.create("BOPP Decision", (short) 1, true, true);
		this.placementTermDelegate.create(
				offender, paroleRange, paroleOfficeTerm, paroleTerm,
				boppDecisionReason, null, false);
		
		// More arrangements - place Le Chiffre on probation
		DateRange probationRange = new DateRange(
				this.parseDateText("06/30/2015"),
				this.parseDateText("06/30/2025"));
		CorrectionalStatus probation = this.correctionalStatusDelegate
				.create("Probation", "PRO", false, (short) 3, true);
		CorrectionalStatusTerm probationTerm
			= this.correctionalStatusTermDelegate
				.create(offender, probationRange, probation);
		SupervisoryOrganization probationOffice
			= this.supervisoryOrganizationDelegate.create(
					"Probation Office", "PRFF", null);
		SupervisoryOrganizationTerm probationOfficeTerm
			= this.supervisoryOrganizationTermDelegate
				.create(offender, probationRange, probationOffice);
		this.placementTermDelegate.create(
				offender, probationRange, probationOfficeTerm, probationTerm,
				boppDecisionReason, null, false);
		
		// Action - places Le Chiffre on alt placement during prison term and
		// ending after parole placement
		DateRange altRange = new DateRange(
				this.parseDateText("06/30/2005"),
				this.parseDateText("06/30/2020"));
		CorrectionalStatus alt = this.correctionalStatusDelegate
				.create("Alt-Secure", "ALT", true, (short) 4, true);
		SupervisoryOrganization prerelease
			= this.supervisoryOrganizationDelegate
				.create("Prerelease", "PRER", null);
		this.placementTermService.create(
				offender, prerelease, alt, altRange, null, null);
	}
	
	/**
	 * Tests that future placement terms conflict with new placement term
	 * with {@code null} end date.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist - asserted
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictExistingAfterWithNullEndDate()
			throws CorrectionalStatusExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arrangements - placed Blofeld on parole
		Offender blofeld = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", "Auric", null);
		DateRange paroleRange = new DateRange(
				this.parseDateText("02/02/2002"),
				this.parseDateText("04/04/2004"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		CorrectionalStatusTerm paroleTerm = this.correctionalStatusTermDelegate
				.create(blofeld, paroleRange, parole);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
				blofeld, paroleRange, null, paroleTerm, newSentenceReason,
				null, false);
		
		// Action - places Blofeld securely with a null end date before the
		// existing parole placement
		DateRange secureRange = new DateRange(
				this.parseDateText("01/01/2001"), null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		this.placementTermService.create(blofeld, null, secure, secureRange,
				null, null);
	}
	
	/**
	 * Tests that an attempt to create a placement term that conflicts with a
	 * future, unended, placement term is prevented with a
	 * {@code PlacementTermConflictException}.
	 * 
	 * @param PlacementTermConflictException if conflicting placement terms
	 * exist - asserted
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusExistsException if correctional status
	 * exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermConflictException if supervisory
	 * organization term exists
	 * @throws CorrectionalStatusTermConflictException if correctional status
	 * term exists
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictExceptionIfUnendedFutureTermExists()
			throws PlacementTermConflictException,
				PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException {
		
		// Arranges Blofeld on parole in the future
		Offender blofeld = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Felix", null, "XI");
		DateRange paroleRange = new DateRange(
				this.parseDateText("12/12/2018"), null);
		this.placementTermDelegate.create(blofeld, paroleRange, null,
				this.correctionalStatusTermDelegate.create(
						blofeld, paroleRange,
						this.correctionalStatusDelegate.create(
								"Parole", "PAR", false, (short) 1, true)),
				this.placementTermChangeReasonDelegate
					.create("Board Decision", (short) 1, true, true),
				null, false);
		
		// Action - attempts to place Blofeld securely ending after the start
		// of the parole term
		this.placementTermService.create(blofeld, null,
				this.correctionalStatusDelegate.create(
						"Secure", "SEC", true, (short) 1, true),
				new DateRange(
						this.parseDateText("12/12/2015"),
						this.parseDateText("12/12/2019")),
				this.placementTermChangeReasonDelegate.create(
					"Initial Placement", (short) 2, true, false),
				null);
	}
	
	/**
	 * Tests that an attempt to create a placement term that conflicts with a
	 * future, ended, placement term is prevented with a
	 * {@code PlacementTermExistsException}.
	 * 
	 * @param PlacementTermConflictException if conflicting placement terms
	 * exist - asserted
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusExistsException if correctional status
	 * exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws SupervisoryOrganizationTermConflictException if supervisory
	 * organization term exists
	 * @throws CorrectionalStatusTermConflictException if correctional status
	 * term exists
	 */
	@Test(expectedExceptions = {PlacementTermConflictException.class})
	public void testPlacementTermConflictExceptionIfEndedFutureTermExists()
			throws PlacementTermConflictException,
				PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException {
		
		// Arranges Blofeld on parole in the future
		Offender blofeld = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Felix", null, "XI");
		DateRange paroleRange = new DateRange(
				this.parseDateText("12/12/2018"),
				this.parseDateText("12/12/2020"));
		this.placementTermDelegate.create(blofeld, paroleRange, null,
				this.correctionalStatusTermDelegate.create(
						blofeld, paroleRange,
						this.correctionalStatusDelegate.create(
								"Parole", "PAR", false, (short) 1, true)),
				this.placementTermChangeReasonDelegate
					.create("Board Decision", (short) 1, true, true),
				null, false);
		
		// Action - attempts to place Blofeld securely ending after the start
		// of the parole term
		this.placementTermService.create(blofeld, null,
				this.correctionalStatusDelegate.create(
						"Secure", "SEC", true, (short) 1, true),
				new DateRange(
						this.parseDateText("12/12/2015"),
						this.parseDateText("12/12/2019")),
				this.placementTermChangeReasonDelegate.create(
					"Initial Placement", (short) 2, true, false),
				null);
	}
	
	/**
	 * Tests that past placement terms conflict with new placement term with
	 * {@code null} start date.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist - asserted
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists
	 */
	// Disabled as start date cannot be null - remove this test case
	@Test(expectedExceptions = {PlacementTermConflictException.class},
			enabled = false)
	public void testPlacementTermConflictExistingBeforeWithNullStartDate()
				throws CorrectionalStatusExistsException,
					CorrectionalStatusTermExistsException,
					PlacementTermExistsException,
					CorrectionalStatusTermConflictException,
					SupervisoryOrganizationTermConflictException,
					PlacementTermConflictException,
					PlacementTermChangeReasonExistsException {
		
		// Arrangements - places Blofeld securely
		Offender blofeld = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", "Fransisco", null);
		DateRange secureRange = new DateRange(
				this.parseDateText("01/01/2001"),
				this.parseDateText("02/02/2002"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate
				.create(blofeld, secureRange, secure);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
				blofeld, secureRange, null, secureTerm, newSentenceReason, null,
				false);
		
		// Action - places Blofeld on parole with a null start date after the
		// existing secure placement
		DateRange paroleRange = new DateRange(
				null, this.parseDateText("03/03/2003"));
		CorrectionalStatus parole = this.correctionalStatusDelegate
				.create("Parole", "PAR", false, (short) 1, true);
		this.placementTermService.create(blofeld, null, parole, paroleRange,
				null, null);
	}
	
	/**
	 * Tests that {@code PlacementTermConflictException} is thrown when supplied
	 * start date is {@code null} and existing placement term has a {@code null}
	 * end date.
	 * 
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists 
	 * @throws CorrectionalStatusExistsException if correctional status exists 
	 * @throws CorrectionalStatusTermExistsException  if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws PlacementTermConflictException  if conflicting placement terms
	 * exists - asserted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 */
	// Disabled as start date cannot be null - remove this test case
	@Test(expectedExceptions = {PlacementTermConflictException.class},
			enabled = false)
	public void
	testPlacementTermConflictExceptionExistingUnendedBeforeWithNullStartDate()
			throws PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges Blofeld securely and indefinitely
		Offender blofeld = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Victor", "Julius", "X");
		DateRange secureRange
			= new DateRange(this.parseDateText("12/12/2012"), null);
		this.placementTermDelegate.create(blofeld,
				secureRange, null,
				this.correctionalStatusTermDelegate.create(blofeld, secureRange,
						this.correctionalStatusDelegate.create(
								"Secure", "SEC", true, (short) 1, true)),
				this.placementTermChangeReasonDelegate.create(
						"Initial Sentence", (short) 1, true, false),
				null, false);
		
		// Action - attempts to place Blofeld on parole without a start date
		// which would conflict with the existing secure record
		this.placementTermService.create(blofeld, null,
				this.correctionalStatusDelegate.create(
						"Parole", "PAR", false, (short) 1, true),
				new DateRange(null, this.parseDateText("12/12/2015")),
				this.placementTermChangeReasonDelegate.create(
						"Board Decision", (short) 1, true, true), null);
	}
	
	/**
	 * Tests that {@code PlacementTermConflictException} is thrown when supplied
	 * end date is {@code null} and existing placement term has a {@code null}
	 * start date.
	 * 
	 * @throws PlacementTermChangeReasonExistsException if placement term change
	 * reason exists 
	 * @throws CorrectionalStatusExistsException if correctional status exists 
	 * @throws CorrectionalStatusTermExistsException  if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists 
	 * @throws PlacementTermConflictException  if conflicting placement terms
	 * exists - asserted
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * 
	 */
	// Disabled as start date cannot be null - remove this test case
	@Test(expectedExceptions = {PlacementTermConflictException.class},
			enabled = false)
	public void
	testPlacementTermConflictExceptionsExistingUnstartedWithNullEndDate()
			throws PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges Blofeld on parole from the beginning of time
		Offender blofeld = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", null, "XIX");
		DateRange paroleRange
			= new DateRange(null, this.parseDateText("12/12/2012"));
		this.placementTermDelegate.create(blofeld, paroleRange, null,
				this.correctionalStatusTermDelegate.create(blofeld, paroleRange,
						this.correctionalStatusDelegate.create(
								"Parole", "PAR", false, (short) 1, true)),
				this.placementTermChangeReasonDelegate.create(
						"Board Decision", (short) 1, true, true), null, false);
		
		// Action - attempts to place Blofeld securely without a end date
		// which would conflict with the existing parole record
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2011"), null);
		this.placementTermService.create(blofeld, null,
				this.correctionalStatusDelegate.create(
						"Secure", "SEC", true, (short) 1, true), secureRange,
				this.placementTermChangeReasonDelegate.create(
						"Initial Sentence", (short) 1, true, false), null);
	}
	
	/**
	 * Test that when a correctional status term exists before and after the
	 * new placement term each with the same correctional status, the
	 * correctional status term is joined and used by the placement term.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists 
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	@Test(enabled = false)
	public void testJoinSameCorrectionalStatusTerms()
			throws CorrectionalStatusExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender securely at two different times
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		DateRange firstSecureRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2012"));
		this.correctionalStatusTermDelegate.create(
				offender, firstSecureRange, secure);
		DateRange secondSecureRange = new DateRange(
				this.parseDateText("12/12/2014"),
				this.parseDateText("12/12/2016"));
		this.correctionalStatusTermDelegate.create(
				offender, secondSecureRange, secure);
		
		// Action - creates placement term starting in between first term
		// and finishing in between second
		DateRange placementRange = new DateRange(
				this.parseDateText("12/12/2011"),
				this.parseDateText("12/12/2015"));
		PlacementTerm placementTerm = this.placementTermService
				.create(offender, null, secure, placementRange, null, null);
		
		// Asserts that correctional status term of new placement term starts at
		// first and finishes at second secure date range  
		PropertyValueAsserter.create()
			.addExpectedValue("correctionalStatusTerm.dateRange.startDate",
					DateRange.getStartDate(firstSecureRange))
			.addExpectedValue("correctionalStatusTerm.dateRange.endDate",
					DateRange.getEndDate(secondSecureRange))
			.performAssertions(placementTerm);
	}
	
	/**
	 * Tests that when a placement term is created within the range and with
	 * the same status of a containing correctional status term, the containing
	 * correctional status term is used by the placement term.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CorrectionalStatusTermExistsException if correctional status term
	 * exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exists
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exists
	 */
	public void testContainingCorrectionalStatusTermIsUsed()
			throws CorrectionalStatusExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender securely
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", null, null);
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2018"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm = this.correctionalStatusTermDelegate
				.create(offender, secureRange, secure);
		
		// Action - places offender securely for a shorter, contained term
		DateRange placementRange = new DateRange(
				this.parseDateText("12/12/2013"),
				this.parseDateText("12/12/2017"));
		PlacementTerm placementTerm = this.placementTermService
				.create(offender, null, secure, placementRange, null, null);
		
		// Asserts that existing correctional status is used
		PropertyValueAsserter.create()
			.addExpectedValue("correctionalStatusTerm", secureTerm)
			.performAssertions(placementTerm);
	}
	
	/**
	 * Tests that supervisory organization term with same supervisory
	 * organization that spans entirety of new placement term is used by new
	 * placement term.
	 * 
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status term exists
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization term exists
	 * @throws PlacementTermConflictException if conflicting placement term
	 * exists
	 */
	public void testContainingSupervisoryOrganizationTermIsUsed()
			throws SupervisoryOrganizationExistsException,
				SupervisoryOrganizationTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender with P & P office as supervisory organization
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Kananga", "Julius", null, null);
		DateRange pnpRange = new DateRange(
				this.parseDateText("12/12/2012"),
				this.parseDateText("12/12/2016"));
		SupervisoryOrganization pnpOffice
			= this.supervisoryOrganizationDelegate
					.create("P&P Office", "POFF", null);
		SupervisoryOrganizationTerm pnpOfficeTerm
			= this.supervisoryOrganizationTermDelegate.create(
					offender, pnpRange, pnpOffice);
		
		// Action - places offender at P&P office on probation for a shorter,
		// contained term - this would happen if offender ends parole and
		// begins suspended sentence at same P & P office
		CorrectionalStatus probation = this.correctionalStatusDelegate
				.create("Probation", "SEC", true, (short) 1, true);
		DateRange probationRange = new DateRange(
				this.parseDateText("12/12/2013"),
				this.parseDateText("12/12/2015"));
		PlacementTerm securePrisonPlacement
			= this.placementTermService.create(offender, pnpOffice, probation,
					probationRange,
					this.placementTermChangeReasonDelegate.create(
							"End of Prison Term", (short) 1, true, true),
					null);
		
		// Asserts that existing P&P office supervision organization term is
		// used
		PropertyValueAsserter.create()
			.addExpectedValue("supervisoryOrganizationTerm", pnpOfficeTerm)
			.performAssertions(securePrisonPlacement);
	}
	
	/**
	 * Tests that {@code IllegalArgumentException} is thrown on attempt to
	 * create a placement term where start date equals end date.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional statuses exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws IllegalArgumentException if start date is equal to end date
	 * - asserted
	 */
	@Test(expectedExceptions = {IllegalArgumentException.class})
	public void testIllegalArgumentExceptionIfStartDateEqualsEndDate()
			throws CorrectionalStatusExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender and correctional status
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		
		// Action attempt to create placement term where start date equals
		// end date - throws IllegalArgumentException
		this.placementTermService.create(offender, null, secure,
				new DateRange(
						this.parseDateText("12/12/2012"),
						this.parseDateText("12/12/2012")),
				null, null);
	}
	
	/**
	 * Tests that {@code UnsupportedOperationException} is thrown when an
	 * attempt is made to split placement terms.
	 * 
	 * @throws CorrectionalStatusExistsException if correctional status
	 * exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 */
	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void
	testUnsupportedOperationExceptionIfSplittingPlacementTermAttempted()
			throws CorrectionalStatusExistsException,
				CorrectionalStatusTermExistsException,
				PlacementTermExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException,
				PlacementTermChangeReasonExistsException {
		
		// Arranges offender securely
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Grant", "Donald", null, null);
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2013"));
		CorrectionalStatus secure = this.correctionalStatusDelegate
				.create("Secure", "SEC", true, (short) 1, true);
		CorrectionalStatusTerm secureTerm
			= this.correctionalStatusTermDelegate.create(
				offender, secureRange, secure);
		PlacementTermChangeReason newSentenceReason
			= this.placementTermChangeReasonDelegate
				.create("New Sentence", (short) 1, true, true);
		this.placementTermDelegate.create(
				offender, secureRange, null, secureTerm, newSentenceReason,
				null, false);
		
		// Action - attempts to place offender on probation during secure range
		CorrectionalStatus probation = this.correctionalStatusDelegate
				.create("Probation", "PROB", false, (short) 1, true);
		DateRange probationRange = new DateRange(
				this.parseDateText("12/12/2011"),
				this.parseDateText("12/12/2012"));
		PlacementTermChangeReason boppDecisionReason
			= this.placementTermChangeReasonDelegate
				.create("BOPP Decision", (short) 1, true, true);
		this.placementTermService.create(
				offender, null, probation, probationRange, boppDecisionReason,
				null);
	}
	
	/**
	 * Tests that attempts to split correctional status terms result in a
	 * {@code UnsupportedOperationException}.
	 * 
	 * <p>In order to make the attempt, the correctional status term must be
	 * longer than the original placement term.
	 * 
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist
	 */
	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void
	testUnsupportedOperationExceptionIfSplittingCorrectionalStatusTermAttempted()
			throws PlacementTermExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Places Blofeld securely with a correctional status term lasting
		// longer than the placement term
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Ernie", "Julius", "X");
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2018"),
				this.parseDateText("12/12/2019"));
		Date securePlacementEndDate = this.parseDateText("12/12/2021");
		this.placementTermDelegate.create(offender, secureRange, null,
				this.correctionalStatusTermDelegate.create(
						offender, DateRange.adjustEndDate(secureRange,
								securePlacementEndDate),
						this.correctionalStatusDelegate
							.create("Secure", "SEC", true, (short) 1, true)),
				this.placementTermChangeReasonDelegate.create(
						"Initial Placement", (short) 1, true, false),
				null, false);
		
		// Action - attempts to place offender on parole in the middle of the
		// correctional status term
		this.placementTermService.create(offender, null,
				this.correctionalStatusDelegate.create(
						"Parole", "PAR", true, (short) 1, true),
				new DateRange(
						secureRange.getEndDate(),
						this.parseDateText("12/12/2020")),
				this.placementTermChangeReasonDelegate.create(
						"Board Decision", (short) 1, true, true), null);
	}
	
	/**
	 * Tests that attempts to split supervisory organization term results in a
	 * {@code UnsupportedOperationException}.
	 * 
	 * <p>In order to make the attempt, the supervisory organization term must
	 * be longer than the original placement term.
	 * 
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reason exists
	 * @throws CorrectionalStatusExistsException if correctional status exists 
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists 
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization term exists 
	 * @throws PlacementTermExistsException if placemen term exists 
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * 
	 */
	@Test(expectedExceptions = {UnsupportedOperationException.class})
	public void
	testUnsupportedOperationExceptionIfSplittingSupervisoryOrganizationTermAttempted()
			throws PlacementTermExistsException,
				SupervisoryOrganizationTermExistsException,
				SupervisoryOrganizationExistsException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Places Blofeld on parole at a P&P office lasting longer than
		// parole placement term
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", null, "X");
		DateRange pnpRange = new DateRange(
				this.parseDateText("12/12/2018"),
				this.parseDateText("12/12/2021"));
		DateRange paroleRange = DateRange.adjustEndDate(
				pnpRange, this.parseDateText("12/12/2019"));
		this.placementTermDelegate.create(offender, paroleRange,
				this.supervisoryOrganizationTermDelegate.create(
						offender, pnpRange,
						this.supervisoryOrganizationDelegate.create(
								"P&P Office", "POFF", null)),
				this.correctionalStatusTermDelegate.create(
						offender, paroleRange,
						this.correctionalStatusDelegate
							.create("Parole", "PAR", false, (short) 1, true)),
				this.placementTermChangeReasonDelegate.create(
						"Board Decision", (short) 1, true, true),
				null, false);
		
		// Action - attempts to place offender securely after parole term
		// but within range of conflicting term of P&P office supervision
		this.placementTermService.create(offender,
				this.supervisoryOrganizationDelegate.create(
						"Prison", "PRSN", null),
				this.correctionalStatusDelegate.create(
						"Secure", "SEC", true, (short) 1, true),
				new DateRange(paroleRange.getEndDate(),
						this.parseDateText("12/12/2020")),
				this.placementTermChangeReasonDelegate.create(
						"Parole Revoked", (short) 2, true, true),
				null);
	}
	
	/**
	 * Tests that attempts to place offender when conflicting correctional
	 * status terms exist are prevented with
	 * {@code CorrectionalStatusTermConflictException}. Terms that cannot be
	 * adjusted cause such conflicts.
	 * 
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist - asserted
	 * @throws CorrectionalStatusTermExistsException if correctional status
	 * term exists
	 * @throws CorrectionalStatusExistsException if correctional status exists
	 * @throws PlacementTermExistsException if placement term exists
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * chagne reason exists
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exists
	 */
	@Test(expectedExceptions = {CorrectionalStatusTermConflictException.class})
	public void testCorrectionalStatusTermConflictException()
			throws CorrectionalStatusTermConflictException,
				CorrectionalStatusTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermExistsException,
				PlacementTermChangeReasonExistsException,
				SupervisoryOrganizationTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender on consecutive secure, parole and probation
		// correctional statuses
		Offender offender = this.offenderDelegate
				.createWithoutIdentity("Blofeld", "Julius", null, "X");
		DateRange secureRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2012"));
		this.correctionalStatusTermDelegate.create(offender, secureRange,
				this.correctionalStatusDelegate.create(
						"Secure", "SEC", true, (short) 1, true));
		DateRange paroleRange = new DateRange(
				secureRange.getEndDate(),
				this.parseDateText("12/12/2014"));
		this.correctionalStatusTermDelegate.create(offender, paroleRange,
				this.correctionalStatusDelegate.create(
						"Parole", "PAR", false, (short) 2, true));
		DateRange probationRange = new DateRange(
				paroleRange.getEndDate(),
				this.parseDateText("12/12/2016"));
		this.correctionalStatusTermDelegate.create(offender, probationRange,
				this.correctionalStatusDelegate.create(
						"Probation", "PRO", false, (short) 3, true));
		
		// Action - attempts to place offender on "Alt-Secure" status between
		// secure and probation correctional statuses
		DateRange altSecureRange = new DateRange(
				this.parseDateText("12/12/2011"),
				this.parseDateText("12/12/2015"));
		this.placementTermService.create(offender, null,
				this.correctionalStatusDelegate.create(
						"Alt-Secure", "ALT", true, (short) 4, true),
				altSecureRange,
				this.placementTermChangeReasonDelegate.create(
						"Board Decision", (short) 1, true, true),
				null);
	}
	
	/**
	 * Tests that attempts to place offender when conflicting supervisory
	 * organization terms exist are prevented with
	 * {@code SupervisoryOrganizationTermConflictException}. Terms that cannot
	 * be adjusted cause such conflicts.
	 * 
	 * @throws SupervisoryOrganizationTermConflictException if conflicting
	 * supervisory organization terms exist
	 * @throws SupervisoryOrganizationExistsException if supervisory
	 * organization exists 
	 * @throws SupervisoryOrganizationTermExistsException if supervisory
	 * organization terms exist 
	 * @throws PlacementTermConflictException if conflicting placement terms
	 * exist
	 * @throws CorrectionalStatusTermConflictException if conflicting
	 * correctional status terms exist 
	 * @throws PlacementTermChangeReasonExistsException if placement term
	 * change reasons exist 
	 * @throws CorrectionalStatusExistsException if correctional status term
	 * exists 
	 * @throws PlacementTermExistsException if placement term exists 
	 */
	@Test(expectedExceptions
			= {SupervisoryOrganizationTermConflictException.class})
	public void testSupervisoryOrganizationTermConflictException()
			throws SupervisoryOrganizationTermConflictException,
				SupervisoryOrganizationTermExistsException,
				SupervisoryOrganizationExistsException,
				PlacementTermExistsException,
				CorrectionalStatusExistsException,
				PlacementTermChangeReasonExistsException,
				CorrectionalStatusTermConflictException,
				PlacementTermConflictException {
		
		// Arranges offender consecutively at a prison, pre-release and
		// P&P office
		Offender offender = this.offenderDelegate.createWithoutIdentity(
				"Blofeld", "Julius", "Ernst", "Jr");
		DateRange prisonRange = new DateRange(
				this.parseDateText("12/12/2010"),
				this.parseDateText("12/12/2012"));
		this.supervisoryOrganizationTermDelegate.create(offender, prisonRange,
				this.supervisoryOrganizationDelegate.create(
						"Prison", "PRSN", null));
		DateRange prereleaseRange = new DateRange(
				prisonRange.getEndDate(),
				this.parseDateText("12/12/2014"));
		this.supervisoryOrganizationTermDelegate.create(
				offender, prereleaseRange,
				this.supervisoryOrganizationDelegate.create(
						"Prerelease", "PRE", null));
		DateRange pnpRange = new DateRange(
				prereleaseRange.getEndDate(),
				this.parseDateText("12/12/2016"));
		this.supervisoryOrganizationTermDelegate.create(offender, pnpRange,
				this.supervisoryOrganizationDelegate.create(
						"P&P Office", "PNPOF", null));
		
		// Action - attempts to place offender securely at regional prison
		// between prison and P&P officer supervisory organization terms
		DateRange placementRange = new DateRange(
				this.parseDateText("12/12/2011"),
				this.parseDateText("12/12/2015"));
		this.placementTermService.create(offender,
				this.supervisoryOrganizationDelegate
					.create("Regional Prison", "RP", null),
				this.correctionalStatusDelegate
					.create("Secure", "SEC", true, (short) 1, true),
				placementRange,
				this.placementTermChangeReasonDelegate
					.create("Facility Transfer", (short) 1, true, true),
				null);
	}
	
	/* Helper methods. */
	
	// Parses date text
	private Date parseDateText(final String dateText) {
		CustomDateEditor customEditor = this.customDateEditorFactory
				.createCustomDateOnlyEditor(true);
		customEditor.setAsText(dateText);
		return (Date) customEditor.getValue();
	}
}