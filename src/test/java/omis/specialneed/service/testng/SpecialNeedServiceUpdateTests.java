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
 * Tests method to update a special need.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"specialneed", "service"})
public class SpecialNeedServiceUpdateTests
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
	 * Tests update of the comment for a special need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateComment() throws DuplicateEntityFoundException {
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
		SpecialNeed specialNeed = this.specialNeedDelegate.create(comment, 
				startDate, endDate, classification, category, source, 
				sourceComment, offender);
		String newComment = "New comment";
		
		// Action
		specialNeed = this.specialNeedService.update(specialNeed, newComment, 
				startDate, endDate, category, source, sourceComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("comment", newComment)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("classification", classification)
			.addExpectedValue("category", category)
			.addExpectedValue("source", source)
			.addExpectedValue("sourceComment", sourceComment)
			.performAssertions(specialNeed);
	}
	
	/**
	 * Tests update of the date range for a special need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateDateRange() throws DuplicateEntityFoundException {
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
		SpecialNeed specialNeed = this.specialNeedDelegate.create(comment, 
				startDate, endDate, classification, category, source, 
				sourceComment, offender);
		Date newStartDate = this.parseDateText("01/05/2017");
		Date newEndDate = this.parseDateText("05/01/2018");
		
		// Action
		specialNeed = this.specialNeedService.update(specialNeed, comment, 
				newStartDate, newEndDate, category, source, sourceComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", new DateRange(newStartDate, 
					newEndDate))
			.addExpectedValue("classification", classification)
			.addExpectedValue("category", category)
			.addExpectedValue("source", source)
			.addExpectedValue("sourceComment", sourceComment)
			.performAssertions(specialNeed);
	}
	
	/**
	 * Tests update of the category for a special need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateCategory() throws DuplicateEntityFoundException {
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
		SpecialNeed specialNeed = this.specialNeedDelegate.create(comment, 
				startDate, endDate, classification, category, source, 
				sourceComment, offender);
		SpecialNeedCategory newCategory = this.specialNeedCategoryDelegate
				.create("Category 2", classification, true);
		
		// Action
		specialNeed = this.specialNeedService.update(specialNeed, comment, 
				startDate, endDate, newCategory, source, sourceComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("classification", classification)
			.addExpectedValue("category", newCategory)
			.addExpectedValue("source", source)
			.addExpectedValue("sourceComment", sourceComment)
			.performAssertions(specialNeed);
	}
	
	/**
	 * Tests update of the source for a special need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateSource() throws DuplicateEntityFoundException {
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
		SpecialNeed specialNeed = this.specialNeedDelegate.create(comment, 
				startDate, endDate, classification, category, source, 
				sourceComment, offender);
		SpecialNeedSource newSource = this.specialNeedSourceDelegate.create(
				"Source 2", true);
		
		// Action
		specialNeed = this.specialNeedService.update(specialNeed, comment, 
				startDate, endDate, category, newSource, sourceComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("classification", classification)
			.addExpectedValue("category", category)
			.addExpectedValue("source", newSource)
			.addExpectedValue("sourceComment", sourceComment)
			.performAssertions(specialNeed);
	}
	
	/**
	 * Tests update of the source comment for a special need.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateSourceComment() throws DuplicateEntityFoundException {
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
		SpecialNeed specialNeed = this.specialNeedDelegate.create(comment, 
				startDate, endDate, classification, category, source, 
				sourceComment, offender);
		String newSourceComment = "New comment";
		
		// Action
		specialNeed = this.specialNeedService.update(specialNeed, comment, 
				startDate, endDate, category, source, newSourceComment);
		
		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("offender", offender)
			.addExpectedValue("comment", comment)
			.addExpectedValue("dateRange", new DateRange(startDate, endDate))
			.addExpectedValue("classification", classification)
			.addExpectedValue("category", category)
			.addExpectedValue("source", source)
			.addExpectedValue("sourceComment", newSourceComment)
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
		SpecialNeedCategory secondCategory = this.specialNeedCategoryDelegate
				.create("Category 2", classification, true);
		SpecialNeed specialNeed = this.specialNeedDelegate.create(comment, 
				startDate, endDate, classification, secondCategory, source, 
				sourceComment, offender);
		
		// Action
		this.specialNeedService.update(specialNeed, comment, startDate, endDate, 
				category, source, sourceComment);
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
