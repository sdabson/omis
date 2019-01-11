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
package omis.stg.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.exception.SecurityThreatGroupActivityLevelExistsException;
import omis.stg.exception.SecurityThreatGroupAffiliationExistsException;
import omis.stg.exception.SecurityThreatGroupAffiliationNoteExistsException;
import omis.stg.exception.SecurityThreatGroupChapterExistsException;
import omis.stg.exception.SecurityThreatGroupExistsException;
import omis.stg.exception.SecurityThreatGroupRankExistsException;
import omis.stg.service.SecurityThreatGroupAffiliationService;
import omis.stg.service.delegate.SecurityThreatGroupActivityLevelDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationNoteDelegate;
import omis.stg.service.delegate.SecurityThreatGroupChapterDelegate;
import omis.stg.service.delegate.SecurityThreatGroupDelegate;
import omis.stg.service.delegate.SecurityThreatGroupRankDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.user.service.delegate.UserAccountDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create security threat group affiliation notes.
 *
 * @author Josh Divine
 * @author Sheronda Vaughn
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"stg", "service"})
public class SecurityThreatGroupAffiliationServiceCreateNoteTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {
	
	/* Delegates. */

	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupDelegate")
	private SecurityThreatGroupDelegate securityThreatGroupDelegate;
	
	@Autowired
	@Qualifier("stateDelegate")
	private StateDelegate stateDelegate;
	
	@Autowired
	@Qualifier("countryDelegate")
	private CountryDelegate countryDelegate;
	
	@Autowired
	@Qualifier("cityDelegate")
	private CityDelegate cityDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityLevelDelegate")
	private SecurityThreatGroupActivityLevelDelegate 
		securityThreatGroupActivityLevelDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupChapterDelegate")
	private SecurityThreatGroupChapterDelegate securityThreatGroupChapterDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupRankDelegate")
	private SecurityThreatGroupRankDelegate securityThreatGroupRankDelegate;
	
	@Autowired
	@Qualifier("userAccountDelegate")
	private UserAccountDelegate accountDelegate;
	
	@Autowired
	@Qualifier("verificationMethodDelegate")
	private VerificationMethodDelegate verificationMethodDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationDelegate")
	private SecurityThreatGroupAffiliationDelegate 
		securityThreatGroupAffiliationDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationNoteDelegate")
	private SecurityThreatGroupAffiliationNoteDelegate 
		securityThreatGroupAffiliationNoteDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("securityThreatGroupAffiliationService")
	private SecurityThreatGroupAffiliationService 
		securityThreatGroupAffiliationService;

	/* Test methods. */
	
	/**
	 * Tests creation of a security threat group affiliation note.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws SecurityThreatGroupAffiliationNoteExistsException 
	 * @throws SecurityThreatGroupExistsException 
	 * @throws SecurityThreatGroupActivityLevelExistsException 
	 * @throws SecurityThreatGroupRankExistsException 
	 * @throws SecurityThreatGroupChapterExistsException 
	 * @throws SecurityThreatGroupAffiliationExistsException 
	 */
	@Test
	public void testCreateNote() throws DuplicateEntityFoundException, 
		SecurityThreatGroupAffiliationNoteExistsException, SecurityThreatGroupExistsException, 
		SecurityThreatGroupActivityLevelExistsException, SecurityThreatGroupRankExistsException, 
		SecurityThreatGroupChapterExistsException, SecurityThreatGroupAffiliationExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("07/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		SecurityThreatGroup group = this.securityThreatGroupDelegate.create(
				"Group", state);
		City city = this.cityDelegate.create("City", true, state, country);
		SecurityThreatGroupActivityLevel activityLevel = 
				this.securityThreatGroupActivityLevelDelegate.create("Level");
		SecurityThreatGroupChapter chapter = 
				this.securityThreatGroupChapterDelegate.create("Chapter", 
						group);
		SecurityThreatGroupRank rank = this.securityThreatGroupRankDelegate
				.create("Rank", group);
		String moniker = "Moniker";
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		SecurityThreatGroupAffiliation affiliation = 
				this.securityThreatGroupAffiliationDelegate.create(offender,
						dateRange, group, activityLevel, chapter, rank, state, 
						city, moniker, comment, verificationSignature);
		Date date = this.parseDateText("03/01/2017");
		String note = "Note";

		// Action
		SecurityThreatGroupAffiliationNote securityThreatGroupAffiliationNote = 
				this.securityThreatGroupAffiliationService.createNote(
						affiliation, date, note);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("affiliation", affiliation)
			.addExpectedValue("date", date)
			.addExpectedValue("note", note)
			.performAssertions(securityThreatGroupAffiliationNote);
	}

	/**
	 * Tests {@code SecurityThreatGroupAffiliationNoteExistsException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 * @throws SecurityThreatGroupAffiliationNoteExistsException 
	 * @throws SecurityThreatGroupAffiliationExistsException 
	 * @throws SecurityThreatGroupRankExistsException 
	 * @throws SecurityThreatGroupChapterExistsException 
	 * @throws SecurityThreatGroupExistsException 
	 * @throws SecurityThreatGroupActivityLevelExistsException 
	 */
	@Test(expectedExceptions = {SecurityThreatGroupAffiliationNoteExistsException.class})
	public void testSecurityThreatGroupAffiliationNoteExistsException() 
			throws DuplicateEntityFoundException, SecurityThreatGroupAffiliationNoteExistsException, 
			SecurityThreatGroupAffiliationExistsException, SecurityThreatGroupRankExistsException, 
			SecurityThreatGroupChapterExistsException, SecurityThreatGroupExistsException, 
			SecurityThreatGroupActivityLevelExistsException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("07/01/2017"));
		Country country = this.countryDelegate.create("Country", "C", true);
		State state = this.stateDelegate.create("State", "ST", country, true, 
				true);
		SecurityThreatGroup group = this.securityThreatGroupDelegate.create(
				"Group", state);
		City city = this.cityDelegate.create("City", true, state, country);
		SecurityThreatGroupActivityLevel activityLevel = 
				this.securityThreatGroupActivityLevelDelegate.create("Level");
		SecurityThreatGroupChapter chapter = 
				this.securityThreatGroupChapterDelegate.create("Chapter", 
						group);
		SecurityThreatGroupRank rank = this.securityThreatGroupRankDelegate
				.create("Rank", group);
		String moniker = "Moniker";
		String comment = "Comment";
		VerificationMethod method = this.verificationMethodDelegate.create(
				"Method", (short) 1, true);
		VerificationSignature verificationSignature = new VerificationSignature(
				this.accountDelegate.findByUsername("AUDIT"), 
				this.parseDateText("02/01/2017"), true, method);
		SecurityThreatGroupAffiliation affiliation = 
				this.securityThreatGroupAffiliationDelegate.create(offender,
						dateRange, group, activityLevel, chapter, rank, state, 
						city, moniker, comment, verificationSignature);
		Date date = this.parseDateText("03/01/2017");
		String note = "Note";
		this.securityThreatGroupAffiliationNoteDelegate.addNote(affiliation, 
				date, note);

		// Action
		this.securityThreatGroupAffiliationService.createNote(affiliation, date,
				note);
	}

	// Parses date text
	private Date parseDateText(final String text) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(text);
		} catch (ParseException e) {
			throw new RuntimeException("Parse error", e);
		}
	}
}