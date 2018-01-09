package omis.specialneed.service.testng;

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
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;
import omis.specialneed.service.SpecialNeedService;
import omis.specialneed.service.delegate.SpecialNeedCategoryDelegate;
import omis.specialneed.service.delegate.SpecialNeedClassificationDelegate;
import omis.specialneed.service.delegate.SpecialNeedDelegate;
import omis.specialneed.service.delegate.SpecialNeedSourceDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create special needs.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"specialneed", "service"})
public class SpecialNeedServiceCreateTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Service delegates. */
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	@Autowired
	@Qualifier("specialNeedCategoryDelegate")
	private SpecialNeedCategoryDelegate specialNeedCategoryDelegate;
	
	@Autowired
	@Qualifier("specialNeedClassificationDelegate")
	private SpecialNeedClassificationDelegate specialNeedClassificationDelegate;
	
	@Autowired
	@Qualifier("specialNeedSourceDelegate")
	private SpecialNeedSourceDelegate specialNeedSourceDelegate;
	
	@Autowired
	@Qualifier("specialNeedDelegate")
	private SpecialNeedDelegate specialNeedDelegate;
	
	/* Service to test. */
	
	@Autowired
	@Qualifier("specialNeedService")
	private SpecialNeedService specialNeedService;
	
	/* Test methods. */
	
	/**
	 * Tests creation of a special need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreate() throws DuplicateEntityFoundException {
		// Arrangements
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith", 
				"John", "Jay", null);
		String comment = "Comment";
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = this.parseDateText("05/01/2017");
		SpecialNeedClassification classification = 
				this.specialNeedClassificationDelegate.create("Classification", 
						true);
		SpecialNeedCategory category = this.specialNeedCategoryDelegate.create(
				"Category", classification, true);
		SpecialNeedSource source = this.specialNeedSourceDelegate.create(
				"Source", true);
		String sourceComment = "Source comment";
		
		// Action
		SpecialNeed specialNeed = this.specialNeedService.create(comment, 
				startDate, endDate, classification, category, source, 
				sourceComment, offender);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("classification", classification)
			.addExpectedValue("category", category)
			.addExpectedValue("source", source)
			.addExpectedValue("sourceComment", sourceComment)
			.performAssertions(specialNeed);
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
		String comment = "Comment";
		Date startDate = this.parseDateText("01/01/2017");
		Date endDate = this.parseDateText("05/01/2017");
		SpecialNeedClassification classification = 
				this.specialNeedClassificationDelegate.create("Classification", 
						true);
		SpecialNeedCategory category = this.specialNeedCategoryDelegate.create(
				"Category", classification, true);
		SpecialNeedSource source = this.specialNeedSourceDelegate.create(
				"Source", true);
		String sourceComment = "Source comment";
		this.specialNeedDelegate.create(comment, startDate, endDate, 
				classification, category, source, sourceComment, offender);
		
		// Action
		this.specialNeedService.create(comment, startDate, endDate, 
				classification, category, source, sourceComment, offender);
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
