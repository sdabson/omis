package omis.stg.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.person.domain.Person;
import omis.person.service.delegate.PersonDelegate;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.service.SecurityThreatGroupActivityService;
import omis.stg.service.delegate.SecurityThreatGroupActivityDelegate;
import omis.stg.service.delegate.SecurityThreatGroupActivityInvolvementDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

public class SecurityThreatGroupActivityServiceUpdateInvolvementNarrativeTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("personDelegate")
	private PersonDelegate personDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityDelegate")
	private SecurityThreatGroupActivityDelegate 
		securityThreatGroupActivityDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("securityThreatGroupActivityInvolvementDelegate")
	private SecurityThreatGroupActivityInvolvementDelegate 
		securityThreatGroupActivityInvolvementDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("securityThreatGroupActivityService")
	private SecurityThreatGroupActivityService 
		securityThreatGroupActivityService;

	/* Test methods. */
	
	@Test
	public void testUpdateInvolvementNarrative() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		String narrative = "Narrative";
		SecurityThreatGroupActivityInvolvement involvement = 
				this.securityThreatGroupActivityInvolvementDelegate
						.involveOffender(offender, activity, narrative);
		String newNarrative = "New narrative";
		
		// Action
		involvement = this.securityThreatGroupActivityService
				.updateInvolvementNarrative(involvement, newNarrative);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("narrative", newNarrative)
			.performAssertions(involvement);
	}

	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		Date reportDate = this.parseDateText("01/01/2017");
		Person reportedBy = this.personDelegate.create("Doe", "John", null, 
				null);
		String summary = "Summary";
		SecurityThreatGroupActivity activity = 
				this.securityThreatGroupActivityDelegate.create(reportDate, 
						reportedBy, summary);
		String narrative = "Narrative";
		this.securityThreatGroupActivityInvolvementDelegate.involveOffender(
				offender, activity, narrative);
		String secondNarrative = "Second Narrative";
		SecurityThreatGroupActivityInvolvement involvement = 
				this.securityThreatGroupActivityInvolvementDelegate
						.involveOffender(offender, activity, secondNarrative);
						
		// Action
		involvement = this.securityThreatGroupActivityService
				.updateInvolvementNarrative(involvement, narrative);
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