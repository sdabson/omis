package omis.tierdesignation.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.tierdesignation.domain.OffenderTierDesignation;
import omis.tierdesignation.domain.TierChangeReason;
import omis.tierdesignation.domain.TierLevel;
import omis.tierdesignation.domain.TierSource;
import omis.tierdesignation.service.OffenderTierDesignationService;
import omis.tierdesignation.service.delegate.OffenderTierDesignationDelegate;
import omis.tierdesignation.service.delegate.TierChangeReasonDelegate;
import omis.tierdesignation.service.delegate.TierLevelDelegate;
import omis.tierdesignation.service.delegate.TierSourceDelegate;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create tier designations.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"tierdesignation", "service"})
public class OffenderTierDesignationServiceUpdateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("tierLevelDelegate")
	private TierLevelDelegate tierLevelDelegate;
	
	@Autowired
	@Qualifier("tierSourceDelegate")
	private TierSourceDelegate tierSourceDelegate;
	
	@Autowired
	@Qualifier("tierChangeReasonDelegate")
	private TierChangeReasonDelegate tierChangeReasonDelegate;
	
	@Autowired
	@Qualifier("offenderTierDesignationDelegate")
	private OffenderTierDesignationDelegate offenderTierDesignationDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("offenderTierDesignationService")
	private OffenderTierDesignationService offenderTierDesignationService;
	
	/* Test methods. */
	
	/**
	 * Tests update of the level for a tier designation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateLevel() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		TierLevel level = this.tierLevelDelegate.create("Level", (short) 1, 
				true);
		TierSource source = this.tierSourceDelegate.create("Source", (short) 1, 
				true);
		TierChangeReason changeReason = this.tierChangeReasonDelegate.create(
				"Reason", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/31/2017"));
		String comment = "Comment";
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationDelegate.create(offender, level, 
						source, changeReason, dateRange, comment);
		TierLevel newLevel = this.tierLevelDelegate.create("Level 2", (short) 2, 
				true);
		
		// Action
		tierDesignation = this.offenderTierDesignationService.update(
				tierDesignation, newLevel, source, changeReason, dateRange, 
				comment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("level", newLevel)
			.addExpectedValue("source", source)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("comment", comment)
			.performAssertions(tierDesignation);
	}
	
	/**
	 * Tests update of the source for a tier designation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateSource() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		TierLevel level = this.tierLevelDelegate.create("Level", (short) 1, 
				true);
		TierSource source = this.tierSourceDelegate.create("Source", (short) 1, 
				true);
		TierChangeReason changeReason = this.tierChangeReasonDelegate.create(
				"Reason", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/31/2017"));
		String comment = "Comment";
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationDelegate.create(offender, level, 
						source, changeReason, dateRange, comment);
		TierSource newSource = this.tierSourceDelegate.create("Source 2", 
				(short) 2, true);
		
		// Action
		tierDesignation = this.offenderTierDesignationService.update(
				tierDesignation, level, newSource, changeReason, dateRange, 
				comment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("level", level)
			.addExpectedValue("source", newSource)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("comment", comment)
			.performAssertions(tierDesignation);
	}
	
	/**
	 * Tests update of the change reason for a tier designation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateChangeReason() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		TierLevel level = this.tierLevelDelegate.create("Level", (short) 1, 
				true);
		TierSource source = this.tierSourceDelegate.create("Source", (short) 1, 
				true);
		TierChangeReason changeReason = this.tierChangeReasonDelegate.create(
				"Reason", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/31/2017"));
		String comment = "Comment";
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationDelegate.create(offender, level, 
						source, changeReason, dateRange, comment);
		TierChangeReason newChangeReason = this.tierChangeReasonDelegate.create(
				"Reason 2", (short) 2, true);
		
		// Action
		tierDesignation = this.offenderTierDesignationService.update(
				tierDesignation, level, source, newChangeReason, dateRange, 
				comment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("level", level)
			.addExpectedValue("source", source)
			.addExpectedValue("changeReason", newChangeReason)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("comment", comment)
			.performAssertions(tierDesignation);
	}
	
	/**
	 * Tests update of the date range for a tier designation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateDateRange() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		TierLevel level = this.tierLevelDelegate.create("Level", (short) 1, 
				true);
		TierSource source = this.tierSourceDelegate.create("Source", (short) 1, 
				true);
		TierChangeReason changeReason = this.tierChangeReasonDelegate.create(
				"Reason", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/31/2017"));
		String comment = "Comment";
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationDelegate.create(offender, level, 
						source, changeReason, dateRange, comment);
		DateRange newDateRange = new DateRange(this.parseDateText("05/01/2017"), 
				this.parseDateText("12/31/2017"));
		
		// Action
		tierDesignation = this.offenderTierDesignationService.update(
				tierDesignation, level, source, changeReason, newDateRange, 
				comment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("level", level)
			.addExpectedValue("source", source)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("dateRange", newDateRange)
			.addExpectedValue("comment", comment)
			.performAssertions(tierDesignation);
	}
	
	/**
	 * Tests update of the comment for a tier designation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateComment() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		TierLevel level = this.tierLevelDelegate.create("Level", (short) 1, 
				true);
		TierSource source = this.tierSourceDelegate.create("Source", (short) 1, 
				true);
		TierChangeReason changeReason = this.tierChangeReasonDelegate.create(
				"Reason", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/31/2017"));
		String comment = "Comment";
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationDelegate.create(offender, level, 
						source, changeReason, dateRange, comment);
		String newComment = "Comment 2";
		
		// Action
		tierDesignation = this.offenderTierDesignationService.update(
				tierDesignation, level, source, changeReason, dateRange, 
				newComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("level", level)
			.addExpectedValue("source", source)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("comment", newComment)
			.performAssertions(tierDesignation);
	}
	
	/**
	 * Tests {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		TierLevel level = this.tierLevelDelegate.create("Level", (short) 1, 
				true);
		TierSource source = this.tierSourceDelegate.create("Source", (short) 1, 
				true);
		TierChangeReason changeReason = this.tierChangeReasonDelegate.create(
				"Reason", (short) 1, true);
		DateRange dateRange = new DateRange(this.parseDateText("01/01/2017"), 
				this.parseDateText("12/31/2017"));
		String comment = "Comment";
		this.offenderTierDesignationDelegate.create(offender, level, source, 
				changeReason, dateRange, comment);
		TierLevel secondLevel = this.tierLevelDelegate.create("Level 2", 
				(short) 2, true);
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationDelegate.create(offender, 
						secondLevel, source, changeReason, dateRange, comment);
		// Action
		this.offenderTierDesignationService.update(tierDesignation, level, 
				source, changeReason, dateRange, comment);
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
