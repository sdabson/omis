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
import omis.specialneed.service.SpecialNeedService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to update documents.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SpecialNeedServiceUpdateDocumentTests
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */

	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;
	
	/* Services. */

	@Autowired
	@Qualifier("specialNeedService")
	private SpecialNeedService specialNeedService;

	/* Test methods. */

	/**
	 * Tests the update of the date for a document.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateDocumentDate() throws DuplicateEntityFoundException {
		// Arrangements
		Date date = this.parseDateText("01/01/2017");
		String title = "Title";
		String filename = "filename";
		String fileExtension = "pdf";
		Document document = this.documentDelegate.create(date, filename, 
				fileExtension, title);
		Date newDate = this.parseDateText("01/03/2017");
		
		// Action
		document = this.specialNeedService.updateDocument(document, newDate, 
				title);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", newDate)
			.addExpectedValue("title", title)
			.performAssertions(document);
	}
	
	/**
	 * Tests the update of the title for a document.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testUpdateDocumentTitle() throws DuplicateEntityFoundException {
		// Arrangements
		Date date = this.parseDateText("01/01/2017");
		String title = "Title";
		String filename = "filename";
		String fileExtension = "pdf";
		Document document = this.documentDelegate.create(date, filename, 
				fileExtension, title);
		String newTitle = "New Title";
		
		// Action
		document = this.specialNeedService.updateDocument(document, date, 
				newTitle);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", date)
			.addExpectedValue("title", newTitle)
			.performAssertions(document);
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