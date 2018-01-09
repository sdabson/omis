package omis.specialneed.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.document.domain.Document;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.service.delegate.OffenderDelegate;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedSource;
import omis.specialneed.service.SpecialNeedService;
import omis.specialneed.service.delegate.SpecialNeedAssociableDocumentCategoryDelegate;
import omis.specialneed.service.delegate.SpecialNeedAssociableDocumentDelegate;
import omis.specialneed.service.delegate.SpecialNeedCategoryDelegate;
import omis.specialneed.service.delegate.SpecialNeedClassificationDelegate;
import omis.specialneed.service.delegate.SpecialNeedDelegate;
import omis.specialneed.service.delegate.SpecialNeedSourceDelegate;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create special need associable documents.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SpecialNeedServiceCreateSpecialNeedAssociableDocumentTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("specialNeedDelegate")
	private SpecialNeedDelegate specialNeedDelegate;
	
	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;
	
	@Autowired
	@Qualifier("specialNeedAssociableDocumentCategoryDelegate")
	private SpecialNeedAssociableDocumentCategoryDelegate 
			specialNeedAssociableDocumentCategoryDelegate;
	
	@Autowired
	@Qualifier("specialNeedAssociableDocumentDelegate")
	private SpecialNeedAssociableDocumentDelegate 
			specialNeedAssociableDocumentDelegate;
	
	@Autowired
	@Qualifier("specialNeedClassificationDelegate")
	private SpecialNeedClassificationDelegate specialNeedClassificationDelegate;
	
	@Autowired
	@Qualifier("specialNeedCategoryDelegate")
	private SpecialNeedCategoryDelegate specialNeedCategoryDelegate;
	
	@Autowired
	@Qualifier("specialNeedSourceDelegate")
	private SpecialNeedSourceDelegate specialNeedSourceDelegate;
	
	@Autowired
	@Qualifier("offenderDelegate")
	private OffenderDelegate offenderDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("specialNeedService")
	private SpecialNeedService specialNeedService;

	/* Test methods. */

	@Test
	public void testCreateSpecialNeedAssociableDocument() 
			throws DuplicateEntityFoundException {
		// Arrangements
		SpecialNeedClassification classification = 
				this.specialNeedClassificationDelegate.create("Classification", 
						true);
		SpecialNeedCategory category = this.specialNeedCategoryDelegate.create(
				"Category", classification, true);
		SpecialNeedSource source = this.specialNeedSourceDelegate.create(
				"Source", true);
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		SpecialNeed specialNeed = this.specialNeedDelegate.create("Comment", 
				this.parseDateText("01/01/2017"), 
				this.parseDateText("01/31/2017"), classification, category, 
				source, "Source Comment", offender);
		Document document = this.documentDelegate.create(
				this.parseDateText("01/01/2017"), "filename", "pdf", "Title");
		SpecialNeedAssociableDocumentCategory documentCategory = this
				.specialNeedAssociableDocumentCategoryDelegate.create(
						"Category", true);

		// Action
		SpecialNeedAssociableDocument specialNeedAssociableDocument = this
				.specialNeedService.createSpecialNeedAssociableDocument(
						specialNeed, document, documentCategory);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("specialNeed", specialNeed)
			.addExpectedValue("document", document)
			.addExpectedValue("category", documentCategory)
			.performAssertions(specialNeedAssociableDocument);
	}

	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() throws DuplicateEntityFoundException {
		// Arrangements
		SpecialNeedClassification classification = 
				this.specialNeedClassificationDelegate.create("Classification", 
						true);
		SpecialNeedCategory category = this.specialNeedCategoryDelegate.create(
				"Category", classification, true);
		SpecialNeedSource source = this.specialNeedSourceDelegate.create(
				"Source", true);
		Offender offender = this.offenderDelegate.createWithoutIdentity("Smith",
				"John", "Jay", null);
		SpecialNeed specialNeed = this.specialNeedDelegate.create("Comment", 
				this.parseDateText("01/01/2017"), 
				this.parseDateText("01/31/2017"), classification, category, 
				source, "Source Comment", offender);
		Document document = this.documentDelegate.create(
				this.parseDateText("01/01/2017"), "filename", "pdf", "Title");
		SpecialNeedAssociableDocumentCategory documentCategory = this
				.specialNeedAssociableDocumentCategoryDelegate.create(
						"Category", true);
		this.specialNeedAssociableDocumentDelegate.create(specialNeed, document, 
				documentCategory);

		// Action
		this.specialNeedService.createSpecialNeedAssociableDocument(specialNeed,
				document, documentCategory);
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