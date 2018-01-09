package omis.financial.service.testng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.document.domain.Document;
import omis.document.service.delegate.DocumentDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.financial.service.FinancialProfileService;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;
import omis.util.PropertyValueAsserter;

/**
 * Tests method to create financial profile service document.
 *
 * @author Trevor Isles
 * @version 0.0.1 (August 3, 2017)
 * @since OMIS 3.0
 */
public class FinancialProfileServiceDocumentCreateTests 
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	/* Delegates. */
	
	@Autowired
	@Qualifier("documentDelegate")
	private DocumentDelegate documentDelegate;

	/* Services. */

	@Autowired
	@Qualifier("financialProfileService")
	private FinancialProfileService financialProfileService;

	/* Test methods. */

	/**
	 * Tests create of a financial profile document.
	 * 
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	@Test
	public void testCreateDocument() throws DuplicateEntityFoundException {
		
		// Arrangements
		Date documentDate = this.parseDateText("08/01/2017");
		
		String filename = "Test";
		
		String fileExtension = ".jpg";
		
		String title = "This is a test";

		// Action
		Document document = this.financialProfileService.createDocument(
				documentDate, filename, fileExtension, title);

		// Assertions
		PropertyValueAsserter.create()
			.addExpectedValue("date", documentDate)
			.addExpectedValue("filename", filename)
			.addExpectedValue("fileExtension", fileExtension)
			.addExpectedValue("title", title)
			.performAssertions(document);
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
		Date documentDate = this.parseDateText("08/01/2017");
		
		String filename = "Test";
		
		String fileExtension = ".jpg";
		
		String title = "This is a test";
		
		this.documentDelegate.create(documentDate, filename, fileExtension, 
				title);
		
		// Action
		this.financialProfileService.createDocument(documentDate, filename, 
				fileExtension, title);
		
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
