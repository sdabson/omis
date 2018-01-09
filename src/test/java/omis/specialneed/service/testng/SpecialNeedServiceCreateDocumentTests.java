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
 * Tests method to create documents.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class SpecialNeedServiceCreateDocumentTests
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
	 * Tests the creation of a document.
	 * 
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	@Test
	public void testCreateDocument() throws DuplicateEntityFoundException {
		// Arrangements
		Date date = this.parseDateText("01/01/2017");
		String title = "Title";
		String filename = "filename";
		String fileExtension = "pdf";

		// Action
		Document document = this.specialNeedService.createDocument(date, title, 
				filename, fileExtension);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", date)
			.addExpectedValue("title", title)
			.addExpectedValue("filename", filename)
			.addExpectedValue("fileExtension", fileExtension)
			.performAssertions(document);
	}

	/**
	 * Tests that {@code DuplicateEntityFoundException} is thrown.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test(expectedExceptions = {DuplicateEntityFoundException.class})
	public void testDuplicateEntityFoundException() 
			throws DuplicateEntityFoundException {
		// Arrangements
		Date date = this.parseDateText("01/01/2017");
		String title = "Title";
		String filename = "filename";
		String fileExtension = "pdf";
		this.documentDelegate.create(date, filename, fileExtension, title);

		// Action
		this.specialNeedService.createDocument(date, title, filename, 
				fileExtension);
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