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
public class OffenderTierDesignationServiceCreateTests
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
	 * Tests creation of a tier designation.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreate() throws DuplicateEntityFoundException {
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
		
		// Action
		OffenderTierDesignation tierDesignation = 
				this.offenderTierDesignationService.save(offender, level, 
						source, changeReason, dateRange, comment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("level", level)
			.addExpectedValue("source", source)
			.addExpectedValue("changeReason", changeReason)
			.addExpectedValue("dateRange", dateRange)
			.addExpectedValue("comment", comment)
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
		
		// Action
		this.offenderTierDesignationService.save(offender, level, source, 
				changeReason, dateRange, comment);
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
